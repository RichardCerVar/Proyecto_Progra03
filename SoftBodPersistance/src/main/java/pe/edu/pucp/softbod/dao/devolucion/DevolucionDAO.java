package pe.edu.pucp.softbod.dao.devolucion;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.model.devolucion.DevolucionDTO;

public interface DevolucionDAO {
    
    public Integer insertar(DevolucionDTO devolucion);
    
    public DevolucionDTO obtenerPorId(Integer idDevolucion);
    
    public ArrayList<DevolucionDTO> listarTodos();
    
    public ArrayList<DevolucionDTO> listarPorFecha(String fecha);
    
    public ArrayList<DevolucionDTO> listarPorUsuario(Integer usuarioId);
    
    public ArrayList<DevolucionDTO> listarPorUsuarioYFecha(Integer usuarioId, 
                                    String fecha);
    
}

