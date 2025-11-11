package pe.edu.pucp.softbod.bo.almacen;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.almacen.ProductoDAO;
import pe.edu.pucp.softbod.daoImp.almacen.ProductoDAOImpl;
import pe.edu.pucp.softbod.model.almacen.CategoriaDTO;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import pe.edu.pucp.softbod.model.util.Unidad_Medida;

public class ProductoBO {

    private final ProductoDAO productoDAO;

    public ProductoBO() {
        this.productoDAO = new ProductoDAOImpl();
    }

    public Integer insertar(CategoriaDTO categoria, String nombre, 
                       Double precioUnitario, String unidadMedida, 
                       Integer stock, Integer stockMinimo, Boolean activo) {
        Unidad_Medida uniMedida;
        if (unidadMedida.equals(Unidad_Medida.KILOGRAMO.name()))
            uniMedida = Unidad_Medida.KILOGRAMO;
        else if (unidadMedida.equals(Unidad_Medida.LITRO.name()))
            uniMedida = Unidad_Medida.LITRO;
        else uniMedida = Unidad_Medida.UNIDAD;
        ProductoDTO producto = new ProductoDTO( categoria,  nombre,  precioUnitario,
                uniMedida,  stock,  stockMinimo,  activo);
        return this.productoDAO.insertar(producto);
    }

    public Integer modificar(ProductoDTO producto) {
        return this.productoDAO.modificar(producto);
    }

    public ProductoDTO obtenerPorId(Integer productoId) {
        return this.productoDAO.obtenerPorId(productoId);
    }
    
    public Integer eliminar(ProductoDTO producto){
        return this.productoDAO.eliminar(producto);
    }
    
    public ArrayList<ProductoDTO> listarTodos() {
        return this.productoDAO.listarTodos();
    }
    
    public ArrayList<ProductoDTO> listarProductosConFiltro(Boolean activo,
            String categoria, String nombreProducto){
        return this.productoDAO.listarProductosConFiltro(activo, categoria, nombreProducto);
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
    
}

