-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: kkgames
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `comment_time` time DEFAULT NULL,
  `comment_date` date DEFAULT NULL,
  `comment_content` varchar(255) NOT NULL,
  `game_id` bigint DEFAULT NULL,
  `news_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdnsshxpxsyim4eglkpc9je1ol` (`game_id`),
  KEY `FKnxm8x9npdhuwxv2x2wxsghm17` (`news_id`),
  CONSTRAINT `FKdnsshxpxsyim4eglkpc9je1ol` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `FKnxm8x9npdhuwxv2x2wxsghm17` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'admin','12:12:00','2022-01-01','Comment1 content',1,NULL),(2,'test','12:12:01','2022-01-01','Comment2 content',NULL,1),(3,'admin','12:12:02','2022-01-01','Comment3 content',1,NULL),(4,'user','12:12:03','2022-01-01','Comment4 content',NULL,1),(5,'test','12:12:04','2022-01-01','Comment5 content',2,NULL),(6,'user','12:12:05','2022-01-01','Comment6 content',NULL,2);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','init','SQL','V1__init.sql',899168504,'kkgames','2022-12-20 14:51:45',69,1),(2,'2','insert-data','SQL','V2__insert-data.sql',1040392450,'kkgames','2022-12-20 14:51:45',5,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `game_title` varchar(255) DEFAULT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `review_time` time DEFAULT NULL,
  `review_date` date DEFAULT NULL,
  `review_author` varchar(255) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'Game 1 Title','Game 1 description','12:10:01','2022-01-02','admin',50,'default.jpg'),(2,'Medieval Dynasty','<b>Medieval Dynasty</b> to mieszanka symulatorów życia z serii Dynasty z RPG oraz survivalem od polskiego studia Render Cube. Zaczynamy jako skromny łowca w czasach średniowiecza starający się przetrwać w dzikiej głuszy, by z czasem założyć własne miasto. Medieval Dynasty to intrygująca gra z pogranicza RPG akcji i symulatora życia z elementami survivalu oraz strategii, stworzona przez polskie studio Render Cube. Jest to druga produkcja łódzkiego zespołu i zgoła odmienna od jego debiutanckiego projektu: wyścigów gokartów Monster League z 2018 roku. Tym razem Polacy stworzyli realistyczną grę osadzoną w średniowieczu, trochę przypominającą czeskie Kingdom Come: Deliverance. Tytuł jest też kolejną odsłoną cyklu Dynasty firmy Toplitz Productions, aczkolwiek wyraźnie odmienną od swoich poprzedników.<br><br><b>Fabuła</b><br>Głównym bohaterem jest osiemnastoletni chłop, który po śmierci rodziców opuszcza rodzinny dom, aby znaleźć pracę i wejść w dorosłość. Protagonista liczy na pomoc doświadczonego wuja, niestety okazuje się, że krewny nie żyje już od jakiegoś czasu. Dzięki pomocy lokalnego kasztelana młodzieniec otrzymuje kawałek ziemi i postanawia sam zadbać o swoją przyszłość, mając ambitny plan założenia własnej osady i zdobycia renomy w okolicy. Przy okazji pozna też tajemniczą przeszłość swojego wuja.<br><p style=\"text-align:right;\"><i>Źródło: https://www.gry-online.pl/gry/medieval-dynasty/z05b63</i></p>','12:10:02','2022-01-03','admin',65,'medieval.jpg');
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `news_title` varchar(255) DEFAULT NULL,
  `description` varchar(10000) DEFAULT NULL,
  `news_time` time DEFAULT NULL,
  `news_date` date DEFAULT NULL,
  `news_author` varchar(255) DEFAULT NULL,
  `news_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'News 1 Title','News 1 description','12:10:02','2022-01-01','admin','default.jpg'),(2,'News 2 Title','News 2 description','12:10:03','2022-01-03','test','default.jpg');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(60) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(80) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `account_creation_time` time DEFAULT NULL,
  `account_creation_date` date DEFAULT NULL,
  `user_roles` enum('ADMIN','USER') DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@admin.com','admin','$2a$10$OT07oJXozePV/DjtjBUPxOkCpWcYs9UcCnQUbEzb.6k8TC568qp7i',_binary '','10:12:12','2022-01-01','ADMIN','admin.jpg'),(2,'user@user.com','user','$2a$10$.xVn1nlHBVb0YKSkEmdMYOFQAAW1you99ElCrf4kZx35ghp9CT6CW',_binary '','10:12:12','2022-01-01','USER','user.jpg'),(3,'test@test.com','test','$2a$10$dfA2xjjoQNeQI3vxKEw1KeMeaEmFlfhPUnsXRTLpxGQZyEQZFt6vG',_binary '\0','10:12:12','2022-01-01','USER','testuser.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-20 15:52:11
