USE TA_PROG3;
SET FOREIGN_KEY_CHECKS = 0;

-- ===========================
-- CATEGORÍAS
-- ===========================
INSERT INTO BOD_CATEGORIA (DESCRIPCION)
VALUES 
('Bebidas'),
('Snacks'),
('Lácteos'),
('Limpieza'),
('Granos');

-- ===========================
-- PRODUCTOS
-- ===========================
INSERT INTO BOD_PRODUCTO (CATEGORIA_ID, NOMBRE, PRECIO_UNITARIO, UNIDAD_MEDIDA, STOCK, STOCK_MINIMO, ACTIVO)
VALUES
(1, 'Agua Mineral 600ml', 1.50, 'UNIDAD', 200, 20, 1),
(1, 'Jugo de Naranja 1L', 3.80, 'UNIDAD', 150, 15, 1),
(2, 'Papas Fritas 50g', 2.20, 'UNIDAD', 180, 20, 1),
(2, 'Maní Salado 100g', 2.50, 'UNIDAD', 100, 10, 1),
(3, 'Leche Entera 1L', 3.10, 'UNIDAD', 120, 12, 1),
(3, 'Yogurt Fresa 1L', 4.50, 'UNIDAD', 80, 8, 1),
(4, 'Detergente 1L', 6.50, 'UNIDAD', 90, 10, 1),
(4, 'Suavizante 1L', 7.00, 'UNIDAD', 85, 10, 1),
(5, 'Arroz 1Kg', 4.00, 'KILOGRAMO', 150, 15, 1),
(5, 'Lentejas 1Kg', 4.20, 'KILOGRAMO', 140, 14, 1);

-- ===========================
-- USUARIOS
-- ===========================
INSERT INTO BOD_USUARIO (USUARIO, TIPO_USUARIOS, CORREO, CONTRASENHA, NOMBRE_COMPLETO, TELEFONO_USUARIO, ACTIVO_USUARIO)
VALUES
('admin', 'ADMINISTRADOR', 'admin@bodega.com', 'admin123', 'Administrador General', '987654321', 1),
('carlos', 'OPERARIO', 'carlos@bodega.com', 'pass123', 'Carlos Mendoza', '987111222', 1),
('laura', 'OPERARIO', 'laura@bodega.com', 'pass123', 'Laura Torres', '987333444', 1),
('sofia', 'OPERARIO', 'sofia@bodega.com', 'pass123', 'Sofía Ramírez', '987555666', 1),
('diego', 'OPERARIO', 'diego@bodega.com', 'pass123', 'Diego López', '987777888', 1);

-- ===========================
-- CLIENTES AL FIADO
-- ===========================
INSERT INTO BOD_CLIENTE_AL_FIADO (ALIAS, NOMBRE, TELEFONO, FECHA_DE_PAGO, ACTIVO, MONTO_DEUDA)
VALUES
('juanito', 'Juan Pérez', '999111222', '2025-11-30', 1, 40.00),
('maria', 'María López', '999333444', '2025-11-28', 1, 25.00),
('pedro', 'Pedro Torres', '999555666', '2025-11-25', 1, 0.00),
('carla', 'Carla Rivas', '999777888', '2025-12-05', 1, 10.00),
('lucas', 'Lucas García', '999999000', '2025-11-27', 1, 15.00),
('ana', 'Ana Torres', '999222333', '2025-12-01', 1, 0.00);

-- ===========================
-- RAZONES DE DEVOLUCIÓN
-- ===========================
INSERT INTO BOD_RAZON_DEVOLUCION (DESCRIPCION)
VALUES
('Producto en mal estado'),
('Error en la venta'),
('Cliente cambió de opinión'),
('Producto vencido'),
('Daño en el empaque');

-- ===========================
-- VENTAS
-- ===========================
INSERT INTO BOD_VENTAS (TOTAL, METODO_PAGO, FECHA, USUARIO_ID)
VALUES
(15.60, 'EFECTIVO', '2025-11-01', 2),
(10.50, 'TRANSFERENCIA', '2025-11-01', 3),
(25.30, 'EFECTIVO', '2025-11-02', 4),
(8.90, 'EFECTIVO', '2025-11-02', 5),
(12.00, 'TRANSFERENCIA', '2025-11-03', 2),
(18.70, 'EFECTIVO', '2025-11-03', 3),
(9.60, 'EFECTIVO', '2025-11-04', 4),
(14.20, 'TRANSFERENCIA', '2025-11-05', 5),
(11.80, 'EFECTIVO', '2025-11-05', 2),
(23.10, 'EFECTIVO', '2025-11-06', 3),
(16.00, 'TRANSFERENCIA', '2025-11-06', 4),
(9.40, 'EFECTIVO', '2025-11-07', 5),
(21.50, 'EFECTIVO', '2025-11-07', 2),
(18.00, 'TRANSFERENCIA', '2025-11-08', 3),
(24.90, 'EFECTIVO', '2025-11-08', 4),
(17.30, 'EFECTIVO', '2025-11-09', 5),
(12.70, 'TRANSFERENCIA', '2025-11-09', 2),
(20.00, 'EFECTIVO', '2025-11-10', 3),
(13.60, 'EFECTIVO', '2025-11-10', 4),
(19.90, 'TRANSFERENCIA', '2025-11-10', 5);

