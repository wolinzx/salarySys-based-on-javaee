/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : salarysys

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-12-31 20:37:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('admin', 'admin');
INSERT INTO `t_admin` VALUES ('111', '111');

-- ----------------------------
-- Table structure for t_attendance
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance`;
CREATE TABLE `t_attendance` (
  `staffid` int(11) DEFAULT NULL,
  `attdate` date DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attendance
-- ----------------------------
INSERT INTO `t_attendance` VALUES ('27', '2017-12-12', '请假');
INSERT INTO `t_attendance` VALUES ('27', '2017-12-12', '迟到');
INSERT INTO `t_attendance` VALUES ('45', '2017-12-12', '请假');
INSERT INTO `t_attendance` VALUES ('45', '2017-12-12', '请假');
INSERT INTO `t_attendance` VALUES ('45', '2017-12-12', '请假');
INSERT INTO `t_attendance` VALUES ('27', '2017-12-12', '早退');
INSERT INTO `t_attendance` VALUES ('46', '2017-12-12', '请假');
INSERT INTO `t_attendance` VALUES ('21', '2017-12-16', '请假');
INSERT INTO `t_attendance` VALUES ('45', '2017-12-16', '请假');
INSERT INTO `t_attendance` VALUES ('21', '2017-12-09', '请假');
INSERT INTO `t_attendance` VALUES ('45', '2017-12-02', '请假');
INSERT INTO `t_attendance` VALUES ('22', '2017-12-08', '早退');
INSERT INTO `t_attendance` VALUES ('55', '2017-12-27', '请假');
INSERT INTO `t_attendance` VALUES ('56', '2017-12-09', '迟到');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` int(11) DEFAULT NULL,
  `recordtype` varchar(20) DEFAULT NULL,
  `recorduser` varchar(20) DEFAULT NULL,
  `recorddate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES ('1', '25', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('2', '25', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('3', '28', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('4', '0', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('5', '0', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('6', '24', '修改资料', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('7', '24', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('8', '0', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('9', '25', '修改资料', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('10', '25', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('11', '31', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('12', '22', '登记出勤', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('13', '37', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('14', '49', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('15', '53', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('16', '47', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('17', '27', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('18', '26', '修改资料', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('19', '26', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('20', '29', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('21', '27', '修改资料', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('22', '30', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('23', '50', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('24', '27', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('25', '28', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('26', '30', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('27', '31', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('28', '32', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('29', '0', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('30', '33', '修改资料', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('31', '34', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('32', '54', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('33', '55', '登记出勤', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('34', '0', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('35', '55', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('36', '55', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('37', '55', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('38', '54', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('39', '0', '添加员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('40', '33', '修改资料', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('41', '33', '删除员工', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('42', '56', '工资发布', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('43', '56', '登记出勤', 'admin', '2017-12-27');
INSERT INTO `t_record` VALUES ('44', '56', '工资发布', 'admin', '2017-12-27');

-- ----------------------------
-- Table structure for t_salary
-- ----------------------------
DROP TABLE IF EXISTS `t_salary`;
CREATE TABLE `t_salary` (
  `staffid` int(11) NOT NULL AUTO_INCREMENT,
  `salary` int(11) DEFAULT NULL,
  `allsalary` int(11) DEFAULT NULL,
  `lastdate` date DEFAULT NULL,
  PRIMARY KEY (`staffid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_salary
