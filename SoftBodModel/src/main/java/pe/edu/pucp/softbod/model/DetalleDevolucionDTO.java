package pe.edu.pucp.softbod.model;

public class DetalleDevolucionDTO {
    private DevolucionDTO devolucion;
    private ProductoDTO producto;
    private Double subtotal;
    private Integer cantidad;
    private RazonDevolucionDTO razonDevolucion;

    public DetalleDevolucionDTO() {
        this.devolucion = null;
        this.producto = null;
        this.subtotal = null;
        this.cantidad = null;
        this.razonDevolucion = null;
    }

    public DetalleDevolucionDTO(DevolucionDTO devolucion, ProductoDTO producto, Double subtotal, 
                                Integer cantidad, RazonDevolucionDTO razonDevolucion) {
        this.devolucion = devolucion;
        this.producto = producto;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.razonDevolucion = razonDevolucion;
    }

    public DevolucionDTO getDevolucion() { return devolucion; }
    public void setDevolucion(DevolucionDTO devolucion) { this.devolucion = devolucion; }
    public ProductoDTO getProducto() { return producto; }
    public void setProducto(ProductoDTO producto) { this.producto = producto; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public RazonDevolucionDTO getRazonDevolucion() { return razonDevolucion; }
    public void setRazonDevolucion(RazonDevolucionDTO razonDevolucion) { 
        this.razonDevolucion = razonDevolucion; 
    }
}
