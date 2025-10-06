
package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.dao.VentasDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.daoImp.util.VentaParametrosBusqueda;
import pe.edu.pucp.softbod.daoImp.util.VentaParametrosBusquedaBuilder;
import pe.edu.pucp.softbod.model.VentaDTO;


public class VentaDAOImpl extends DAOImplBase implements VentasDAO{
    
    private VentaDTO venta;   
    private final CargarTablas cargaTablas;
    
    public VentaDAOImpl(){
        super("BOD_VENTAS");
        this.venta=null;
        this.retornarLlavePrimaria = true;
        this.cargaTablas = new CargarTablas();
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VENTA_ID",true,true));
        this.listaColumnas.add(new Columna("TOTAL",false,false));
        this.listaColumnas.add(new Columna("METODO_PAGO",false,false));
        this.listaColumnas.add(new Columna("FECHA",false,false));
        this.listaColumnas.add(new Columna("USUARIO_ID",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1,this.venta.getTotal());
        this.statement.setString(2,this.venta.getMetodoPago().name());
        this.statement.setDate(3, this.venta.getFecha());
        this.statement.setInt(4, this.venta.getUsuario().getUsuarioId());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.venta.getVentaId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.venta = this.cargaTablas.cargarVentaDTO(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.venta = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.venta);
    }
    
    @Override
    public Integer insertar(VentaDTO venta) {
        this.venta=venta;
        return super.insertar();
    }

    @Override
    public VentaDTO obtenerPorId(Integer venta_Id) {
        ArrayList<VentaDTO> lista = this.listarTodosGenerico(venta_Id);
        if(!lista.isEmpty()){
            this.venta = lista.getFirst();
        }
        return this.venta;
    }
    
    private ArrayList<VentaDTO> listarTodosGenerico(Integer ventaId){
        String sql = "{CALL sp_listarVentasGenerico(?)}";
        Object parametros = new VentaParametrosBusquedaBuilder()
                            .conVentaId(ventaId)
                            .BuildVentaParametrosBusqueda();
        return (ArrayList<VentaDTO>) super.listarTodos(sql, this::incluirValorDeParametrosParaListarVentas, parametros);
    }

    @Override
    public ArrayList<VentaDTO> listarTodos() {
        Integer ventaId = null;
        return this.listarTodosGenerico(ventaId);
    }

    private void incluirValorDeParametrosParaListarVentas(Object parametros) {
        VentaParametrosBusqueda ventaParametros = (VentaParametrosBusqueda) parametros;
        try {
            if(ventaParametros.getVentaId() == null){
                this.statement.setNull(1, Types.INTEGER);
            }else{
                this.statement.setInt(1, ventaParametros.getVentaId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
