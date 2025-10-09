--Triggers:
--PARA ACTUALIZAR EL SALDO DEL CLIENTE AL FIADO.
USE TA_PROG3;

DELIMITER $$

CREATE DEFINER=`admin`@`%` TRIGGER TRG_AFTER_INSERT_VENTA_FIADA
AFTER INSERT ON BOD_VENTAS_FIADAS
FOR EACH ROW
BEGIN
    DECLARE v_total DECIMAL(10,2);
    
    SELECT TOTAL INTO v_total
    FROM BOD_VENTAS
    WHERE VENTA_ID = NEW.VENTA_ID;
    
    UPDATE BOD_CLIENTE_AL_FIADO
    SET MONTO_DEUDA = MONTO_DEUDA + v_total
    WHERE CLIENTE_ID = NEW.CLIENTE_ID;
END$$



--TRIGGER QUE ACTUALIZA EL MONTO DE DEUDA DE UN CLIENTE TRAS EL PAGO PARCIAL O TOTAL DE SU DEUDA. TAMBIEN VALIDA QUE EL MONTO SEA MENOR O IGUAL A LA DEUDA.
DELIMITER $$

CREATE TRIGGER TRG_BEFORE_INSERT_PAGO_FIADO
BEFORE INSERT ON BOD_REGISTRO_PAGOS_FIADOS
FOR EACH ROW
BEGIN
    DECLARE v_deuda_actual DECIMAL(10,2);
    
    SELECT MONTO_DEUDA INTO v_deuda_actual
    FROM BOD_CLIENTE_AL_FIADO
    WHERE CLIENTE_ID = NEW.CLIENTE_ID;
    
    IF NEW.MONTO > v_deuda_actual THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El monto del pago no puede ser mayor a la deuda actual del cliente';
    END IF;
    
    UPDATE BOD_CLIENTE_AL_FIADO
    SET MONTO_DEUDA = MONTO_DEUDA - NEW.MONTO
    WHERE CLIENTE_ID = NEW.CLIENTE_ID;
END$$


-- =====================================================
-- 1. TRIGGER: Control de Stock en Ventas
-- =====================================================
CREATE TRIGGER TRG_BEFORE_INSERT_DETALLE_VENTA
BEFORE INSERT ON BOD_DETALLE_VENTA
FOR EACH ROW
BEGIN
    DECLARE v_stock_actual INT;
    DECLARE v_nombre_producto VARCHAR(45);
    
    SELECT STOCK, NOMBRE INTO v_stock_actual, v_nombre_producto
    FROM BOD_PRODUCTO
    WHERE PRODUCTO_ID = NEW.PRODUCTO_ID AND ACTIVO = 1;
    
    IF v_stock_actual IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El producto no existe o está inactivo';
    END IF;
    
    IF NEW.CANTIDAD > v_stock_actual THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Stock insuficiente para realizar la venta';
    END IF;
    
    UPDATE BOD_PRODUCTO
    SET STOCK = STOCK - NEW.CANTIDAD
    WHERE PRODUCTO_ID = NEW.PRODUCTO_ID;
END$$

-- =====================================================
-- 2. TRIGGER: Incrementar Stock en Devoluciones
-- =====================================================
CREATE TRIGGER TRG_AFTER_INSERT_DETALLE_DEVOLUCION
AFTER INSERT ON BOD_DETALLE_DEVOLUCION
FOR EACH ROW
BEGIN
    DECLARE v_venta_id INT;
    
    SELECT VENTA_ID INTO v_venta_id
    FROM BOD_DETALLE_VENTA
    WHERE PRODUCTO_ID = NEW.PRODUCTO_ID
    LIMIT 1;
    
    UPDATE BOD_PRODUCTO
    SET STOCK = STOCK + NEW.CANTIDAD
    WHERE PRODUCTO_ID = NEW.PRODUCTO_ID;
    
    DELETE FROM BOD_DETALLE_VENTA
    WHERE PRODUCTO_ID = NEW.PRODUCTO_ID
    AND VENTA_ID = v_venta_id;
END$$

-- =====================================================
-- 3. TRIGGER: Validar Cliente Activo en Ventas Fiadas
-- =====================================================
CREATE TRIGGER TRG_BEFORE_INSERT_VENTA_FIADA
BEFORE INSERT ON BOD_VENTAS_FIADAS
FOR EACH ROW
BEGIN
    DECLARE v_cliente_activo TINYINT;
    DECLARE v_alias_cliente VARCHAR(40);
    
    SELECT ACTIVO, ALIAS INTO v_cliente_activo, v_alias_cliente
    FROM BOD_CLIENTE_AL_FIADO
    WHERE CLIENTE_ID = NEW.CLIENTE_ID;
    
    IF v_cliente_activo IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El cliente no existe';
    END IF;
    
    IF v_cliente_activo = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: No se puede realizar venta fiada a un cliente inactivo';
    END IF;
END$$

DELIMITER ;
