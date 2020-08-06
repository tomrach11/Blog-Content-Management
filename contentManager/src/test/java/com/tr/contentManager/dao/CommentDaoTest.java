package com.tr.contentManager.dao;

import com.tr.contentManager.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.IContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentDaoTest {

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

        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        comment.setComment("comment");
        comment.setContent(content);
        comment.setUser(user);
        comment = commentDao.create(comment);

        Comment fromDao = commentDao.readById(comment.getId());
        assertEquals(comment, fromDao);
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

        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        comment.setComment("comment");
        comment.setContent(content);
        comment.setUser(user);
        comment = commentDao.create(comment);
        Comment comment2 = new Comment();
        comment2.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        comment2.setComment("comment2");
        comment2.setContent(content);
        comment2.setUser(user);
        comment2 = commentDao.create(comment2);

        List<Comment> formDao = commentDao.readAll();
        assertEquals(2, formDao.size());
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

        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        comment.setComment("comment");
        comment.setContent(content);
        comment.setUser(user);
        comment = commentDao.create(comment);
        Comment comment2 = new Comment();
        comment2.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        comment2.setComment("comment2");
        comment2.setContent(content);
        comment2.setUser(user);
        comment2 = commentDao.create(comment2);

        Comment fromDao = commentDao.readById(comment2.getId());
        assertEquals(comment2, fromDao);
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

        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        comment.setComment("comment");
        comment.setContent(content);
        comment.setUser(user);
        comment = commentDao.create(comment);

        comment.setComment("changed");
        commentDao.update(comment);

        Comment fromDao = commentDao.readById(comment.getId());
        assertEquals(comment, fromDao);
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

        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        comment.setComment("comment");
        comment.setContent(content);
        comment.setUser(user);
        comment = commentDao.create(comment);
        Comment comment2 = new Comment();
        comment2.setCreateDate(LocalDateTime.parse("2020-01-01T12:45:30"));
        comment2.setComment("comment2");
        comment2.setContent(content);
        comment2.setUser(user);
        comment2 = commentDao.create(comment2);

        commentDao.delete(comment2.getId());

        List<Comment> formDao = commentDao.readAll();
        assertEquals(1, formDao.size());
    }


}