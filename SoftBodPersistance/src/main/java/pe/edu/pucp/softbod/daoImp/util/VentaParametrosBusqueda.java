package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class VentaParametrosBusqueda {
    private Integer ventaId;
    private Date fecha;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
