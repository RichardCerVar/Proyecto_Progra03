package pe.edu.pucp.softbod.daoImp.util;

public class DetalleVentaParametrosBuilder {
    private Integer ventaId;
    private Integer productoId;
    private Boolean estadoProducto;
    
    public DetalleVentaParametrosBuilder (){
        this.ventaId = null;
        this.productoId = null;
        this.estadoProducto = null;
    }
    
    public DetalleVentaParametrosBuilder conVentaId (Integer ventaId){
        this.ventaId = ventaId;
        return this;
    }
    
    public DetalleVentaParametrosBuilder conProductoId (Integer productoId){
        this.productoId = productoId;
        return this;
    }
    
    public DetalleVentaParametrosBuilder conEstadoProducto (Boolean estadoProducto){
        this.estadoProducto = estadoProducto;
        return this;
    }
    
    public DetalleVentaParametros BuildDetalleVentaParametros (){
        DetalleVentaParametros parametros = new DetalleVentaParametros();
        parametros.setVentaId(ventaId);
        parametros.setProductoId(productoId);
        parametros.setEstadoProducto(estadoProducto);
        return parametros;
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
