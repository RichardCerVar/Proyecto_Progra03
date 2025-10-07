package pe.edu.pucp.softbod.bo;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.DevolucionDAO;
import pe.edu.pucp.softbod.daoImp.DevolucionDAOImpl;
import pe.edu.pucp.softbod.model.DevolucionDTO;

public class DevolucionBO {
    
    
    private final DevolucionDAO devolucionDAO;
    
    public DevolucionBO (){
        this.devolucionDAO = new DevolucionDAOImpl();
    }
    
    public Integer insertar(DevolucionDTO devolucion){
        return this.devolucionDAO.insertar(devolucion);
    }
    
    public DevolucionDTO obtenerPorId (Integer devolucionId){
        return this.devolucionDAO.obtenerPorId(devolucionId);
    }
    
    public ArrayList<DevolucionDTO> litarTodos (){
        return this.devolucionDAO.listarTodos();
    }
    
    public ArrayList<DevolucionDTO> listarPorFecha(Date fecha){
        return this.devolucionDAO.listarPorFecha(fecha);
    }
    
    public ArrayList<DevolucionDTO> listarPorUsuario(Integer usuarioId){
        return this.devolucionDAO.listarPorUsuario(usuarioId);
    }
    
    public ArrayList<DevolucionDTO> listarPorUsuarioYFecha(Integer usuarioId, Date fecha){
        return this.devolucionDAO.listarPorUsuarioYFecha(usuarioId, fecha);
    }
    
}
