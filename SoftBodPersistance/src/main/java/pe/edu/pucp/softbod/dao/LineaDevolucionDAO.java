package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.DetalleDevolucionDTO;

public interface LineaDevolucionDAO {
    public Integer insertar(DetalleDevolucionDTO linea);
    public DetalleDevolucionDTO obtenerPorId(Integer idLinea);
    public ArrayList<DetalleDevolucionDTO> listarTodos();
    public Integer modificar(DetalleDevolucionDTO linea);
    public Integer eliminar(DetalleDevolucionDTO linea);
}

