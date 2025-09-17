package pe.edu.pucp.softbod.model;

import java.util.Date;

public class VentasDTO {
    private Integer venta_Id;
    private Double total;
    private Tipo_de_pago metodo_pago;
    private Date fecha;
    private Integer usuario_Id;
    
    public VentasDTO() {
        venta_Id = null;
        total = null;
        fecha = null;
        usuario_Id = null;
    }

    public VentasDTO(Integer venta_Id, Double total, Tipo_de_pago metodo_pago, Date fecha, Integer usuario_Id) {
        this.venta_Id = venta_Id;
        this.total = total;
        this.metodo_pago = metodo_pago;
        this.fecha = fecha;
        this.usuario_Id = usuario_Id;
    }

    public Integer getVenta_Id() {
        return venta_Id;
    }

    public void setVenta_Id(Integer venta_Id) {
        this.venta_Id = venta_Id;
    }
    

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Tipo_de_pago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(Tipo_de_pago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getUsuario_Id() {
        return usuario_Id;
    }


    public void setUsuario_Id(Integer usuario_Id) {
        this.usuario_Id = usuario_Id;
    }

    
    


}
