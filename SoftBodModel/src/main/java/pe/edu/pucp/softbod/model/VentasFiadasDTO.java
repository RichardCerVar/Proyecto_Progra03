package pe.edu.pucp.softbod.model;

import java.util.Date;

public class VentasFiadasDTO extends VentasDTO{
    private Integer ventaFiadaId;
    private Integer ventaId;
    private Integer clienteId;

    public VentasFiadasDTO() {
        super();
        ventaFiadaId = null;
        clienteId = null;
    }

    public VentasFiadasDTO(Integer ventaFiadaId, Integer clienteId, Integer ventaId, Double total,
                Tipo_de_pago metodo_pago, Date fecha,Integer  usuario_id) {
        super(ventaId, total, metodo_pago, fecha, usuario_id);
        this.ventaFiadaId = ventaFiadaId;
        this.ventaId = ventaId;
        this.clienteId = clienteId;
    }
    
    public Integer getVentaFiadaId() {
        return ventaFiadaId;
    }

    public void setVentaFiadaId(Integer ventaFiadaId) {
        this.ventaFiadaId = ventaFiadaId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

}
