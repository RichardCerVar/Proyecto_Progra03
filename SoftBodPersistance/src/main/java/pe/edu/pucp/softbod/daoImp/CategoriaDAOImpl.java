package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import pe.edu.pucp.softbod.dao.CategoriaDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.CategoriaDTO;

public class CategoriaDAOImpl extends DAOImplBase implements CategoriaDAO {
    
    private CategoriaDTO categoria;
    
    private final CargarTablas cargarTablas;

    public CategoriaDAOImpl() {
        super("BOD_CATEGORIA");
        this.categoria = null;
        this.retornarLlavePrimaria = true;
        this.cargarTablas = new CargarTablas();
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("CATEGORIA_ID", true, true));
        this.listaColumnas.add(new Columna("DESCRIPCION", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.categoria.getDescripcion());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.categoria.getCategoriaId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.categoria.getCategoriaId());
    }
    
    @Override
    public Integer insertar(CategoriaDTO categoria) {
        this.categoria = categoria;
        return super.insertar();
    }

    @Override
    public Integer eliminar(CategoriaDTO categoria) {
        this.categoria = categoria;
        return super.eliminar();
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.categoria);
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.categoria = this.cargarTablas.cargarCategoria(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.categoria = null;
    }
    
    @Override
    public ArrayList<CategoriaDTO> listarTodos() {
        String sql = "{CALL TA_PROG3.sp_listar_categoria()}";
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<CategoriaDTO>) super.listarTodos(sql,incluirValorDeParametros,parametros);
    }

    @Override
    public CategoriaDTO obtenerPorId(Integer categoriaId) {
        this.categoria = new CategoriaDTO();
        this.categoria.setCategoriaId(categoriaId);
        super.obtenerPorId();
        return this.categoria;
    }
    
}

