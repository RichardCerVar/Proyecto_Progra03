package pe.edu.pucp.softbod.bo;

import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.UsuarioDAO;
import pe.edu.pucp.softbod.daoImp.UsuarioDAOImpl;
import pe.edu.pucp.softbod.model.UsuarioDTO;

public class UsuarioBO {
    
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }
    
    public Integer insertar(UsuarioDTO usuario) {
        return this.usuarioDAO.insertar(usuario);
    }
    
    public Integer modificar(UsuarioDTO usuario) {
        return this.usuarioDAO.modificar(usuario);
    }
    
    public Integer eliminarLogicoUsuario(UsuarioDTO usuario) {
        return this.usuarioDAO.eliminarLogicoUsuario(usuario);
    }
    
    public UsuarioDTO obtenerPorId(Integer usuarioId) {
        return this.usuarioDAO.obtenerPorId(usuarioId);
    }
    
    public UsuarioDTO obtenerCuenta(String correo, String contrasenha) {
        return this.usuarioDAO.obtenerCuenta(correo, contrasenha);
    }
    
    public UsuarioDTO obtenerPorCorreo(String emailUser) {
        return this.usuarioDAO.obtenerPorCorreo(emailUser);
    }
    
    public ArrayList<UsuarioDTO> listarTodos() {
        return this.usuarioDAO.listarTodos();
    }
    
    public ArrayList<UsuarioDTO> listarActivos() {
        return this.usuarioDAO.listarActivos();
    }
    
    public ArrayList<UsuarioDTO> listarInactivos() {
        return this.usuarioDAO.listarInactivos();
    }
    
    public ArrayList<UsuarioDTO> listarPorNombreParcial(String nombreUser) {
        return this.usuarioDAO.listarPorNombreParcial(nombreUser);
    }
}
