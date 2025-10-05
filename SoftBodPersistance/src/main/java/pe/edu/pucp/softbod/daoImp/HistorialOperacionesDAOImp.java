package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.dao.HistorialOperacionesDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.HistorialOperacionesDTO;
import pe.edu.pucp.softbod.model.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Operacion;


public class HistorialOperacionesDAOImp extends DAOImplBase implements HistorialOperacionesDAO {

   private HistorialOperacionesDTO historial;
    
    public HistorialOperacionesDAOImp() {
        super("BOD_HISTORIAL_OPERACIONES");
        this.historial=null;
        this.retornarLlavePrimaria=true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("OPERACION_ID",true,true));
        this.listaColumnas.add(new Columna("TABLA_AFECTADA",false,false));
        this.listaColumnas.add(new Columna("OPERACION",false,false));
        this.listaColumnas.add(new Columna("FECHA_HORA",false,false));
        this.listaColumnas.add(new Columna("USUARIO_ID",false,false));
    }
    
    @Override //ocurre algo si lo implemento en orden distinto?
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1,this.historial.getOperacionId());
        this.statement.setDate(2,this.historial.getFechaHora());
        this.statement.setString(3,this.historial.getTablaAfectada());
        this.statement.setString(4,this.historial.getOperacion().name());
        this.statement.setInt(5,this.historial.getUsuario().getUsuarioId());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.historial.getOperacionId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.historial = new HistorialOperacionesDTO();
        this.historial.setOperacionId(this.resultSet.getInt("OPERACION_ID"));
        this.historial.setTablaAfectada((this.resultSet.getString("TABLA_AFECTADA")));
        this.historial.setFechaHora(this.resultSet.getDate("FECHA_HORA"));
        this.historial.setOperacion(Tipo_Operacion.valueOf(this.resultSet.getString("OPERACION")));
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioId(this.resultSet.getInt("USUARIO_ID"));
        this.historial.setUsuario(usuario);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.historial = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.historial);
    }
    
    @Override
    public Integer insertar(HistorialOperacionesDTO devolucion) {
        this.historial = devolucion;
        return super.insertar();
    }
    
    @Override
    public HistorialOperacionesDTO obtenerPorId(Integer operacionId) {
        this.historial = new HistorialOperacionesDTO();
        this.historial.setOperacionId(operacionId);
        super.obtenerPorId();
        return this.historial;
    }

    @Override
    public ArrayList<HistorialOperacionesDTO> listarTodos() {
        return (ArrayList<HistorialOperacionesDTO>) super.listarTodos();
    }
    
}
