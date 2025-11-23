
package pe.edu.pucp.softbod.bo.util;

import java.sql.Date;
import java.sql.Timestamp;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;

public abstract class OperacionBOBase {
    protected void registrarEnHistorial(UsuarioDTO usuario, 
                                      String tablaAfectada, 
                                      Tipo_Operacion operacion) {
        
        HistorialOperacionesDTO historial = new HistorialOperacionesDTO();
        historial.setUsuario(usuario);
        historial.setTablaAfectada(tablaAfectada);
        historial.setOperacion(operacion);
        historial.setFechaHora(new Timestamp(System.currentTimeMillis()).toString());
        
        HistorialDeOperacionBO historialBO = new HistorialDeOperacionBO();
        Integer resultado = historialBO.insertar(historial);
 
    }
}
