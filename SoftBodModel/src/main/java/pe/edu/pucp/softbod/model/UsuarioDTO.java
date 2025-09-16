package pe.edu.pucp.softbod.model;

public class UsuarioDTO {
    private Integer usuarioId;
    private String usuario;
    private Tipo_Usuario tipo_usuario;
    private String correo;
    private String contrasenha;
    private String nombre;
    private String telefono;
    
    public Integer getUsuario_id() {
        return usuarioId;
    }

    public void setUsuario_id(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioDTO(){
        usuarioId = null;
        usuario = null;
        tipo_usuario = null;
        correo = null;
        contrasenha = null;
        nombre = null;
        telefono = null;
    }

    public UsuarioDTO(Integer usuarioId,String usuario, Tipo_Usuario tipo_usuario, 
                String correo, String contrasenha, String nombre, String telefono) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.tipo_usuario = tipo_usuario;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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