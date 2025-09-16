package pe.edu.pucp.softbod.model;

import java.util.Date;

public class VentasFiadasDTO extends VentasDTO{
    private String clienteId;

    public VentasFiadasDTO() {
        super();
    }

    public VentasFiadasDTO(String clienteId, Integer ventaId, Double total, Tipo_de_pago metodo_pago, Date fecha, String usuario) {
        super(ventaId, total, metodo_pago, fecha, usuario);
        this.clienteId = clienteId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }
}
