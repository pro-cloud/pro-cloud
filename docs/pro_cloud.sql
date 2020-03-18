/*
 Navicat Premium Data Transfer

 Source Server         : 119.23.8.73
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 119.23.8.73:3306
 Source Schema         : pro_cloud

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 18/03/2020 22:23:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', 'admin,oss,websock', '$2a$10$PXm/Y1blhC5BPUHL01syP.y0/llhmQdfGEszUUqSn5qNdKPP8.6Xm', 'app', 'password,refresh_token', NULL, '', 10000, 19900, NULL, NULL, 0);
INSERT INTO `oauth_client_details` VALUES ('client_1', 'admin,oss', '$2a$10$PXm/Y1blhC5BPUHL01syP.y0/llhmQdfGEszUUqSn5qNdKPP8.6Xm', 'app', 'authorization_code,refresh_token', 'http://localhost:4040/sso1/login', '', 10000, 19900, NULL, 'true', 0);
INSERT INTO `oauth_client_details` VALUES ('client_2', NULL, '$2a$10$PXm/Y1blhC5BPUHL01syP.y0/llhmQdfGEszUUqSn5qNdKPP8.6Xm', 'app', 'authorization_code,password,refresh_token', NULL, NULL, 10000, 19900, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '编号',
  `parent_id` bigint(20) UNSIGNED NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域编码',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域类型',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_area_parent_id`(`parent_id`) USING BTREE,
  INDEX `sys_area_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES (1, 0, '0,', '中国', 10, '100000', '1', 1, '2019-11-25 08:00:00', 1, '2019-11-25 08:00:00', '', 0, 0);
INSERT INTO `sys_area` VALUES (2, 1, '0,1,', '江西省', 20, '110000', '2', 1, '2019-11-25 08:00:00', 1, '2019-11-25 08:00:00', '', 0, 0);
INSERT INTO `sys_area` VALUES (3, 2, '0,1,2,', '抚州市', 30, '120000', '2', 1, '2019-11-25 08:00:00', 1, '2019-11-25 08:00:00', '', 0, 0);
INSERT INTO `sys_area` VALUES (4, 2, '0,1,2,', '南昌', 40, '120000', '2', 1, '2019-11-25 08:00:00', 1, '2019-11-25 08:00:00', '', 0, 0);
INSERT INTO `sys_area` VALUES (5, 2, '0,1,2,', '九江', 50, '120000', '2', 1, '2019-11-25 08:00:00', 1, '2019-11-25 08:00:00', '', 0, 0);

-- ----------------------------
-- Table structure for sys_data_source_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_source_conf`;
CREATE TABLE `sys_data_source_conf`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_data_source_conf
-- ----------------------------
INSERT INTO `sys_data_source_conf` VALUES (1, 'database1', 'jdbc:mysql://119.23.8.73:3306/cloud_crm?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC', 'aijm', 'myadmin@qq.com', 1, '2019-06-13 23:24:03', 1, '2019-06-19 23:24:09', '', 0);
INSERT INTO `sys_data_source_conf` VALUES (2, 'database2', 'jdbc:mysql://119.23.8.73:3306/pro_cloud?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC', 'aijm', 'myadmin@qq.com', 1, '2019-06-13 23:24:03', 1, '2019-06-19 23:24:09', '', 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型字典/树 0 列表 1树形',
  `type_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `system` tinyint(1) NULL DEFAULT 0 COMMENT '是否是系统级别数据 1 系统 0 非系统',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '结算单位', 1, 'list_unit', 1, 1, '2019-11-11 18:11:37', 1, '2019-11-25 20:52:05', '结算单位', 0, 0);
INSERT INTO `sys_dict` VALUES (1195638740639420416, '2343', 0, '444', 0, 1, '2019-11-16 17:44:24', 1, '2019-11-16 17:44:24', '55', 0, 0);
INSERT INTO `sys_dict` VALUES (1195685529795039232, '组织结构', 1, 'tree_group', 0, 1, '2019-11-16 20:50:20', 1, '2019-11-25 20:54:10', '组织结构树形图', 0, 0);

-- ----------------------------
-- Table structure for sys_dict_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_list`;
CREATE TABLE `sys_dict_list`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `type_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '排序（升序）',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`label`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典项list' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_list
-- ----------------------------
INSERT INTO `sys_dict_list` VALUES (1, '2', '3', '4', '4400', 0, 1, '2019-11-06 19:16:45', 1, '2019-11-19 19:16:50', '1', 1, 0);
INSERT INTO `sys_dict_list` VALUES (1195666203889242112, '122341', '442', '444', '4400', 443, 1, '2019-11-16 19:33:32', 1, '2019-11-16 19:36:31', '445', 0, 0);
INSERT INTO `sys_dict_list` VALUES (1195686576139669504, '223991', '31', '441', 'list_34', 451, 1, '2019-11-16 20:54:29', 1, '2019-11-16 20:54:49', '5651', 0, 0);

-- ----------------------------
-- Table structure for sys_dict_tree
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_tree`;
CREATE TABLE `sys_dict_tree`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '编号',
  `parent_id` bigint(20) UNSIGNED NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所有父级编号',
  `type_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型code',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码；一般唯一',
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '排序（升序）',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典项tree' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_tree
-- ----------------------------
INSERT INTO `sys_dict_tree` VALUES (1195691936552456192, 0, '0,', '4400', '123', '1', 1, 1, '2019-11-16 21:15:47', 1, '2019-11-16 21:53:57', '1', 0, 0);
INSERT INTO `sys_dict_tree` VALUES (1195693614605406208, 0, '0,', '4400', '23', '3', 4, 1, '2019-11-16 21:22:27', 1, '2019-11-16 21:22:27', '45', 0, 0);
INSERT INTO `sys_dict_tree` VALUES (1195694269403369472, 0, '0,', '4400', '2', '2', 2, 1, '2019-11-16 21:25:03', 1, '2019-11-16 21:25:03', '2', 0, 0);
INSERT INTO `sys_dict_tree` VALUES (1195694387263311872, 0, '0,', '4400', '22', '22', 22, 1, '2019-11-16 21:25:32', 1, '2019-11-16 21:25:32', '22', 0, 0);
INSERT INTO `sys_dict_tree` VALUES (1195694684450721792, 0, '0,', '4400', '12', '12', 2, 1, '2019-11-16 21:26:42', 1, '2019-11-16 21:26:42', '3', 1, 0);
INSERT INTO `sys_dict_tree` VALUES (1195695613275148288, 0, '0,', '4400', '3', '44', 4, 1, '2019-11-16 21:30:24', 1, '2019-11-16 21:30:24', '4', 1, 0);
INSERT INTO `sys_dict_tree` VALUES (1195695833027317760, 0, '0,', '4400', '2', '2', 2, 1, '2019-11-16 21:31:16', 1, '2019-11-16 21:31:16', '2', 1, 0);
INSERT INTO `sys_dict_tree` VALUES (1195696256542969856, 1195691936552456192, '0,1195691936552456192,', '4400', '23336', '3333', 433, 1, '2019-11-16 21:32:57', 1, '2019-11-16 21:44:00', '333', 0, 0);
INSERT INTO `sys_dict_tree` VALUES (1195696706889584640, 1195691936552456192, '0,1195691936552456192,', '4400', '39993', '394', 39, 1, '2019-11-16 21:34:45', 1, '2019-11-16 21:52:37', '39', 0, 0);
INSERT INTO `sys_dict_tree` VALUES (1195713458444832768, 1195696256542969856, '0,1195691936552456192,1195696256542969856,', '4400', '23', '3', 4, 1, '2019-11-16 22:41:18', 1, '2019-11-16 22:41:18', '3', 0, 0);

-- ----------------------------
-- Table structure for sys_email_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_email_template`;
CREATE TABLE `sys_email_template`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `sender_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮件名称',
  `sender_account` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送邮件帐号',
  `sort` int(10) NOT NULL DEFAULT 0 COMMENT '排序（升序）',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题模版',
  `template` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容模板',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件模版表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_email_template
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `belong_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '归属应用',
  `belong_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '归属应用类别',
  `belong_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '归属应用状态；0：开启 ',
  `pre_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址前缀',
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文件名称（）目录名称',
  `file_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文件外网访问路径',
  `file_path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文件存储路径',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型(视频类型:0;音频类型:1; zip类型:2; doc类型:3; 图片类型:4; 其他类型:9;)',
  `file_size` bigint(50) NULL DEFAULT 0 COMMENT '文件大小',
  `file_suffix` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文件后缀',
  `props` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文件属性',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件统一管理(目录也是文件一种)' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1177472123753598976, 'default', 'default', 4, 'default', '1.csv', 'http://bj-oss-d-shujiajia.oss-cn-beijing.aliyuncs.com/default/default/default/20190927/142729/1.csv?Expires=1569906825&OSSAccessKeyId=LTAIER3YM8cdW2yI&Signature=UfrrsNUUIkP8npUxGX61YnD3agI%3D', 'default/default/default/20190927/142729/1.csv', '3', 0, 'csv', '', 1, '2019-09-27 14:36:45', 1, '2019-09-27 14:36:45', '', 0, 0);
INSERT INTO `sys_file` VALUES (1178500048879751168, 'default', 'default', 8, 'default', '1.csv', 'http://bj-oss-d-shujiajia.oss-cn-beijing.aliyuncs.com/default/default/default/20190930/103959/1.csv?Expires=1570151901&OSSAccessKeyId=LTAIER3YM8cdW2yI&Signature=B6RvY1h22tTWY5X7v4MpLbQayFY%3D', 'default/default/default/20190930/103959/1.csv', '3', 51, 'csv', '', 1, '2019-09-30 10:41:22', 1, '2019-09-30 10:41:22', '', 0, 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '编号',
  `parent_id` bigint(20) UNSIGNED NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `href` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `is_show` bigint(1) UNSIGNED NOT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_menu_parent_id`(`parent_id`) USING BTREE,
  INDEX `sys_menu_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '0,', '功能菜单', 0, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (2, 1, '0,1,', '系统设置', 900, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (3, 2, '0,1,2,', '系统设置', 980, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (4, 3, '0,1,2,3,', '菜单管理', 30, '/views/menu/menuList.html', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (5, 4, '0,1,2,3,4,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'sys:menu:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (6, 4, '0,1,2,3,4,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'sys:menu:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (7, 3, '0,1,2,3,', '角色管理', 50, '/views/user/role/roleList.html', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (8, 7, '0,1,2,3,7,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'sys:role:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (9, 7, '0,1,2,3,7,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'sys:role:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (10, 3, '0,1,2,3,', '字典管理', 60, '/views/dict/dictList.html', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (11, 10, '0,1,2,3,10,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'sys:dict:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (12, 10, '0,1,2,3,10,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'sys:dict:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (13, 2, '0,1,2,', '机构用户', 970, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (14, 13, '0,1,2,13,', '区域管理', 50, '/sys/area/', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (15, 14, '0,1,2,13,14,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'sys:area:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (16, 14, '0,1,2,13,14,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'sys:area:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (17, 13, '0,1,2,13,', '部门管理', 40, '/views/office/officeList.html', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (18, 17, '0,1,2,13,17,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'sys:office:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (19, 17, '0,1,2,13,17,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'sys:office:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (20, 13, '0,1,2,13,', '用户管理', 30, '/views/user/user/userList.html', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (21, 20, '0,1,2,13,20,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'sys:user:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (22, 20, '0,1,2,13,20,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'sys:user:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (23, 2, '0,1,2,', '关于帮助', 990, NULL, NULL, 'layui-icon-home', 0, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (24, 23, '0,1,2,23', '官方首页', 30, 'http://jeesite.com', '_blank', 'layui-icon-home', 0, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (25, 23, '0,1,2,23', '项目支持', 50, 'http://jeesite.com/donation.html', '_blank', 'layui-icon-home', 0, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 1, 0);
INSERT INTO `sys_menu` VALUES (26, 23, '0,1,2,23', '论坛交流', 80, 'http://bbs.jeesite.com', '_blank', 'layui-icon-home', 0, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 1, 0);
INSERT INTO `sys_menu` VALUES (27, 1, '0,1,', '我的面板', 100, '', '', 'layui-icon-home', 1, '1:23', 1, '2013-05-27 08:00:00', 1, '2019-11-22 13:25:02', '222', 0, 0);
INSERT INTO `sys_menu` VALUES (28, 27, '0,1,27,', '个人信息', 30, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (29, 28, '0,1,27,28,', '个人信息', 30, '/src/views/user/info/infoForm.html', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (30, 28, '0,1,27,28,', '修改密码', 40, '/sys/user/modifyPwd', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (31, 1, '0,1,', '内容管理', 500, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (32, 31, '0,1,31,', '栏目设置', 990, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (33, 32, '0,1,31,32', '栏目管理', 30, '/cms/category/', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (34, 33, '0,1,31,32,33,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'cms:category:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (35, 33, '0,1,31,32,33,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'cms:category:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (36, 32, '0,1,31,32', '站点设置', 40, '/cms/site/', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (37, 36, '0,1,31,32,36,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'cms:site:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (38, 36, '0,1,31,32,36,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'cms:site:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (39, 32, '0,1,31,32', '切换站点', 50, '/cms/site/select', NULL, 'layui-icon-home', 1, 'cms:site:select', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (40, 31, '0,1,31,', '内容管理', 500, NULL, NULL, 'layui-icon-home', 1, 'cms:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (41, 40, '0,1,31,40,', '内容发布', 30, '/cms/', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (42, 41, '0,1,31,40,41,', '文章模型', 40, '/cms/article/', NULL, 'layui-icon-home', 0, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (43, 42, '0,1,31,40,41,42,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'cms:article:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (44, 42, '0,1,31,40,41,42,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'cms:article:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (45, 42, '0,1,31,40,41,42,', '审核', 50, NULL, NULL, 'layui-icon-home', 0, 'cms:article:audit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (46, 41, '0,1,31,40,41,', '链接模型', 60, '/cms/link/', NULL, 'layui-icon-home', 0, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (47, 46, '0,1,31,40,41,46,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'cms:link:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (48, 46, '0,1,31,40,41,46,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'cms:link:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (49, 46, '0,1,31,40,41,46,', '审核', 50, NULL, NULL, 'layui-icon-home', 0, 'cms:link:audit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (50, 40, '0,1,31,40,', '评论管理', 40, '/cms/comment/?status=2', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (51, 50, '0,1,31,40,50,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'cms:comment:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (52, 50, '0,1,31,40,50,', '审核', 40, NULL, NULL, 'layui-icon-home', 0, 'cms:comment:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (53, 40, '0,1,31,40,', '公共留言', 80, '/cms/guestbook/?status=2', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (54, 53, '0,1,31,40,53,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'cms:guestbook:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (55, 53, '0,1,31,40,53,', '审核', 40, NULL, NULL, 'layui-icon-home', 0, 'cms:guestbook:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (56, 71, '0,1,27,71,', '文件管理', 90, '/../static/ckfinder/ckfinder.html', NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (57, 56, '0,1,27,40,56,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'cms:ckfinder:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (58, 56, '0,1,27,40,56,', '上传', 40, NULL, NULL, 'layui-icon-home', 0, 'cms:ckfinder:upload', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (59, 56, '0,1,27,40,56,', '修改', 50, NULL, NULL, 'layui-icon-home', 0, 'cms:ckfinder:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (60, 31, '0,1,31,', '统计分析', 600, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (61, 60, '0,1,31,60,', '信息量统计', 30, '/cms/stats/article', NULL, 'layui-icon-home', 1, 'cms:stats:article', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (62, 1, '0,1,', '在线办公', 200, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (63, 62, '0,1,62,', '个人办公', 30, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (64, 63, '0,1,62,63,', '请假办理', 300, '/oa/leave', NULL, 'layui-icon-home', 0, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (65, 64, '0,1,62,63,64,', '查看', 30, NULL, NULL, 'layui-icon-home', 0, 'oa:leave:view', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (66, 64, '0,1,62,63,64,', '修改', 40, NULL, NULL, 'layui-icon-home', 0, 'oa:leave:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (67, 2, '0,1,2,', '日志查询', 985, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-06-03 08:00:00', 1, '2013-06-03 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (68, 67, '0,1,2,67,', '日志查询', 30, '/sys/log', NULL, 'layui-icon-home', 1, 'sys:log:view', 1, '2013-06-03 08:00:00', 1, '2013-06-03 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (69, 62, '0,1,62,', '流程管理', 300, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (70, 69, '0,1,62,69,', '流程管理', 50, '/act/process', NULL, 'layui-icon-home', 1, 'act:process:edit', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (71, 27, '0,1,27,', '文件管理', 90, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (72, 69, '0,1,62,69,', '模型管理', 100, '/act/model', NULL, 'layui-icon-home', 1, 'act:model:edit', 1, '2013-09-20 08:00:00', 1, '2013-09-20 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (73, 63, '0,1,62,63,', '我的任务', 50, '/act/task/todo/', NULL, 'layui-icon-home', 1, NULL, 1, '2013-09-24 08:00:00', 1, '2013-09-24 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (74, 63, '0,1,62,63,', '审批测试', 100, '/oa/testAudit', NULL, 'layui-icon-home', 1, 'oa:testAudit:view,oa:testAudit:edit', 1, '2013-09-24 08:00:00', 1, '2013-09-24 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (75, 1, '0,1,', '在线演示', 3000, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-10-08 08:00:00', 1, '2013-10-08 08:00:00', '', 1, 0);
INSERT INTO `sys_menu` VALUES (79, 1, '0,1,', '代码生成', 5000, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-10-16 08:00:00', 1, '2013-10-16 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (80, 79, '0,1,79,', '代码生成', 50, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-10-16 08:00:00', 1, '2013-10-16 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (81, 80, '0,1,79,80,', '生成方案配置', 30, '/gen/genScheme', NULL, 'layui-icon-home', 1, 'gen:genScheme:view,gen:genScheme:edit', 1, '2013-10-16 08:00:00', 1, '2013-10-16 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (82, 80, '0,1,79,80,', '业务表配置', 20, '/gen/genTable', NULL, 'layui-icon-home', 1, 'gen:genTable:view,gen:genTable:edit,gen:genTableColumn:view,gen:genTableColumn:edit', 1, '2013-10-16 08:00:00', 1, '2013-10-16 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (83, 80, '0,1,79,80,', '代码模板管理', 90, '/gen/genTemplate', NULL, 'layui-icon-home', 1, 'gen:genTemplate:view,gen:genTemplate:edit', 1, '2013-10-16 08:00:00', 1, '2013-10-16 08:00:00', '', 1, 0);
INSERT INTO `sys_menu` VALUES (84, 67, '0,1,2,67,', '连接池监视', 40, '/../druid', NULL, 'layui-icon-home', 1, NULL, 1, '2013-10-18 08:00:00', 1, '2013-10-18 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (85, 76, '0,1,75,76,', '行政区域', 80, '/../static/map/map-city.html', NULL, 'layui-icon-home', 1, NULL, 1, '2013-10-22 08:00:00', 1, '2013-10-22 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (86, 75, '0,1,75,', '组件演示', 50, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-10-22 08:00:00', 1, '2013-10-22 08:00:00', '', 1, 0);
INSERT INTO `sys_menu` VALUES (87, 86, '0,1,75,86,', '组件演示', 30, '/test/test/form', NULL, 'layui-icon-home', 1, 'test:test:view,test:test:edit', 1, '2013-10-22 08:00:00', 1, '2013-10-22 08:00:00', '', 1, 0);
INSERT INTO `sys_menu` VALUES (88, 62, '0,1,62,', '通知通告', 20, '', '', 'layui-icon-home', 1, '', 1, '2013-11-08 08:00:00', 1, '2013-11-08 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (89, 88, '0,1,62,88,', '我的通告', 30, '/oa/oaNotify/self', '', 'layui-icon-home', 1, '', 1, '2013-11-08 08:00:00', 1, '2013-11-08 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (90, 88, '0,1,62,88,', '通告管理', 50, '/oa/oaNotify', '', 'layui-icon-home', 1, 'oa:oaNotify:view,oa:oaNotify:edit', 1, '2013-11-08 08:00:00', 1, '2013-11-08 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (101, 2, '0,1,79,3c92c17886944d0687e73e286cada573,', '树结构', 90, '/test/testTree', '', 'layui-icon-home', 1, '', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 0, 0);
INSERT INTO `sys_menu` VALUES (200, 3, '0,1,79,3c92c17886944d0687e73e286cada573,b1f6d1b86ba24365bae7fd86c5082317,', '查看', 30, '', '', 'layui-icon-home', 0, 'test:testDataMain:view', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 0, 0);
INSERT INTO `sys_menu` VALUES (230, 43, '0,1,79,3c92c17886944d0687e73e286cada573,0ca004d6b1bf4bcab9670a5060d82a55,', '编辑', 60, '', '', 'layui-icon-home', 0, 'test:testTree:edit', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 0, 0);
INSERT INTO `sys_menu` VALUES (231, 2, '0,1,79,3c92c17886944d0687e73e286cada573,', '主子表', 60, '/test/testDataMain', '', 'layui-icon-home', 1, '', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 0, 0);
INSERT INTO `sys_menu` VALUES (232, 2, '0,1,79,3c92c17886944d0687e73e286cada573,', '单表', 30, '/test/testData', '', 'layui-icon-home', 1, '', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 0, 0);
INSERT INTO `sys_menu` VALUES (233, 2, '0,1,79,3c92c17886944d0687e73e286cada573,0ca004d6b1bf4bcab9670a5060d82a55,', '查看', 30, '', '', 'layui-icon-home', 0, 'test:testTree:view', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 0, 0);
INSERT INTO `sys_menu` VALUES (234, 2, '0,1,79,3c92c17886944d0687e73e286cada573,b1f6d1b86ba24365bae7fd86c5082317,', '编辑', 60, '', '', 'layui-icon-home', 0, 'test:testDataMain:edit', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 0, 0);
INSERT INTO `sys_menu` VALUES (235, 2, '0,1,79,3c92c17886944d0687e73e286cada573,ba8092291b40482db8fe7fc006ea3d76,', '查看', 30, '', '', 'layui-icon-home', 0, 'test:testData:view', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 0, 0);
INSERT INTO `sys_menu` VALUES (239, 79, '0,1,79,', '生成示例', 120, '', '', 'layui-icon-home', 1, '', 1, '2013-08-12 13:10:05', 1, '2013-08-12 13:10:05', '', 1, 0);
INSERT INTO `sys_menu` VALUES (300, 0, '0,', '功能菜单12221113331', 0, NULL, NULL, 'layui-icon-home', 1, NULL, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_menu` VALUES (1195953400785801216, 27, '0,1,27,', '1', 2, '2', '2', 'layui-icon-home', 1, '2', 1, '2019-11-17 14:34:45', 1, '2019-11-17 16:18:42', '2', 1, 0);
INSERT INTO `sys_menu` VALUES (1195953681598648320, 27, '0,1,27,', '23', 3, '23', '34', 'layui-icon-home', 0, '3', 1, '2019-11-17 14:35:52', 1, '2019-11-17 14:35:52', '34', 1, 0);
INSERT INTO `sys_menu` VALUES (1195979424252694528, 27, '0,1,27,', '2239966', 6, '34556', '66', 'layui-icon-home', 1, '6', 1, '2019-11-17 16:18:10', 1, '2019-11-17 16:18:26', '6', 0, 0);
INSERT INTO `sys_menu` VALUES (1196455498405253120, 27, '0,1,27,', 'redis', 1, 'redis', 'redis', 'layui-icon-home', 0, 'redis', 1, '2019-11-18 23:49:55', 1, '2019-11-18 23:49:55', 'redis', 0, 0);
INSERT INTO `sys_menu` VALUES (1196456888225632256, 27, '0,1,27,', 'reids2', 2, '2', '2', 'layui-icon-home', 1, '2', 1, '2019-11-18 23:55:26', 1, '2019-11-18 23:55:26', '2', 0, 0);
INSERT INTO `sys_menu` VALUES (1196457016177070080, 27, '0,1,27,', 'redis2', 2, 'redis2', 'redios', 'layui-icon-home', 0, '2', 1, '2019-11-18 23:55:56', 1, '2019-11-18 23:55:56', '2', 0, 0);

-- ----------------------------
-- Table structure for sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg`;
CREATE TABLE `sys_msg`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `request_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '发短信请求id',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '短信类型:1.用户注册验证码2.登录确认验证码',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '内容',
  `send_state` tinyint(1) NOT NULL COMMENT '发送状态 0 失败1 成功',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信发送消息日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_msg
-- ----------------------------

-- ----------------------------
-- Table structure for sys_msg_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg_config`;
CREATE TABLE `sys_msg_config`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '编号',
  `sms_plat_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信平台地址',
  `sms_plat_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信平台帐号',
  `sms_plat_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信平台密码',
  `sender_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送短信签名',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信配置信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_msg_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '编号',
  `parent_id` bigint(20) UNSIGNED NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构全称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构代码,唯一字段',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构类型',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `primary_person` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主负责人',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_office_parent_id`(`parent_id`) USING BTREE,
  INDEX `sys_office_del_flag`(`del_flag`) USING BTREE,
  INDEX `sys_office_type`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES (1, 0, '0,', '江西省总公司', '江西省总公司', 10, '', '1', '江西省抚州市', '2', '18210584253', '1223696910@qq.com', 'CEO', 1, '2019-11-25 08:00:00', 1, '2019-11-25 22:36:51', '江西省', 0, 0);
INSERT INTO `sys_office` VALUES (2, 1, '0,1,', '分公司A', '江西省分公司A', 10, '', '1', '分公司A', '2', '18210584253', '1223696910@qq.com', 'CEO', 1, '2019-11-25 08:00:00', 1, '2019-11-25 22:36:51', '江西省分公司A', 0, 0);
INSERT INTO `sys_office` VALUES (3, 1, '0,1,', '分公司B', '江西省分公司B', 10, '', '1', '分公司B', '2', '1810584253', '1223696910@qq.com', 'CEO', 1, '2019-11-25 08:00:00', 1, '2019-11-25 22:36:51', '江西省分公司B', 0, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `enname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `role_type` tinyint(1) NULL DEFAULT NULL COMMENT '角色类型',
  `data_scope` tinyint(1) NULL DEFAULT NULL COMMENT '数据范围',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_role_del_flag`(`del_flag`) USING BTREE,
  INDEX `sys_role_enname`(`enname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'dept', 1, 1, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '', 0, 0);
INSERT INTO `sys_role` VALUES (2, '公司管理员', 'hr', 1, 2, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 0, 0);
INSERT INTO `sys_role` VALUES (3, '本公司管理员', 'a', 1, 3, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 0, 0);
INSERT INTO `sys_role` VALUES (4, '部门管理员', 'b', 1, 4, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 0, 0);
INSERT INTO `sys_role` VALUES (5, '本部门管理员', 'c', 1, 5, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 0, 0);
INSERT INTO `sys_role` VALUES (6, '普通用户', 'd', 1, 8, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 1, 0);
INSERT INTO `sys_role` VALUES (7, '济南市管理员', 'e', 1, 9, 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 0, 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色编号',
  `menu_id` bigint(20) UNSIGNED NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 27);
INSERT INTO `sys_role_menu` VALUES (1, 28);
INSERT INTO `sys_role_menu` VALUES (1, 29);
INSERT INTO `sys_role_menu` VALUES (1, 30);
INSERT INTO `sys_role_menu` VALUES (1, 31);
INSERT INTO `sys_role_menu` VALUES (1, 32);
INSERT INTO `sys_role_menu` VALUES (1, 33);
INSERT INTO `sys_role_menu` VALUES (1, 34);
INSERT INTO `sys_role_menu` VALUES (1, 35);
INSERT INTO `sys_role_menu` VALUES (1, 36);
INSERT INTO `sys_role_menu` VALUES (1, 37);
INSERT INTO `sys_role_menu` VALUES (1, 38);
INSERT INTO `sys_role_menu` VALUES (1, 39);
INSERT INTO `sys_role_menu` VALUES (1, 40);
INSERT INTO `sys_role_menu` VALUES (1, 41);
INSERT INTO `sys_role_menu` VALUES (1, 42);
INSERT INTO `sys_role_menu` VALUES (1, 43);
INSERT INTO `sys_role_menu` VALUES (1, 44);
INSERT INTO `sys_role_menu` VALUES (1, 45);
INSERT INTO `sys_role_menu` VALUES (1, 46);
INSERT INTO `sys_role_menu` VALUES (1, 47);
INSERT INTO `sys_role_menu` VALUES (1, 48);
INSERT INTO `sys_role_menu` VALUES (1, 49);
INSERT INTO `sys_role_menu` VALUES (1, 50);
INSERT INTO `sys_role_menu` VALUES (1, 51);
INSERT INTO `sys_role_menu` VALUES (1, 52);
INSERT INTO `sys_role_menu` VALUES (1, 53);
INSERT INTO `sys_role_menu` VALUES (1, 54);
INSERT INTO `sys_role_menu` VALUES (1, 55);
INSERT INTO `sys_role_menu` VALUES (1, 56);
INSERT INTO `sys_role_menu` VALUES (1, 57);
INSERT INTO `sys_role_menu` VALUES (1, 58);
INSERT INTO `sys_role_menu` VALUES (1, 59);
INSERT INTO `sys_role_menu` VALUES (1, 60);
INSERT INTO `sys_role_menu` VALUES (1, 61);
INSERT INTO `sys_role_menu` VALUES (1, 62);
INSERT INTO `sys_role_menu` VALUES (1, 63);
INSERT INTO `sys_role_menu` VALUES (1, 64);
INSERT INTO `sys_role_menu` VALUES (1, 65);
INSERT INTO `sys_role_menu` VALUES (1, 66);
INSERT INTO `sys_role_menu` VALUES (1, 67);
INSERT INTO `sys_role_menu` VALUES (1, 68);
INSERT INTO `sys_role_menu` VALUES (1, 69);
INSERT INTO `sys_role_menu` VALUES (1, 70);
INSERT INTO `sys_role_menu` VALUES (1, 71);
INSERT INTO `sys_role_menu` VALUES (1, 72);
INSERT INTO `sys_role_menu` VALUES (1, 73);
INSERT INTO `sys_role_menu` VALUES (1, 74);
INSERT INTO `sys_role_menu` VALUES (1, 75);
INSERT INTO `sys_role_menu` VALUES (1, 76);
INSERT INTO `sys_role_menu` VALUES (1, 77);
INSERT INTO `sys_role_menu` VALUES (1, 78);
INSERT INTO `sys_role_menu` VALUES (1, 79);
INSERT INTO `sys_role_menu` VALUES (1, 80);
INSERT INTO `sys_role_menu` VALUES (1, 81);
INSERT INTO `sys_role_menu` VALUES (1, 82);
INSERT INTO `sys_role_menu` VALUES (1, 83);
INSERT INTO `sys_role_menu` VALUES (1, 84);
INSERT INTO `sys_role_menu` VALUES (1, 85);
INSERT INTO `sys_role_menu` VALUES (1, 86);
INSERT INTO `sys_role_menu` VALUES (1, 87);
INSERT INTO `sys_role_menu` VALUES (1, 88);
INSERT INTO `sys_role_menu` VALUES (1, 89);
INSERT INTO `sys_role_menu` VALUES (1, 90);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 7);
INSERT INTO `sys_role_menu` VALUES (2, 8);
INSERT INTO `sys_role_menu` VALUES (2, 9);
INSERT INTO `sys_role_menu` VALUES (2, 10);
INSERT INTO `sys_role_menu` VALUES (2, 11);
INSERT INTO `sys_role_menu` VALUES (2, 12);
INSERT INTO `sys_role_menu` VALUES (2, 13);
INSERT INTO `sys_role_menu` VALUES (2, 14);
INSERT INTO `sys_role_menu` VALUES (2, 15);
INSERT INTO `sys_role_menu` VALUES (2, 16);
INSERT INTO `sys_role_menu` VALUES (2, 17);
INSERT INTO `sys_role_menu` VALUES (2, 18);
INSERT INTO `sys_role_menu` VALUES (2, 19);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 24);
INSERT INTO `sys_role_menu` VALUES (2, 25);
INSERT INTO `sys_role_menu` VALUES (2, 26);
INSERT INTO `sys_role_menu` VALUES (2, 27);
INSERT INTO `sys_role_menu` VALUES (2, 28);
INSERT INTO `sys_role_menu` VALUES (2, 29);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 31);
INSERT INTO `sys_role_menu` VALUES (2, 32);
INSERT INTO `sys_role_menu` VALUES (2, 33);
INSERT INTO `sys_role_menu` VALUES (2, 34);
INSERT INTO `sys_role_menu` VALUES (2, 35);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (2, 37);
INSERT INTO `sys_role_menu` VALUES (2, 38);
INSERT INTO `sys_role_menu` VALUES (2, 39);
INSERT INTO `sys_role_menu` VALUES (2, 40);
INSERT INTO `sys_role_menu` VALUES (2, 41);
INSERT INTO `sys_role_menu` VALUES (2, 42);
INSERT INTO `sys_role_menu` VALUES (2, 43);
INSERT INTO `sys_role_menu` VALUES (2, 44);
INSERT INTO `sys_role_menu` VALUES (2, 45);
INSERT INTO `sys_role_menu` VALUES (2, 46);
INSERT INTO `sys_role_menu` VALUES (2, 47);
INSERT INTO `sys_role_menu` VALUES (2, 48);
INSERT INTO `sys_role_menu` VALUES (2, 49);
INSERT INTO `sys_role_menu` VALUES (2, 50);
INSERT INTO `sys_role_menu` VALUES (2, 51);
INSERT INTO `sys_role_menu` VALUES (2, 52);
INSERT INTO `sys_role_menu` VALUES (2, 53);
INSERT INTO `sys_role_menu` VALUES (2, 54);
INSERT INTO `sys_role_menu` VALUES (2, 55);
INSERT INTO `sys_role_menu` VALUES (2, 56);
INSERT INTO `sys_role_menu` VALUES (2, 57);
INSERT INTO `sys_role_menu` VALUES (2, 58);
INSERT INTO `sys_role_menu` VALUES (2, 59);
INSERT INTO `sys_role_menu` VALUES (2, 60);
INSERT INTO `sys_role_menu` VALUES (2, 61);
INSERT INTO `sys_role_menu` VALUES (2, 62);
INSERT INTO `sys_role_menu` VALUES (2, 63);
INSERT INTO `sys_role_menu` VALUES (2, 64);
INSERT INTO `sys_role_menu` VALUES (2, 65);
INSERT INTO `sys_role_menu` VALUES (2, 66);
INSERT INTO `sys_role_menu` VALUES (2, 67);
INSERT INTO `sys_role_menu` VALUES (2, 68);
INSERT INTO `sys_role_menu` VALUES (2, 69);
INSERT INTO `sys_role_menu` VALUES (2, 70);
INSERT INTO `sys_role_menu` VALUES (2, 71);
INSERT INTO `sys_role_menu` VALUES (2, 72);
INSERT INTO `sys_role_menu` VALUES (2, 73);
INSERT INTO `sys_role_menu` VALUES (2, 74);
INSERT INTO `sys_role_menu` VALUES (2, 75);
INSERT INTO `sys_role_menu` VALUES (2, 76);
INSERT INTO `sys_role_menu` VALUES (2, 77);
INSERT INTO `sys_role_menu` VALUES (2, 78);
INSERT INTO `sys_role_menu` VALUES (2, 79);
INSERT INTO `sys_role_menu` VALUES (2, 80);
INSERT INTO `sys_role_menu` VALUES (2, 81);
INSERT INTO `sys_role_menu` VALUES (2, 82);
INSERT INTO `sys_role_menu` VALUES (2, 83);
INSERT INTO `sys_role_menu` VALUES (2, 84);
INSERT INTO `sys_role_menu` VALUES (2, 85);
INSERT INTO `sys_role_menu` VALUES (2, 86);
INSERT INTO `sys_role_menu` VALUES (2, 87);
INSERT INTO `sys_role_menu` VALUES (2, 88);
INSERT INTO `sys_role_menu` VALUES (2, 89);
INSERT INTO `sys_role_menu` VALUES (2, 90);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 5);
INSERT INTO `sys_role_menu` VALUES (3, 6);
INSERT INTO `sys_role_menu` VALUES (3, 7);
INSERT INTO `sys_role_menu` VALUES (3, 8);
INSERT INTO `sys_role_menu` VALUES (3, 9);
INSERT INTO `sys_role_menu` VALUES (3, 10);
INSERT INTO `sys_role_menu` VALUES (3, 11);
INSERT INTO `sys_role_menu` VALUES (3, 12);
INSERT INTO `sys_role_menu` VALUES (3, 13);
INSERT INTO `sys_role_menu` VALUES (3, 14);
INSERT INTO `sys_role_menu` VALUES (3, 15);
INSERT INTO `sys_role_menu` VALUES (3, 16);
INSERT INTO `sys_role_menu` VALUES (3, 17);
INSERT INTO `sys_role_menu` VALUES (3, 18);
INSERT INTO `sys_role_menu` VALUES (3, 19);
INSERT INTO `sys_role_menu` VALUES (3, 20);
INSERT INTO `sys_role_menu` VALUES (3, 21);
INSERT INTO `sys_role_menu` VALUES (3, 22);
INSERT INTO `sys_role_menu` VALUES (3, 23);
INSERT INTO `sys_role_menu` VALUES (3, 24);
INSERT INTO `sys_role_menu` VALUES (3, 25);
INSERT INTO `sys_role_menu` VALUES (3, 26);
INSERT INTO `sys_role_menu` VALUES (3, 27);
INSERT INTO `sys_role_menu` VALUES (3, 28);
INSERT INTO `sys_role_menu` VALUES (3, 29);
INSERT INTO `sys_role_menu` VALUES (3, 30);
INSERT INTO `sys_role_menu` VALUES (3, 31);
INSERT INTO `sys_role_menu` VALUES (3, 32);
INSERT INTO `sys_role_menu` VALUES (3, 33);
INSERT INTO `sys_role_menu` VALUES (3, 34);
INSERT INTO `sys_role_menu` VALUES (3, 35);
INSERT INTO `sys_role_menu` VALUES (3, 36);
INSERT INTO `sys_role_menu` VALUES (3, 37);
INSERT INTO `sys_role_menu` VALUES (3, 38);
INSERT INTO `sys_role_menu` VALUES (3, 39);
INSERT INTO `sys_role_menu` VALUES (3, 40);
INSERT INTO `sys_role_menu` VALUES (3, 41);
INSERT INTO `sys_role_menu` VALUES (3, 42);
INSERT INTO `sys_role_menu` VALUES (3, 43);
INSERT INTO `sys_role_menu` VALUES (3, 44);
INSERT INTO `sys_role_menu` VALUES (3, 45);
INSERT INTO `sys_role_menu` VALUES (3, 46);
INSERT INTO `sys_role_menu` VALUES (3, 47);
INSERT INTO `sys_role_menu` VALUES (3, 48);
INSERT INTO `sys_role_menu` VALUES (3, 49);
INSERT INTO `sys_role_menu` VALUES (3, 50);
INSERT INTO `sys_role_menu` VALUES (3, 51);
INSERT INTO `sys_role_menu` VALUES (3, 52);
INSERT INTO `sys_role_menu` VALUES (3, 53);
INSERT INTO `sys_role_menu` VALUES (3, 54);
INSERT INTO `sys_role_menu` VALUES (3, 55);
INSERT INTO `sys_role_menu` VALUES (3, 56);
INSERT INTO `sys_role_menu` VALUES (3, 57);
INSERT INTO `sys_role_menu` VALUES (3, 58);
INSERT INTO `sys_role_menu` VALUES (3, 59);
INSERT INTO `sys_role_menu` VALUES (3, 60);
INSERT INTO `sys_role_menu` VALUES (3, 61);
INSERT INTO `sys_role_menu` VALUES (3, 62);
INSERT INTO `sys_role_menu` VALUES (3, 63);
INSERT INTO `sys_role_menu` VALUES (3, 64);
INSERT INTO `sys_role_menu` VALUES (3, 65);
INSERT INTO `sys_role_menu` VALUES (3, 66);
INSERT INTO `sys_role_menu` VALUES (3, 67);
INSERT INTO `sys_role_menu` VALUES (3, 68);
INSERT INTO `sys_role_menu` VALUES (3, 69);
INSERT INTO `sys_role_menu` VALUES (3, 70);
INSERT INTO `sys_role_menu` VALUES (3, 71);
INSERT INTO `sys_role_menu` VALUES (3, 72);
INSERT INTO `sys_role_menu` VALUES (3, 73);
INSERT INTO `sys_role_menu` VALUES (3, 74);
INSERT INTO `sys_role_menu` VALUES (3, 75);
INSERT INTO `sys_role_menu` VALUES (3, 76);
INSERT INTO `sys_role_menu` VALUES (3, 77);
INSERT INTO `sys_role_menu` VALUES (3, 78);
INSERT INTO `sys_role_menu` VALUES (3, 79);
INSERT INTO `sys_role_menu` VALUES (3, 80);
INSERT INTO `sys_role_menu` VALUES (3, 81);
INSERT INTO `sys_role_menu` VALUES (3, 82);
INSERT INTO `sys_role_menu` VALUES (3, 83);
INSERT INTO `sys_role_menu` VALUES (3, 84);
INSERT INTO `sys_role_menu` VALUES (3, 85);
INSERT INTO `sys_role_menu` VALUES (3, 86);
INSERT INTO `sys_role_menu` VALUES (3, 87);
INSERT INTO `sys_role_menu` VALUES (3, 88);
INSERT INTO `sys_role_menu` VALUES (3, 89);
INSERT INTO `sys_role_menu` VALUES (3, 90);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '编号',
  `office_id` bigint(20) UNSIGNED NOT NULL COMMENT '归属部门',
  `login_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `user_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型0：管理员 1:教师 2:学生 ',
  `photo` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `login_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登陆IP',
  `login_flag` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否可登录',
  `create_by` bigint(20) UNSIGNED NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) UNSIGNED NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
  `wx_openid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '微信id',
  `qq_openid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'qq id',
  `tenant_id` int(11) NULL DEFAULT 0 COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_user_office_id`(`office_id`) USING BTREE,
  INDEX `sys_user_login_name`(`login_name`) USING BTREE,
  INDEX `sys_user_update_date`(`update_date`) USING BTREE,
  INDEX `sys_user_del_flag`(`del_flag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 2, 'myadmin', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0011', '全球CEO', '2929783435@qq.com', '18210584253', '18210584253', '2', NULL, NULL, NULL, '1', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 0, '', '', 0);
INSERT INTO `sys_user` VALUES (12, 2, 'hyq_admin', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0012', '江西南昌领导', '2929783435@qq.com', '18210584253', '18210584253', '2', NULL, NULL, NULL, '1', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 0, '', '', 0);
INSERT INTO `sys_user` VALUES (13, 2, '2929783435@qq.com', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0013', '济南抚州领导', '2929783435@qq.com', '18210584253', '18210584253', '2', NULL, NULL, NULL, '1', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', ' ', 0, '', '', 0);
INSERT INTO `sys_user` VALUES (124, 1, 'aijiamin1', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0001', '系统管理员', '2929783435@qq.com', '18210684253', '18210584253', '2', NULL, NULL, NULL, '1', 1, '2013-05-27 08:00:00', 1, '2013-05-27 08:00:00', '最高管理员', 0, '', '', 0);
INSERT INTO `sys_user` VALUES (1182672899547467776, 2, 'admin', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0001', '系统管理员', '2929783435@qq.com', '18210684254', '8675', '2', NULL, NULL, NULL, '1', 1, '2019-10-11 23:02:47', 1, '2019-10-11 23:02:47', '最高管理员222', 0, '', '', 0);
INSERT INTO `sys_user` VALUES (1196764259711848448, 2, 'syd_admin', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0002', '管理员', '2929783435@qq.com', '18210584253', '18210584253', '1', '', NULL, NULL, NULL, 1, '2019-11-19 20:16:49', 1, '2019-11-19 20:16:49', '', 0, '', '', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户编号',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);
INSERT INTO `sys_user_role` VALUES (4, 3);
INSERT INTO `sys_user_role` VALUES (5, 4);
INSERT INTO `sys_user_role` VALUES (7, 2);
INSERT INTO `sys_user_role` VALUES (7, 7);
INSERT INTO `sys_user_role` VALUES (8, 2);
INSERT INTO `sys_user_role` VALUES (9, 1);
INSERT INTO `sys_user_role` VALUES (10, 2);
INSERT INTO `sys_user_role` VALUES (11, 3);
INSERT INTO `sys_user_role` VALUES (12, 4);
INSERT INTO `sys_user_role` VALUES (13, 5);
INSERT INTO `sys_user_role` VALUES (14, 6);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
