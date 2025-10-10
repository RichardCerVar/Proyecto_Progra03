package pe.edu.pucp.softbod.bo.rrhh;

import java.sql.Date;
import pe.edu.pucp.softbod.dao.rrhh.UsuarioDAO;
import pe.edu.pucp.softbod.daoImp.rrhh.UsuarioDAOImpl;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;
import pe.edu.pucp.softbod.model.util.Tipo_Usuario;
import java.util.regex.Pattern;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;

public class LoginBO {

    private final UsuarioDAO usuarioDAO;
    private final HistorialDeOperacionBO historialBO;

    public LoginBO() {
        this.usuarioDAO = new UsuarioDAOImpl();
        this.historialBO = new HistorialDeOperacionBO();
    }

    public UsuarioDTO autenticar(String correo, String contrasenha) {
        try {
            // 1. Validar parámetros de entrada
            if (correo == null || correo.trim().isEmpty() || 
                contrasenha == null || contrasenha.trim().isEmpty()) {
                System.err.println("Error: Credenciales vacías");
                return null;
            }

            // 2. Obtener usuario con las credenciales proporcionadas
            UsuarioDTO usuario = usuarioDAO.obtenerCuenta(correo.trim(), contrasenha);
            
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
            
            // 5. Registrar login exitoso en el historial
            registrarLoginEnHistorial(usuario, "LOGIN_EXITOSO");
            
            // 6. Autenticación exitosa
            System.out.println("✓ Autenticación exitosa para usuario: " + usuario.getUsuario());
            return usuario;
            
        } catch (Exception e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
            return null;
        }
    }

    public Boolean verificarUsuarioActivo(String correo) {
        try {
            if (correo == null || correo.trim().isEmpty()) {
                return Boolean.FALSE;
            }
            
            UsuarioDTO usuario = usuarioDAO.obtenerPorCorreo(correo.trim());
            return usuario != null && usuario.getActivo();
            
        } catch (Exception e) {
            System.err.println("Error al verificar usuario activo: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Boolean esAdministrador(String correo, String contrasenha) {
        try {
            UsuarioDTO usuario = autenticar(correo, contrasenha);
            
            if (usuario == null) {
                return Boolean.FALSE;
            }
            
            return usuario.getTipoUsuarios() == Tipo_Usuario.ADMINISTRADOR;
            
        } catch (Exception e) {
            System.err.println("Error al verificar si es administrador: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Boolean esOperario(String correo, String contrasenha) {
        try {
            UsuarioDTO usuario = autenticar(correo, contrasenha);
            
            if (usuario == null) {
                return Boolean.FALSE;
            }
            
            return usuario.getTipoUsuarios() == Tipo_Usuario.OPERARIO;
            
        } catch (Exception e) {
            System.err.println("Error al verificar si es operario: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    public Boolean cambiarContrasenha(String correo, String contrasenhaActual, String nuevaContrasenha) {
        try {
            // 1. Validar parámetros
            if (correo == null || contrasenhaActual == null || nuevaContrasenha == null) {
                System.err.println("Error: Parámetros inválidos");
                return Boolean.FALSE;
            }
            
            // 2. Validar formato de la nueva contraseña
            if (!validarFormatoContrasenha(nuevaContrasenha)) {
                System.err.println("Error: La nueva contraseña no cumple con el formato requerido");
                return Boolean.FALSE;
            }
            
            // 3. Autenticar con contraseña actual
            UsuarioDTO usuario = usuarioDAO.obtenerCuenta(correo, contrasenhaActual);
            
            if (usuario == null) {
                System.err.println("Error: Contraseña actual incorrecta");
                return Boolean.FALSE;
            }

            // 4. Cambiar contraseña
            usuario.setContrasenha(nuevaContrasenha);
            Integer resultado = usuarioDAO.modificar(usuario);
            
            if (resultado == null || resultado <= 0) {
                System.err.println("Error: No se pudo actualizar la contraseña");
                return Boolean.FALSE;
            }
            
            // 5. Registrar cambio de contraseña en historial
            registrarLoginEnHistorial(usuario, "CAMBIO_CONTRASENHA");
            
            System.out.println("✓ Contraseña actualizada exitosamente");
            return Boolean.TRUE;
            
        } catch (Exception e) {
            System.err.println("Error al cambiar contraseña: " + e.getMessage());
            return Boolean.FALSE;
        }
    }
    
    public void cerrarSesion(UsuarioDTO usuario) {
        try {
            if (usuario == null || usuario.getUsuarioId() == null) {
                return;
            }
            
            registrarLoginEnHistorial(usuario, "LOGOUT");
            System.out.println("✓ Sesión cerrada para usuario: " + usuario.getUsuario());
            
        } catch (Exception e) {
            System.err.println("Error al cerrar sesión: " + e.getMessage());
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

    private void registrarLoginEnHistorial(UsuarioDTO usuario, String accion) {
        try {
            HistorialOperacionesDTO historial = new HistorialOperacionesDTO();
            historial.setUsuario(usuario);
            historial.setTablaAfectada("BOD_USUARIOS");
            
            // Mapear acción a tipo de operación
            Tipo_Operacion operacion = switch (accion) {
                case "LOGIN_EXITOSO" -> Tipo_Operacion.CONSULTAR;
                case "LOGOUT" -> Tipo_Operacion.CONSULTAR;
                case "CAMBIO_CONTRASENHA" -> Tipo_Operacion.MODIFICACION;
                default -> Tipo_Operacion.CONSULTAR;
            };
            
            historial.setOperacion(operacion);
            historial.setFechaHora(new Date(System.currentTimeMillis()));
            
            Integer resultado = this.historialBO.insertar(historial);
            
            if (resultado == null || resultado <= 0) {
                System.err.println("Advertencia: No se pudo registrar " + accion + " en el historial");
            }
        } catch (Exception e) {
            // No lanzamos la excepción para no afectar el flujo de login
            System.err.println("Advertencia: Error al registrar en historial: " + e.getMessage());
        }
    }
}