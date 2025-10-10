package pe.edu.pucp.softbod.model.devolucion;

public class RazonDevolucionDTO {
    private Integer razonDevolucionId;
    private String descripcion;

    public RazonDevolucionDTO() {
        this.razonDevolucionId = null;
        this.descripcion = null;
    }

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
