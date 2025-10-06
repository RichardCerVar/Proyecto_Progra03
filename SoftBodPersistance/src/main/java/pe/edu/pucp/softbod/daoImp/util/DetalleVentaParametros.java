package pe.edu.pucp.softbod.daoImp.util;

public class DetalleVentaParametros {
    private Integer ventaId;
    private Integer productoId;
    private Boolean estadoProducto;
    
    public DetalleVentaParametros (){
        this.ventaId = null;
        this.productoId = null;
        this.estadoProducto = null;
    }

    public Integer getVentaId() { return ventaId; }

    public void setVentaId(Integer ventaId) { this.ventaId = ventaId; }

    public Integer getProductoId() { return productoId; }

    public void setProductoId(Integer productoId) { this.productoId = productoId;}

    public Boolean getEstadoProducto() { return estadoProducto; }

    public void setEstadoProducto(Boolean estadoProducto) {
        this.estadoProducto = estadoProducto;
    }
    
}
