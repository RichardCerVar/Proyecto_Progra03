package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.RazonDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.db.DBManager;
import pe.edu.pucp.softbod.model.RazonDevolucionDTO;

public class RazonDevolucionDAOImp extends DAOImplBase implements RazonDevolucionDAO{

    private RazonDevolucionDTO razonDevolucion;

    public RazonDevolucionDAOImp() {
        super("BOD_RAZON_DEVOLUCION");
        this.razonDevolucion = null;
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("RAZON_DEVOLUCION_ID",true,true));
        this.listaColumnas.add(new Columna("DESCRIPCION",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.razonDevolucion.getDescripcion());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.razonDevolucion.getDescripcion());
        this.statement.setInt(2, this.razonDevolucion.getRazonDevolucionId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,this.razonDevolucion.getRazonDevolucionId());
    }
    
    @Override
    public Integer insertar(RazonDevolucionDTO razonDevolucion) {
        this.razonDevolucion = razonDevolucion;
        return super.insertar();
    }

    @Override
    public RazonDevolucionDTO obtenerPorId(Integer razonId) {
        RazonDevolucionDTO razonDevolucion = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT RAZON_DEVOLUCION_ID, DESCRIPCION FROM BOD_RAZON_DEVOLUCION"
                       + " WHERE RAZON_DEVOLUCION_ID = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, razonId);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                razonDevolucion = new RazonDevolucionDTO(
                this.resultSet.getInt("RAZON_DEVOLUCION_ID"),
                this.resultSet.getString("DESCRIPCION"));
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
        return razonDevolucion;
    }

    @Override
    public ArrayList<RazonDevolucionDTO> listarTodos() {
        ArrayList<RazonDevolucionDTO> listaRazonDevolucion = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT RAZON_DEVOLUCION_ID, DESCRIPCION FROM BOD_RAZON_DEVOLUCION"
                    + " ORDER BY RAZON_DEVOLUCION_ID";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                RazonDevolucionDTO razonDevolucion = new RazonDevolucionDTO(
                this.resultSet.getInt("RAZON_DEVOLUCION_ID"),
                this.resultSet.getString("DESCRIPCION"));
                
                listaRazonDevolucion.add(razonDevolucion);
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
        return listaRazonDevolucion;
    }

    @Override
    public Integer modificar(RazonDevolucionDTO razonDevolucion) {
        this.razonDevolucion = razonDevolucion;
        return super.modificar();}

    @Override
    public Integer eliminar(RazonDevolucionDTO razonDevolucion) {
        this.razonDevolucion = razonDevolucion;
        return super.eliminar();
    }
    
}
