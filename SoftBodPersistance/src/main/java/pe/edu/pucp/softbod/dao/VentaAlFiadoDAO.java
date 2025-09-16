package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;

public interface VentaAlFiadoDAO {
    public Integer insertar(VentaAlFiadoDTO ventaAlFiado);
    
    public ArrayList<VentaAlFiadoDTO> listarTodos();
}
