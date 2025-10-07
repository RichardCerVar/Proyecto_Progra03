package pe.edu.pucp.softbod.daoImp.util;

public class ProductoParametrosBusqueda {
    private Boolean activo;
    private String categoria;
    private String nombreProducto;

    public ProductoParametrosBusqueda() {
        this.activo = null;
        this.categoria = null;
        this.nombreProducto = null;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}

