package pe.edu.pucp.softbod.model.trazabilidad;

import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import java.sql.Date;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;

public class HistorialOperacionesDTO {
    private Integer operacionId;
    private String tablaAfectada;
    private Tipo_Operacion operacion;
    private String fechaHora;
    private UsuarioDTO usuario;

    public HistorialOperacionesDTO() {
        this.operacionId = null;
        this.tablaAfectada = null;
        this.operacion = null;
        this.fechaHora = null;
        this.usuario = null;
    }

    public HistorialOperacionesDTO(Integer operacionId, String tablaAfectada, 
                                   Tipo_Operacion operacion, String fechaHora, 
                                   UsuarioDTO usuario) {
        this.operacionId = operacionId;
        this.tablaAfectada = tablaAfectada;
        this.operacion = operacion;
        this.fechaHora = fechaHora;
        this.usuario = usuario;
    }

    public Integer getOperacionId() { return operacionId; }
    public void setOperacionId(Integer operacionId) { this.operacionId = operacionId; }
    public String getTablaAfectada() { return tablaAfectada; }
    public void setTablaAfectada(String tablaAfectada) { this.tablaAfectada = tablaAfectada; }
    public Tipo_Operacion getOperacion() { return operacion; }
    public void setOperacion(Tipo_Operacion operacion) { this.operacion = operacion; }
    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
}
