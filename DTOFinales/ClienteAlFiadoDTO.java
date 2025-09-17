package pe.edu.pucp.softbod.model;

import java.sql.Date;

public class ClienteAlFiadoDTO {
    private Integer clienteId;
    private String alias;
    private String nombre;
    private String telefono;
    private Date fecha_de_pago;
    
    public ClienteAlFiadoDTO(){
        clienteId = null;
        alias = null;
        nombre = null;
        telefono = null;
        fecha_de_pago = null;
    }

    public ClienteAlFiadoDTO(Integer clienteId,String alias, String nombre, 
                                String telefono, Date fecha_de_pago) {
        this.clienteId = clienteId;
        this.alias = alias;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fecha_de_pago = fecha_de_pago;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_de_pago() {
        return fecha_de_pago;
    }

    public void setFecha_de_pago(Date fecha_de_pago) {
        this.fecha_de_pago = fecha_de_pago;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}
