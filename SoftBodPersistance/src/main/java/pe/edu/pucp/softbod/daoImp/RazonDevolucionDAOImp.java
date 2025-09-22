package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import pe.edu.pucp.softbod.dao.RazonDevolucionDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.RazonDevolucionDTO;

public class RazonDevolucionDAOImp extends DAOImplBase implements RazonDevolucionDAO{

    private RazonDevolucionDTO razonDevolucion;

    public RazonDevolucionDAOImp() {
        super("BOD_RAZON_DEVOLUCION");
        this.razonDevolucion = null;
        this.retornarLlavePrimaria = true;
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
    public Integer insertar(RazonDevolucionDTO razonDevolucion) {
        this.razonDevolucion = razonDevolucion;
        return super.insertar();
    }
    
    @Override
    public Integer eliminar(RazonDevolucionDTO razonDevolucion) {
        this.razonDevolucion = razonDevolucion;
        return super.eliminar();
    }
    
}
