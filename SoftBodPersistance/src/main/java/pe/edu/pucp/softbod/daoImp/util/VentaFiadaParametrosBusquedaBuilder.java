package pe.edu.pucp.softbod.daoImp.util;

public class VentaFiadaParametrosBusquedaBuilder {
    private String aliasCliente;
    private Integer ventaFiadaId;
    
    public VentaFiadaParametrosBusquedaBuilder() {
        this.aliasCliente = null;
        this.ventaFiadaId = null;
    }

    public VentaFiadaParametrosBusquedaBuilder conAliasCliente(String aliasCliente) {
        this.aliasCliente = aliasCliente;
        return this;
    }

    public VentaFiadaParametrosBusquedaBuilder conVentaFiadaId(Integer ventaFiadaId) {
        this.ventaFiadaId = ventaFiadaId;
        return this;
    }

    public VentaFiadaParametrosBusqueda buildVentaFiadaParametrosBusqueda() {
        VentaFiadaParametrosBusqueda parametros = new VentaFiadaParametrosBusqueda();
        parametros.setAliasCliente(this.getAliasCliente());
        parametros.setVentaFiadaId(this.getVentaFiadaId());
        return parametros;
    }

    public String getAliasCliente() {
        return aliasCliente;
    }

    public void setAliasCliente(String aliasCliente) {
        this.aliasCliente = aliasCliente;
    }

    public Integer getVentaFiadaId() {
        return ventaFiadaId;
    }

    public void setVentaFiadaId(Integer ventaFiadaId) {
        this.ventaFiadaId = ventaFiadaId;
    }
}

