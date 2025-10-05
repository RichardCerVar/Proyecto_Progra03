package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.dao.ClienteAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
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
        this.statement.setDate(4,this.clienteAlFiado.getFechaDePago());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.clienteAlFiado.getAlias());
        this.statement.setString(2, this.clienteAlFiado.getNombre());
        this.statement.setString(3, this.clienteAlFiado.getTelefono());
        this.statement.setDate(4, this.clienteAlFiado.getFechaDePago());
        this.statement.setInt(5, this.clienteAlFiado.getClienteId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.clienteAlFiado.getClienteId());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.clienteAlFiado = new ClienteAlFiadoDTO();
        this.clienteAlFiado.setClienteId(this.resultSet.getInt("CLIENTE_ID"));
        this.clienteAlFiado.setAlias(this.resultSet.getString("ALIAS"));
        this.clienteAlFiado.setNombre(this.resultSet.getString("NOMBRE"));
        this.clienteAlFiado.setTelefono(this.resultSet.getString("TELEFONO"));
        this.clienteAlFiado.setFechaDePago(this.resultSet.getDate("FECHA_DE_PAGO"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.clienteAlFiado = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.clienteAlFiado);
    }
    
    @Override
    public Integer insertar(ClienteAlFiadoDTO clienteAlFiado) {
        this.clienteAlFiado = clienteAlFiado;
        return super.insertar();
    }

    @Override
    public Integer modificar(ClienteAlFiadoDTO clienteAlFiado) {
        this.clienteAlFiado = clienteAlFiado;
        return super.modificar();
    }
    
    @Override
    public ClienteAlFiadoDTO obtenerPorId(Integer clienteId) {
        this.clienteAlFiado = new ClienteAlFiadoDTO();
        this.clienteAlFiado.setClienteId(clienteId);
        super.obtenerPorId();
        return this.clienteAlFiado; 
    }

    @Override
    public ArrayList<ClienteAlFiadoDTO> listarTodos() {
        return (ArrayList<ClienteAlFiadoDTO>) super.listarTodos();
    }

}
