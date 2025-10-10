package pe.edu.pucp.softbod.dao.devolucion;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;

public interface DetalleDevolucionDAO {
    
    public Integer insertar(DetalleDevolucionDTO linea);
    
    public ArrayList<DetalleDevolucionDTO> listarTodos();
    
    public ArrayList<DetalleDevolucionDTO> listarPorProducto(Integer productoId);
    
    public ArrayList<DetalleDevolucionDTO> listarPorDevolucion(Integer devolucionId);
    
    public ArrayList<DetalleDevolucionDTO> listarPorRazonDevolucion(String razonDevolucion);
    
    public DetalleDevolucionDTO obtenerPorId(Integer productoId,Integer devolucionId);
    
}

