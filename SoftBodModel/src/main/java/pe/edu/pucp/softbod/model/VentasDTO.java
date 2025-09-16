package pe.edu.pucp.softbod.model;

import java.util.Date;

public class VentasDTO {
    private Integer ventaId;
    private Double total;
    private Tipo_de_pago metodo_pago;
    private Date fecha;
    private String usuario;
    
    public VentasDTO() {
        ventaId = null;
        total = null;
        fecha = null;
        usuario = null;
    }

    public VentasDTO(Integer ventaId, Double total, Tipo_de_pago metodo_pago, Date fecha, String usuario) {
        this.ventaId = ventaId;
        this.total = total;
        this.metodo_pago = metodo_pago;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Tipo_de_pago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(Tipo_de_pago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
