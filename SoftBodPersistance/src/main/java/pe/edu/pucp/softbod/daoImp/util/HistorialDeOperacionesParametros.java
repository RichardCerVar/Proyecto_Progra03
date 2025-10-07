package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class HistorialDeOperacionesParametros {
    private Integer operacionId;
    private String nombreTabla;
    private String tipoOperacion;
    private Date fecha;
    private Integer usuarioId;
    private String usuario;
    private String tipoUsuario;
    private Boolean estado;
    
    public HistorialDeOperacionesParametros (){
        this.operacionId = null;
        this.nombreTabla = null;
        this.tipoOperacion = null;
        this.fecha = null;
        this.usuarioId = null;
        this.usuario = null;
        this.tipoUsuario = null;
        this.estado = null;
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

    public Boolean getEstado() { return estado; }

    public void setEstado(Boolean estado) { this.estado = estado; }
    
}
