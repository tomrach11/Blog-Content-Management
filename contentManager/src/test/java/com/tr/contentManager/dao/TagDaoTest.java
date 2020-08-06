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
class TagDaoTest {

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
        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);

        Tag fromDao = tagDao.readById(tag.getId());
        assertEquals(tag, fromDao);
    }

    @Test
    void readAll() {
        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        Tag tag2 = new Tag();
        tag2.setName("tag2");
        tag2 = tagDao.create(tag2);
        Tag tag3 = new Tag();
        tag3.setName("tag3");
        tag3 = tagDao.create(tag3);

        List<Tag> fromDao = tagDao.readAll();
        assertEquals(3, fromDao.size());
    }

    @Test
    void readById() {
        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        Tag tag2 = new Tag();
        tag2.setName("tag2");
        tag2 = tagDao.create(tag2);
        Tag tag3 = new Tag();
        tag3.setName("tag3");
        tag3 = tagDao.create(tag3);

        Tag fromDao = tagDao.readById(tag.getId());
        assertEquals(tag, fromDao);
    }

    @Test
    void update() {
        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);

        tag.setName("changed");
        tagDao.update(tag);

        Tag fromDao = tagDao.readById(tag.getId());
        assertEquals(tag, fromDao);
    }

    @Test
    void delete() {
        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        Tag tag2 = new Tag();
        tag2.setName("tag2");
        tag2 = tagDao.create(tag2);
        Tag tag3 = new Tag();
        tag3.setName("tag3");
        tag3 = tagDao.create(tag3);

        tagDao.delete(tag.getId());

        List<Tag> fromDao = tagDao.readAll();
        assertEquals(2, fromDao.size());
    }
}