package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.DevolucionDTO;

public interface DevolucionDAO {
    
    public Integer insertar(DevolucionDTO devolucion);
    
    public DevolucionDTO obtenerPorId(Integer idDevolucion);
    
    public ArrayList<DevolucionDTO> listarTodos();
    
    //listarPorFecha(Date Inicio, Date fin);
    
    //listarPorUsuario(int usuarioId);
    
    /////public Integer eliminar(DevolucionDTO devolucion);
}

