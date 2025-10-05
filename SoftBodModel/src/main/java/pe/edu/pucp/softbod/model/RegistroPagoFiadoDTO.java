package pe.edu.pucp.softbod.model;

import pe.edu.pucp.softbod.model.util.Tipo_de_pago;
import java.sql.Date;

public class RegistroPagoFiadoDTO {
    private Integer pagoId;
    private UsuarioDTO usuario;
    private ClienteAlFiadoDTO cliente;
    private Date fecha;
    private Tipo_de_pago metodoPago;
    private Double monto;

    public RegistroPagoFiadoDTO() {}

    public RegistroPagoFiadoDTO(Integer pagoId, UsuarioDTO usuario, ClienteAlFiadoDTO cliente, 
                                  Date fecha, Tipo_de_pago metodoPago, Double monto) {
        this.pagoId = pagoId;
        this.usuario = usuario;
        this.cliente = cliente;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.monto = monto;
    }

    public Integer getPagoId() { return pagoId; }
    public void setPagoId(Integer pagoId) { this.pagoId = pagoId; }
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
    public ClienteAlFiadoDTO getCliente() { return cliente; }
    public void setCliente(ClienteAlFiadoDTO cliente) { this.cliente = cliente; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Tipo_de_pago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(Tipo_de_pago metodoPago) { this.metodoPago = metodoPago; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
}
