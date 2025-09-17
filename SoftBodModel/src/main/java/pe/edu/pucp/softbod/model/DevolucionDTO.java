package pe.edu.pucp.softbod.model;

import java.sql.Date;

public class DevolucionDTO {
    private Integer devolucionId;
    private Double total;
    private Date fecha;
    private Integer usuarioId;
    
    public DevolucionDTO() {
        devolucionId = null;
        total = null;
        fecha = null;
        usuarioId = null;
    }

    public DevolucionDTO(Integer devolucionId, Double total, Date fecha, Integer usuarioId) {
        this.devolucionId = devolucionId;
        this.total = total;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
    }

    public Integer getDevolucionId() {
        return devolucionId;
    }

    public void setDevolucionId(Integer devolucionId) {
        this.devolucionId = devolucionId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
