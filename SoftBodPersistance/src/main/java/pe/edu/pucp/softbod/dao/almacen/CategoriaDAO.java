package pe.edu.pucp.softbod.dao.almacen;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.almacen.CategoriaDTO;

public interface CategoriaDAO{
    
    public Integer insertar(CategoriaDTO categoria);
    
    public Integer eliminar(CategoriaDTO categoria);
    
    public ArrayList<CategoriaDTO> listarTodos();
    
    public CategoriaDTO obtenerPorId(Integer categoriaId);
}
