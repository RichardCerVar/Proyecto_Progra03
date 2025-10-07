package pe.edu.pucp.softbod.daoImp.util;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.softbod.model.*;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;
import pe.edu.pucp.softbod.model.util.Tipo_Usuario;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;
import pe.edu.pucp.softbod.model.util.Unidad_Medida;


public class CargarTablas {
    
    public CategoriaDTO cargarCategoria(ResultSet resultSet) throws SQLException {
		
        CategoriaDTO categoria = new CategoriaDTO();
        categoria.setCategoriaId(resultSet.getInt("CATEGORIA_ID"));
        categoria.setDescripcion(resultSet.getString("DESCRIPCION"));
		
	return categoria;
    }
	
    public ClienteAlFiadoDTO cargarClienteAlFiado(ResultSet resultSet) throws SQLException {
		
        ClienteAlFiadoDTO clienteAlFiado = new ClienteAlFiadoDTO();
        clienteAlFiado.setClienteId(resultSet.getInt("CLIENTE_ID"));
        clienteAlFiado.setAlias(resultSet.getString("ALIAS"));
        clienteAlFiado.setNombre(resultSet.getString("NOMBRE"));
        clienteAlFiado.setTelefono(resultSet.getString("TELEFONO"));
        clienteAlFiado.setFechaDePago(resultSet.getDate("FECHA_DE_PAGO"));
        clienteAlFiado.setActivo(resultSet.getBoolean("ACTIVO"));
		
	return clienteAlFiado;
    }
	
    public DetalleDevolucionDTO cargarDetalleDevolucion(ResultSet resultSet) throws SQLException {
        
	DetalleDevolucionDTO linea = new DetalleDevolucionDTO();
	linea.setCantidad(resultSet.getInt("CANTIDAD"));
        linea.setSubtotal(resultSet.getDouble("SUBTOTAL"));
		
        DevolucionDTO devolucion = this.cargarDevolucionSinUsuario(resultSet);
        linea.setDevolucion(devolucion);
		
	ProductoDTO producto = this.cargarProductoSinCategoria(resultSet);
        linea.setProducto(producto);
        
	RazonDevolucionDTO razon = this.cargaRazonDevolucionDTO(resultSet);
        linea.setRazonDevolucion(razon);
		
	return linea;
    }
	
    public DevolucionDTO cargarDevolucionSinUsuario(ResultSet resultSet) throws SQLException {
		
        DevolucionDTO devolucion = new DevolucionDTO();
        devolucion.setDevolucionId(resultSet.getInt("DEVOLUCION_ID"));
        devolucion.setTotal(resultSet.getDouble("TOTAL"));
        devolucion.setFecha(resultSet.getDate("FECHA"));
        
        return devolucion;
    }
    
    public DevolucionDTO cargarDevolucion(ResultSet resultSet) throws SQLException {
		
        DevolucionDTO devolucion = new DevolucionDTO();
        devolucion.setDevolucionId(resultSet.getInt("DEVOLUCION_ID"));
        devolucion.setTotal(resultSet.getDouble("TOTAL"));
        devolucion.setFecha(resultSet.getDate("FECHA"));
		
        UsuarioDTO usuario = this.cargarUsuario(resultSet);
        devolucion.setUsuario(usuario);
        
        return devolucion;
    }
	
	
    public HistorialOperacionesDTO cargarHistorialOperaciones(ResultSet resultSet) throws SQLException {
		
        HistorialOperacionesDTO historial = new HistorialOperacionesDTO();
        historial.setOperacionId(resultSet.getInt("OPERACION_ID"));
        historial.setTablaAfectada(resultSet.getString("TABLA_AFECTADA"));
        historial.setFechaHora(resultSet.getDate("FECHA_HORA"));
        historial.setOperacion(Tipo_Operacion.valueOf(resultSet.getString("OPERACION")));
		
        UsuarioDTO usuario = this.cargarUsuario(resultSet);
        historial.setUsuario(usuario);
		
	return historial;
    }
    
    public DetalleVentaDTO cargarDetalleVenta(ResultSet resulSet) throws SQLException{
        DetalleVentaDTO detalleVenta = new DetalleVentaDTO();
        detalleVenta.setCantidad(resulSet.getInt("CANTIDAD"));
        detalleVenta.setSubtotal(resulSet.getDouble("SUBTOTAL"));
        
        ProductoDTO prod = this.cargarProductoSinCategoria(resulSet);
        detalleVenta.setProducto(prod);
        
        VentaDTO venta = this.cargarVentaDTOsinUsuario(resulSet);
        detalleVenta.setVenta(venta);
        
        return detalleVenta;
    }
    
