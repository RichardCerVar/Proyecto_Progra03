
package pe.edu.pucp.softbod.model;

public class Linea_VentaDTO {
    private Integer linea_ventaId;
    private Integer devolucionId;
    private Integer productoId;
    private Double cantidad;
    private Double subtotal;
    private String razon;
    
    
    public Linea_VentaDTO (){
        linea_ventaId=null;
        devolucionId=null;
        productoId=null;
        cantidad=null;
        subtotal=null;
        razon=null;
    }
    public Linea_VentaDTO (Integer linea_ventaId,Integer devolucionId, Integer productoId, Double cantidad,Double subtotal,String razon){
        this.linea_ventaId=linea_ventaId;
        this.devolucionId=devolucionId;
        this.productoId=productoId;
        this.cantidad=cantidad;
        this.subtotal=subtotal;
        this.subtotal=subtotal;
        this.razon=razon;
    }


    public Integer getId() {
        return linea_ventaId;
    }


    public void setId(Integer linea_ventaId) {
        this.linea_ventaId = linea_ventaId;
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


    public Double getCantidad() {
        return cantidad;
    }


    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }


    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }


    public String getRazon() {
        return razon;
    }


    public void setRazon(String razon) {
        this.razon = razon;
    }

   
   
    
}
