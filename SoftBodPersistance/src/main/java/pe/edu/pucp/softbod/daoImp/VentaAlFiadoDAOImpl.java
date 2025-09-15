package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.sofbod.dao.VentaAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;

public class VentaAlFiadoDAOImpl extends DAOImplBase implements VentaAlFiadoDAO {
    
    private VentaAlFiadoDTO ventaAlFiado;
    
    public VentaAlFiadoDAOImpl() {
        super("BOD_VENTAS_FIADAS");
        this.ventaAlFiado = null;
    }
     
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VENTA_FIADO_ID", false, false));
        this.listaColumnas.add(new Columna("CLIENTE_ID", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.ventaAlFiado.getVentaAlFiadoId());
        this.statement.setString(2, this.ventaAlFiado.getClienteId());
    }
    
    @Override
    public Integer insertar(VentaAlFiadoDTO ventaAlFiado) {
        this.ventaAlFiado = ventaAlFiado;
        return super.insertar();
    }

    @Override
    public ArrayList<VentaAlFiadoDTO> listarTodos() {
        ArrayList<VentaAlFiadoDTO> listaVentasAlFiado = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT VENTA_FIADO_ID, CLIENTE_ID FROM BOD_VENTAS_FIADAS";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                VentaAlFiadoDTO ventaAlFiado = new VentaAlFiadoDTO();
                ventaAlFiado.setVentaAlFiadoId(this.resultSet.getInt("VENTA_FIADO_ID"));
                ventaAlFiado.setClienteId(this.resultSet.getString("CLIENTE_ID"));
                listaVentasAlFiado.add(ventaAlFiado);
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
        return listaVentasAlFiado;
    }
    
}
