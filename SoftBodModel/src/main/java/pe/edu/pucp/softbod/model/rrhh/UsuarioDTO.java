package pe.edu.pucp.softbod.model.rrhh;

import pe.edu.pucp.softbod.model.util.Tipo_Usuario;

public class UsuarioDTO {
    private Integer usuarioId;
    private String usuario;
    private Tipo_Usuario tipoUsuarios;
    private String correo;
    private String contrasenha;
    private String nombre;
    private String telefono;
    private Boolean activo;

    public UsuarioDTO() {
        this.usuarioId = null;
        this.usuario = null;
        this.tipoUsuarios = null;
        this.correo = null;
        this.contrasenha = null;
        this.nombre = null;
        this.telefono = null;
        this.activo = null;
    }

    public UsuarioDTO(Integer usuarioId, String usuario, Tipo_Usuario tipoUsuarios, 
                      String correo, String contrasenha, String nombre, String telefono, 
                      Boolean activo) {
        this.usuarioId = usuarioId;
        this.usuario = usuario;
        this.tipoUsuarios = tipoUsuarios;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.nombre = nombre;
        this.telefono = telefono;
        this.activo = activo;
    }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public Tipo_Usuario getTipoUsuarios() { return tipoUsuarios; }
    public void setTipoUsuarios(Tipo_Usuario tipoUsuarios) { this.tipoUsuarios = tipoUsuarios; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasenha() { return contrasenha; }
    public void setContrasenha(String contrasenha) { this.contrasenha = contrasenha; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}