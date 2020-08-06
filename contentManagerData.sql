USE contentManagerDB;

INSERT INTO role (name) values
	("ROLE_ADMIN"),
    ("ROLE_MANAGER"),
    ("ROLE_USER");

INSERT INTO user (username, password, firstname, lastname, email, phone, enable) values 
	("admin", "password", "Tom", "Rachtawarn", "tomrach11@gmail.com", "347318500", true),
    ("content1", "password", "John", "Doe", "johnD@gmail.com", "3478459586", true),
    ("content2", "password", "Marry", "Jane", "marryJ@gmail.com", "9294785961", true),
    ("user1", "password", "Willaim", "Smith", "will@gmail.com", "3474898000", true),
    ("user2", "password", "Jay", "Won", "jwon@gmail.com", "2015467845", true),
    ("user3", "password", "kavin", "Stones", "kavstone@gmail.com", "9295145599", true);
    
INSERT INTO user_role (userId, roleId) values
	(1, 1), (1, 2), (1, 3),
    (2, 2), (2, 3),
    (3, 2), (3, 3),
    (4,3),
    (5,3),
    (6,3);
    
-- INSERT INTO tag (name) values
-- 	("American Food"),
--     ("Asain Food"),
--     ("African Food"),
--     ("Thai Food"),
--     ("Japanese Food"),
--     ("Spanish Food"),
--     ("Food"),
--     ("Resturant"),
--     ("Desert"),
--     ("Drinnk"),
--     ("Fruit"),
--     ("Baverage"),
--     ("Cocktail"),
--     ("Beer"),
--     ("Alchohol Drink"),
--     ("Non-Alchohol Drink"),
--     ("Burger"),
--     ("Sushi"),
--     ("Ramen");
    
INSERT INTO tag (name) values
	("Static"),
    ("Computer"),
    ("Phone"),
    ("Tablet"),
    ("Game"),
    ("Technology"),
    ("Car"),
    ("Truck"),
    ("IOS"),
    ("Android"),
    ("Window"),
    ("Programming"),
    ("Spec"),
    ("X-Box"),
    ("Play Station");
    
