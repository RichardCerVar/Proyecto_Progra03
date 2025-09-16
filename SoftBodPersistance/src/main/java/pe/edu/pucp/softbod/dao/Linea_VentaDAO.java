package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.Linea_VentaDTO;


public interface Linea_VentaDAO {
    public Integer insertar(Linea_VentaDTO linea_venta);
    
    public Integer obtenerPorId(Integer linea_ventaId);
    
    public ArrayList<Linea_VentaDTO>listarTodos();
    
    public Integer modificar(Linea_VentaDTO linea_venta);
    public Integer eliminar(Linea_VentaDTO linea_venta);
    
}
