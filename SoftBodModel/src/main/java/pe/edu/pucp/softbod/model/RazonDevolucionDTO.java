package pe.edu.pucp.softbod.model;

public class RazonDevolucionDTO {
    private Integer razonDevolucionId;
    private String descripcion;

    public RazonDevolucionDTO() {}

    public RazonDevolucionDTO(Integer razonDevolucionId, String descripcion) {
        this.razonDevolucionId = razonDevolucionId;
        this.descripcion = descripcion;
    }

    public Integer getRazonDevolucionId() { return razonDevolucionId; }
    public void setRazonDevolucionId(Integer razonDevolucionId) { 
        this.razonDevolucionId = razonDevolucionId; 
    }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
