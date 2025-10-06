package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class DevolucionParametrosBuilder {
    private Integer devolucionId;
    private Integer usuarioId;
    private Date fecha;
    
    public DevolucionParametrosBuilder (){
        this.devolucionId = null;
        this.usuarioId = null;
        this.fecha = null;
    }
    
    public DevolucionParametrosBuilder conDevolucionId (Integer devolucionId){
        this.devolucionId = devolucionId;
        return this;
    }
    
    public DevolucionParametrosBuilder conUsuarioId (Integer usuarioId){
        this.usuarioId = usuarioId;
        return this;
    }
    
    public DevolucionParametrosBuilder conFecha (Date fecha){
        this.fecha = fecha;
        return this;
    }
    
    public DevolucionParametros BuildDevolucionParametros (){
        DevolucionParametros parametros = new DevolucionParametros();
        parametros.setDevolucionId(devolucionId);
        parametros.setUsuarioId(usuarioId);
        parametros.setFecha(fecha);
        return parametros;
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
