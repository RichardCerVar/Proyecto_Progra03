package pe.edu.pucp.softbod.model;

import java.util.Date;

public class RegistroPagosFiadosDTO {
    private String usuario;
    private String cliente_al_fiado;
    private Date fecha;
    private Tipo_de_pago metodo_pago;
    
    public RegistroPagosFiadosDTO(){
        usuario = null;
        cliente_al_fiado = null;
        fecha = null;
        metodo_pago = null;
    }

    public RegistroPagosFiadosDTO(String usuario, String cliente_al_fiado, Date fecha, Tipo_de_pago metodo_pago) {
        this.usuario = usuario;
        this.cliente_al_fiado = cliente_al_fiado;
        this.fecha = fecha;
        this.metodo_pago = metodo_pago;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCliente_al_fiado() {
        return cliente_al_fiado;
    }

    public void setCliente_al_fiado(String cliente_al_fiado) {
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
}
