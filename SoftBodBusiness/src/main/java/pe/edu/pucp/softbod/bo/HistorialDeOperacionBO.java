package pe.edu.pucp.softbod.bo;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.HistorialOperacionesDAO;
import pe.edu.pucp.softbod.daoImp.HistorialOperacionesDAOImpl;
import pe.edu.pucp.softbod.model.HistorialOperacionesDTO;

public class HistorialDeOperacionBO {
    
    
    private final HistorialOperacionesDAO historialDAO;
    
    public HistorialDeOperacionBO (){
        this.historialDAO = new HistorialOperacionesDAOImpl();
    }
    
    public Integer insertar(HistorialOperacionesDTO historial){
        return this.historialDAO.insertar(historial);
    }
    
    public HistorialOperacionesDTO obtenerPorId(Integer historialId){
        return this.historialDAO.obtenerPorId(historialId);
    }
    
    public ArrayList<HistorialOperacionesDTO> listarTodos(){
        return this.historialDAO.listarTodos();
    }
    
    public ArrayList<HistorialOperacionesDTO> listarPorUsuario(Integer usuarioId){
        return this.historialDAO.listarPorUsuario(usuarioId);
    }
    
    public ArrayList<HistorialOperacionesDTO> listarPorTabla(String nombreTabla){
        return this.historialDAO.listarPorTabla(nombreTabla);
    }
    
    public ArrayList<HistorialOperacionesDTO> listarPorOperacion(String tipoOperacion){
        return this.historialDAO.listarPorOperacion(tipoOperacion);
    }
    
    public ArrayList<HistorialOperacionesDTO> listarPorUsuarioYTabla(Integer usuarioId,
                                              String nombreTabla){
        return this.historialDAO.listarPorUsuarioYTabla(usuarioId, nombreTabla);
    }
    
    public ArrayList<HistorialOperacionesDTO> listarPorTablaYOperacion(String nombreTabla,
                                              String tipoOperacion){
        return this.historialDAO.listarPorTablaYOperacion(nombreTabla, tipoOperacion);
    }
    
    public ArrayList<HistorialOperacionesDTO> listarPorFecha(Date fecha){
        return this.historialDAO.listarPorFecha(fecha);
    }
    
    public ArrayList<HistorialOperacionesDTO> listarHistorialFiltros (Integer operacionId, 
            String nombreTabla, String tipoOperacion, 
         Date fechaOperacion, Integer usuarioId, String usuario, 
         String tipoUsuario, Boolean estado){
        return this.historialDAO.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, usuario, tipoUsuario, estado);
    }
    
}
