package pe.edu.pucp.softbod.bo.ventas;

import pe.edu.pucp.softbod.bo.ventas.DetalleVentaBO;
import pe.edu.pucp.softbod.bo.almacen.ProductoBO;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;
import pe.edu.pucp.softbod.bo.util.OperacionBOBase;
import pe.edu.pucp.softbod.dao.ventas.VentaDAO;
import pe.edu.pucp.softbod.daoImp.ventas.VentaDAOImpl;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.ventas.VentaDTO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;

public class VentaBO extends OperacionBOBase{
    
    private final VentaDAO ventaDAO;
    private final DetalleVentaBO detalleVentaBO;
    private final HistorialDeOperacionBO historialBO;
    
    public VentaBO(){
        this.ventaDAO = new VentaDAOImpl();
        this.detalleVentaBO = new DetalleVentaBO();
        this.historialBO = new HistorialDeOperacionBO();
    }

    public VentaDTO obtenerPorId(Integer venta_Id){
        return this.ventaDAO.obtenerPorId(venta_Id);
    }
    
    public ArrayList<VentaDTO> listarTodos(){
        return this.ventaDAO.listarTodos();
    }
    
    public ArrayList<VentaDTO> listarTodosPorFecha(Date fecha){
        return this.ventaDAO.listarTodosPorFecha(fecha);
    }
    
    public Integer insertar(
            UsuarioDTO usuario,//el front ya tiene al usuario
            Tipo_de_pago metodoPago,  //el front ya tiene el tipo de pago
            ArrayList<DetalleVentaDTO> detallesVenta) {
        
        //insercion de la venta completa:
        VentaDTO nuevaVenta = new VentaDTO();
        Date fechaActual = new Date(System.currentTimeMillis());
        nuevaVenta.setFecha(fechaActual);
        nuevaVenta.setMetodoPago(metodoPago);
        nuevaVenta.setUsuario(usuario);
        nuevaVenta.setTotal(calcularMontoTotalVenta(detallesVenta));
        Integer idNuevaVenta = this.ventaDAO.insertar(nuevaVenta);
        nuevaVenta.setVentaId(idNuevaVenta);
        //insercion de los detalles de los detalles:
        for (DetalleVentaDTO d : detallesVenta) {
            d.setVenta(nuevaVenta);//detalle ya vino producto(basta solo con id), subtotal,cantidad
            this.detalleVentaBO.insertar(d); //inserta el datalle x venta
        }
        //Para trazabilidad:
        HistorialOperacionesDTO operacion = new HistorialOperacionesDTO();
        operacion.setFechaHora(fechaActual);
        operacion.setOperacion(Tipo_Operacion.INSERCION);
        operacion.setTablaAfectada("BOD_VENTAS");
        operacion.setUsuario(usuario);
        Integer nuevaOperacion = this.historialBO.insertar(operacion);
        return idNuevaVenta;
    }
    
    public Double calcularMontoTotalVenta(ArrayList<DetalleVentaDTO> detallesVenta){
        Double total = 0.0;
        for (DetalleVentaDTO d : detallesVenta) total += d.getSubtotal();
        return total;
    }
}