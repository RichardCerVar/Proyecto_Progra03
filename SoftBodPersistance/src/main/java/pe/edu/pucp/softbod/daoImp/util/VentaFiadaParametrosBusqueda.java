package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class VentaFiadaParametrosBusqueda {
    private String aliasCliente;
    private Integer ventaFiadaId;
    private String fecha;

    public VentaFiadaParametrosBusqueda() {
        this.aliasCliente = null;
        this.ventaFiadaId = null;
        this.fecha = null;
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
