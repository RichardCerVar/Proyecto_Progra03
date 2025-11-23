package pe.edu.pucp.softbod.bo.rrhh;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;
import pe.edu.pucp.softbod.bo.util.OperacionBOBase;
import pe.edu.pucp.softbod.dao.rrhh.UsuarioDAO;
import pe.edu.pucp.softbod.daoImp.rrhh.UsuarioDAOImpl;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;
import pe.edu.pucp.softbod.model.util.Tipo_Usuario;

public class UsuarioBO extends OperacionBOBase{
    
    private final UsuarioDAO usuarioDAO;
    private final HistorialDeOperacionBO historialBO;
    private final LoginBO loginBO; // Para reutilizar validación de contraseña
    
    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAOImpl();
        this.historialBO = new HistorialDeOperacionBO();
        this.loginBO = new LoginBO();
    }
    
    public Integer eliminar(Integer usuarioId){
        UsuarioDTO user = new UsuarioDTO();
        user.setUsuarioId(usuarioId);
        
        Integer resultado = this.usuarioDAO.eliminar(user);
        if (resultado != null && resultado > 0) {
            user.setUsuarioId(1);
            // 6. Registrar en historial
            registrarEnHistorial(user, "BOD_USUARIOS", Tipo_Operacion.INSERCION);
                
            System.out.println("✓ Usuario elimnado exitosamente. ID: " + resultado);
        }
        return resultado;
    }
    
    public Integer insertar(String usuario, String correo, String tipoUsuarios,
            String contrasenha, String nombre, String telefono, Boolean activo) {
        Tipo_Usuario tipUser;
        if (tipoUsuarios.equals(Tipo_Usuario.ADMINISTRADOR.name()))
            tipUser = Tipo_Usuario.ADMINISTRADOR;
        else tipUser = Tipo_Usuario.OPERARIO;
        UsuarioDTO usuarioDTO = new UsuarioDTO(0, usuario, tipUser, correo, 
                       contrasenha,  nombre, telefono, activo);
        
        try {
//            // 1. Validar datos de entrada 
//            if (usuarioDTO == null) {
//                System.err.println("Error: Usuario no puede ser null");
//                return null;
//            } NUNCA ES NULA
            
            // 2. Validar que la contraseña cumpla con el formato requerido
//            if (usuarioDTO.getContrasenha() == null || 
//                !loginBO.validarFormatoContrasenha(usuarioDTO.getContrasenha())) {
//                System.err.println("Error: La contraseña no cumple con el formato requerido");
//                return null;
//            }
            
            // 3. Validar que el correo sea único
//            if (!validarCorreoUnico(usuarioDTO.getCorreo())) {
//                System.err.println("Error: El correo ya está registrado");
//                return null;
//            }
//            
//            // 4. Validar que el nombre de usuario sea único
//            if (!validarUsuarioUnico(usuarioDTO.getUsuario())) {
//                System.err.println("Error: El nombre de usuario ya está registrado");
//                return null;
//            }
//            
            // 5. Insertar usuario
            Integer resultado = this.usuarioDAO.insertar(usuarioDTO);
            
            if (resultado != null && resultado > 0) {
                usuarioDTO.setUsuarioId(1);
                
                // 6. Registrar en historial
                registrarEnHistorial(usuarioDTO, "BOD_USUARIOS", Tipo_Operacion.INSERCION);
                
                System.out.println("✓ Usuario insertado exitosamente. ID: " + resultado);
            }
            
            return resultado;
            
        } catch (Exception e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return null;
        }
    }
    
    public Integer modificar(Integer usuarioId, String usuario, String tipoUsuarios, String correo, 
                      String contrasenha, String nombre, String telefono, Boolean activo) {
        Tipo_Usuario tipUser;
        if (tipoUsuarios.equals(Tipo_Usuario.ADMINISTRADOR.name()))
            tipUser = Tipo_Usuario.ADMINISTRADOR;
        else tipUser = Tipo_Usuario.OPERARIO;
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioId, usuario, tipUser, correo, 
                       contrasenha,  nombre, telefono, activo);
        try {
            if (usuario == null || usuarioDTO.getUsuarioId() == null) {
                System.err.println("Error: Usuario inválido");
                return null;
            }
            
            // Validar correo único si se está modificando
            if (usuarioDTO.getCorreo() != null && 
                !validarCorreoUnicoParaModificar(usuarioDTO.getUsuarioId(), usuarioDTO.getCorreo())) {
                System.err.println("Error: El correo ya está registrado en otro usuario");
                return null;
            }
            
            Integer resultado = this.usuarioDAO.modificar(usuarioDTO);
            
            if (resultado != null && resultado > 0) {
                usuarioDTO.setUsuarioId(1);
                registrarEnHistorial(usuarioDTO, "BOD_USUARIOS", Tipo_Operacion.MODIFICACION);
                System.out.println("✓ Usuario modificado exitosamente");
            }
            
            return resultado;
            
        } catch (Exception e) {
            System.err.println("Error al modificar usuario: " + e.getMessage());
            return null;
        }
    }

    public Integer eliminarLogicoUsuario(Integer usuarioId, String usuario, String tipoUsuarios, String correo, 
                      String contrasenha, String nombre, String telefono, Boolean activo) {
        Tipo_Usuario tipUser;
        if (tipoUsuarios.equals(Tipo_Usuario.ADMINISTRADOR.name()))
            tipUser = Tipo_Usuario.ADMINISTRADOR;
        else tipUser = Tipo_Usuario.OPERARIO;
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioId, usuario, tipUser, correo, 
                       contrasenha,  nombre, telefono, activo);
        
        try {
            if (usuario == null || usuarioDTO.getUsuarioId() == null) {
                System.err.println("Error: Usuario inválido");
                return null;
            }
            
            Integer resultado = this.usuarioDAO.eliminarLogicoUsuario(usuarioDTO);
            
            if (resultado != null && resultado > 0) {
                usuarioDTO.setUsuarioId(1);
                registrarEnHistorial(usuarioDTO, "BOD_USUARIOS", Tipo_Operacion.ELIMINACION);
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
}