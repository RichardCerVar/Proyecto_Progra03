package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class DetalleVentaParametros {
    private Integer ventaId;
    private Integer productoId;
    private Date fechaVenta;
    
    public DetalleVentaParametros (){
        this.ventaId = null;
        this.productoId = null;
        this.fechaVenta = null;
    }

    public Integer getVentaId() { return ventaId; }

    public void setVentaId(Integer ventaId) { this.ventaId = ventaId; }

    public Integer getProductoId() { return productoId; }

    public void setProductoId(Integer productoId) { this.productoId = productoId;}

    public Date getFechaVenta() { return fechaVenta; }

    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }
    
}
