package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.gestclientes.RegistroPagoFiadoBO;
import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;
import pe.edu.pucp.softbod.model.gestclientes.RegistroPagoFiadoDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;

@WebService(serviceName = "RegistroPagoFiado")
public class RegistroPagoFiado {

    private RegistroPagoFiadoBO registroPagoFiadoBO;
    
    public RegistroPagoFiado(){
        this.registroPagoFiadoBO = new RegistroPagoFiadoBO();
    }
    
    @WebMethod(operationName = "insertarRegistroPagoFiado")
    public Integer insertarRegistroPagoFiado(@WebParam(name = "usuario") UsuarioDTO usuario,
            @WebParam(name = "cliente") ClienteAlFiadoDTO cliente, 
            @WebParam(name = "metodoPago") String metodoPago,@WebParam(name = "monto") Double monto){
        
        return this.registroPagoFiadoBO.insertar(usuario, cliente, metodoPago, monto);
    }
    
    @WebMethod(operationName = "listarTodosRegistrosPagoFiado")
    public ArrayList<RegistroPagoFiadoDTO> listarTodosRegistrosPagoFiado(){
        return this.registroPagoFiadoBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarRegistrosPagoFiadoPorAliasCliente")
    public ArrayList<RegistroPagoFiadoDTO> listarRegistrosPagoFiadoPorAliasCliente(@WebParam(name = "aliasCliente") String aliasCliente){
        return this.registroPagoFiadoBO.listarTodosPorAliasCliente(aliasCliente);
    }
    
    @WebMethod(operationName = "listarRegistrosPagoFiadoPorAliasClienteConFechaFin")
    public ArrayList<RegistroPagoFiadoDTO> listarRegistrosPagoFiadoPorAliasClienteConFechaFin(
            @WebParam(name = "aliasCliente") String aliasCliente,@WebParam(name = "fechaFin") String fechaFin){
        return this.registroPagoFiadoBO.listarTodosPorAliasClienteConFechaFin(aliasCliente, fechaFin);
    }
    
    @WebMethod(operationName = "registrarPagoFiado")
    public Integer registrarPagoFiado(@WebParam(name = "pago")  RegistroPagoFiadoDTO pago) {
        return this.registroPagoFiadoBO.registrarPago(pago);
    }
 
}