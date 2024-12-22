-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: post_db
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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `post_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbqnvawwwv4gtlctsi3o7vs131` (`post_id`),
  CONSTRAINT `FKbqnvawwwv4gtlctsi3o7vs131` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'This is a great post!','sour4321',1),(2,'Paul lennon is sexy fr','kingdarius',1),(3,'Water treatment  removes contaminants from water to make it safe for consumption and use, includes filtration, chemical treatment, and disinfection to eliminate harmful bacteria, pollutants, and toxins.','ThePlank',25),(4,'Consideration refers to something of value that is exchanged between parties to a contract. For a contract to be legally binding, each party must provide consideration as a promise to perform or refrain from performing an action.','Monksey',12),(5,'None! Choon Xiang the goat','Sour4321',3),(6,'For 50 Hz you need prescaler 1599, arr 999','John1234',4),(7,'1)A 2)B 3)D 4)D 5)B 6)A 7)C','John1234',5),(8,'use demorgans theorem','Sour4321',6),(9,'Thanks!','DreamSMP',7),(10,'You forgot to add that the entire process is tightly regulated to ensure accurate DNA replication before cell division. ','Herro3',17);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `post_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKowd6f4s7x9f3w50pvlo6x3b41` (`post_id`),
  CONSTRAINT `FKowd6f4s7x9f3w50pvlo6x3b41` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,'kingdarius',1),(2,'kingdarius',3),(4,'sour4321',1);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `module_id` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'CICD is bussin in me frfr',1,'W CICD',2),(2,'1)A 2)A 3)C 4)D 5)B 6)C 7)C',1,'CICD Test answer leak ',1),(3,'Here is the list of top 3 nominated class rep for Embedded Systems: 1. Yi Zheng 2. Tamer Zraiq 3. Davidj7705 ',2,'Class Rep nomination for Embedded System',1),(4,'How do I make the servo motor work?',2,'Servo motor values',2),(5,'Whats the key answers for the quiz?',3,'Quiz 2 answers',2),(6,'Does anyone have the answers for question 1 2023 final exam?',3,'2023 final exam',1),(7,'Assets = Liabilities + Equity',4,'Formula for Q3',3),(8,'An income statement is a financial report that shows a company\'s revenues, expenses, and profits over a specific period. It highlights how much money the company earned (revenue) and spent (expenses), ultimately revealing the net profit or loss. This statement is essential for assessing a company\'s financial performance.\n\n\n\n\n\n\n',4,'Concept of income statement',4),(9,'Total Cost of Production = (Total Fixed Cost + Total Variable Cost) x Number of Units.',5,'2022 Final exam Q2  Formula of total cost ',4),(10,'Whats the answer for Q4 part ii in the transactions worksheet?',5,'Transactions',3),(11,'PBT = Operating Profit - Interest Expenses',6,'Formula for Tax profit',3),(12,'Taxation is the process by which governments collect financial contributions (taxes) from individuals, businesses, and other entities to fund public services and infrastructure. It involves various types of taxes, such as income, sales, property, and corporate taxes, each with specific rules and rates based on income, transactions, or assets. Taxation plays a key role in redistributing wealth and ensuring the functioning of public services and government operations.',6,'Taxation Q1 answer',4),(13,'ATP (adenosine triphosphate) is the primary energy carrier in cells. It stores and transfers energy necessary for various cellular processes, including muscle contraction, protein synthesis, and active transport across cell membranes. ',7,'Whats ATP?',5),(14,'Enzymes are biological catalysts that speed up chemical reactions in the body without being consumed in the process. They lower the activation energy required for a reaction to occur, allowing reactions to happen more efficiently and at a faster rate.',7,'What is the function of enzymes in biochemical reactions?',5),(15,'agonists are drugs or molecules that bind to a receptor and activate it, triggering a biological response.antagonists are drugs that bind to a receptor but do not activate it. Instead, they block or inhibit the action of agonists or endogenous substances.',8,'What is the difference between agonists and antagonists in pharmacology?',6),(16,'The half-life (t₁/₂) of a drug is the time it takes for the concentration of the drug in the bloodstream to decrease by half. t=0.693/k',8,'May 2021 Q5',5),(17,'DNA replication starts at the origin of replication, where helicase unwinds the DNA. Primase then adds RNA primers, and DNA polymerase begins synthesizing the new DNA strand, using the primer as a starting point. The process creates two strands: one is synthesized continuously (leading strand), and the other in fragments (lagging strand).',9,'Winter repeat 2022 Q2',5),(18,'PCR is a technique used to make many copies of a specific DNA segment. It involves heating the DNA to separate the strands, adding primers to target the DNA, and using DNA polymerase to build new strands, allowing for DNA analysis and cloning.',9,'Winter 2022 Q4 answer',6),(19,'Judicial review is the power of courts to examine and determine the constitutionality of legislative acts and executive actions. If a law or action is found to be unconstitutional, the court can invalidate it, ensuring that government actions comply with the constitution.',10,'Judical review summary',7),(20,' felony is a more serious crime, typically punishable by imprisonment for over a year or even death, such as murder or robbery. A misdemeanor is a less severe offense, often punishable by less than a year in jail or fines, such as petty theft or simple assault.',11,'May 2023 Q1 answer',8),(21,'Does anyone have the answer for the consideration question in the winter paper?',12,'Consideration question',8),(22,'A structural load is the weight or force a structure must support, including permanent loads (like the building\'s weight), temporary loads (like people or furniture), and environmental loads (like wind or snow). Properly managing these loads ensures safety and stability.',13,'Structural Load question answer',9),(23,' A foundation transfers the weight of a building or structure to the ground, ensuring stability and preventing settling or tilting. It must be designed to support the structure\'s loads and withstand environmental factors like soil movement.',13,'Foundation summary',10),(24,'Traffic flow theory studies how vehicles move on roadways, focusing on the relationship between traffic density, speed, and flow. It helps engineers design roads, intersections, and signals to optimize traffic movement and reduce congestion.',14,'Autumn 2023 Q4 answer',9),(25,'How do I answer this question?',15,'Autumn \'23 Q1 ',10);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-22 17:48:38
