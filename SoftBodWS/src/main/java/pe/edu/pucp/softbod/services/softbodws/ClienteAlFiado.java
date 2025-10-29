package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.gestclientes.ClienteAlFiadoBO;
import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;

@WebService(serviceName = "ClienteAlFiado")
public class ClienteAlFiado {

    private ClienteAlFiadoBO clienteAlFiadoBO;
    
    public ClienteAlFiado(){
        this.clienteAlFiadoBO = new ClienteAlFiadoBO();
    }
    
    @WebMethod(operationName = "insertarClienteAlFiado")
    public Integer insertarClienteAlFiado(@WebParam(name = "alias") String alias,@WebParam(name = "nombre") String nombre,
            @WebParam(name = "telefono") String telefono, @WebParam(name = "fechaDePago") Date fechaDePago, 
            @WebParam(name = "activo") Boolean activo,@WebParam(name = "montoDeuda")  Double montoDeuda){
        
        return this.clienteAlFiadoBO.insertar(alias, nombre, telefono, fechaDePago, activo, montoDeuda);
    }
    
    @WebMethod(operationName = "modificarClienteAlFiado")
    public Integer modificarClienteAlFiado(@WebParam(name = "alias") String alias,@WebParam(name = "nombre") String nombre,
            @WebParam(name = "telefono") String telefono, @WebParam(name = "fechaDePago") Date fechaDePago, 
            @WebParam(name = "activo") Boolean activo,@WebParam(name = "montoDeuda")  Double montoDeuda){
        
        return this.clienteAlFiadoBO.modificar(alias, nombre, telefono, fechaDePago, activo, montoDeuda);
    }
    
    @WebMethod(operationName = "obtenerClienteAlFiadoPorId")
    public ClienteAlFiadoDTO obtenerClienteAlFiadoPorId(@WebParam(name = "clienteId") Integer clienteId){
        return this.clienteAlFiadoBO.obtenerPorId(clienteId);
    }
    
    @WebMethod(operationName = "listarTodosClientesAlFiado")
    public ArrayList<ClienteAlFiadoDTO> listarTodosClientesAlFiado(){
        return this.clienteAlFiadoBO.litarTodos();
    }
    
    @WebMethod(operationName = "listarTodosClientesAlFiadoLike")
    public ArrayList<ClienteAlFiadoDTO> listarTodosClientesAlFiadoLike(@WebParam(name = "cadena") String cadena){
        return this.clienteAlFiadoBO.litarTodosLike(cadena);
    }

    @WebMethod(operationName = "bloquearClienteAlFiadoPorMorosidad")
    public void bloquearClienteAlFiadoPorMorosidad(@WebParam(name = "clienteId") Integer clienteId) {
        this.clienteAlFiadoBO.bloquearClientePorMorosidad(clienteId);
    }
    
    @WebMethod(operationName = "desbloquearClienteAlFiado")
    public void desbloquearClienteAlFiado(@WebParam(name = "clienteId") Integer clienteId) {
        this.clienteAlFiadoBO.desbloquearCliente(clienteId);
    }
    
}