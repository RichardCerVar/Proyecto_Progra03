package pe.edu.pucp.softbod.bo;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.ClienteAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.ClienteAlFiadoDAOImpl;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public class ClienteAlFiadoBO {
    
    private final ClienteAlFiadoDAO clienteAlFiadoDAO;
    
    public ClienteAlFiadoBO (){
        this.clienteAlFiadoDAO = new ClienteAlFiadoDAOImpl();
    }
    
    public Integer insertar(ClienteAlFiadoDTO clienteAlFiado){
        return this.clienteAlFiadoDAO.insertar(clienteAlFiado);
    }
    
    public Integer modificar(ClienteAlFiadoDTO clienteAlFiado){
        return this.clienteAlFiadoDAO.modificar(clienteAlFiado);
    }
    
    public ClienteAlFiadoDTO obtenerPorId (Integer clienteId){
        return this.clienteAlFiadoDAO.obtenerPorId(clienteId);
    }
    
    public ArrayList<ClienteAlFiadoDTO> litarTodos (){
        return this.clienteAlFiadoDAO.listarTodos();
    }
    
    public ArrayList<ClienteAlFiadoDTO> litarTodosLike (String cadena){
        return this.clienteAlFiadoDAO.listarTodosLike(cadena);
    }
    
}
