package com.tr.contentManager.dao;

import com.tr.contentManager.model.Content;
import com.tr.contentManager.model.Role;
import com.tr.contentManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDao<T> implements Dao<User> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public User create(User model) {
        final String INSERT_USER = "INSERT INTO user (username, password, firstName, lastName, email, phone, profilePicture, enable) VALUES (?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_USER, model.getUsername(), model.getPassword(), model.getFirstName(), model.getLastName(), model.getEmail(), model.getPhone(), model.getProfilePicture(), model.isEnable());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(newId);
        insertIntoUserRole(model);
        return model;
    }

    @Override
    public List<User> readAll() {
        final String SELECT_ALL_USERS = "SELECT * FROM user";
        List<User> userList = jdbc.query(SELECT_ALL_USERS, new UserMapper());
        associateUserRole(userList);

        return userList;
    }

    @Override
    public User readById(int id) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
            user.setRoles(getRoleForUser(user.getId()));
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void update(User model) {
        final String UPDATE_USER = "UPDATE user SET " +
                "username = ?, " +
                "password = ?, " +
                "firstName = ?, " +
                "lastName = ?, " +
                "email = ?, " +
                "phone = ?, " +
                "profilePicture = ?, " +
                "enable = ? " +
                "WHERE id = ?";
        jdbc.update(UPDATE_USER, model.getUsername(), model.getPassword(), model.getFirstName(), model.getLastName(), model.getEmail(), model.getPhone(), model.getProfilePicture(), model.isEnable(), model.getId());

        final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE userId = ?";
        jdbc.update(DELETE_USER_ROLE, model.getId());

        insertIntoUserRole(model);
    }

    @Override
    @Transactional
    public void delete(int id) {
        final String SELECT_CONTENT_BY_USER_ID = "SELECT * FROM content WHERE userId = ?";
        List<Content> contentList = jdbc.query(SELECT_CONTENT_BY_USER_ID, new ContentMapper(), id);
        for (Content content : contentList) {
            final String DELETE_CONTENT_TAG = "DELETE FROM content_tag WHERE contentId = ?";
            jdbc.update(DELETE_CONTENT_TAG, content.getId());

            final String DELETE_COMMENT = "DELETE FROM comment WHERE contentId = ?";
            jdbc.update(DELETE_COMMENT, content.getId());

            final String DELETE_CONTENT = "DELETE FROM content WHERE id = ?";
            jdbc.update(DELETE_CONTENT, content.getId());
        }

//        //delete for content
//        final String DELETE_COMMENT_FOR_CONTENT = "DELETE FROM comment cm " +
//                "JOIN content ct ON ct.id = cm.contentId " +
//                "WHERE ct.userId = ?";
//        jdbc.update(DELETE_COMMENT_FOR_CONTENT, id);
//
//        final String DELETE_CONTENT_TAG_FOR_CONTENT = "DELETE FROM content_tag ct " +
//                "JOIN content c ON c.id = ct.contentId " +
//                "WHERE cn.userId = ?";
//        jdbc.update(DELETE_CONTENT_TAG_FOR_CONTENT, id);
//
//        final String DELETE_CONTENT = "DELETE FROM content c " +
//                "WHERE userId = ?";
//        jdbc.update(DELETE_CONTENT, id);

        //start delete other things
        final String DELETE_COMMENT = "DELETE FROM comment WHERE userId = ?";
        jdbc.update(DELETE_COMMENT, id);

        final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE userId = ?";
        jdbc.update(DELETE_USER_ROLE, id);

        final String DELETE_USER = "DELETE FROM user WHERE id = ?";
        jdbc.update(DELETE_USER, id);
    }

    private void insertIntoUserRole(User user) {
        final String INSERT_USER_ROLE = "INSERT INTO user_role (userId, roleId) VALUES (?,?)";
        for (Role role : user.getRoles()) {
            jdbc.update(INSERT_USER_ROLE, user.getId(), role.getId());
        }
    }

    public List<Role> getRoleForUser(int userId) {
        final String SELECT_ROLE_BY_USER_ID = "SELECT r.* FROM role r " +
                "JOIN user_role ur ON r.Id = ur.roleId " +
                "WHERE ur.userId = ?";
        return jdbc.query(SELECT_ROLE_BY_USER_ID, new RoleMapper(), userId);
    }

    private void associateUserRole(List<User> userList) {
        for (User user : userList) {
            List<Role> roleList = getRoleForUser(user.getId());
            user.setRoles(roleList);
        }
    }

    public User getUserByUsername(String username) {
        try {
            final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(), username);
            user.setRoles(getRoleForUser(user.getId()));
            return user;
        } catch (DataAccessException ex) {
            return null;
        }
    }
}
