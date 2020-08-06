DROP DATABASE IF EXISTS contentManagerDB;
CREATE DATABASE contentManagerDB;

USE contentManagerDB;

CREATE TABLE role (
	id int PRIMARY KEY auto_increment,
    name varchar(15)
);

CREATE TABLE user (
	id int PRIMARY KEY auto_increment,
    username varchar(30) not null,
    password varchar(100) not null,
    firstName varchar(30) not null,
    lastName varchar(30) not null,
    email varchar(30) not null,
    phone varchar(30),
    profilePicture varchar(255) DEFAULT "https://i.ibb.co/rskNZpT/profile-pic-1.jpg",
    enable boolean not null
);

CREATE TABLE user_role (
	userId int,
    roleId int,
    PRIMARY KEY (userId, roleId),
    FOREIGN KEY (userId) references user(id),
    FOREIGN KEY (roleId) references role(id)
);

CREATE TABLE content (
	id int PRIMARY KEY auto_increment,
    createDate datetime,
    title varchar(70) not null,
    type varchar(6) not null,
    status varchar(10) not null,
    content text not null,
    scheduleDate datetime,
    expiredDate datetime,
    titlePicture varchar(255) DEFAULT "http://placehold.it/750x300",
    userId int,
    FOREIGN KEY (userId) references user(id)
);

CREATE TABLE content_picuture (
	id int PRIMARY KEY auto_increment,
    picture blob,
    contentId int,
    FOREIGN KEY (contentId) references content(id)
);

CREATE TABLE comment (
	id int PRIMARY KEY auto_increment,
    createDate datetime,
    comment varchar(255) not null,
    userId int,
    FOREIGN KEY (userId) references user(id),
    contentId int,
    FOREIGN KEY (contentId) references content(id)
);

CREATE TABLE tag (
	id int PRIMARY KEY auto_increment,
    name varchar(30) not null
);

CREATE TABLE content_tag (
	contentId int,
    tagId int,
    PRIMARY KEY (contentId, tagId),
    FOREIGN KEY (contentId) references content(id),
    FOREIGN KEY (tagId) references tag(id)
);





