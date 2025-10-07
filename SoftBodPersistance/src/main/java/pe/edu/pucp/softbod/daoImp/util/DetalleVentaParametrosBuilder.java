package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class DetalleVentaParametrosBuilder {
    private Integer ventaId;
    private Integer productoId;
    private Date fechaVenta;
    
    public DetalleVentaParametrosBuilder (){
        this.ventaId = null;
        this.productoId = null;
        this.fechaVenta = null;
    }
    
    public DetalleVentaParametrosBuilder conVentaId (Integer ventaId){
        this.ventaId = ventaId;
        return this;
    }
    
    public DetalleVentaParametrosBuilder conProductoId (Integer productoId){
        this.productoId = productoId;
        return this;
    }
    
    public DetalleVentaParametrosBuilder conFecha (Date fechaVenta){
        this.fechaVenta = fechaVenta;
        return this;
    }
    
    public DetalleVentaParametros BuildDetalleVentaParametros (){
        DetalleVentaParametros parametros = new DetalleVentaParametros();
        parametros.setVentaId(ventaId);
        parametros.setProductoId(productoId);
        parametros.setFechaVenta(fechaVenta);
        return parametros;
    }

    public Integer getVentaId() { return ventaId; }

    public void setVentaId(Integer ventaId) { this.ventaId = ventaId; }

    public Integer getProductoId() { return productoId; }

    public void setProductoId(Integer productoId) { this.productoId = productoId;}
    
    public Date getFechaVenta() { return fechaVenta; }

    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }
    
}
