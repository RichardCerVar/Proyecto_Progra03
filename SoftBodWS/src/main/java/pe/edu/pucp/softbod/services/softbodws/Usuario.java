package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.rrhh.UsuarioBO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;

@WebService(serviceName = "Usuario")
public class Usuario {
    
    private UsuarioBO usuarioBO;
    
    public Usuario(){
        this.usuarioBO = new UsuarioBO();
    }
    
    @WebMethod(operationName = "insertarUsuario")
    public Integer insertarUsuario(@WebParam(name = "usuario") String usuario,
            @WebParam(name = "correo")  String correo,@WebParam(name = "tipoUsuarios")  String tipoUsuarios,
            @WebParam(name = "contrasenha") String contrasenha,@WebParam(name = "nombre")  String nombre,
            @WebParam(name = "telefono")  String telefono,@WebParam(name = "activo")  Boolean activo) {
        
        return this.usuarioBO.insertar(usuario, correo, tipoUsuarios, contrasenha, nombre, telefono, activo);
    }
    
    @WebMethod(operationName = "modificarUsuario")
    public Integer modificarUsuario(@WebParam(name = "usuarioId") Integer usuarioId,@WebParam(name = "usuario") String usuario,
            @WebParam(name = "correo")  String correo,@WebParam(name = "tipoUsuarios")  String tipoUsuarios,
            @WebParam(name = "contrasenha") String contrasenha,@WebParam(name = "nombre")  String nombre,
            @WebParam(name = "telefono")  String telefono,@WebParam(name = "activo")  Boolean activo) {
        
        return this.usuarioBO.modificar(usuarioId, usuario, tipoUsuarios, correo, contrasenha, nombre, telefono, activo);
    }
    
    @WebMethod(operationName = "eliminarLogicoUsuario")
    public Integer eliminarLogicoUsuario(@WebParam(name = "usuarioId") Integer usuarioId,@WebParam(name = "usuario") String usuario,
            @WebParam(name = "correo")  String correo,@WebParam(name = "tipoUsuarios")  String tipoUsuarios,
            @WebParam(name = "contrasenha") String contrasenha,@WebParam(name = "nombre")  String nombre,
            @WebParam(name = "telefono")  String telefono,@WebParam(name = "activo")  Boolean activo){
        
        return this.usuarioBO.eliminarLogicoUsuario(usuarioId, usuario, tipoUsuarios, correo, contrasenha, nombre, telefono, activo);
    }
    
    @WebMethod(operationName = "obtenerUsuarioPorId")
    public UsuarioDTO obtenerUsuarioPorId(@WebParam(name = "usuarioId") Integer usuarioId) {
        return this.usuarioBO.obtenerPorId(usuarioId);
    }
    
    @WebMethod(operationName = "obtenerCuentaUsuario")
    public UsuarioDTO obtenerCuentaUsuario(@WebParam(name = "correo") String correo,
                    @WebParam(name = "contrasenha") String contrasenha) {
        return this.usuarioBO.obtenerCuenta(correo, contrasenha);
    }
    
    @WebMethod(operationName = "obtenerUsuarioPorCorreo")
    public UsuarioDTO obtenerUsuarioPorCorreo(@WebParam(name = "emailUser") String emailUser) {
        return this.usuarioBO.obtenerPorCorreo(emailUser);
    }
    
    @WebMethod(operationName = "listarTodosUsuarios")
    public ArrayList<UsuarioDTO> listarTodosUsuarios() {
        return this.usuarioBO.listarTodos();
    }
    
    @WebMethod(operationName = "listarUsuariosActivos")
    public ArrayList<UsuarioDTO> listarUsuariosActivos() {
        return this.usuarioBO.listarActivos();
    }
    
    @WebMethod(operationName = "listarUsuariosInactivos")
    public ArrayList<UsuarioDTO> listarUsuariosInactivos() {
        return this.usuarioBO.listarInactivos();
    }
    
    @WebMethod(operationName = "listarUsuariosPorNombreParcial")
    public ArrayList<UsuarioDTO> listarUsuariosPorNombreParcial(@WebParam(name = "nombreUser") String nombreUser) {
        return this.usuarioBO.listarPorNombreParcial(nombreUser);
    }
    
}