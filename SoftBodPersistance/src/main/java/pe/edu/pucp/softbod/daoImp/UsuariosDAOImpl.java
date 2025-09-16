package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.UsuariosDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.db.DBManager;
import pe.edu.pucp.softbod.model.Tipo_Usuario;
import pe.edu.pucp.softbod.model.UsuarioDTO;

public class UsuariosDAOImpl extends DAOImplBase implements UsuariosDAO  {
    private UsuarioDTO usuario;
    //private Tipo_Usuario tipo_usuario;

    public UsuariosDAOImpl() {
        super("BOD_USUARIO");
        this.usuario = null;
        this.retornarLlavePrimaria = true;
    }
    //CAMBIAR EL NOMBRE DE LA PRIMARY KEY
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("USUARIO_ID", true, true));
        this.listaColumnas.add(new Columna("USUARIO", false, false));
        this.listaColumnas.add(new Columna("TIPO_USUARIO", false, false));
        this.listaColumnas.add(new Columna("CORREO", false, false));
        this.listaColumnas.add(new Columna("CONTRASENHA", false, false));
        this.listaColumnas.add(new Columna("NOMBRE", false, false));
        this.listaColumnas.add(new Columna("TELEFONO", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.usuario.getUsuario());
        this.statement.setString(2, this.usuario.getTipo_usuario().name());
        this.statement.setString(3, this.usuario.getCorreo());
        this.statement.setString(4, this.usuario.getContrasenha());
        this.statement.setString(5, this.usuario.getNombre());
        this.statement.setString(6, this.usuario.getTelefono());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.usuario.getUsuario());
        this.statement.setString(2, this.usuario.getTipo_usuario().name());
        this.statement.setString(3, this.usuario.getCorreo());
        this.statement.setString(4, this.usuario.getContrasenha());
        this.statement.setString(5, this.usuario.getNombre());
        this.statement.setString(6, this.usuario.getTelefono());
        this.statement.setInt(7, this.usuario.getUsuario_id());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1, this.usuario.getUsuario_id());
    }

    
    @Override
    public Integer insertar(UsuarioDTO usuario) {
        this.usuario = usuario;
        return super.insertar();
    }

    @Override
    public UsuarioDTO obtenerPorId(Integer usuarioId) {
        UsuarioDTO usuario = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT USUARIO_ID, USUARIO, TIPO_USUARIO, CORREO, CONTRASENHA, "
                    + "NOMBRE, TELEFONO FROM BOD_USUARIO WHERE USUARIO_ID = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, usuarioId);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                usuario = new UsuarioDTO();
                usuario.setUsuario_id(this.resultSet.getInt("USUARIO_ID"));
                usuario.setUsuario(this.resultSet.getString("USUARIO"));
                usuario.setTipo_usuario(Tipo_Usuario.valueOf(this.resultSet.getString("TIPO_USUARIO")));
                usuario.setCorreo(this.resultSet.getString("CORREO"));
                usuario.setContrasenha(this.resultSet.getString("CONTRASENHA"));
                usuario.setNombre(this.resultSet.getString("NOMBRE"));
                usuario.setTelefono(this.resultSet.getString("TELEFONO"));
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return usuario;
    }

    @Override
    public ArrayList<UsuarioDTO> listarTodos() {
        ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT USUARIO_ID, TIPO_USUARIO, CORREO, CONTRASENHA, "
                    + "NOMBRE, TELEFONO FROM BOD_USUARIO";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setUsuario_id(this.resultSet.getInt("USUARIO_ID"));
                usuario.setUsuario(this.resultSet.getString("USUARIO"));
                usuario.setTipo_usuario(Tipo_Usuario.valueOf(this.resultSet.getString("TIPO_USUARIO")));
                usuario.setCorreo(this.resultSet.getString("CORREO"));
                usuario.setContrasenha(this.resultSet.getString("CONTRASENHA"));
                usuario.setNombre(this.resultSet.getString("NOMBRE"));
                usuario.setTelefono(this.resultSet.getString("TELEFONO"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return listaUsuarios;
    }

    @Override
    public Integer modificar(UsuarioDTO usuario) {
        this.usuario = usuario;
        return super.modificar();
    }

    @Override
    public Integer eliminar(UsuarioDTO usuario) {
        this.usuario = usuario;
        return super.eliminar();
    }
}
