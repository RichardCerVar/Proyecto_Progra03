package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public interface ClienteAlFiadoDAO {
    
    public Integer insertar(ClienteAlFiadoDTO clienteAlFiado);
        
    public ClienteAlFiadoDTO obtenerPorId(String aliasClienteAlFiado);
    
    public ArrayList<ClienteAlFiadoDTO> listarTodos();  
    
    public Integer modificar(ClienteAlFiadoDTO clienteAlFiado);
    
    public Integer eliminar(ClienteAlFiadoDTO clienteAlFiado);
}
