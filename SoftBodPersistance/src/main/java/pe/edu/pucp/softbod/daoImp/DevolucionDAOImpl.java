package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.DevolucionDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.DevolucionDTO;
import pe.edu.pucp.softbod.db.DBManager;


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
        this.listaColumnas.add(new Columna("USUARIO_INT",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1,this.devolucion.getTotal());
        this.statement.setDate(2,this.devolucion.getFecha());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setDouble(1,this.devolucion.getTotal());
        this.statement.setDate(2, this.devolucion.getFecha());
        this.statement.setInt(3, this.devolucion.getDevolucionId()); 
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,this.devolucion.getDevolucionId());
    }
    
    @Override
    public Integer insertar(DevolucionDTO devolucion) {
        this.devolucion = devolucion;
        return super.insertar();
    }
    
    
    @Override
    public DevolucionDTO obtenerPorId(Integer devolucionId) {
        DevolucionDTO devolucion = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT DEVOLUCION_ID, TOTAL, FECHA, USUARIO_ID"
                    + "FROM BOD_DEVOLUCION WHERE DEVOLUCION_ID = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, devolucionId);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                devolucion = new DevolucionDTO();
                devolucion.setDevolucionId(this.resultSet.getInt("DEVOLUCION_ID"));
                devolucion.setTotal(this.resultSet.getDouble("TOTAL"));
                devolucion.setFecha(this.resultSet.getDate("FECHA"));
                devolucion.setUsuario(this.resultSet.getString("USUARIO_ID"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return devolucion;
    }

    @Override
    public ArrayList<DevolucionDTO> listarTodos() {
        ArrayList<DevolucionDTO> listaDevoluciones = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT DEVOLUCION_ID, TOTAL, FECHA, USUARIO_ID"
                            + "FROM BOD_DEVOLUCION";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                DevolucionDTO devolucion = new DevolucionDTO();
                devolucion.setDevolucionId(this.resultSet.getInt("DEVOLUCION_ID"));
                devolucion.setTotal(this.resultSet.getDouble("TOTAL"));
                devolucion.setFecha(this.resultSet.getDate("FECHA"));
                devolucion.setUsuario(this.resultSet.getString("USUARIO_ID"));
                listaDevoluciones.add(devolucion);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return listaDevoluciones;
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
