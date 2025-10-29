package pe.edu.pucp.softbod.services.softbodws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.Date;
import java.util.ArrayList;
import pe.edu.pucp.softbod.bo.trazabilidad.HistorialDeOperacionBO;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;

@WebService(serviceName = "HistorialOperaciones")
public class HistorialOperaciones {

    private HistorialDeOperacionBO historialBO;

    public HistorialOperaciones() {
        this.historialBO = new HistorialDeOperacionBO();
    }

    @WebMethod(operationName = "insertarHistorialOperacion")
    public Integer insertarHistorialOperacion(@WebParam(name = "historial") HistorialOperacionesDTO historial) {
        return this.historialBO.insertar(historial);
    }

    @WebMethod(operationName = "obtenerHistorialOperacionPorId")
    public HistorialOperacionesDTO obtenerHistorialOperacionPorId(@WebParam(name = "historialId") Integer historialId) {
        return this.historialBO.obtenerPorId(historialId);
    }

    @WebMethod(operationName = "listarTodosHistorialOperaciones")
    public ArrayList<HistorialOperacionesDTO> listarTodosHistorialOperaciones() {
        return this.historialBO.listarTodos();
    }

    @WebMethod(operationName = "listarHistorialOperacionesPorUsuario")
    public ArrayList<HistorialOperacionesDTO> listarHistorialOperacionesPorUsuario(@WebParam(name = "usuarioId") Integer usuarioId) {
        return this.historialBO.listarPorUsuario(usuarioId);
    }

    @WebMethod(operationName = "listarHistorialOperacionesPorTabla")
    public ArrayList<HistorialOperacionesDTO> listarHistorialOperacionesPorTabla(@WebParam(name = "nombreTabla") String nombreTabla) {
        return this.historialBO.listarPorTabla(nombreTabla);
    }

    @WebMethod(operationName = "listarHistorialOperacionesPorTipoOperacion")
    public ArrayList<HistorialOperacionesDTO> listarHistorialOperacionesPorTipoOperacion(@WebParam(name = "tipoOperacion") String tipoOperacion) {
        return this.historialBO.listarPorOperacion(tipoOperacion);
    }

    @WebMethod(operationName = "listarHistorialOperacionesPorUsuarioYTabla")
    public ArrayList<HistorialOperacionesDTO> listarHistorialOperacionesPorUsuarioYTabla(@WebParam(name = "usuarioId") Integer usuarioId,
                                                                                           @WebParam(name = "nombreTabla") String nombreTabla) {
        return this.historialBO.listarPorUsuarioYTabla(usuarioId, nombreTabla);
    }

    @WebMethod(operationName = "listarHistorialOperacionesPorTablaYTipoOperacion")
    public ArrayList<HistorialOperacionesDTO> listarHistorialOperacionesPorTablaYTipoOperacion(@WebParam(name = "nombreTabla") String nombreTabla,
                                                                                                 @WebParam(name = "tipoOperacion") String tipoOperacion) {
        return this.historialBO.listarPorTablaYOperacion(nombreTabla, tipoOperacion);
    }

    @WebMethod(operationName = "listarHistorialOperacionesPorFecha")
    public ArrayList<HistorialOperacionesDTO> listarHistorialOperacionesPorFecha(@WebParam(name = "fecha") Date fecha) {
        return this.historialBO.listarPorFecha(fecha);
    }

    @WebMethod(operationName = "listarHistorialOperacionesConFiltros")
    public ArrayList<HistorialOperacionesDTO> listarHistorialOperacionesConFiltros(@WebParam(name = "operacionId") Integer operacionId,
                                                                                     @WebParam(name = "nombreTabla") String nombreTabla,
                                                                                     @WebParam(name = "tipoOperacion") String tipoOperacion,
                                                                                     @WebParam(name = "fechaOperacion") Date fechaOperacion,
                                                                                     @WebParam(name = "usuarioId") Integer usuarioId,
                                                                                     @WebParam(name = "usuario") String usuario,
                                                                                     @WebParam(name = "tipoUsuario") String tipoUsuario,
                                                                                     @WebParam(name = "estado") Boolean estado) {
        return this.historialBO.listarHistorialFiltros(operacionId, nombreTabla, tipoOperacion, fechaOperacion, usuarioId, usuario, tipoUsuario, estado);
    }
}