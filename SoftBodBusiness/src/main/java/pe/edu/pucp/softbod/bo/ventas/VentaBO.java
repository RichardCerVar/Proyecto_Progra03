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

public class VentaBO extends OperacionBOBase{
    
    private final VentaDAO ventaDAO;
    private final DetalleVentaBO detalleVentaBO;
    private final ProductoBO productoBO;
    private final HistorialDeOperacionBO historialBO;
    
    public VentaBO(){
        this.ventaDAO = new VentaDAOImpl();
        this.detalleVentaBO = new DetalleVentaBO();
        this.productoBO = new ProductoBO();
        this.historialBO = new HistorialDeOperacionBO();
    }
    
    public Integer insertar(VentaDTO venta){
        return this.ventaDAO.insertar(venta);
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
    
    public Integer registrarVentaCompleta(VentaDTO venta, ArrayList<DetalleVentaDTO> detalles) {
        try {
            // 1. Validar datos de entrada
            if (venta == null || detalles == null || detalles.isEmpty()) {
                System.err.println("Error: Datos de venta inválidos");
                return null;
            }
            
            // Validar que tenga usuario asignado
            if (venta.getUsuario() == null || venta.getUsuario().getUsuarioId() == null) {
                System.err.println("Error: La venta debe tener un usuario asignado");
                return null;
            }
            
            // 2. Validar stock para TODOS los productos antes de insertar
            if (!validarStockParaVenta(detalles)) {
                System.err.println("Error: Stock insuficiente para uno o más productos");
                return null;
            }
            
            // 3. Calcular y setear el total de la venta
            Double totalCalculado = calcularTotalVenta(detalles);
            venta.setTotal(totalCalculado);
            
            // 4. Insertar la venta principal
            Integer ventaId = this.ventaDAO.insertar(venta);
            
            if (ventaId == null || ventaId <= 0) {
                System.err.println("Error: No se pudo insertar la venta");
                return null;
            }
            
            // 5. Actualizar el ID en el DTO de venta
            venta.setVentaId(ventaId);
            
            // 6. Insertar cada detalle de venta
            for (DetalleVentaDTO detalle : detalles) {
                detalle.setVenta(venta);
                
                Integer resultadoDetalle = this.detalleVentaBO.insertar(detalle);
                
                if (resultadoDetalle == null || resultadoDetalle <= 0) {
                    System.err.println("Error: No se pudo insertar detalle de venta");
                    return null;
                }
            }
            
            // 7. Registrar en el historial de operaciones
            registrarEnHistorial(venta.getUsuario(), "BOD_VENTAS", Tipo_Operacion.INSERCION);
            
            // 8. Retornar el ID de la venta exitosamente registrada
            System.out.println("✓ Venta registrada exitosamente. ID: " + ventaId);
            return ventaId;
            
        } catch (Exception e) {
            System.err.println("Error al registrar venta completa: " + e.getMessage());
            return null;
        }
    }
    
    public Boolean validarStockParaVenta(ArrayList<DetalleVentaDTO> detalles) {
        try {
            if (detalles == null || detalles.isEmpty()) {
                return Boolean.FALSE;
            }
            
            for (DetalleVentaDTO detalle : detalles) {
                if (detalle.getProducto() == null || 
                    detalle.getProducto().getProductoId() == null ||
                    detalle.getCantidad() == null || 
                    detalle.getCantidad() <= 0) {
                    return Boolean.FALSE;
                }
                
                // Verificar stock disponible usando el método del ProductoBO
                Boolean tieneStock = this.productoBO.verificarStockDisponible(
                    detalle.getProducto().getProductoId(), 
                    detalle.getCantidad()
                );
                
                if (!tieneStock) {
                    System.err.println("Stock insuficiente para producto ID: " + 
                                     detalle.getProducto().getProductoId());
                    return Boolean.FALSE;
                }
            }
            
            return Boolean.TRUE;
            
        } catch (Exception e) {
            System.err.println("Error al validar stock para venta: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Double calcularTotalVenta(ArrayList<DetalleVentaDTO> detalles) {
        try {
            if (detalles == null || detalles.isEmpty()) {
                return 0.0;
            }
            
            double total = 0.0;
            
            for (DetalleVentaDTO detalle : detalles) {
                if (detalle.getSubtotal() != null) {
                    total += detalle.getSubtotal();
                }
            }
            
            return total;
            
        } catch (Exception e) {
            System.err.println("Error al calcular total de venta: " + e.getMessage());
            return 0.0;
        }
    }

    public Double calcularVentasTotalesPorFecha(Date fecha) {
        try {
            if (fecha == null) {
                return 0.0;
            }
            
            ArrayList<VentaDTO> ventas = this.ventaDAO.listarTodosPorFecha(fecha);
            
            if (ventas == null || ventas.isEmpty()) {
                return 0.0;
            }
            
            double totalVentas = 0.0;
            
            for (VentaDTO venta : ventas) {
                if (venta.getTotal() != null) {
                    totalVentas += venta.getTotal();
                }
            }
            
            return totalVentas;
            
        } catch (Exception e) {
            System.err.println("Error al calcular ventas por fecha: " + e.getMessage());
            return 0.0;
        }
    }
 
    public Double calcularVentasTotalesPorRangoFechas(Date inicio, Date fin) {
        try {
            if (inicio == null || fin == null) {
                return 0.0;
            }
            
            // Validar que la fecha de inicio no sea posterior a la fecha fin
            if (inicio.after(fin)) {
                System.err.println("Error: Fecha de inicio posterior a fecha fin");
                return 0.0;
            }
            
            ArrayList<VentaDTO> todasLasVentas = this.ventaDAO.listarTodos();
            
            if (todasLasVentas == null || todasLasVentas.isEmpty()) {
                return 0.0;
            }
            
            double totalVentas = 0.0;
            
            for (VentaDTO venta : todasLasVentas) {
                if (venta.getFecha() != null && venta.getTotal() != null) {
                    // Verificar que la fecha de la venta esté dentro del rango
                    if (!venta.getFecha().before(inicio) && !venta.getFecha().after(fin)) {
                        totalVentas += venta.getTotal();
                    }
                }
            }
            
            return totalVentas;
            
        } catch (Exception e) {
            System.err.println("Error al calcular ventas por rango de fechas: " + e.getMessage());
            return 0.0;
        }
    }
    
}