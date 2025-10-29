package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.devolucion.DevolucionBO;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.DevolucionDTO;


@WebService(serviceName = "Devolucion")
public class Devolucion {
    private DevolucionBO devolucionBO;
    
    public Devolucion(){
        this.devolucionBO = new DevolucionBO();
    }
    
    @WebMethod(operationName = "insertarDevolucion")
    public Integer insertarDevolucion(@WebParam(name = "devolucion")DevolucionDTO devolucion){
        return this.devolucionBO.insertar(devolucion);
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
    public ArrayList<DevolucionDTO> listarDevolucionesPorFecha(@WebParam(name = "fecha")Date fecha){
        return this.devolucionBO.listarPorFecha(fecha);
    }
    
    @WebMethod(operationName = "listarDevolucionesPorUsuario")
    public ArrayList<DevolucionDTO> listarDevolucionesPorUsuario(@WebParam(name = "usuarioId")Integer usuarioId){
        return this.devolucionBO.listarPorUsuario(usuarioId);
    }
    
    @WebMethod(operationName = "listarDevolucionesPorUsuarioYFecha")
    public ArrayList<DevolucionDTO> listarDevolucionesPorUsuarioYFecha(@WebParam(name = "usuarioId")Integer usuarioId, @WebParam(name = "fecha")Date fecha){
        return this.devolucionBO.listarPorUsuarioYFecha(usuarioId, fecha);
    }

    @WebMethod(operationName = "registrarDevolucionCompleta")
    public Integer registrarDevolucionCompleta(@WebParam(name = "devolucion")DevolucionDTO devolucion, @WebParam(name = "detalles")ArrayList<DetalleDevolucionDTO> detalles) {
        return this.devolucionBO.registrarDevolucionCompleta(devolucion, detalles);
    }
    
    @WebMethod(operationName = "validarDevolucionPermitida")
    public Boolean validarDevolucionPermitida(@WebParam(name = "ventaId")Integer ventaId,@WebParam(name = "diasMaximosDevolucion") Integer diasMaximosDevolucion) {
        return this.devolucionBO.validarDevolucionPermitida(ventaId, diasMaximosDevolucion);
    }

}