/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 03/05/2021 12:09:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accept_goods
-- ----------------------------
DROP TABLE IF EXISTS `accept_goods`;
CREATE TABLE `accept_goods`  (
  `id` int(0) NOT NULL,
  `accept_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bill_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `price` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `tax` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `yes_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `no_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `shortage` int(255) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `surplus` int(255) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for accept_inform
-- ----------------------------
DROP TABLE IF EXISTS `accept_inform`;
CREATE TABLE `accept_inform`  (
  `id` int(0) NOT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accept_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `provider` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contract` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `invoice` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_information
-- ----------------------------
DROP TABLE IF EXISTS `base_information`;
CREATE TABLE `base_information`  (
  `id` int(0) NOT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '暂时保留为null',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for check_form
-- ----------------------------
DROP TABLE IF EXISTS `check_form`;
CREATE TABLE `check_form`  (
  `id` int(0) NOT NULL,
  `head_id` int(0) NOT NULL COMMENT '对应check_header的id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '暂时保留，不用处理，默认为null',
  `actual_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '实有数',
  `price` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `book_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '账面数',
  `incre_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '增加数',
  `decre_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '减少数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for check_header
-- ----------------------------
DROP TABLE IF EXISTS `check_header`;
CREATE TABLE `check_header`  (
  `id` int(0) NOT NULL,
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_principal` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `checker` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `check_date` datetime(0) NULL DEFAULT NULL,
  `keeper` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for department_manage
-- ----------------------------
DROP TABLE IF EXISTS `department_manage`;
CREATE TABLE `department_manage`  (
  `id` bigint(0) NOT NULL,
  `depart_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `principal` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for entry_form
-- ----------------------------
DROP TABLE IF EXISTS `entry_form`;
CREATE TABLE `entry_form`  (
  `id` int(0) NOT NULL,
  `accept_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `entry_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `entry_date` datetime(0) NULL DEFAULT NULL,
  `keeper` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for large_equipment
-- ----------------------------
DROP TABLE IF EXISTS `large_equipment`;
CREATE TABLE `large_equipment`  (
  `id` int(0) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `buy_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for material_purchase
-- ----------------------------
DROP TABLE IF EXISTS `material_purchase`;
CREATE TABLE `material_purchase`  (
  `id` int(0) NOT NULL,
  `table_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `material_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `material_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `depart_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `purchase_num` int(0) NULL DEFAULT NULL,
  `part_used` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purchase_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for material_requisition
-- ----------------------------
DROP TABLE IF EXISTS `material_requisition`;
CREATE TABLE `material_requisition`  (
  `id` int(0) NOT NULL,
  `requisition_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `due_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `actual_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `price` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `tax` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `reamrk` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for out_form
-- ----------------------------
DROP TABLE IF EXISTS `out_form`;
CREATE TABLE `out_form`  (
  `id` int(0) NOT NULL,
  `requisition_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `out_date` datetime(0) NULL DEFAULT NULL,
  `out_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `purpose` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for plan_deadline
-- ----------------------------
DROP TABLE IF EXISTS `plan_deadline`;
CREATE TABLE `plan_deadline`  (
  `id` int(0) NOT NULL,
  `deadline` int(0) NULL DEFAULT NULL COMMENT '截止日期，1~31之间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for purchase_information
-- ----------------------------
DROP TABLE IF EXISTS `purchase_information`;
CREATE TABLE `purchase_information`  (
  `id` int(0) NOT NULL,
  `approver` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `closing_date` datetime(0) NULL,
  `auditor` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `prepare` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repo_authority
-- ----------------------------
DROP TABLE IF EXISTS `repo_authority`;
CREATE TABLE `repo_authority`  (
  `id` bigint(0) NOT NULL,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repo_position
-- ----------------------------
DROP TABLE IF EXISTS `repo_position`;
CREATE TABLE `repo_position`  (
  `id` bigint(0) NOT NULL,
  `position_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repo_position_authority
-- ----------------------------
DROP TABLE IF EXISTS `repo_position_authority`;
CREATE TABLE `repo_position_authority`  (
  `id` bigint(0) NOT NULL,
  `p_id` bigint(0) NOT NULL,
  `a_id` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  INDEX `a_id`(`a_id`) USING BTREE,
  CONSTRAINT `repo_position_authority_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `repo_position` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `repo_position_authority_ibfk_2` FOREIGN KEY (`a_id`) REFERENCES `repo_authority` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repo_role
-- ----------------------------
DROP TABLE IF EXISTS `repo_role`;
CREATE TABLE `repo_role`  (
  `id` bigint(0) NOT NULL,
  `p_id` bigint(0) NOT NULL,
  `d_id` bigint(0) NOT NULL,
  `role_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  INDEX `repo_role_ibfk_2`(`d_id`) USING BTREE,
  CONSTRAINT `repo_role_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `repo_position` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `repo_role_ibfk_2` FOREIGN KEY (`d_id`) REFERENCES `department_manage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repo_user
-- ----------------------------
DROP TABLE IF EXISTS `repo_user`;
CREATE TABLE `repo_user`  (
  `id` bigint(0) NOT NULL,
  `uesrname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for repo_user_role
-- ----------------------------
DROP TABLE IF EXISTS `repo_user_role`;
CREATE TABLE `repo_user_role`  (
  `id` bigint(0) NOT NULL,
  `u_id` bigint(0) NOT NULL,
  `r_id` bigint(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `r_id`(`r_id`) USING BTREE,
  CONSTRAINT `repo_user_role_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `repo_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `repo_user_role_ibfk_2` FOREIGN KEY (`r_id`) REFERENCES `repo_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for requisition_header
-- ----------------------------
DROP TABLE IF EXISTS `requisition_header`;
CREATE TABLE `requisition_header`  (
  `id` int(0) NOT NULL,
  `provider` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accept_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requisition_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `principal` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `depart_leader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_leader` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for service_provider
-- ----------------------------
DROP TABLE IF EXISTS `service_provider`;
CREATE TABLE `service_provider`  (
  `id` int(0) NOT NULL,
  `regist_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address_post` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contacts` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for store_inform
-- ----------------------------
DROP TABLE IF EXISTS `store_inform`;
CREATE TABLE `store_inform`  (
  `id` int(0) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `specification` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_num` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
