package pe.edu.pucp.softbod.bo.devolucion;

import pe.edu.pucp.softbod.bo.devolucion.DetalleDevolucionBO;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.devolucion.RazonDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.devolucion.RazonDevolucionDAOImpl;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.RazonDevolucionDTO;

public class RazonDevolucionBO {
    
    private final RazonDevolucionDAO razonDevolucionDAO;
    private final DetalleDevolucionBO detalleDevolucionBO;
    
    public RazonDevolucionBO(){
        this.razonDevolucionDAO = new RazonDevolucionDAOImpl();
        this.detalleDevolucionBO = new DetalleDevolucionBO();
    }
    
    public Integer insertar(RazonDevolucionDTO razonDevolucion){
        return this.razonDevolucionDAO.insertar(razonDevolucion);
    }
    
    public Integer eliminar(RazonDevolucionDTO razonDevolucion){
        return this.razonDevolucionDAO.eliminar(razonDevolucion);
    }
    
    public RazonDevolucionDTO obtenerPorId(Integer razonId){
        return this.razonDevolucionDAO.obtenerPorId(razonId);
    }
    
    public ArrayList<RazonDevolucionDTO> listarTodos(){
        return this.razonDevolucionDAO.listarTodos();
    }
    
    public ArrayList<RazonDevolucionDTO> listarTodosPorNombreParcial(String nombre){
        return this.razonDevolucionDAO.listarTodosPorNombreParcial(nombre);
    }
    
    public Boolean razonTieneDevolucionesAsociadas(Integer razonId) {
        try {
            if (razonId == null) {
                return Boolean.FALSE;
            }
            
            // Obtener la razón para verificar que existe y obtener su descripción
            RazonDevolucionDTO razon = this.razonDevolucionDAO.obtenerPorId(razonId);
            
            if (razon == null || razon.getDescripcion() == null) {
                System.err.println("Error: Razón de devolución no encontrada");
                return Boolean.FALSE;
            }
            
            // Listar detalles de devolución con esta razón
            ArrayList<DetalleDevolucionDTO> detalles = 
                this.detalleDevolucionBO.listarPorRazonDevolucion(razon.getDescripcion());
            
            // Si tiene detalles asociados, retornar true
            if (detalles != null && !detalles.isEmpty()) {
                return Boolean.TRUE;
            }
            
            return Boolean.FALSE;
            
        } catch (Exception e) {
            System.err.println("Error al verificar devoluciones asociadas: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
    public Boolean puedeEliminarRazon(Integer razonId) {
        try {
            // Una razón puede ser eliminada solo si NO tiene devoluciones asociadas
            Boolean tieneDevolucionesAsociadas = razonTieneDevolucionesAsociadas(razonId);
            
            if (tieneDevolucionesAsociadas) {
                System.err.println("Error: No se puede eliminar la razón porque tiene devoluciones asociadas");
                return Boolean.FALSE;
            }
            
            return Boolean.TRUE;
            
        } catch (Exception e) {
            System.err.println("Error al validar si puede eliminar razón: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
}

