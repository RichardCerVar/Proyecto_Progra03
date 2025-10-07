package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import pe.edu.pucp.softbod.model.UsuarioDTO;

public interface UsuariosDAO {
    public Integer insertar(UsuarioDTO usuario);
    public UsuarioDTO obtenerPorId(Integer usuarioId);
    public UsuarioDTO obtenerPorNombre(String nombreUser);
    public ArrayList<UsuarioDTO> listarTodos();
    public ArrayList<UsuarioDTO> listarActivos();
    public Integer modificar(UsuarioDTO usuario);
    public Integer eliminarLogicoUsuario(UsuarioDTO usuario);
}
