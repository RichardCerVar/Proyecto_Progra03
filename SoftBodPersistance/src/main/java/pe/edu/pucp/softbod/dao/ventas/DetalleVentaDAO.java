
package pe.edu.pucp.softbod.dao.ventas;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;

public interface DetalleVentaDAO {
    
    public Integer insertar(DetalleVentaDTO detalle);
    
    public ArrayList<DetalleVentaDTO> listarTodos();
    
    public ArrayList<DetalleVentaDTO> listarPorVenta(Integer ventaId);
    
    public ArrayList<DetalleVentaDTO> listarPorProducto(Integer productoId);
    
    public DetalleVentaDTO obtenerPorId(Integer ventaId,Integer productoId);
    
}
