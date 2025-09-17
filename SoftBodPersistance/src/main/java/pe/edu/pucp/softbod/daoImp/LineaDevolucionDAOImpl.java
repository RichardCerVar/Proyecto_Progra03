package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.LineaDevolucionDAO;
import pe.edu.pucp.softbod.model.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.db.DBManager;
import pe.edu.pucp.softbod.daoImp.util.Columna;

public class LineaDevolucionDAOImpl extends DAOImplBase implements LineaDevolucionDAO {

    private DetalleDevolucionDTO linea;

    public LineaDevolucionDAOImpl() {
        super("BOD_DETALLE_DEVOLUCION");
        this.linea = null;
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("DETALLE_ID",true,true));
        this.listaColumnas.add(new Columna("DEVOLUCION_id",false,false));
        this.listaColumnas.add(new Columna("PRODUCTO_id",false,false));
        this.listaColumnas.add(new Columna("CANTIDAD",false,false));
        this.listaColumnas.add(new Columna("SUBTOTAL",false,false));
        this.listaColumnas.add(new Columna("RAZON_DEVOLUCION_ID",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.linea.getDevolucionId());
        this.statement.setInt(2, this.linea.getProductoId());
        this.statement.setDouble(3, this.linea.getCantidad());
        this.statement.setDouble(4, this.linea.getSubtotal());
        this.statement.setInt(5, this.linea.getRazonId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setInt(1, this.linea.getDevolucionId());
        this.statement.setInt(2, this.linea.getProductoId());
        this.statement.setDouble(3, this.linea.getCantidad());
        this.statement.setDouble(4, this.linea.getSubtotal());
        this.statement.setInt(5, this.linea.getRazonId());
        this.statement.setInt(6, this.linea.getDetalleId());
    }
    
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.linea.getDetalleId());
    }
    
    @Override
    public Integer insertar(DetalleDevolucionDTO linea) {
        this.linea = linea;
        return super.insertar();
    }
    
    @Override
    public DetalleDevolucionDTO obtenerPorId(Integer lineaId) {
        DetalleDevolucionDTO linea = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT DETALLE_ID, DEVOLUCION_ID, PRODUCTO_ID, CANTIDAD, SUBTOTAL, "
                    + "RAZON_DEVOLUCION_ID FROM BOD_DETALLE_DEVOLUCION WHERE USUARIO_ID = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, lineaId);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                linea = new DetalleDevolucionDTO();
                this.linea.setDetalleId(this.resultSet.getInt("DETALLE_ID"));
                this.linea.setDevolucionId(this.resultSet.getInt("DEVOLUCION_ID"));
                this.linea.setProductoId(this.resultSet.getInt("PRODUCTO_ID"));
                this.linea.setCantidad(this.resultSet.getDouble("CANTIDAD"));
                this.linea.setSubtotal(this.resultSet.getDouble("SUBTOTAL"));
                this.linea.setRazonId(this.resultSet.getInt("RAZON_DEVOLUCION_ID"));
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
        return linea;
    }

    @Override
    public ArrayList<DetalleDevolucionDTO> listarTodos() {
        ArrayList<DetalleDevolucionDTO> listaUsuarios = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT DETALLE_ID, DEVOLUCION_ID, PRODUCTO_ID, CANTIDAD, SUBTOTAL, "
                    + "RAZON_DEVOLUCION_ID FROM BOD_DETALLE_DEVOLUCION";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                DetalleDevolucionDTO linea = new DetalleDevolucionDTO();
                this.linea.setDetalleId(this.resultSet.getInt("DETALLE_ID"));
                this.linea.setDevolucionId(this.resultSet.getInt("DEVOLUCION_ID"));
                this.linea.setProductoId(this.resultSet.getInt("PRODUCTO_ID"));
                this.linea.setCantidad(this.resultSet.getDouble("CANTIDAD"));
                this.linea.setSubtotal(this.resultSet.getDouble("SUBTOTAL"));
                this.linea.setRazonId(this.resultSet.getInt("RAZON_DEVOLUCION_ID"));
                listaUsuarios.add(linea);
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
        return listaUsuarios;
    }
    
    @Override
    public Integer modificar(DetalleDevolucionDTO linea) {
       this.linea = linea;
       return super.modificar();
    }

    @Override
    public Integer eliminar(DetalleDevolucionDTO linea) {
        this.linea = linea;
        return super.eliminar();
    }
    
}
