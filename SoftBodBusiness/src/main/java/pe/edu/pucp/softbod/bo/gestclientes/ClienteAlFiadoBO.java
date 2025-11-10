package pe.edu.pucp.softbod.bo.gestclientes;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.gestclientes.ClienteAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.gestclientes.ClienteAlFiadoDAOImpl;
import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;

public class ClienteAlFiadoBO {
    
    private final ClienteAlFiadoDAO clienteAlFiadoDAO;
    
    public ClienteAlFiadoBO (){
        this.clienteAlFiadoDAO = new ClienteAlFiadoDAOImpl();
    }
    
    public Integer insertar(String alias, String nombre, String telefono, 
                             String fechaDePago){
        ClienteAlFiadoDTO clienteAlFiado = new ClienteAlFiadoDTO( alias, nombre,  
                        telefono, fechaDePago,Boolean.TRUE,  0.00);
        return this.clienteAlFiadoDAO.insertar(clienteAlFiado);
    }
    
    public Integer modificar (ClienteAlFiadoDTO clienteModificado){
        return this.clienteAlFiadoDAO.modificar(clienteModificado);
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
 
    public Boolean validarAliasUnico(String alias) {
        try {
            if (alias == null || alias.trim().isEmpty()) {
                return Boolean.FALSE;
            }
            
            // Buscar clientes con el alias usando LIKE
            ArrayList<ClienteAlFiadoDTO> clientes = 
                this.clienteAlFiadoDAO.listarTodosLike(alias.trim());
            
            if (clientes == null || clientes.isEmpty()) {
                return Boolean.TRUE; // No existe, es único
            }
            
            // Verificar si alguno tiene exactamente el mismo alias (case-insensitive)
            for (ClienteAlFiadoDTO cliente : clientes) {
                if (cliente.getAlias() != null && 
                    cliente.getAlias().equalsIgnoreCase(alias.trim())) {
                    return Boolean.FALSE; // Ya existe
                }
            }
            
            return Boolean.TRUE; // No se encontró coincidencia exacta
            
        } catch (Exception e) {
            System.err.println("Error al validar alias único: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
    public Boolean validarAliasUnicoParaModificar(Integer clienteId, String nuevoAlias) {
        try {
            if (clienteId == null || nuevoAlias == null || nuevoAlias.trim().isEmpty()) {
                return Boolean.FALSE;
            }
            
            // Buscar clientes con el alias usando LIKE
            ArrayList<ClienteAlFiadoDTO> clientes = 
                this.clienteAlFiadoDAO.listarTodosLike(nuevoAlias.trim());
            
            if (clientes == null || clientes.isEmpty()) {
                return Boolean.TRUE; // No existe, es único
            }
            
            // Verificar si algún OTRO cliente (diferente al actual) tiene el mismo alias
            for (ClienteAlFiadoDTO cliente : clientes) {
                if (cliente.getAlias() != null && 
                    cliente.getAlias().equalsIgnoreCase(nuevoAlias.trim()) &&
                    !cliente.getClienteId().equals(clienteId)) {
                    return Boolean.FALSE; // Ya existe en otro cliente
                }
            }
            
            return Boolean.TRUE; // No se encontró en otro cliente
            
        } catch (Exception e) {
            System.err.println("Error al validar alias para modificar: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public void bloquearClientePorMorosidad(Integer clienteId) {
        try {
            if (clienteId == null) {
                System.err.println("Error: ID de cliente inválido");
                return;
            }
            
            // Obtener el cliente
            ClienteAlFiadoDTO cliente = this.clienteAlFiadoDAO.obtenerPorId(clienteId);
            
            if (cliente == null || cliente.getClienteId() == null) {
                System.err.println("Error: Cliente no encontrado");
                return;
            }
            
            // Verificar si ya está inactivo
            if (!cliente.getActivo()) {
                System.out.println("El cliente ya está bloqueado");
                return;
            }
            
            // Marcar como inactivo
            cliente.setActivo(false);
            
            // Actualizar en la base de datos
            Integer resultado = this.clienteAlFiadoDAO.modificar(cliente);
            
            if (resultado != null && resultado > 0) {
                System.out.println("Cliente bloqueado exitosamente");
            } else {
                System.err.println("Error al bloquear cliente");
            }
            
        } catch (Exception e) {
            System.err.println("Error al bloquear cliente por morosidad: " + e.getMessage());
        }
    }
    
    public void desbloquearCliente(Integer clienteId) {
        try {
            if (clienteId == null) {
                System.err.println("Error: ID de cliente inválido");
                return;
            }
            
            // Obtener el cliente
            ClienteAlFiadoDTO cliente = this.clienteAlFiadoDAO.obtenerPorId(clienteId);
            
            if (cliente == null || cliente.getClienteId() == null) {
                System.err.println("Error: Cliente no encontrado");
                return;
            }
            
            // Verificar si ya está activo
            if (cliente.getActivo()) {
                System.out.println("El cliente ya está activo");
                return;
            }
            
            // Marcar como activo
            cliente.setActivo(true);
            
            // Actualizar en la base de datos
            Integer resultado = this.clienteAlFiadoDAO.modificar(cliente);
            
            if (resultado != null && resultado > 0) {
                System.out.println("Cliente desbloqueado exitosamente");
            } else {
                System.err.println("Error al desbloquear cliente");
            }
            
        } catch (Exception e) {
            System.err.println("Error al desbloquear cliente: " + e.getMessage());
        }
    }
    
}
