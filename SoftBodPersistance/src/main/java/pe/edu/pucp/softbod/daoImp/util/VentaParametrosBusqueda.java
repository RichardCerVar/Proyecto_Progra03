package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class VentaParametrosBusqueda {
    private Integer ventaId;
    private String fecha;

    public VentaParametrosBusqueda() {
        this.ventaId = null;
        this.fecha = null;
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
