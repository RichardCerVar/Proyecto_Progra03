package pe.edu.pucp.softbod.softbodbusiness;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.ClienteAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.ClienteAlFiadoDAOImp;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public class ClienteAlFiadoBO {
    
    private ClienteAlFiadoDAO  clienteAlFiadoDAO;
    
    public ClienteAlFiadoBO(){
        this.clienteAlFiadoDAO = new ClienteAlFiadoDAOImp();
    }
    
    public Integer insertar(String alias, String nombre, 
                            String telefono, Date fecha_de_pago){
        ClienteAlFiadoDTO clienteAlfiadoDTO = new ClienteAlFiadoDTO();
        clienteAlfiadoDTO.setAlias(alias);
        clienteAlfiadoDTO.setNombre(nombre);
        clienteAlfiadoDTO.setTelefono(telefono);
        clienteAlfiadoDTO.setFecha_de_pago(fecha_de_pago);
        return this.clienteAlFiadoDAO.insertar(clienteAlfiadoDTO);
    }
    
    public ClienteAlFiadoDTO obtenerPorId(String aliasClienteAlFiado){
        return this.clienteAlFiadoDAO.obtenerPorId(aliasClienteAlFiado);
    }
    
    public ArrayList<ClienteAlFiadoDTO> listarTodos(){
        return this.clienteAlFiadoDAO.listarTodos();
    }
    //METODO PARA MODIFICAR el ALIAS DEL CLIENTE, NUMERO DE TELEFONO Y FECHA DE PAGO
    public Integer modificar(String busquedaAliasCliente, String nuevoAliasCliente,
                             String nuevoTelefono,Date nuevaFechaDePago){
        ClienteAlFiadoDTO clienteAlfiadoDTO = this.clienteAlFiadoDAO.obtenerPorId(busquedaAliasCliente);
        instanciarAtributosModificar(clienteAlfiadoDTO, nuevoAliasCliente, 
                                              nuevoTelefono, nuevaFechaDePago);
        return this.clienteAlFiadoDAO.modificar(clienteAlfiadoDTO);
    }
    //METODO PARA CAMBIAR SOLO EL ALIAS DEL CLIENTE
    public Integer modificar(String busquedaAliasCliente, String nuevoAliasCliente){
        ClienteAlFiadoDTO clienteAlfiadoDTO = this.clienteAlFiadoDAO.obtenerPorId(busquedaAliasCliente);
        
        instanciarAtributosModificar(clienteAlfiadoDTO, nuevoAliasCliente, 
        clienteAlfiadoDTO.getTelefono(),clienteAlfiadoDTO.getFecha_de_pago());
        
        return this.clienteAlFiadoDAO.modificar(clienteAlfiadoDTO);
    }
    //METODO PARA CAMBIAR SOLO LA FECHA DE PAGO
    public Integer modificar(String busquedaAliasCliente,Date nuevaFecha_de_pago){
        ClienteAlFiadoDTO clienteAlfiadoDTO = this.clienteAlFiadoDAO.obtenerPorId(busquedaAliasCliente);
        
        instanciarAtributosModificar(clienteAlfiadoDTO, clienteAlfiadoDTO.getAlias(), 
        clienteAlfiadoDTO.getTelefono(),nuevaFecha_de_pago);
        
        return this.clienteAlFiadoDAO.modificar(clienteAlfiadoDTO);
    }
    
    public Integer eliminar(String busquedaAliasCliente){
        ClienteAlFiadoDTO clienteAlfiadoDTO = this.clienteAlFiadoDAO.obtenerPorId(busquedaAliasCliente);
        return this.clienteAlFiadoDAO.eliminar(clienteAlfiadoDTO);
    }
    
    public void instanciarAtributosModificar(ClienteAlFiadoDTO clienteAlFiado
               ,String nuevoAlias,String nuevoTelefono,Date nuevaFecha_de_pago){
        clienteAlFiado.setAlias(nuevoAlias);
        clienteAlFiado.setTelefono(nuevoTelefono);
        clienteAlFiado.setFecha_de_pago(nuevaFecha_de_pago);
    }
    
    
}
