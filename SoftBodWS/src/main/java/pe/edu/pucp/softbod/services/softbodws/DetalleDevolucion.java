/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
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
    
    @WebMethod(operationName = "insertar")
    public Integer insertar(@WebParam(name = "detalleDevolucion")DetalleDevolucionDTO detalleDevolucion){
        return this.detalleDevolucionBO.insertar(detalleDevolucion);
    }
    
    @WebMethod(operationName = "obtenerPorId")
    public DetalleDevolucionDTO obtenerPorId (@WebParam(name = "productoId")Integer productoId,@WebParam(name = "devolucionId")Integer devolucionId){
        return this.detalleDevolucionBO.obtenerPorId(productoId, devolucionId);
    }
    
    @WebMethod(operationName = "listarTodos")
    public ArrayList<DetalleDevolucionDTO> listarTodos (){
        return this.detalleDevolucionBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarPorProducto")
    public ArrayList<DetalleDevolucionDTO> listarPorProducto(@WebParam(name = "productoId")Integer productoId){
        return this.detalleDevolucionBO.listarPorProducto(productoId);
    }
    
    @WebMethod(operationName = "listarPorDevolucion")
    public ArrayList<DetalleDevolucionDTO> listarPorDevolucion(@WebParam(name = "devolucionId")Integer devolucionId){
        return this.detalleDevolucionBO.listarPorDevolucion(devolucionId);
    }
    
    @WebMethod(operationName = "listarPorRazonDevolucion")
    public ArrayList<DetalleDevolucionDTO> listarPorRazonDevolucion(@WebParam(name = "razonDevolucion")String razonDevolucion){
        return this.detalleDevolucionBO.listarPorRazonDevolucion(razonDevolucion);
    }
}
