package pe.edu.pucp.softbod.daoImp;
//revisar
import java.sql.SQLException;
import pe.edu.pucp.softbod.dao.DetalleVentaDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
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
        this.statement.setInt(1, this.detalleVenta.getVenta().getVentaId());
        this.statement.setInt(2, this.detalleVenta.getProducto().getProductoId());
        this.statement.setDouble(3, this.detalleVenta.getSubtotal());
        this.statement.setDouble(4, this.detalleVenta.getCantidad());
    }
    

    @Override
    public Integer insertar(DetalleVentaDTO detalleVenta) {
        this.detalleVenta=detalleVenta;
        return super.insertar();
    }


    
}
