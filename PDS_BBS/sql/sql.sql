/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50041
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50041
File Encoding         : 65001

Date: 2015-12-08 13:11:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(20) collate utf8_bin NOT NULL,
  `password` varchar(20) collate utf8_bin NOT NULL,
  `name` varchar(20) collate utf8_bin default NULL,
  `gender` varchar(2) collate utf8_bin default NULL,
  `age` int(11) default NULL,
  `email` varchar(30) collate utf8_bin default NULL,
  `phone` varchar(11) collate utf8_bin default NULL,
  `picture` varchar(255) collate utf8_bin default '',
  `scale` smallint(2) default '0',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL auto_increment,
  `author_id` int(11) NOT NULL,
  `pid` int(11) default NULL,
  `title` varchar(255) collate utf8_bin NOT NULL,
  `content` varchar(255) collate utf8_bin NOT NULL,
  `write_time` datetime default NULL,
  `last_update_time` datetime default NULL,
  `isleaf` tinyint(1) default '0',
  PRIMARY KEY  (`id`),
  KEY `FKD458CCF6E2D28C88` (`author_id`),
  CONSTRAINT `FKD458CCF6E2D28C88` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `id` int(11) NOT NULL,
  `filename` varchar(30) collate utf8_bin NOT NULL,
  `filetype` varchar(50) collate utf8_bin NOT NULL,
  `filedesc` varchar(255) collate utf8_bin default NULL,
  `file_author_id` int(11) NOT NULL,
  `filepath` varchar(255) collate utf8_bin NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_author_id` (`file_author_id`),
  CONSTRAINT `fk_author_id` FOREIGN KEY (`file_author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_uname` varchar(20) collate utf8_bin default NULL,
  `friend_id` int(11) NOT NULL,
  `friend_uname` varchar(20) collate utf8_bin default NULL,
  `add_time` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_uid` (`user_id`),
  KEY `fk_uname` (`friend_id`),
  CONSTRAINT `fk_uid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_uname` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
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
