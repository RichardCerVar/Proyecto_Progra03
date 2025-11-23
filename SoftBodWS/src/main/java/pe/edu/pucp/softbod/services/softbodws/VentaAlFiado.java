package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.ventas.VentaAlFiadoBO;
import pe.edu.pucp.softbod.model.ventas.VentaAlFiadoDTO;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;

@WebService(serviceName = "VentaAlFiado")
public class VentaAlFiado {

    private VentaAlFiadoBO ventaAlFiadoBO;

    public VentaAlFiado() {
        this.ventaAlFiadoBO = new VentaAlFiadoBO();
    }

    @WebMethod(operationName = "insertarVentaAlFiado")
    public Integer insertarVentaAlFiado(@WebParam(name = "cliente") ClienteAlFiadoDTO cliente,
                                         @WebParam(name = "usuario") UsuarioDTO usuario,
                                         @WebParam(name = "metodoPago") String metodoPago,
                                         @WebParam(name = "detallesVenta") ArrayList<DetalleVentaDTO> detallesVenta) {
        return this.ventaAlFiadoBO.insertar(cliente, usuario, metodoPago, detallesVenta);
    }

    @WebMethod(operationName = "obtenerVentaAlFiadoPorId")
    public VentaAlFiadoDTO obtenerVentaAlFiadoPorId(@WebParam(name = "ventaFiadaId") Integer ventaFiadaId) {
        return this.ventaAlFiadoBO.obtenerPorId(ventaFiadaId);
    }

    @WebMethod(operationName = "listarTodasVentasAlFiado")
    public ArrayList<VentaAlFiadoDTO> listarTodasVentasAlFiado() {
        return this.ventaAlFiadoBO.listarTodos();
    }

    @WebMethod(operationName = "listarVentasAlFiadoPorAliasCliente")
    public ArrayList<VentaAlFiadoDTO> listarVentasAlFiadoPorAliasCliente(@WebParam(name = "aliasCliente") String aliasCliente) {
        return this.ventaAlFiadoBO.listarTodosPorAliasCliente(aliasCliente);
    }

    @WebMethod(operationName = "listarVentasAlFiadoPorAliasClienteYFecha")
    public ArrayList<VentaAlFiadoDTO> listarVentasAlFiadoPorAliasClienteYFecha(@WebParam(name = "aliasCliente") String aliasCliente,
                                                                                 @WebParam(name = "fecha") String fecha) {
        return this.ventaAlFiadoBO.listarTodosPorAliasClienteFecha(aliasCliente, fecha);
    }
}