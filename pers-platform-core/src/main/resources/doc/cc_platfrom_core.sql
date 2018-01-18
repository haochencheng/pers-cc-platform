/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : cc_platfrom_core

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-01-18 21:29:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for api_access_counter
-- ----------------------------
DROP TABLE IF EXISTS `api_access_counter`;
CREATE TABLE `api_access_counter` (
  `api_user_auth_Id` int(11) NOT NULL,
  `pond` tinyint(4) NOT NULL COMMENT '池子，就是用来随机用的',
  `api_request_count` int(11) NOT NULL,
  PRIMARY KEY (`api_user_auth_Id`,`pond`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for api_list
-- ----------------------------
DROP TABLE IF EXISTS `api_list`;
CREATE TABLE `api_list` (
  `id` bigint(20) NOT NULL,
  `apiName` varchar(50) NOT NULL,
  `apiPath` varchar(50) NOT NULL,
  `apiStatus` tinyint(2) NOT NULL,
  `appName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for api_type
-- ----------------------------
DROP TABLE IF EXISTS `api_type`;
CREATE TABLE `api_type` (
  `id` bigint(20) NOT NULL,
  `apiTypeCode` varchar(20) NOT NULL,
  `apiTypeName` varchar(50) NOT NULL,
  `isFree` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for api_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `api_user_auth`;
CREATE TABLE `api_user_auth` (
  `id` decimal(10,0) NOT NULL,
  `user_id` decimal(10,0) NOT NULL,
  `api_list_Id` decimal(10,0) NOT NULL,
  `api_key` varchar(50) NOT NULL,
  `apiSecret` varchar(50) NOT NULL,
  `apiBeginTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `apiExpireTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `apiUserType` tinyint(2) NOT NULL,
  `apiRequestCount` decimal(10,0) NOT NULL,
  `islocked` tinyint(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for auto_id
-- ----------------------------
DROP TABLE IF EXISTS `auto_id`;
CREATE TABLE `auto_id` (
  `id_name` varchar(20) NOT NULL,
  `id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Procedure structure for get_increment_id
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_increment_id`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_increment_id`(in idname_in varchar(20), in small_in bigint, out id_out bigint)
begin  
declare oldid bigint;  
start transaction;  
select id into oldid from maibo_auto_id where idname=idname_in for update;  
if oldid is NULL then  
insert into maibo_auto_id(idname,id) value(idname_in, small_in);  
set id_out=small_in;  
else  
update maibo_auto_id set id=id+1 where idname=idname_in;  
set id_out=oldid+1;  
end if;  
commit;  
end
;;
DELIMITER ;
SET FOREIGN_KEY_CHECKS=1;
