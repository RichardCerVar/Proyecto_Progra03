package pe.edu.pucp.softbod.daoImp.util;

public class DetalleDevolucionParametros {
    
    private Integer devolucionId;
    private Integer productoId;
    private String razonDevolucion;
    private Boolean estado;
    
    public DetalleDevolucionParametros (){
        this.devolucionId = null;
        this.productoId = null;
        this.razonDevolucion = null;
        this.estado = null;
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
