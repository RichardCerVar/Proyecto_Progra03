package pe.edu.pucp.softbod.dao.gestclientes;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;

public interface ClienteAlFiadoDAO {
    
    public Integer insertar(ClienteAlFiadoDTO clienteAlFiado);
        
    public Integer modificar(ClienteAlFiadoDTO clienteAlFiado);
    
    public ArrayList<ClienteAlFiadoDTO> listarTodos();  
    
    public ArrayList<ClienteAlFiadoDTO> listarTodosLike(String cadena); 
    
    public ClienteAlFiadoDTO obtenerPorId(Integer clienteId);
    
    public Integer eliminar(ClienteAlFiadoDTO cliente);
    
    //public listarActivos();

    //obtenerPorALias(String alias);
    
    ////public Integer eliminar(ClienteAlFiadoDTO clienteAlFiado);
}
