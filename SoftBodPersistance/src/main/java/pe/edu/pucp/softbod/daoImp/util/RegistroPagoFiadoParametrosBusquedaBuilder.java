package pe.edu.pucp.softbod.daoImp.util;

import java.sql.Date;

public class RegistroPagoFiadoParametrosBusquedaBuilder {
    private String aliasCliente;
    private Date fechaMaxima;

    public RegistroPagoFiadoParametrosBusquedaBuilder() {
        this.aliasCliente = null;
        this.fechaMaxima = null;
    }

    public RegistroPagoFiadoParametrosBusquedaBuilder conAliasCliente(String aliasCliente) {
        this.aliasCliente = aliasCliente;
        return this;
    }

    public RegistroPagoFiadoParametrosBusquedaBuilder conFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
        return this;
    }

    public RegistroPagoFiadoParametrosBusqueda buildRegistroPagoFiadoParametrosBusqueda() {
        RegistroPagoFiadoParametrosBusqueda parametros = new RegistroPagoFiadoParametrosBusqueda();
        parametros.setAliasCliente(this.getAliasCliente());
        parametros.setFechaMaxima(this.getFechaMaxima());
        return parametros;
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
