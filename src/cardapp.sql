/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50640
Source Host           : 123.206.180.67:3306
Source Database       : cardapp

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-06-04 20:07:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_card
-- ----------------------------
DROP TABLE IF EXISTS `tb_card`;
CREATE TABLE `tb_card` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `isdelete` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_card
-- ----------------------------
INSERT INTO `tb_card` VALUES ('1', '孔涵', '18854801234', '山东省泰安市', '111111@qq.com', '1', '0');
INSERT INTO `tb_card` VALUES ('2', '啥发啥', 'asf', 'asf', '发', '1', '0');
INSERT INTO `tb_card` VALUES ('3', '按时发顺丰是', '啊司法所', 'aaa', 'aaa', '1', '1');
INSERT INTO `tb_card` VALUES ('4', 'aaaa', 'aaaa', 'aaaa', 'aaaa', '1', '0');
INSERT INTO `tb_card` VALUES ('5', 'eee', 'eee', 'eee', 'eee', '1', '0');
INSERT INTO `tb_card` VALUES ('6', 'rrrrr', 'rrr', 'rrrr', 'eee', '1', '0');
INSERT INTO `tb_card` VALUES ('7', 'asfasfasf', 'asfasf', '山东潍坊', '1234@qq.com', '3', '0');
INSERT INTO `tb_card` VALUES ('8', '孔涵1', '18854802345', 'asfasfa', '水电费', '3', '1');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `authrity` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '1');
INSERT INTO `tb_user` VALUES ('2', 'user', '4QrcOUm6Wau+VuBX8g+IPg==', '2');
INSERT INTO `tb_user` VALUES ('3', 'konghan', 'lueSGJZetyySpUndWjMBEg==', '2');
