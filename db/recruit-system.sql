-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: recruit_system
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adjust_stu_info`
--

DROP TABLE IF EXISTS `adjust_stu_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adjust_stu_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) NOT NULL,
  `stu_id` char(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `adjust_dept` int NOT NULL,
  `major` varchar(50) NOT NULL,
  `college` int NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `adjust_stu_info_openid_uindex` (`openid`),
  UNIQUE KEY `adjust_stu_info_stu_id_uindex` (`stu_id`),
  UNIQUE KEY `adjust_stu_info_id_uindex` (`id`),
  KEY `adjust_stu_info_college_index` (`college`),
  KEY `adjust_stu_info_adjust_dept_index` (`adjust_dept`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='调剂学生名单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_list`
--

DROP TABLE IF EXISTS `admin_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_list_username_uindex` (`username`),
  UNIQUE KEY `admin_list_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stu_id` char(10) NOT NULL,
  `mark` int NOT NULL,
  `comment` text,
  `interviewer` char(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对学生的评价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `interviewer_list`
--

DROP TABLE IF EXISTS `interviewer_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interviewer_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `dept` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `interviewer_list_username_uindex` (`username`),
  UNIQUE KEY `interviewer_list_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试官列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `poccess`
--

DROP TABLE IF EXISTS `poccess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poccess` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `poccess_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='面试整体进度';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stu_info`
--

DROP TABLE IF EXISTS `stu_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stu_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) NOT NULL,
  `stu_id` char(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` int NOT NULL,
  `profile` text,
  `skill` text,
  `has_join` text,
  `first_dept` int NOT NULL,
  `second_dept` int DEFAULT NULL,
  `major` varchar(50) NOT NULL,
  `college` int NOT NULL,
  `clazz` varchar(50) NOT NULL,
  `where_find` varchar(40) DEFAULT NULL,
  `status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `table_name_openid_uindex` (`openid`),
  UNIQUE KEY `table_name_stu_id_uindex` (`stu_id`),
  UNIQUE KEY `table_name_id_uindex` (`id`),
  KEY `table_name_college_index` (`college`),
  KEY `table_name_first_dept_index` (`first_dept`),
  KEY `table_name_status_index` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生信息';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-07 18:39:08
