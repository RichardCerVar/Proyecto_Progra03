package pe.edu.pucp.softbod.daoImp.util;

public class DetalleDevolucionParametrosBuilder {
    private Integer devolucionId;
    private Integer productoId;
    private String razonDevolucion;
    private Boolean estado;
    
    public DetalleDevolucionParametrosBuilder (){
        this.devolucionId = null;
        this.productoId = null;
        this.razonDevolucion = null;
        this.estado = null;
    }
     
    public DetalleDevolucionParametrosBuilder conDevolucionId (Integer devolucionId){
        this.devolucionId = devolucionId;
        return this;
    }
    
    public DetalleDevolucionParametrosBuilder conProductoId (Integer productoId){
        this.productoId = productoId;
        return this;
    }
    
    public DetalleDevolucionParametrosBuilder conEstado (Boolean estado){
        this.estado = estado;
        return this;
    }
    
    public DetalleDevolucionParametros BuildDetalleDevolucionParametros(){
        DetalleDevolucionParametros parametros = new DetalleDevolucionParametros();
        parametros.setDevolucionId(devolucionId);
        parametros.setProductoId(productoId);
        parametros.setRazonDevolucion(razonDevolucion);
        parametros.setEstado(estado);
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

    public Boolean getEstado() { return estado; }

    public void setEstado(Boolean estado) { this.estado = estado; }
}
