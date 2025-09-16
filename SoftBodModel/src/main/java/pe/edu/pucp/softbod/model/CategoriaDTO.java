package pe.edu.pucp.softbod.model;
//    ALIMENTOS,
//    BEBIDAS,
//    LIMPIEZA_DEL_HOGAR,
//    HIGIENE,
//    MASCOTAS,
//    OTROS
public class CategoriaDTO {
    private Integer categoriaId;
    private String descripcion;
    
    public CategoriaDTO() {
        this.categoriaId = null;
        this.descripcion = null;
    }

    public CategoriaDTO(Integer categoriaId, String descripcion) {
        this.categoriaId = categoriaId;
        this.descripcion = descripcion;
    }
    
    /**
     * @return the categoriaId
     */
    public Integer getCategoriaId() {
        return categoriaId;
    }

    /**
     * @param categoriaId the categoriaId to set
     */
    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
