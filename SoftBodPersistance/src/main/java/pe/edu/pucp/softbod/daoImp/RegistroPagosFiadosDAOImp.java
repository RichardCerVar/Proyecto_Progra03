package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import pe.edu.pucp.softbod.dao.RegistroPagosFiadosDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.RegistroPagoFiadoDTO;

public class RegistroPagosFiadosDAOImp extends DAOImplBase implements RegistroPagosFiadosDAO{
    
    private RegistroPagoFiadoDTO registroPagosFiados;

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

    @Override
    public Integer insertar(RegistroPagoFiadoDTO registroPagoFiado) {
        this.registroPagosFiados = registroPagoFiado;
        return super.insertar();
    }

}
