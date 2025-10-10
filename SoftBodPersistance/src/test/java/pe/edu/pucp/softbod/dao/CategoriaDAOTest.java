package pe.edu.pucp.softbod.dao;

import pe.edu.pucp.softbod.dao.almacen.CategoriaDAO;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softbod.daoImp.almacen.CategoriaDAOImpl;
import pe.edu.pucp.softbod.model.almacen.CategoriaDTO;

public class CategoriaDAOTest {
    
    private CategoriaDAO categoriaDAO;
    
    public CategoriaDAOTest() {
        this.categoriaDAO = new CategoriaDAOImpl();
    }
    
    private void imprimeCategoria(CategoriaDTO categoria) {
        System.out.println("id: " + categoria.getCategoriaId());
        System.out.println("Descripcion: " + categoria.getDescripcion());
    }
    
//   @Test
    public void testInsertar() {
        System.out.println("insertar-CategoriaDAOTest");
        System.out.println("-------------------------");

        CategoriaDTO categoria = new CategoriaDTO();
        categoria.setDescripcion("Bebidas Alcohólicas");
        
        Integer resultado = this.categoriaDAO.insertar(categoria);
        assert(resultado!=0);
    }
    
    //@Test
    public void testEliminar() {
        System.out.println("eliminar-CategoriaDAOTest");
        System.out.println("-------------------------");

        CategoriaDTO categoria = this.categoriaDAO.obtenerPorId(22);
        assertNotNull(categoria);
        System.out.println("Categoria a eliminar: ");
        imprimeCategoria(categoria);

        Integer resultado = this.categoriaDAO.eliminar(categoria);
        assert(resultado!=0);

        categoria = this.categoriaDAO.obtenerPorId(22);
        assertNull(categoria);
    }
    
    //@Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId-CategoriaDAOTest");
        System.out.println("------------------------------");

        CategoriaDTO categoria = this.categoriaDAO.obtenerPorId(22);
        assertNotNull(categoria);
        imprimeCategoria(categoria);
    }
    
    //@Test
    public void testListarTodos() {
        System.out.println("listarTodos-CategoriaDAOTest");
        System.out.println("----------------------------");

        ArrayList<CategoriaDTO> listaCategorias = this.categoriaDAO.listarTodos();
        assert(!listaCategorias.isEmpty());
        for (Integer i = 0; i < listaCategorias.size(); i++) {
            imprimeCategoria(listaCategorias.get(i));
            System.out.println("----------------------------");
        }
    }
    
}

