package pe.edu.pucp.softbod.model;

public class DetalleDevolucionDTO {
    private Integer detalleId;
    private Integer devolucionId;
    private Integer productoId;
    private Double subtotal;
    private Double cantidad;
    private Integer razonId;
    
    public DetalleDevolucionDTO() {
        detalleId = null;
        devolucionId = null;
        productoId = null;
        subtotal = null;
        cantidad = null;
        razonId = null;
    }

    public DetalleDevolucionDTO(Integer detalleId, Integer devolucionId, 
            Integer productoId, Double subtotal, Double cantidad, Integer razonId) {
        this.detalleId = detalleId;
        this.devolucionId = devolucionId;
        this.productoId = productoId;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.razonId = razonId;
    }
    
    public Integer getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(Integer detalleId) {
        this.detalleId = detalleId;
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

    public Integer getRazonId() {
        return razonId;
    }

    public void setRazonId(Integer razonId) {
        this.razonId = razonId;
    }

}
