package pe.edu.pucp.softbod.model;

import java.sql.Date;
import java.util.ArrayList;

public class DevolucionDTO {
    private Integer devolucionId;
    private Double total;
    private Date fecha;
    private UsuarioDTO usuario;
    private ArrayList<DetalleDevolucionDTO> detalles;

    public DevolucionDTO() {
        this.detalles = new ArrayList<>();
    }

    public DevolucionDTO(Integer devolucionId, Double total, Date fecha, UsuarioDTO usuario) {
        this.devolucionId = devolucionId;
        this.total = total;
        this.fecha = fecha;
        this.usuario = usuario;
        this.detalles = new ArrayList<>();
    }

    public Integer getDevolucionId() { return devolucionId; }
    public void setDevolucionId(Integer devolucionId) { this.devolucionId = devolucionId; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
    public ArrayList<DetalleDevolucionDTO> getDetalles() { return detalles; }
    public void setDetalles(ArrayList<DetalleDevolucionDTO> detalles) { this.detalles = detalles; }
}
