package pe.edu.pucp.softbod.model.gestclientes;

import java.sql.Date;

public class ClienteAlFiadoDTO {
    private Integer clienteId;
    private String alias;
    private String nombre;
    private String telefono;
    private Date fechaDePago;
    private Boolean activo;
    private Double montoDeuda;

    public ClienteAlFiadoDTO() {
        this.clienteId = null;
        this.alias = null;
        this.nombre = null;
        this.telefono = null;
        this.fechaDePago = null;
        this.activo = null;
        this.montoDeuda = null;
    }

    public ClienteAlFiadoDTO(String alias, String nombre, String telefono, 
                             Date fechaDePago, Boolean activo, Double montoDeuda) {
        this.alias = alias;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaDePago = fechaDePago;
        this.activo = activo;
        this.montoDeuda = montoDeuda;
    }

    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public Date getFechaDePago() { return fechaDePago; }
    public void setFechaDePago(Date fechaDePago) { this.fechaDePago = fechaDePago; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public Double getMontoDeuda() {return montoDeuda; }
    public void setMontoDeuda(Double montoDeuda) {this.montoDeuda = montoDeuda;}
}
