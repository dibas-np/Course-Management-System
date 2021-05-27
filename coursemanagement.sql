-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: coursemanagement
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_name` varchar(20) DEFAULT NULL,
  `admin_username` varchar(20) DEFAULT NULL,
  `admin_password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('Course Admin','admin','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(30) DEFAULT NULL,
  `status` varchar(10) DEFAULT 'True',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (120,'Computer Science','True'),(220,'Business Management','True'),(320,'Test','True');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor` (
  `instructor_id` int(11) DEFAULT NULL,
  `instructor_name` varchar(20) DEFAULT NULL,
  `instructor_email` varchar(25) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  KEY `module_id` (`module_id`),
  CONSTRAINT `instructor_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `module` (`module_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (1,'Subiran Shrestha','subiran@gmail.com',120),(2,'Ramesh Koirala','ramesh@gmail.com',10),(3,'Subiran Shrestha','subiran@gmail.com',20),(4,'Dipson Sharma','dipson@gmail.com',40),(6,'Sajan SHarma','sajan@gmail.com',60),(8,'Sonia Karki','sonia@gmail.com',80),(9,'Basanta Sharma','basanta@gmail.com',90),(5,'Hemanta Gautam','hemanta@gmail.com',100),(10,'Sandesh Sharma','sandesh@gmail.com',130),(7,'Hari Sharma','hari@gmail.com',140),(1,'Subiran Shrestha','subiran@gmail.com',30),(2,'Ramesh Koirala','ramesh@gmail.com',50),(3,'Subiran Shrestha','subiran@gmail.com',70),(5,'Hemanta Gautam','hemanta@gmail.com',150),(6,'Sajan SHarma','sajan@gmail.com',160),(7,'Hari Sharma','hari@gmail.com',170),(8,'Sonia Karki','sonia@gmail.com',180),(9,'Basanta Sharma','basanta@gmail.com',190),(10,'Sandesh Sharma','sandesh@gmail.com',200),(10,'Sandesh Sharma','sandesh@gmail.com',210),(9,'Basanta Sharma','basanta@gmail.com',220),(10,'Sandesh Sharma','sandesh@gmail.com',230),(2,'Ramesh Koirala','ramesh@gmail.com',240),(3,'Subiran Shrestha','subiran@gmail.com',250),(4,'Dipson Sharma','dipson@gmail.com',260),(5,'Hemanta Gautam','hemanta@gmail.com',270),(6,'Sajan SHarma','sajan@gmail.com',280),(7,'Hari Sharma','hari@gmail.com',290),(1,'Subiran Shrestha','subiran@gmail.com',300),(6,'Sajan SHarma','sajan@gmail.com',310),(4,'Dipson Sharma','dipson@gmail.com',320),(77,'Kamlesh','kamlesh@gmail.com',140);
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `module_id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `module_name` varchar(25) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`module_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `module_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (10,120,4,'Personal Development for ',2),(20,120,4,'Introduction to Programmi',3),(30,120,4,'Introduction to Web Techn',1),(40,120,4,'Computing Mathematics and',4),(50,120,4,'Introduction to Database ',2),(60,120,4,'Introduction to Programmi',6),(70,120,5,'Software Engineering',3),(80,120,5,'Object-Oriented Design & ',8),(90,120,5,'Software Project Manageme',9),(100,120,5,'System Analysis and Desig',5),(110,120,5,'Research and Development ',11),(120,120,5,'Introductory Computer Net',1),(130,120,6,'Big Data',10),(140,120,6,'Complex System',77),(150,120,6,'High Perfomance Computing',5),(160,120,6,'AI & Machine Learning',6),(170,220,4,'Academic English',7),(180,220,4,'Undergraduate study and r',8),(190,220,4,'Foundation Mathematics',9),(200,220,4,'Introduction to IT',10),(210,220,4,'Introduction to Business',10),(220,220,4,'Foundation Statistics',9),(230,220,5,'Global Business',10),(240,220,5,'Accounting & Finance',2),(250,220,5,'Principles of Marketing',3),(260,220,5,'Organizational Behaviour',4),(270,220,5,'Analytics for Business',5),(280,220,5,'Building sustainable glob',6),(290,220,6,'Internatinal Marketing',7),(300,220,6,'Strategic Management',1),(310,220,6,'Debating Globalisation',6),(320,220,6,'International Trade & Fin',4);
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) DEFAULT NULL,
  `student_name` varchar(25) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  `marks` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (3,'Rmi',130,60),(3,'Rmi',140,60),(3,'Rmi',150,60),(3,'Rmi',160,60),(5,'ram',140,0),(5,'ram',150,0),(7,'Shita',230,70),(7,'Shita',240,0),(7,'Shita',250,0),(7,'Shita',260,0),(7,'Shita',270,0),(7,'Shita',280,0),(8,'Shyam',170,50),(8,'Shyam',180,50),(8,'Shyam',190,50),(8,'Shyam',200,50),(8,'Shyam',210,50),(8,'Shyam',220,50),(2,'Rajesh Hamal',230,0),(2,'Rajesh Hamal',240,0),(2,'Rajesh Hamal',250,0),(2,'Rajesh Hamal',260,0),(2,'Rajesh Hamal',270,0),(2,'Rajesh Hamal',280,0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-14 19:23:24
