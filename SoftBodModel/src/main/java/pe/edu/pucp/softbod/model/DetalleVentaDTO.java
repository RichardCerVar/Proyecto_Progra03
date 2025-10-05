package pe.edu.pucp.softbod.model;

public class DetalleVentaDTO {
    private VentaDTO venta;
    private ProductoDTO producto;
    private Double subtotal;
    private Integer cantidad;

    public DetalleVentaDTO() {
        this.venta = null;
        this.producto = null;
        this.subtotal = null;
        this.cantidad = null;
    }

    public DetalleVentaDTO(VentaDTO venta, ProductoDTO producto, Double subtotal, Integer cantidad) {
        this.venta = venta;
        this.producto = producto;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
    }

    public VentaDTO getVenta() { return venta; }
    public void setVenta(VentaDTO venta) { this.venta = venta; }
    public ProductoDTO getProducto() { return producto; }
    public void setProducto(ProductoDTO producto) { this.producto = producto; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}

