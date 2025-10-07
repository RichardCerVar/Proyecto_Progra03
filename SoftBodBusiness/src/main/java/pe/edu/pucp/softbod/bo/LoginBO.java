package pe.edu.pucp.softbod.bo;

import pe.edu.pucp.softbod.dao.UsuarioDAO;
import pe.edu.pucp.softbod.daoImp.UsuarioDAOImpl;
import pe.edu.pucp.softbod.model.UsuarioDTO;

public class LoginBO {

    private final UsuarioDAO usuarioDAO;

    public LoginBO() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public UsuarioDTO autenticar(String correo, String contrasenha) {
        if (correo == null || correo.trim().isEmpty() || contrasenha == null || contrasenha.trim().isEmpty()) {
            return null;
        }

        UsuarioDTO usuario = usuarioDAO.obtenerCuenta(correo, contrasenha);
        if (usuario != null && usuario.getActivo()) {
            return usuario;
        }
        return null;
    }

    public Boolean verificarUsuarioActivo(String correo) {
        UsuarioDTO usuario = usuarioDAO.obtenerPorCorreo(correo);
        return usuario != null && usuario.getActivo();
    }

    public Boolean esAdministrador(String correo, String contrasenha) {
        UsuarioDTO usuario = autenticar(correo, contrasenha);
        return usuario != null && "ADMINISTRADOR".equalsIgnoreCase(usuario.getTipoUsuarios().toString());
    }

    public Boolean esOperario(String correo, String contrasenha) {
        UsuarioDTO usuario = autenticar(correo, contrasenha);
        return usuario != null && "OPERARIO".equalsIgnoreCase(usuario.getTipoUsuarios().toString());
    }

    public Boolean cambiarContrasenha(String correo, String contrasenhaActual, String nuevaContrasenha) {
        UsuarioDTO usuario = usuarioDAO.obtenerCuenta(correo, contrasenhaActual);
        if (usuario == null) return false;

        usuario.setContrasenha(nuevaContrasenha);
        Integer filas = usuarioDAO.modificar(usuario);
        return filas != null && filas > 0;
    }
 
}
