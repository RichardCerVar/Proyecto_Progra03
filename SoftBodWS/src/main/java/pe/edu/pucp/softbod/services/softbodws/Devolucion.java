package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.bind.annotation.XmlElement;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.devolucion.DevolucionBO;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.DevolucionDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;


@WebService(serviceName = "Devolucion")
public class Devolucion {
    private DevolucionBO devolucionBO;
    
    public Devolucion(){
        this.devolucionBO = new DevolucionBO();
    }
    
    @WebMethod(operationName = "insertarDevolucion")
    public Integer insertarDevolucion(
        @WebParam(name = "usuario") UsuarioDTO usuario,
        @WebParam(name = "detallesDev") ArrayList<DetalleDevolucionDTO> detalles) {
        return this.devolucionBO.insertar(usuario, detalles);
    }
    
    @WebMethod(operationName = "obtenerDevolucionPorId")
    public DevolucionDTO obtenerDevolucionPorId (@WebParam(name = "devolucionId")Integer devolucionId){
        return this.devolucionBO.obtenerPorId(devolucionId);
    }
    
    @WebMethod(operationName = "listarTodasDevoluciones")
    public ArrayList<DevolucionDTO> listarTodasDevoluciones (){
        return this.devolucionBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarDevolucionesPorFecha")
    public ArrayList<DevolucionDTO> listarDevolucionesPorFecha(@WebParam(name = "fecha")String fecha){
        return this.devolucionBO.listarPorFecha(fecha);
    }
    
    @WebMethod(operationName = "listarDevolucionesPorUsuario")
    public ArrayList<DevolucionDTO> listarDevolucionesPorUsuario(@WebParam(name = "usuarioId")Integer usuarioId){
        return this.devolucionBO.listarPorUsuario(usuarioId);
    }
    
    @WebMethod(operationName = "listarDevolucionesPorUsuarioYFecha")
    public ArrayList<DevolucionDTO> listarDevolucionesPorUsuarioYFecha(@WebParam(name = "usuarioId")Integer usuarioId, @WebParam(name = "fecha")String fecha){
        return this.devolucionBO.listarPorUsuarioYFecha(usuarioId, fecha);
    }
}