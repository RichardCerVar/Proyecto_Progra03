package pe.edu.pucp.softbod.daoImp.trazabilidad;

import pe.edu.pucp.softbod.daoImp.devolucion.DetalleDevolucionDAOImpl;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.dao.trazabilidad.HistorialOperacionesDAO;
import pe.edu.pucp.softbod.daoImp.DAOImplBase;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.daoImp.util.HistorialDeOperacionesParametros;
import pe.edu.pucp.softbod.daoImp.util.HistorialDeOperacionesParametrosBuilder;
import pe.edu.pucp.softbod.model.trazabilidad.HistorialOperacionesDTO;


public class HistorialOperacionesDAOImpl extends DAOImplBase implements HistorialOperacionesDAO {

   private HistorialOperacionesDTO historial;
   
   private final CargarTablas cargarTablas;
    
    public HistorialOperacionesDAOImpl() {
        super("BOD_HISTORIAL_OPERACIONES");
        this.historial=null;
        this.retornarLlavePrimaria=true;
        this.cargarTablas = new CargarTablas();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("OPERACION_ID",true,true));
        this.listaColumnas.add(new Columna("TABLA_AFECTADA",false,false));
        this.listaColumnas.add(new Columna("OPERACION",false,false));
        this.listaColumnas.add(new Columna("FECHA_HORA",false,false));
        this.listaColumnas.add(new Columna("USUARIO_ID",false,false));
    }
    
    @Override //ocurre algo si lo implemento en orden distinto?
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1,this.historial.getTablaAfectada());
        this.statement.setString(2,this.historial.getOperacion().name());
        this.statement.setTimestamp(3,Timestamp.valueOf(this.historial.getFechaHora()));
        this.statement.setInt(4,this.historial.getUsuario().getUsuarioId());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.historial.getOperacionId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.historial = this.cargarTablas.cargarHistorialOperaciones(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.historial = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.historial);
    }
    
    @Override
    public Integer insertar(HistorialOperacionesDTO devolucion) {
        this.historial = devolucion;
        return super.insertar();
    }
    
    @Override
    public HistorialOperacionesDTO obtenerPorId(Integer operacionId) {
        Integer usuarioId = null;
        String nombreTabla = null, tipoOperacion = null, usuario = null, 
               tipoUsuario = null;
        String fechaOperacion = null;
        Boolean estado= null;
        ArrayList<HistorialOperacionesDTO> lista = this.listarHistorialFiltros
                (operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, 
                usuario, tipoUsuario, estado);
        
        if (!lista.isEmpty()){
            this.historial = lista.getFirst();
        }
        return this.historial;
    }
    
    @Override
    public ArrayList<HistorialOperacionesDTO> listarTodos() { 
        Integer operacionId = null, usuarioId = null;
        String nombreTabla = null, tipoOperacion = null, usuario = null, 
               tipoUsuario = null;
        String fechaOperacion = null;
        Boolean estado= null;
        return this.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, 
                usuario, tipoUsuario, estado);
    }
    
    @Override
    public ArrayList<HistorialOperacionesDTO> listarPorUsuario(Integer usuarioId) {
        Integer operacionId = null;
        String nombreTabla = null, tipoOperacion = null, usuario = null, 
               tipoUsuario = null;
        String fechaOperacion = null;
        Boolean estado= null;
        return this.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, 
                usuario, tipoUsuario, estado);
    }

    @Override
    public ArrayList<HistorialOperacionesDTO> listarPorTabla(String nombreTabla) {
        Integer operacionId = null, usuarioId = null;
        String tipoOperacion = null, usuario = null, 
               tipoUsuario = null;
        String fechaOperacion = null;
        Boolean estado= null;
        return this.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, 
                usuario, tipoUsuario, estado);
    }

    @Override
    public ArrayList<HistorialOperacionesDTO> listarPorOperacion(String tipoOperacion) {
        Integer operacionId = null, usuarioId = null;
        String nombreTabla = null, usuario = null, 
               tipoUsuario = null;
        String fechaOperacion = null;
        Boolean estado= null;
        return this.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, 
                usuario, tipoUsuario, estado);
    }

    @Override
    public ArrayList<HistorialOperacionesDTO> listarPorUsuarioYTabla(Integer usuarioId,
                                                String nombreTabla) {
        Integer operacionId = null;
        String tipoOperacion = null, usuario = null, 
               tipoUsuario = null;
        String fechaOperacion = null;
        Boolean estado= null;
        return this.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, 
                usuario, tipoUsuario, estado);
    }

    @Override
    public ArrayList<HistorialOperacionesDTO> listarPorTablaYOperacion(String nombreTabla,
                                                String tipoOperacion) {
        Integer operacionId = null, usuarioId = null;
        String usuario = null, tipoUsuario = null;
        String fechaOperacion = null;
        Boolean estado= null;
        return this.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, 
                usuario, tipoUsuario, estado);
    }

    @Override
    public ArrayList<HistorialOperacionesDTO> listarPorFecha(String fechaOperacion) {
        Integer operacionId = null, usuarioId = null;
        String nombreTabla = null, tipoOperacion = null, usuario = null, 
               tipoUsuario = null;
        Boolean estado= null;
        return this.listarHistorialFiltros(operacionId, nombreTabla,
                tipoOperacion, fechaOperacion, usuarioId, 
                usuario, tipoUsuario, estado);
    }
    
    // quitar del resultset el campo contraseña
    @Override
    public ArrayList<HistorialOperacionesDTO> listarHistorialFiltros
        (Integer operacionId, String nombreTabla, String tipoOperacion, 
         String fechaOperacion, Integer usuarioId, String usuario, 
         String tipoUsuario, Boolean estado){
        String sql = "{CALL TA_PROG3.SP_LISTAR_HISTORIAL_OPERACIONES(?,?,?,?,?,?,?,?)}";
        Object parametros = new HistorialDeOperacionesParametrosBuilder()
                            .conOperacionId(operacionId)
                            .conNombreTabla(nombreTabla)
                            .conTipoOperacion(tipoOperacion)
                            .conFecha(fechaOperacion)
                            .conUsuarioId(usuarioId)
                            .conUsuario(usuario)
                            .conTipoUsuario(tipoUsuario)
                            .conEstado(estado)
                            .BuildHistorialDeOperacionesParametros();
        return (ArrayList <HistorialOperacionesDTO>) 
                super.listarTodos(sql, this::incluirValorDeParametrosDeHistorialOperaciones,
                                    parametros);
    }
        
    private void incluirValorDeParametrosDeHistorialOperaciones (Object parametros){
        HistorialDeOperacionesParametros historialParametros = (HistorialDeOperacionesParametros) parametros;
        try {
            if (historialParametros.getOperacionId()!= null)
                this.statement.setInt(1, historialParametros.getOperacionId());
            else
                this.statement.setNull(1,Types.INTEGER);
            
            if (historialParametros.getNombreTabla()!= null)
                this.statement.setString(2, historialParametros.getNombreTabla());
            else
                this.statement.setNull(2,Types.VARCHAR);
            
            if (historialParametros.getTipoOperacion()!= null)
                this.statement.setString(3, historialParametros.getTipoOperacion());
            else
                this.statement.setNull(3,Types.VARCHAR);
            
            if (historialParametros.getFecha()!= null)
                this.statement.setDate(4, Date.valueOf(historialParametros.getFecha()));
            else
                this.statement.setNull(4,Types.DATE);
            
            if (historialParametros.getUsuarioId()!= null)
                this.statement.setInt(5, historialParametros.getUsuarioId());
            else
                this.statement.setNull(5,Types.INTEGER);
            
            if (historialParametros.getUsuario()!= null)
                this.statement.setString(6, historialParametros.getUsuario());
            else
                this.statement.setNull(6,Types.VARCHAR);
            
            if (historialParametros.getTipoUsuario()!= null)
                this.statement.setString(7, historialParametros.getTipoUsuario());
            else
                this.statement.setNull(7,Types.VARCHAR);
            
            if (historialParametros.getEstado()!= null)
                this.statement.setBoolean(8, historialParametros.getEstado());
            else
                this.statement.setNull(8,Types.TINYINT);
            
        } catch (SQLException ex) {
            Logger.getLogger(DetalleDevolucionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
      
}
