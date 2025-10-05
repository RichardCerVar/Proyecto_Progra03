package pe.edu.pucp.softbod.daoImp;
//revisar
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import pe.edu.pucp.softbod.dao.DetalleVentaDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.ProductoDTO;
import pe.edu.pucp.softbod.model.VentaDTO;

public class DetalleVentaDAOImpl extends DAOImplBase implements DetalleVentaDAO{
    
    private DetalleVentaDTO detalleVenta;

    private final CargarTablas cargarTabla;
    
    public DetalleVentaDAOImpl() {
        super("BOD_DETALLE_VENTA");
        this.detalleVenta=null;
        this.retornarLlavePrimaria = true;
        this.cargarTabla = null;
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

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.detalleVenta);
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.detalleVenta = new DetalleVentaDTO();
        
        VentaDTO venta = this.cargarTabla.cargarVentas();
        this.detalleVenta.setVenta(venta);
        ProductoDTO producto = this.cargarTabla.cargarProducto();
        this.detalleVenta.setProducto(producto);
        this.detalleVenta.setCantidad(this.resultSet.getInt("CANTIDAD"));
        this.detalleVenta.setSubtotal(this.resultSet.getDouble("SUBTOTAL"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.detalleVenta = null;
    }
    
    @Override
    public ArrayList<DetalleVentaDTO> listarTodos() {
        String sql = "{CALL TA_PROG3.sp_listar_detalleVenta()}";
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<DetalleVentaDTO>) super.listarTodos(sql,incluirValorDeParametros,parametros);
    }
}
