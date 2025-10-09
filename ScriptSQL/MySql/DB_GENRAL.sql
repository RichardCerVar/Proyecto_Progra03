-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: ta-prog3.czckeyy6ic4v.us-east-1.rds.amazonaws.com    Database: TA_PROG3
-- ------------------------------------------------------
-- Server version	8.4.4

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `BOD_CATEGORIA`
--

DROP TABLE IF EXISTS `BOD_CATEGORIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_CATEGORIA` (
  `CATEGORIA_ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(60) NOT NULL,
  PRIMARY KEY (`CATEGORIA_ID`),
  UNIQUE KEY `DESCRIPCION_UNIQUE` (`DESCRIPCION`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_CATEGORIA`
--

LOCK TABLES `BOD_CATEGORIA` WRITE;
/*!40000 ALTER TABLE `BOD_CATEGORIA` DISABLE KEYS */;
INSERT INTO `BOD_CATEGORIA` VALUES (18,'Artículos de Oficina'),(1,'Bebidas'),(9,'Bebidas Alcoholicas'),(11,'Cereales y Granos'),(15,'Condimentos y Salsas'),(13,'Congelados'),(7,'Conservas'),(17,'Ferretería'),(8,'Frutas y Verduras'),(10,'Golosinas'),(6,'Higiene Personal'),(16,'Hogar y Cocina'),(3,'Lácteos'),(5,'Limpieza'),(4,'Panadería'),(14,'Panadería y Pastelería'),(12,'Pescados y Mariscos'),(2,'Snacks');
/*!40000 ALTER TABLE `BOD_CATEGORIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_CLIENTE_AL_FIADO`
--

DROP TABLE IF EXISTS `BOD_CLIENTE_AL_FIADO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_CLIENTE_AL_FIADO` (
  `CLIENTE_ID` int NOT NULL AUTO_INCREMENT,
  `ALIAS` varchar(40) NOT NULL,
  `NOMBRE` varchar(60) NOT NULL,
  `TELEFONO` varchar(9) NOT NULL,
  `FECHA_DE_PAGO` date NOT NULL,
  `ACTIVO` tinyint NOT NULL,
  `MONTO_DEUDA` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`CLIENTE_ID`),
  UNIQUE KEY `ALIAS_UNIQUE` (`ALIAS`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_CLIENTE_AL_FIADO`
--

LOCK TABLES `BOD_CLIENTE_AL_FIADO` WRITE;
/*!40000 ALTER TABLE `BOD_CLIENTE_AL_FIADO` DISABLE KEYS */;
INSERT INTO `BOD_CLIENTE_AL_FIADO` VALUES (1,'El Vecino 1','Juan Perez','910001000','2025-10-15',1,613.50),(2,'Sra. Rosa M.','Rosa Milla','920002000','2025-10-20',1,0.40),(3,'Don Luis Ramos','Luis Ramos','930003000','2025-11-01',1,8.25),(4,'Marta la Morosa','Marta Gomez','940004000','2025-09-30',0,70.00),(5,'Carlos S. Fiel','Carlos Soto','950005000','2025-10-10',1,0.00),(6,'Vecino 6','Hector Diaz','960006000','2025-10-18',1,33.10),(7,'Vecino 7','Isabel Flores','970007000','2025-11-05',1,12.00),(8,'Vecino 8','Javier Peña','980008000','2025-10-25',1,60.00),(9,'Vecino 9','Karla Luna','990009000','2025-10-12',1,0.00),(10,'Juanito','Leo Torres','911111111','2025-10-30',1,20.00),(11,'Vecino 11','Monica Vega','922222222','2025-11-15',1,0.00),(12,'Vecino 12','Nico Polo','933333333','2025-10-28',1,10.50),(13,'Vecino 13','Omar Cueva','944444444','2025-11-10',1,55.00),(14,'Vecino 14','Pilar Soto','955555555','2025-10-05',1,0.00),(15,'Vecino 15','Quique Vidal','966666666','2025-10-22',1,25.00),(16,'Vecino 16','Rocio Uria','977777777','2025-11-20',1,0.00),(17,'JUAN Carlos','Sergio Vera','988888888','2025-10-27',1,14.99),(18,'Vecino 18','Tina Salas','999999999','2025-11-03',1,40.00),(19,'Vecino 19','Ulises Paz','901010101','2025-10-17',1,0.00),(20,'Vecino 20','Vanesa Rey','902020202','2025-11-08',1,3.50),(21,'Cli Inactivo 1','Willy Cardenas','903030303','2025-10-01',0,100.00),(22,'Cli Inactivo 2','Ximena Nuñez','904040404','2025-10-05',0,0.00),(23,'Cli Inactivo 3','Yuri Chavez','905050505','2025-10-10',0,10.00),(24,'Cli Inactivo 4','Zara Perez','906060606','2025-10-15',0,0.00),(25,'Cli Inactivo 5','Alina Lopez','907070707','2025-10-20',0,50.00),(26,'Vecino 26','Bryan Gomez','908080808','2025-11-25',1,18.00),(27,'Vecino 27','Claudia Rios','909090909','2025-11-30',1,0.00),(28,'Vecino 28','Dario Muñoz','910101010','2025-12-05',1,5.00),(29,'Cli Inactivo 6','Eva Torres','911121314','2025-12-10',0,22.00),(30,'Vecino 30','Fidel Castro','915161718','2025-12-15',1,0.00),(31,'Vecino 31','Gloria Soto','919202122','2025-12-20',1,30.00),(32,'Vecino 32','Hugo Paz','923242526','2025-12-25',1,1.50),(33,'Vecino 33','Irma Luna','927282930','2026-01-01',1,0.00),(34,'Vecino 34','Jose Diaz','931323334','2026-01-05',1,16.00),(35,'Vecino 35','Kelly Ramos','935363738','2026-01-10',1,0.00),(36,'JUAN_MOD','Juan Pérez Modificado','999888777','2025-11-06',0,0.00),(37,'JUAN123','Juan Pérez García','987654321','2025-10-22',1,0.00),(38,'CArLO_MOD','Carlos Pérez Modificado','999888777','2025-11-06',0,0.00),(39,'CARLOS123','Carlos Pérez García','987654321','2025-10-23',1,0.00);
/*!40000 ALTER TABLE `BOD_CLIENTE_AL_FIADO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_DETALLE_DEVOLUCION`
--

DROP TABLE IF EXISTS `BOD_DETALLE_DEVOLUCION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_DETALLE_DEVOLUCION` (
  `DEVOLUCION_ID` int NOT NULL,
  `PRODUCTO_ID` int NOT NULL,
  `SUBTOTAL` decimal(10,2) NOT NULL,
  `CANTIDAD` int NOT NULL,
  `RAZON_DEVOLUCION_ID` int NOT NULL,
  PRIMARY KEY (`DEVOLUCION_ID`,`PRODUCTO_ID`),
  KEY `fk_BOD_DETALLE_FIADO_BOD_DEVOLUCION1_idx` (`DEVOLUCION_ID`),
  KEY `fk_BOD_DETALLE_FIADO_BOD_PRODUCTO1_idx` (`PRODUCTO_ID`),
  KEY `fk_BOD_DETALLE_DEVOLUCION_BOD_RAZON_DEVOLUCION1_idx` (`RAZON_DEVOLUCION_ID`),
  CONSTRAINT `fk_BOD_DETALLE_DEVOLUCION_BOD_RAZON_DEVOLUCION1` FOREIGN KEY (`RAZON_DEVOLUCION_ID`) REFERENCES `BOD_RAZON_DEVOLUCION` (`RAZON_DEVOLUCION_ID`),
  CONSTRAINT `fk_BOD_DETALLE_FIADO_BOD_DEVOLUCION1` FOREIGN KEY (`DEVOLUCION_ID`) REFERENCES `BOD_DEVOLUCION` (`DEVOLUCION_ID`),
  CONSTRAINT `fk_BOD_DETALLE_FIADO_BOD_PRODUCTO1` FOREIGN KEY (`PRODUCTO_ID`) REFERENCES `BOD_PRODUCTO` (`PRODUCTO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_DETALLE_DEVOLUCION`
--

LOCK TABLES `BOD_DETALLE_DEVOLUCION` WRITE;
/*!40000 ALTER TABLE `BOD_DETALLE_DEVOLUCION` DISABLE KEYS */;
INSERT INTO `BOD_DETALLE_DEVOLUCION` VALUES (1,1,1.50,1,1),(1,2,80.00,2,1),(1,16,12.00,2,16),(2,1,40.00,5,1),(2,2,7.60,2,2),(2,3,80.00,2,1),(2,17,8.00,2,1),(3,3,7.50,3,3),(3,18,6.00,2,2),(4,4,4.40,2,4),(4,19,8.00,2,3),(5,5,4.00,1,5),(5,20,10.00,2,4),(6,6,11.00,2,6),(6,21,30.00,2,5),(7,7,2.40,2,7),(7,22,70.00,2,6),(8,8,4.00,2,8),(8,23,9.00,2,7),(9,9,7.00,2,9),(9,24,2.00,2,8),(10,10,6.00,2,10),(10,25,13.00,2,9),(11,11,3.60,2,11),(12,12,2.00,2,12),(13,13,20.00,2,13),(14,14,30.00,2,14),(15,15,4.00,2,15);
/*!40000 ALTER TABLE `BOD_DETALLE_DEVOLUCION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_DETALLE_VENTA`
--

DROP TABLE IF EXISTS `BOD_DETALLE_VENTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_DETALLE_VENTA` (
  `VENTA_ID` int NOT NULL,
  `PRODUCTO_ID` int NOT NULL,
  `SUBTOTAL` decimal(10,2) NOT NULL,
  `CANTIDAD` int NOT NULL,
  PRIMARY KEY (`VENTA_ID`,`PRODUCTO_ID`),
  KEY `fk_BOD_DETALLE_VENTA_BOD_VENTAS1_idx` (`VENTA_ID`),
  KEY `fk_BOD_DETALLE_VENTA_BOD_PRODUCTO1_idx` (`PRODUCTO_ID`),
  CONSTRAINT `fk_BOD_DETALLE_VENTA_BOD_PRODUCTO1` FOREIGN KEY (`PRODUCTO_ID`) REFERENCES `BOD_PRODUCTO` (`PRODUCTO_ID`),
  CONSTRAINT `fk_BOD_DETALLE_VENTA_BOD_VENTAS1` FOREIGN KEY (`VENTA_ID`) REFERENCES `BOD_VENTAS` (`VENTA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_DETALLE_VENTA`
--

LOCK TABLES `BOD_DETALLE_VENTA` WRITE;
/*!40000 ALTER TABLE `BOD_DETALLE_VENTA` DISABLE KEYS */;
INSERT INTO `BOD_DETALLE_VENTA` VALUES (1,1,1.50,1),(1,2,3.80,1),(1,3,2.50,1),(1,4,2.20,1),(1,5,4.00,1),(1,6,5.50,1),(1,7,1.20,1),(1,8,2.00,1),(2,1,50.00,10),(2,2,10.00,5),(2,3,50.00,5),(2,4,50.00,5),(2,9,3.50,1),(2,10,3.00,1),(2,11,1.80,1),(2,12,1.00,1),(3,13,10.00,1),(3,14,15.00,1),(3,15,2.00,1),(4,16,6.00,1),(4,17,8.50,1),(4,18,3.00,1),(5,19,4.00,1),(5,20,5.00,1),(5,21,15.00,1),(6,22,35.00,1),(6,23,4.50,1),(6,24,1.00,1),(7,25,6.50,1),(7,26,3.00,1),(7,27,2.50,1),(8,28,7.00,1),(8,29,1.50,1),(8,30,5.00,1),(9,31,3.50,1),(9,32,8.00,1),(10,33,7.00,1),(10,34,15.00,1),(10,35,35.00,1),(11,36,4.50,1),(11,37,1.00,1),(11,38,6.50,1),(12,39,3.00,1),(12,40,2.50,1),(13,1,1.50,1),(13,2,3.80,1),(13,3,2.50,1),(14,4,2.20,1),(14,5,4.00,1),(15,6,5.50,1),(15,7,1.20,1),(16,8,2.00,1),(17,9,3.50,1),(17,10,3.00,1),(18,11,1.80,1),(18,12,1.00,1),(19,13,10.00,1),(19,14,15.00,1),(20,15,2.00,1),(20,16,6.00,1),(21,17,8.50,1),(21,18,3.00,1),(22,19,4.00,1),(22,20,5.00,1),(23,21,15.00,1),(23,22,35.00,1),(24,23,4.50,1),(24,24,1.00,1),(25,25,6.50,1),(25,26,3.00,1),(26,27,2.50,1),(26,28,7.00,1),(27,29,1.50,1),(27,30,5.00,1),(28,31,3.50,1),(28,32,8.00,1),(29,33,7.00,1),(29,34,15.00,1),(30,35,35.00,1),(30,36,4.50,1),(31,37,1.00,1),(31,38,6.50,1),(32,39,3.00,1),(32,40,2.50,1),(33,1,1.50,1),(33,2,3.80,1),(34,3,2.50,1),(34,4,2.20,1),(35,5,4.00,1),(35,6,5.50,1),(36,7,1.20,1),(36,8,2.00,1),(37,9,3.50,1),(37,10,3.00,1),(38,11,1.80,1),(38,12,1.00,1),(39,13,10.00,1),(39,14,15.00,1),(40,15,2.00,1),(40,16,6.00,1),(41,17,8.50,1),(41,18,3.00,1),(42,19,4.00,1),(42,20,5.00,1),(43,21,15.00,1),(43,22,35.00,1),(44,23,4.50,1),(44,24,1.00,1),(45,25,6.50,1),(45,26,3.00,1),(46,27,2.50,1),(46,28,7.00,1),(47,29,1.50,1),(47,30,5.00,1),(48,31,3.50,1),(48,32,8.00,1),(49,33,7.00,1),(49,34,15.00,1),(50,35,35.00,1),(50,36,4.50,1),(51,37,1.00,1),(51,38,6.50,1),(52,39,3.00,1),(52,40,2.50,1),(53,1,1.50,1),(53,2,3.80,1),(54,3,2.50,1),(54,4,2.20,1),(55,5,4.00,1),(55,6,5.50,1),(56,7,1.20,1),(56,8,2.00,1),(57,9,3.50,1),(57,10,3.00,1),(62,1,65.00,5),(62,3,3.00,2),(63,1,65.00,5),(63,3,3.00,2),(64,1,65.00,5),(64,3,3.00,2),(65,1,39.00,3),(65,2,7.00,1),(66,1,39.00,3),(66,2,7.00,1),(67,1,39.00,3),(67,2,7.00,1),(68,1,70.00,5),(68,3,3.00,2),(69,1,42.00,3),(69,2,7.00,1),(70,1,75.00,5),(70,3,3.00,2),(71,1,45.00,3),(71,2,7.00,1),(72,1,80.00,5),(72,3,3.00,2),(73,1,48.00,3),(73,2,7.00,1),(74,1,85.00,5),(74,3,3.00,2),(75,1,51.00,3),(75,2,7.00,1),(76,1,95.00,5),(76,3,3.00,2),(77,1,57.00,3),(77,2,7.00,1),(78,1,100.00,5),(78,3,3.00,2),(79,1,60.00,3),(79,2,7.00,1),(80,1,105.00,5),(80,3,3.00,2),(81,1,63.00,3),(81,2,7.00,1),(82,1,110.00,5),(82,3,3.00,2),(83,1,66.00,3),(83,2,7.00,1),(84,1,115.00,5),(84,3,3.00,2),(85,1,69.00,3),(85,2,7.00,1);
/*!40000 ALTER TABLE `BOD_DETALLE_VENTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_DEVOLUCION`
--

DROP TABLE IF EXISTS `BOD_DEVOLUCION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_DEVOLUCION` (
  `DEVOLUCION_ID` int NOT NULL AUTO_INCREMENT,
  `TOTAL` decimal(10,2) NOT NULL,
  `FECHA` date NOT NULL,
  `USUARIO_ID` int NOT NULL,
  PRIMARY KEY (`DEVOLUCION_ID`),
  KEY `fk_BOD_DEVOLUCION_BOD_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `fk_BOD_DEVOLUCION_BOD_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `BOD_USUARIO` (`USUARIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_DEVOLUCION`
--

LOCK TABLES `BOD_DEVOLUCION` WRITE;
/*!40000 ALTER TABLE `BOD_DEVOLUCION` DISABLE KEYS */;
INSERT INTO `BOD_DEVOLUCION` VALUES (1,15.00,'2025-10-08',6),(2,70.00,'2025-10-08',7),(3,45.50,'2025-10-08',8),(4,20.00,'2025-10-08',9),(5,6.50,'2025-10-07',6),(6,30.00,'2025-10-07',7),(7,9.40,'2025-10-06',8),(8,7.00,'2025-10-06',9),(9,7.50,'2025-10-05',6),(10,5.00,'2025-10-05',7),(11,13.60,'2025-10-04',8),(12,27.00,'2025-10-04',9),(13,14.00,'2025-10-03',6),(14,50.00,'2025-10-03',7),(15,6.00,'2025-10-02',8),(16,125.50,'2025-10-07',1),(17,125.50,'2025-10-07',1),(18,30.00,'2025-10-07',2),(19,30.00,'2025-10-08',2),(20,30.00,'2025-10-09',2),(21,30.00,'2025-10-09',2),(22,30.00,'2025-10-09',2),(23,30.00,'2025-10-09',2),(24,30.00,'2025-10-09',2),(25,30.00,'2025-10-09',2),(26,30.00,'2025-10-09',2);
/*!40000 ALTER TABLE `BOD_DEVOLUCION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_HISTORIAL_OPERACIONES`
--

DROP TABLE IF EXISTS `BOD_HISTORIAL_OPERACIONES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_HISTORIAL_OPERACIONES` (
  `OPERACION_ID` int NOT NULL AUTO_INCREMENT,
  `TABLA_AFECTADA` varchar(60) NOT NULL,
  `OPERACION` enum('INSERCION','MODIFICACION','ELIMINACION') NOT NULL,
  `FECHA_HORA` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USUARIO_ID` int NOT NULL,
  PRIMARY KEY (`OPERACION_ID`),
  UNIQUE KEY `OPERACION_ID_UNIQUE` (`OPERACION_ID`),
  KEY `fk_BOD_HISTORIAL_OPERACIONES_BOD_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `fk_BOD_HISTORIAL_OPERACIONES_BOD_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `BOD_USUARIO` (`USUARIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_HISTORIAL_OPERACIONES`
--

LOCK TABLES `BOD_HISTORIAL_OPERACIONES` WRITE;
/*!40000 ALTER TABLE `BOD_HISTORIAL_OPERACIONES` DISABLE KEYS */;
INSERT INTO `BOD_HISTORIAL_OPERACIONES` VALUES (1,'BOD_PRODUCTO','INSERCION','2025-10-07 10:00:00',1),(2,'BOD_CLIENTE_AL_FIADO','MODIFICACION','2025-10-07 10:05:00',6),(3,'BOD_CATEGORIA','INSERCION','2025-10-07 10:10:00',3),(4,'BOD_PRODUCTO','ELIMINACION','2025-10-07 10:15:00',10),(5,'BOD_VENTAS','INSERCION','2025-10-07 10:20:00',7),(6,'BOD_USUARIO','MODIFICACION','2025-10-07 10:25:00',2),(7,'BOD_DEVOLUCION','INSERCION','2025-10-06 15:00:00',8),(8,'BOD_REGISTRO_PAGOS_FIADOS','INSERCION','2025-10-06 15:05:00',9),(9,'BOD_VENTAS_FIADAS','INSERCION','2025-10-06 15:10:00',6),(10,'BOD_PRODUCTO','MODIFICACION','2025-10-06 15:15:00',7),(11,'BOD_CLIENTE_AL_FIADO','INSERCION','2025-10-05 09:00:00',8),(12,'BOD_CATEGORIA','MODIFICACION','2025-10-05 09:05:00',9),(13,'BOD_PRODUCTO','INSERCION','2025-10-04 11:00:00',1),(14,'BOD_USUARIO','ELIMINACION','2025-10-03 14:00:00',2),(15,'BOD_VENTAS','INSERCION','2025-10-03 14:05:00',6),(16,'BOD_CLIENTE_AL_FIADO','MODIFICACION','2025-10-02 16:00:00',7),(17,'BOD_DEVOLUCION','INSERCION','2025-10-01 17:00:00',8),(18,'BOD_PRODUCTO','MODIFICACION','2025-09-30 18:00:00',9),(19,'BOD_VENTAS','INSERCION','2025-09-29 19:00:00',6),(20,'BOD_USUARIO','INSERCION','2025-09-28 20:00:00',1),(21,'BOD_RAZON_DEVOLUCION','INSERCION','2025-09-27 21:00:00',3),(22,'BOD_PRODUCTO','MODIFICACION','2025-09-26 22:00:00',10),(23,'BOD_VENTAS','INSERCION','2025-09-25 23:00:00',7),(24,'BOD_CLIENTE_AL_FIADO','MODIFICACION','2025-09-24 00:00:00',8),(25,'BOD_PRODUCTO','INSERCION','2025-09-23 01:00:00',9);
/*!40000 ALTER TABLE `BOD_HISTORIAL_OPERACIONES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_PRODUCTO`
--

DROP TABLE IF EXISTS `BOD_PRODUCTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_PRODUCTO` (
  `PRODUCTO_ID` int NOT NULL AUTO_INCREMENT,
  `CATEGORIA_ID` int NOT NULL,
  `NOMBRE` varchar(45) NOT NULL,
  `PRECIO_UNITARIO` decimal(10,2) NOT NULL,
  `UNIDAD_MEDIDA` enum('KILOGRAMO','LITRO','UNIDAD') NOT NULL,
  `STOCK` int NOT NULL,
  `STOCK_MINIMO` int NOT NULL,
  `ACTIVO` tinyint NOT NULL,
  PRIMARY KEY (`PRODUCTO_ID`),
  KEY `fk_BOD_PRODUCTO_BOD_CATEGORIA1_idx` (`CATEGORIA_ID`),
  CONSTRAINT `fk_BOD_PRODUCTO_BOD_CATEGORIA1` FOREIGN KEY (`CATEGORIA_ID`) REFERENCES `BOD_CATEGORIA` (`CATEGORIA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_PRODUCTO`
--

LOCK TABLES `BOD_PRODUCTO` WRITE;
/*!40000 ALTER TABLE `BOD_PRODUCTO` DISABLE KEYS */;
INSERT INTO `BOD_PRODUCTO` VALUES (1,9,'Pila AA Duradera (4u)',23.00,'UNIDAD',49,8,1),(2,10,'Cuaderno Espiral A4',7.00,'UNIDAD',83,15,1),(3,1,'Agua Mineral 1L',1.50,'LITRO',136,20,1),(4,1,'Gaseosa Cola 2L',3.80,'LITRO',80,15,1),(5,2,'Papas Fritas Grandes',2.50,'UNIDAD',45,10,1),(6,3,'Leche Entera 1L',2.20,'LITRO',60,12,1),(7,4,'Pan de Molde',4.00,'UNIDAD',30,5,1),(8,5,'Detergente Liquido 1L',5.50,'LITRO',25,8,1),(9,6,'Jabon de Tocador',1.20,'UNIDAD',100,25,1),(10,7,'Atún en Lata',2.00,'UNIDAD',75,15,1),(11,8,'Manzanas Rojas',3.50,'KILOGRAMO',50,10,1),(12,5,'Limpia Vidrios',3.00,'LITRO',10,5,0),(13,2,'Galletas de Vainilla',1.80,'UNIDAD',120,30,1),(14,3,'Yogur Natural',1.00,'UNIDAD',90,20,1),(15,9,'Clavos de 2 Pulgadas',0.10,'KILOGRAMO',50,10,0),(16,10,'Tinta Impresora Negra',25.00,'UNIDAD',5,2,0),(17,11,'Harina de Trigo 5kg',10.00,'KILOGRAMO',110,20,1),(18,12,'Filete de Merluza Cong.',15.00,'KILOGRAMO',40,10,1),(19,13,'Bolsa de Hielo',2.00,'UNIDAD',200,30,1),(20,14,'Torta de Chocolate (porc)',6.00,'UNIDAD',25,5,1),(21,15,'Aceite de Oliva Extra',8.50,'LITRO',60,15,1),(22,16,'Velitas Aromaticas',3.00,'UNIDAD',150,20,1),(23,17,'Cinta Adhesiva Ancha',4.00,'UNIDAD',80,10,1),(24,18,'Resmas de Papel A4',5.00,'UNIDAD',70,15,1),(25,1,'Cerveza Premium 6P',15.00,'UNIDAD',50,10,1),(26,1,'Vino Tinto Reserva',35.00,'LITRO',15,5,1),(27,2,'Chocolate Barra Grande',4.50,'UNIDAD',200,30,1),(28,2,'Gomitas Surtidas 100g',1.00,'KILOGRAMO',350,50,1),(29,3,'Avena Hojuelas 1kg',6.50,'KILOGRAMO',80,15,1),(30,3,'Arroz Extra',3.00,'KILOGRAMO',120,25,1),(31,4,'Sardinas en Aceite',2.50,'UNIDAD',150,20,1),(32,5,'Papas Pre-Fritas 1kg',7.00,'KILOGRAMO',40,10,1),(33,6,'Croissant Unitario',1.50,'UNIDAD',60,15,1),(34,7,'Mayonesa Grande',5.00,'UNIDAD',75,12,1),(35,8,'Bolsas de Basura Rll',3.50,'UNIDAD',110,20,1),(36,1,'Licor de Hierbas',25.00,'LITRO',8,5,1),(37,2,'Chicle Menta',0.50,'UNIDAD',15,10,1),(38,5,'Helado Vainilla 1L',12.00,'LITRO',10,5,1),(39,7,'Salsa Picante Importada',9.00,'LITRO',18,5,1),(40,8,'Trapeador de Algodón',11.00,'UNIDAD',12,5,1),(41,7,'Milanesas De Carne (3u)',10.50,'UNIDAD',10,2,1),(42,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(43,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(44,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(45,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(46,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(47,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(48,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(49,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(50,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(51,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(52,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(53,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(54,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(55,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(56,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(57,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1),(58,1,'Producto Prueba Test',15.50,'UNIDAD',100,10,1);
/*!40000 ALTER TABLE `BOD_PRODUCTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_RAZON_DEVOLUCION`
--

DROP TABLE IF EXISTS `BOD_RAZON_DEVOLUCION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_RAZON_DEVOLUCION` (
  `RAZON_DEVOLUCION_ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(60) NOT NULL,
  PRIMARY KEY (`RAZON_DEVOLUCION_ID`),
  UNIQUE KEY `DESCRIPCION_UNIQUE` (`DESCRIPCION`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_RAZON_DEVOLUCION`
--

LOCK TABLES `BOD_RAZON_DEVOLUCION` WRITE;
/*!40000 ALTER TABLE `BOD_RAZON_DEVOLUCION` DISABLE KEYS */;
INSERT INTO `BOD_RAZON_DEVOLUCION` VALUES (12,'Cambio de opinion del cliente'),(4,'Cliente ya no lo quiere'),(16,'Descontinuado por proveedor'),(8,'Diferencia de precio'),(7,'Embalaje defectuoso'),(3,'Error en el pedido'),(9,'Exceso de stock al cliente'),(2,'Fecha de caducidad vencida'),(15,'Garantía solicitada'),(14,'Mala manipulación en tienda'),(11,'No corresponde al catálogo'),(10,'Pedido duplicado'),(5,'Problema de calidad'),(1,'Producto dañado'),(6,'Producto incorrecto'),(25,'Razon de Prueba Test Backend'),(13,'Rotura durante el transporte');
/*!40000 ALTER TABLE `BOD_RAZON_DEVOLUCION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_REGISTRO_PAGOS_FIADOS`
--

DROP TABLE IF EXISTS `BOD_REGISTRO_PAGOS_FIADOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_REGISTRO_PAGOS_FIADOS` (
  `PAGO_ID` int NOT NULL AUTO_INCREMENT,
  `USUARIO_ID` int NOT NULL,
  `CLIENTE_ID` int NOT NULL,
  `FECHA` date NOT NULL,
  `METODO_PAGO` enum('EFECTIVO','TRANSFERENCIA') NOT NULL,
  `MONTO` decimal(10,2) NOT NULL,
  PRIMARY KEY (`PAGO_ID`),
  KEY `fk_BOD_REGISTRO_PAGOS_FIADOS_BOD_USUARIO1_idx` (`USUARIO_ID`),
  KEY `fk_BOD_REGISTRO_PAGOS_FIADOS_BOD_CLIENTE_AL_FIADO1_idx` (`CLIENTE_ID`),
  CONSTRAINT `fk_BOD_REGISTRO_PAGOS_FIADOS_BOD_CLIENTE_AL_FIADO1` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `BOD_CLIENTE_AL_FIADO` (`CLIENTE_ID`),
  CONSTRAINT `fk_BOD_REGISTRO_PAGOS_FIADOS_BOD_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `BOD_USUARIO` (`USUARIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_REGISTRO_PAGOS_FIADOS`
--

LOCK TABLES `BOD_REGISTRO_PAGOS_FIADOS` WRITE;
/*!40000 ALTER TABLE `BOD_REGISTRO_PAGOS_FIADOS` DISABLE KEYS */;
INSERT INTO `BOD_REGISTRO_PAGOS_FIADOS` VALUES (1,6,1,'2025-10-01','EFECTIVO',50.00),(2,7,1,'2025-10-05','TRANSFERENCIA',50.00),(3,8,2,'2025-10-03','EFECTIVO',10.00),(4,9,3,'2025-10-01','EFECTIVO',8.25),(5,6,4,'2025-10-02','TRANSFERENCIA',20.00),(6,7,6,'2025-10-05','EFECTIVO',33.10),(7,8,8,'2025-10-06','TRANSFERENCIA',10.00),(8,9,10,'2025-10-04','EFECTIVO',5.00),(9,6,12,'2025-10-05','TRANSFERENCIA',10.50),(10,7,13,'2025-10-06','EFECTIVO',25.00),(11,8,15,'2025-10-07','TRANSFERENCIA',10.00),(12,9,17,'2025-10-07','EFECTIVO',14.99),(13,6,18,'2025-10-07','EFECTIVO',15.00),(14,7,20,'2025-10-07','TRANSFERENCIA',3.50),(15,8,21,'2025-10-07','EFECTIVO',50.00),(16,9,21,'2025-10-07','TRANSFERENCIA',50.00),(17,6,23,'2025-10-07','EFECTIVO',10.00),(18,7,25,'2025-10-07','TRANSFERENCIA',25.00),(19,8,13,'2025-10-07','EFECTIVO',30.00),(20,9,18,'2025-10-07','TRANSFERENCIA',10.00),(21,6,26,'2025-10-08','EFECTIVO',18.00),(22,7,28,'2025-10-08','TRANSFERENCIA',5.00),(23,8,31,'2025-10-09','EFECTIVO',15.00),(24,9,32,'2025-10-09','TRANSFERENCIA',1.50),(25,6,34,'2025-10-10','EFECTIVO',16.00),(26,1,2,'2025-10-08','EFECTIVO',45.50),(27,1,2,'2025-10-08','EFECTIVO',45.50),(28,1,2,'2025-10-08','EFECTIVO',45.50),(29,1,2,'2025-10-08','EFECTIVO',45.50),(30,1,2,'2025-10-08','EFECTIVO',45.50),(31,1,2,'2025-10-09','EFECTIVO',45.50);
/*!40000 ALTER TABLE `BOD_REGISTRO_PAGOS_FIADOS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_USUARIO`
--

DROP TABLE IF EXISTS `BOD_USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_USUARIO` (
  `USUARIO_ID` int NOT NULL AUTO_INCREMENT,
  `USUARIO` varchar(30) NOT NULL,
  `TIPO_USUARIOS` enum('ADMINISTRADOR','OPERARIO') NOT NULL,
  `CORREO` varchar(45) NOT NULL,
  `CONTRASENHA` varchar(30) NOT NULL,
  `NOMBRE_COMPLETO` varchar(100) NOT NULL,
  `TELEFONO_USUARIO` varchar(9) NOT NULL,
  `ACTIVO_USUARIO` tinyint NOT NULL,
  PRIMARY KEY (`USUARIO_ID`),
  UNIQUE KEY `USUARIO_OPERARIO_UNIQUE` (`USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_USUARIO`
--

LOCK TABLES `BOD_USUARIO` WRITE;
/*!40000 ALTER TABLE `BOD_USUARIO` DISABLE KEYS */;
INSERT INTO `BOD_USUARIO` VALUES (1,'adm_jefe','ADMINISTRADOR','jefe@bodega.com','admin_A1','Javier Lopez Jefe','900000001',1),(2,'adm_finanzas','ADMINISTRADOR','finanzas@bodega.com','admin_A2','Sofia Ramos Finanzas','900000002',1),(3,'adm_rrhh','ADMINISTRADOR','rrhh@bodega.com','admin_A3','David Soto RRHH','900000003',1),(4,'adm_logistica','ADMINISTRADOR','logistica@bodega.com','admin_A4','Elena Castro Logistica','900000004',1),(5,'adm_sistemas','ADMINISTRADOR','sistemas@bodega.com','admin_A5','Marcos Vidal Sistemas','900000005',1),(6,'op_caja1','OPERARIO','caja1@bodega.com','op_O1','Ana Gomez Caja Principal','900000006',1),(7,'op_caja2','OPERARIO','caja2@bodega.com','op_O2','Ricardo Peña Auxiliar','900000007',1),(8,'op_almacen','OPERARIO','almacen@bodega.com','op_O3','Laura Diaz Almacen','900000008',1),(9,'op_repone','OPERARIO','repone@bodega.com','op_O4','Felipe Ortiz Reponedor','900000009',1),(10,'op_delivery','OPERARIO','delivery@bodega.com','op_O5','Gaby Luna Delivery','900000010',0),(11,'op_ausente1','OPERARIO','ausente1@bodega.com','op_I1','Hugo Reyes Inactivo 1','900000011',0),(12,'op_ausente2','OPERARIO','ausente2@bodega.com','op_I2','Irene Salas Inactivo 2','900000012',0),(13,'op_ausente3','OPERARIO','ausente3@bodega.com','op_I3','Kevin Flores Inactivo 3','900000013',0),(14,'op_ausente4','OPERARIO','ausente4@bodega.com','op_I4','Luz Moran Inactivo 4','900000014',0),(15,'op_ausente5','OPERARIO','ausente5@bodega.com','op_I5','Memo Salas Inactivo 5','900000015',0),(16,'adm_ex1','ADMINISTRADOR','exadm1@bodega.com','adm_I1','Nora Rivas Ex-Admin 1','900000016',0),(17,'adm_ex2','ADMINISTRADOR','exadm2@bodega.com','adm_I2','Oscar Polo Ex-Admin 2','900000017',0),(18,'adm_ex3','ADMINISTRADOR','exadm3@bodega.com','adm_I3','Paty Vega Ex-Admin 3','900000018',0),(19,'adm_ex4','ADMINISTRADOR','exadm4@bodega.com','adm_I4','Quique Toro Ex-Admin 4','900000019',0),(20,'adm_ex5','ADMINISTRADOR','exadm5@bodega.com','adm_I5','Rocio Uria Ex-Admin 5','900000020',0),(21,'test_usuario','OPERARIO','test@bodega.com','test123','Usuario de Prueba Test','999888777',0);
/*!40000 ALTER TABLE `BOD_USUARIO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_VENTAS`
--

DROP TABLE IF EXISTS `BOD_VENTAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_VENTAS` (
  `VENTA_ID` int NOT NULL AUTO_INCREMENT,
  `TOTAL` decimal(10,2) NOT NULL,
  `METODO_PAGO` enum('EFECTIVO','TRANSFERENCIA') NOT NULL,
  `FECHA` date NOT NULL,
  `USUARIO_ID` int NOT NULL,
  PRIMARY KEY (`VENTA_ID`),
  KEY `fk_BOD_VENTAS_BOD_USUARIO1_idx` (`USUARIO_ID`),
  CONSTRAINT `fk_BOD_VENTAS_BOD_USUARIO1` FOREIGN KEY (`USUARIO_ID`) REFERENCES `BOD_USUARIO` (`USUARIO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_VENTAS`
--

LOCK TABLES `BOD_VENTAS` WRITE;
/*!40000 ALTER TABLE `BOD_VENTAS` DISABLE KEYS */;
INSERT INTO `BOD_VENTAS` VALUES (1,150.50,'EFECTIVO','2025-09-28',6),(2,45.90,'TRANSFERENCIA','2025-09-29',7),(3,8.25,'EFECTIVO','2025-09-30',8),(4,70.00,'EFECTIVO','2025-09-30',9),(5,33.10,'TRANSFERENCIA','2025-10-01',6),(6,12.00,'EFECTIVO','2025-10-01',7),(7,60.00,'TRANSFERENCIA','2025-10-02',8),(8,20.00,'EFECTIVO','2025-10-03',9),(9,10.50,'EFECTIVO','2025-10-04',6),(10,55.00,'TRANSFERENCIA','2025-10-04',7),(11,25.00,'EFECTIVO','2025-10-05',8),(12,14.99,'TRANSFERENCIA','2025-10-05',9),(13,40.00,'EFECTIVO','2025-10-06',6),(14,3.50,'EFECTIVO','2025-10-06',7),(15,100.00,'TRANSFERENCIA','2025-10-07',8),(16,50.00,'EFECTIVO','2025-10-07',6),(17,12.30,'TRANSFERENCIA','2025-10-07',7),(18,9.90,'EFECTIVO','2025-10-07',8),(19,22.15,'TRANSFERENCIA','2025-10-07',9),(20,1.50,'EFECTIVO','2025-10-06',6),(21,35.00,'TRANSFERENCIA','2025-10-06',7),(22,18.00,'EFECTIVO','2025-10-05',8),(23,4.50,'TRANSFERENCIA','2025-10-05',9),(24,6.50,'EFECTIVO','2025-10-04',6),(25,3.00,'TRANSFERENCIA','2025-10-04',7),(26,2.50,'EFECTIVO','2025-10-03',8),(27,7.00,'TRANSFERENCIA','2025-10-03',9),(28,1.50,'EFECTIVO','2025-10-02',6),(29,5.00,'TRANSFERENCIA','2025-10-02',7),(30,3.50,'EFECTIVO','2025-10-01',8),(31,8.00,'TRANSFERENCIA','2025-10-01',9),(32,7.00,'EFECTIVO','2025-09-30',6),(33,15.00,'TRANSFERENCIA','2025-09-30',7),(34,35.00,'EFECTIVO','2025-09-29',8),(35,4.50,'TRANSFERENCIA','2025-09-29',9),(36,1.00,'EFECTIVO','2025-09-28',6),(37,6.50,'TRANSFERENCIA','2025-09-28',7),(38,3.00,'EFECTIVO','2025-09-27',8),(39,2.50,'TRANSFERENCIA','2025-09-27',9),(40,7.00,'EFECTIVO','2025-09-26',6),(41,1.50,'TRANSFERENCIA','2025-09-26',7),(42,5.00,'EFECTIVO','2025-09-25',8),(43,3.50,'TRANSFERENCIA','2025-09-25',9),(44,8.00,'EFECTIVO','2025-09-24',6),(45,7.00,'TRANSFERENCIA','2025-09-24',7),(46,15.00,'EFECTIVO','2025-09-23',8),(47,35.00,'TRANSFERENCIA','2025-09-23',9),(48,4.50,'EFECTIVO','2025-09-22',6),(49,1.00,'TRANSFERENCIA','2025-09-22',7),(50,10.00,'EFECTIVO','2025-09-21',8),(51,20.00,'TRANSFERENCIA','2025-09-21',9),(52,30.00,'EFECTIVO','2025-09-20',6),(53,40.00,'TRANSFERENCIA','2025-09-20',7),(54,5.00,'EFECTIVO','2025-09-19',8),(55,15.00,'TRANSFERENCIA','2025-09-19',9),(56,2.00,'EFECTIVO','2025-09-18',6),(57,2.50,'TRANSFERENCIA','2025-09-18',7),(58,3.00,'EFECTIVO','2025-09-17',8),(59,3.50,'TRANSFERENCIA','2025-09-17',9),(60,4.00,'EFECTIVO','2025-09-16',6),(61,4.50,'TRANSFERENCIA','2025-09-16',7),(62,68.00,'TRANSFERENCIA','2025-10-07',1),(63,68.00,'TRANSFERENCIA','2025-10-07',1),(64,68.00,'TRANSFERENCIA','2025-10-08',1),(65,46.00,'TRANSFERENCIA','2025-10-08',1),(66,46.00,'TRANSFERENCIA','2025-10-08',1),(67,46.00,'TRANSFERENCIA','2025-10-08',1),(68,73.00,'TRANSFERENCIA','2025-10-08',1),(69,49.00,'TRANSFERENCIA','2025-10-08',1),(70,78.00,'TRANSFERENCIA','2025-10-08',1),(71,52.00,'TRANSFERENCIA','2025-10-08',1),(72,83.00,'TRANSFERENCIA','2025-10-09',1),(73,55.00,'TRANSFERENCIA','2025-10-09',1),(74,88.00,'TRANSFERENCIA','2025-10-09',1),(75,58.00,'TRANSFERENCIA','2025-10-09',1),(76,98.00,'TRANSFERENCIA','2025-10-09',1),(77,64.00,'TRANSFERENCIA','2025-10-09',1),(78,103.00,'TRANSFERENCIA','2025-10-09',1),(79,67.00,'TRANSFERENCIA','2025-10-09',1),(80,108.00,'TRANSFERENCIA','2025-10-09',1),(81,70.00,'TRANSFERENCIA','2025-10-09',1),(82,113.00,'TRANSFERENCIA','2025-10-09',1),(83,73.00,'TRANSFERENCIA','2025-10-09',1),(84,118.00,'TRANSFERENCIA','2025-10-09',1),(85,76.00,'TRANSFERENCIA','2025-10-09',1);
/*!40000 ALTER TABLE `BOD_VENTAS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOD_VENTAS_FIADAS`
--

DROP TABLE IF EXISTS `BOD_VENTAS_FIADAS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOD_VENTAS_FIADAS` (
  `VENTA_FIADA_ID` int NOT NULL AUTO_INCREMENT,
  `VENTA_ID` int NOT NULL,
  `CLIENTE_ID` int NOT NULL,
  PRIMARY KEY (`VENTA_FIADA_ID`),
  UNIQUE KEY `VENTA_ID_UNIQUE` (`VENTA_ID`),
  KEY `fk_BOD_VENTAS_FIADAS_BOD_VENTAS1_idx` (`VENTA_ID`),
  KEY `fk_BOD_VENTAS_FIADAS_BOD_CLIENTE_AL_FIADO1_idx` (`CLIENTE_ID`),
  CONSTRAINT `fk_BOD_VENTAS_FIADAS_BOD_CLIENTE_AL_FIADO1` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `BOD_CLIENTE_AL_FIADO` (`CLIENTE_ID`),
  CONSTRAINT `fk_BOD_VENTAS_FIADAS_BOD_VENTAS1` FOREIGN KEY (`VENTA_ID`) REFERENCES `BOD_VENTAS` (`VENTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_VENTAS_FIADAS`
--

LOCK TABLES `BOD_VENTAS_FIADAS` WRITE;
/*!40000 ALTER TABLE `BOD_VENTAS_FIADAS` DISABLE KEYS */;
INSERT INTO `BOD_VENTAS_FIADAS` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,6),(6,6,7),(7,7,8),(8,8,10),(9,9,12),(10,10,13),(11,11,15),(12,12,17),(13,13,18),(14,14,20),(15,15,21),(16,65,1),(17,66,1),(18,67,1),(19,69,1),(20,71,1),(21,73,1),(22,75,1),(23,77,1),(24,79,1),(25,81,1),(26,83,1),(27,85,1);
/*!40000 ALTER TABLE `BOD_VENTAS_FIADAS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'TA_PROG3'
--
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_CLIENTE_AL_FIADO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_CLIENTE_AL_FIADO`(
    IN p_texto VARCHAR(30)
)
BEGIN
    SELECT 
        CLIENTE_ID,
        ALIAS,
        NOMBRE,
        TELEFONO,
        FECHA_DE_PAGO,
        ACTIVO,
        MONTO_DEUDA
    FROM BOD_CLIENTE_AL_FIADO
    WHERE (p_texto IS NULL OR ALIAS LIKE CONCAT('%', p_texto, '%'))
    ORDER BY CLIENTE_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_DETALLE_DEVOLUCION` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_DETALLE_DEVOLUCION`(
	IN p_id_devolucion INT,
	IN p_id_producto INT,
	IN p_razon_devolucion VARCHAR(30),
	IN p_fecha DATE
)
BEGIN
    SELECT 
		-- BOD_DETALLE_DEVOLUCION
		dd.CANTIDAD,
        dd.SUBTOTAL,
		-- BOD_PRODUCTO
		p.PRODUCTO_ID,
        p.NOMBRE,
        p.PRECIO_UNITARIO,
        p.UNIDAD_MEDIDA,
		p.STOCK,
		p.STOCK_MINIMO,
		p.ACTIVO,
		p.CATEGORIA_ID,
        -- BOD_RAZON_DEVOLUCION
        r.RAZON_DEVOLUCION_ID,
        r.DESCRIPCION,
		-- BOD_DEVOLUCION
		d.DEVOLUCION_ID,
        d.TOTAL,
        d.FECHA
    FROM BOD_DETALLE_DEVOLUCION dd
    JOIN BOD_PRODUCTO p ON dd.PRODUCTO_ID = p.PRODUCTO_ID
    JOIN BOD_RAZON_DEVOLUCION r ON dd.RAZON_DEVOLUCION_ID = r.RAZON_DEVOLUCION_ID
    JOIN BOD_DEVOLUCION d ON dd.DEVOLUCION_ID = d.DEVOLUCION_ID
	WHERE (p_id_devolucion IS NULL OR dd.DEVOLUCION_ID = p_id_devolucion)
	  AND (p_id_producto IS NULL OR dd.PRODUCTO_ID = p_id_producto)
	  AND (p_razon_devolucion IS NULL OR r.DESCRIPCION = p_razon_devolucion)
	  AND (p_fecha IS NULL OR DATE(d.FECHA) = p_fecha)
	  AND (p.ACTIVO = 1);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_DETALLE_VENTA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_DETALLE_VENTA`(
	IN p_id_venta INT,
	IN p_id_producto INT,
	IN p_fecha DATE
)
BEGIN
    SELECT 
		-- Detalle Venta
        dv.CANTIDAD,
        dv.SUBTOTAL,
        -- Datos de la venta
		v.VENTA_ID,
        v.TOTAL,
        v.METODO_PAGO,
        v.FECHA,
        -- Datos del producto
		p.PRODUCTO_ID,
        p.NOMBRE,
        p.PRECIO_UNITARIO,
        p.UNIDAD_MEDIDA,
        p.STOCK,
        p.STOCK_MINIMO,
        p.ACTIVO,
		p.CATEGORIA_ID
    FROM BOD_DETALLE_VENTA dv
    JOIN BOD_VENTAS v ON dv.VENTA_ID = v.VENTA_ID
    JOIN BOD_PRODUCTO p ON dv.PRODUCTO_ID = p.PRODUCTO_ID
	WHERE (p_id_venta IS NULL OR dv.VENTA_ID = p_id_venta)
	  AND (p_id_producto IS NULL OR dv.PRODUCTO_ID = p_id_producto)
	  AND (p_fecha IS NULL OR DATE(v.FECHA) = p_fecha)
	  AND (p.ACTIVO = 1)
    ORDER BY dv.VENTA_ID, dv.PRODUCTO_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_DEVOLUCION` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_DEVOLUCION`(
    IN p_id_devolucion INT,
    IN p_id_usuario INT,
    IN p_fecha_devolucion DATE
)
BEGIN
    SELECT 
        d.DEVOLUCION_ID,
        d.TOTAL,
        d.FECHA,
        u.USUARIO_ID,
        u.USUARIO,
        u.TIPO_USUARIOS,
        u.CORREO,
        u.CONTRASENHA,
        u.NOMBRE_COMPLETO,
        u.TELEFONO_USUARIO,
        u.ACTIVO_USUARIO
    FROM BOD_DEVOLUCION d
    JOIN BOD_USUARIO u ON d.USUARIO_ID = u.USUARIO_ID
    WHERE (p_id_devolucion IS NULL OR d.DEVOLUCION_ID = p_id_devolucion)
      AND (p_id_usuario IS NULL OR u.USUARIO_ID = p_id_usuario)
      AND (p_fecha_devolucion IS NULL OR DATE(d.FECHA) = p_fecha_devolucion);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_HISTORIAL_OPERACIONES` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_HISTORIAL_OPERACIONES`(
    IN p_id_operacion INT,
    IN p_nombre_tabla VARCHAR(60),
    IN p_operacion VARCHAR(20),
    IN p_fecha_operacion DATE,
    IN p_id_usuario INT,
    IN p_nombre_usuario VARCHAR(30),
    IN p_tipo_usuario VARCHAR(20),
    IN p_estado_usuario TINYINT
)
BEGIN
    SELECT 
        h.OPERACION_ID,
        h.TABLA_AFECTADA,
        h.FECHA_HORA,
        h.OPERACION,
        u.USUARIO_ID,
        u.USUARIO,
        u.TIPO_USUARIOS,
        u.CORREO,
        u.CONTRASENHA,
        u.NOMBRE_COMPLETO,
        u.TELEFONO_USUARIO,
        u.ACTIVO_USUARIO
    FROM BOD_HISTORIAL_OPERACIONES h
    JOIN BOD_USUARIO u ON h.USUARIO_ID = u.USUARIO_ID
    WHERE (p_id_operacion IS NULL OR h.OPERACION_ID = p_id_operacion)
      AND (p_nombre_tabla IS NULL OR h.TABLA_AFECTADA = p_nombre_tabla)
      AND (p_operacion IS NULL OR h.OPERACION = p_operacion)
      AND (p_fecha_operacion IS NULL OR DATE(h.FECHA_HORA) = p_fecha_operacion)
      AND (p_id_usuario IS NULL OR u.USUARIO_ID = p_id_usuario)
      AND (p_nombre_usuario IS NULL OR u.USUARIO = p_nombre_usuario)
      AND (p_tipo_usuario IS NULL OR u.TIPO_USUARIOS = p_tipo_usuario)
      AND (p_estado_usuario IS NULL OR u.ACTIVO_USUARIO = p_estado_usuario)
    ORDER BY h.FECHA_HORA DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_PRODUCTOS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_PRODUCTOS`(
    IN p_activo TINYINT,             -- 1 = activos, 0 = inactivos, NULL = todos
    IN p_categoria VARCHAR(60),      -- nombre exacto de la categoría, NULL = todas
    IN p_nombre_producto VARCHAR(45) -- nombre parcial del producto, NULL = todos
)
BEGIN
    SELECT 
        p.PRODUCTO_ID,
        p.NOMBRE,
        p.PRECIO_UNITARIO,
        p.UNIDAD_MEDIDA,
        p.STOCK,
        p.STOCK_MINIMO,
        p.ACTIVO,
        p.CATEGORIA_ID
    FROM BOD_PRODUCTO p
    WHERE 
        (p_activo IS NULL OR p.ACTIVO = p_activo)
        -- CAMBIO CLAVE: Se usa "=" en lugar de LIKE CONCAT('%', p_categoria, '%')
        AND (p_categoria IS NULL OR p.CATEGORIA_ID IN (
                SELECT c.CATEGORIA_ID
                FROM BOD_CATEGORIA c
                WHERE c.DESCRIPCION = p_categoria 
            ))
        AND (p_nombre_producto IS NULL OR p.NOMBRE LIKE CONCAT('%', p_nombre_producto, '%'))
    ORDER BY p.PRODUCTO_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_RAZONES_DEVOLUCION` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_RAZONES_DEVOLUCION`(
    IN p_descripcion VARCHAR(100) -- texto parcial o NULL para listar todas
)
BEGIN
    SELECT 
		r.RAZON_DEVOLUCION_ID,
        r.DESCRIPCION
    FROM BOD_RAZON_DEVOLUCION r
    WHERE 
        (p_descripcion IS NULL OR r.DESCRIPCION LIKE CONCAT('%', p_descripcion, '%'))
    ORDER BY r.DESCRIPCION;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS`(
    IN p_alias_cliente VARCHAR(40),
    IN p_fecha DATE
)
BEGIN
    SELECT
        r.PAGO_ID,
        r.FECHA,
        r.METODO_PAGO,
        r.MONTO,
        c.CLIENTE_ID,
        c.ALIAS,
        c.NOMBRE,
        c.TELEFONO,
        c.FECHA_DE_PAGO,
        c.ACTIVO,
        c.MONTO_DEUDA,
        u.USUARIO_ID,
        u.USUARIO,
        u.TIPO_USUARIOS,
        u.CORREO,
        u.CONTRASENHA,
        u.NOMBRE_COMPLETO,
        u.TELEFONO_USUARIO,
        u.ACTIVO_USUARIO
    FROM BOD_REGISTRO_PAGOS_FIADOS r
    INNER JOIN BOD_CLIENTE_AL_FIADO c ON r.CLIENTE_ID = c.CLIENTE_ID
    INNER JOIN BOD_USUARIO u ON r.USUARIO_ID = u.USUARIO_ID
    WHERE 
        (p_alias_cliente IS NULL OR c.ALIAS LIKE CONCAT('%', p_alias_cliente, '%'))
        AND (p_fecha IS NULL OR r.FECHA = p_fecha)
    ORDER BY r.FECHA DESC, r.PAGO_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_USUARIOS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_USUARIOS`(
    IN p_nombre_usuario VARCHAR(30),
    IN p_correo VARCHAR(45),
    IN p_activo TINYINT
)
BEGIN
    SELECT 
        USUARIO_ID,
        USUARIO,
        TIPO_USUARIOS,
        CORREO,
        CONTRASENHA,
        NOMBRE_COMPLETO,
        TELEFONO_USUARIO,
        ACTIVO_USUARIO
    FROM BOD_USUARIO
    WHERE 
        (p_nombre_usuario IS NULL OR USUARIO LIKE CONCAT('%', p_nombre_usuario, '%'))
        AND (p_correo IS NULL OR CORREO LIKE CONCAT('%', p_correo, '%'))
        AND (p_activo IS NULL OR ACTIVO_USUARIO = p_activo)
    ORDER BY USUARIO_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_VENTAS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_VENTAS`(
    IN p_venta_id INT,
    IN p_fecha DATE
)
BEGIN
    SELECT 
        v.VENTA_ID,
        v.TOTAL,
        v.METODO_PAGO,
        v.FECHA,
        u.USUARIO_ID,
        u.USUARIO,
        u.TIPO_USUARIOS,
        u.CORREO,
        u.CONTRASENHA,
        u.NOMBRE_COMPLETO,
        u.TELEFONO_USUARIO,
        u.ACTIVO_USUARIO
    FROM BOD_VENTAS v
    JOIN BOD_USUARIO u ON v.USUARIO_ID = u.USUARIO_ID
    WHERE 
        (p_venta_id IS NULL OR v.VENTA_ID = p_venta_id)
        AND (p_fecha IS NULL OR DATE(v.FECHA) = p_fecha)
    ORDER BY v.VENTA_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_LISTAR_VENTAS_AL_FIADO` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`admin`@`%` PROCEDURE `SP_LISTAR_VENTAS_AL_FIADO`(
    IN p_alias_cliente VARCHAR(40),
    IN p_venta_fiada_id INT,
    IN p_fecha DATE
)
BEGIN
    SELECT
        vf.VENTA_FIADA_ID,
        v.VENTA_ID,
        v.TOTAL,
        v.METODO_PAGO,
        v.FECHA,
        c.CLIENTE_ID,
        c.ALIAS,
        c.NOMBRE,
        c.TELEFONO,
        c.FECHA_DE_PAGO,
        c.ACTIVO,
        c.MONTO_DEUDA,
        u.USUARIO_ID,
        u.USUARIO,
        u.TIPO_USUARIOS,
        u.CORREO,
        u.CONTRASENHA,
        u.NOMBRE_COMPLETO,
        u.TELEFONO_USUARIO,
        u.ACTIVO_USUARIO
    FROM BOD_VENTAS_FIADAS vf
    INNER JOIN BOD_VENTAS v ON vf.VENTA_ID = v.VENTA_ID
    INNER JOIN BOD_CLIENTE_AL_FIADO c ON vf.CLIENTE_ID = c.CLIENTE_ID
    INNER JOIN BOD_USUARIO u ON v.USUARIO_ID = u.USUARIO_ID
    WHERE 
        (p_alias_cliente IS NULL OR c.ALIAS LIKE CONCAT('%', p_alias_cliente, '%'))
        AND (p_venta_fiada_id IS NULL OR vf.VENTA_FIADA_ID = p_venta_fiada_id)
        AND (p_fecha IS NULL OR DATE(v.FECHA) = p_fecha)
    ORDER BY v.FECHA DESC, vf.VENTA_FIADA_ID;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-09  1:54:48
