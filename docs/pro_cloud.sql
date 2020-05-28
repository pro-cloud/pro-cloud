/*
Navicat MySQL Data Transfer

Source Server         : 119.23.8.73
Source Server Version : 50726
Source Host           : 119.23.8.73:3306
Source Database       : pro_cloud

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-05-28 19:52:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gen_data_source
-- ----------------------------
DROP TABLE IF EXISTS `gen_data_source`;
CREATE TABLE `gen_data_source` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据源表';

-- ----------------------------
-- Records of gen_data_source
-- ----------------------------
INSERT INTO `gen_data_source` VALUES ('2', 'database2', 'jdbc:mysql://119.23.8.73:3306/pro_cloud?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC', 'aijm', '23454', '1', '2019-06-13 23:24:03', '1', '2019-06-19 23:24:09', '', '0');

-- ----------------------------
-- Table structure for gen_scheme
-- ----------------------------
DROP TABLE IF EXISTS `gen_scheme`;
CREATE TABLE `gen_scheme` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `source_Id` bigint(20) NOT NULL COMMENT '数据源id',
  `author` varchar(100) NOT NULL COMMENT '作者名',
  `table_name` varchar(200) DEFAULT NULL COMMENT '表名',
  `category` char(1) DEFAULT NULL COMMENT '分类',
  `package_name` varchar(500) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `class_name` varchar(500) DEFAULT NULL COMMENT '生成功能名,也为实体名',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='生成代码表';

-- ----------------------------
-- Records of gen_scheme
-- ----------------------------
INSERT INTO `gen_scheme` VALUES ('1', '0', 'Aijm', 'gen_scheme', '2', 'com.cloud', 'generator', null, '1', '2020-05-13 16:05:04', '1', '2020-05-16 22:25:56', '', '0');
INSERT INTO `gen_scheme` VALUES ('2', '0', 'Aijm', 'sys_user', '1', 'com.cloud', 'admin', null, '1', '2020-05-13 16:05:04', '1', '2020-05-16 16:04:57', '', '1');
INSERT INTO `gen_scheme` VALUES ('1264588529246277632', '0', 'aijmtom', 'sys_user', '0', 'com.cloud.aijm', 'aijm', 'Tom', '1', '2020-05-25 00:06:15', '1', '2020-05-25 00:06:15', '', '1');
INSERT INTO `gen_scheme` VALUES ('1264786002204037120', '0', 'Aijm', 'sys_tenant', '0', 'com.cloud', 'admin', '', '1', '2020-05-25 13:10:56', '1', '2020-05-25 13:32:18', '', '0');

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', 'admin,oss,websock', '$2a$10$PXm/Y1blhC5BPUHL01syP.y0/llhmQdfGEszUUqSn5qNdKPP8.6Xm', 'app', 'password,refresh_token', null, '', '100000', '199000', null, null, '0');
INSERT INTO `oauth_client_details` VALUES ('client_1', 'admin,oss', '$2a$10$PXm/Y1blhC5BPUHL01syP.y0/llhmQdfGEszUUqSn5qNdKPP8.6Xm', 'app', 'authorization_code,refresh_token', 'http://localhost:4040/sso1/login', '', '10000', '19900', null, 'true', '0');
INSERT INTO `oauth_client_details` VALUES ('client_2', null, '$2a$10$PXm/Y1blhC5BPUHL01syP.y0/llhmQdfGEszUUqSn5qNdKPP8.6Xm', 'app', 'authorization_code,password,refresh_token', null, null, '10000', '19900', null, null, '0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型字典/树 0 列表 1树形',
  `type_code` varchar(100) DEFAULT NULL COMMENT '类型编码',
  `system` tinyint(1) DEFAULT '0' COMMENT '是否是系统级别数据 1 系统 0 非系统',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '结算单位', '0', 'list_unit', '1', '1', '2019-11-11 18:11:37', '1', '2020-05-05 22:20:14', '结算单位', '0', '0');
INSERT INTO `sys_dict` VALUES ('1195638740639420416', '2343', '0', '444', '0', '1', '2019-11-16 17:44:24', '1', '2019-11-16 17:44:24', '55', '1', '0');
INSERT INTO `sys_dict` VALUES ('1195685529795039232', '组织结构', '1', 'tree_group', '0', '1', '2019-11-16 20:50:20', '1', '2019-11-25 20:54:10', '组织结构树形图', '0', '0');
INSERT INTO `sys_dict` VALUES ('1257656154767626240', '性别', '0', 'list_set', '0', '1', '2020-05-05 20:59:28', '1', '2020-05-05 20:59:28', '备注', '1', '0');
INSERT INTO `sys_dict` VALUES ('1257672896508203008', 'set', '1', 'list_addr_dd', '0', '1', '2020-05-05 22:06:00', '1', '2020-05-05 22:08:52', '2', '1', '0');
INSERT INTO `sys_dict` VALUES ('1257672952271474688', 'set', '1', 'list_addr', '1', '1', '2020-05-05 22:06:13', '1', '2020-05-05 22:06:13', '2666', '0', '0');
INSERT INTO `sys_dict` VALUES ('1257844392513900544', '333', '0', 'tree_group', '0', '1', '2020-05-06 09:27:28', '1', '2020-05-06 09:27:28', '', '0', '0');
INSERT INTO `sys_dict` VALUES ('1257844446570090496', 'list_addr', '0', 'list_addr', '0', '1', '2020-05-06 09:27:40', '1', '2020-05-06 09:27:40', '', '1', '0');
INSERT INTO `sys_dict` VALUES ('1258026859564765184', '111', '0', 'tree_addr', '0', '1', '2020-05-06 21:32:31', '1', '2020-05-06 21:32:31', '11', '1', '0');
INSERT INTO `sys_dict` VALUES ('1258027287203418112', '11', '0', 'list_addr', '0', '1', '2020-05-06 21:34:13', '1', '2020-05-06 21:34:13', '22', '1', '0');
INSERT INTO `sys_dict` VALUES ('1258027627655073792', '11', '0', 'list_addr', '0', '1', '2020-05-06 21:35:34', '1', '2020-05-06 21:35:34', '11', '1', '0');
INSERT INTO `sys_dict` VALUES ('1258028194817249280', '11', '0', 'list_addr', '0', '1', '2020-05-06 21:37:49', '1', '2020-05-06 21:37:49', '11', '1', '0');

-- ----------------------------
-- Table structure for sys_dict_list
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_list`;
CREATE TABLE `sys_dict_list` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(100) DEFAULT NULL COMMENT '值',
  `label` varchar(100) DEFAULT NULL COMMENT '标签',
  `type_code` varchar(100) DEFAULT NULL COMMENT '编码',
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '排序（升序）',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典项list';

-- ----------------------------
-- Records of sys_dict_list
-- ----------------------------
INSERT INTO `sys_dict_list` VALUES ('1', '2', '3', '4', '444', '0', '1', '2019-11-06 19:16:45', '1', '2019-11-19 19:16:50', '1', '1', '0');
INSERT INTO `sys_dict_list` VALUES ('1195666203889242112', '122341', '442', '444', 'list_unit', '443', '1', '2019-11-16 19:33:32', '1', '2019-11-16 19:36:31', '445', '0', '0');
INSERT INTO `sys_dict_list` VALUES ('1195686576139669504', '2239913', '31', '441', 'list_unit', '451', '1', '2019-11-16 20:54:29', '1', '2020-05-05 19:40:11', '5651', '0', '0');
INSERT INTO `sys_dict_list` VALUES ('1257630149059416064', '122341', '4429', '55,677', 'list_unit', '43', '1', '2020-05-05 19:16:08', '1', '2020-05-05 19:43:16', '445', '1', '0');
INSERT INTO `sys_dict_list` VALUES ('1257633802302394368', '77', '77', '78', '444', '4', '1', '2020-05-05 19:30:39', '1', '2020-05-05 19:30:39', '', '1', '0');
INSERT INTO `sys_dict_list` VALUES ('1257633868152967168', '55', '555', '', '444', '10', '1', '2020-05-05 19:30:55', '1', '2020-05-05 19:35:58', '', '0', '0');
INSERT INTO `sys_dict_list` VALUES ('1258402796651286528', '你的', '1', 'my,nu', 'list_unit', '10', '1', '2020-05-07 22:26:21', '1', '2020-05-24 15:38:08', '', '0', '0');

-- ----------------------------
-- Table structure for sys_dict_tree
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_tree`;
CREATE TABLE `sys_dict_tree` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(220) NOT NULL COMMENT '所有父级编号',
  `type_code` varchar(100) DEFAULT NULL COMMENT '字典类型code',
  `name` varchar(100) DEFAULT NULL COMMENT '标签',
  `value` varchar(100) DEFAULT NULL COMMENT '编码；一般唯一',
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '排序（升序）',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典项tree';

-- ----------------------------
-- Records of sys_dict_tree
-- ----------------------------
INSERT INTO `sys_dict_tree` VALUES ('1195691936552456192', '0', '0,', '4400', '123', '1', '1', '1', '2019-11-16 21:15:47', '1', '2019-11-16 21:53:57', '1', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195693614605406208', '0', '0,', '4400', '23', '3', '4', '1', '2019-11-16 21:22:27', '1', '2019-11-16 21:22:27', '45', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195694269403369472', '0', '0,', '4400', '2', '2', '2', '1', '2019-11-16 21:25:03', '1', '2019-11-16 21:25:03', '2', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195694387263311872', '0', '0,', '4400', '22', '22', '22', '1', '2019-11-16 21:25:32', '1', '2019-11-16 21:25:32', '22', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195694684450721792', '0', '0,', '4400', '12', '12', '2', '1', '2019-11-16 21:26:42', '1', '2019-11-16 21:26:42', '3', '1', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195695613275148288', '0', '0,', '4400', '3', '44', '4', '1', '2019-11-16 21:30:24', '1', '2019-11-16 21:30:24', '4', '1', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195695833027317760', '0', '0,', '4400', '2', '2', '2', '1', '2019-11-16 21:31:16', '1', '2019-11-16 21:31:16', '2', '1', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195696256542969856', '0', '0,', 'tree_group', '23336', '3333', '433', '1', '2019-11-16 21:32:57', '1', '2019-11-16 21:44:00', '333', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195696706889584640', '1195696256542969856', '0,1195696256542969856,', 'tree_group', '39993', '394', '39', '1', '2019-11-16 21:34:45', '1', '2019-11-16 21:52:37', '39', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1195713458444832768', '1195696256542969856', '0,1195696256542969856,', 'tree_group', '23', '3', '4', '1', '2019-11-16 22:41:18', '1', '2019-11-16 22:41:18', '3', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258410251594633216', '1195713458444832768', '0,1195696256542969856,1195713458444832768,', 'tree_group', '11', '11', '10', '1', '2020-05-07 22:55:59', '1', '2020-05-07 22:55:59', '', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258410322780360704', '1195696706889584640', '0,1195696256542969856,1195696706889584640,', 'tree_group', '111', '11', '10', '1', '2020-05-07 22:56:16', '1', '2020-05-07 22:56:16', '111', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258410386823188480', '1195696706889584640', '0,1195696256542969856,1195696706889584640,', 'tree_group', '111', '11', '10', '1', '2020-05-07 22:56:31', '1', '2020-05-07 22:56:31', '111', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258410456637378560', '1195696706889584640', '0,1195696256542969856,1195696706889584640,', 'tree_group', '11444', '11', '10', '1', '2020-05-07 22:56:48', '1', '2020-05-07 22:56:48', '111', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258411574088044544', '0', '0,', 'tree_group', '第二级', '3', '10', '1', '2020-05-07 23:01:14', '1', '2020-05-07 23:01:14', '33', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258412045511036928', '0', '0,', 'tree_group', '第二级v', '1', '10', '1', '2020-05-07 23:03:06', '1', '2020-05-07 23:03:06', '', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258412539012845568', '0', '0,', 'tree_group', '000', '0', '10', '1', '2020-05-07 23:05:04', '1', '2020-05-07 23:05:04', '1', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258412934275665920', '0', '0,', 'tree_group', '0999', '6', '10', '1', '2020-05-07 23:06:38', '1', '2020-05-07 23:06:38', '', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258413276828667904', '1258412045511036928', '0,1258412045511036928,', 'tree_group', '第二级', '33', '10', '1', '2020-05-07 23:08:00', '1', '2020-05-07 23:08:00', '33', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258419353683103744', '0', '0,', 'tree_group', '第一个', '111', '10', '1', '2020-05-07 23:32:09', '1', '2020-05-07 23:32:09', '', '0', '0');
INSERT INTO `sys_dict_tree` VALUES ('1258419459572502528', '1258419353683103744', '0,1258419353683103744,', 'tree_group', '999', '00', '10', '1', '2020-05-07 23:32:34', '1', '2020-05-07 23:32:34', '90', '0', '0');

-- ----------------------------
-- Table structure for sys_email_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_email_template`;
CREATE TABLE `sys_email_template` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `sender_name` varchar(100) NOT NULL DEFAULT '' COMMENT '邮件名称',
  `sender_account` varchar(100) NOT NULL COMMENT '发送邮件帐号',
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '排序（升序）',
  `title` varchar(100) NOT NULL COMMENT '标题模版',
  `template` varchar(2000) NOT NULL COMMENT '内容模板',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件模版表';

-- ----------------------------
-- Records of sys_email_template
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `belong_name` varchar(100) NOT NULL DEFAULT '' COMMENT '归属应用',
  `belong_type` varchar(100) NOT NULL DEFAULT '' COMMENT '归属应用类别',
  `belong_status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '归属应用状态；0：开启 ',
  `pre_path` varchar(100) DEFAULT NULL COMMENT '文件地址前缀',
  `file_name` varchar(100) NOT NULL DEFAULT '' COMMENT '文件名称（）目录名称',
  `file_url` varchar(300) NOT NULL DEFAULT '' COMMENT '文件外网访问路径',
  `file_path` varchar(100) NOT NULL DEFAULT '' COMMENT '文件存储路径',
  `type` varchar(50) DEFAULT NULL COMMENT '文件类型(视频类型:0;音频类型:1; zip类型:2; doc类型:3; 图片类型:4; 其他类型:9;)',
  `file_size` bigint(50) DEFAULT '0' COMMENT '文件大小',
  `file_suffix` varchar(10) NOT NULL DEFAULT '' COMMENT '文件后缀',
  `props` varchar(20) DEFAULT '' COMMENT '文件属性',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件统一管理(目录也是文件一种)';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('1177472123753598976', 'default', 'default', '4', 'default', '1.csv', '', 'default/default/default/20190927/142729/1.csv', '3', '0', 'csv', '', '1', '2019-09-27 14:36:45', '1', '2019-09-27 14:36:45', '', '0', '0');
INSERT INTO `sys_file` VALUES ('1178500048879751168', 'default', 'default', '8', 'default', '1.csv', '', 'default/default/default/20190930/103959/1.csv', '3', '51', 'csv', '', '1', '2019-09-30 10:41:22', '1', '2019-09-30 10:41:22', '', '0', '0');
INSERT INTO `sys_file` VALUES ('1259469794223198208', 'cloud', 'admin', '0', 'profile/photo', '22.png', '', 'cloud/admin/profile/photo/200510/210612/22.png', '4', '47210', 'png', '1139*621', '1', '2020-05-10 21:06:13', '1', '2020-05-10 21:06:13', '', '0', '0');
INSERT INTO `sys_file` VALUES ('1259477046225145856', 'cloud', 'admin', '0', 'profile/photo', '2689526-874dc44e81a0bcd1.png', '', 'cloud/admin/profile/photo/20200510/213500/2689526-874dc44e81a0bcd1.png', '4', '59849', 'png', '772*183', '1', '2020-05-10 21:35:02', '1', '2020-05-10 21:35:02', '', '0', '0');
INSERT INTO `sys_file` VALUES ('1259480871262621696', 'cloud', 'admin', '0', 'profile/photo', 'P}KB~WLGPKSPDKIVUXCLGH3.png', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'cloud/admin/profile/photo/20200510/215012/P}KB~WLGPKSPDKIVUXCLGH3.png', '4', '237689', 'png', '732*510', '1', '2020-05-10 21:50:14', '1', '2020-05-10 21:50:14', '', '0', '0');
INSERT INTO `sys_file` VALUES ('1259481106646962176', 'cloud', 'admin', '0', 'profile/photo', 'P}KB~WLGPKSPDKIVUXCLGH3.png', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'cloud/admin/profile/photo/20200510/215108/P}KB~WLGPKSPDKIVUXCLGH3.png', '4', '237689', 'png', '732*510', '1', '2020-05-10 21:51:11', '1', '2020-05-10 21:51:11', '', '0', '0');
INSERT INTO `sys_file` VALUES ('1259481310766960640', 'cloud', 'admin', '0', 'profile/photo', 'P}KB~WLGPKSPDKIVUXCLGH3.png', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'cloud/admin/profile/photo/20200510/215157/P}KB~WLGPKSPDKIVUXCLGH3.png', '4', '237689', 'png', '732*510', '1', '2020-05-10 21:51:59', '1', '2020-05-10 21:51:59', '', '0', '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(220) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `type` char(1) DEFAULT '' COMMENT '菜单类型0 目录 1 组建 2.外部链接 3按钮',
  `target` varchar(100) DEFAULT '' COMMENT '目标',
  `icon` varchar(100) DEFAULT '' COMMENT '图标',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否在菜单中显示(1 显示 0 隐藏)',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`),
  KEY `sys_menu_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '0,', '系统设置', '900', '0', '', 'fa fa-gear', '1', null, '1', '2020-05-01 08:00:00', '1', '2020-05-24 21:24:51', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '0,1,', '菜单管理', '100', '1', '/sys/menu/index', 'fa fa-tachometer', '1', '', '1', '2020-05-01 08:00:00', '1', '2020-05-24 21:29:53', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258044495979745280', '2', '0,1,2,', '添加菜单', '30', '3', null, 'bug', '0', 'admin_sysmenu_add', '1', '2020-05-06 22:42:36', '1', '2020-05-06 22:48:19', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258044642629390336', '2', '0,1,2,', '修改菜单', '30', '3', null, 'bug', '0', 'admin_sysmenu_edit', '1', '2020-05-06 22:43:11', '1', '2020-05-06 22:48:30', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258044742063755264', '2', '0,1,2,', '删除菜单', '30', '3', null, 'bug', '0', 'admin_sysmenu_del', '1', '2020-05-06 22:43:35', '1', '2020-05-06 22:47:57', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258045171631788032', '2', '0,1,2,', '查询菜单', '30', '3', null, 'bug', '0', 'admin_sysmenu_view', '1', '2020-05-06 22:45:17', '1', '2020-05-06 22:48:08', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258045601027854336', '1', '0,1,', '用户管理', '30', '1', '/sys/user/index', 'fa fa-group', '1', null, '1', '2020-05-06 22:46:59', '1', '2020-05-24 21:28:04', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258045758737879040', '1258045601027854336', '0,1,1258045601027854336,', '添加用户', '30', '3', '', 'bug', '0', 'admin_sysuser_add', '1', '2020-05-06 22:47:37', '1', '2020-05-26 23:41:00', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258046246191501312', '1258045601027854336', '0,1,1258045601027854336,', '修改用户', '30', '3', null, 'bug', '0', 'admin_sysuser_edit', '1', '2020-05-06 22:49:33', '1', '2020-05-06 22:49:33', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258046357961314304', '1258045601027854336', '0,1,1258045601027854336,', '删除用户', '30', '3', null, 'bug', '0', 'admin_sysuser_del', '1', '2020-05-06 22:50:00', '1', '2020-05-06 22:50:17', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1258046525712502784', '1258045601027854336', '0,1,1258045601027854336,', '查询用户', '30', '3', null, 'bug', '0', 'admin_sysuser_view', '1', '2020-05-06 22:50:40', '1', '2020-05-06 22:50:40', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1259153262855196672', '1', '0,1,', '字典管理', '30', '1', '/sys/dict/index', 'fa fa-map', '1', null, '1', '2020-05-10 00:08:26', '1', '2020-05-24 21:27:27', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1259393565025505280', '1', '0,1,', '部门管理', '30', '1', '/sys/dept/index', 'fa fa-suitcase', '1', null, '1', '2020-05-10 16:03:19', '1', '2020-05-24 21:28:54', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1259394035957764096', '1', '0,1,', '角色管理', '30', '1', '/sys/role/index', 'fa fa-user-circle', '1', null, '1', '2020-05-10 16:05:11', '1', '2020-05-24 21:27:45', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1261658051136589824', '1', '0,1,', '代码生成', '30', '1', '/generator/index', 'fa fa-save', '1', null, '1', '2020-05-16 22:01:35', '1', '2020-05-24 21:26:39', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1263481815482634240', '0', '0,', '监控管理', '30', '0', '', 'fa fa-desktop', '1', null, '1', '2020-05-21 22:48:34', '1', '2020-05-24 21:23:42', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1263482033192177664', '1263481815482634240', '0,1263481815482634240,', '接口文档', '30', '1', '/swagger/index', 'fa fa-file-pdf-o', '1', null, '1', '2020-05-21 22:49:26', '1', '2020-05-24 21:31:55', '', '0', '0');
INSERT INTO `sys_menu` VALUES ('1264796258971815936', '1', '0,1,', '租户管理', '30', '1', '/admin/index', 'fa fa-cloud', '1', '', '1', '2020-05-25 13:51:42', '1', '2020-05-25 13:51:42', '', '0', '0');

-- ----------------------------
-- Table structure for sys_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg`;
CREATE TABLE `sys_msg` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `request_id` varchar(100) NOT NULL DEFAULT '' COMMENT '发短信请求id',
  `type` char(1) NOT NULL DEFAULT '1' COMMENT '短信类型:1.用户注册验证码2.登录确认验证码',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `content` varchar(255) DEFAULT '' COMMENT '内容',
  `send_state` tinyint(1) NOT NULL COMMENT '发送状态 0 失败1 成功',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送消息日志';

-- ----------------------------
-- Records of sys_msg
-- ----------------------------

-- ----------------------------
-- Table structure for sys_msg_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_msg_config`;
CREATE TABLE `sys_msg_config` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `sms_plat_url` varchar(255) NOT NULL COMMENT '短信平台地址',
  `sms_plat_account` varchar(255) NOT NULL COMMENT '短信平台帐号',
  `sms_plat_password` varchar(200) NOT NULL COMMENT '短信平台密码',
  `sender_name` varchar(255) DEFAULT NULL COMMENT '发送短信签名',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信配置信息';

-- ----------------------------
-- Records of sys_msg_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(200) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `primary_person` varchar(64) DEFAULT NULL COMMENT '主负责人',
  `status` tinyint(4) unsigned DEFAULT '0' COMMENT '状态0 启用 1 禁用',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('1', '0', '0,', '江西省总公司', '10', '江西省抚州市', '18210584444', '2929783435@qq.com', 'CEO', '0', '1', '2019-11-25 08:00:00', '1', '2020-05-24 15:39:56', '江西省', '0', '0');
INSERT INTO `sys_office` VALUES ('2', '1', '0,1,', '分公司A', '10', '江西省抚州市', '18210584444', '2929783435@qq.com', 'CEO', '0', '1', '2019-11-25 08:00:00', '1', '2019-11-25 22:36:51', '江西省分公司A', '0', '0');
INSERT INTO `sys_office` VALUES ('3', '1', '0,1,', '分公司B', '10', '江西省抚州市', '18210584444', '2929783435@qq.com', 'CEO', '0', '1', '2019-11-25 08:00:00', '1', '2019-11-25 22:36:51', '江西省分公司B', '0', '0');
INSERT INTO `sys_office` VALUES ('1256447722286878720', '1', '0,1,', '部门D', '30', null, '111', '633@qq.com', '3334', '1', '1', '2020-05-02 12:57:35', '1', '2020-05-24 17:08:11', '', '0', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `enname` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `role_type` tinyint(1) DEFAULT NULL COMMENT '角色类型',
  `data_scope` tinyint(1) DEFAULT NULL COMMENT '数据范围',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`),
  KEY `sys_role_enname` (`enname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', 'dept', '1', '1', '1', '2020-05-24 21:34:04', '1', '2020-05-24 21:34:04', '', '0', '0');
INSERT INTO `sys_role` VALUES ('2', '公司管理员', 'hr', '1', '2', '1', '2020-05-24 21:34:04', '1', '2020-05-24 21:34:04', ' ', '0', '0');
INSERT INTO `sys_role` VALUES ('3', '本公司管理员', 'a', '1', '3', '1', '2020-05-24 21:34:04', '1', '2020-05-24 21:34:04', ' ', '0', '0');
INSERT INTO `sys_role` VALUES ('4', '部门管理员', 'b', '1', '3', '1', '2020-05-24 21:34:04', '1', '2020-05-24 21:34:04', ' ', '0', '0');
INSERT INTO `sys_role` VALUES ('5', '本部门管理员', 'c', '1', '3', '1', '2020-05-24 21:34:04', '1', '2020-05-24 21:34:04', ' ', '0', '0');
INSERT INTO `sys_role` VALUES ('6', '普通用户', 'd', '1', '2', '1', '2020-05-24 21:34:04', '1', '2020-05-24 21:34:04', ' ', '1', '0');
INSERT INTO `sys_role` VALUES ('7', '管理员i', 'e', '1', '1', '1', '2020-05-24 21:34:04', '1', '2020-05-24 21:34:04', ' ', '1', '0');
INSERT INTO `sys_role` VALUES ('1256634632846315520', '我的数', 'od', '1', '2', '1', '2020-05-24 21:34:04', '1', '2020-05-24 21:34:04', '', '1', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色编号',
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '1258044495979745280');
INSERT INTO `sys_role_menu` VALUES ('1', '1258044642629390336');
INSERT INTO `sys_role_menu` VALUES ('1', '1258044742063755264');
INSERT INTO `sys_role_menu` VALUES ('1', '1258045171631788032');
INSERT INTO `sys_role_menu` VALUES ('1', '1258045601027854336');
INSERT INTO `sys_role_menu` VALUES ('1', '1258045758737879040');
INSERT INTO `sys_role_menu` VALUES ('1', '1258046246191501312');
INSERT INTO `sys_role_menu` VALUES ('1', '1258046357961314304');
INSERT INTO `sys_role_menu` VALUES ('1', '1258046525712502784');
INSERT INTO `sys_role_menu` VALUES ('1', '1259153262855196672');
INSERT INTO `sys_role_menu` VALUES ('1', '1259393565025505280');
INSERT INTO `sys_role_menu` VALUES ('1', '1259394035957764096');
INSERT INTO `sys_role_menu` VALUES ('1', '1261658051136589824');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '4');
INSERT INTO `sys_role_menu` VALUES ('2', '5');
INSERT INTO `sys_role_menu` VALUES ('2', '6');
INSERT INTO `sys_role_menu` VALUES ('2', '7');
INSERT INTO `sys_role_menu` VALUES ('2', '8');
INSERT INTO `sys_role_menu` VALUES ('2', '9');
INSERT INTO `sys_role_menu` VALUES ('2', '10');
INSERT INTO `sys_role_menu` VALUES ('2', '11');
INSERT INTO `sys_role_menu` VALUES ('2', '12');
INSERT INTO `sys_role_menu` VALUES ('2', '13');
INSERT INTO `sys_role_menu` VALUES ('2', '14');
INSERT INTO `sys_role_menu` VALUES ('2', '15');
INSERT INTO `sys_role_menu` VALUES ('2', '16');
INSERT INTO `sys_role_menu` VALUES ('2', '17');
INSERT INTO `sys_role_menu` VALUES ('2', '18');
INSERT INTO `sys_role_menu` VALUES ('2', '19');
INSERT INTO `sys_role_menu` VALUES ('2', '20');
INSERT INTO `sys_role_menu` VALUES ('2', '21');
INSERT INTO `sys_role_menu` VALUES ('2', '22');
INSERT INTO `sys_role_menu` VALUES ('2', '23');
INSERT INTO `sys_role_menu` VALUES ('2', '24');
INSERT INTO `sys_role_menu` VALUES ('2', '25');
INSERT INTO `sys_role_menu` VALUES ('2', '26');
INSERT INTO `sys_role_menu` VALUES ('2', '27');
INSERT INTO `sys_role_menu` VALUES ('2', '28');
INSERT INTO `sys_role_menu` VALUES ('2', '29');
INSERT INTO `sys_role_menu` VALUES ('2', '30');
INSERT INTO `sys_role_menu` VALUES ('2', '31');
INSERT INTO `sys_role_menu` VALUES ('2', '32');
INSERT INTO `sys_role_menu` VALUES ('2', '33');
INSERT INTO `sys_role_menu` VALUES ('2', '34');
INSERT INTO `sys_role_menu` VALUES ('2', '35');
INSERT INTO `sys_role_menu` VALUES ('2', '36');
INSERT INTO `sys_role_menu` VALUES ('2', '37');
INSERT INTO `sys_role_menu` VALUES ('2', '38');
INSERT INTO `sys_role_menu` VALUES ('2', '39');
INSERT INTO `sys_role_menu` VALUES ('2', '40');
INSERT INTO `sys_role_menu` VALUES ('2', '41');
INSERT INTO `sys_role_menu` VALUES ('2', '42');
INSERT INTO `sys_role_menu` VALUES ('2', '43');
INSERT INTO `sys_role_menu` VALUES ('2', '44');
INSERT INTO `sys_role_menu` VALUES ('2', '45');
INSERT INTO `sys_role_menu` VALUES ('2', '46');
INSERT INTO `sys_role_menu` VALUES ('2', '47');
INSERT INTO `sys_role_menu` VALUES ('2', '48');
INSERT INTO `sys_role_menu` VALUES ('2', '49');
INSERT INTO `sys_role_menu` VALUES ('2', '50');
INSERT INTO `sys_role_menu` VALUES ('2', '51');
INSERT INTO `sys_role_menu` VALUES ('2', '52');
INSERT INTO `sys_role_menu` VALUES ('2', '53');
INSERT INTO `sys_role_menu` VALUES ('2', '54');
INSERT INTO `sys_role_menu` VALUES ('2', '55');
INSERT INTO `sys_role_menu` VALUES ('2', '56');
INSERT INTO `sys_role_menu` VALUES ('2', '57');
INSERT INTO `sys_role_menu` VALUES ('2', '58');
INSERT INTO `sys_role_menu` VALUES ('2', '59');
INSERT INTO `sys_role_menu` VALUES ('2', '60');
INSERT INTO `sys_role_menu` VALUES ('2', '61');
INSERT INTO `sys_role_menu` VALUES ('2', '62');
INSERT INTO `sys_role_menu` VALUES ('2', '63');
INSERT INTO `sys_role_menu` VALUES ('2', '64');
INSERT INTO `sys_role_menu` VALUES ('2', '65');
INSERT INTO `sys_role_menu` VALUES ('2', '66');
INSERT INTO `sys_role_menu` VALUES ('2', '67');
INSERT INTO `sys_role_menu` VALUES ('2', '68');
INSERT INTO `sys_role_menu` VALUES ('2', '69');
INSERT INTO `sys_role_menu` VALUES ('2', '70');
INSERT INTO `sys_role_menu` VALUES ('2', '71');
INSERT INTO `sys_role_menu` VALUES ('2', '72');
INSERT INTO `sys_role_menu` VALUES ('2', '73');
INSERT INTO `sys_role_menu` VALUES ('2', '74');
INSERT INTO `sys_role_menu` VALUES ('2', '75');
INSERT INTO `sys_role_menu` VALUES ('2', '76');
INSERT INTO `sys_role_menu` VALUES ('2', '77');
INSERT INTO `sys_role_menu` VALUES ('2', '78');
INSERT INTO `sys_role_menu` VALUES ('2', '79');
INSERT INTO `sys_role_menu` VALUES ('2', '80');
INSERT INTO `sys_role_menu` VALUES ('2', '81');
INSERT INTO `sys_role_menu` VALUES ('2', '82');
INSERT INTO `sys_role_menu` VALUES ('2', '83');
INSERT INTO `sys_role_menu` VALUES ('2', '84');
INSERT INTO `sys_role_menu` VALUES ('2', '85');
INSERT INTO `sys_role_menu` VALUES ('2', '86');
INSERT INTO `sys_role_menu` VALUES ('2', '87');
INSERT INTO `sys_role_menu` VALUES ('2', '88');
INSERT INTO `sys_role_menu` VALUES ('2', '89');
INSERT INTO `sys_role_menu` VALUES ('2', '90');
INSERT INTO `sys_role_menu` VALUES ('3', '1');
INSERT INTO `sys_role_menu` VALUES ('3', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '3');
INSERT INTO `sys_role_menu` VALUES ('3', '4');
INSERT INTO `sys_role_menu` VALUES ('3', '5');
INSERT INTO `sys_role_menu` VALUES ('3', '6');
INSERT INTO `sys_role_menu` VALUES ('3', '7');
INSERT INTO `sys_role_menu` VALUES ('3', '8');
INSERT INTO `sys_role_menu` VALUES ('3', '9');
INSERT INTO `sys_role_menu` VALUES ('3', '10');
INSERT INTO `sys_role_menu` VALUES ('3', '11');
INSERT INTO `sys_role_menu` VALUES ('3', '12');
INSERT INTO `sys_role_menu` VALUES ('3', '13');
INSERT INTO `sys_role_menu` VALUES ('3', '14');
INSERT INTO `sys_role_menu` VALUES ('3', '15');
INSERT INTO `sys_role_menu` VALUES ('3', '16');
INSERT INTO `sys_role_menu` VALUES ('3', '17');
INSERT INTO `sys_role_menu` VALUES ('3', '18');
INSERT INTO `sys_role_menu` VALUES ('3', '19');
INSERT INTO `sys_role_menu` VALUES ('3', '20');
INSERT INTO `sys_role_menu` VALUES ('3', '21');
INSERT INTO `sys_role_menu` VALUES ('3', '22');
INSERT INTO `sys_role_menu` VALUES ('3', '23');
INSERT INTO `sys_role_menu` VALUES ('3', '24');
INSERT INTO `sys_role_menu` VALUES ('3', '25');
INSERT INTO `sys_role_menu` VALUES ('3', '26');
INSERT INTO `sys_role_menu` VALUES ('3', '27');
INSERT INTO `sys_role_menu` VALUES ('3', '28');
INSERT INTO `sys_role_menu` VALUES ('3', '29');
INSERT INTO `sys_role_menu` VALUES ('3', '30');
INSERT INTO `sys_role_menu` VALUES ('3', '31');
INSERT INTO `sys_role_menu` VALUES ('3', '32');
INSERT INTO `sys_role_menu` VALUES ('3', '33');
INSERT INTO `sys_role_menu` VALUES ('3', '34');
INSERT INTO `sys_role_menu` VALUES ('3', '35');
INSERT INTO `sys_role_menu` VALUES ('3', '36');
INSERT INTO `sys_role_menu` VALUES ('3', '37');
INSERT INTO `sys_role_menu` VALUES ('3', '38');
INSERT INTO `sys_role_menu` VALUES ('3', '39');
INSERT INTO `sys_role_menu` VALUES ('3', '40');
INSERT INTO `sys_role_menu` VALUES ('3', '41');
INSERT INTO `sys_role_menu` VALUES ('3', '42');
INSERT INTO `sys_role_menu` VALUES ('3', '43');
INSERT INTO `sys_role_menu` VALUES ('3', '44');
INSERT INTO `sys_role_menu` VALUES ('3', '45');
INSERT INTO `sys_role_menu` VALUES ('3', '46');
INSERT INTO `sys_role_menu` VALUES ('3', '47');
INSERT INTO `sys_role_menu` VALUES ('3', '48');
INSERT INTO `sys_role_menu` VALUES ('3', '49');
INSERT INTO `sys_role_menu` VALUES ('3', '50');
INSERT INTO `sys_role_menu` VALUES ('3', '51');
INSERT INTO `sys_role_menu` VALUES ('3', '52');
INSERT INTO `sys_role_menu` VALUES ('3', '53');
INSERT INTO `sys_role_menu` VALUES ('3', '54');
INSERT INTO `sys_role_menu` VALUES ('3', '55');
INSERT INTO `sys_role_menu` VALUES ('3', '56');
INSERT INTO `sys_role_menu` VALUES ('3', '57');
INSERT INTO `sys_role_menu` VALUES ('3', '58');
INSERT INTO `sys_role_menu` VALUES ('3', '59');
INSERT INTO `sys_role_menu` VALUES ('3', '60');
INSERT INTO `sys_role_menu` VALUES ('3', '61');
INSERT INTO `sys_role_menu` VALUES ('3', '62');
INSERT INTO `sys_role_menu` VALUES ('3', '63');
INSERT INTO `sys_role_menu` VALUES ('3', '64');
INSERT INTO `sys_role_menu` VALUES ('3', '65');
INSERT INTO `sys_role_menu` VALUES ('3', '66');
INSERT INTO `sys_role_menu` VALUES ('3', '67');
INSERT INTO `sys_role_menu` VALUES ('3', '68');
INSERT INTO `sys_role_menu` VALUES ('3', '69');
INSERT INTO `sys_role_menu` VALUES ('3', '70');
INSERT INTO `sys_role_menu` VALUES ('3', '71');
INSERT INTO `sys_role_menu` VALUES ('3', '72');
INSERT INTO `sys_role_menu` VALUES ('3', '73');
INSERT INTO `sys_role_menu` VALUES ('3', '74');
INSERT INTO `sys_role_menu` VALUES ('3', '75');
INSERT INTO `sys_role_menu` VALUES ('3', '76');
INSERT INTO `sys_role_menu` VALUES ('3', '77');
INSERT INTO `sys_role_menu` VALUES ('3', '78');
INSERT INTO `sys_role_menu` VALUES ('3', '79');
INSERT INTO `sys_role_menu` VALUES ('3', '80');
INSERT INTO `sys_role_menu` VALUES ('3', '81');
INSERT INTO `sys_role_menu` VALUES ('3', '82');
INSERT INTO `sys_role_menu` VALUES ('3', '83');
INSERT INTO `sys_role_menu` VALUES ('3', '84');
INSERT INTO `sys_role_menu` VALUES ('3', '85');
INSERT INTO `sys_role_menu` VALUES ('3', '86');
INSERT INTO `sys_role_menu` VALUES ('3', '87');
INSERT INTO `sys_role_menu` VALUES ('3', '88');
INSERT INTO `sys_role_menu` VALUES ('3', '89');
INSERT INTO `sys_role_menu` VALUES ('3', '90');

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `company_name` varchar(200) DEFAULT NULL COMMENT '租户名称(公司名称)',
  `login_name` varchar(100) NOT NULL COMMENT '租户管理登录用户名(为超级管理员)',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `user_name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `qq_num` varchar(50) DEFAULT NULL COMMENT 'qq号',
  `wx_num` varchar(50) DEFAULT NULL COMMENT '微信号',
  `beg_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `status` tinyint(4) unsigned DEFAULT '0' COMMENT '状态0 启用 1 禁用',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户管理';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
INSERT INTO `sys_tenant` VALUES ('1', '南昌有限公司', 'myadmin', '12345', '11', null, null, null, null, null, '2020-05-27 00:17:52', '2020-05-26 00:17:55', '0', '1', '2020-05-26 00:17:24', '1', '2020-05-26 00:17:17', '', '0', '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL COMMENT '编号',
  `office_id` bigint(20) unsigned NOT NULL COMMENT '归属部门',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型0：管理员 1:教师 2:学生 ',
  `photo` varchar(300) DEFAULT NULL COMMENT '用户头像',
  `status` tinyint(4) unsigned DEFAULT '0' COMMENT '状态0 启用 1 禁用',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `create_by` bigint(20) unsigned NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) unsigned NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
  `wx_openid` varchar(32) DEFAULT '' COMMENT '微信id',
  `qq_openid` varchar(32) DEFAULT '' COMMENT 'qq id',
  `tenant_id` int(11) DEFAULT '0' COMMENT '租户id',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_login_name` (`login_name`),
  KEY `sys_user_update_date` (`update_date`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2', 'myadmin', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0011', '全球CEO', '2929783435@qq.com', '18210584253', '18210584253', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', null, null, '1', '2013-05-27 08:00:00', '1', '2020-05-25 17:38:41', ' ', '0', '', '', '0');
INSERT INTO `sys_user` VALUES ('12', '2', 'Aijm_99', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0012', '江西南昌领导0000', '2929783435@qq.com', '18210584253', '18210584253', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', null, null, '1', '2013-05-27 08:00:00', '1', '2020-05-27 00:10:06', ' ', '0', '', '', '0');
INSERT INTO `sys_user` VALUES ('13', '2', '2929783435@qq.com', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0013', '江西抚州', '2929783435@qq.com', '18210584253', '18210584253', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', ' ', '0', '', '', '0');
INSERT INTO `sys_user` VALUES ('124', '1', 'aijiamin1', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0001', '系统管理员', '2929783435@qq.com', '18210684253', '18210584253', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '最高管理员', '0', '', '', '0');
INSERT INTO `sys_user` VALUES ('1182672899547467776', '2', 'admin', '$2a$10$k9tBdw0GHbLtToX2Apheq.bR/D2WYWb5Rq0oPJt1Hn2axpsA.CI9O', '0001', '系统管理员', '2929783435@qq.com', '18210684254', '18210584253', '2', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', null, null, '1', '2019-10-11 23:02:47', '1', '2020-05-25 17:38:23', '最高管理员222', '0', '', '', '0');
INSERT INTO `sys_user` VALUES ('1196764259711848448', '2', 'syd_admin', '$2a$10$tqvPozBCqQ0Yh6impeHEceGLqtrTIWQO1IYjwNXks0IN5UgZgFUsK', '0002', '管理员', '2929783435@qq.com', '18210584253', '18210584253', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0', null, null, '1', '2019-11-19 20:16:49', '1', '2020-05-10 21:52:04', '', '0', '', '', '0');
INSERT INTO `sys_user` VALUES ('1265305717968080896', '2', 'Aijm_99', '$2a$10$IWzk5HPpoOxc4n63e9OB/OgkXrcQGkj50.Z1Tndv0KwrCbJNCEl7m', null, 'Aijm', null, null, null, '1', null, '0', null, null, '1', '2020-05-26 23:36:06', '1', '2020-05-27 00:09:06', '', '0', '', '', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户编号',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2');
INSERT INTO `sys_user_role` VALUES ('4', '3');
INSERT INTO `sys_user_role` VALUES ('5', '4');
INSERT INTO `sys_user_role` VALUES ('8', '2');
INSERT INTO `sys_user_role` VALUES ('9', '1');
INSERT INTO `sys_user_role` VALUES ('10', '2');
INSERT INTO `sys_user_role` VALUES ('11', '3');
INSERT INTO `sys_user_role` VALUES ('12', '1256634632846315520');
INSERT INTO `sys_user_role` VALUES ('13', '5');
INSERT INTO `sys_user_role` VALUES ('14', '6');
INSERT INTO `sys_user_role` VALUES ('1182672899547467776', '1');
INSERT INTO `sys_user_role` VALUES ('1196764259711848448', '2');
INSERT INTO `sys_user_role` VALUES ('1196764259711848448', '1256634632846315520');
INSERT INTO `sys_user_role` VALUES ('1265305717968080896', '1');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
