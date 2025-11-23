package pe.edu.pucp.softbod.bo.trazabilidad;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.trazabilidad.HistorialOperacionesDAO;
import pe.edu.pucp.softbod.daoImp.trazabilidad.HistorialOperacionesDAOImpl;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;
import pe.edu.pucp.softbod.bo.util.OperacionBOBase;

public class HistorialDeOperacionBO extends OperacionBOBase{
    
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
    
    public ArrayList<HistorialOperacionesDTO> listarPorFecha(String fecha){
        return this.historialDAO.listarPorFecha(fecha);
    }
    
    public ArrayList<HistorialOperacionesDTO> listarHistorialFiltros (Integer operacionId, 
            String nombreTabla, String tipoOperacion, 
         String fechaOperacion, Integer usuarioId, String usuario, 
         String tipoUsuario, Boolean estado){
        return this.historialDAO.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, usuario, tipoUsuario, estado);
    }
    
    public void registrarHistorialOperaciones(UsuarioDTO usuario, 
                                      String tablaAfectada, 
                                      String operacion) {
        Tipo_Operacion tipoOperacion;
        tipoOperacion = Tipo_Operacion.CONSULTAR;
        if (operacion.equals("MODIFICACION")) tipoOperacion = Tipo_Operacion.MODIFICACION;
        if (operacion.equals("ELIMINACION")) tipoOperacion = Tipo_Operacion.ELIMINACION;
        if (operacion.equals("INSERCION")) tipoOperacion = Tipo_Operacion.INSERCION;
        registrarEnHistorial(usuario, tablaAfectada, tipoOperacion);
    }
    
}
