
package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.RazonDevolucionDTO;

public interface RazonDevolucionDAO {
    public Integer insertar(RazonDevolucionDTO razonDevolucion);
        
    public RazonDevolucionDTO obtenerPorId(Integer razonId);
    
    public ArrayList<RazonDevolucionDTO> listarTodos();  
    
    public Integer modificar(RazonDevolucionDTO razonDevolucion);
    
    public Integer eliminar(RazonDevolucionDTO razonDevolucion);
}
