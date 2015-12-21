/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50041
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50041
File Encoding         : 65001

Date: 2015-12-21 23:00:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(20) collate utf8_bin NOT NULL,
  `password` varchar(20) collate utf8_bin NOT NULL,
  `name` varchar(20) collate utf8_bin default NULL,
  `gender` varchar(2) collate utf8_bin default NULL,
  `age` int(11) default NULL,
  `email` varchar(30) collate utf8_bin default NULL,
  `phone` varchar(11) collate utf8_bin default NULL,
  `picture` varchar(255) collate utf8_bin default NULL,
  `scale` smallint(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL auto_increment,
  `author_id` int(11) NOT NULL,
  `type` varchar(255) collate utf8_bin NOT NULL,
  `title` varchar(255) collate utf8_bin NOT NULL,
  `content` longtext collate utf8_bin NOT NULL,
  `write_time` datetime default NULL,
  `last_update_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKD458CCF6E2D28C88` (`author_id`),
  CONSTRAINT `FKD458CCF6E2D28C88` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL auto_increment,
  `author_id` int(11) NOT NULL,
  `pid` int(11) default NULL,
  `content` longtext collate utf8_bin NOT NULL,
  `write_time` datetime default NULL,
  `last_update_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK38A5EE5FE2D28C88` (`author_id`),
  CONSTRAINT `FK38A5EE5FE2D28C88` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `id` int(11) NOT NULL auto_increment,
  `file_author_id` int(11) NOT NULL,
  `filename` varchar(30) collate utf8_bin NOT NULL,
  `filetype` varchar(50) collate utf8_bin NOT NULL,
  `filedesc` varchar(255) collate utf8_bin default NULL,
  `filepath` varchar(255) collate utf8_bin NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2EEFAA595BD185` (`file_author_id`),
  CONSTRAINT `FK2EEFAA595BD185` FOREIGN KEY (`file_author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `user_uname` varchar(20) collate utf8_bin default NULL,
  `friend_uname` varchar(20) collate utf8_bin default NULL,
  `add_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB4860A9E82199A48` (`user_id`),
  KEY `FKB4860A9E456DA695` (`friend_id`),
  CONSTRAINT `FKB4860A9E456DA695` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKB4860A9E82199A48` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(20) collate utf8_bin NOT NULL,
  `password` varchar(15) collate utf8_bin NOT NULL,
  `name` varchar(20) collate utf8_bin default NULL,
  `gender` varchar(5) collate utf8_bin default NULL,
  `birthday` date default NULL,
  `reg_date` datetime default NULL,
  `email` varchar(30) collate utf8_bin NOT NULL,
  `picture` varchar(255) collate utf8_bin default NULL,
  `question` varchar(30) collate utf8_bin default NULL,
  `answer` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
