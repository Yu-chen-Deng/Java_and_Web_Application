-- MySQL dump 10.13  Distrib 8.4.3, for Win64 (x86_64)
--
-- Host: localhost    Database: swubbs
-- ------------------------------------------------------
-- Server version	8.4.3

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
-- Table structure for table `adminuser_info`
--

DROP TABLE IF EXISTS `adminuser_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adminuser_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '绠＄悊鍛樼紪鍙?,
  `nickname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '鐢ㄦ埛鏄电О',
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '鐢ㄦ埛璐﹀彿',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '瀵嗙爜',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='绠＄悊鍛樿〃';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminuser_info`
--

LOCK TABLES `adminuser_info` WRITE;
/*!40000 ALTER TABLE `adminuser_info` DISABLE KEYS */;
INSERT INTO `adminuser_info` VALUES (1,'Yuchen','123','123');
/*!40000 ALTER TABLE `adminuser_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum_info`
--

DROP TABLE IF EXISTS `forum_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forum_info` (
  `fid` int NOT NULL AUTO_INCREMENT COMMENT '甯栧瓙缂栧彿',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '鏍囬',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '甯栧瓙鍐呭',
  `create_time` datetime NOT NULL COMMENT '鍙戝竷鏃堕棿',
  `up` int DEFAULT '0' COMMENT '鐐硅禐鏁伴噺',
  `reply_count` int DEFAULT '0' COMMENT '鍥炲鏁伴噺',
  `user_id` int NOT NULL COMMENT '妤间富缂栧彿',
  `selected` int DEFAULT NULL COMMENT '鏄惁涓虹簿閫?,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='甯栧瓙琛?;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_info`
--

LOCK TABLES `forum_info` WRITE;
/*!40000 ALTER TABLE `forum_info` DISABLE KEYS */;
INSERT INTO `forum_info` VALUES (21,'杩欐槸涓€涓祴璇曡创 from Yuchen~','杩欐槸涓€涓祴璇曡创 from Yuchen','2024-11-14 19:55:56',0,0,10,NULL),(22,'杩欐槸涓€涓祴璇曡创 from Yuchen~','杩欐槸涓€涓祴璇曡创 from Yuchen','2024-11-14 19:56:18',5,1,10,NULL);
/*!40000 ALTER TABLE `forum_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply_info`
--

DROP TABLE IF EXISTS `reply_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply_info` (
  `reply_id` int NOT NULL AUTO_INCREMENT COMMENT '鍥炲笘缂栧彿',
  `reply_content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '鍥炲鍐呭',
  `reply_time` datetime NOT NULL COMMENT '鍥炲鏃堕棿',
  `up` int DEFAULT '0' COMMENT '鐐硅禐鏁伴噺',
  `user_id` int NOT NULL COMMENT '鐢ㄦ埛缂栧彿',
  `fid` int NOT NULL COMMENT '甯栧瓙缂栧彿',
  PRIMARY KEY (`reply_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='鍥炲琛?;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply_info`
--

LOCK TABLES `reply_info` WRITE;
/*!40000 ALTER TABLE `reply_info` DISABLE KEYS */;
INSERT INTO `reply_info` VALUES (1,'浣犲ソ鍝堝搱鍝堝搱鍝?,'2024-01-04 17:31:38',0,3,9),(2,'123123','2024-01-04 17:43:39',0,4,11),(3,'123123','2024-01-04 17:43:49',0,4,16),(4,'test reply','2024-11-16 12:10:07',2,10,22);
/*!40000 ALTER TABLE `reply_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '鐢ㄦ埛缂栧彿',
  `nickname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '鐢ㄦ埛鏄电О',
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '鐢ㄦ埛璐﹀彿',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '瀵嗙爜',
  `birthday` date DEFAULT NULL COMMENT '鐢熸棩',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '鎬у埆',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '鐢佃瘽鍙风爜',
  `email` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '娉ㄥ唽閭',
  `signature` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '涓汉绛惧悕',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='鐢ㄦ埛琛?;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (10,'Yuchen','123456','123456','2005-07-14','鐢?,'12345678','fmmsmw','testSignature');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-19  1:34:17
