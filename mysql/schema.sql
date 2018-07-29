-- 创建数据库
create database `kiwitheater` default character set utf8 collate utf8_general_ci;

use kiwitheater;

-- 测试用户密码均为 1234

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomname` varchar(32) NOT NULL,
  `anchorid` int(11) NOT NULL,
  `img` varchar(32) DEFAULT 'pic/null_room.jpg',
  `description` text,
  `category` int(11) DEFAULT NULL,
  `living` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

LOCK TABLES `room` WRITE;

INSERT INTO `room` VALUES (9,'room1',1,'pic/null_room.jpg','欢迎来到<br>anchor1<br><br>的直播间',3,0),(10,'room2',2,'pic/null_room.jpg',NULL,1,0),(11,'room3',3,'pic/null_room.jpg',NULL,1,0),(12,'room4',4,'pic/null_room.jpg',NULL,1,0),(13,'room5',5,'pic/null_room.jpg',NULL,1,0),(14,'room6',6,'pic/null_room.jpg',NULL,1,0),(15,'room7',7,'pic/null_room.jpg',NULL,1,0),(16,'room8',8,'pic/null_room.jpg',NULL,1,0),(17,'room9',9,'pic/null_room.jpg',NULL,1,0),(19,'超级恐怖的直播间',11,'pic/null_room.jpg','直播间！',5,0);

UNLOCK TABLES;



DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `coin` double(11,1) DEFAULT '0.0',
  `usedcoin` double(11,1) DEFAULT '0.0',
  `like` text,
  `avatar` varchar(32) DEFAULT 'pic/null_user.jpg',
  `roomid` int(11) DEFAULT NULL,
  `nickname` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;


LOCK TABLES `user` WRITE;

INSERT INTO `user` VALUES (1,'anchor1','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',9,'小明'),(2,'anchor2','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',10,'anchor2'),(3,'anchor3','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',11,'anchor3'),(4,'anchor4','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',12,'anchor4'),(5,'anchor5','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',13,'anchor5'),(6,'anchor6','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',14,'anchor6'),(7,'anchor7','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',15,'anchor7'),(8,'anchor8','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',16,'anchor8'),(9,'anchor9','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',17,'anchor9'),(10,'user0','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user0'),(11,'user1','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',19,'小红'),(12,'user2','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user2'),(13,'user3','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user3'),(14,'user4','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user4'),(15,'user5','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user5'),(16,'user6','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user6'),(17,'user7','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user7'),(18,'user8','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user8'),(19,'user9','81DC9BDB52D04DC20036DBD8313ED055',5.0,0.0,NULL,'pic/null_user.jpg',NULL,'user9');

UNLOCK TABLES;



