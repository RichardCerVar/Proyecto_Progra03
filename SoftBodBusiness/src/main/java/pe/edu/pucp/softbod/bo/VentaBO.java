package pe.edu.pucp.softbod.bo;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.VentaDAO;
import pe.edu.pucp.softbod.daoImp.VentaDAOImpl;
import pe.edu.pucp.softbod.model.VentaDTO;

public class VentaBO {
    
    private final VentaDAO ventaDAO;
    
    public VentaBO(){
        this.ventaDAO = new VentaDAOImpl();
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
}
