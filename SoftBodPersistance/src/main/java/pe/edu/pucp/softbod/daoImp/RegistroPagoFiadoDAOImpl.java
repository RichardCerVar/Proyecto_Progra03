package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.RegistroPagoFiadoDTO;
import pe.edu.pucp.softbod.dao.RegistroPagoFiadoDAO;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;

public class RegistroPagoFiadoDAOImpl extends DAOImplBase implements RegistroPagoFiadoDAO{
    
    private RegistroPagoFiadoDTO registroPagosFiados;
    private final CargarTablas cargaTablas;
    
    public RegistroPagoFiadoDAOImpl() {
        super("BOD_REGISTRO_PAGOS_FIADOS");
        this.registroPagosFiados = null;
        this.retornarLlavePrimaria = true;
        this.cargaTablas = new CargarTablas();
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("PAGO_ID",true,true));
        this.listaColumnas.add(new Columna("USUARIO_ID",false,false));
        this.listaColumnas.add(new Columna("CLIENTE_ID",false,false));
        this.listaColumnas.add(new Columna("FECHA",false,false));
        this.listaColumnas.add(new Columna("METODO_PAGO",false,false));
        this.listaColumnas.add(new Columna("MONTO",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.registroPagosFiados.getUsuario().getUsuarioId());
        this.statement.setInt(2, this.registroPagosFiados.getCliente().getClienteId());
        this.statement.setDate(3, this.registroPagosFiados.getFecha());
        this.statement.setString(4,this.registroPagosFiados.getMetodoPago().name());
        this.statement.setDouble(5,this.registroPagosFiados.getMonto());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.registroPagosFiados.getPagoId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.registroPagosFiados = this.cargaTablas.cargarRegistroPagoFiadoDTO(resultSet);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.registroPagosFiados = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.registroPagosFiados);
    }

    @Override
    public Integer insertar(RegistroPagoFiadoDTO registroPagoFiado) {
        this.registroPagosFiados = registroPagoFiado;
        return super.insertar();
    }

    @Override
    public RegistroPagoFiadoDTO obtenerPorId(Integer pagoId) {
        this.registroPagosFiados = new RegistroPagoFiadoDTO();
        this.registroPagosFiados.setPagoId(pagoId);
        super.obtenerPorId();
        return this.registroPagosFiados;
    }

    @Override
    public ArrayList<RegistroPagoFiadoDTO> listarTodos() {
        return (ArrayList<RegistroPagoFiadoDTO>) super.listarTodos();
    }

}
