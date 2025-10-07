package pe.edu.pucp.softbod.daoImp.util;

public class UsuarioParametrosBusqueda {
    private String nombreUsuario;
    private String correo;
    private Boolean activo;

    public UsuarioParametrosBusqueda() {
        this.nombreUsuario = null;
        this.correo = null;
        this.activo = null;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
