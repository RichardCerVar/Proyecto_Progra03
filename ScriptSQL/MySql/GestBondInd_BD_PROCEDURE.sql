-- ============================================
-- Script de Stored Procedures
-- Base de Datos: Gestión de Bodega
-- ============================================

-- Usar el esquema
USE TA_PROG3;

-- ============================================
-- STORED PROCEDURES
-- ============================================

-- Procedimiento: SP_LISTAR_CLIENTE_AL_FIADO
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_CLIENTE_AL_FIADO`$$
CREATE PROCEDURE `SP_LISTAR_CLIENTE_AL_FIADO`(
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
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_DETALLE_DEVOLUCION
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_DETALLE_DEVOLUCION`$$
CREATE PROCEDURE `SP_LISTAR_DETALLE_DEVOLUCION`(
    IN p_id_devolucion INT,
    IN p_id_producto INT,
    IN p_razon_devolucion VARCHAR(30),
    IN p_fecha DATE
)
BEGIN
    SELECT 
        dd.CANTIDAD,
        dd.SUBTOTAL,
        p.PRODUCTO_ID,
        p.NOMBRE,
        p.PRECIO_UNITARIO,
        p.UNIDAD_MEDIDA,
        p.STOCK,
        p.STOCK_MINIMO,
        p.ACTIVO,
        p.CATEGORIA_ID,
        r.RAZON_DEVOLUCION_ID,
        r.DESCRIPCION,
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
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_DETALLE_VENTA
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_DETALLE_VENTA`$$
CREATE PROCEDURE `SP_LISTAR_DETALLE_VENTA`(
    IN p_id_venta INT,
    IN p_id_producto INT,
    IN p_fecha DATE
)
BEGIN
    SELECT 
        dv.CANTIDAD,
        dv.SUBTOTAL,
        v.VENTA_ID,
        v.TOTAL,
        v.METODO_PAGO,
        v.FECHA,
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
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_DEVOLUCION
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_DEVOLUCION`$$
CREATE PROCEDURE `SP_LISTAR_DEVOLUCION`(
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
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_HISTORIAL_OPERACIONES
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_HISTORIAL_OPERACIONES`$$
CREATE PROCEDURE `SP_LISTAR_HISTORIAL_OPERACIONES`(
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
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_PRODUCTOS
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_PRODUCTOS`$$
CREATE PROCEDURE `SP_LISTAR_PRODUCTOS`(
    IN p_activo TINYINT,
    IN p_categoria VARCHAR(60),
    IN p_nombre_producto VARCHAR(45)
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
        p.CATEGORIA_ID,
        c.DESCRIPCION 
    FROM BOD_PRODUCTO p
    LEFT JOIN BOD_CATEGORIA c ON p.CATEGORIA_ID = c.CATEGORIA_ID
    WHERE 
        (p_activo IS NULL OR p.ACTIVO = p_activo)
        AND (p_categoria IS NULL OR c.DESCRIPCION = p_categoria)
        AND (p_nombre_producto IS NULL OR p.NOMBRE LIKE CONCAT('%', p_nombre_producto, '%'))
    ORDER BY p.PRODUCTO_ID;
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_RAZONES_DEVOLUCION
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_RAZONES_DEVOLUCION`$$
CREATE PROCEDURE `SP_LISTAR_RAZONES_DEVOLUCION`(
    IN p_descripcion VARCHAR(100)
)
BEGIN
    SELECT 
        r.RAZON_DEVOLUCION_ID,
        r.DESCRIPCION
    FROM BOD_RAZON_DEVOLUCION r
    WHERE 
        (p_descripcion IS NULL OR r.DESCRIPCION LIKE CONCAT('%', p_descripcion, '%'))
    ORDER BY r.DESCRIPCION;
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS`$$
CREATE PROCEDURE `SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS`(
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
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_USUARIOS
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_USUARIOS`$$
CREATE PROCEDURE `SP_LISTAR_USUARIOS`(
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
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_VENTAS
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_VENTAS`$$
CREATE PROCEDURE `SP_LISTAR_VENTAS`(
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
END$$
DELIMITER ;

-- Procedimiento: SP_LISTAR_VENTAS_AL_FIADO
DELIMITER $$
DROP PROCEDURE IF EXISTS `SP_LISTAR_VENTAS_AL_FIADO`$$
CREATE PROCEDURE `SP_LISTAR_VENTAS_AL_FIADO`(
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
END$$
DELIMITER ;

-- ============================================
-- FIN DEL SCRIPT DE STORED PROCEDURES
-- ============================================