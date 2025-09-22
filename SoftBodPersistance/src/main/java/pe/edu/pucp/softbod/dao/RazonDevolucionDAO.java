
package pe.edu.pucp.softbod.dao;

import pe.edu.pucp.softbod.model.RazonDevolucionDTO;

public interface RazonDevolucionDAO {
    public Integer insertar(RazonDevolucionDTO razonDevolucion);
    
    public Integer eliminar(RazonDevolucionDTO razonDevolucion);
}
