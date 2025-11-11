package pe.edu.pucp.softbod.bo.devolucion;

import java.sql.Timestamp;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;
import pe.edu.pucp.softbod.bo.util.OperacionBOBase;
import pe.edu.pucp.softbod.dao.devolucion.DevolucionDAO;
import pe.edu.pucp.softbod.daoImp.devolucion.DevolucionDAOImpl;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.DevolucionDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;

public class DevolucionBO extends OperacionBOBase{
    
    private final DevolucionDAO devolucionDAO;
    private final DetalleDevolucionBO detalleDevolucionBO;
    private final RazonDevolucionBO razonDevBo;
    private final HistorialDeOperacionBO historialBO;
    
    public DevolucionBO (){
        this.devolucionDAO = new DevolucionDAOImpl();
        this.detalleDevolucionBO = new DetalleDevolucionBO();
        this.razonDevBo = new RazonDevolucionBO();
        this.historialBO = new HistorialDeOperacionBO();
    }
    
    public Integer insertar(
            UsuarioDTO usuario,
            ArrayList<DetalleDevolucionDTO> detalles){
        //insercion de la devoucion completa
        DevolucionDTO nuevaDev = new DevolucionDTO();
        String fechaActual = new Timestamp(System.currentTimeMillis()).toString(); 
        nuevaDev.setFecha(fechaActual);
        nuevaDev.setUsuario(usuario);
        nuevaDev.setTotal(calcularTotalDevolucion(detalles));
        Integer idNuevaDev = this.devolucionDAO.insertar(nuevaDev);
        nuevaDev.setDevolucionId(idNuevaDev);
        
        for (DetalleDevolucionDTO detalle : detalles) {
            detalle.setDevolucion(nuevaDev);//detalle ya vino con producto(basta con el id), subtotal,cantidad y razondevol(basta con id)
            this.detalleDevolucionBO.insertar(detalle);
        }
        this.registrarEnHistorial(usuario, "BOD_DEVOLUCION", Tipo_Operacion.INSERCION);
        return idNuevaDev;
    }
    
    public DevolucionDTO obtenerPorId (Integer devolucionId){
        return this.devolucionDAO.obtenerPorId(devolucionId);
    }
    
    public ArrayList<DevolucionDTO> listarTodos (){
        return this.devolucionDAO.listarTodos();
    }
    
    public ArrayList<DevolucionDTO> listarPorFecha(String fecha){
        return this.devolucionDAO.listarPorFecha(fecha);
    }
    
    public ArrayList<DevolucionDTO> listarPorUsuario(Integer usuarioId){
        return this.devolucionDAO.listarPorUsuario(usuarioId);
    }
    
    public ArrayList<DevolucionDTO> listarPorUsuarioYFecha(Integer usuarioId, String fecha){
        return this.devolucionDAO.listarPorUsuarioYFecha(usuarioId, fecha);
    }

    public Double calcularTotalDevolucion(ArrayList<DetalleDevolucionDTO> detalles) {
        Double total = 0.0;
        for (DetalleDevolucionDTO detalle : detalles) total += detalle.getSubtotal();
        return total;
    }
}