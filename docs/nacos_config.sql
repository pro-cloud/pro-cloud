/*
Navicat MySQL Data Transfer

Source Server         : 119.23.8.73
Source Server Version : 50726
Source Host           : 119.23.8.73:3306
Source Database       : nacos_config

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-12-11 18:47:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES ('4', 'cloud-auth-dev.yaml', 'pro-cloud', 0x7365727665723A0D0A20706F72743A2039303030, '67bc16c765a8795a774418cd64183b64', '2019-07-25 12:04:59', '2019-10-19 21:10:45', null, '115.171.202.146', '', 'd4885c2a-412b-4df6-8342-68c6c4a3dd4c', '这是一个测试案例', 'null', 'null', 'yaml', 0x6E756C6C);
INSERT INTO `config_info` VALUES ('5', 'common.yaml', 'pro-cloud', 0x737072696E673A0D0A202072656469733A0D0A20202020686F73743A203131392E32332E382E37330D0A2020202070617373776F72643A0D0A20202020706F72743A20383337390D0A2020202074696D656F75743A203130303030202320E8BF9EE68EA5E8B685E697B6E697B6E997B4EFBC88E6AFABE7A792EFBC890D0A2020202064617461626173653A20302023205265646973E9BB98E8AEA4E68385E586B5E4B88BE69C893136E4B8AAE58886E78987EFBC8CE8BF99E9878CE9858DE7BDAEE585B7E4BD93E4BDBFE794A8E79A84E58886E78987EFBC8CE9BB98E8AEA4E698AF30202020200D0A202064617461736F757263653A0D0A20202020747970653A20636F6D2E7A61787865722E68696B6172692E48696B61726944617461536F757263650D0A202020206472697665722D636C6173732D6E616D653A20636F6D2E6D7973716C2E636A2E6A6462632E4472697665720D0A2020202075726C3A206A6462633A6D7973716C3A2F2F3131392E32332E382E37333A333330362F70726F5F636C6F75643F636861726163746572456E636F64696E673D75746638267A65726F4461746554696D654265686176696F723D636F6E76657274546F4E756C6C2675736553534C3D66616C7365267573654A444243436F6D706C69616E7454696D657A6F6E6553686966743D74727565267573654C65676163794461746574696D65436F64653D66616C73652673657276657254696D657A6F6E653D474D54253242380D0A20202020757365726E616D653A20454E4328483135644A2B47377A5643744758705A6E6F433861773D3D290D0A2020202070617373776F72643A20454E4328526C34716D572F4832756B50547235706759774C795149706F47424336746A4F290D0A0D0A0D0A6A61737970743A0D0A2020656E63727970746F723A0D0A2020202070617373776F72643A20756F6A3230406430336A6420202020, 'c93406d6127e983479fe0ba1218aa722', '2019-07-25 12:09:00', '2019-12-11 18:47:31', null, '10.10.19.49', '', 'd4885c2a-412b-4df6-8342-68c6c4a3dd4c', 'null', 'null', 'null', 'null', 0x6E756C6C);
INSERT INTO `config_info` VALUES ('10', 'cloud-gateway-dev.yaml', 'pro-cloud', 0x7365727665723A0D0A2020706F72743A2039393939, '01e0cfa6deb6df14a899dbc9baa0ae38', '2019-07-31 23:43:00', '2019-10-19 21:11:34', null, '115.171.202.146', '', 'd4885c2a-412b-4df6-8342-68c6c4a3dd4c', 'null', 'null', 'null', 'yaml', 0x6E756C6C);
INSERT INTO `config_info` VALUES ('16', 'sentinel', 'pro-cloud', 0x5B0D0A202020207B0D0A2020202020202020227265736F75726365223A20222F757365722F757064617465222C0D0A2020202020202020226C696D6974417070223A202264656661756C74222C0D0A2020202020202020226772616465223A20312C0D0A202020202020202022636F756E74223A20352C0D0A2020202020202020227374726174656779223A20302C0D0A202020202020202022636F6E74726F6C4265686176696F72223A20302C0D0A202020202020202022636C75737465724D6F6465223A2066616C73650D0A202020207D0D0A5D, '7a72e39fc07fa038213f6f5c3c25fd7a', '2019-08-04 22:23:44', '2019-08-04 22:23:44', null, '192.168.1.103', '', 'd4885c2a-412b-4df6-8342-68c6c4a3dd4c', null, null, null, 'json', null);
INSERT INTO `config_info` VALUES ('18', 'cloud-admin-service-dev.yaml', 'pro-cloud', 0x61696A6D3A200D0A2020636C69656E745365637265743A20313131777777323232, '1565146df0244a516bd82c5c02e6576b', '2019-08-08 21:53:16', '2019-08-08 22:53:01', null, '192.168.1.103', '', 'd4885c2a-412b-4df6-8342-68c6c4a3dd4c', 'null', 'null', 'null', 'yaml', 0x6E756C6C);
INSERT INTO `config_info` VALUES ('29', 'crowd-admin-monitor-dev.yaml', 'pro-cloud', 0x2320E9858DE7BDAEE982AEE4BBB60D0A20206D61696C3A0D0A20202020686F73743A20736D74702E3136332E636F6D0D0A20202020706F72743A0D0A2020202070726F746F636F6C3A20736D74700D0A20202020757365726E616D653A206A7861696A6D3934403136332E636F6D0D0A2020202070617373776F72643A2061693132333435360D0A2020626F6F743A0D0A2020202061646D696E3A0D0A2020202020206E6F746966793A0D0A20202020202020206D61696C3A0D0A2020202020202020202066726F6D3A206A7861696A6D3934403136332E636F6D0D0A20202020202020202020746F3A20313939313132313564403136332E636F6D0D0A20202020202020202020656E61626C65643A2074727565, 'a8fd411f7023d63874f1d8c92e181769', '2019-08-14 17:42:20', '2019-08-14 17:43:07', null, '10.10.19.49', '', 'd4885c2a-412b-4df6-8342-68c6c4a3dd4c', 'null', 'null', 'null', 'yaml', 0x6E756C6C);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES ('5', '42', 'common.yaml', 'pro-cloud', '', 0x737072696E673A0D0A202072656469733A0D0A20202020686F73743A203131392E32332E382E37330D0A2020202070617373776F72643A0D0A20202020706F72743A20383337390D0A2020202074696D656F75743A203130303030202320E8BF9EE68EA5E8B685E697B6E697B6E997B4EFBC88E6AFABE7A792EFBC890D0A2020202064617461626173653A20302023205265646973E9BB98E8AEA4E68385E586B5E4B88BE69C893136E4B8AAE58886E78987EFBC8CE8BF99E9878CE9858DE7BDAEE585B7E4BD93E4BDBFE794A8E79A84E58886E78987EFBC8CE9BB98E8AEA4E698AF30202020200D0A202064617461736F757263653A0D0A20202020747970653A20636F6D2E7A61787865722E68696B6172692E48696B61726944617461536F757263650D0A202020206472697665722D636C6173732D6E616D653A20636F6D2E6D7973716C2E636A2E6A6462632E4472697665720D0A2020202075726C3A206A6462633A6D7973716C3A2F2F3131392E32332E382E37333A333330362F636C6F75645F63726D3F636861726163746572456E636F64696E673D75746638267A65726F4461746554696D654265686176696F723D636F6E76657274546F4E756C6C2675736553534C3D66616C7365267573654A444243436F6D706C69616E7454696D657A6F6E6553686966743D74727565267573654C65676163794461746574696D65436F64653D66616C73652673657276657254696D657A6F6E653D474D54253242380D0A20202020757365726E616D653A20454E4328483135644A2B47377A5643744758705A6E6F433861773D3D290D0A2020202070617373776F72643A20454E4328526C34716D572F4832756B50547235706759774C795149706F47424336746A4F290D0A0D0A0D0A6A61737970743A0D0A2020656E63727970746F723A0D0A2020202070617373776F72643A20756F6A3230406430336A6420202020, '3eb8c265a90cf75df7e7a89e4bd5e1b0', '2010-05-05 00:00:00', '2019-12-11 18:47:31', null, '10.10.19.49', 'U', 'd4885c2a-412b-4df6-8342-68c6c4a3dd4c');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES ('1', '1', 'd4885c2a-412b-4df6-8342-68c6c4a3dd4c', 'pro', 'pro-cloud项目的命名空间', 'nacos', '1564026833853', '1564026833853');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', '1');
