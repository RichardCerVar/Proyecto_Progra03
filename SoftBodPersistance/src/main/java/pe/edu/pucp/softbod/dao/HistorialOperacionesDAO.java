package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.HistorialOperacionesDTO;

public interface HistorialOperacionesDAO {
    
    public Integer insertar(HistorialOperacionesDTO historial);
    
    public HistorialOperacionesDTO obtenerPorId(Integer historialId);
    
    public ArrayList<HistorialOperacionesDTO> listarTodos();
    
    //listarPorUsuario(int usuarioId);
    
    //litarPorFecha(Date inicio, Date fin);
    
    //listarPorTabla(String tabla);
    
}
