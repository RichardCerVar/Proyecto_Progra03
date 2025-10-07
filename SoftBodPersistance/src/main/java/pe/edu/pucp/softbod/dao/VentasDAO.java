package pe.edu.pucp.softbod.dao;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.model.VentaDTO;

public interface VentasDAO {
    public Integer insertar(VentaDTO venta);
    public VentaDTO obtenerPorId (Integer venta_Id);
    public ArrayList<VentaDTO> listarTodos();
    public ArrayList<VentaDTO> listarTodosPorFecha(Date fecha);
}
