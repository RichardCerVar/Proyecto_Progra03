package pe.edu.pucp.softbod.model;

public class DetalleVentaDTO {
    private Integer ventaId;
    private Integer productoId;
    private Double subtotal;
    private Double cantidad;
    
    public DetalleVentaDTO() {}

    public DetalleVentaDTO(Integer ventaId, Integer productoId, Double subtotal, Double cantidad) {
        this.ventaId = ventaId;
        this.productoId = productoId;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
