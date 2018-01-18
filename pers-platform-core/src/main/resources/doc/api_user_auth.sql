/*
 Navicat Premium Data Transfer

 Source Server         : localMysql
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : cc_platform_core

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 18/01/2018 19:50:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `api_user_auth`;
CREATE TABLE `api_user_auth`  (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `apiListId` bigint(20) NOT NULL,
  `apiKey` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apiSecret` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apiBeginTime` datetime(0) DEFAULT NULL,
  `apiExpireTime` datetime(0) DEFAULT NULL,
  `apiUserType` tinyint(2) NOT NULL DEFAULT 0,
  `apiRequestType` tinyint(2) DEFAULT 0,
  `locked` tinyint(2) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
