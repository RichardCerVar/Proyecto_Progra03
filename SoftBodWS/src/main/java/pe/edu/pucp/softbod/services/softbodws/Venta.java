package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.ventas.VentaBO;
import pe.edu.pucp.softbod.model.ventas.VentaDTO;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;
import pe.edu.pucp.softbod.reporte.util.ReporteUtil;

@WebService(serviceName = "Venta")
public class Venta {

    private VentaBO ventaBO;

    public Venta() {
        this.ventaBO = new VentaBO();
    }

    @WebMethod(operationName = "insertarVenta")
    public Integer insertarVenta(@WebParam(name = "usuario") UsuarioDTO usuario,
                                  @WebParam(name = "metodoPago") Tipo_de_pago metodoPago,
                                  @WebParam(name = "detallesVenta") ArrayList<DetalleVentaDTO> detallesVenta) {
        return this.ventaBO.insertar(usuario, metodoPago, detallesVenta);
    }

    @WebMethod(operationName = "obtenerVentaPorId")
    public VentaDTO obtenerVentaPorId(@WebParam(name = "ventaId") Integer ventaId) {
        return this.ventaBO.obtenerPorId(ventaId);
    }

    @WebMethod(operationName = "listarTodosVentas")
    public ArrayList<VentaDTO> listarTodasVentas() {
        return this.ventaBO.listarTodos();
    }

    @WebMethod(operationName = "listarVentasPorFecha")
    public ArrayList<VentaDTO> listarVentasPorFecha(@WebParam(name = "fecha") String fecha) {
        return this.ventaBO.listarTodosPorFecha(fecha);
    }
    
    @WebMethod(operationName = "reporteDevolucionesYVentas")
    public byte[] reporteDevolucionesYVentas(@WebParam(name = "fechaInicio")String fechaInicio, @WebParam(name = "fechaFin")String fechaFin){
        return ReporteUtil.reporteDevolucionesYVentas(fechaInicio, fechaFin);
    }
    
}