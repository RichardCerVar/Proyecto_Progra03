package pe.edu.pucp.softbod.model;

import java.sql.Date;

public class RegistroPagosFiadosDTO {
    private Integer pagoId;
    private Integer usuario;
    private Integer cliente_al_fiado;
    private Date fecha;
    private Tipo_de_pago metodo_pago;
    private Double monto;

    public RegistroPagosFiadosDTO() {
        this.pagoId = null;
        this.usuario = null;
        this.cliente_al_fiado = null;
        this.fecha = null;
        this.metodo_pago = null;
        this.monto = null;
    }
    
    public RegistroPagosFiadosDTO(Integer pagoId, Integer usuario, Integer cliente_al_fiado, Date fecha, Tipo_de_pago metodo_pago, Double monto) {
        this.pagoId = pagoId;
        this.usuario = usuario;
        this.cliente_al_fiado = cliente_al_fiado;
        this.fecha = fecha;
        this.metodo_pago = metodo_pago;
        this.monto = monto;
    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getCliente_al_fiado() {
        return cliente_al_fiado;
    }

    public void setCliente_al_fiado(Integer cliente_al_fiado) {
        this.cliente_al_fiado = cliente_al_fiado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tipo_de_pago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(Tipo_de_pago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    
}
