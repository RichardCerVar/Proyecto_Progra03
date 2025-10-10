package pe.edu.pucp.softbod.bo.almacen;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.almacen.ProductoDAO;
import pe.edu.pucp.softbod.daoImp.almacen.ProductoDAOImpl;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;

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

    public Boolean verificarStockDisponible(Integer productoId, Integer cantidadRequerida) {
        try {
            if (productoId == null || cantidadRequerida == null || cantidadRequerida <= 0) {
                return Boolean.FALSE;
            }
            
            ProductoDTO producto = this.productoDAO.obtenerPorId(productoId);
            
            if (producto == null || producto.getProductoId() == null) {
                return Boolean.FALSE;
            }
            
            // Verificar que el producto esté activo
            if (!producto.getActivo()) {
                return Boolean.FALSE;
            }
            
            // Verificar stock suficiente
            return producto.getStock() >= cantidadRequerida;
            
        } catch (Exception e) {
            System.err.println("Error al verificar stock disponible: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
    public Double calcularValorTotalInventarioActivo() {
        try {
            ArrayList<ProductoDTO> productosActivos = this.productoDAO.listarTodosActivos();
            
            if (productosActivos == null || productosActivos.isEmpty()) {
                return 0.0;
            }
            
            double valorTotal = 0.0;
            
            for (ProductoDTO producto : productosActivos) {
                if (producto.getPrecioUnitario() != null && producto.getStock() != null) {
                    valorTotal += (producto.getPrecioUnitario() * producto.getStock());
                }
            }
            
            return valorTotal;
            
        } catch (Exception e) {
            System.err.println("Error al calcular valor total del inventario: " + e.getMessage());
            return 0.0;
        }
    }
    
    public Double calcularValorInventarioActivoPorCategoria(String nombreCategoria) {
        try {
            if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
                return 0.0;
            }
            
            ArrayList<ProductoDTO> productosCategoria = 
                this.productoDAO.listarTodosActivosPorCategoria(nombreCategoria);
            
            if (productosCategoria == null || productosCategoria.isEmpty()) {
                return 0.0;
            }
            
            double valorTotal = 0.0;
            
            for (ProductoDTO producto : productosCategoria) {
                if (producto.getPrecioUnitario() != null && producto.getStock() != null) {
                    valorTotal += (producto.getPrecioUnitario() * producto.getStock());
                }
            }
            
            return valorTotal;
            
        } catch (Exception e) {
            System.err.println("Error al calcular valor inventario por categoría: " + e.getMessage());
            return 0.0;
        }
    }
}

