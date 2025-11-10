package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class VentaFiadaParametrosBusquedaBuilder {
    private String aliasCliente;
    private Integer ventaFiadaId;
    private String fecha;

    public VentaFiadaParametrosBusquedaBuilder() {
        this.aliasCliente = null;
        this.ventaFiadaId = null;
        this.fecha = null;
    }

    public VentaFiadaParametrosBusquedaBuilder conAliasCliente(String aliasCliente) {
        this.aliasCliente = aliasCliente;
        return this;
    }

    public VentaFiadaParametrosBusquedaBuilder conVentaFiadaId(Integer ventaFiadaId) {
        this.ventaFiadaId = ventaFiadaId;
        return this;
    }

    public VentaFiadaParametrosBusquedaBuilder conFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public VentaFiadaParametrosBusqueda buildVentaFiadaParametrosBusqueda() {
        VentaFiadaParametrosBusqueda parametros = new VentaFiadaParametrosBusqueda();
        parametros.setAliasCliente(this.getAliasCliente());
        parametros.setVentaFiadaId(this.getVentaFiadaId());
        parametros.setFecha(this.getFecha());
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
