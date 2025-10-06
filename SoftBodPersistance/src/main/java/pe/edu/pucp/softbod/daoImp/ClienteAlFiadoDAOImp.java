package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import pe.edu.pucp.softbod.dao.ClienteAlFiadoDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public class ClienteAlFiadoDAOImp extends DAOImplBase implements ClienteAlFiadoDAO{
    
    private ClienteAlFiadoDTO clienteAlFiado;
    
    private final CargarTablas cargarTablas;

    public ClienteAlFiadoDAOImp() {
        super("BOD_CLIENTE_AL_FIADO");
        this.clienteAlFiado = null;
        this.retornarLlavePrimaria = true;
        this.cargarTablas = new CargarTablas();
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("CLIENTE_ID",true,true));
        this.listaColumnas.add(new Columna("ALIAS",false,false));
        this.listaColumnas.add(new Columna("NOMBRE",false,false));
        this.listaColumnas.add(new Columna("TELEFONO",false,false));
        this.listaColumnas.add(new Columna("FECHA_DE_PAGO",false,false));
        this.listaColumnas.add(new Columna("ACTIVO",false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.clienteAlFiado.getAlias());
        this.statement.setString(2, this.clienteAlFiado.getNombre());
        this.statement.setString(3, this.clienteAlFiado.getTelefono());
        this.statement.setDate(4,this.clienteAlFiado.getFechaDePago());
        this.statement.setBoolean(5,this.clienteAlFiado.getActivo());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.clienteAlFiado.getAlias());
        this.statement.setString(2, this.clienteAlFiado.getNombre());
        this.statement.setString(3, this.clienteAlFiado.getTelefono());
        this.statement.setDate(4, this.clienteAlFiado.getFechaDePago());
        this.statement.setBoolean(5, this.clienteAlFiado.getActivo());
        this.statement.setInt(6, this.clienteAlFiado.getClienteId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.clienteAlFiado.getClienteId());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.clienteAlFiado = this.cargarTablas.cargarClienteAlFiado(resultSet);
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
//        String sql = "{CALL TA_PROG3.sp_listar_clienteAlFiado()}";
//        Consumer incluirValorDeParametros = null;
//        Object parametros = null;
//        return (ArrayList<ClienteAlFiadoDTO>) super.listarTodos(sql,incluirValorDeParametros,parametros);
        return (ArrayList<ClienteAlFiadoDTO>) super.listarTodos();
    }

}
