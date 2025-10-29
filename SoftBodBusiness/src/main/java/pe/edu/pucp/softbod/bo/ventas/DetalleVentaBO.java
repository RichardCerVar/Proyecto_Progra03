package pe.edu.pucp.softbod.bo.ventas;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.ventas.DetalleVentaDAO;
import pe.edu.pucp.softbod.daoImp.ventas.DetalleVentaDAOImpl;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;

public class DetalleVentaBO {
    
    private final DetalleVentaDAO detalleVentaDAO;
    
    public DetalleVentaBO (){
        this.detalleVentaDAO = new DetalleVentaDAOImpl();
    }
    
    public Integer insertar(DetalleVentaDTO detalleVenta){//el front lo envia con producto(basta que el objeto tenga solo id), subtotal, cantidad
        return this.detalleVentaDAO.insertar(detalleVenta);
    }
    
    public DetalleVentaDTO obtenerPorId (Integer productoId,Integer ventaId){
        return this.detalleVentaDAO.obtenerPorId(productoId, ventaId);
    }
    
    public ArrayList<DetalleVentaDTO> litarTodos (){
        return this.detalleVentaDAO.listarTodos();
    }
    
    public ArrayList<DetalleVentaDTO> listarPorProducto(Integer productoId){
        return this.detalleVentaDAO.listarPorProducto(productoId);
    }
    
    public ArrayList<DetalleVentaDTO> listarPorVenta(Integer ventaId){
        return this.detalleVentaDAO.listarPorVenta(ventaId);
    }
    
    
}
