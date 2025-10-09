package pe.edu.pucp.softbod.bo;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.UsuarioDAO;
import pe.edu.pucp.softbod.daoImp.UsuarioDAOImpl;
import pe.edu.pucp.softbod.model.UsuarioDTO;
import pe.edu.pucp.softbod.model.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;

public class UsuarioBO {
    
    private final UsuarioDAO usuarioDAO;
    private final HistorialDeOperacionBO historialBO;
    private final LoginBO loginBO; // Para reutilizar validación de contraseña
    
    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAOImpl();
        this.historialBO = new HistorialDeOperacionBO();
        this.loginBO = new LoginBO();
    }
    
    public Integer insertar(UsuarioDTO usuario) {
        try {
            // 1. Validar datos de entrada
            if (usuario == null) {
                System.err.println("Error: Usuario no puede ser null");
                return null;
            }
            
            // 2. Validar que la contraseña cumpla con el formato requerido
            if (usuario.getContrasenha() == null || 
                !loginBO.validarFormatoContrasenha(usuario.getContrasenha())) {
                System.err.println("Error: La contraseña no cumple con el formato requerido");
                return null;
            }
            
            // 3. Validar que el correo sea único
            if (!validarCorreoUnico(usuario.getCorreo())) {
                System.err.println("Error: El correo ya está registrado");
                return null;
            }
            
            // 4. Validar que el nombre de usuario sea único
            if (!validarUsuarioUnico(usuario.getUsuario())) {
                System.err.println("Error: El nombre de usuario ya está registrado");
                return null;
            }
            
            // 5. Insertar usuario
            Integer resultado = this.usuarioDAO.insertar(usuario);
            
            if (resultado != null && resultado > 0) {
                usuario.setUsuarioId(resultado);
                
                // 6. Registrar en historial
                registrarEnHistorial(usuario, "BOD_USUARIOS", Tipo_Operacion.INSERCION);
                
                System.out.println("✓ Usuario insertado exitosamente. ID: " + resultado);
            }
            
            return resultado;
            
        } catch (Exception e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return null;
        }
    }
    
    public Integer modificar(UsuarioDTO usuario) {
        try {
            if (usuario == null || usuario.getUsuarioId() == null) {
                System.err.println("Error: Usuario inválido");
                return null;
            }
            
            // Validar correo único si se está modificando
            if (usuario.getCorreo() != null && 
                !validarCorreoUnicoParaModificar(usuario.getUsuarioId(), usuario.getCorreo())) {
                System.err.println("Error: El correo ya está registrado en otro usuario");
                return null;
            }
            
            Integer resultado = this.usuarioDAO.modificar(usuario);
            
            if (resultado != null && resultado > 0) {
                registrarEnHistorial(usuario, "BOD_USUARIOS", Tipo_Operacion.MODIFICACION);
                System.out.println("✓ Usuario modificado exitosamente");
            }
            
            return resultado;
            
        } catch (Exception e) {
            System.err.println("Error al modificar usuario: " + e.getMessage());
            return null;
        }
    }

    public Integer eliminarLogicoUsuario(UsuarioDTO usuario) {
        try {
            if (usuario == null || usuario.getUsuarioId() == null) {
                System.err.println("Error: Usuario inválido");
                return null;
            }
            
            Integer resultado = this.usuarioDAO.eliminarLogicoUsuario(usuario);
            
            if (resultado != null && resultado > 0) {
                registrarEnHistorial(usuario, "BOD_USUARIOS", Tipo_Operacion.ELIMINACION);
                System.out.println("✓ Usuario eliminado lógicamente");
            }
            
            return resultado;
            
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return null;
        }
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
            
            UsuarioDTO usuario = this.usuarioDAO.obtenerPorCorreo(correo.trim());
            
            // Si no se encuentra ningún usuario, el correo es único
            return usuario == null || usuario.getUsuarioId() == null;
            
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
            
            ArrayList<UsuarioDTO> usuarios = 
                this.usuarioDAO.listarPorNombreParcial(nombreUsuario.trim());
            
            if (usuarios == null || usuarios.isEmpty()) {
                return Boolean.TRUE;
            }
            
            // Verificar si alguno tiene exactamente el mismo nombre de usuario
            for (UsuarioDTO usuario : usuarios) {
                if (usuario.getUsuario() != null && 
                    usuario.getUsuario().equalsIgnoreCase(nombreUsuario.trim())) {
                    return Boolean.FALSE;
                }
            }
            
            return Boolean.TRUE;
            
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
            
            UsuarioDTO usuario = this.usuarioDAO.obtenerPorCorreo(nuevoCorreo.trim());
            
            // Si no se encuentra ningún usuario, el correo es único
            if (usuario == null || usuario.getUsuarioId() == null) {
                return Boolean.TRUE;
            }
            
            // Si el usuario encontrado es el mismo que se está modificando, es válido
            return usuario.getUsuarioId().equals(usuarioId);
            
        } catch (Exception e) {
            System.err.println("Error al validar correo para modificar: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
 
    private void registrarEnHistorial(UsuarioDTO usuario, String tablaAfectada, Tipo_Operacion operacion) {
        try {
            HistorialOperacionesDTO historial = new HistorialOperacionesDTO();
            historial.setUsuario(usuario);
            historial.setTablaAfectada(tablaAfectada);
            historial.setOperacion(operacion);
            historial.setFechaHora(new Date(System.currentTimeMillis()));
            
            Integer resultado = this.historialBO.insertar(historial);
            
            if (resultado == null || resultado <= 0) {
                System.err.println("Advertencia: No se pudo registrar en el historial");
            }
        } catch (Exception e) {
            System.err.println("Advertencia: Error al registrar en historial: " + e.getMessage());
        }
    }
}