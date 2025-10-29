package pe.edu.pucp.softbod.daoImp.ventas;
//revisar
import pe.edu.pucp.softbod.daoImp.devolucion.DetalleDevolucionDAOImpl;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.dao.ventas.DetalleVentaDAO;
import pe.edu.pucp.softbod.daoImp.DAOImplBase;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.daoImp.util.DetalleVentaParametros;
import pe.edu.pucp.softbod.daoImp.util.DetalleVentaParametrosBuilder;
import pe.edu.pucp.softbod.model.ventas.DetalleVentaDTO;
import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import pe.edu.pucp.softbod.model.ventas.VentaDTO;

public class DetalleVentaDAOImpl extends DAOImplBase implements DetalleVentaDAO{
    
    private DetalleVentaDTO detalleVenta;

    private final CargarTablas cargarTabla;
    
    public DetalleVentaDAOImpl() {
        super("BOD_DETALLE_VENTA");
        this.detalleVenta = null;
        this.retornarLlavePrimaria = true;
        this.cargarTabla = new CargarTablas();
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
        this.detalleVenta = this.cargarTabla.cargarDetalleVenta(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.detalleVenta = null;
    }
    
    @Override
    public ArrayList<DetalleVentaDTO> listarTodos() {
        Integer ventaId = null, productoId = null;
        Date fechaVenta = null;
        return this.listarDetalleVentaFiltros(ventaId, productoId ,fechaVenta);
    }
    
    @Override
    public ArrayList<DetalleVentaDTO> listarPorVenta(Integer ventaId) {
        Integer productoId = null;
        Date fechaVenta = null;
        return this.listarDetalleVentaFiltros(ventaId, productoId ,fechaVenta);
    }

    @Override
    public ArrayList<DetalleVentaDTO> listarPorProducto(Integer productoId) {
        Integer ventaId = null;
        Date fechaVenta = null;
        return this.listarDetalleVentaFiltros(ventaId, productoId ,fechaVenta);
    }

    @Override
    public DetalleVentaDTO obtenerPorId(Integer ventaId, Integer productoId) {
        Date fechaVenta = null;
        ArrayList<DetalleVentaDTO> lista = this.listarDetalleVentaFiltros
                                        (ventaId, productoId ,fechaVenta);
        if (!lista.isEmpty()){
            this.detalleVenta = lista.getFirst();
        }
        
        return this.detalleVenta;
    }
    
    private ArrayList<DetalleVentaDTO> listarDetalleVentaFiltros 
        (Integer ventaId, Integer productoId,Date fecha){
        String sql = "{CALL TA_PROG3.SP_LISTAR_DETALLE_VENTA(?,?,?)}";
        Object parametros = new DetalleVentaParametrosBuilder()
                            .conVentaId(ventaId)
                            .conProductoId(productoId)
                            .conFecha(fecha)
                            .BuildDetalleVentaParametros();
        return (ArrayList <DetalleVentaDTO>) 
                super.listarTodos(sql, this::incluirValorDeParametrosDeDetalleDevolucion,
                                    parametros);
    }
    
    private void incluirValorDeParametrosDeDetalleDevolucion (Object parametros){
        DetalleVentaParametros detVenParametros = (DetalleVentaParametros) parametros;
        try {
            if (detVenParametros.getVentaId()!= null)
                this.statement.setInt(1, detVenParametros.getVentaId());
            else
                this.statement.setNull(1,Types.INTEGER);
            
            if (detVenParametros.getProductoId()!= null)
                this.statement.setInt(2, detVenParametros.getProductoId());
            else
                this.statement.setNull(2,Types.INTEGER);
            
            if (detVenParametros.getFechaVenta()!= null)
                this.statement.setDate(3, detVenParametros.getFechaVenta());
            else
                this.statement.setNull(3,Types.DATE);
        } catch (SQLException ex) {
            Logger.getLogger(DetalleDevolucionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

}
