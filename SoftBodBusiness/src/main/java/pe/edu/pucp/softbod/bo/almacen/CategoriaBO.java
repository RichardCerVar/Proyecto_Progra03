package pe.edu.pucp.softbod.bo.almacen;

import pe.edu.pucp.softbod.bo.almacen.ProductoBO;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.almacen.CategoriaDAO;
import pe.edu.pucp.softbod.daoImp.almacen.CategoriaDAOImpl;
import pe.edu.pucp.softbod.model.almacen.CategoriaDTO;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;

public class CategoriaBO {
    
    private final CategoriaDAO categoriaDAO;
    private final ProductoBO productoBO;
    
    public CategoriaBO(){
        this.categoriaDAO = new CategoriaDAOImpl();
        this.productoBO = new ProductoBO();

    }
    
    public Integer insertar(String descripcion){
        CategoriaDTO categoria = new CategoriaDTO(descripcion);
        return this.categoriaDAO.insertar(categoria);
    }
    
    public Integer eliminar(CategoriaDTO categoria){
        return this.categoriaDAO.eliminar(categoria);
    }
    
    public CategoriaDTO obtenerPorId (Integer categoriaId){
        return this.categoriaDAO.obtenerPorId(categoriaId);
    }
    
    public ArrayList<CategoriaDTO> listarTodos (){
        return this.categoriaDAO.listarTodos();
    }
    
    public Boolean categoriaContieneProductos(Integer categoriaId) {
        try {
            if (categoriaId == null) {
                return Boolean.FALSE;
            }
            
            // Obtener la categoría para verificar que existe
            CategoriaDTO categoria = this.categoriaDAO.obtenerPorId(categoriaId);
            
            if (categoria == null || categoria.getDescripcion() == null) {
                System.err.println("Error: Categoría no encontrada");
                return Boolean.FALSE;
            }
            
            // Listar productos de la categoría
            ArrayList<ProductoDTO> productos = 
                this.productoBO.listarTodosPorCategoria(categoria.getDescripcion());
            
            // Si tiene productos, retornar true
            if (productos != null && !productos.isEmpty()) {
                return Boolean.TRUE;
            }
            
            return Boolean.FALSE;
            
        } catch (Exception e) {
            System.err.println("Error al verificar si categoría contiene productos: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
    public Boolean puedeEliminarCategoria(Integer categoriaId) {
        try {
            // Una categoría puede ser eliminada solo si NO contiene productos
            Boolean contieneProductos = categoriaContieneProductos(categoriaId);
            
            if (contieneProductos) {
                System.err.println("Error: No se puede eliminar la categoría porque tiene productos asociados");
                return Boolean.FALSE;
            }
            
            return Boolean.TRUE;
            
        } catch (Exception e) {
            System.err.println("Error al validar si puede eliminar categoría: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Boolean nombreCategoriaYaExiste(String descripcion) {
        try {
            if (descripcion == null || descripcion.trim().isEmpty()) {
                return Boolean.FALSE;
            }
            
            // Obtener todas las categorías
            ArrayList<CategoriaDTO> categorias = this.categoriaDAO.listarTodos();
            
            if (categorias == null || categorias.isEmpty()) {
                return Boolean.FALSE; // No existe
            }
            
            // Verificar si alguna tiene la misma descripción (case-insensitive)
            for (CategoriaDTO categoria : categorias) {
                if (categoria.getDescripcion() != null && 
                    categoria.getDescripcion().trim().equalsIgnoreCase(descripcion.trim())) {
                    return Boolean.TRUE; // Ya existe
                }
            }
            
            return Boolean.FALSE; // No existe
            
        } catch (Exception e) {
            System.err.println("Error al validar nombre de categoría: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
}
