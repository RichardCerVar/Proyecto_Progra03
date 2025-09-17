package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import pe.edu.pucp.softbod.dao.RegistroPagosFiadosDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.RegistroPagosFiadosDTO;

public class RegistroPagosFiadosDAOImp extends DAOImplBase implements RegistroPagosFiadosDAO{
    
    private RegistroPagosFiadosDTO registroPagosFiados;

    public RegistroPagosFiadosDAOImp() {
        super("BOD_REGISTRO_PAGOS_FIADOS");
        this.registroPagosFiados = null;
        this.retornarLlavePrimaria = true;
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
        this.statement.setInt(1, this.registroPagosFiados.getUsuario());
        this.statement.setInt(2, this.registroPagosFiados.getCliente_al_fiado());
        this.statement.setDate(3, this.registroPagosFiados.getFecha());
        this.statement.setString(4,this.registroPagosFiados.getMetodo_pago().name());
        this.statement.setDouble(5,this.registroPagosFiados.getMonto());
    }

//    @Override
//    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
//        this.statement.setInt(1, this.registroPagosFiados.getUsuario());
//        this.statement.setInt(2, this.registroPagosFiados.getCliente_al_fiado());
//        this.statement.setDate(3, this.registroPagosFiados.getFecha());
//        this.statement.setString(4,this.registroPagosFiados.getMetodo_pago().name());
//        this.statement.setDouble(5,this.registroPagosFiados.getMonto());
//        this.statement.setInt(6,this.registroPagosFiados.getPagoId());
//    }

//    @Override
//    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
//        this.statement.setInt(1,this.registroPagosFiados.getPagoId());
//    }

    @Override
    public Integer insertar(RegistroPagosFiadosDTO registroPagoFiado) {
        this.registroPagosFiados = registroPagoFiado;
        return super.insertar();
    }
    
//    @Override
//    public RegistroPagosFiadosDTO obtenerPorId(Integer pagoId) {
//        RegistroPagosFiadosDTO registroPagoFiado = null;
//        try {
//            this.conexion = DBManager.getInstance().getConnection();
//            String sql = "SELECT PAGO_ID, USUARIO_ID, CLIENTE_ID, FECHA"
//                       + " , METODO_PAGO, MONTO FROM BOD_REGISTRO_PAGOS_FIADOS WHERE PAGO_ID = ?";
//            this.statement = this.conexion.prepareCall(sql);
//            this.statement.setInt(1, pagoId);
//            this.resultSet = this.statement.executeQuery();
//            if (this.resultSet.next()) {
//                registroPagoFiado = new RegistroPagosFiadosDTO(
//                this.resultSet.getInt("PAGO_ID"),
//                this.resultSet.getInt("USUARIO_ID"), 
//                this.resultSet.getInt("CLIENTE_ID"), 
//                this.resultSet.getDate("FECHA"),
//                Tipo_de_pago.valueOf(this.resultSet.getString("METODO_PAGO")),
//                this.resultSet.getDouble("MONTO"));
//            }
//        } catch (SQLException ex) {
//            System.err.println("Error al intentar obtenerPorId - " + ex);
//        } finally {
//            try {
//                if (this.conexion != null) {
//                    this.conexion.close();
//                }
//            } catch (SQLException ex) {
//                System.err.println("Error al cerrar la conexión - " + ex);
//            }
//        }
//        return registroPagoFiado;
//    }

//    @Override
//    public ArrayList<RegistroPagosFiadosDTO> listarTodos() {
//        ArrayList<RegistroPagosFiadosDTO> listaRegistroPagos = new ArrayList<>();
//        try {
//            this.conexion = DBManager.getInstance().getConnection();
//            String sql = "SELECT PAGO_ID, USUARIO_ID, CLIENTE_ID, FECHA"
//                       + " , METODO_PAGO, MONTO FROM BOD_REGISTRO_PAGOS_FIADOS";
//            this.statement = this.conexion.prepareCall(sql);
//            this.resultSet = this.statement.executeQuery();
//            while (this.resultSet.next()) {
//                RegistroPagosFiadosDTO clienteAlFiado = new RegistroPagosFiadosDTO(
//                this.resultSet.getInt("PAGO_ID"),
//                this.resultSet.getInt("USUARIO_ID"), 
//                this.resultSet.getInt("CLIENTE_ID"), 
//                this.resultSet.getDate("FECHA"),
//                Tipo_de_pago.valueOf(this.resultSet.getString("METODO_PAGO")),
//                this.resultSet.getDouble("MONTO"));
//                
//                listaRegistroPagos.add(clienteAlFiado);
//            }
//        } catch (SQLException ex) {
//            System.err.println("Error al intentar listarTodos - " + ex);
//        } finally {
//            try {
//                if (this.conexion != null) {
//                    this.conexion.close();
//                }
//            } catch (SQLException ex) {
//                System.err.println("Error al cerrar la conexión - " + ex);
//            }
//        }
//        return listaRegistroPagos;
//    }

//    @Override
//    public Integer modificar(RegistroPagosFiadosDTO registroPagoFiado) {
//        this.registroPagosFiados = registroPagoFiado;
//        return super.modificar();
//    }
//
//    @Override
//    public Integer eliminar(RegistroPagosFiadosDTO registroPagoFiado) {
//        this.registroPagosFiados = registroPagoFiado;
//        return super.eliminar();
//    }
    
}
