
package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.DetalleVentaDTO;

public interface DetalleVentaDAO {
    public Integer insertar(DetalleVentaDTO detalle);

    public DetalleVentaDTO obtenerPorId(Integer venta_Id, Integer producto_Id);
    
    public ArrayList<DetalleVentaDTO> listarTodos();

    public Integer actualizar(DetalleVentaDTO detalle);

    public Integer eliminar(DetalleVentaDTO detalle);
}
