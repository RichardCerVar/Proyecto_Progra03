package pe.edu.pucp.softbod.daoImp.util;


public class RegistroPagoFiadoParametrosBusquedaBuilder {
    private String aliasCliente;
    private String fechaMaxima;

    public RegistroPagoFiadoParametrosBusquedaBuilder() {
        this.aliasCliente = null;
        this.fechaMaxima = null;
    }

    public RegistroPagoFiadoParametrosBusquedaBuilder conAliasCliente(String aliasCliente) {
        this.aliasCliente = aliasCliente;
        return this;
    }

    public RegistroPagoFiadoParametrosBusquedaBuilder conFechaMaxima(String fechaMaxima) {
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

    public String getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(String fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }
}
