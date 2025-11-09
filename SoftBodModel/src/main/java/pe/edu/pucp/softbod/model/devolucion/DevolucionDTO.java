package pe.edu.pucp.softbod.model.devolucion;

import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;

public class DevolucionDTO {
    private Integer devolucionId;
    private Double total;
    private String fecha;
    private UsuarioDTO usuario;

    public DevolucionDTO() {
        this.devolucionId = null;
        this.total = null;
        this.fecha = null;
        this.usuario = null;
    }

    public DevolucionDTO(Double total, String fecha, UsuarioDTO usuario) {
        this.total = total;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Integer getDevolucionId() { return devolucionId; }
    public void setDevolucionId(Integer devolucionId) { this.devolucionId = devolucionId; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
}
