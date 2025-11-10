package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class VentaParametrosBusquedaBuilder {
    private Integer ventaId;
    private String fecha;

    public VentaParametrosBusquedaBuilder() {
        this.ventaId = null;
        this.fecha = null;
    }

    public VentaParametrosBusquedaBuilder conVentaId(Integer ventaId) {
        this.ventaId = ventaId;
        return this;
    }

    public VentaParametrosBusquedaBuilder conFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public VentaParametrosBusqueda buildVentaParametrosBusqueda() {
        VentaParametrosBusqueda parametros = new VentaParametrosBusqueda();
        parametros.setVentaId(this.getVentaId());
        parametros.setFecha(this.getFecha());
        return parametros;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
