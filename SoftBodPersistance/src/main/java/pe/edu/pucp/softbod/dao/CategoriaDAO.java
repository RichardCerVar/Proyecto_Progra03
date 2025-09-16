package pe.edu.pucp.softbod.dao;

import pe.edu.pucp.softbod.model.CategoriaDTO;

public interface CategoriaDAO{
    public Integer insertar(CategoriaDTO categoria);
    
    public Integer eliminar(CategoriaDTO categoria);
}