-- ===========================
-- DETALLES DE VENTAS
-- ===========================
INSERT INTO BOD_DETALLE_VENTA (VENTA_ID, PRODUCTO_ID, SUBTOTAL, CANTIDAD)
VALUES
(1, 1, 3.00, 2),
(1, 2, 7.60, 2),
(2, 3, 4.40, 2),
(3, 5, 9.30, 3),
(3, 6, 13.50, 3),
(4, 7, 6.50, 1),
(5, 4, 5.00, 2),
(6, 1, 7.50, 5),
(7, 8, 7.00, 1),
(8, 9, 8.00, 2),
(9, 10, 8.40, 2),
(10, 2, 7.60, 2),
(11, 5, 6.20, 2),
(12, 3, 4.40, 2),
(13, 6, 9.00, 2),
(14, 7, 6.50, 1),
(15, 8, 7.00, 1),
(16, 9, 8.00, 2),
(17, 10, 8.40, 2),
(18, 1, 3.00, 2),
(19, 9, 8.00, 2),   -- Arroz 1Kg (4.00 * 2 = 8.00)
(19, 5, 3.10, 1),   -- Leche 1L  (3.10 * 1 = 3.10)
(19, 4, 2.50, 1),   -- Maní 100g  (2.50 * 1 = 2.50)
(20, 6, 9.00, 2),   -- Yogurt Fresa 1L (4.50 * 2 = 9.00)
(20, 7, 6.50, 1),   -- Detergente 1L    (6.50 * 1 = 6.50)
(20, 3, 4.40, 2);   -- Papas Fritas 50g  (2.20 * 2 = 4.40)

-- ===========================
-- DEVOLUCIONES
-- ===========================
INSERT INTO BOD_DEVOLUCION (TOTAL, FECHA, USUARIO_ID)
VALUES
(7.60, '2025-11-03', 2),
(5.00, '2025-11-04', 3),
(9.30, '2025-11-05', 4),
(6.50, '2025-11-06', 5),
(4.40, '2025-11-07', 2),
(8.00, '2025-11-08', 3),
(7.00, '2025-11-09', 4),
(6.20, '2025-11-10', 5),
(4.00, '2025-11-10', 2),
(8.40, '2025-11-11', 3);

-- ===========================
-- DETALLES DE DEVOLUCIONES
-- ===========================
INSERT INTO BOD_DETALLE_DEVOLUCION (DEVOLUCION_ID, PRODUCTO_ID, SUBTOTAL, CANTIDAD, RAZON_DEVOLUCION_ID)
VALUES
(1, 2, 7.60, 2, 2),
(2, 4, 5.00, 2, 1),
(3, 5, 9.30, 3, 5),
(4, 7, 6.50, 1, 3),
(5, 3, 4.40, 2, 1),
(6, 9, 8.00, 2, 4),
(7, 8, 7.00, 1, 2),
(8, 5, 6.20, 2, 3),
(9, 1, 4.00, 2, 1),
(10, 10, 8.40, 2, 5);

-- ===========================
-- VENTAS FIADAS
-- ===========================
INSERT INTO BOD_VENTAS_FIADAS (VENTA_ID, CLIENTE_ID)
VALUES
(2, 1),
(4, 2),
(6, 3),
(9, 4),
(12, 5),
(14, 6);

-- ===========================
-- REGISTROS DE PAGOS FIADOS
-- ===========================
INSERT INTO BOD_REGISTRO_PAGOS_FIADOS (USUARIO_ID, CLIENTE_ID, FECHA, METODO_PAGO, MONTO)
VALUES
(2, 1, '2025-11-06', 'EFECTIVO', 10.00),
(3, 2, '2025-11-07', 'TRANSFERENCIA', 5.00),
(4, 3, '2025-11-08', 'EFECTIVO', 7.00),
(5, 4, '2025-11-09', 'EFECTIVO', 8.00),
(2, 5, '2025-11-10', 'TRANSFERENCIA', 6.00);

-- ===========================
-- HISTORIAL DE OPERACIONES
-- ===========================
INSERT INTO BOD_HISTORIAL_OPERACIONES (TABLA_AFECTADA, OPERACION, USUARIO_ID)
VALUES
('BOD_VENTAS', 'INSERCION', 2),
('BOD_VENTAS', 'INSERCION', 3),
('BOD_VENTAS', 'INSERCION', 4),
('BOD_DEVOLUCION', 'INSERCION', 5),
('BOD_PRODUCTO', 'MODIFICACION', 2),
('BOD_CLIENTE_AL_FIADO', 'CONSULTAR', 3),
('BOD_VENTAS_FIADAS', 'INSERCION', 4),
('BOD_REGISTRO_PAGOS_FIADOS', 'INSERCION', 5);

SET FOREIGN_KEY_CHECKS = 1;

SELECT '✅ Datos de prueba cargados exitosamente con coherencia y trazabilidad.' AS Resultado;
