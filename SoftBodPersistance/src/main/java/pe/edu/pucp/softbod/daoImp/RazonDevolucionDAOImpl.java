package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.dao.RazonDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.daoImp.util.RazonDevolucionParametrosBusqueda;
import pe.edu.pucp.softbod.daoImp.util.RazonDevolucionParametrosBusquedaBuilder;
import pe.edu.pucp.softbod.model.RazonDevolucionDTO;

public class RazonDevolucionDAOImpl extends DAOImplBase implements RazonDevolucionDAO{

    private RazonDevolucionDTO razonDevolucion;
    private final CargarTablas cargaTablas;
    
    public RazonDevolucionDAOImpl() {
        super("BOD_RAZON_DEVOLUCION");
        this.razonDevolucion = null;
        this.retornarLlavePrimaria = true;
        this.cargaTablas = new CargarTablas();
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("RAZON_DEVOLUCION_ID",true,true));
        this.listaColumnas.add(new Columna("DESCRIPCION",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.razonDevolucion.getDescripcion());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1,this.razonDevolucion.getRazonDevolucionId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.razonDevolucion.getRazonDevolucionId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.razonDevolucion = this.cargaTablas.cargaRazonDevolucionDTO(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.razonDevolucion = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.razonDevolucion);
    }
    
    @Override
    public Integer insertar(RazonDevolucionDTO razonDevolucion) {
        this.razonDevolucion = razonDevolucion;
        return super.insertar();
    }
    
    @Override
    public Integer eliminar(RazonDevolucionDTO razonDevolucion) {
        this.razonDevolucion = razonDevolucion;
        return super.eliminar();
    }

    @Override
    public RazonDevolucionDTO obtenerPorId(Integer razonId) {
        this.razonDevolucion = new RazonDevolucionDTO();
        this.razonDevolucion.setRazonDevolucionId(razonId);
        super.obtenerPorId();
        return this.razonDevolucion;
    }

    @Override
    public ArrayList<RazonDevolucionDTO> listarTodos() {
        String descripcion = null;
        return (ArrayList<RazonDevolucionDTO>) listarRazonesSP(descripcion);
    }

    @Override
    public ArrayList<RazonDevolucionDTO> listarTodosPorNombreParcial(String nombreRazon) {
        return (ArrayList<RazonDevolucionDTO>) listarRazonesSP(nombreRazon);
    }
    
    private ArrayList<RazonDevolucionDTO> listarRazonesSP(String descripcion) {
        String sql = "{ CALL SP_LISTAR_RAZONES_DEVOLUCION(?) }";

        Object parametros = new RazonDevolucionParametrosBusquedaBuilder()
                            .conDescripcion(descripcion)
                            .buildRazonDevolucionParametrosBusqueda();
        
        return (ArrayList<RazonDevolucionDTO>) super.listarTodos(sql,
                this::incluirValorDeParametrosParaBuscarRazon,
                parametros);
    }

    private void incluirValorDeParametrosParaBuscarRazon(Object parametros) {
        RazonDevolucionParametrosBusqueda filtros = (RazonDevolucionParametrosBusqueda) parametros;

        try {
            if (filtros.getDescripcion() != null) {
                this.statement.setString(1, filtros.getDescripcion());
            } else {
                this.statement.setNull(1, Types.VARCHAR);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RazonDevolucionDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
