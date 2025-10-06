package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class HistorialDeOperacionesParametrosBuilder {
    private Integer operacionId;
    private String nombreTabla;
    private String tipoOperacion;
    private Date fecha;
    private Integer usuarioId;
    private String usuario;
    private String tipoUsuario;
    private String nombreUsuario;
    
    public HistorialDeOperacionesParametrosBuilder (){
        this.operacionId = null;
        this.nombreTabla = null;
        this.tipoOperacion = null;
        this.fecha = null;
        this.usuarioId = null;
        this.usuario = null;
        this.tipoUsuario = null;
        this.nombreUsuario = null;
    }
    
    public HistorialDeOperacionesParametrosBuilder conOperacionId (Integer operacionId){
        this.operacionId = operacionId;
        return this;
    }
    public HistorialDeOperacionesParametrosBuilder conNombreTabla (String nombreTabla){
        this.nombreTabla = nombreTabla;
        return this;
    }
    public HistorialDeOperacionesParametrosBuilder conTipoOperacion (String tipoOperacion){
        this.tipoOperacion = tipoOperacion;
        return this;
    }
    public HistorialDeOperacionesParametrosBuilder conFecha (Date fecha){
        this.fecha = fecha;
        return this;
    }
    public HistorialDeOperacionesParametrosBuilder conUsuarioId (Integer usuarioId){
        this.usuarioId = usuarioId;
        return this;
    }
    public HistorialDeOperacionesParametrosBuilder conUsuario (String usuario){
        this.usuario = usuario;
        return this;
    }
    public HistorialDeOperacionesParametrosBuilder conTipoUsuario (String tipoUsuario){
        this.tipoUsuario = tipoUsuario;
        return this;
    }
    public HistorialDeOperacionesParametrosBuilder conNombreUsuario (String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
        return this;
    }
    
    public HistorialDeOperacionesParametros BuildHistorialDeOperacionesParametros (){
        HistorialDeOperacionesParametros parametros = new HistorialDeOperacionesParametros();
        parametros.setOperacionId(operacionId);
        parametros.setNombreTabla(nombreTabla);
        parametros.setTipoOperacion(tipoOperacion);
        parametros.setFecha(fecha);
        parametros.setUsuarioId(usuarioId);
        parametros.setUsuario(usuario);
        parametros.setTipoUsuario(tipoUsuario);
        parametros.setNombreUsuario(nombreUsuario);
        return parametros;
    }

    public Integer getOperacionId() { return operacionId; }

    public void setOperacionId(Integer operacionId) {
        this.operacionId = operacionId;
    }

    public String getNombreTabla() { return nombreTabla; }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getTipoOperacion() { return tipoOperacion; }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Date getFecha() { return fecha; }

    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Integer getUsuarioId() { return usuarioId; }

    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getTipoUsuario() { return tipoUsuario; }

    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario;}

    public String getNombreUsuario() { return nombreUsuario; }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
