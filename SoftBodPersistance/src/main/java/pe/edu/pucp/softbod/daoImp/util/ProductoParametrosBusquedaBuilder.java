package pe.edu.pucp.softbod.daoImp.util;

public class ProductoParametrosBusquedaBuilder {
    private Boolean activo;
    private String categoria;
    private String nombreProducto;

    public ProductoParametrosBusquedaBuilder() {
        this.activo = null;
        this.categoria = null;
        this.nombreProducto = null;
    }

    public ProductoParametrosBusquedaBuilder conActivo(Boolean activo) {
        this.activo = activo;
        return this;
    }

    public ProductoParametrosBusquedaBuilder conCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProductoParametrosBusquedaBuilder conNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
        return this;
    }

    public ProductoParametrosBusqueda buildProductoParametrosBusqueda() {
        ProductoParametrosBusqueda parametros = new ProductoParametrosBusqueda();
        parametros.setActivo(this.getActivo());
        parametros.setCategoria(this.getCategoria());
        parametros.setNombreProducto(this.getNombreProducto());
        return parametros;
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

