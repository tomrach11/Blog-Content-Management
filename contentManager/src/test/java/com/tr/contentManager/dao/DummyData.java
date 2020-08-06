package com.tr.contentManager.dao;

import com.tr.contentManager.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DummyData {

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

    private void addDummyData () {
        Role role = new Role();
        role.setName("role1");
        role = roleDao.create(role);
        Role role2 = new Role();
        role2.setName("role2");
        role2 = roleDao.create(role2);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        roleList.add(role2);

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
        User user2 = new User();
        user2.setUsername("username2");
        user2.setPassword("password");
        user2.setFirstName("firstname2");
        user2.setLastName("lastname2");
        user2.setEnable(true);
        user2.setPhone("1234567890");
        user2.setEmail("test@gmail.com");
        user2.setRoles(roleList);
        user2 = userDao.create(user2);

        Tag tag = new Tag();
        tag.setName("tag1");
        tag = tagDao.create(tag);
        Tag tag2 = new Tag();
        tag2.setName("tag2");
        tag2 = tagDao.create(tag2);
        Tag tag3 = new Tag();
        tag3.setName("tag3");
        tag3 = tagDao.create(tag3);
        List<Tag> tagList = tagDao.readAll();

        Content content = new Content();
        content.setCreateDate(LocalDateTime.parse("2020-01-01"));
        content.setTitle("title");
        content.setContent("content");
        content.setType("type");
        content.setStatus("status");
        content.setUser(user);
        content.setTags(tagList);
        content = contentDao.create(content);
        Content content2 = new Content();
        content2.setCreateDate(LocalDateTime.parse("2020-01-01"));
        content2.setTitle("title2");
        content2.setContent("content2");
        content2.setType("type");
        content2.setStatus("status");
        content2.setUser(user2);
        content2.setTags(tagList);
        content2 = contentDao.create(content2);

        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.parse("2020-01-01"));
        comment.setComment("comment");
        comment.setContent(content);
        comment.setUser(user);
        comment = commentDao.create(comment);
        Comment comment2 = new Comment();
        comment2.setCreateDate(LocalDateTime.parse("2020-01-01"));
        comment2.setComment("comment2");
        comment2.setContent(content);
        comment2.setUser(user);
        comment2 = commentDao.create(comment2);
    }
}
