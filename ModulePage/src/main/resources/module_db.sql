-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: module_db
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,1,'A CI/CD module teaches the automation of software development workflows using Continuous Integration (CI) to merge and test code regularly, and Continuous Deployment (CD) to deliver updates efficiently and reliably.','Continuous Integration (CI) and Continuous Deployment (CD)'),(2,1,'An embedded systems module covers designing and programming specialized hardware-software systems, focusing on microcontrollers, sensors, real-time operations, and low-level programming.','Embedded Systems'),(3,1,'A machine learning with Python module focuses on building predictive models using Python libraries, covering data preprocessing, algorithms, and tools like NumPy, pandas, and scikit-learn.\r \r \r \r \r \r \r ','Machine Learning with Python'),(4,2,'A financial accounting module teaches recording, summarizing, and reporting financial transactions to prepare accurate financial statements.','Financial Accounting'),(5,2,'A cost accounting module focuses on analyzing and managing costs to improve efficiency and support decision-making in business operations.','Cost Accounting'),(6,2,'A taxation module covers tax laws, compliance, and planning to prepare and manage individual and business tax obligations.','Taxation'),(7,3,'A module that explores the chemical processes and molecular structures in living organisms.','Biochemistry'),(8,3,'A module that examines the effects, mechanisms, and uses of drugs in biological systems.','Pharmacology'),(9,3,'A module focused on the structure, function, and manipulation of genes and cells.','Molecular Biology'),(10,4,'Focuses on the principles and framework of a country\'s constitution, exploring the distribution of government powers, individual rights, and the interpretation of laws within a constitutional context.','Constitutional Law'),(11,4,'Examines the definition of criminal offenses, the legal processes involved in prosecution and defense, and the system of punishments designed to maintain order and justice in society.','Criminal Law'),(12,4,'Covers the creation, interpretation, and enforcement of legal agreements between parties, including topics like offer, acceptance, breach, and remedies.','Contracts'),(13,5,'Focuses on designing and analyzing buildings, bridges, and other structures to ensure they are safe, stable, and can withstand various forces.','Structural Engineering'),(14,5,'Involves planning, designing, and maintaining transportation systems, including roads, highways, and public transit, to ensure safe and efficient movement of people and goods.','Transportation Engineering'),(15,5,'Addresses the design and implementation of systems to protect the environment, including water treatment, waste management, and pollution control.','Environmental Engineering');
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-22 17:48:15
