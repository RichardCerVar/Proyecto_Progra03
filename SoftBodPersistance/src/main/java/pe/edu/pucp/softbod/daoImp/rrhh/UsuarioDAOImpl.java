package pe.edu.pucp.softbod.daoImp.rrhh;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.daoImp.util.*;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.dao.rrhh.UsuarioDAO;
import pe.edu.pucp.softbod.daoImp.DAOImplBase;

public class UsuarioDAOImpl extends DAOImplBase implements UsuarioDAO  {
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
        String nameUser = null;
        String emailUser = null;
        Boolean activos = null;
        return (ArrayList<UsuarioDTO>) listarUsuariosConFiltro(nameUser, emailUser, activos);
    }

    @Override
    public Integer modificar(UsuarioDTO usuario) {
        this.usuario = usuario;
        return super.modificar();
    }

    @Override
    public Integer eliminarLogicoUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
        this.usuario.setActivo(false);
        return super.modificar();
    }

    @Override   
    public ArrayList<UsuarioDTO> listarPorNombreParcial(String nombreUser) {
        String emailUser = null;
        Boolean activos = null;
        
        return (ArrayList<UsuarioDTO>) this.listarUsuariosConFiltro(nombreUser, emailUser, activos);
    }

    @Override
    public ArrayList<UsuarioDTO> listarActivos() {
        String nameUser = null;
        String emailUser = null;
        Boolean activos = true;
        return (ArrayList<UsuarioDTO>) listarUsuariosConFiltro(nameUser, emailUser, activos);
    }
    
    private ArrayList<UsuarioDTO> listarUsuariosConFiltro(String nameUser,
                                    String emailUser,Boolean activos) {

        String sql = "{ CALL SP_LISTAR_USUARIOS(?, ?, ?) }";
        Object parametros = new UsuarioParametrosBusquedaBuilder()
                            .conNombreUsuario(nameUser)
                            .conCorreo(emailUser)
                            .conActivo(activos)
                            .buildUsuarioParametrosBusqueda();
        return (ArrayList<UsuarioDTO>)super.listarTodos(sql,
                this::incluirValorDeParametrosParaBuscarUsuarios,
                parametros);
    }

    private void incluirValorDeParametrosParaBuscarUsuarios(Object parametros) {
        UsuarioParametrosBusqueda parametrosUser = (UsuarioParametrosBusqueda) parametros;

        try {
            if (parametrosUser.getNombreUsuario() != null) {
                this.statement.setString(1, parametrosUser.getNombreUsuario());
            } else {
                this.statement.setNull(1, Types.VARCHAR);
            }

            if (parametrosUser.getCorreo() != null) {
                this.statement.setString(2, parametrosUser.getCorreo());
            } else {
                this.statement.setNull(2, Types.VARCHAR);
            }

            if (parametrosUser.getActivo() != null) {
                this.statement.setBoolean(3, parametrosUser.getActivo());
            } else {
                this.statement.setNull(3, Types.BOOLEAN);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<UsuarioDTO> listarInactivos() {
        String nameUser = null;
        String emailUser = null;
        Boolean activos = false;
        return (ArrayList<UsuarioDTO>) listarUsuariosConFiltro(nameUser, emailUser, activos);
    }

    @Override
    public UsuarioDTO obtenerPorCorreo(String emailUser) {
        String nombreUser = null;
        Boolean activos = null;
        ArrayList<UsuarioDTO> lista = this.listarUsuariosConFiltro(nombreUser, emailUser, activos);
        if(!lista.isEmpty()){
            this.usuario = lista.getFirst();
        }
        return this.usuario;
    }

    @Override
    public UsuarioDTO obtenerCuenta(String correo, String contrasenha) {
        String sql = "SELECT USUARIO_ID, USUARIO, TIPO_USUARIOS, CORREO, "
                    + "CONTRASENHA, NOMBRE_COMPLETO, TELEFONO_USUARIO, ACTIVO_USUARIO "
                    + "FROM BOD_USUARIO WHERE CORREO=? AND CONTRASENHA= ?";
        this.usuario = new UsuarioDTO();
        this.usuario.setCorreo(correo);
        this.usuario.setContrasenha(contrasenha);
        try {
            this.abrirConexion();
            this.colocarSQLEnStatement(sql);
            this.incluirValorDeParametrosParaObtenerPorCuenta();
            this.ejecutarSelectEnDB();
            if (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
            } else {
                this.limpiarObjetoDelResultSet();
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }  
        return this.usuario;
    }

    private void incluirValorDeParametrosParaObtenerPorCuenta() throws SQLException {
        this.statement.setString(1, this.usuario.getCorreo());
        this.statement.setString(2, this.usuario.getContrasenha());
        
    }
}
