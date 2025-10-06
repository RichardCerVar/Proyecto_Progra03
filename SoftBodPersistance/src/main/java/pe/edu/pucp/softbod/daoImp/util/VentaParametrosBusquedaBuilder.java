package pe.edu.pucp.softbod.daoImp.util;

public class VentaParametrosBusquedaBuilder {
    private Integer ventaId;
    
    public VentaParametrosBusquedaBuilder() {
        this.ventaId = null;
    }
    
    public VentaParametrosBusquedaBuilder conVentaId(Integer ventaId) {
        this.ventaId = ventaId;
        return this;
    }
    
    public VentaParametrosBusqueda BuildVentaParametrosBusqueda() {
        VentaParametrosBusqueda parametros = new VentaParametrosBusqueda();
        parametros.setVentaId(this.getVentaId());
        return parametros;
    }
    
    public Integer getVentaId() {
        return ventaId;
    }
    
    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }
}
