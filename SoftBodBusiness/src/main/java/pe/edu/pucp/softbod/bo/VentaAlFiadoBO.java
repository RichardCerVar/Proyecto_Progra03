package pe.edu.pucp.softbod.bo;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.VentaAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.VentaAlFiadoDAOImpl;
import pe.edu.pucp.softbod.model.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.VentaAlFiadoDTO;

public class VentaAlFiadoBO {
    
    private final VentaAlFiadoDAO ventaAlFiadoDAO;
    private final VentaBO ventaBO;
    
    public VentaAlFiadoBO(){
        this.ventaAlFiadoDAO = new VentaAlFiadoDAOImpl();
        this.ventaBO = new VentaBO();
    }
    
    public Integer insertar(VentaAlFiadoDTO ventaFiada){
        return this.ventaAlFiadoDAO.insertar(ventaFiada);
    }
    
    public VentaAlFiadoDTO obtenerPorId(Integer ventaFiada_Id){
        return this.ventaAlFiadoDAO.obtenerPorId(ventaFiada_Id);
    }
    
    public ArrayList<VentaAlFiadoDTO> listarTodos(){
        return this.ventaAlFiadoDAO.listarTodos();
    }
    
    public ArrayList<VentaAlFiadoDTO> listarTodosPorAliasCliente(String aliasCliente){
        return this.ventaAlFiadoDAO.listarTodosPorAliasCliente(aliasCliente);
    }
    
    public ArrayList<VentaAlFiadoDTO> listarTodosPorAliasClienteFecha(String aliasCliente, Date fecha){
        return this.ventaAlFiadoDAO.listarTodosPorAliasClienteFecha(aliasCliente, fecha);
    }
    
    public Integer registrarVentaFiadaCompleta(VentaAlFiadoDTO ventaFiada, ArrayList<DetalleVentaDTO> detalles) {
        try {
            // 1. Validar datos de entrada
            if (ventaFiada == null || ventaFiada.getVenta() == null || 
                ventaFiada.getCliente() == null || detalles == null || detalles.isEmpty()) {
                System.err.println("Error: Datos de venta fiada inválidos");
                return null;
            }
            
            // 2. Registrar la venta base completa (con detalles y actualización de stock)
            Integer ventaId = this.ventaBO.registrarVentaCompleta(ventaFiada.getVenta(), detalles);
            
            if (ventaId == null || ventaId <= 0) {
                System.err.println("Error: No se pudo registrar la venta base");
                return null;
            }
            
            // 3. Actualizar el ID de la venta en el DTO
            ventaFiada.getVenta().setVentaId(ventaId);
            
            // 4. Insertar la venta fiada
            Integer ventaFiadaId = this.ventaAlFiadoDAO.insertar(ventaFiada);
            
            if (ventaFiadaId == null || ventaFiadaId <= 0) {
                System.err.println("Error: No se pudo insertar la venta fiada");
                return null;
            }
            // 5. Retornar el ID de la venta fiada
            return ventaFiadaId;
            
        } catch (Exception e) {
            System.err.println("Error al registrar venta fiada completa: " + e.getMessage());
            return null;
        }
    }
    
    public Double calcularTotalVentasFiadasPorPeriodo(Date inicio, Date fin) {
        try {
            if (inicio == null || fin == null) {
                return 0.0;
            }
            
            // Validar que la fecha de inicio no sea posterior a la fecha fin
            if (inicio.after(fin)) {
                System.err.println("Error: Fecha de inicio posterior a fecha fin");
                return 0.0;
            }
            
            ArrayList<VentaAlFiadoDTO> todasLasVentasFiadas = this.ventaAlFiadoDAO.listarTodos();
            
            if (todasLasVentasFiadas == null || todasLasVentasFiadas.isEmpty()) {
                return 0.0;
            }
            
            double totalVentasFiadas = 0.0;
            
            for (VentaAlFiadoDTO ventaFiada : todasLasVentasFiadas) {
                if (ventaFiada.getVenta() != null && 
                    ventaFiada.getVenta().getFecha() != null && 
                    ventaFiada.getVenta().getTotal() != null) {
                    
                    Date fechaVenta = ventaFiada.getVenta().getFecha();
                    
                    // Verificar que la fecha esté dentro del rango
                    if (!fechaVenta.before(inicio) && !fechaVenta.after(fin)) {
                        totalVentasFiadas += ventaFiada.getVenta().getTotal();
                    }
                }
            }
            
            return totalVentasFiadas;
            
        } catch (Exception e) {
            System.err.println("Error al calcular total ventas fiadas por período: " + e.getMessage());
            return 0.0;
        }
    }
}

