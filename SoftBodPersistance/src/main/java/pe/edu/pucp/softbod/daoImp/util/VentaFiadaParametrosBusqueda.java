package pe.edu.pucp.softbod.daoImp.util;

public class VentaFiadaParametrosBusqueda {
    private String aliasCliente;
    private Integer ventaFiadaId;
    
    public VentaFiadaParametrosBusqueda() {
        this.aliasCliente = null;
        this.ventaFiadaId = null;
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
