package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.devolucion.RazonDevolucionBO;
import pe.edu.pucp.softbod.model.devolucion.RazonDevolucionDTO;

@WebService(serviceName = "RazonDevolucion")
public class RazonDevolucion {
    
    private RazonDevolucionBO razonDevolucionBO;
    
    public RazonDevolucion(){
        this.razonDevolucionBO = new RazonDevolucionBO();
    }
    
    @WebMethod(operationName = "insertarRazonDevolucion")
    public Integer insertarRazonDevolucion(@WebParam(name = "descripcion") String descripcion){
        return this.razonDevolucionBO.insertar(descripcion);
    }
    
    @WebMethod(operationName = "eliminarRazonDevolucion")
    public Integer eliminarRazonDevolucion(@WebParam(name = "IdRazonDevolucion") Integer IdRazonDevolucion){
        return this.razonDevolucionBO.eliminar(IdRazonDevolucion);
    }
    
    @WebMethod(operationName = "obtenerRazonDevolucionPorId")
    public RazonDevolucionDTO obtenerRazonDevolucionPorId(@WebParam(name = "razonId") Integer razonId){
        return this.razonDevolucionBO.obtenerPorId(razonId);
    }
    
    @WebMethod(operationName = "listarTodasRazonesDevolucion")
    public ArrayList<RazonDevolucionDTO> listarTodasRazonesDevolucion(){
        return this.razonDevolucionBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarRazonesDevolucionPorNombreParcial")
    public ArrayList<RazonDevolucionDTO> listarRazonesDevolucionPorNombreParcial(@WebParam(name = "nombre") String nombre){
        return this.razonDevolucionBO.listarTodosPorNombreParcial(nombre);
    }

}