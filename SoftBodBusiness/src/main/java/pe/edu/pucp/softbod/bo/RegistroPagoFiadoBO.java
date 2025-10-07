package pe.edu.pucp.softbod.bo;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.RegistroPagoFiadoDAO;
import pe.edu.pucp.softbod.daoImp.RegistroPagoFiadoDAOImpl;
import pe.edu.pucp.softbod.model.RegistroPagoFiadoDTO;

public class RegistroPagoFiadoBO {
    
    private final RegistroPagoFiadoDAO registroPagoFiadoDAO;
    
    public RegistroPagoFiadoBO(){
        this.registroPagoFiadoDAO = new RegistroPagoFiadoDAOImpl();
    }
    
    public Integer insertar(RegistroPagoFiadoDTO registroPagoFiado){
        return this.registroPagoFiadoDAO.insertar(registroPagoFiado);
    }
    
    public ArrayList<RegistroPagoFiadoDTO> listarTodos(){
        return this.registroPagoFiadoDAO.listarTodos();
    }
    
    public ArrayList<RegistroPagoFiadoDTO> listarTodosPorAliasCliente(String aliasCliente){
        return this.registroPagoFiadoDAO.listarTodosPorAliasCliente(aliasCliente);
    }
    
    public ArrayList<RegistroPagoFiadoDTO> listarTodosPorAliasClienteConFechaFin(String aliasCliente, Date fechaFin){
        return this.registroPagoFiadoDAO.listarTodosPorAliasClienteConFechaFin(aliasCliente, fechaFin);
    }
}

