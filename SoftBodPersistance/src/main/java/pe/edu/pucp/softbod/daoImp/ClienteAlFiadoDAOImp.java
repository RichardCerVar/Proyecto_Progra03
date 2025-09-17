package pe.edu.pucp.softbod.daoImp;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.ClienteAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.db.DBManager;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public class ClienteAlFiadoDAOImp extends DAOImplBase implements ClienteAlFiadoDAO{
    
    private ClienteAlFiadoDTO clienteAlFiado;

    public ClienteAlFiadoDAOImp() {
        super("BOD_CLIENTE_AL_FIADO");
        this.clienteAlFiado = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("CLIENTE_ID",true,true));
        this.listaColumnas.add(new Columna("ALIAS",false,false));
        this.listaColumnas.add(new Columna("NOMBRE",false,false));
        this.listaColumnas.add(new Columna("TELEFONO",false,false));
        this.listaColumnas.add(new Columna("FECHA_DE_PAGO",false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.clienteAlFiado.getAlias());
        this.statement.setString(2, this.clienteAlFiado.getNombre());
        this.statement.setString(3, this.clienteAlFiado.getTelefono());
        this.statement.setDate(4,this.clienteAlFiado.getFecha_de_pago());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.clienteAlFiado.getAlias());
        this.statement.setString(2, this.clienteAlFiado.getNombre());
        this.statement.setString(3, this.clienteAlFiado.getTelefono());
        this.statement.setDate(4, this.clienteAlFiado.getFecha_de_pago());
        this.statement.setInt(5, this.clienteAlFiado.getClienteId());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.clienteAlFiado.getClienteId());
    }
    

    @Override
    public Integer insertar(ClienteAlFiadoDTO clienteAlFiado) {
        this.clienteAlFiado = clienteAlFiado;
        return super.insertar();
    }

    @Override
    public ClienteAlFiadoDTO obtenerPorId(String aliasClienteAlFiado) {
        ClienteAlFiadoDTO clienteAlFiado = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT CLIENTE_ID, ALIAS, NOMBRE, TELEFONO,FECHA_DE_PAGO FROM "
                    + "BOD_CLIENTE_AL_FIADO WHERE ALIAS = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setString(1, aliasClienteAlFiado);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                clienteAlFiado = new ClienteAlFiadoDTO(
                this.resultSet.getInt("CLIENTE_ID"),
                this.resultSet.getString("ALIAS"), 
                this.resultSet.getString("NOMBRE"),
                this.resultSet.getString("TELEFONO"),
                this.resultSet.getDate("FECHA_DE_PAGO"));
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
        return clienteAlFiado;
    }

    @Override
    public ArrayList<ClienteAlFiadoDTO> listarTodos() {
        ArrayList<ClienteAlFiadoDTO> listaClientesAlFiado = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT CLIENTE_ID, ALIAS, NOMBRE, TELEFONO,FECHA_DE_PAGO FROM "
                            + "BOD_CLIENTE_AL_FIADO";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                ClienteAlFiadoDTO clienteAlFiado = new ClienteAlFiadoDTO(
                this.resultSet.getInt("CLIENTE_ID"),
                this.resultSet.getString("ALIAS"), 
                this.resultSet.getString("NOMBRE"),
                this.resultSet.getString("TELEFONO"),
                this.resultSet.getDate("FECHA_DE_PAGO"));
                
                listaClientesAlFiado.add(clienteAlFiado);
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
        return listaClientesAlFiado;
    }

    @Override
    public Integer modificar(ClienteAlFiadoDTO clienteAlFiado) {
        this.clienteAlFiado = clienteAlFiado;
        return super.modificar();
    }

    @Override
    public Integer eliminar(ClienteAlFiadoDTO clienteAlFiado) {
        this.clienteAlFiado = clienteAlFiado;
        return super.eliminar();
    }

}
