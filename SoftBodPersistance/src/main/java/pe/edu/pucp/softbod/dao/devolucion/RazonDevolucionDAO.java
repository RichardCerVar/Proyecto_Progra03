package pe.edu.pucp.softbod.dao.devolucion;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.devolucion.RazonDevolucionDTO;

public interface RazonDevolucionDAO {
    public Integer insertar(RazonDevolucionDTO razonDevolucion);
    public Integer eliminar(RazonDevolucionDTO razonDevolucion);
    public RazonDevolucionDTO obtenerPorId(Integer razonId);
    public ArrayList<RazonDevolucionDTO> listarTodos();
    public ArrayList<RazonDevolucionDTO> listarTodosPorNombreParcial(String nombre);
}
