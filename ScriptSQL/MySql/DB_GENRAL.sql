-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: db-ta-prog3.czckeyy6ic4v.us-east-1.rds.amazonaws.com    Database: TA_PROG3
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_CATEGORIA`
--

LOCK TABLES `BOD_CATEGORIA` WRITE;
/*!40000 ALTER TABLE `BOD_CATEGORIA` DISABLE KEYS */;
INSERT INTO `BOD_CATEGORIA` VALUES (18,'Abarrotes'),(23,'BEBIDAS');
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
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_CLIENTE_AL_FIADO`
--

LOCK TABLES `BOD_CLIENTE_AL_FIADO` WRITE;
/*!40000 ALTER TABLE `BOD_CLIENTE_AL_FIADO` DISABLE KEYS */;
INSERT INTO `BOD_CLIENTE_AL_FIADO` VALUES (264,'Alexito Pro (Actualizado)','Juan Carlos Pérez García','912345678','1916-04-22',1,0.00),(265,'Cj','JACKSON','12345','2022-02-04',1,0.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_DEVOLUCION`
--

LOCK TABLES `BOD_DEVOLUCION` WRITE;
/*!40000 ALTER TABLE `BOD_DEVOLUCION` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_HISTORIAL_OPERACIONES`
--

LOCK TABLES `BOD_HISTORIAL_OPERACIONES` WRITE;
/*!40000 ALTER TABLE `BOD_HISTORIAL_OPERACIONES` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_PRODUCTO`
--

LOCK TABLES `BOD_PRODUCTO` WRITE;
/*!40000 ALTER TABLE `BOD_PRODUCTO` DISABLE KEYS */;
INSERT INTO `BOD_PRODUCTO` VALUES (17,18,'Arroz Costeño',5.50,'KILOGRAMO',100,20,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_RAZON_DEVOLUCION`
--

LOCK TABLES `BOD_RAZON_DEVOLUCION` WRITE;
/*!40000 ALTER TABLE `BOD_RAZON_DEVOLUCION` DISABLE KEYS */;
INSERT INTO `BOD_RAZON_DEVOLUCION` VALUES (134,'Producto Vencido');
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
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_REGISTRO_PAGOS_FIADOS`
--

LOCK TABLES `BOD_REGISTRO_PAGOS_FIADOS` WRITE;
/*!40000 ALTER TABLE `BOD_REGISTRO_PAGOS_FIADOS` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_USUARIO`
--

LOCK TABLES `BOD_USUARIO` WRITE;
/*!40000 ALTER TABLE `BOD_USUARIO` DISABLE KEYS */;
INSERT INTO `BOD_USUARIO` VALUES (308,'CHICHICO REFORMED','OPERARIO','Holacomoestas@outlook.com','jjdajsdjasd123','Jose Carlos','955882323',1),(309,'Chichico EL PRIMERO','OPERARIO','dasdjasd@gmai.com','jjdajsdjasd123','Jose Carlos','987654321',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_VENTAS`
--

LOCK TABLES `BOD_VENTAS` WRITE;
/*!40000 ALTER TABLE `BOD_VENTAS` DISABLE KEYS */;
INSERT INTO `BOD_VENTAS` VALUES (42,40.23,'TRANSFERENCIA','1919-12-12',308),(43,40.23,'TRANSFERENCIA','1919-12-12',308),(44,40.23,'TRANSFERENCIA','1919-12-12',308),(45,150.50,'EFECTIVO','1919-09-12',308),(46,150.50,'EFECTIVO','1919-09-12',308),(47,150.50,'EFECTIVO','1919-09-12',308),(48,200.50,'EFECTIVO','1919-09-12',308),(49,300.50,'EFECTIVO','1919-09-12',308),(50,120.50,'EFECTIVO','1919-09-12',308);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOD_VENTAS_FIADAS`
--

LOCK TABLES `BOD_VENTAS_FIADAS` WRITE;
/*!40000 ALTER TABLE `BOD_VENTAS_FIADAS` DISABLE KEYS */;
INSERT INTO `BOD_VENTAS_FIADAS` VALUES (1,47,264),(2,48,264),(3,49,264),(4,50,265);
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
        ACTIVO
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
        p.ACTIVO
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
    IN p_categoria VARCHAR(60),      -- nombre parcial de la categoría, NULL = todas
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
        p.ACTIVO
    FROM BOD_PRODUCTO p
    WHERE 
        (p_activo IS NULL OR p.ACTIVO = p_activo)
        AND (p_categoria IS NULL OR p.CATEGORIA_ID IN (
                SELECT c.CATEGORIA_ID
                FROM BOD_CATEGORIA c
                WHERE c.DESCRIPCION LIKE CONCAT('%', p_categoria, '%')
            ))
        AND (p_nombre_producto IS NULL OR p.NOMBRE LIKE CONCAT('%', p_nombre_producto, '%'))
    ORDER BY p.PRODUCTO_ID;
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
    IN p_venta_id INT
)
BEGIN
    SELECT 
        v.VENTA_ID,
        v.TOTAL,
        v.METODO_PAGO,
        v.FECHA
    FROM BOD_VENTAS v
    WHERE p_venta_id IS NULL OR v.VENTA_ID = p_venta_id
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
    IN p_venta_fiada_id INT
)
BEGIN
    SELECT
        vf.VENTA_FIADA_ID,
        v.VENTA_ID,
        v.TOTAL,
        v.METODO_PAGO,
        v.FECHA
    FROM BOD_VENTAS_FIADAS vf
    INNER JOIN BOD_VENTAS v ON vf.VENTA_ID = v.VENTA_ID
    INNER JOIN BOD_CLIENTE_AL_FIADO c ON vf.CLIENTE_ID = c.CLIENTE_ID
    WHERE 
        (p_alias_cliente IS NULL OR c.ALIAS LIKE CONCAT('%', p_alias_cliente, '%'))
        AND (p_venta_fiada_id IS NULL OR vf.VENTA_FIADA_ID = p_venta_fiada_id)
    ORDER BY v.FECHA DESC;
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

-- Dump completed on 2025-10-06 23:16:33
