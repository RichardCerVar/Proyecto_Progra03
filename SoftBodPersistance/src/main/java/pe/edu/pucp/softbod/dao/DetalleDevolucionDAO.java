package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.DetalleDevolucionDTO;

public interface DetalleDevolucionDAO {
    
    public Integer insertar(DetalleDevolucionDTO linea);
    
    public ArrayList<DetalleDevolucionDTO> listarTodos();
    
    //listarPorDevolucion(int devolucionId);
    
}

