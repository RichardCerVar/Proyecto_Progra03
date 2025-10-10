package pe.edu.pucp.softbod.dao.rrhh;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;

public interface UsuarioDAO {
    public Integer insertar(UsuarioDTO usuario);
    public UsuarioDTO obtenerPorId(Integer usuarioId);
    public UsuarioDTO obtenerCuenta(String correo, String contrasenha);
    public ArrayList<UsuarioDTO> listarPorNombreParcial(String nombreUser);
    public UsuarioDTO obtenerPorCorreo(String emailUser);
    public ArrayList<UsuarioDTO> listarTodos();
    public ArrayList<UsuarioDTO> listarActivos();
    public ArrayList<UsuarioDTO> listarInactivos();
    public Integer modificar(UsuarioDTO usuario);
    public Integer eliminarLogicoUsuario(UsuarioDTO usuario);
}
