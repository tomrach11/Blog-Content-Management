package com.tr.contentManager.dao;

import com.tr.contentManager.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoleDaoTest {
    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ContentDao contentDao;

    @Autowired
    TagDao tagDao;

    @Autowired
    CommentDao commentDao;

    @BeforeEach
    void setUp() {
        List<Role> roleList = roleDao.readAll();
        for (Role role : roleList) {
            roleDao.delete(role.getId());
        }
        List<User> userList = userDao.readAll();
        for (User user : userList) {
            userDao.delete(user.getId());
        }
        List<Content> contentList = contentDao.readAll();
        for (Content content : contentList) {
            userDao.delete(content.getId());
        }
        List<Comment> commentList = commentDao.readAll();
        for (Comment comment : commentList) {
            commentDao.delete(comment.getId());
        }
        List<Tag> tagList = tagDao.readAll();
        for (Tag tag : tagList) {
            tagDao.delete(tag.getId());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);

        Role fromDao = roleDao.readById(role.getId());
        assertEquals(role, fromDao);
    }

    @Test
    void readAll() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);
        Role role2 = new Role();
        role2.setName("role2");
        role2 = roleDao.create(role2);

        List<Role> fromDao = roleDao.readAll();
        assertEquals(2, fromDao.size());
    }

    @Test
    void readById() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);
        Role role2 = new Role();
        role2.setName("role2");
        role2 = roleDao.create(role2);

        Role fromDao = roleDao.readById(role2.getId());
        assertEquals(role2, fromDao);
    }

    @Test
    void update() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);

        role.setName("Changed");
        roleDao.update(role);

        Role fromDao = roleDao.readById(role.getId());
        assertEquals(role, fromDao);
    }

    @Test
    void delete() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);
        Role role2 = new Role();
        role2.setName("role2");
        role2 = roleDao.create(role2);

        roleDao.delete(role2.getId());

        List<Role> fromDao = roleDao.readAll();
        assertEquals(1, fromDao.size());
    }
}