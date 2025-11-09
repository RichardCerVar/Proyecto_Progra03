package pe.edu.pucp.softbod.model.ventas;

import java.sql.Date;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;

public class VentaDTO {
    private Integer ventaId;
    private Double total;
    private Tipo_de_pago metodoPago;
    private String fecha;
    private UsuarioDTO usuario;

    public VentaDTO() {
        this.ventaId = null;
        this.total = null;
        this.metodoPago = null;
        this.fecha = null;
        this.usuario = null;
    }

    public VentaDTO(Integer ventaId, Double total, Tipo_de_pago metodoPago, 
                     String fecha, UsuarioDTO usuario) {
        this.ventaId = ventaId;
        this.total = total;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Integer getVentaId() { return ventaId; }
    public void setVentaId(Integer ventaId) { this.ventaId = ventaId; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public Tipo_de_pago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(Tipo_de_pago metodoPago) { this.metodoPago = metodoPago; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
}
