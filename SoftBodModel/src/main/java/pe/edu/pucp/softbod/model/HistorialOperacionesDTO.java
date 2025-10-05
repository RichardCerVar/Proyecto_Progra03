package pe.edu.pucp.softbod.model;

import java.sql.Date;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;

public class HistorialOperacionesDTO {
    private Integer operacionId;
    private String tablaAfectada;
    private Tipo_Operacion operacion;
    private Date fechaHora;
    private UsuarioDTO usuario;

    public HistorialOperacionesDTO() {}

    public HistorialOperacionesDTO(Integer operacionId, String tablaAfectada, 
                                   Tipo_Operacion operacion, Date fechaHora, 
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
    public Date getFechaHora() { return fechaHora; }
    public void setFechaHora(Date fechaHora) { this.fechaHora = fechaHora; }
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
}
