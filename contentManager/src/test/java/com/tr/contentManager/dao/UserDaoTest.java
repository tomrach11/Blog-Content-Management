package com.tr.contentManager.dao;

import com.tr.contentManager.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDaoTest {

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

        User fromDao = userDao.readById(user.getId());
        assertEquals(user, fromDao);
    }

    @Test
    void readAll() {
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

        List<User> fromDao = userDao.readAll();
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

        User fromDao = userDao.readById(user2.getId());
        assertEquals(user2, fromDao);
    }

    @Test
    void update() {
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

        user.setFirstName("changed");
        user.setLastName("changed");
        userDao.update(user);

        User fromDao = userDao.readById(user.getId());
        assertEquals(user, fromDao);
        assertEquals("changed", fromDao.getFirstName());
    }

    @Test
    void delete() {
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

        userDao.delete(user2.getId());

        List<User> fromDao = userDao.readAll();
        assertEquals(1, fromDao.size());
    }
}