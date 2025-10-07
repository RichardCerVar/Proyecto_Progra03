package pe.edu.pucp.softbod.daoImp.util;

public class UsuarioParametrosBusquedaBuilder {
    private String nombreUsuario;
    private String correo;
    private Boolean activo;

    public UsuarioParametrosBusquedaBuilder() {
        this.nombreUsuario = null;
        this.correo = null;
        this.activo = null;
    }

    public UsuarioParametrosBusquedaBuilder conNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public UsuarioParametrosBusquedaBuilder conCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public UsuarioParametrosBusquedaBuilder conActivo(Boolean activo) {
        this.activo = activo;
        return this;
    }

    public UsuarioParametrosBusqueda buildUsuarioParametrosBusqueda() {
        UsuarioParametrosBusqueda parametros = new UsuarioParametrosBusqueda();
        parametros.setNombreUsuario(this.getNombreUsuario());
        parametros.setCorreo(this.getCorreo());
        parametros.setActivo(this.getActivo());
        return parametros;
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
