package pe.edu.pucp.softbod.dao.almacen;

import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import java.util.ArrayList;

public interface ProductoDAO {
    public Integer insertar(ProductoDTO producto);
    public ProductoDTO obtenerPorId(Integer productoId);
    public Integer modificar(ProductoDTO producto);
    public ArrayList<ProductoDTO> listarTodos();
    public ArrayList<ProductoDTO> listarTodosActivos();
    public ArrayList<ProductoDTO> listarTodosInactivos();
    public ArrayList<ProductoDTO> listarTodosPorNombre(String nombreProd);
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialActivo(String nombreProd);
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialInactivo(String nombreProd);
    public ArrayList<ProductoDTO> listarTodosPorCategoria(String nameCategoria);
    public ArrayList<ProductoDTO> listarTodosActivosPorCategoria(String nameCategoria);
    public ArrayList<ProductoDTO> listarTodosInactivosPorCategoria(String nameCategoria);
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoria(String nameCategoria,String nombreProd);
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoriaActivo(String nameCategoria,String nombreProd);
    public ArrayList<ProductoDTO> listarTodosPorNombreParcialYcategoriaInactivo(String nameCategoria,String nombreProd);
}
