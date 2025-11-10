package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class DevolucionParametros {
    private Integer devolucionId;
    private Integer usuarioId;
    private String fecha;
    
    public DevolucionParametros (){
        this.devolucionId = null;
        this.usuarioId = null;
        this.fecha = null;
    }

    public Integer getDevolucionId() { return devolucionId; }

    public void setDevolucionId(Integer devolucionId) {
        this.devolucionId = devolucionId;
    }

    public Integer getUsuarioId() { return usuarioId; }

    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public String getFecha() { return fecha;}

    public void setFecha(String fecha) { this.fecha = fecha; }
    
}
