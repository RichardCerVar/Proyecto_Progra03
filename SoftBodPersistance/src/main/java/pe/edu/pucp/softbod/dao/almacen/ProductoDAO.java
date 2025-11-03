package pe.edu.pucp.softbod.dao.almacen;

import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import java.util.ArrayList;

public interface ProductoDAO {
    public Integer insertar(ProductoDTO producto);
    public ProductoDTO obtenerPorId(Integer productoId);
    public Integer modificar(ProductoDTO producto);
    public ArrayList<ProductoDTO> listarProductosConFiltro(Boolean activo,
            String categoria, String nombreProducto);
    public ArrayList<ProductoDTO> listarTodos();
}
