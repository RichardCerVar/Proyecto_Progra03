package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public interface ClienteAlFiadoDAO {
    
    public Integer insertar(ClienteAlFiadoDTO clienteAlFiado);
        
    public Integer modificar(ClienteAlFiadoDTO clienteAlFiado);
    
    public ArrayList<ClienteAlFiadoDTO> listarTodos();  
    
    public ClienteAlFiadoDTO obtenerPorId(String aliasClienteAlFiado);
    
    //public listarActivos();

    //obtenerPorALias(String alias);
    
    ////public Integer eliminar(ClienteAlFiadoDTO clienteAlFiado);
}
