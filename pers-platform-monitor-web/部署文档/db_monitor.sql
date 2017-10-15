/*
Navicat MySQL Data Transfer

Source Server         : cc
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : db_monitor

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-10-15 20:10:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `user_create_time` datetime DEFAULT NULL,
  `user_modify_time` datetime DEFAULT NULL,
  `user_name` varchar(50) NOT NULL,
  `locked` int(2) NOT NULL,
  `salt` varchar(50) NOT NULL,
  `remember_me` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '532224524@qq.com', '737d4aef8d278782f9663728034e67db', '17623089939', null, null, null, null, 'admin', '0', 'd9cb297199c5da0cf49c8017aeb1aa5bd7ef', null);
SET FOREIGN_KEY_CHECKS=1;
