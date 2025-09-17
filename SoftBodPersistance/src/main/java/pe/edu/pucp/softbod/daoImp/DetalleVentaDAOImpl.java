package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.DetalleVentaDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.db.DBManager;
import pe.edu.pucp.softbod.model.DetalleVentaDTO;

public class DetalleVentaDAOImpl extends DAOImplBase implements DetalleVentaDAO{
    private DetalleVentaDTO detalleVenta;

    public DetalleVentaDAOImpl() {
        super("BOD_DETALLE_VENTA");
        this.detalleVenta=null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VENTA_ID",true,false));
        this.listaColumnas.add(new Columna("PRODUCTO_ID",true,false));
        this.listaColumnas.add(new Columna("SUBTOTAL",false,false));
        this.listaColumnas.add(new Columna("CANTIDAD",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.detalleVenta.getVentaId());
        this.statement.setInt(2, this.detalleVenta.getProductoId());
        this.statement.setDouble(3, this.detalleVenta.getSubtotal());
        this.statement.setDouble(4, this.detalleVenta.getCantidad());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException { 
        this.statement.setInt(1, this.detalleVenta.getVentaId());
        this.statement.setInt(2, this.detalleVenta.getProductoId());
        this.statement.setDouble(3, this.detalleVenta.getSubtotal());
        this.statement.setDouble(4, this.detalleVenta.getCantidad());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1, this.detalleVenta.getProductoId());
        this.statement.setInt(2, this.detalleVenta.getVentaId());
    }
    
    
    
    @Override
    public Integer insertar(DetalleVentaDTO detalleVenta) {
        this.detalleVenta=detalleVenta;
        return super.insertar();
    }

    @Override
    public DetalleVentaDTO obtenerPorId(Integer ventaId, Integer productoId) {
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT VENTA_ID, PRODUCTO_ID, SUBTOTAL, CANTIDAD FROM BOD_DETALLE_VENTA WHERE VENTA_ID = ? AND PRODUCTO_ID = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, ventaId);
            this.statement.setInt(2, productoId);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                detalleVenta = new DetalleVentaDTO();
                detalleVenta.setVentaId(this.resultSet.getInt("VENTA_ID"));
                detalleVenta.setProductoId(this.resultSet.getInt("PRODUCTO_ID"));
                detalleVenta.setSubtotal(this.resultSet.getDouble("SUBTOTAL"));
                detalleVenta.setCantidad(this.resultSet.getDouble("CANTIDAD"));
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
        return detalleVenta;
    }

    @Override
    public ArrayList<DetalleVentaDTO> listarTodos() {
        ArrayList<DetalleVentaDTO> listaDetalleVentas = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT VENTA_ID, PRODUCTO_ID, SUBTOTAL, CANTIDAD FROM BOD_DETALLE_VENTA";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                DetalleVentaDTO detalleVenta = new DetalleVentaDTO();
                detalleVenta.setVentaId(this.resultSet.getInt("VENTA_ID"));
                detalleVenta.setProductoId(this.resultSet.getInt("PRODUCTO_ID"));
                detalleVenta.setSubtotal(this.resultSet.getDouble("SUBTOTAL"));
                detalleVenta.setCantidad(this.resultSet.getDouble("CANTIDAD"));
                listaDetalleVentas.add(detalleVenta);
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
        return listaDetalleVentas;
    }

    @Override
    public Integer actualizar(DetalleVentaDTO detalleVenta) {
        this.detalleVenta=detalleVenta;
        return super.modificar();
    }

    @Override
    public Integer eliminar(DetalleVentaDTO detalleVenta) {
        this.detalleVenta=detalleVenta;
        return super.eliminar();
    }
    
}
