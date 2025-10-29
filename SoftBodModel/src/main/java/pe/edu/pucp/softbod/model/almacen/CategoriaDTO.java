package pe.edu.pucp.softbod.model.almacen;

public class CategoriaDTO {
    private Integer categoriaId;
    private String descripcion;

    public CategoriaDTO() {
        this.categoriaId = null;
        this.descripcion = null;
    }

    public CategoriaDTO(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
