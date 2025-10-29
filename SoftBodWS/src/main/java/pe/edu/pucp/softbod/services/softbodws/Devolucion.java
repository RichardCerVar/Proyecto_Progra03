/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
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
    
    @WebMethod(operationName = "insertar")
    public Integer insertar(@WebParam(name = "devolucion")DevolucionDTO devolucion){
        return this.devolucionBO.insertar(devolucion);
    }
    
    @WebMethod(operationName = "obtenerPorId")
    public DevolucionDTO obtenerPorId (@WebParam(name = "devolucionId")Integer devolucionId){
        return this.devolucionBO.obtenerPorId(devolucionId);
    }
    
    @WebMethod(operationName = "listarTodos")
    public ArrayList<DevolucionDTO> listarTodos (){
        return this.devolucionBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarPorFecha")
    public ArrayList<DevolucionDTO> listarPorFecha(@WebParam(name = "fecha")Date fecha){
        return this.devolucionBO.listarPorFecha(fecha);
    }
    
    @WebMethod(operationName = "listarPorUsuario")
    public ArrayList<DevolucionDTO> listarPorUsuario(@WebParam(name = "usuarioId")Integer usuarioId){
        return this.devolucionBO.listarPorUsuario(usuarioId);
    }
    
    @WebMethod(operationName = "listarPorUsuarioYFecha")
    public ArrayList<DevolucionDTO> listarPorUsuarioYFecha(@WebParam(name = "usuarioId")Integer usuarioId, @WebParam(name = "fecha")Date fecha){
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
    
    @WebMethod(operationName = "calcularTotalDevolucion")
    public Double calcularTotalDevolucion(@WebParam(name = "detalles")ArrayList<DetalleDevolucionDTO> detalles) {
        return this.calcularTotalDevolucion(detalles);
    }
    
    @WebMethod(operationName = "validarMontoDevolucion")
    public Boolean validarMontoDevolucion(@WebParam(name = "totalDevolucion")Double totalDevolucion,@WebParam(name = "ventaId") Integer ventaId) {
        return this.validarMontoDevolucion(totalDevolucion, ventaId);
    }
}
