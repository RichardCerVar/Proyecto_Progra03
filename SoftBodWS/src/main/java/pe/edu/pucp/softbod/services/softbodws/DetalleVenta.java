package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.ventas.DetalleVentaBO;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;

@WebService(serviceName = "DetalleVenta")
public class DetalleVenta {

    private DetalleVentaBO detalleVentaBO;

    public DetalleVenta() {
        this.detalleVentaBO = new DetalleVentaBO();
    }

    @WebMethod(operationName = "insertarDetalleVenta")
    public Integer insertarDetalleVenta(@WebParam(name = "detalleVenta") DetalleVentaDTO detalleVenta) {
        return this.detalleVentaBO.insertar(detalleVenta);
    }

    @WebMethod(operationName = "obtenerDetalleVentaPorId")
    public DetalleVentaDTO obtenerDetalleVentaPorId(@WebParam(name = "productoId") Integer productoId,
                                                      @WebParam(name = "ventaId") Integer ventaId) {
        return this.detalleVentaBO.obtenerPorId(productoId, ventaId);
    }

    @WebMethod(operationName = "listarTodosDetallesVenta")
    public ArrayList<DetalleVentaDTO> listarTodosDetallesVenta() {
        return this.detalleVentaBO.litarTodos();
    }

    @WebMethod(operationName = "listarDetallesVentaPorProducto")
    public ArrayList<DetalleVentaDTO> listarDetallesVentaPorProducto(@WebParam(name = "productoId") Integer productoId) {
        return this.detalleVentaBO.listarPorProducto(productoId);
    }

    @WebMethod(operationName = "listarDetallesVentaPorVenta")
    public ArrayList<DetalleVentaDTO> listarDetallesVentaPorVenta(@WebParam(name = "ventaId") Integer ventaId) {
        return this.detalleVentaBO.listarPorVenta(ventaId);
    }
}