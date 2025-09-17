package pe.edu.pucp.softbod.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softbod.daoImp.CategoriaDAOImpl;
import pe.edu.pucp.softbod.model.CategoriaDTO;

public class CategoriaDAOTest {
    
    private CategoriaDAO categoriaDAO;
    
    public CategoriaDAOTest() {
        this.categoriaDAO = new CategoriaDAOImpl();
    }
    
    //@Test
    public void testInsertar() {
        System.out.println("insertar");
        
        CategoriaDTO categoria = new CategoriaDTO();
        
        categoria.setDescripcion("BEBIDAS");
        Integer resultado = this.categoriaDAO.insertar(categoria);
        assertTrue(resultado != 0);
        categoria = new CategoriaDTO();
        
        categoria.setCategoriaId(resultado);
        resultado = this.categoriaDAO.eliminar(categoria);
        assertTrue(resultado == 0);
       
    }
    
}