    public ProductoDTO cargarProductoSinCategoria (ResultSet resulSet) throws SQLException{
        ProductoDTO prod = new ProductoDTO();
        prod.setProductoId(resulSet.getInt("PRODUCTO_ID"));
        prod.setNombre(resulSet.getString("NOMBRE"));
        prod.setPrecioUnitario(resulSet.getDouble("PRECIO_UNITARIO"));
        prod.setUnidadMedida(Unidad_Medida.valueOf(resulSet.getString("UNIDAD_MEDIDA")));
        prod.setStock(resulSet.getInt("STOCK"));
        prod.setStockMinimo(resulSet.getInt("STOCK_MINIMO"));
        prod.setActivo(resulSet.getBoolean("ACTIVO"));
        return prod;
    }
    
    public ProductoDTO cargarProductoDTO(ResultSet resulSet) throws SQLException{
        ProductoDTO prod = new ProductoDTO();
        prod.setProductoId(resulSet.getInt("PRODUCTO_ID"));
        prod.setNombre(resulSet.getString("NOMBRE"));
        prod.setPrecioUnitario(resulSet.getDouble("PRECIO_UNITARIO"));
        prod.setUnidadMedida(Unidad_Medida.valueOf(resulSet.getString("UNIDAD_MEDIDA")));
        prod.setStock(resulSet.getInt("STOCK"));
        prod.setStockMinimo(resulSet.getInt("STOCK_MINIMO"));
        prod.setActivo(resulSet.getBoolean("ACTIVO"));
        
        CategoriaDTO cate = this.cargarCategoria(resulSet);
        prod.setCategoria(cate);
        
        return prod;
    }
    
    public RazonDevolucionDTO cargaRazonDevolucionDTO(ResultSet resulSet) throws SQLException{
        RazonDevolucionDTO razonDev = new RazonDevolucionDTO();
        razonDev.setRazonDevolucionId(resulSet.getInt("RAZON_DEVOLUCION_ID"));
        razonDev.setDescripcion(resulSet.getString("DESCRIPCION"));
        
        return razonDev;
    }
   
    public RegistroPagoFiadoDTO cargarRegistroPagoFiadoDTO(ResultSet resulSet) throws SQLException{
        RegistroPagoFiadoDTO registroPago = new RegistroPagoFiadoDTO();
        registroPago.setPagoId(resulSet.getInt("PAGO_ID"));
        registroPago.setFecha(resulSet.getDate("FECHA"));
        registroPago.setMetodoPago(Tipo_de_pago.valueOf(resulSet.getString("METODO_PAGO")));
        registroPago.setMonto(resulSet.getDouble("MONTO"));
        
        return registroPago;
    }
    
    public VentaAlFiadoDTO cargarVentaFiadaDTO(ResultSet resulSet) throws SQLException{
        VentaAlFiadoDTO ventaFiada = new VentaAlFiadoDTO();
        ventaFiada.setVentaFiadaId(resulSet.getInt("VENTA_FIADA_ID"));

        VentaDTO venta = this.cargarVentaDTOsinUsuario(resulSet);
        ventaFiada.setVenta(venta);
        
//        ClienteAlFiadoDTO cliente = this.cargarClienteAlFiado(resulSet);
//        ventaFiada.setCliente(cliente);
        
        return ventaFiada;
    }

    public VentaDTO cargarVentaDTOsinUsuario(ResultSet resulSet) throws SQLException{
        VentaDTO venta = new VentaDTO();
        venta.setVentaId(resulSet.getInt("VENTA_ID"));
        venta.setTotal(resulSet.getDouble("TOTAL"));
        venta.setMetodoPago(Tipo_de_pago.valueOf(resulSet.getString("METODO_PAGO")));
        venta.setFecha(resulSet.getDate("FECHA"));
        return venta;
    }
    
    public VentaDTO cargarVentaDTO(ResultSet resulSet) throws SQLException{
        VentaDTO venta = cargarVentaDTOsinUsuario(resulSet);

        UsuarioDTO user = this.cargarUsuario(resulSet);
        venta.setUsuario(user);
        
        return venta;
    }
    
    public UsuarioDTO cargarUsuario(ResultSet resulSet) throws SQLException{
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioId(resulSet.getInt("USUARIO_ID"));
        usuario.setUsuario(resulSet.getString("USUARIO"));
        usuario.setTipoUsuarios(Tipo_Usuario.valueOf(resulSet.getString("TIPO_USUARIOS")));
        usuario.setCorreo(resulSet.getString("CORREO"));
        usuario.setContrasenha(resulSet.getString("CONTRASENHA"));
        usuario.setNombre(resulSet.getString("NOMBRE_COMPLETO"));
        usuario.setTelefono(resulSet.getString("TELEFONO_USUARIO"));
        usuario.setActivo(resulSet.getBoolean("ACTIVO_USUARIO"));
        
        return usuario;
    }
    
}