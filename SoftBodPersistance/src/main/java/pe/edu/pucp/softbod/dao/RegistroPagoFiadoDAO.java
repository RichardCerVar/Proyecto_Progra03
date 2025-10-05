package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.RegistroPagoFiadoDTO;

public interface RegistroPagoFiadoDAO {
    public Integer insertar(RegistroPagoFiadoDTO registroPagoFiado);
    public RegistroPagoFiadoDTO obtenerPorId(Integer pagoId);
    public ArrayList<RegistroPagoFiadoDTO> listarTodos();  
}
