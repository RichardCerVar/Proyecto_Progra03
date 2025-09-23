package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.dao.DevolucionDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.DevolucionDTO;


public class DevolucionDAOImpl extends DAOImplBase implements DevolucionDAO {

    private DevolucionDTO devolucion;
    
    public DevolucionDAOImpl() {
        super("BOD_DEVOLUCION");
        this.devolucion=null;
        this.retornarLlavePrimaria=true;
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
        this.statement.setInt(3,this.devolucion.getUsuarioId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setDouble(1,this.devolucion.getTotal());
        this.statement.setDate(2, this.devolucion.getFecha());
        this.statement.setInt(3,this.devolucion.getUsuarioId());
        this.statement.setInt(4, this.devolucion.getDevolucionId()); 
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,this.devolucion.getDevolucionId());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.devolucion.getDevolucionId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.devolucion = new DevolucionDTO();
        this.devolucion.setDevolucionId(this.resultSet.getInt("DEVOLUCION_ID"));
        this.devolucion.setTotal(this.resultSet.getDouble("TOTAL"));
        this.devolucion.setFecha(this.resultSet.getDate("FECHA"));
        this.devolucion.setUsuario(this.resultSet.getInt("USUARIO_ID"));
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
        this.devolucion = new DevolucionDTO();
        this.devolucion.setDevolucionId(devolucionId);
        super.obtenerPorId(false);
        return this.devolucion;
    }

    @Override
    public ArrayList<DevolucionDTO> listarTodos() {
        return (ArrayList<DevolucionDTO>) super.listarTodos();
    }
   
    @Override
    public Integer modificar(DevolucionDTO devolucion) {
        this.devolucion = devolucion;
        return super.modificar();
    }

    @Override
    public Integer eliminar(DevolucionDTO devolucion) {
        this.devolucion = devolucion;
        return super.eliminar();
    }

}
