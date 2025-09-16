
package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.VentasDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.db.DBManager;
import pe.edu.pucp.softbod.model.Tipo_de_pago;
import pe.edu.pucp.softbod.model.VentasDTO;


public class VentasDAOImpl extends DAOImplBase implements VentasDAO{
    
    private VentasDTO venta;    
    
    public VentasDAOImpl(){
        super("BOD_VENTAS");
        this.venta=null;
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VENTA_ID",true,true));
        this.listaColumnas.add(new Columna("TOTAL",false,false));
        this.listaColumnas.add(new Columna("METODO_PAGO",false,false));
        this.listaColumnas.add(new Columna("FECHA",false,false));
        this.listaColumnas.add(new Columna("USUARIO",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1,this.venta.getTotal());
        this.statement.setString(2,this.venta.getMetodo_pago().name());
        this.statement.setDate(3, new java.sql.Date(this.venta.getFecha().getTime()));
        this.statement.setString(4,this.venta.getUsuario());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException { 
        this.statement.setDouble(1,this.venta.getTotal());
        this.statement.setString(2,this.venta.getMetodo_pago().name());
        this.statement.setDate(3, new java.sql.Date(this.venta.getFecha().getTime()));
        this.statement.setString(4,this.venta.getUsuario());
        this.statement.setInt(5,this.venta.getVentaId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1, this.venta.getVentaId());
    }


    @Override
    public Integer insertar(VentasDTO venta) {
        this.venta=venta;
        return super.insertar();
    }

    @Override
    public VentasDTO obtenerPorId(Integer ventaId) {
        VentasDTO venta = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT VENTA_ID, TOTAL, METODO_PAGO, FECHA, USUARIO FROM BOD_VENTAS WHERE VENTA_ID = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, ventaId);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                venta = new VentasDTO();
                venta.setVentaId(this.resultSet.getInt("VENTA_ID"));
                venta.setTotal(this.resultSet.getDouble("TOTAL"));
                venta.setMetodo_pago(Tipo_de_pago.valueOf(this.resultSet.getString("METODO_PAGO")));
                venta.setFecha(this.resultSet.getDate("FECHA"));
                venta.setUsuario(this.resultSet.getString("USUARIO"));
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
        return venta;
    }

    @Override
    public ArrayList<VentasDTO> listarTodos() {
        ArrayList<VentasDTO> listaVentas = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT AVENTA_ID, TOTAL, METODO_PAGO, FECHA, USUARIO FROM BOD_VENTAS";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                VentasDTO venta = new VentasDTO();
                venta.setVentaId(this.resultSet.getInt("VENTA_ID"));
                venta.setTotal(this.resultSet.getDouble("TOTAL"));
                venta.setMetodo_pago(Tipo_de_pago.valueOf(this.resultSet.getString("METODO_PAGO")));
                venta.setFecha(this.resultSet.getDate("FECHA"));
                venta.setUsuario(this.resultSet.getString("USUARIO"));
                listaVentas.add(venta);
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
        return listaVentas;
    }

    @Override
    public Integer modificar(VentasDTO venta) {
        this.venta=venta;
        return super.modificar();
    }

    @Override
    public Integer eliminar(VentasDTO venta) {
        this.venta=venta;
        return super.eliminar();
    }
    
}
