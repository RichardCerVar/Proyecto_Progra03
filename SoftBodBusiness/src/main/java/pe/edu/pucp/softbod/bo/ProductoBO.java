package pe.edu.pucp.softbod.bo;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.ProductoDAO;
import pe.edu.pucp.softbod.daoImp.ProductoDAOImpl;
import pe.edu.pucp.softbod.model.ProductoDTO;

public class ProductoBO {

    private final ProductoDAO productoDAO;

    public ProductoBO() {
        this.productoDAO = new ProductoDAOImpl();
    }

    public Integer insertar(ProductoDTO producto) {
        return this.productoDAO.insertar(producto);
    }

    public Integer modificar(ProductoDTO producto) {
        return this.productoDAO.modificar(producto);
    }

    public ProductoDTO obtenerPorId(Integer productoId) {
        return this.productoDAO.obtenerPorId(productoId);
    }

    public ArrayList<ProductoDTO> listarTodos() {
        return this.productoDAO.listarTodos();
    }

    public ArrayList<ProductoDTO> listarTodosActivos() {
        return this.productoDAO.listarTodosActivos();
    }

    public ArrayList<ProductoDTO> listarTodosInactivos() {
        return this.productoDAO.listarTodosInactivos();
    }

    public ArrayList<ProductoDTO> listarTodosPorNombre(String nombreProd) {
        return this.productoDAO.listarTodosPorNombre(nombreProd);
    }

    public ArrayList<ProductoDTO> listarTodosPorNombreParcialActivo(String nombreProd) {
        return this.productoDAO.listarTodosPorNombreParcialActivo(nombreProd);
    }

    public ArrayList<ProductoDTO> listarTodosPorNombreParcialInactivo(String nombreProd) {
        return this.productoDAO.listarTodosPorNombreParcialInactivo(nombreProd);
    }

    public ArrayList<ProductoDTO> listarTodosPorCategoria(String nameCategoria) {
        return this.productoDAO.listarTodosPorCategoria(nameCategoria);
    }

    public ArrayList<ProductoDTO> listarTodosActivosPorCategoria(String nameCategoria) {
        return this.productoDAO.listarTodosActivosPorCategoria(nameCategoria);
    }

    public ArrayList<ProductoDTO> listarTodosInactivosPorCategoria(String nameCategoria) {
        return this.productoDAO.listarTodosInactivosPorCategoria(nameCategoria);
    }

    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoria(String nameCategoria, String nombreProd) {
        return this.productoDAO.listarTodosPorNombreParcialYcategoria(nameCategoria, nombreProd);
    }

    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoriaActivo(String nameCategoria, String nombreProd) {
        return this.productoDAO.listarTodosPorNombreParcialYcategoriaActivo(nameCategoria, nombreProd);
    }

    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoriaInactivo(String nameCategoria, String nombreProd) {
        return this.productoDAO.listarTodosPorNombreParcialYcategoriaInactivo(nameCategoria, nombreProd);
    }
}

