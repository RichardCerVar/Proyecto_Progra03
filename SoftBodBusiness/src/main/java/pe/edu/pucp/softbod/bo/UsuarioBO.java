package pe.edu.pucp.softbod.bo;

import java.util.ArrayList;
import java.util.regex.Pattern;
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

    public Boolean validarCorreoUnico(String correo) {
        try {
            if (correo == null || correo.trim().isEmpty()) {
                return Boolean.FALSE;
            }
            
            // Buscar usuario con ese correo
            UsuarioDTO usuario = this.usuarioDAO.obtenerPorCorreo(correo.trim());
            
            // Si no se encuentra ningún usuario, el correo es único
            if (usuario == null || usuario.getUsuarioId() == null) {
                return Boolean.TRUE;
            }
            
            // Si se encontró un usuario, el correo ya existe
            return Boolean.FALSE;
            
        } catch (Exception e) {
            System.err.println("Error al validar correo único: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Boolean validarUsuarioUnico(String nombreUsuario) {
        try {
            if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
                return Boolean.FALSE;
            }
            
            // Buscar usuarios con ese nombre usando búsqueda parcial
            ArrayList<UsuarioDTO> usuarios = 
                this.usuarioDAO.listarPorNombreParcial(nombreUsuario.trim());
            
            if (usuarios == null || usuarios.isEmpty()) {
                return Boolean.TRUE; // No existe, es único
            }
            
            // Verificar si alguno tiene exactamente el mismo nombre de usuario
            for (UsuarioDTO usuario : usuarios) {
                if (usuario.getUsuario() != null && 
                    usuario.getUsuario().equalsIgnoreCase(nombreUsuario.trim())) {
                    return Boolean.FALSE; // Ya existe
                }
            }
            
            return Boolean.TRUE; // No se encontró coincidencia exacta
            
        } catch (Exception e) {
            System.err.println("Error al validar usuario único: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
    public Boolean validarCorreoUnicoParaModificar(Integer usuarioId, String nuevoCorreo) {
        try {
            if (usuarioId == null || nuevoCorreo == null || nuevoCorreo.trim().isEmpty()) {
                return Boolean.FALSE;
            }
            
            // Buscar usuario con ese correo
            UsuarioDTO usuario = this.usuarioDAO.obtenerPorCorreo(nuevoCorreo.trim());
            
            // Si no se encuentra ningún usuario, el correo es único
            if (usuario == null || usuario.getUsuarioId() == null) {
                return Boolean.TRUE;
            }
            
            // Si el usuario encontrado es el mismo que se está modificando, es válido
            if (usuario.getUsuarioId().equals(usuarioId)) {
                return Boolean.TRUE;
            }
            
            // Si es otro usuario diferente, el correo ya existe
            return Boolean.FALSE;
            
        } catch (Exception e) {
            System.err.println("Error al validar correo para modificar: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
    public UsuarioDTO autenticarUsuario(String correo, String contrasenha) {
        try {
            // 1. Validar parámetros de entrada
            if (correo == null || correo.trim().isEmpty() || 
                contrasenha == null || contrasenha.trim().isEmpty()) {
                System.err.println("Error: Credenciales inválidas");
                return null;
            }
            
            // 2. Obtener usuario con las credenciales proporcionadas
            UsuarioDTO usuario = this.usuarioDAO.obtenerCuenta(correo.trim(), contrasenha);
            
            // 3. Verificar si se encontró el usuario
            if (usuario == null || usuario.getUsuarioId() == null) {
                System.err.println("Error: Credenciales incorrectas");
                return null;
            }
            
            // 4. Verificar que el usuario esté activo
            if (!usuario.getActivo()) {
                System.err.println("Error: Usuario inactivo. Contacte al administrador");
                return null;
            }
            
            // 5. Autenticación exitosa
            System.out.println("Autenticación exitosa para usuario: " + usuario.getUsuario());
            return usuario;
            
        } catch (Exception e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
            return null;
        }
    }

    public Boolean validarFormatoContrasenha(String contrasenha) {
        try {
            if (contrasenha == null || contrasenha.trim().isEmpty()) {
                System.err.println("Error: La contraseña no puede estar vacía");
                return Boolean.FALSE;
            }
            
            // Validar longitud mínima
            if (contrasenha.length() < 6) {
                System.err.println("Error: La contraseña debe tener al menos 6 caracteres");
                return Boolean.FALSE;
            }
            
            // Validar que tenga al menos una letra mayúscula
            if (!Pattern.compile("[A-Z]").matcher(contrasenha).find()) {
                System.err.println("Error: La contraseña debe tener al menos una letra mayúscula");
                return Boolean.FALSE;
            }
            
            // Validar que tenga al menos una letra minúscula
            if (!Pattern.compile("[a-z]").matcher(contrasenha).find()) {
                System.err.println("Error: La contraseña debe tener al menos una letra minúscula");
                return Boolean.FALSE;
            }
            
            // Validar que tenga al menos un número
            if (!Pattern.compile("[0-9]").matcher(contrasenha).find()) {
                System.err.println("Error: La contraseña debe tener al menos un número");
                return Boolean.FALSE;
            }
            
            return Boolean.TRUE;
            
        } catch (Exception e) {
            System.err.println("Error al validar formato de contraseña: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
}
