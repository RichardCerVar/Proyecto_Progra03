package pe.edu.pucp.softbod.daoImp.gestclientes;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.gestclientes.RegistroPagoFiadoDTO;
import pe.edu.pucp.softbod.dao.gestclientes.RegistroPagoFiadoDAO;
import pe.edu.pucp.softbod.daoImp.DAOImplBase;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.RegistroPagoFiadoParametrosBusqueda;
import pe.edu.pucp.softbod.daoImp.util.RegistroPagoFiadoParametrosBusquedaBuilder;

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
        this.statement.setTimestamp(3, Timestamp.valueOf(this.registroPagosFiados.getFecha()));
        this.statement.setString(4,this.registroPagosFiados.getMetodoPago().name());
        this.statement.setDouble(5,this.registroPagosFiados.getMonto());
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
    public ArrayList<RegistroPagoFiadoDTO> listarTodos() {
        String aliasCliente = null;
        String fechaMaxima = null;
        return (ArrayList<RegistroPagoFiadoDTO>) this.listarPagosFiadosConFiltro(aliasCliente, fechaMaxima);
    }

    public ArrayList<RegistroPagoFiadoDTO> listarPagosFiadosConFiltro(String aliasCliente, String fechaMaxima) {
        String sql = "{ CALL SP_LISTAR_REGISTRO_DE_PAGOS_FIADOS(?, ?) }";

        Object parametros = new RegistroPagoFiadoParametrosBusquedaBuilder()
                            .conAliasCliente(aliasCliente)
                            .conFechaMaxima(fechaMaxima)
                            .buildRegistroPagoFiadoParametrosBusqueda();

        return (ArrayList<RegistroPagoFiadoDTO>) super.listarTodos(sql,
                this::incluirValorDeParametrosParaBuscarPagosFiados,parametros);
    }

    private void incluirValorDeParametrosParaBuscarPagosFiados(Object parametros) {
        RegistroPagoFiadoParametrosBusqueda filtros = (RegistroPagoFiadoParametrosBusqueda) parametros;

        try {
            // 1️⃣ Alias del cliente
            if (filtros.getAliasCliente() != null) {
                this.statement.setString(1, filtros.getAliasCliente());
            } else {
                this.statement.setNull(1, Types.VARCHAR);
            }

            // 2️⃣ Fecha máxima
            if (filtros.getFechaMaxima() != null) {
                this.statement.setDate(2, Date.valueOf(filtros.getFechaMaxima()));
            } else {
                this.statement.setNull(2, Types.DATE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RegistroPagoFiadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<RegistroPagoFiadoDTO> listarTodosPorAliasCliente(String aliasCliente) {
        String fechaMaxima = null;
        return (ArrayList<RegistroPagoFiadoDTO>) this.listarPagosFiadosConFiltro(aliasCliente, fechaMaxima);
    }

    @Override
    public ArrayList<RegistroPagoFiadoDTO> listarTodosPorAliasClienteConFechaFin(String aliasCliente, String fechaFin) {
        return (ArrayList<RegistroPagoFiadoDTO>) this.listarPagosFiadosConFiltro(aliasCliente, fechaFin);
    }


    
}
