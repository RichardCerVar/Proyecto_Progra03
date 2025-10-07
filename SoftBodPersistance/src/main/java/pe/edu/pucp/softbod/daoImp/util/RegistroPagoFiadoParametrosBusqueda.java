package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class RegistroPagoFiadoParametrosBusqueda {
    private String aliasCliente;
    private Date fechaMaxima;

    public RegistroPagoFiadoParametrosBusqueda() {
        this.aliasCliente = null;
        this.fechaMaxima = null;
    }

    public String getAliasCliente() {
        return aliasCliente;
    }

    public void setAliasCliente(String aliasCliente) {
        this.aliasCliente = aliasCliente;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }
}
