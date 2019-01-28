CREATE DATABASE  IF NOT EXISTS `fairy-pro` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fairy-pro`;
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
  `roleType` int(11) NOT NULL COMMENT '角色类型 0 表示最高管理员(任何操作) 1表示普通管理员(允许创建用户) 2 表示普通用户(不能创建用户查询日志信息)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_base_role`
--

LOCK TABLES `fairy_base_role` WRITE;
/*!40000 ALTER TABLE `fairy_base_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `fairy_base_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairy_base_session`
--

DROP TABLE IF EXISTS `fairy_base_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fairy_base_session` (
  `id` bigint(20) NOT NULL,
  `session_code` varchar(64) NOT NULL COMMENT '生成64位的SessionCode防止暴力破解',
  `ipAddr` varchar(45) NOT NULL COMMENT '第一次登入的IP地址',
  `equipment` int(11) NOT NULL COMMENT '登入的设备类型',
  `last_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登入时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最早登入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登入的session会话';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_base_session`
--

LOCK TABLES `fairy_base_session` WRITE;
/*!40000 ALTER TABLE `fairy_base_session` DISABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础人员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairy_base_user`
--

LOCK TABLES `fairy_base_user` WRITE;
/*!40000 ALTER TABLE `fairy_base_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `fairy_base_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairy_group_role`
--

DROP TABLE IF EXISTS `fairy_group_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fairy_group_role` (
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
-- Dumping data for table `fairy_group_role`
--

LOCK TABLES `fairy_group_role` WRITE;
/*!40000 ALTER TABLE `fairy_group_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `fairy_group_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-28 17:02:21