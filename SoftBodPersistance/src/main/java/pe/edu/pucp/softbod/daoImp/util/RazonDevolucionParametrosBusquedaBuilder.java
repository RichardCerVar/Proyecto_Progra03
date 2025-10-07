package pe.edu.pucp.softbod.daoImp.util;

public class RazonDevolucionParametrosBusquedaBuilder {
    private String descripcion;

    public RazonDevolucionParametrosBusquedaBuilder() {
        this.descripcion = null;
    }

    public RazonDevolucionParametrosBusquedaBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public RazonDevolucionParametrosBusqueda buildRazonDevolucionParametrosBusqueda() {
        RazonDevolucionParametrosBusqueda parametros = new RazonDevolucionParametrosBusqueda();
        parametros.setDescripcion(this.getDescripcion());
        return parametros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
