package pe.edu.pucp.softbod.model;

public class CategoriaDTO {
    private Integer categoriaId;
    private String descripcion;

    public CategoriaDTO() {}

    public CategoriaDTO(Integer categoriaId, String descripcion) {
        this.categoriaId = categoriaId;
        this.descripcion = descripcion;
    }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
