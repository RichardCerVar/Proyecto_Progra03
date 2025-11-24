USE TA_PROG3;
GO

IF OBJECT_ID('SP_LISTAR_CLIENTE_AL_FIADO', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_CLIENTE_AL_FIADO;
GO
CREATE PROCEDURE SP_LISTAR_CLIENTE_AL_FIADO
    @p_texto VARCHAR(30) = NULL
AS
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
    WHERE (@p_texto IS NULL OR ALIAS LIKE '%' + @p_texto + '%')
    ORDER BY CLIENTE_ID;
END;
GO

IF OBJECT_ID('SP_LISTAR_DETALLE_DEVOLUCION', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_DETALLE_DEVOLUCION;
GO
CREATE PROCEDURE SP_LISTAR_DETALLE_DEVOLUCION
    @p_id_devolucion INT = NULL,
    @p_id_producto INT = NULL,
    @p_razon_devolucion VARCHAR(30) = NULL,
    @p_fecha DATE = NULL
AS
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
    WHERE (@p_id_devolucion IS NULL OR dd.DEVOLUCION_ID = @p_id_devolucion)
      AND (@p_id_producto IS NULL OR dd.PRODUCTO_ID = @p_id_producto)
      AND (@p_razon_devolucion IS NULL OR r.DESCRIPCION = @p_razon_devolucion)
      AND (@p_fecha IS NULL OR CAST(d.FECHA AS DATE) = @p_fecha)
      AND (p.ACTIVO = 1);
END;
GO

IF OBJECT_ID('SP_LISTAR_DETALLE_VENTA', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_DETALLE_VENTA;
GO
CREATE PROCEDURE SP_LISTAR_DETALLE_VENTA
    @p_id_venta INT = NULL,
    @p_id_producto INT = NULL,
    @p_fecha DATE = NULL
AS
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
    WHERE (@p_id_venta IS NULL OR dv.VENTA_ID = @p_id_venta)
      AND (@p_id_producto IS NULL OR dv.PRODUCTO_ID = @p_id_producto)
      AND (@p_fecha IS NULL OR CAST(v.FECHA AS DATE) = @p_fecha)
      AND (p.ACTIVO = 1)
    ORDER BY dv.VENTA_ID, dv.PRODUCTO_ID;
END;
GO

IF OBJECT_ID('SP_LISTAR_DEVOLUCION', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_DEVOLUCION;
GO
CREATE PROCEDURE SP_LISTAR_DEVOLUCION
    @p_id_devolucion INT = NULL,
    @p_id_usuario INT = NULL,
    @p_fecha_devolucion DATE = NULL
AS
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
    WHERE (@p_id_devolucion IS NULL OR d.DEVOLUCION_ID = @p_id_devolucion)
      AND (@p_id_usuario IS NULL OR u.USUARIO_ID = @p_id_usuario)
      AND (@p_fecha_devolucion IS NULL OR CAST(d.FECHA AS DATE) = @p_fecha_devolucion);
END;
GO

IF OBJECT_ID('SP_LISTAR_HISTORIAL_OPERACIONES', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_HISTORIAL_OPERACIONES;
GO
CREATE PROCEDURE SP_LISTAR_HISTORIAL_OPERACIONES
    @p_id_operacion INT = NULL,
    @p_nombre_tabla VARCHAR(60) = NULL,
    @p_operacion VARCHAR(20) = NULL,
    @p_fecha_operacion DATE = NULL,
    @p_id_usuario INT = NULL,
    @p_nombre_usuario VARCHAR(30) = NULL,
    @p_tipo_usuario VARCHAR(20) = NULL,
    @p_estado_usuario TINYINT = NULL
AS
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
    WHERE (@p_id_operacion IS NULL OR h.OPERACION_ID = @p_id_operacion)
      AND (@p_nombre_tabla IS NULL OR h.TABLA_AFECTADA = @p_nombre_tabla)
      AND (@p_operacion IS NULL OR h.OPERACION = @p_operacion)
      AND (@p_fecha_operacion IS NULL OR CAST(h.FECHA_HORA AS DATE) = @p_fecha_operacion)
      AND (@p_id_usuario IS NULL OR u.USUARIO_ID = @p_id_usuario)
      AND (@p_nombre_usuario IS NULL OR u.USUARIO = @p_nombre_usuario)
      AND (@p_tipo_usuario IS NULL OR u.TIPO_USUARIOS = @p_tipo_usuario)
      AND (@p_estado_usuario IS NULL OR u.ACTIVO_USUARIO = @p_estado_usuario)
    ORDER BY h.FECHA_HORA DESC;
END;
GO

IF OBJECT_ID('SP_LISTAR_PRODUCTOS', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_PRODUCTOS;
GO
CREATE PROCEDURE SP_LISTAR_PRODUCTOS
    @p_activo TINYINT = NULL,
    @p_categoria VARCHAR(60) = NULL,
    @p_nombre_producto VARCHAR(45) = NULL
AS
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
        (@p_activo IS NULL OR p.ACTIVO = @p_activo)
        AND (@p_categoria IS NULL OR @p_categoria = '' OR c.DESCRIPCION = @p_categoria)
        AND (@p_nombre_producto IS NULL OR @p_nombre_producto = '' 
             OR p.NOMBRE LIKE '%' + @p_nombre_producto + '%')
    ORDER BY p.PRODUCTO_ID;
END;
GO

IF OBJECT_ID('SP_LISTAR_RAZONES_DEVOLUCION', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_RAZONES_DEVOLUCION;
GO
CREATE PROCEDURE SP_LISTAR_RAZONES_DEVOLUCION
    @p_descripcion VARCHAR(100) = NULL
AS
BEGIN
    SELECT 
        r.RAZON_DEVOLUCION_ID,
        r.DESCRIPCION
    FROM BOD_RAZON_DEVOLUCION r
    WHERE 
        (@p_descripcion IS NULL OR r.DESCRIPCION LIKE '%' + @p_descripcion + '%')
    ORDER BY r.DESCRIPCION;
END;
GO

IF OBJECT_ID('SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS;
GO
CREATE PROCEDURE SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS
    @p_alias_cliente VARCHAR(40) = NULL,
    @p_fecha DATE = NULL
AS
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
        (@p_alias_cliente IS NULL OR c.ALIAS LIKE '%' + @p_alias_cliente + '%')
        AND (@p_fecha IS NULL OR CAST(r.FECHA AS DATE)= @p_fecha)
    ORDER BY r.FECHA DESC, r.PAGO_ID;
END;
GO

IF OBJECT_ID('SP_LISTAR_USUARIOS', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_USUARIOS;
GO
CREATE PROCEDURE SP_LISTAR_USUARIOS
    @p_nombre_usuario VARCHAR(30) = NULL,
    @p_correo VARCHAR(45) = NULL,
    @p_activo TINYINT = NULL
AS
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
        (@p_nombre_usuario IS NULL OR USUARIO LIKE '%' + @p_nombre_usuario + '%')
        AND (@p_correo IS NULL OR CORREO LIKE '%' + @p_correo + '%')
        AND (@p_activo IS NULL OR ACTIVO_USUARIO = @p_activo)
    ORDER BY USUARIO_ID;
END;
GO

IF OBJECT_ID('SP_LISTAR_VENTAS', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_VENTAS;
GO
CREATE PROCEDURE SP_LISTAR_VENTAS
    @p_venta_id INT = NULL,
    @p_fecha DATE = NULL
AS
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
        (@p_venta_id IS NULL OR v.VENTA_ID = @p_venta_id)
        AND (@p_fecha IS NULL OR CAST(v.FECHA AS DATE) = @p_fecha)
    ORDER BY v.VENTA_ID;
END;
GO

IF OBJECT_ID('SP_LISTAR_VENTAS_AL_FIADO', 'P') IS NOT NULL DROP PROCEDURE SP_LISTAR_VENTAS_AL_FIADO;
GO
CREATE PROCEDURE SP_LISTAR_VENTAS_AL_FIADO
    @p_alias_cliente VARCHAR(40) = NULL,
    @p_venta_fiada_id INT = NULL,
    @p_fecha DATE = NULL
AS
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
        (@p_alias_cliente IS NULL OR c.ALIAS LIKE '%' + @p_alias_cliente + '%')
        AND (@p_venta_fiada_id IS NULL OR vf.VENTA_FIADA_ID = @p_venta_fiada_id)
        AND (@p_fecha IS NULL OR CAST(v.FECHA AS DATE) = @p_fecha)
    ORDER BY v.FECHA DESC, vf.VENTA_FIADA_ID;
END;
GO