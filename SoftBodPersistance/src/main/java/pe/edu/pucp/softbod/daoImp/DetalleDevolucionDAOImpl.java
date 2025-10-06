package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import pe.edu.pucp.softbod.model.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.dao.DetalleDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;

public class DetalleDevolucionDAOImpl extends DAOImplBase implements DetalleDevolucionDAO {

    private DetalleDevolucionDTO linea;
    
    private final CargarTablas cargarTablas;

    public DetalleDevolucionDAOImpl() {
        super("BOD_DETALLE_DEVOLUCION");
        this.linea = null;
        this.retornarLlavePrimaria = true;
        this.cargarTablas = new CargarTablas();
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("DEVOLUCION_ID",false,false));
        this.listaColumnas.add(new Columna("PRODUCTO_ID",false,false));
        this.listaColumnas.add(new Columna("CANTIDAD",false,false));
        this.listaColumnas.add(new Columna("SUBTOTAL",false,false));
        this.listaColumnas.add(new Columna("RAZON_DEVOLUCION_ID",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.linea.getDevolucion().getDevolucionId());
        this.statement.setInt(2, this.linea.getProducto().getProductoId());
        this.statement.setInt(3, this.linea.getCantidad());
        this.statement.setDouble(4, this.linea.getSubtotal());
        this.statement.setInt(5, this.linea.getRazonDevolucion().getRazonDevolucionId());
    }
   
    @Override
    public Integer insertar(DetalleDevolucionDTO linea) {
        this.linea = linea;
        return super.insertar();
    }
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.linea);
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.linea = this.cargarTablas.cargarDetalleDevolucion(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.linea = null;
    }
    
    @Override
    public ArrayList<DetalleDevolucionDTO> listarTodos() {
        String sql = "{CALL TA_PROG3.sp_listar_detalleDevolucion()}";
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<DetalleDevolucionDTO>) super.listarTodos(sql,incluirValorDeParametros,parametros);
    }
}
