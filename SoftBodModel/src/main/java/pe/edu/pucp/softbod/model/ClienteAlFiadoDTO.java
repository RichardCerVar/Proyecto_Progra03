package pe.edu.pucp.softbod.model;

import java.sql.Date;
import java.util.ArrayList;

public class ClienteAlFiadoDTO {
    private Integer clienteId;
    private String alias;
    private String nombre;
    private String telefono;
    private Date fechaDePago;
    private Boolean activo;
    private ArrayList<VentaFiadaDTO> ventasFiadas;
    private ArrayList<RegistroPagoFiadoDTO> pagos;

    public ClienteAlFiadoDTO() {
        this.ventasFiadas = new ArrayList<>();
        this.pagos = new ArrayList<>();
        this.clienteId = null;
        this.alias = null;
        this.nombre = null;
        this.telefono = null;
        this.fechaDePago = null;
        this.activo = null;
    }

    public ClienteAlFiadoDTO(Integer clienteId, String alias, String nombre, String telefono, 
                             Date fechaDePago, Boolean activo) {
        this.clienteId = clienteId;
        this.alias = alias;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaDePago = fechaDePago;
        this.activo = activo;
        this.ventasFiadas = new ArrayList<>();
        this.pagos = new ArrayList<>();
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
    public ArrayList<VentaFiadaDTO> getVentasFiadas() { return ventasFiadas; }
    public void setVentasFiadas(ArrayList<VentaFiadaDTO> ventasFiadas) { this.ventasFiadas = ventasFiadas; }
    public ArrayList<RegistroPagoFiadoDTO> getPagos() { return pagos; }
    public void setPagos(ArrayList<RegistroPagoFiadoDTO> pagos) { this.pagos = pagos; }
}
