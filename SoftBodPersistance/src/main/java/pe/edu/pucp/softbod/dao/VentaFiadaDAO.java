package pe.edu.pucp.softbod.dao;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.model.VentaFiadaDTO;

public interface VentaFiadaDAO {
    public Integer insertar(VentaFiadaDTO ventaFiada);
    public VentaFiadaDTO obtenerPorId (Integer ventaFiada_Id);
    public ArrayList<VentaFiadaDTO> listarTodos();
    public ArrayList<VentaFiadaDTO> listarTodosPorAliasCliente(String aliasCliente);
    public ArrayList<VentaFiadaDTO> listarTodosPorAliasClienteFecha(String aliasCliente,Date fecha);
}
