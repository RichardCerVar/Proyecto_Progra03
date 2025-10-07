package pe.edu.pucp.softbod.bo;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.VentaAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.VentaAlFiadoDAOImpl;
import pe.edu.pucp.softbod.model.VentaAlFiadoDTO;

public class VentaAlFiadoBO {
    
    private final VentaAlFiadoDAO ventaAlFiadoDAO;
    
    public VentaAlFiadoBO(){
        this.ventaAlFiadoDAO = new VentaAlFiadoDAOImpl();
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
}

