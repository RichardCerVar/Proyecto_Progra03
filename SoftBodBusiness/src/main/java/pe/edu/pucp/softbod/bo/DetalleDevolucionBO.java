package pe.edu.pucp.softbod.bo;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.DetalleDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.DetalleDevolucionDAOImpl;
import pe.edu.pucp.softbod.model.DetalleDevolucionDTO;

public class DetalleDevolucionBO {
    
    private final DetalleDevolucionDAO detalleDevolucionDAO;
    
    public DetalleDevolucionBO (){
        this.detalleDevolucionDAO = new DetalleDevolucionDAOImpl();
    }
    
    public Integer insertar(DetalleDevolucionDTO detalleDevolucion){
        return this.detalleDevolucionDAO.insertar(detalleDevolucion);
    }
    
    public DetalleDevolucionDTO obtenerPorId (Integer productoId,Integer devolucionId){
        return this.detalleDevolucionDAO.obtenerPorId(productoId, devolucionId);
    }
    
    public ArrayList<DetalleDevolucionDTO> litarTodos (){
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
