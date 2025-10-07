package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class DetalleDevolucionParametros {
    
    private Integer devolucionId;
    private Integer productoId;
    private String razonDevolucion;
    private Date fecha;
    
    public DetalleDevolucionParametros (){
        this.devolucionId = null;
        this.productoId = null;
        this.razonDevolucion = null;
        this.fecha = null;
    }
    
    public Integer getDevolucionId() { return devolucionId;}

    public void setDevolucionId(Integer devolucionId) {
        this.devolucionId = devolucionId;
    }

    public Integer getProductoId() { return productoId;}

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getRazonDevolucion() { return razonDevolucion;}

    public void setRazonDevolucion(String razonDevolucion) {
        this.razonDevolucion = razonDevolucion;
    }

    public Date getFecha() { return fecha; }

    public void setFecha(Date fecha) { this.fecha = fecha; }

}
