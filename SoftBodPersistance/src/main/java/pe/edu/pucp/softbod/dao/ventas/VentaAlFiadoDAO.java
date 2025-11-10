package pe.edu.pucp.softbod.dao.ventas;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.model.ventas.VentaAlFiadoDTO;

public interface VentaAlFiadoDAO {
    public Integer insertar(VentaAlFiadoDTO ventaFiada);
    public VentaAlFiadoDTO obtenerPorId (Integer ventaFiada_Id);
    public ArrayList<VentaAlFiadoDTO> listarTodos();
    public ArrayList<VentaAlFiadoDTO> listarTodosPorAliasCliente(String aliasCliente);
    public ArrayList<VentaAlFiadoDTO> listarTodosPorAliasClienteFecha(String aliasCliente,String fecha);
}
