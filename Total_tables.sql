CREATE DATABASE  IF NOT EXISTS `railway` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `railway`;
-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: dbms_project
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.22.04.1

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
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'Verma Priyanshu','6261909184','verma@gmail.com','2003-05-06','Hostel no. 206'),(2,'Anas','8768868686','wildmak@gmail.com','2003-04-30','Singroli'),(3,'Rahul Srestha','8797136137','rsisrs@gmail.com','2002-01-25','Jharkhand'),(4,'Devashish','6375748888','deva@gmail.com','2002-07-08','U.P.');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerOrder`
--

DROP TABLE IF EXISTS `CustomerOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CustomerOrder` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL,
  `transactionID` int DEFAULT NULL,
  `modeOfPayment` varchar(255) DEFAULT NULL,
  `total` int DEFAULT NULL,
  `customerID` int DEFAULT NULL,
  `servedBy` int DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `customerID` (`customerID`),
  KEY `servedBy` (`servedBy`),
  CONSTRAINT `CustomerOrder_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `Customer` (`customerID`) ON DELETE CASCADE,
  CONSTRAINT `CustomerOrder_ibfk_2` FOREIGN KEY (`servedBy`) REFERENCES `Employee` (`empID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerOrder`
--

LOCK TABLES `CustomerOrder` WRITE;
/*!40000 ALTER TABLE `CustomerOrder` DISABLE KEYS */;
INSERT INTO `CustomerOrder` VALUES (2,'2022-11-09',99090,'SBI YONO',97500,1,2),(3,'2022-11-08',45466,'Cash',101300,3,1),(4,'2022-11-11',665656565,'UPI',105100,2,1),(5,'2022-11-11',45454,'Online banking',99000,1,1),(6,'2022-11-08',0,'Cash',48000,4,4);
/*!40000 ALTER TABLE `CustomerOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerOrderItem`
--

DROP TABLE IF EXISTS `CustomerOrderItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CustomerOrderItem` (
  `orderID` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `sellingPrice` int DEFAULT NULL,
  `productID` int NOT NULL,
  `additionalInfo` longtext,
  PRIMARY KEY (`productID`,`orderID`),
  KEY `orderID` (`orderID`),
  CONSTRAINT `CustomerOrderItem_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `CustomerOrder` (`orderID`) ON DELETE CASCADE,
  CONSTRAINT `CustomerOrderItem_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `Product` (`productID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerOrderItem`
--

LOCK TABLES `CustomerOrderItem` WRITE;
/*!40000 ALTER TABLE `CustomerOrderItem` DISABLE KEYS */;
INSERT INTO `CustomerOrderItem` VALUES (2,1,95000,11,'laptop'),(3,1,99000,13,'laptop'),(5,1,99000,13,'.'),(2,1,2500,15,'speaker'),(3,1,2300,16,'speaker'),(4,1,2500,16,'speaker'),(4,1,45000,17,'.'),(6,1,48000,17,'.'),(4,1,17000,18,'.'),(4,1,55000,19,'.');
/*!40000 ALTER TABLE `CustomerOrderItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `empID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `salary` int DEFAULT NULL,
  `joinDate` date DEFAULT NULL,
  `role` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`empID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'RK','1980-03-27','rkchirawa@yahoo.co.in','9351716881',0,'2000-11-11',1,'Pilani','1234'),(2,'Mukesh','1988-11-11','mukesh@gmail.com','9988776655',30000,'2006-11-11',0,'Surajgarh','1234'),(4,'Chirag','2002-03-08','ctnak@gmail.com','8752473982',0,NULL,0,'Jhunjhunu','1234');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `InventoryItem`
--

DROP TABLE IF EXISTS `InventoryItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `InventoryItem` (
  `itemID` int NOT NULL AUTO_INCREMENT,
  `productID` int DEFAULT NULL,
  `supplyOrderID` int DEFAULT NULL,
  `orderID` int DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  KEY `productID` (`productID`),
  KEY `supplyOrderID` (`supplyOrderID`),
  KEY `orderID` (`orderID`),
  CONSTRAINT `InventoryItem_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `Product` (`productID`),
  CONSTRAINT `InventoryItem_ibfk_2` FOREIGN KEY (`supplyOrderID`) REFERENCES `SupplyOrder` (`orderID`) ON DELETE CASCADE,
  CONSTRAINT `InventoryItem_ibfk_3` FOREIGN KEY (`orderID`) REFERENCES `CustomerOrder` (`orderID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `InventoryItem`
--

LOCK TABLES `InventoryItem` WRITE;
/*!40000 ALTER TABLE `InventoryItem` DISABLE KEYS */;
INSERT INTO `InventoryItem` VALUES (2,11,1,2),(3,11,2,NULL),(4,11,2,NULL),(5,11,2,NULL),(6,11,2,NULL),(7,11,2,NULL),(8,11,2,NULL),(9,11,2,NULL),(10,11,2,NULL),(11,11,2,NULL),(12,12,2,NULL),(13,12,2,NULL),(14,12,2,NULL),(15,12,2,NULL),(16,12,2,NULL),(17,12,2,NULL),(18,12,2,NULL),(19,12,2,NULL),(20,12,2,NULL),(21,12,2,NULL),(22,13,2,3),(23,13,2,5),(24,13,2,NULL),(25,13,2,NULL),(26,13,2,NULL),(27,13,2,NULL),(28,13,2,NULL),(29,13,2,NULL),(30,13,2,NULL),(31,13,2,NULL),(32,14,2,NULL),(33,14,2,NULL),(34,14,2,NULL),(35,14,2,NULL),(36,14,2,NULL),(37,14,2,NULL),(38,14,2,NULL),(39,14,2,NULL),(40,14,2,NULL),(41,14,2,NULL),(42,15,2,2),(43,15,2,NULL),(44,15,2,NULL),(45,15,2,NULL),(46,15,2,NULL),(47,15,2,NULL),(48,15,2,NULL),(49,15,2,NULL),(50,15,2,NULL),(51,15,2,NULL),(52,16,3,3),(53,16,3,4),(54,16,3,NULL),(55,16,3,NULL),(56,16,3,NULL),(57,16,3,NULL),(58,16,3,NULL),(59,16,3,NULL),(60,16,3,NULL),(61,16,3,NULL),(62,17,3,4),(63,17,3,6),(64,17,3,NULL),(65,17,3,NULL),(66,17,3,NULL),(67,17,3,NULL),(68,17,3,NULL),(69,17,3,NULL),(70,17,3,NULL),(71,17,3,NULL),(72,18,3,4),(73,18,3,NULL),(74,18,3,NULL),(75,18,3,NULL),(76,18,3,NULL),(77,18,3,NULL),(78,18,3,NULL),(79,18,3,NULL),(80,18,3,NULL),(81,18,3,NULL),(82,19,3,4),(83,19,3,NULL),(84,19,3,NULL),(85,19,3,NULL),(86,19,3,NULL),(87,19,3,NULL),(88,19,3,NULL),(89,19,3,NULL),(90,19,3,NULL),(91,19,3,NULL);
/*!40000 ALTER TABLE `InventoryItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Product` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `warrantyLength` int DEFAULT NULL,
  `warrantyCoverage` longtext,
  `MRP` int DEFAULT NULL,
  `costPrice` int DEFAULT NULL,
  `variant` varchar(1024) DEFAULT NULL,
  `amountInStock` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photoPath` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`productID`),
  KEY `name` (`name`),
  CONSTRAINT `Product_ibfk_1` FOREIGN KEY (`name`) REFERENCES `ProductCategory` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (11,'ASUS ROG Strix G17 (2022), 17.3-inch (43.94 cms) FHD 144Hz, AMD Ryzen 7-6800H, RTX 3050 4GB Graphics, Gaming Laptop (16GB/512GB SSD/Windows 11/Green/2.5 Kg), G713RC-HX021W',1,'All Expect Physical Damage ',95000,90000,'1',9,'Asus ROG Strix G15','rog.jpg'),(12,'Processor 11th Gen Intel® Core™ i3-1115G4 (6 MB cache, 2 cores, 4 threads, up to 4.10 GHz Turbo)    Operating System Windows 11 Home (S mode), English    Graphics Card Intel® UHD Graphics    Display 15.6\", FHD 1920x1080, 60Hz, Non-Touch, AG, WVA, LED-Backlit, Narrow Border    Memory  8 GB, 1 x 8 GB, DDR4, 2666 MHz    Hard Drive 256 GB, M.2, PCIe NVMe, SSD',1,'All Expect Physical Damage ',75000,68000,'1',10,'Dell Inspiron 15','dellinspiron.jpg'),(13,'Hp Pavilion 15 12Th Gen Intel Core I5 8Gb Sdram/512Gb Ssd 15.6 Inches Fhd,IPS,Micro-Edge Display/Intel Iris Xe Graphics/B&O/Windows 11 Home/Ms Office 2021/Fast Charge/1.75Kg, 15-Eg2009Tu, Silver',1,'All Expect Physical Damage ',99000,95000,'1',8,'Hp Pavilion 15','hpPavillion.jpg'),(14,'HP 2020 15, 15.6\" HD Touchscreen Premium Laptop 10th Gen Intel Core i5-1035G1, 16GB DDR4, 512GB SSD, USB Type-C, HDMI, Windows 10 (Silver W)',1,'All Expect Physical Damage ',70000,65000,'1',10,'HP 2020 15','hpLaptop.jpg'),(15,'40 Watt 2.1 Channel Bluetooth Wireless Multimedia Speaker with RGB LED Illuminated Subwoofer, Remote/USB/SD/FM/AUX (Black)',1,'All Expect Physical Damage ',3000,2300,'1',9,'iBall Thunder','iballSpeakers.jpg'),(16,'Wireless Bluetooth 16W Portable Bar Speaker with Supporting LED Display, USB, SD Card, AUX, FM, TWS & Call Function. (Black)',1,'All Expect Physical Damage ',2900,2100,'1',8,'Zebronics Zeb-VITA Plus','zebronicsSpeaker.jpg'),(17,'Samsung 336 L 3 Star Inverter Frost Free Double Door Refrigerator (RT37A4633S8/HL, Silver, Elegant Inox, Curd Maestro)',5,'All Expect Physical Damage ',50000,40000,'1',8,'Samsung 336 Refrigerator','samsungFridge.jpg'),(18,'Samsung 80 cm (32 Inches) Wondertainment Series HD Ready LED Smart TV UA32T4340BKXXL (Glossy Black)',3,'All Expect Physical Damage ',18000,13000,'1',9,'Samsung Smart LED TV','samsungTV.jpg'),(19,'Voltas 1.5 Ton Hot and Cold Split AC (Copper 18H SZS White)',5,'All Expect Physical Damage ',60000,50000,'1',9,'TATA Voltas','tataAC.jpg');
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductCategory`
--

DROP TABLE IF EXISTS `ProductCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProductCategory` (
  `name` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductCategory`
--

LOCK TABLES `ProductCategory` WRITE;
/*!40000 ALTER TABLE `ProductCategory` DISABLE KEYS */;
INSERT INTO `ProductCategory` VALUES ('Asus ROG Strix G15','Laptops'),('Dell Inspiron 15','Laptops'),('HP 2020 15','Laptops'),('Hp Pavilion 15','Laptops'),('iBall Thunder','Speakers'),('Samsung 336 Refrigerator','Refridgetor'),('Samsung Smart LED TV','TV'),('TATA Voltas','AC'),('Zebronics Zeb-VITA Plus','Speakers');
/*!40000 ALTER TABLE `ProductCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductSuppliers`
--

DROP TABLE IF EXISTS `ProductSuppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ProductSuppliers` (
  `supplierID` int NOT NULL,
  `productID` int NOT NULL,
  PRIMARY KEY (`supplierID`,`productID`),
  KEY `productID` (`productID`),
  CONSTRAINT `ProductSuppliers_ibfk_1` FOREIGN KEY (`supplierID`) REFERENCES `Supplier` (`supplierID`) ON DELETE CASCADE,
  CONSTRAINT `ProductSuppliers_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `Product` (`productID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductSuppliers`
--

LOCK TABLES `ProductSuppliers` WRITE;
/*!40000 ALTER TABLE `ProductSuppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProductSuppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Supplier`
--

DROP TABLE IF EXISTS `Supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Supplier` (
  `name` varchar(255) DEFAULT NULL,
  `supplierID` int NOT NULL AUTO_INCREMENT,
  `phone` varchar(15) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `moneySpent` int DEFAULT NULL,
  `ordersFulfilled` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`supplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Supplier`
--

LOCK TABLES `Supplier` WRITE;
/*!40000 ALTER TABLE `Supplier` DISABLE KEYS */;
INSERT INTO `Supplier` VALUES ('Gaurav Kumar',1,'6261900194','Lonavala',0,0,'gaurav@gmail.com'),('Nitin',2,'8752477292','M.P.',0,0,'nitin@gmail.com');
/*!40000 ALTER TABLE `Supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SupplyOrder`
--

DROP TABLE IF EXISTS `SupplyOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SupplyOrder` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL,
  `deliveryDate` date DEFAULT NULL,
  `deliveryStatus` varchar(255) DEFAULT NULL,
  `totalAmount` int DEFAULT NULL,
  `supplierID` int DEFAULT NULL,
  `placedBy` int DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `supplierID` (`supplierID`),
  KEY `placedBy` (`placedBy`),
  CONSTRAINT `SupplyOrder_ibfk_1` FOREIGN KEY (`supplierID`) REFERENCES `Supplier` (`supplierID`) ON DELETE CASCADE,
  CONSTRAINT `SupplyOrder_ibfk_2` FOREIGN KEY (`placedBy`) REFERENCES `Employee` (`empID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SupplyOrder`
--

LOCK TABLES `SupplyOrder` WRITE;
/*!40000 ALTER TABLE `SupplyOrder` DISABLE KEYS */;
INSERT INTO `SupplyOrder` VALUES (1,'2022-11-03','2022-11-08','Delivered',90000,1,2),(2,'2022-11-04','2022-11-09','Delivered',3133000,1,2),(3,'2022-11-10','2022-11-13','Delivered',1240000,2,2);
/*!40000 ALTER TABLE `SupplyOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SupplyOrderItem`
--

DROP TABLE IF EXISTS `SupplyOrderItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SupplyOrderItem` (
  `supplyOrderID` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  `productID` int NOT NULL,
  `additionalInfo` longtext,
  PRIMARY KEY (`productID`,`supplyOrderID`),
  KEY `supplyOrderID` (`supplyOrderID`),
  CONSTRAINT `SupplyOrderItem_ibfk_1` FOREIGN KEY (`supplyOrderID`) REFERENCES `SupplyOrder` (`orderID`) ON DELETE CASCADE,
  CONSTRAINT `SupplyOrderItem_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `Product` (`productID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SupplyOrderItem`
--

LOCK TABLES `SupplyOrderItem` WRITE;
/*!40000 ALTER TABLE `SupplyOrderItem` DISABLE KEYS */;
INSERT INTO `SupplyOrderItem` VALUES (1,1,90000,11,'Nothing'),(2,9,810000,11,'rog'),(2,10,680000,12,'laptop'),(2,10,950000,13,'laptop'),(2,10,650000,14,'laptop'),(2,10,23000,15,'speaker'),(3,10,21000,16,'speaker'),(3,10,400000,17,'Fridge'),(3,10,130000,18,'TV'),(3,10,500000,19,'AC');
/*!40000 ALTER TABLE `SupplyOrderItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `username` varchar(256) NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-08 17:26:12
