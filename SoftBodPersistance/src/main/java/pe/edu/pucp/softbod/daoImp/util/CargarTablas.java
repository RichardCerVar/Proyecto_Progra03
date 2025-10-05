package pe.edu.pucp.softbod.daoImp.util;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.pucp.softbod.model.CategoriaDTO;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;
import pe.edu.pucp.softbod.model.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.RazonDevolucionDTO;
import pe.edu.pucp.softbod.model.ProductoDTO;
import pe.edu.pucp.softbod.model.UsuarioDTO;
import pe.edu.pucp.softbod.model.DevolucionDTO;
import pe.edu.pucp.softbod.model.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;


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
		
	return clienteAlFiado;
    }
	
    public DetalleDevolucionDTO cargarDetalleDevolucion(ResultSet resultSet) throws SQLException {
        
	DetalleDevolucionDTO linea = new DetalleDevolucionDTO();
	linea.setCantidad(resultSet.getInt("CANTIDAD"));
        linea.setSubtotal(resultSet.getDouble("SUBTOTAL"));
		
        DevolucionDTO devolucion = this.cargarDevolucion(resultSet);
        linea.setDevolucion(devolucion);
		
	ProductoDTO producto = new ProductoDTO();
        producto.setProductoId(resultSet.getInt("PRODUCTO_ID"));
        linea.setProducto(producto);
        
	RazonDevolucionDTO razon = new RazonDevolucionDTO();
        razon.setRazonDevolucionId(resultSet.getInt("RAZON_DEVOLUCION_ID"));
        linea.setRazonDevolucion(razon);
		
	return linea;
    }
	
    public DevolucionDTO cargarDevolucion(ResultSet resultSet) throws SQLException {
		
        DevolucionDTO devolucion = new DevolucionDTO();
        devolucion.setDevolucionId(resultSet.getInt("DEVOLUCION_ID"));
        devolucion.setTotal(resultSet.getDouble("TOTAL"));
        devolucion.setFecha(resultSet.getDate("FECHA"));
		
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioId(resultSet.getInt("USUARIO_ID"));
        devolucion.setUsuario(usuario);
		
        return devolucion;
    }
	
	
    public HistorialOperacionesDTO cargarHistorialOperaciones(ResultSet resultSet) throws SQLException {
		
        HistorialOperacionesDTO historial = new HistorialOperacionesDTO();
        historial.setOperacionId(resultSet.getInt("OPERACION_ID"));
        historial.setTablaAfectada(resultSet.getString("TABLA_AFECTADA"));
        historial.setFechaHora(resultSet.getDate("FECHA_HORA"));
        historial.setOperacion(Tipo_Operacion.valueOf(resultSet.getString("OPERACION")));
		
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioId(resultSet.getInt("USUARIO_ID"));
        historial.setUsuario(usuario);
		
        
	return historial;
    }
    
}