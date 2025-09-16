package pe.edu.pucp.softbod.model;

public class DetalleDevolucionDTO {
    private Integer devolucionId;
    private Integer productoId;
    private Double subtotal;
    private Double cantidad;
    private Razon_Devolucion razon;
    
    public DetalleDevolucionDTO() {
        devolucionId = null;
        productoId = null;
        subtotal = null;
        cantidad = null;
    }

    public DetalleDevolucionDTO(Integer devolucionId, Integer productoId, Double subtotal, Double cantidad, Razon_Devolucion razon) {
        this.devolucionId = devolucionId;
        this.productoId = productoId;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.razon = razon;
    }

    public Integer getDevolucionId() {
        return devolucionId;
    }

    public void setDevolucionId(Integer devolucionId) {
        this.devolucionId = devolucionId;
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

    public Razon_Devolucion getRazon() {
        return razon;
    }

    public void setRazon(Razon_Devolucion razon) {
        this.razon = razon;
    }
}