-- ----------------------------
INSERT INTO `t_salary` VALUES ('35', '2029', '222', '2017-12-06');
INSERT INTO `t_salary` VALUES ('36', '2029', '222', '2017-12-06');
INSERT INTO `t_salary` VALUES ('37', '6000', '5855', '2017-12-27');
INSERT INTO `t_salary` VALUES ('38', '2000', '222', '2017-12-06');
INSERT INTO `t_salary` VALUES ('39', '3000', '222', '2017-12-06');
INSERT INTO `t_salary` VALUES ('40', '2000', '222', '2017-12-06');
INSERT INTO `t_salary` VALUES ('41', '2000', '222', '2017-12-06');
INSERT INTO `t_salary` VALUES ('42', '2000', '222', '2017-12-06');
INSERT INTO `t_salary` VALUES ('43', '2000', '222', '2017-12-06');
INSERT INTO `t_salary` VALUES ('45', '3000', '2534', '2017-12-27');
INSERT INTO `t_salary` VALUES ('46', '10200', '9860', '2017-12-06');
INSERT INTO `t_salary` VALUES ('47', '6600', '6395', '2017-12-27');
INSERT INTO `t_salary` VALUES ('48', '6600', null, null);
INSERT INTO `t_salary` VALUES ('49', '18900', '16055', '2017-12-27');
INSERT INTO `t_salary` VALUES ('50', '18900', '16055', '2017-12-27');
INSERT INTO `t_salary` VALUES ('51', '18900', '18900', '2017-12-27');
INSERT INTO `t_salary` VALUES ('52', '5600', '5600', '2017-12-27');
INSERT INTO `t_salary` VALUES ('53', '9600', '8935', '2017-12-27');
INSERT INTO `t_salary` VALUES ('56', '5900', '5747', '2017-12-27');

-- ----------------------------
-- Table structure for t_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff` (
  `staffid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `seniority` int(11) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `post` varchar(4) DEFAULT NULL,
  `depid` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`staffid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_staff
-- ----------------------------
INSERT INTO `t_staff` VALUES ('35', '123', '123456', '男', '4324', '2017-12-15', '销售员', '654', '3123');
INSERT INTO `t_staff` VALUES ('36', '123', '123456', '男', '123', '2017-12-08', '销售员', '123', '123');
INSERT INTO `t_staff` VALUES ('37', '687', '645', '女', '253', '2017-12-20', '程序员', '876', '68');
INSERT INTO `t_staff` VALUES ('38', '45', '6456', '男', '6546', '2017-12-23', '程序员', '786', '678');
INSERT INTO `t_staff` VALUES ('39', '645', '645', '女', '6456', '2017-12-24', '程序员', '678', '687');
INSERT INTO `t_staff` VALUES ('40', '123', '123456', '男', '342', '2017-12-09', '程序员', '312', '432');
INSERT INTO `t_staff` VALUES ('41', '312312', '123456', '男', '432423', '2017-12-14', '产品经理', '3123', '123');
INSERT INTO `t_staff` VALUES ('42', '312312', '123456', '男', '432423', '2017-12-14', '设计', '3123', '123');
INSERT INTO `t_staff` VALUES ('43', '213', '123456', '男', '12321', '2017-12-08', '研发', '12312', '12312');
INSERT INTO `t_staff` VALUES ('45', '薛鑫', '123456', '男', '4', '2017-12-14', '产品经理', '呵呵部', '110');
INSERT INTO `t_staff` VALUES ('46', '林章旭', '123456', '男', '4', '2017-12-08', '产品经理', '11', '11');
INSERT INTO `t_staff` VALUES ('47', '22', '123456', '男', '2', '2017-12-01', '研发', '12312', '3123');
INSERT INTO `t_staff` VALUES ('48', '33', '123456', '男', '2', '2017-11-29', '研发', '231', '123');
INSERT INTO `t_staff` VALUES ('49', '321', '123456', '男', '33', '2017-12-16', '产品经理', '3123', '123');
INSERT INTO `t_staff` VALUES ('50', '321', '123456', '男', '33', '2017-12-16', '产品经理', '3123', '123');
INSERT INTO `t_staff` VALUES ('51', '321', '123456', '男', '33', '2017-12-16', '产品经理', '3123', '123');
INSERT INTO `t_staff` VALUES ('52', '123', '123456', '男', '1', '2017-12-08', '运营', '123', '123');
INSERT INTO `t_staff` VALUES ('53', '111', '123456', '男', '2', '2017-12-01', '产品经理', '12314', '123213');
INSERT INTO `t_staff` VALUES ('56', '林章旭', '123456', '男', '3', '2017-12-08', '策划', '人事部', '110');
