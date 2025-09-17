package pe.edu.pucp.softbod.model;

public class DetalleVentaDTO {
    private Integer venta_Id;
    private Integer producto_Id;
    private Double subtotal;
    private Double cantidad;
    
    public DetalleVentaDTO() {}

    public DetalleVentaDTO(Integer venta_Id, Integer producto_Id, Double subtotal, Double cantidad) {
        this.venta_Id = venta_Id;
        this.producto_Id = producto_Id;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
    }

    public void setVenta_Id(Integer venta_Id) {
        this.venta_Id = venta_Id;
    }


    public Integer getProducto_Id() {
        return producto_Id;
    }

    public void setProducto_Id(Integer producto_Id) {
        this.producto_Id = producto_Id;
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
    public Integer getVenta_Id() {
        return venta_Id;
    }


}


