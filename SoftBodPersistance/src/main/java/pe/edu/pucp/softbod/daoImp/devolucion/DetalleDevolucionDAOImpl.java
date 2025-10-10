package pe.edu.pucp.softbod.daoImp.devolucion;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.model.devolucion.DetalleDevolucionDTO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.dao.devolucion.DetalleDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.DAOImplBase;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.DetalleDevolucionParametros;
import pe.edu.pucp.softbod.daoImp.util.DetalleDevolucionParametrosBuilder;

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
        Integer devolucionId = null, productoId = null;
        String razonDevolucion = null;
        Date fecha = null;
        return this.listarDetalledevolucionFiltros(devolucionId, productoId, 
                                                    razonDevolucion, fecha);
    }
    
    @Override
    public ArrayList<DetalleDevolucionDTO> listarPorProducto(Integer productoId) {
        Integer devolucionId = null;
        String razonDevolucion = null;
        Date fecha = null;
        return this.listarDetalledevolucionFiltros(devolucionId, productoId, 
                                                    razonDevolucion, fecha);
    }

    @Override
    public ArrayList<DetalleDevolucionDTO> listarPorDevolucion(Integer devolucionId) {
        Integer productoId = null;
        String razonDevolucion = null;
        Date fecha = null;
        return this.listarDetalledevolucionFiltros(devolucionId, productoId, 
                                                    razonDevolucion, fecha);
    }

    @Override
    public ArrayList<DetalleDevolucionDTO> listarPorRazonDevolucion(String razonDevolucion) {
        Integer productoId = null, devolucionId = null;
        Date fecha = null;
        return this.listarDetalledevolucionFiltros(devolucionId, productoId, 
                                                    razonDevolucion, fecha);
    }
    
    @Override
    public DetalleDevolucionDTO obtenerPorId(Integer productoId, Integer devolucionId){
        String razonDevolucion = null;
        Date fecha = null;
        ArrayList<DetalleDevolucionDTO> lista;
        lista = this.listarDetalledevolucionFiltros(devolucionId, productoId, 
                                                    razonDevolucion, fecha);
        if (!lista.isEmpty()){
            this.linea = lista.getFirst();
        }
        return this.linea;
    }
    
    private ArrayList<DetalleDevolucionDTO> listarDetalledevolucionFiltros 
        (Integer devolucionId, Integer productoId, String razonDevolucion,
        Date fecha){
        String sql = "{CALL TA_PROG3.SP_LISTAR_DETALLE_DEVOLUCION(?,?,?,?)}";
        Object parametros = new DetalleDevolucionParametrosBuilder()
                            .conDevolucionId(devolucionId)
                            .conProductoId(productoId)
                            .conRazonDevolucion(razonDevolucion)
                            .conFecha(fecha)
                            .BuildDetalleDevolucionParametros();
        return (ArrayList <DetalleDevolucionDTO>) 
                super.listarTodos(sql, this::incluirValorDeParametrosDeDetalleDevolucion,
                                    parametros);
    } 
    
    private void incluirValorDeParametrosDeDetalleDevolucion (Object parametros){
        DetalleDevolucionParametros detDevParametros = (DetalleDevolucionParametros) parametros;
        try {
            if (detDevParametros.getDevolucionId() != null)
                this.statement.setInt(1, detDevParametros.getDevolucionId());
            else
                this.statement.setNull(1,Types.INTEGER);
            
            if (detDevParametros.getProductoId()!= null)
                this.statement.setInt(2, detDevParametros.getProductoId());
            else
                this.statement.setNull(2,Types.INTEGER);
            
            if (detDevParametros.getRazonDevolucion()!= null)
                this.statement.setString(3, detDevParametros.getRazonDevolucion());
            else
                this.statement.setNull(3,Types.VARCHAR);
            
            if (detDevParametros.getFecha()!= null)
                this.statement.setDate(4, detDevParametros.getFecha());
            else
                this.statement.setNull(4,Types.DATE);
        } catch (SQLException ex) {
            Logger.getLogger(DetalleDevolucionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
