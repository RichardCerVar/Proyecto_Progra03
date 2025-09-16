package pe.edu.pucp.softbod.model;

public class UsuarioDTO {
    private String usuario_operario;
    private Tipo_Usuario tipo_usuario;
    private String correo;
    private String contrasenha;
    private String nombre;
    private String telefono;
    
    public UsuarioDTO() {
        usuario_operario = null;
        tipo_usuario = null;
        correo = null;
        contrasenha = null;
        nombre = null;
        telefono = null;
    }

    public UsuarioDTO(String usuario_operario, Tipo_Usuario tipo_usuario, String correo, String contrasenha, String nombre, String telefono) {
        this.usuario_operario = usuario_operario;
        this.tipo_usuario = tipo_usuario;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getUsuario_operario() {
        return usuario_operario;
    }

    public void setUsuario_operario(String usuario_operario) {
        this.usuario_operario = usuario_operario;
    }

    public Tipo_Usuario getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(Tipo_Usuario tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
