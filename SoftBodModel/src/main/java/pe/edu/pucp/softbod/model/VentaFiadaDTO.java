package pe.edu.pucp.softbod.model;

public class VentaFiadaDTO {
    private Integer ventaFiadaId;
    private VentaDTO venta;
    private ClienteAlFiadoDTO cliente;

    public VentaFiadaDTO() {
        this.ventaFiadaId = null;
        this.venta = null;
        this.cliente = null;
    }

    public VentaFiadaDTO(Integer ventaFiadaId, VentaDTO venta, ClienteAlFiadoDTO cliente) {
        this.ventaFiadaId = ventaFiadaId;
        this.venta = venta;
        this.cliente = cliente;
    }

    public Integer getVentaFiadaId() { return ventaFiadaId; }
    public void setVentaFiadaId(Integer ventaFiadaId) { this.ventaFiadaId = ventaFiadaId; }
    public VentaDTO getVenta() { return venta; }
    public void setVenta(VentaDTO venta) { this.venta = venta; }
    public ClienteAlFiadoDTO getCliente() { return cliente; }
    public void setCliente(ClienteAlFiadoDTO cliente) { this.cliente = cliente; }
}
