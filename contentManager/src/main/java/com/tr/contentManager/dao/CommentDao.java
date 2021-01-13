package com.tr.contentManager.dao;

import com.tr.contentManager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao<T> implements Dao<Comment> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Comment create(Comment model) {
        //add to
        final String INSERT_COMMENT = "INSERT INTO comment(createDate, comment, userId, contentId) VALUES (?,?,?,?)";
        jdbc.update(INSERT_COMMENT, model.getCreateDate(), model.getComment(), model.getUser().getId(), model.getContent().getId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(newId);

        return model;
    }

    @Override
    public List<Comment> readAll() {
        final String SELECT_ALL_COMMENT = "SELECT * FROM comment";
        List<Comment> commentList = jdbc.query(SELECT_ALL_COMMENT, new CommentMapper());

        associateUserComment(commentList);
        associateContentComment(commentList);

        return commentList;
    }

    @Override
    public Comment readById(int id) {
        final String SELECT_COMMENT_BY_ID = "SELECT * FROM comment WHERE id = ?";
        Comment comment = jdbc.queryForObject(SELECT_COMMENT_BY_ID, new CommentMapper(), id);

        comment.setUser(getUserForComment(comment.getId()));
        comment.setContent(getContentForComment(comment.getId()));

        return comment;
    }

    @Override
    public void update(Comment model) {
        final String UPDATE_COMMENT = "UPDATE comment SET " +
                "createDate = ?, " +
                "comment = ?, " +
                "userId = ?, " +
                "contentId = ? " +
                "WHERE id = ?";
        jdbc.update(UPDATE_COMMENT,  model.getCreateDate(), model.getComment(), model.getUser().getId(), model.getContent().getId(), model.getId());
    }

    @Override
    public void delete(int id) {
        final String DELETE_COMMENT = "DELETE FROM comment WHERE id = ?";
        jdbc.update(DELETE_COMMENT, id);
    }

    public User getUserForComment(int commentId) {
        final String SELECT_USER_BY_COMMENT_ID = "SELECT u.* FROM user u " +
                "JOIN comment c ON u.id = c.userId " +
                "WHERE c.id = ?";
        User user = jdbc.queryForObject(SELECT_USER_BY_COMMENT_ID, new UserMapper(), commentId);
        user.setRoles(getRoleForUser(user.getId()));
        return user;
    }

    private void associateUserComment(List<Comment> commentList) {
        for(Comment comment : commentList) {
            User user = getUserForComment(comment.getId());
            comment.setUser(user);
        }
    }

    public Content getContentForComment(int commentId) {
        final String SELECT_CONTENT_BY_COMMENT_ID = "SELECT ct.* FROM content ct " +
                "JOIN comment cm ON ct.id = cm.contentId " +
                "WHERE cm.id = ?";
        Content content = jdbc.queryForObject(SELECT_CONTENT_BY_COMMENT_ID, new ContentMapper(), commentId);
        content.setUser(getUserForContent(content.getId()));
        content.setTags(getTagsForContent(content.getId()));
        return content;
    }

    private void associateContentComment(List<Comment> commentList) {
        for(Comment comment : commentList) {
            Content content = getContentForComment(comment.getId());
        }
    }

    public List<Role> getRoleForUser(int userId) {
        final String SELECT_ROLE_BY_USER_ID = "SELECT r.* FROM role r " +
                "JOIN user_role ur ON r.Id = ur.roleId " +
                "WHERE ur.userId = ?";
        return jdbc.query(SELECT_ROLE_BY_USER_ID, new RoleMapper(), userId);
    }

    public User getUserForContent(int contentId) {
        final String SELECT_USER_BY_CONTENT_ID = "SELECT u.* FROM user u " +
                "JOIN content c ON u.Id = c.userId " +
                "WHERE c.id = ?";
        User user = jdbc.queryForObject(SELECT_USER_BY_CONTENT_ID, new UserMapper(), contentId);
        user.setRoles(getRoleForUser(user.getId()));
        return user;
    }

    public List<Tag> getTagsForContent(int contentId) {
        final String SELECT_TAG_BY_CONTENT_ID = "SELECT t.* FROM tag t " +
                "JOIN content_tag ct ON t.id = ct.tagId " +
                "WHERE ct.contentId = ?";
        return jdbc.query(SELECT_TAG_BY_CONTENT_ID, new TagMapper(), contentId);
    }
    public List<Comment> getCommentByContentId(int contentId) {
        final String SELECT_COMMENT_BY_CONTENT = "SELECT * FROM comment WHERE contentId = ?";
        List<Comment> commentList =  jdbc.query(SELECT_COMMENT_BY_CONTENT, new CommentMapper(), contentId);
        associateContentComment(commentList);
        associateUserComment(commentList);

        return commentList;
    }
}
