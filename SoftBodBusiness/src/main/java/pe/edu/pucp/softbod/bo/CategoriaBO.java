package pe.edu.pucp.softbod.bo;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.CategoriaDAO;
import pe.edu.pucp.softbod.daoImp.CategoriaDAOImpl;
import pe.edu.pucp.softbod.model.CategoriaDTO;

public class CategoriaBO {
    
    private final CategoriaDAO categoriaDAO;
    
    public CategoriaBO(){
        this.categoriaDAO = new CategoriaDAOImpl();
    }
    
    public Integer insertar(CategoriaDTO categoria){
        return this.categoriaDAO.insertar(categoria);
    }
    
    public Integer eliminar(CategoriaDTO categoria){
        return this.categoriaDAO.eliminar(categoria);
    }
    
    public CategoriaDTO obtenerPorId (Integer categoriaId){
        return this.categoriaDAO.obtenerPorId(categoriaId);
    }
    
    public ArrayList<CategoriaDTO> litarTodos (){
        return this.categoriaDAO.listarTodos();
    }
    
}
