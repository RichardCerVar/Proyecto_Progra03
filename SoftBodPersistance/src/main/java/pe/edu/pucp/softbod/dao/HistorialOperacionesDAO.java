package pe.edu.pucp.softbod.dao;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.model.HistorialOperacionesDTO;

public interface HistorialOperacionesDAO {
    
    public Integer insertar(HistorialOperacionesDTO historial);
    
    public HistorialOperacionesDTO obtenerPorId(Integer historialId);
    
    public ArrayList<HistorialOperacionesDTO> listarTodos();
    
    public ArrayList<HistorialOperacionesDTO> listarPorUsuario(Integer usuarioId);
    
    public ArrayList<HistorialOperacionesDTO> listarPorTabla(String nombreTabla);
    
    public ArrayList<HistorialOperacionesDTO> listarPorOperacion(String tipoOperacion);
    
    public ArrayList<HistorialOperacionesDTO> listarPorUsuarioYTabla(Integer usuarioId,
                                              String nombreTabla);
    
    public ArrayList<HistorialOperacionesDTO> listarPorTablaYOperacion(String nombreTabla,
                                              String tipoOperacion);
    
    public ArrayList<HistorialOperacionesDTO> listarPorTabla(Date fecha);
    
    public ArrayList<HistorialOperacionesDTO> listarHistorialFiltros (Integer operacionId, 
            String nombreTabla, String tipoOperacion, 
         Date fechaOperacion, Integer usuarioId, String usuario, 
         String tipoUsuario, Boolean estado);
    
    //listarPorUsuario(int usuarioId);
    
    //litarPorFecha(Date inicio, Date fin);
    
    //listarPorTabla(String tabla);
    
    //exportarAuditoria(Date inicio, Date fin) 
}
