package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class DetalleDevolucionParametrosBuilder {
    private Integer devolucionId;
    private Integer productoId;
    private String razonDevolucion;
    private Date fecha;
    
    public DetalleDevolucionParametrosBuilder (){
        this.devolucionId = null;
        this.productoId = null;
        this.razonDevolucion = null;
        this.fecha = null;
    }
     
    public DetalleDevolucionParametrosBuilder conDevolucionId (Integer devolucionId){
        this.devolucionId = devolucionId;
        return this;
    }
    
    public DetalleDevolucionParametrosBuilder conProductoId (Integer productoId){
        this.productoId = productoId;
        return this;
    }
    
    public DetalleDevolucionParametrosBuilder conFecha (Date fecha){
        this.fecha = fecha;
        return this;
    }
    
    public DetalleDevolucionParametros BuildDetalleDevolucionParametros(){
        DetalleDevolucionParametros parametros = new DetalleDevolucionParametros();
        parametros.setDevolucionId(devolucionId);
        parametros.setProductoId(productoId);
        parametros.setRazonDevolucion(razonDevolucion);
        parametros.setFecha(fecha);
        return parametros;
    }
    
    public DetalleDevolucionParametrosBuilder conRazonDevolucion (String razonDevolucion){
        this.razonDevolucion = razonDevolucion;
        return this;
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
