package pe.edu.pucp.softbod.daoImp;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.VentaFiadaDTO;
import pe.edu.pucp.softbod.dao.VentaFiadaDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.VentaFiadaParametrosBusquedaBuilder;
import pe.edu.pucp.softbod.daoImp.util.VentaFiadaParametrosBusqueda;

public class VentaAlFiadoDAOImpl extends DAOImplBase implements VentaFiadaDAO {
    
    private VentaFiadaDTO ventaAlFiado; 
    private final CargarTablas cargaTablas;
    
    public VentaAlFiadoDAOImpl() {
        super("BOD_VENTAS_FIADAS");
        this.ventaAlFiado = null;
        this.retornarLlavePrimaria = true;
        this.cargaTablas = new CargarTablas();
    }
     
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VENTA_FIADA_ID", true, true));
        this.listaColumnas.add(new Columna("VENTA_ID", false, false));
        this.listaColumnas.add(new Columna("CLIENTE_ID", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1,this.ventaAlFiado.getVenta().getVentaId());
        this.statement.setInt(2, this.ventaAlFiado.getCliente().getClienteId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.ventaAlFiado.getVentaFiadaId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.ventaAlFiado = this.cargaTablas.cargarVentaFiadaDTO(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.ventaAlFiado = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.ventaAlFiado);
    }
    
    private ArrayList<VentaFiadaDTO> listarTodosConFiltros(String aliasCliente, Integer ventaFiadaId,Date fecha){
        String sql = "{ CALL SP_LISTAR_VENTAS_AL_FIADO(?, ?) }";
        Object parametros = new VentaFiadaParametrosBusquedaBuilder()
                            .conAliasCliente(aliasCliente)
                            .conVentaFiadaId(ventaFiadaId)
                            .conFecha(fecha)
                            .buildVentaFiadaParametrosBusqueda();
        return (ArrayList<VentaFiadaDTO>) super.listarTodos(sql, this::incluirValorDeParametrosParaListarVentasFiadas, parametros);
    }
    
    
    @Override
    public ArrayList<VentaFiadaDTO> listarTodos() {
        Integer ventaFiadaId = null;
        String aliasCliente = null;
        Date fecha = null;
        return (ArrayList<VentaFiadaDTO>) listarTodosConFiltros(aliasCliente, ventaFiadaId,fecha);
    }

    private void incluirValorDeParametrosParaListarVentasFiadas(Object parametros){
        VentaFiadaParametrosBusqueda ventaFiadaParam = (VentaFiadaParametrosBusqueda) parametros;
        
        try {
            if(ventaFiadaParam.getAliasCliente()!=null){
                this.statement.setString(1,ventaFiadaParam.getAliasCliente());
            }else{
                this.statement.setNull(1,Types.VARCHAR);
            }
            
            if(ventaFiadaParam.getVentaFiadaId()!=null){
                this.statement.setInt(2,ventaFiadaParam.getVentaFiadaId());
            }else{
                this.statement.setNull(2, Types.INTEGER);
            }
            
            if(ventaFiadaParam.getFecha()!=null){
                this.statement.setDate(3,ventaFiadaParam.getFecha());
            }else{
                this.statement.setNull(3, Types.DATE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaAlFiadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @Override
    public Integer insertar(VentaFiadaDTO ventaFiada) {
        this.ventaAlFiado = ventaFiada;
        return super.insertar();
    }

    @Override
    public VentaFiadaDTO obtenerPorId(Integer ventaFiada_Id) {
        ArrayList<VentaFiadaDTO> lista;
        String aliasCliente = null;
        Date fecha = null;
        lista = this.listarTodosConFiltros(aliasCliente, ventaFiada_Id,fecha);
        if(!lista.isEmpty()){
            this.ventaAlFiado = lista.getFirst();
        }
        return this.ventaAlFiado;
    }

    @Override
    public ArrayList<VentaFiadaDTO> listarTodosPorAliasCliente(String aliasCliente) {
        Integer ventaFiadaId = null;
        Date fecha = null;
        return (ArrayList<VentaFiadaDTO>) listarTodosConFiltros(aliasCliente, ventaFiadaId,fecha);
    }

    @Override
    public ArrayList<VentaFiadaDTO> listarTodosPorAliasClienteFecha(String aliasCliente, Date fecha) {
        Integer ventaFiadaId = null;
        return (ArrayList<VentaFiadaDTO>) listarTodosConFiltros(aliasCliente, ventaFiadaId,fecha);
    }
}