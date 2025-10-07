package pe.edu.pucp.softbod.bo;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.RazonDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.RazonDevolucionDAOImpl;
import pe.edu.pucp.softbod.model.RazonDevolucionDTO;

public class RazonDevolucionBO {
    
    private final RazonDevolucionDAO razonDevolucionDAO;
    
    public RazonDevolucionBO(){
        this.razonDevolucionDAO = new RazonDevolucionDAOImpl();
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
}

