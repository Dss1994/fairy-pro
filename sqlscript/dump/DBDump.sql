-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: fairy-pro
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fairy_base_role`
--

DROP TABLE IF EXISTS `fairy_base_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fairy_base_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(45) NOT NULL COMMENT '角色名称',
  `role_type` int(11) NOT NULL COMMENT '角色类型 0 表示最高管理员(任何操作) 1表示普通管理员(允许创建用户) 2 表示普通用户(不能创建用户查询日志信息)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_base_role`
--

LOCK TABLES `fairy_base_role` WRITE;
/*!40000 ALTER TABLE `fairy_base_role` DISABLE KEYS */;
INSERT INTO `fairy_base_role` VALUES (0,'超级管理',0,'2019-01-29 13:56:32'),(1,'管理员',1,'2019-01-30 11:31:06');
/*!40000 ALTER TABLE `fairy_base_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairy_base_route`
--

DROP TABLE IF EXISTS `fairy_base_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fairy_base_route` (
  `id` bigint(20) NOT NULL COMMENT '唯一ID',
  `target` varchar(45) NOT NULL COMMENT '目标的地址',
  `remarks` varchar(45) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态 0 表示正常 1表示禁止',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `route_type` int(11) NOT NULL DEFAULT '3' COMMENT '路由类型,默认为1  ,  4 表示未登入即可访问 ,1表示管理员权限,0表示超级管理员权限.3表示普通权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础的URL路由信息,用来做权限管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_base_route`
--

LOCK TABLES `fairy_base_route` WRITE;
/*!40000 ALTER TABLE `fairy_base_route` DISABLE KEYS */;
INSERT INTO `fairy_base_route` VALUES (0,'/api/user/login','用户登入接口',0,'2019-01-31 15:13:24',4),(1,'/api/user/logout','用户登出接口',0,'2019-01-31 15:13:24',4),(2,'/api/user/addUser','添加用户',0,'2019-01-31 15:13:24',1);
/*!40000 ALTER TABLE `fairy_base_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairy_base_session`
--

DROP TABLE IF EXISTS `fairy_base_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fairy_base_session` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '当前登入的用户ID',
  `session_code` varchar(64) NOT NULL COMMENT '生成64位的SessionCode防止暴力破解',
  `ip_addr` varchar(45) NOT NULL COMMENT '第一次登入的IP地址',
  `equipment` int(11) NOT NULL COMMENT '登入的设备类型',
  `last_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登入时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最早登入时间',
  PRIMARY KEY (`id`),
  KEY `USER_FK_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登入的session会话';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_base_session`
--

LOCK TABLES `fairy_base_session` WRITE;
/*!40000 ALTER TABLE `fairy_base_session` DISABLE KEYS */;
INSERT INTO `fairy_base_session` VALUES (210987740433481728,0,'5e7187526bb84317913fdb781cce04ae323debabfe84469ebb873af1cc18113e','127.0.0.1',0,'2019-01-31 05:09:57','2019-01-31 05:09:57');
/*!40000 ALTER TABLE `fairy_base_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairy_base_user`
--

DROP TABLE IF EXISTS `fairy_base_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fairy_base_user` (
  `id` bigint(20) NOT NULL COMMENT '唯一ID',
  `login_name` varchar(45) NOT NULL COMMENT '系统登入名称',
  `real_name` varchar(45) NOT NULL COMMENT '用户的真实名称',
  `identity_card` varchar(45) NOT NULL COMMENT '用户所属国家的身份证号码',
  `password` varchar(32) NOT NULL COMMENT '32位的Hash的密码',
  `email` varchar(45) NOT NULL COMMENT '当前用户注册的邮箱地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `online_time` int(11) NOT NULL DEFAULT '30' COMMENT '在线时间,单位分钟,默认30分钟',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础人员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_base_user`
--

LOCK TABLES `fairy_base_user` WRITE;
/*!40000 ALTER TABLE `fairy_base_user` DISABLE KEYS */;
INSERT INTO `fairy_base_user` VALUES (0,'admin','超级管理员','429005199609080071','+/?/^\'^||?,,/+!//_?/^+?~+~/,.-`=','zhangjin0908@hotmail.com','2019-01-29 13:55:20',30);
/*!40000 ALTER TABLE `fairy_base_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairy_grant_role`
--

DROP TABLE IF EXISTS `fairy_grant_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fairy_grant_role` (
  `id` bigint(20) NOT NULL COMMENT '唯一ID',
  `user_id` bigint(20) NOT NULL COMMENT '人员ID信息',
  `role_id` bigint(20) NOT NULL COMMENT '关联的角色信息',
  `authorize` bigint(20) NOT NULL COMMENT '授权人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `USER_FK_idx` (`user_id`),
  KEY `ROLE_FK_idx` (`role_id`),
  KEY `AUTHORIZE_FK_idx` (`authorize`),
  CONSTRAINT `AUTHORIZE_FK` FOREIGN KEY (`authorize`) REFERENCES `fairy_base_user` (`id`),
  CONSTRAINT `ROLE_FK` FOREIGN KEY (`role_id`) REFERENCES `fairy_base_role` (`id`),
  CONSTRAINT `USER_FK` FOREIGN KEY (`user_id`) REFERENCES `fairy_base_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员与角色之间的关联信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_grant_role`
--

LOCK TABLES `fairy_grant_role` WRITE;
/*!40000 ALTER TABLE `fairy_grant_role` DISABLE KEYS */;
INSERT INTO `fairy_grant_role` VALUES (0,0,0,0,'2019-01-31 11:48:24');
/*!40000 ALTER TABLE `fairy_grant_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairy_grant_route`
--

DROP TABLE IF EXISTS `fairy_grant_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fairy_grant_route` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `route_id` bigint(20) NOT NULL COMMENT '路由ID',
  `authorize` bigint(20) NOT NULL COMMENT '授权人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ROUTE_FK_idx` (`route_id`),
  KEY `ROUTE_USER_FK_idx` (`authorize`),
  KEY `ROLE_ROUTE_idx` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权路由';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_grant_route`
--

LOCK TABLES `fairy_grant_route` WRITE;
/*!40000 ALTER TABLE `fairy_grant_route` DISABLE KEYS */;
/*!40000 ALTER TABLE `fairy_grant_route` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-13 10:43:08
