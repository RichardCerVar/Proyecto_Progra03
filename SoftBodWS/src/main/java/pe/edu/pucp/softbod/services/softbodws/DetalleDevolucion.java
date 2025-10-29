package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.devolucion.DetalleDevolucionBO;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;

@WebService(serviceName = "DetalleDevolucion")
public class DetalleDevolucion {

    private DetalleDevolucionBO detalleDevolucionBO;
    
    public DetalleDevolucion(){
        this.detalleDevolucionBO = new DetalleDevolucionBO();
    }
    
    @WebMethod(operationName = "insertarDetalleDevolucion")
    public Integer insertarDetalleDevolucion(@WebParam(name = "detalleDevolucion")DetalleDevolucionDTO detalleDevolucion){
        return this.detalleDevolucionBO.insertar(detalleDevolucion);
    }
    
    @WebMethod(operationName = "obtenerDetalleDevolucionPorId")
    public DetalleDevolucionDTO obtenerDetalleDevolucionPorId (@WebParam(name = "productoId")Integer productoId,@WebParam(name = "devolucionId")Integer devolucionId){
        return this.detalleDevolucionBO.obtenerPorId(productoId, devolucionId);
    }
    
    @WebMethod(operationName = "listarTodosDetallesDevolucion")
    public ArrayList<DetalleDevolucionDTO> listarTodosDetallesDevolucion (){
        return this.detalleDevolucionBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarDetallesDevolucionPorProducto")
    public ArrayList<DetalleDevolucionDTO> listarDetallesDevolucionPorProducto(@WebParam(name = "productoId")Integer productoId){
        return this.detalleDevolucionBO.listarPorProducto(productoId);
    }
    
    @WebMethod(operationName = "listarDetallesDevolucionPorDevolucion")
    public ArrayList<DetalleDevolucionDTO> listarDetallesDevolucionPorDevolucion(@WebParam(name = "devolucionId")Integer devolucionId){
        return this.detalleDevolucionBO.listarPorDevolucion(devolucionId);
    }
    
    @WebMethod(operationName = "listarDetallesDevolucionPorRazon")
    public ArrayList<DetalleDevolucionDTO> listarDetallesDevolucionPorRazon(@WebParam(name = "razonDevolucion")String razonDevolucion){
        return this.detalleDevolucionBO.listarPorRazonDevolucion(razonDevolucion);
    }
}