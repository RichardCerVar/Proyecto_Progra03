package pe.edu.pucp.softbod.bo.ventas;

import pe.edu.pucp.softbod.bo.ventas.DetalleVentaBO;
import pe.edu.pucp.softbod.bo.almacen.ProductoBO;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;
import pe.edu.pucp.softbod.bo.util.OperacionBOBase;
import pe.edu.pucp.softbod.dao.ventas.VentaDAO;
import pe.edu.pucp.softbod.daoImp.ventas.VentaDAOImpl;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.ventas.VentaDTO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;

public class VentaBO extends OperacionBOBase{
    
    private final VentaDAO ventaDAO;
    private final DetalleVentaBO detalleVentaBO;
    private final ProductoBO productoBO;
    
    public VentaBO(){
        this.ventaDAO = new VentaDAOImpl();
        this.detalleVentaBO = new DetalleVentaBO();
        this.productoBO = new ProductoBO();
    }

    public VentaDTO obtenerPorId(Integer venta_Id){
        return this.ventaDAO.obtenerPorId(venta_Id);
    }
    
    public ArrayList<VentaDTO> listarTodos(){
        return this.ventaDAO.listarTodos();
    }
    
    public ArrayList<VentaDTO> listarTodosPorFecha(String fecha){
        return this.ventaDAO.listarTodosPorFecha(fecha);
    }
    
    public Integer insertar(
            UsuarioDTO usuario,//el front ya tiene al usuario
            Tipo_de_pago metodoPago,  //el front ya tiene el tipo de pago
            ArrayList<DetalleVentaDTO> detallesVenta) {
        
        //insercion de la venta completa:
        VentaDTO nuevaVenta = new VentaDTO();
        String fechaActual = new Timestamp(System.currentTimeMillis()).toString();
        
        nuevaVenta.setFecha(fechaActual);
        nuevaVenta.setMetodoPago(metodoPago);
        nuevaVenta.setUsuario(usuario);
        nuevaVenta.setTotal(calcularMontoTotalVenta(detallesVenta));
        Integer idNuevaVenta = this.ventaDAO.insertar(nuevaVenta);
        nuevaVenta.setVentaId(idNuevaVenta);
        //insercion de los detalles de los detalles:
        for (DetalleVentaDTO d : detallesVenta) {
            d.setVenta(nuevaVenta);//detalle ya vino producto(basta solo con id), subtotal,cantidad
            ProductoDTO prod = this.productoBO.obtenerPorId(d.getProducto().getProductoId());
            prod.setStock(prod.getStock()-d.getCantidad());
            this.productoBO.modificar(prod);
            this.detalleVentaBO.insertar(d); //inserta el datalle x venta
        }
        //Para trazabilidad:
        this.registrarEnHistorial(usuario, "BOD_VENTAS", Tipo_Operacion.INSERCION);
        return idNuevaVenta;
    }
    
    public Double calcularMontoTotalVenta(ArrayList<DetalleVentaDTO> detallesVenta){
        Double total = 0.0;
        for (DetalleVentaDTO d : detallesVenta) total += d.getSubtotal();
        return total;
    }
}