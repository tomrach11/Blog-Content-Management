package com.tr.contentManager.dao;

import com.tr.contentManager.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ContentDaoTest {

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
        List<Role> roleList = roleDao.readAll();

        User user = new User();
        user.setUsername("username1");
        user.setPassword("password");
        user.setFirstName("firstname1");
        user.setLastName("lastname1");
        user.setEnable(true);
        user.setPhone("1234567890");
        user.setEmail("test@gmail.com");
        user.setRoles(roleList);
        user = userDao.create(user);

        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        List<Tag> tagList = tagDao.readAll();

        Content content = new Content();
        content.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        content.setTitle("title");
        content.setContent("content");
        content.setType("type");
        content.setStatus("status");
        content.setUser(user);
        content.setTags(tagList);
        content = contentDao.create(content);

        Content fromDao = contentDao.readById(content.getId());
        assertEquals(content, fromDao);
    }

    @Test
    void readAll() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);
        List<Role> roleList = roleDao.readAll();

        User user = new User();
        user.setUsername("username1");
        user.setPassword("password");
        user.setFirstName("firstname1");
        user.setLastName("lastname1");
        user.setEnable(true);
        user.setPhone("1234567890");
        user.setEmail("test@gmail.com");
        user.setRoles(roleList);
        user = userDao.create(user);

        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        List<Tag> tagList = tagDao.readAll();

        Content content = new Content();
        content.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        content.setTitle("title");
        content.setContent("content");
        content.setType("type");
        content.setStatus("status");
        content.setUser(user);
        content.setTags(tagList);
        content = contentDao.create(content);
        Content content2 = new Content();
        content2.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        content2.setTitle("title2");
        content2.setContent("content2");
        content2.setType("type");
        content2.setStatus("status");
        content2.setUser(user);
        content2.setTags(tagList);
        content2 = contentDao.create(content2);

        List<Content> fromDao = contentDao.readAll();
        assertEquals(2, fromDao.size());
    }

    @Test
    void readById() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);
        List<Role> roleList = roleDao.readAll();

        User user = new User();
        user.setUsername("username1");
        user.setPassword("password");
        user.setFirstName("firstname1");
        user.setLastName("lastname1");
        user.setEnable(true);
        user.setPhone("1234567890");
        user.setEmail("test@gmail.com");
        user.setRoles(roleList);
        user = userDao.create(user);

        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        List<Tag> tagList = tagDao.readAll();

        Content content = new Content();
        content.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        content.setTitle("title");
        content.setContent("content");
        content.setType("type");
        content.setStatus("status");
        content.setUser(user);
        content.setTags(tagList);
        content = contentDao.create(content);
        Content content2 = new Content();
        content2.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        content2.setTitle("title2");
        content2.setContent("content2");
        content2.setType("type");
        content2.setStatus("status");
        content2.setUser(user);
        content2.setTags(tagList);
        content2 = contentDao.create(content2);

        Content fromDao = contentDao.readById(content2.getId());
        assertEquals(content2, fromDao);
    }

    @Test
    void update() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);
        List<Role> roleList = roleDao.readAll();

        User user = new User();
        user.setUsername("username1");
        user.setPassword("password");
        user.setFirstName("firstname1");
        user.setLastName("lastname1");
        user.setEnable(true);
        user.setPhone("1234567890");
        user.setEmail("test@gmail.com");
        user.setRoles(roleList);
        user = userDao.create(user);

        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        List<Tag> tagList = tagDao.readAll();

        Content content = new Content();
        content.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        content.setTitle("title");
        content.setContent("content");
        content.setType("type");
        content.setStatus("status");
        content.setUser(user);
        content.setTags(tagList);
        content = contentDao.create(content);

        content.setContent("changed");
        contentDao.update(content);

        Content fromDao = contentDao.readById(content.getId());
        assertEquals(content, fromDao);
        assertEquals("changed", fromDao.getContent());
    }

    @Test
    void delete() {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);
        List<Role> roleList = roleDao.readAll();

        User user = new User();
        user.setUsername("username1");
        user.setPassword("password");
        user.setFirstName("firstname1");
        user.setLastName("lastname1");
        user.setEnable(true);
        user.setPhone("1234567890");
        user.setEmail("test@gmail.com");
        user.setRoles(roleList);
        user = userDao.create(user);

        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        List<Tag> tagList = tagDao.readAll();

        Content content = new Content();
        content.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        content.setTitle("title");
        content.setContent("content");
        content.setType("type");
        content.setStatus("status");
        content.setUser(user);
        content.setTags(tagList);
        content = contentDao.create(content);
        Content content2 = new Content();
        content2.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        content2.setTitle("title2");
        content2.setContent("content2");
        content2.setType("type");
        content2.setStatus("status");
        content2.setUser(user);
        content2.setTags(tagList);
        content2 = contentDao.create(content2);

        contentDao.delete(content2.getId());

        List<Content> fromDao = contentDao.readAll();
        assertEquals(1, fromDao.size());
    }
}