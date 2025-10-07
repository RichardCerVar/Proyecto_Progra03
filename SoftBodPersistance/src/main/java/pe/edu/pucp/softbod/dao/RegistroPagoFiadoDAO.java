package pe.edu.pucp.softbod.dao;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.model.RegistroPagoFiadoDTO;

public interface RegistroPagoFiadoDAO {
    public Integer insertar(RegistroPagoFiadoDTO registroPagoFiado);
    public ArrayList<RegistroPagoFiadoDTO> listarTodos();  
    public ArrayList<RegistroPagoFiadoDTO> listarTodosPorAliasCliente(String aliasCliente);
    public ArrayList<RegistroPagoFiadoDTO> listarTodosPorAliasClienteConFechaFin(String aliasCliente, Date fechaFin);
}
