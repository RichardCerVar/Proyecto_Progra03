package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.VentasFiadasDTO;

public interface VentaAlFiadoDAO {
    public Integer insertar(VentasFiadasDTO ventaAlFiado);
    
    public ArrayList<VentasFiadasDTO> listarTodos();
}
