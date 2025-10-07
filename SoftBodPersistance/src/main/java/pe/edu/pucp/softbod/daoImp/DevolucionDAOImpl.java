package pe.edu.pucp.softbod.daoImp;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.dao.DevolucionDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.daoImp.util.DevolucionParametros;
import pe.edu.pucp.softbod.daoImp.util.DevolucionParametrosBuilder;
import pe.edu.pucp.softbod.model.DevolucionDTO;


public class DevolucionDAOImpl extends DAOImplBase implements DevolucionDAO {

    private DevolucionDTO devolucion;
    
    private final CargarTablas cargarTablas;
    
    public DevolucionDAOImpl() {
        super("BOD_DEVOLUCION");
        this.devolucion=null;
        this.retornarLlavePrimaria=true;
        this.cargarTablas = new CargarTablas();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("DEVOLUCION_ID",true,true));
        this.listaColumnas.add(new Columna("TOTAL",false,false));
        this.listaColumnas.add(new Columna("FECHA",false,false));
        this.listaColumnas.add(new Columna("USUARIO_ID",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1,this.devolucion.getTotal());
        this.statement.setDate(2,this.devolucion.getFecha());
        this.statement.setInt(3,this.devolucion.getUsuario().getUsuarioId());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.devolucion.getDevolucionId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.devolucion = this.cargarTablas.cargarDevolucion(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.devolucion = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.devolucion);
    }
    
    @Override
    public Integer insertar(DevolucionDTO devolucion) {
        this.devolucion = devolucion;
        return super.insertar();
    }
    
    @Override
    public DevolucionDTO obtenerPorId(Integer devolucionId) {
        Integer usuarioId = null;
        Date fecha = null;
        ArrayList<DevolucionDTO> lista = this.listarDevolucionFiltros(devolucionId,
                                        usuarioId, fecha);
        super.obtenerPorId();
        if (!lista.isEmpty()){
            this.devolucion = lista.getFirst();
        }
        return this.devolucion;
    }
    
    @Override
    public ArrayList<DevolucionDTO> listarTodos() { 
        Integer devolucionId = null, usuarioId = null;
        Date fecha = null;
        return this.listarDevolucionFiltros(devolucionId, usuarioId, fecha);
    }
    
    @Override
    public ArrayList<DevolucionDTO> listarPorFecha(Date fecha) {
        Integer devolucionId = null, usuarioId = null;
        return this.listarDevolucionFiltros(devolucionId, usuarioId, fecha);
    }

    @Override
    public ArrayList<DevolucionDTO> listarPorUsuario(Integer usuarioId) {
        Integer devolucionId = null;
        Date fecha = null;
        return this.listarDevolucionFiltros(devolucionId, usuarioId, fecha);
    }

    @Override
    public ArrayList<DevolucionDTO> listarPorUsuarioYFecha(Integer usuarioId, Date fecha) {
        Integer devolucionId = null;
        return this.listarDevolucionFiltros(devolucionId, usuarioId, fecha);
    }
    
    private ArrayList<DevolucionDTO> listarDevolucionFiltros
        (Integer devolucionId, Integer usuarioId,
        Date fecha){
        String sql = "{CALL TA_PROG3.sp_listar_devolucion(?,?,?)}";
        Object parametros = new DevolucionParametrosBuilder()
                            .conDevolucionId(devolucionId)
                            .conUsuarioId(usuarioId)
                            .conFecha(fecha)
                            .BuildDevolucionParametros();
        return (ArrayList <DevolucionDTO>) 
                super.listarTodos(sql, this::incluirValorDeParametrosDeDetalleDevolucion,
                                    parametros);
    }
        
    private void incluirValorDeParametrosDeDetalleDevolucion (Object parametros){
        DevolucionParametros devolucionParametros = (DevolucionParametros) parametros;
        try {
            if (devolucionParametros.getDevolucionId()!= null)
                this.statement.setInt(1, devolucionParametros.getDevolucionId());
            else
                this.statement.setNull(1,Types.INTEGER);
            
            if (devolucionParametros.getUsuarioId()!= null)
                this.statement.setInt(2, devolucionParametros.getUsuarioId());
            else
                this.statement.setNull(2,Types.INTEGER);
            
            if (devolucionParametros.getFecha()!= null)
                this.statement.setDate(3, devolucionParametros.getFecha());
            else
                this.statement.setNull(3,Types.DATE);
        } catch (SQLException ex) {
            Logger.getLogger(DetalleDevolucionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    
}
