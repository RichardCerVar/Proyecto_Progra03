package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.VentaAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.db.DBManager;
import pe.edu.pucp.softbod.model.VentasFiadasDTO;

public class VentaAlFiadoDAOImpl extends DAOImplBase implements VentaAlFiadoDAO {
    
    private VentasFiadasDTO ventaAlFiado;
    
    public VentaAlFiadoDAOImpl() {
        super("BOD_VENTAS_FIADAS");
        this.ventaAlFiado = null;
    }
     
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VENTA_FIADO_ID", true, true));
        this.listaColumnas.add(new Columna("CLIENTE_ID", false, false));
        this.listaColumnas.add(new Columna("VENTA_ID", false, false));
    }

    @Override
    public ArrayList<VentasFiadasDTO> listarTodos() {
        ArrayList<VentasFiadasDTO> listaVentasAlFiado = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT VENTA_FIADO_ID, CLIENTE_ID, VENTA_ID FROM BOD_VENTAS_FIADAS";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                VentasFiadasDTO ventaAlFiado = new VentasFiadasDTO();
                ventaAlFiado.setVentaFiadaId(this.resultSet.getInt("VENTA_FIADO_ID"));
                ventaAlFiado.setClienteId(this.resultSet.getInt("CLIENTE_ID"));
                ventaAlFiado.setVentaId(this.resultSet.getInt("VENTA_ID"));
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
