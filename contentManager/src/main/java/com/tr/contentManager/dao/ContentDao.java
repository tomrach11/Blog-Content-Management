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
        //add content to db
        final String INSERT_CONTENT = "INSERT INTO content (createDate, title, type, status, content, scheduleDate, expiredDate, titlePicture, userId) VALUE (?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_CONTENT, model.getCreateDate(), model.getTitle(), model.getType(), model.getStatus(), model.getContent(), model.getScheduleDate(), model.getExpiredDate(), model.getTitlePicture(), model.getUser().getId());
        //get recent insert id and set it in content object
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(newId);

        //insert into bridge table
        insertIntoContentTag(model);

        return model;
    }

    @Override
    public List<Content> readAll() {
        final String SELECT_ALL_CONTENT = "SELECT * FROM content ORDER BY scheduleDate";
        List<Content> contentList = jdbc.query(SELECT_ALL_CONTENT, new ContentMapper());
        //add user object to each content in the list
        associateUserContent(contentList);
        //add tags to each content in the list
        associateContentTag(contentList);

        return contentList;
    }

    @Override
    public Content readById(int id) {
        final String SELECT_CONTENT_BY_ID = "SELECT * FROM content WHERE id = ?";
        Content content = jdbc.queryForObject(SELECT_CONTENT_BY_ID, new ContentMapper(), id);

        //set user and tags to content object
        content.setUser(getUserForContent(content.getId()));
        content.setTags(getTagsForContent(content.getId()));


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

        //delete all tags in content_tag so we can update new list to it
        final String DELETE_CONTENT_TAG = "DELETE FROM content_tag WHERE contentId = ?";
        jdbc.update(DELETE_CONTENT_TAG, model.getId());

        insertIntoContentTag(model);
    }

    @Override
    public void delete(int id) {
        //delete from other table that has content id first before delete the actual delete row
        final String DELETE_CONTENT_TAG = "DELETE FROM content_tag WHERE contentId = ?";
        jdbc.update(DELETE_CONTENT_TAG, id);

        final String DELETE_COMMENT = "DELETE FROM comment WHERE contentId = ?";
        jdbc.update(DELETE_COMMENT, id);

        final String DELETE_CONTENT = "DELETE FROM content WHERE id = ?";
        jdbc.update(DELETE_CONTENT, id);

    }

    private void insertIntoContentTag (Content content) {
        //loop from tag array in content
        for(Tag tag : content.getTags()) {
            final String INSERT_CONTENT_TAG = "INSERT INTO content_tag (contentId, tagId) VALUES (?,?)";
            jdbc.update(INSERT_CONTENT_TAG, content.getId(), tag.getId());
        }
    }

    public User getUserForContent(int contentId) {
        //get user object for content
        final String SELECT_USER_BY_CONTENT_ID = "SELECT u.* FROM user u " +
                "JOIN content c ON u.Id = c.userId " +
                "WHERE c.id = ?";
        User user = jdbc.queryForObject(SELECT_USER_BY_CONTENT_ID, new UserMapper(), contentId);
        user.setRoles(getRoleForUser(user.getId()));
        return user;
    }

    private void associateUserContent(List<Content> contentList) {
        //add user for each content
        for (Content content : contentList) {
            User user = getUserForContent(content.getId());
            content.setUser(user);
        }
    }

    public List<Tag> getTagsForContent(int contentId) {
        //get array of tag for content
        final String SELECT_TAG_BY_CONTENT_ID = "SELECT t.* FROM tag t " +
                "JOIN content_tag ct ON t.id = ct.tagId " +
                "WHERE ct.contentId = ?";
        return jdbc.query(SELECT_TAG_BY_CONTENT_ID, new TagMapper(), contentId);
    }

    private void associateContentTag(List<Content> contentList) {
        //add tags array for each content
        for (Content content : contentList) {
            List<Tag> tagList = getTagsForContent(content.getId());
            content.setTags(tagList);
        }
    }

    public List<Role> getRoleForUser(int userId) {
        //user need to have a role. This add a role to the user
        final String SELECT_ROLE_BY_USER_ID = "SELECT r.* FROM role r " +
                "JOIN user_role ur ON r.Id = ur.roleId " +
                "WHERE ur.userId = ?";
        return jdbc.query(SELECT_ROLE_BY_USER_ID, new RoleMapper(), userId);
    }

    public List<Content> getContentByType (String type) {
        List<Content> contentList = this.readAll();
        contentList = filterScheduleExpiredDate(contentList);
        List<Content> typeList = new ArrayList();
        //filter list of content base on the string type
        for (Content content : contentList) {
            if (content.getType().equals(type) && content.getStatus().equals("public")) {
                typeList.add(content);
            }
        }
        return typeList;
    }

    public List<Content> getContentByTag (int tagId) {
        //filter using sql query instead of using java
        final String SELECT_CONTENT_BY_TAG = "SELECT c.* FROM content c " +
                "JOIN content_tag ct ON ct.contentId = c.id " +
                "WHERE ct.tagId = ? and c.status = ?";
        List<Content> contentList =  jdbc.query(SELECT_CONTENT_BY_TAG, new ContentMapper(), tagId, "public");
        //take out expired content
        contentList = filterScheduleExpiredDate(contentList);
        //fill out tags and user
        associateContentTag(contentList);
        associateUserContent(contentList);
        return contentList;
    }

    public List<Content> getContentBySearchTitle(String searchText) {
        //filter using sql query
        searchText = "%" + searchText + "%";
        final String SELECT_CONTENT_BY_SEARCH_TITLE = "SELECT * FROM content WHERE title LIKE ?";
        List<Content> contentList = jdbc.query(SELECT_CONTENT_BY_SEARCH_TITLE, new ContentMapper(), searchText);
        //take out expired content
        contentList = filterScheduleExpiredDate(contentList);
        //fill out tags and user
        associateContentTag(contentList);
        associateUserContent(contentList);

        return contentList;
    }

    public List<Content> filterScheduleExpiredDate (List<Content>blogList) {
        //filter out expired content
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


}
