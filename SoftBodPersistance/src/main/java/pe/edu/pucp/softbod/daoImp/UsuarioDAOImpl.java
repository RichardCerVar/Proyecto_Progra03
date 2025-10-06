package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.dao.UsuariosDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.UsuarioDTO;

public class UsuarioDAOImpl extends DAOImplBase implements UsuariosDAO  {
    private UsuarioDTO usuario;
    private final CargarTablas cargaTablas;

    public UsuarioDAOImpl() {
        super("BOD_USUARIO");
        this.usuario = null;
        this.retornarLlavePrimaria = true;
        this.cargaTablas = new CargarTablas();
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("USUARIO_ID", true, true));
        this.listaColumnas.add(new Columna("USUARIO", false, false));
        this.listaColumnas.add(new Columna("TIPO_USUARIOS", false, false));
        this.listaColumnas.add(new Columna("CORREO", false, false));
        this.listaColumnas.add(new Columna("CONTRASENHA", false, false));
        this.listaColumnas.add(new Columna("NOMBRE_COMPLETO", false, false));
        this.listaColumnas.add(new Columna("TELEFONO_USUARIO", false, false));
        this.listaColumnas.add(new Columna("ACTIVO_USUARIO", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.usuario.getUsuario());
        this.statement.setString(2, this.usuario.getTipoUsuarios().name());
        this.statement.setString(3, this.usuario.getCorreo());
        this.statement.setString(4, this.usuario.getContrasenha());
        this.statement.setString(5, this.usuario.getNombre());
        this.statement.setString(6, this.usuario.getTelefono());
        this.statement.setBoolean(7, this.usuario.getActivo());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.usuario.getUsuario());
        this.statement.setString(2, this.usuario.getTipoUsuarios().name());
        this.statement.setString(3, this.usuario.getCorreo());
        this.statement.setString(4, this.usuario.getContrasenha());
        this.statement.setString(5, this.usuario.getNombre());
        this.statement.setString(6, this.usuario.getTelefono());
        this.statement.setBoolean(7, this.usuario.getActivo());
        this.statement.setInt(8, this.usuario.getUsuarioId());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.usuario.getUsuarioId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.usuario = this.cargaTablas.cargarUsuario(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.usuario = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.usuario);
    }
    
    @Override
    public Integer insertar(UsuarioDTO usuario) {
        this.usuario = usuario;
        return super.insertar();
    }

    @Override
    public UsuarioDTO obtenerPorId(Integer usuarioId) {
        this.usuario = new UsuarioDTO();
        this.usuario.setUsuarioId(usuarioId);
        super.obtenerPorId();
        return this.usuario;
    }

    @Override
    public ArrayList<UsuarioDTO> listarTodos() {
        return (ArrayList<UsuarioDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(UsuarioDTO usuario) {
        this.usuario = usuario;
        return super.modificar();
    }
    
}