INSERT INTO content (createDate, title, type, status, userId, content) values
	("2020-7-1", "Building Gaming PC", "blog", "public", 1,
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vitae turpis massa sed elementum tempus egestas sed sed. Nulla facilisi cras fermentum odio eu feugiat pretium nibh. Lacus vestibulum sed arcu non odio. Convallis aenean et tortor at risus viverra adipiscing. Sit amet mattis vulputate enim nulla aliquet porttitor. In hendrerit gravida rutrum quisque non. Nec tincidunt praesent semper feugiat nibh sed pulvinar proin. Vel quam elementum pulvinar etiam non. Odio ut enim blandit volutpat maecenas. Tincidunt augue interdum velit euismod in pellentesque. Commodo nulla facilisi nullam vehicula ipsum. At lectus urna duis convallis convallis tellus id interdum. Gravida in fermentum et sollicitudin ac orci phasellus egestas tellus. Volutpat commodo sed egestas egestas fringilla phasellus faucibus scelerisque. Velit aliquet sagittis id consectetur. At ultrices mi tempus imperdiet nulla. Imperdiet massa tincidunt nunc pulvinar sapien et ligula.
		Eget nunc lobortis mattis aliquam. Fermentum dui faucibus in ornare quam. Ornare arcu odio ut sem nulla pharetra diam sit amet. Tristique senectus et netus et. Lobortis feugiat vivamus at augue eget arcu. Leo in vitae turpis massa sed. Proin libero nunc consequat interdum varius sit amet mattis vulputate. Est ullamcorper eget nulla facilisi. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Gravida quis blandit turpis cursus in. Egestas egestas fringilla phasellus faucibus scelerisque. Pharetra vel turpis nunc eget.
		Sem nulla pharetra diam sit. Sit amet consectetur adipiscing elit ut. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus. Faucibus pulvinar elementum integer enim neque volutpat ac. In nulla posuere sollicitudin aliquam ultrices. Purus sit amet luctus venenatis lectus magna fringilla urna. Dolor morbi non arcu risus. Sed odio morbi quis commodo odio. Quam adipiscing vitae proin sagittis. Pharetra pharetra massa massa ultricies mi. Dictum varius duis at consectetur lorem donec massa sapien. Quis ipsum suspendisse ultrices gravida.
		Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Habitasse platea dictumst quisque sagittis purus. Felis donec et odio pellentesque diam volutpat commodo sed egestas. Elementum tempus egestas sed sed risus pretium quam vulputate. Mollis aliquam ut porttitor leo a. Ut consequat semper viverra nam libero justo laoreet sit. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Id aliquet risus feugiat in. Quam viverra orci sagittis eu volutpat odio. Aliquam etiam erat velit scelerisque in dictum non consectetur a. Morbi leo urna molestie at elementum eu. Sit amet nisl purus in mollis nunc sed. Tincidunt augue interdum velit euismod in pellentesque massa. A erat nam at lectus urna. Interdum velit laoreet id donec ultrices. 
        Cras semper auctor neque vitae tempus quam pellentesque nec."),
	("2020-6-1", "Up coming PS5", "blog", "public", 2,
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vitae turpis massa sed elementum tempus egestas sed sed. Nulla facilisi cras fermentum odio eu feugiat pretium nibh. Lacus vestibulum sed arcu non odio. Convallis aenean et tortor at risus viverra adipiscing. Sit amet mattis vulputate enim nulla aliquet porttitor. In hendrerit gravida rutrum quisque non. Nec tincidunt praesent semper feugiat nibh sed pulvinar proin. Vel quam elementum pulvinar etiam non. Odio ut enim blandit volutpat maecenas. Tincidunt augue interdum velit euismod in pellentesque. Commodo nulla facilisi nullam vehicula ipsum. At lectus urna duis convallis convallis tellus id interdum. Gravida in fermentum et sollicitudin ac orci phasellus egestas tellus. Volutpat commodo sed egestas egestas fringilla phasellus faucibus scelerisque. Velit aliquet sagittis id consectetur. At ultrices mi tempus imperdiet nulla. Imperdiet massa tincidunt nunc pulvinar sapien et ligula.
		Eget nunc lobortis mattis aliquam. Fermentum dui faucibus in ornare quam. Ornare arcu odio ut sem nulla pharetra diam sit amet. Tristique senectus et netus et. Lobortis feugiat vivamus at augue eget arcu. Leo in vitae turpis massa sed. Proin libero nunc consequat interdum varius sit amet mattis vulputate. Est ullamcorper eget nulla facilisi. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Gravida quis blandit turpis cursus in. Egestas egestas fringilla phasellus faucibus scelerisque. Pharetra vel turpis nunc eget.
		Sem nulla pharetra diam sit. Sit amet consectetur adipiscing elit ut. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus. Faucibus pulvinar elementum integer enim neque volutpat ac. In nulla posuere sollicitudin aliquam ultrices. Purus sit amet luctus venenatis lectus magna fringilla urna. Dolor morbi non arcu risus. Sed odio morbi quis commodo odio. Quam adipiscing vitae proin sagittis. Pharetra pharetra massa massa ultricies mi. Dictum varius duis at consectetur lorem donec massa sapien. Quis ipsum suspendisse ultrices gravida.
		Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Habitasse platea dictumst quisque sagittis purus. Felis donec et odio pellentesque diam volutpat commodo sed egestas. Elementum tempus egestas sed sed risus pretium quam vulputate. Mollis aliquam ut porttitor leo a. Ut consequat semper viverra nam libero justo laoreet sit. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Id aliquet risus feugiat in. Quam viverra orci sagittis eu volutpat odio. Aliquam etiam erat velit scelerisque in dictum non consectetur a. Morbi leo urna molestie at elementum eu. Sit amet nisl purus in mollis nunc sed. Tincidunt augue interdum velit euismod in pellentesque massa. A erat nam at lectus urna. Interdum velit laoreet id donec ultrices. 
		Cras semper auctor neque vitae tempus quam pellentesque nec."),
    ("2020-2-1", "Up coming new X-Box", "blog", "public", 2,
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vitae turpis massa sed elementum tempus egestas sed sed. Nulla facilisi cras fermentum odio eu feugiat pretium nibh. Lacus vestibulum sed arcu non odio. Convallis aenean et tortor at risus viverra adipiscing. Sit amet mattis vulputate enim nulla aliquet porttitor. In hendrerit gravida rutrum quisque non. Nec tincidunt praesent semper feugiat nibh sed pulvinar proin. Vel quam elementum pulvinar etiam non. Odio ut enim blandit volutpat maecenas. Tincidunt augue interdum velit euismod in pellentesque. Commodo nulla facilisi nullam vehicula ipsum. At lectus urna duis convallis convallis tellus id interdum. Gravida in fermentum et sollicitudin ac orci phasellus egestas tellus. Volutpat commodo sed egestas egestas fringilla phasellus faucibus scelerisque. Velit aliquet sagittis id consectetur. At ultrices mi tempus imperdiet nulla. Imperdiet massa tincidunt nunc pulvinar sapien et ligula.
		Eget nunc lobortis mattis aliquam. Fermentum dui faucibus in ornare quam. Ornare arcu odio ut sem nulla pharetra diam sit amet. Tristique senectus et netus et. Lobortis feugiat vivamus at augue eget arcu. Leo in vitae turpis massa sed. Proin libero nunc consequat interdum varius sit amet mattis vulputate. Est ullamcorper eget nulla facilisi. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Gravida quis blandit turpis cursus in. Egestas egestas fringilla phasellus faucibus scelerisque. Pharetra vel turpis nunc eget.
		Sem nulla pharetra diam sit. Sit amet consectetur adipiscing elit ut. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus. Faucibus pulvinar elementum integer enim neque volutpat ac. In nulla posuere sollicitudin aliquam ultrices. Purus sit amet luctus venenatis lectus magna fringilla urna. Dolor morbi non arcu risus. Sed odio morbi quis commodo odio. Quam adipiscing vitae proin sagittis. Pharetra pharetra massa massa ultricies mi. Dictum varius duis at consectetur lorem donec massa sapien. Quis ipsum suspendisse ultrices gravida.
		Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Habitasse platea dictumst quisque sagittis purus. Felis donec et odio pellentesque diam volutpat commodo sed egestas. Elementum tempus egestas sed sed risus pretium quam vulputate. Mollis aliquam ut porttitor leo a. Ut consequat semper viverra nam libero justo laoreet sit. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Id aliquet risus feugiat in. Quam viverra orci sagittis eu volutpat odio. Aliquam etiam erat velit scelerisque in dictum non consectetur a. Morbi leo urna molestie at elementum eu. Sit amet nisl purus in mollis nunc sed. Tincidunt augue interdum velit euismod in pellentesque massa. A erat nam at lectus urna. Interdum velit laoreet id donec ultrices. 
		Cras semper auctor neque vitae tempus quam pellentesque nec."),
    ("2019-7-1", "Why CyberPunk 2020 is going to be good", "blog", "private", 3,
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vitae turpis massa sed elementum tempus egestas sed sed. Nulla facilisi cras fermentum odio eu feugiat pretium nibh. Lacus vestibulum sed arcu non odio. Convallis aenean et tortor at risus viverra adipiscing. Sit amet mattis vulputate enim nulla aliquet porttitor. In hendrerit gravida rutrum quisque non. Nec tincidunt praesent semper feugiat nibh sed pulvinar proin. Vel quam elementum pulvinar etiam non. Odio ut enim blandit volutpat maecenas. Tincidunt augue interdum velit euismod in pellentesque. Commodo nulla facilisi nullam vehicula ipsum. At lectus urna duis convallis convallis tellus id interdum. Gravida in fermentum et sollicitudin ac orci phasellus egestas tellus. Volutpat commodo sed egestas egestas fringilla phasellus faucibus scelerisque. Velit aliquet sagittis id consectetur. At ultrices mi tempus imperdiet nulla. Imperdiet massa tincidunt nunc pulvinar sapien et ligula.
		Eget nunc lobortis mattis aliquam. Fermentum dui faucibus in ornare quam. Ornare arcu odio ut sem nulla pharetra diam sit amet. Tristique senectus et netus et. Lobortis feugiat vivamus at augue eget arcu. Leo in vitae turpis massa sed. Proin libero nunc consequat interdum varius sit amet mattis vulputate. Est ullamcorper eget nulla facilisi. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Gravida quis blandit turpis cursus in. Egestas egestas fringilla phasellus faucibus scelerisque. Pharetra vel turpis nunc eget.
		Sem nulla pharetra diam sit. Sit amet consectetur adipiscing elit ut. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus. Faucibus pulvinar elementum integer enim neque volutpat ac. In nulla posuere sollicitudin aliquam ultrices. Purus sit amet luctus venenatis lectus magna fringilla urna. Dolor morbi non arcu risus. Sed odio morbi quis commodo odio. Quam adipiscing vitae proin sagittis. Pharetra pharetra massa massa ultricies mi. Dictum varius duis at consectetur lorem donec massa sapien. Quis ipsum suspendisse ultrices gravida.
		Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Habitasse platea dictumst quisque sagittis purus. Felis donec et odio pellentesque diam volutpat commodo sed egestas. Elementum tempus egestas sed sed risus pretium quam vulputate. Mollis aliquam ut porttitor leo a. Ut consequat semper viverra nam libero justo laoreet sit. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Id aliquet risus feugiat in. Quam viverra orci sagittis eu volutpat odio. Aliquam etiam erat velit scelerisque in dictum non consectetur a. Morbi leo urna molestie at elementum eu. Sit amet nisl purus in mollis nunc sed. Tincidunt augue interdum velit euismod in pellentesque massa. A erat nam at lectus urna. Interdum velit laoreet id donec ultrices. 
		Cras semper auctor neque vitae tempus quam pellentesque nec."),
    ("2018-7-1", "Disclaimer", "static", "public", 1,
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vitae turpis massa sed elementum tempus egestas sed sed. Nulla facilisi cras fermentum odio eu feugiat pretium nibh. Lacus vestibulum sed arcu non odio. Convallis aenean et tortor at risus viverra adipiscing. Sit amet mattis vulputate enim nulla aliquet porttitor. In hendrerit gravida rutrum quisque non. Nec tincidunt praesent semper feugiat nibh sed pulvinar proin. Vel quam elementum pulvinar etiam non. Odio ut enim blandit volutpat maecenas. Tincidunt augue interdum velit euismod in pellentesque. Commodo nulla facilisi nullam vehicula ipsum. At lectus urna duis convallis convallis tellus id interdum. Gravida in fermentum et sollicitudin ac orci phasellus egestas tellus. Volutpat commodo sed egestas egestas fringilla phasellus faucibus scelerisque. Velit aliquet sagittis id consectetur. At ultrices mi tempus imperdiet nulla. Imperdiet massa tincidunt nunc pulvinar sapien et ligula.
		Eget nunc lobortis mattis aliquam. Fermentum dui faucibus in ornare quam. Ornare arcu odio ut sem nulla pharetra diam sit amet. Tristique senectus et netus et. Lobortis feugiat vivamus at augue eget arcu. Leo in vitae turpis massa sed. Proin libero nunc consequat interdum varius sit amet mattis vulputate. Est ullamcorper eget nulla facilisi. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Gravida quis blandit turpis cursus in. Egestas egestas fringilla phasellus faucibus scelerisque. Pharetra vel turpis nunc eget.
		Sem nulla pharetra diam sit. Sit amet consectetur adipiscing elit ut. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus. Faucibus pulvinar elementum integer enim neque volutpat ac. In nulla posuere sollicitudin aliquam ultrices. Purus sit amet luctus venenatis lectus magna fringilla urna. Dolor morbi non arcu risus. Sed odio morbi quis commodo odio. Quam adipiscing vitae proin sagittis. Pharetra pharetra massa massa ultricies mi. Dictum varius duis at consectetur lorem donec massa sapien. Quis ipsum suspendisse ultrices gravida.
		Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Habitasse platea dictumst quisque sagittis purus. Felis donec et odio pellentesque diam volutpat commodo sed egestas. Elementum tempus egestas sed sed risus pretium quam vulputate. Mollis aliquam ut porttitor leo a. Ut consequat semper viverra nam libero justo laoreet sit. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Id aliquet risus feugiat in. Quam viverra orci sagittis eu volutpat odio. Aliquam etiam erat velit scelerisque in dictum non consectetur a. Morbi leo urna molestie at elementum eu. Sit amet nisl purus in mollis nunc sed. Tincidunt augue interdum velit euismod in pellentesque massa. A erat nam at lectus urna. Interdum velit laoreet id donec ultrices. 
		Cras semper auctor neque vitae tempus quam pellentesque nec."),
    ("2020-7-11", "Contact Us", "static", "public", 1,
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vitae turpis massa sed elementum tempus egestas sed sed. Nulla facilisi cras fermentum odio eu feugiat pretium nibh. Lacus vestibulum sed arcu non odio. Convallis aenean et tortor at risus viverra adipiscing. Sit amet mattis vulputate enim nulla aliquet porttitor. In hendrerit gravida rutrum quisque non. Nec tincidunt praesent semper feugiat nibh sed pulvinar proin. Vel quam elementum pulvinar etiam non. Odio ut enim blandit volutpat maecenas. Tincidunt augue interdum velit euismod in pellentesque. Commodo nulla facilisi nullam vehicula ipsum. At lectus urna duis convallis convallis tellus id interdum. Gravida in fermentum et sollicitudin ac orci phasellus egestas tellus. Volutpat commodo sed egestas egestas fringilla phasellus faucibus scelerisque. Velit aliquet sagittis id consectetur. At ultrices mi tempus imperdiet nulla. Imperdiet massa tincidunt nunc pulvinar sapien et ligula.
		Eget nunc lobortis mattis aliquam. Fermentum dui faucibus in ornare quam. Ornare arcu odio ut sem nulla pharetra diam sit amet. Tristique senectus et netus et. Lobortis feugiat vivamus at augue eget arcu. Leo in vitae turpis massa sed. Proin libero nunc consequat interdum varius sit amet mattis vulputate. Est ullamcorper eget nulla facilisi. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Gravida quis blandit turpis cursus in. Egestas egestas fringilla phasellus faucibus scelerisque. Pharetra vel turpis nunc eget.
		Sem nulla pharetra diam sit. Sit amet consectetur adipiscing elit ut. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus. Faucibus pulvinar elementum integer enim neque volutpat ac. In nulla posuere sollicitudin aliquam ultrices. Purus sit amet luctus venenatis lectus magna fringilla urna. Dolor morbi non arcu risus. Sed odio morbi quis commodo odio. Quam adipiscing vitae proin sagittis. Pharetra pharetra massa massa ultricies mi. Dictum varius duis at consectetur lorem donec massa sapien. Quis ipsum suspendisse ultrices gravida.
		Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Habitasse platea dictumst quisque sagittis purus. Felis donec et odio pellentesque diam volutpat commodo sed egestas. Elementum tempus egestas sed sed risus pretium quam vulputate. Mollis aliquam ut porttitor leo a. Ut consequat semper viverra nam libero justo laoreet sit. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Id aliquet risus feugiat in. Quam viverra orci sagittis eu volutpat odio. Aliquam etiam erat velit scelerisque in dictum non consectetur a. Morbi leo urna molestie at elementum eu. Sit amet nisl purus in mollis nunc sed. Tincidunt augue interdum velit euismod in pellentesque massa. A erat nam at lectus urna. Interdum velit laoreet id donec ultrices. 
		Cras semper auctor neque vitae tempus quam pellentesque nec."),
    ("2020-7-1", "About Us", "static", "public", 1,
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Vitae turpis massa sed elementum tempus egestas sed sed. Nulla facilisi cras fermentum odio eu feugiat pretium nibh. Lacus vestibulum sed arcu non odio. Convallis aenean et tortor at risus viverra adipiscing. Sit amet mattis vulputate enim nulla aliquet porttitor. In hendrerit gravida rutrum quisque non. Nec tincidunt praesent semper feugiat nibh sed pulvinar proin. Vel quam elementum pulvinar etiam non. Odio ut enim blandit volutpat maecenas. Tincidunt augue interdum velit euismod in pellentesque. Commodo nulla facilisi nullam vehicula ipsum. At lectus urna duis convallis convallis tellus id interdum. Gravida in fermentum et sollicitudin ac orci phasellus egestas tellus. Volutpat commodo sed egestas egestas fringilla phasellus faucibus scelerisque. Velit aliquet sagittis id consectetur. At ultrices mi tempus imperdiet nulla. Imperdiet massa tincidunt nunc pulvinar sapien et ligula.
		Eget nunc lobortis mattis aliquam. Fermentum dui faucibus in ornare quam. Ornare arcu odio ut sem nulla pharetra diam sit amet. Tristique senectus et netus et. Lobortis feugiat vivamus at augue eget arcu. Leo in vitae turpis massa sed. Proin libero nunc consequat interdum varius sit amet mattis vulputate. Est ullamcorper eget nulla facilisi. Sit amet consectetur adipiscing elit duis tristique sollicitudin. Gravida quis blandit turpis cursus in. Egestas egestas fringilla phasellus faucibus scelerisque. Pharetra vel turpis nunc eget.
		Sem nulla pharetra diam sit. Sit amet consectetur adipiscing elit ut. Sed egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Sollicitudin tempor id eu nisl nunc mi ipsum faucibus. Faucibus pulvinar elementum integer enim neque volutpat ac. In nulla posuere sollicitudin aliquam ultrices. Purus sit amet luctus venenatis lectus magna fringilla urna. Dolor morbi non arcu risus. Sed odio morbi quis commodo odio. Quam adipiscing vitae proin sagittis. Pharetra pharetra massa massa ultricies mi. Dictum varius duis at consectetur lorem donec massa sapien. Quis ipsum suspendisse ultrices gravida.
		Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum. Habitasse platea dictumst quisque sagittis purus. Felis donec et odio pellentesque diam volutpat commodo sed egestas. Elementum tempus egestas sed sed risus pretium quam vulputate. Mollis aliquam ut porttitor leo a. Ut consequat semper viverra nam libero justo laoreet sit. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Id aliquet risus feugiat in. Quam viverra orci sagittis eu volutpat odio. Aliquam etiam erat velit scelerisque in dictum non consectetur a. Morbi leo urna molestie at elementum eu. Sit amet nisl purus in mollis nunc sed. Tincidunt augue interdum velit euismod in pellentesque massa. A erat nam at lectus urna. Interdum velit laoreet id donec ultrices. 
		Cras semper auctor neque vitae tempus quam pellentesque nec.");
        
INSERT INTO content_tag (contentId, tagId) values 
	(1, 1), (1, 12),
    (2, 4), (2, 14),
    (3, 4), (3, 13),
	(4, 4), (4, 14), (4, 13);
    
INSERT INTO comment (createDate, comment, userId, contentId) values 
	("2020-7-11", "So hype for this gaming console.", 6, 2),
    ("2020-7-12", "Buidling a PC is an art !!", 5, 1),
    ("2020-7-13", "Buy it for sure !!", 6, 2),
    ("2020-7-13", "Building PC is too hard ...", 6, 1);
        

UPDATE user SET password = '$2a$10$F2ERRQiGv0yK5wnUiHWdxeJYyS8q3s/gBEXgHg18GcJ.VHFeRoitC' WHERE id = 1;
UPDATE user SET password = '$2a$10$F2ERRQiGv0yK5wnUiHWdxeJYyS8q3s/gBEXgHg18GcJ.VHFeRoitC' WHERE id = 2;
UPDATE user SET password = '$2a$10$F2ERRQiGv0yK5wnUiHWdxeJYyS8q3s/gBEXgHg18GcJ.VHFeRoitC' WHERE id = 3;
UPDATE user SET password = '$2a$10$F2ERRQiGv0yK5wnUiHWdxeJYyS8q3s/gBEXgHg18GcJ.VHFeRoitC' WHERE id = 4;
UPDATE user SET password = '$2a$10$F2ERRQiGv0yK5wnUiHWdxeJYyS8q3s/gBEXgHg18GcJ.VHFeRoitC' WHERE id = 5;
UPDATE user SET password = '$2a$10$F2ERRQiGv0yK5wnUiHWdxeJYyS8q3s/gBEXgHg18GcJ.VHFeRoitC' WHERE id = 6;
        
        
UPDATE content SET titlePicture = "https://i.ibb.co/7zDs3RN/best-gaming-pc-2020.jpg" WHERE id = 1;
UPDATE content SET titlePicture = "https://i.ibb.co/C9Pt9dH/ps5.jpg" WHERE id = 2;
UPDATE content SET titlePicture = "https://i.ibb.co/HGsPpCg/xbox-series-x.jpg" WHERE id = 3;
UPDATE content SET titlePicture = "https://i.ibb.co/X59nxFn/cyberpuck-2077.png" WHERE id = 4;
-- UPDATE content SET titlePicture = "https://i.ibb.co/7zDs3RN/best-gaming-pc-2020.jpg" WHERE id = 5;
-- UPDATE content SET titlePicture = "https://i.ibb.co/7zDs3RN/best-gaming-pc-2020.jpg" WHERE id = 6;
-- UPDATE content SET titlePicture = "https://i.ibb.co/7zDs3RN/best-gaming-pc-2020.jpg" WHERE id = 7;        

UPDATE content SET scheduleDate = "2020-6-1" WHERE id = 1;
UPDATE content SET scheduleDate = "2020-6-28" WHERE id = 2;
UPDATE content SET scheduleDate = "2020-7-1" WHERE id = 3;
UPDATE content SET scheduleDate = "2020-7-15" WHERE id = 4;
UPDATE content SET scheduleDate = "2020-7-26" WHERE id = 5;
UPDATE content SET scheduleDate = "2020-7-26" WHERE id = 6;
UPDATE content SET scheduleDate = "2020-7-26" WHERE id = 7;
        
        
        
        