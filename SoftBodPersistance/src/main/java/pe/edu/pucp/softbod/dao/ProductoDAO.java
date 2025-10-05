package pe.edu.pucp.softbod.dao;

import pe.edu.pucp.softbod.model.ProductoDTO;
import java.util.ArrayList;

public interface ProductoDAO {
    public Integer insertar(ProductoDTO producto);
    public ProductoDTO obtenerPorId(Integer productoId);
    public ArrayList<ProductoDTO> listarTodos();
    public Integer modificar(ProductoDTO producto);
}
