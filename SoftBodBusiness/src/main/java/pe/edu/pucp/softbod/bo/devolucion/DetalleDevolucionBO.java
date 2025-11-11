package pe.edu.pucp.softbod.bo.devolucion;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.devolucion.DetalleDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.devolucion.DetalleDevolucionDAOImpl;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.DevolucionDTO;
import pe.edu.pucp.softbod.model.devolucion.RazonDevolucionDTO;

public class DetalleDevolucionBO {
    
    private final DetalleDevolucionDAO detalleDevolucionDAO;
    
    public DetalleDevolucionBO (){
        this.detalleDevolucionDAO = new DetalleDevolucionDAOImpl();
    }
    
    public Integer insertar(DetalleDevolucionDTO detalle){
        return this.detalleDevolucionDAO.insertar(detalle);
    }
    
    public DetalleDevolucionDTO obtenerPorId (Integer productoId,Integer devolucionId){
        return this.detalleDevolucionDAO.obtenerPorId(productoId, devolucionId);
    }
    
    public ArrayList<DetalleDevolucionDTO> listarTodos (){
        return this.detalleDevolucionDAO.listarTodos();
    }
    
    public ArrayList<DetalleDevolucionDTO> listarPorProducto(Integer productoId){
        return this.detalleDevolucionDAO.listarPorProducto(productoId);
    }
    
    public ArrayList<DetalleDevolucionDTO> listarPorDevolucion(Integer devolucionId){
        return this.detalleDevolucionDAO.listarPorDevolucion(devolucionId);
    }
    
    public ArrayList<DetalleDevolucionDTO> listarPorRazonDevolucion(String razonDevolucion){
        return this.detalleDevolucionDAO.listarPorRazonDevolucion(razonDevolucion);
    }
    
}
