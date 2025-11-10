package pe.edu.pucp.softbod.bo.devolucion;

import pe.edu.pucp.softbod.bo.devolucion.DetalleDevolucionBO;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;
import pe.edu.pucp.softbod.bo.ventas.VentaBO;
import pe.edu.pucp.softbod.bo.util.OperacionBOBase;
import pe.edu.pucp.softbod.dao.devolucion.DevolucionDAO;
import pe.edu.pucp.softbod.daoImp.devolucion.DevolucionDAOImpl;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.DevolucionDTO;
import pe.edu.pucp.softbod.model.ventas.VentaDTO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;

public class DevolucionBO extends OperacionBOBase{
    
    private final DevolucionDAO devolucionDAO;
    private final DetalleDevolucionBO detalleDevolucionBO;
    private final VentaBO ventaBO;
    private final HistorialDeOperacionBO historialBO;
    
    public DevolucionBO (){
        this.devolucionDAO = new DevolucionDAOImpl();
        this.detalleDevolucionBO = new DetalleDevolucionBO();
        this.ventaBO = new VentaBO();
        this.historialBO = new HistorialDeOperacionBO();
    }
    
    public Integer insertar(Double total, String fecha, UsuarioDTO usuario ){
        DevolucionDTO devolucion = new DevolucionDTO(total, fecha, usuario);
        return this.devolucionDAO.insertar(devolucion);
    }
    
    public DevolucionDTO obtenerPorId (Integer devolucionId){
        return this.devolucionDAO.obtenerPorId(devolucionId);
    }
    
    public ArrayList<DevolucionDTO> listarTodos (){
        return this.devolucionDAO.listarTodos();
    }
    
    public ArrayList<DevolucionDTO> listarPorFecha(String fecha){
        return this.devolucionDAO.listarPorFecha(fecha);
    }
    
    public ArrayList<DevolucionDTO> listarPorUsuario(Integer usuarioId){
        return this.devolucionDAO.listarPorUsuario(usuarioId);
    }
    
    public ArrayList<DevolucionDTO> listarPorUsuarioYFecha(Integer usuarioId, String fecha){
        return this.devolucionDAO.listarPorUsuarioYFecha(usuarioId, fecha);
    }

    public Integer registrarDevolucionCompleta(DevolucionDTO devolucion, ArrayList<DetalleDevolucionDTO> detalles) {
        try {
            // 1. Validar datos de entrada
            if (devolucion == null || detalles == null || detalles.isEmpty()) {
                System.err.println("Error: Datos de devolución inválidos");
                return null;
            }
            
            // Validar que tenga usuario asignado
            if (devolucion.getUsuario() == null || devolucion.getUsuario().getUsuarioId() == null) {
                System.err.println("Error: La devolución debe tener un usuario asignado");
                return null;
            }
            
            // 2. Calcular y setear el total de la devolución
            Double totalCalculado = calcularTotalDevolucion(detalles);
            devolucion.setTotal(totalCalculado);
            
            // 3. Insertar la devolución principal
            Integer devolucionId = this.devolucionDAO.insertar(devolucion);
            
            if (devolucionId == null || devolucionId <= 0) {
                System.err.println("Error: No se pudo insertar la devolución");
                return null;
            }
            
            // 4. Actualizar el ID en el DTO de devolución
            devolucion.setDevolucionId(devolucionId);
            
//            // 5. Insertar cada detalle de devolución
//            for (DetalleDevolucionDTO detalle : detalles) {
//                detalle.setDevolucion(devolucion);
//                
//                Integer resultadoDetalle = this.detalleDevolucionBO.insertar(devolucion, null, totalCalculado, devolucionId, null);
//                
//                if (resultadoDetalle == null || resultadoDetalle < 0) {
//                    System.err.println("Error: No se pudo insertar detalle de devolución");
//                    return null;
//                }
//            }
            
            // 6. Registrar en el historial de operaciones
            registrarEnHistorial(devolucion.getUsuario(), "BOD_DEVOLUCIONES", Tipo_Operacion.INSERCION);
            
            // 7. Retornar el ID de la devolución exitosamente registrada
            System.out.println("✓ Devolución registrada exitosamente. ID: " + devolucionId);
            return devolucionId;
            
        } catch (Exception e) {
            System.err.println("Error al registrar devolución completa: " + e.getMessage());
            return null;
        }
    }
//
//    public Boolean validarDevolucionPermitida(Integer ventaId, Integer diasMaximosDevolucion) {
//        try {
//            if (ventaId == null || diasMaximosDevolucion == null || diasMaximosDevolucion <= 0) {
//                return Boolean.FALSE;
//            }
//            
//            // Obtener la venta
//            VentaDTO venta = this.ventaBO.obtenerPorId(ventaId);
//            
//            if (venta == null || venta.getFecha() == null) {
//                System.err.println("Error: Venta no encontrada");
//                return Boolean.FALSE;
//            }
//            
//            // Calcular los días transcurridos
//            Date fechaVenta = venta.getFecha();
//            Date fechaActual = new Date(System.currentTimeMillis());
//            
//            long diferenciaMillis = fechaActual.getTime() - fechaVenta.getTime();
//            long diasTranscurridos = diferenciaMillis / (1000 * 60 * 60 * 24);
//            
//            // Verificar si está dentro del plazo
//            if (diasTranscurridos > diasMaximosDevolucion) {
//                System.err.println("Error: Han transcurrido " + diasTranscurridos + 
//                                 " días. Máximo permitido: " + diasMaximosDevolucion);
//                return Boolean.FALSE;
//            }
//            
//            return Boolean.TRUE;
//            
//        } catch (Exception e) {
//            System.err.println("Error al validar devolución permitida: " + e.getMessage());
//            return Boolean.FALSE;
//        }
//    }

    public Double calcularTotalDevolucion(ArrayList<DetalleDevolucionDTO> detalles) {
        try {
            if (detalles == null || detalles.isEmpty()) {
                return 0.0;
            }
            
            double total = 0.0;
            
            for (DetalleDevolucionDTO detalle : detalles) {
                if (detalle.getSubtotal() != null) {
                    total += detalle.getSubtotal();
                }
            }
            
            return total;
            
        } catch (Exception e) {
            System.err.println("Error al calcular total de devolución: " + e.getMessage());
            return 0.0;
        }
    }

    public Boolean validarMontoDevolucion(Double totalDevolucion, Integer ventaId) {
        try {
            if (totalDevolucion == null || totalDevolucion <= 0 || ventaId == null) {
                return Boolean.FALSE;
            }
            
            // Obtener la venta original
            VentaDTO venta = this.ventaBO.obtenerPorId(ventaId);
            
            if (venta == null || venta.getTotal() == null) {
                System.err.println("Error: Venta no encontrada");
                return Boolean.FALSE;
            }
            
            // Validar que el monto de devolución no exceda el total de la venta
            if (totalDevolucion > venta.getTotal()) {
                System.err.println("Error: El monto de devolución (" + totalDevolucion + 
                                 ") no puede ser mayor al total de la venta (" + 
                                 venta.getTotal() + ")");
                return Boolean.FALSE;
            }
            
            return Boolean.TRUE;
            
        } catch (Exception e) {
            System.err.println("Error al validar monto de devolución: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

}