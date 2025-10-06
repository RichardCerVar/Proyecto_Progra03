package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class DevolucionParametros {
    private Integer devolucionId;
    private Integer usuarioId;
    private Date fecha;
    
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

    public Date getFecha() { return fecha;}

    public void setFecha(Date fecha) { this.fecha = fecha; }
    
}
