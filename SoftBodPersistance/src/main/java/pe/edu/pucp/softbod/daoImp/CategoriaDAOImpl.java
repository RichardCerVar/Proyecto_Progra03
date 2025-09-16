package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import pe.edu.pucp.softbod.dao.CategoriaDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.CategoriaDTO;

public class CategoriaDAOImpl extends DAOImplBase implements CategoriaDAO {
    
    private CategoriaDTO categoria;

    public CategoriaDAOImpl() {
        super("BOD_CATEGORIA");
        this.categoria = null;
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
    public Integer insertar(CategoriaDTO categoria) {
        this.categoria = categoria;
        return super.insertar();
    }

    @Override
    public Integer eliminar(CategoriaDTO categoria) {
        this.categoria = categoria;
        return super.eliminar();
    }
    
}
