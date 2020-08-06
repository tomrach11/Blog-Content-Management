package com.tr.contentManager.dao;

import com.tr.contentManager.model.Content;
import com.tr.contentManager.model.Role;
import com.tr.contentManager.model.Tag;
import com.tr.contentManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContentDao<T> implements Dao<Content> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Content create(Content model) {
        final String INSERT_CONTENT = "INSERT INTO content (createDate, title, type, status, content, scheduleDate, expiredDate, titlePicture, userId) VALUE (?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_CONTENT, model.getCreateDate(), model.getTitle(), model.getType(), model.getStatus(), model.getContent(), model.getScheduleDate(), model.getExpiredDate(), model.getTitlePicture(), model.getUser().getId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(newId);

        //still miss picture
        insertIntoContentTag(model);

        return model;
    }

    @Override
    public List<Content> readAll() {
        final String SELECT_ALL_CONTENT = "SELECT * FROM content ORDER BY scheduleDate";
        List<Content> contentList = jdbc.query(SELECT_ALL_CONTENT, new ContentMapper());
        associateUserContent(contentList);
        associateContentTag(contentList);
        //still missing picture
        return contentList;
    }

    @Override
    public Content readById(int id) {
        final String SELECT_CONTENT_BY_ID = "SELECT * FROM content WHERE id = ?";
        Content content = jdbc.queryForObject(SELECT_CONTENT_BY_ID, new ContentMapper(), id);

        content.setUser(getUserForContent(content.getId()));
        content.setTags(getTagsForContent(content.getId()));
        //still missing picture

        return content;
    }

    @Override
    public void update(Content model) {
        final String UPDATE_CONTENT = "UPDATE content SET " +
                "createDate = ?, " +
                "title = ?, " +
                "type = ?, " +
                "status = ?, " +
                "content = ?, " +
                "scheduleDate = ?, " +
                "expiredDate = ?, " +
                "titlePicture = ?, " +
                "userId = ? " +
                "WHERE id = ?";
        jdbc.update(UPDATE_CONTENT, model.getCreateDate(), model.getTitle(), model.getType(), model.getStatus(), model.getContent(), model.getScheduleDate(), model.getExpiredDate(), model.getTitlePicture(), model.getUser().getId(), model.getId());

        final String DELETE_CONTENT_TAG = "DELETE FROM content_tag WHERE contentId = ?";
        jdbc.update(DELETE_CONTENT_TAG, model.getId());

        //still missing picture

        insertIntoContentTag(model);
    }

    @Override
    public void delete(int id) {
        final String DELETE_CONTENT_TAG = "DELETE FROM content_tag WHERE contentId = ?";
        jdbc.update(DELETE_CONTENT_TAG, id);

        final String DELETE_COMMENT = "DELETE FROM comment WHERE contentId = ?";
        jdbc.update(DELETE_COMMENT, id);

        final String DELETE_CONTENT = "DELETE FROM content WHERE id = ?";
        jdbc.update(DELETE_CONTENT, id);

        //still missing delete picture_content
    }

    private void insertIntoContentTag (Content content) {
        for(Tag tag : content.getTags()) {
            final String INSERT_CONTENT_TAG = "INSERT INTO content_tag (contentId, tagId) VALUES (?,?)";
            jdbc.update(INSERT_CONTENT_TAG, content.getId(), tag.getId());
        }
    }

    public User getUserForContent(int contentId) {
        final String SELECT_USER_BY_CONTENT_ID = "SELECT u.* FROM user u " +
                "JOIN content c ON u.Id = c.userId " +
                "WHERE c.id = ?";
        User user = jdbc.queryForObject(SELECT_USER_BY_CONTENT_ID, new UserMapper(), contentId);
        user.setRoles(getRoleForUser(user.getId()));
        return user;
    }

    private void associateUserContent(List<Content> contentList) {
        for (Content content : contentList) {
            User user = getUserForContent(content.getId());
            content.setUser(user);
        }
    }

    public List<Tag> getTagsForContent(int contentId) {
        final String SELECT_TAG_BY_CONTENT_ID = "SELECT t.* FROM tag t " +
                "JOIN content_tag ct ON t.id = ct.tagId " +
                "WHERE ct.contentId = ?";
        return jdbc.query(SELECT_TAG_BY_CONTENT_ID, new TagMapper(), contentId);
    }

    private void associateContentTag(List<Content> contentList) {
        for (Content content : contentList) {
            List<Tag> tagList = getTagsForContent(content.getId());
            content.setTags(tagList);
        }
    }

    public List<Role> getRoleForUser(int userId) {
        final String SELECT_ROLE_BY_USER_ID = "SELECT r.* FROM role r " +
                "JOIN user_role ur ON r.Id = ur.roleId " +
                "WHERE ur.userId = ?";
        return jdbc.query(SELECT_ROLE_BY_USER_ID, new RoleMapper(), userId);
    }

    public List<Content> getContentByType (String type) {
        List<Content> contentList = this.readAll();
        contentList = filterScheduleExpiredDate(contentList);
        List<Content> typeList = new ArrayList();
        for (Content content : contentList) {
            if (content.getType().equals(type) && content.getStatus().equals("public")) {
                typeList.add(content);
            }
        }
        return typeList;
    }

    public List<Content> getContentByTag (int tagId) {
        final String SELECT_CONTENT_BY_TAG = "SELECT c.* FROM content c " +
                "JOIN content_tag ct ON ct.contentId = c.id " +
                "WHERE ct.tagId = ? and c.status = ?";
        List<Content> contentList =  jdbc.query(SELECT_CONTENT_BY_TAG, new ContentMapper(), tagId, "public");
        contentList = filterScheduleExpiredDate(contentList);
        associateContentTag(contentList);
        associateUserContent(contentList);
        return contentList;
    }

    public List<Content> getContentBySearchTitle(String searchText) {
        searchText = "%" + searchText + "%";
        final String SELECT_CONTENT_BY_SEARCH_TITLE = "SELECT * FROM content WHERE title LIKE ?";
        List<Content> contentList = jdbc.query(SELECT_CONTENT_BY_SEARCH_TITLE, new ContentMapper(), searchText);
        contentList = filterScheduleExpiredDate(contentList);
        associateContentTag(contentList);
        associateUserContent(contentList);

        return contentList;
    }

    public List<Content> filterScheduleExpiredDate (List<Content>blogList) {
        List<Content> filteredBlogList = new ArrayList<>();
        for (Content blog : blogList) {
            if (blog.getExpiredDate() != null) {
                if (LocalDateTime.now().isAfter(blog.getScheduleDate()) && LocalDateTime.now().isBefore(blog.getExpiredDate())) {
                    filteredBlogList.add(blog);
                }
            } else {
                if (blog.getScheduleDate().isBefore(LocalDateTime.now())) {
                    filteredBlogList.add(blog);
                }
            }
        }
        return filteredBlogList;
    }
    public List<String> getPictureForContent(int contentId) {
        return null;
    }

    private void associateContentPicture(List<Content> contentList) {

    }

}
