package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.devolucion.DetalleDevolucionBO;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.DevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.RazonDevolucionDTO;

@WebService(serviceName = "DetalleDevolucion")
public class DetalleDevolucion {

    private DetalleDevolucionBO detalleDevolucionBO;
    
    public DetalleDevolucion(){
        this.detalleDevolucionBO = new DetalleDevolucionBO();
    }
    
    @WebMethod(operationName = "insertarDetalleDevolucion")
    public Integer insertarDetalleDevolucion(@WebParam(name = "devolucion") DevolucionDTO devolucion,
            @WebParam(name = "producto") ProductoDTO producto,@WebParam(name = "subtotal")  Double subtotal, 
            @WebParam(name = "cantidad") Integer cantidad,@WebParam(name = "razonDevolucion")  RazonDevolucionDTO razonDevolucion){
        return this.detalleDevolucionBO.insertar(devolucion, producto, subtotal, cantidad, razonDevolucion);
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