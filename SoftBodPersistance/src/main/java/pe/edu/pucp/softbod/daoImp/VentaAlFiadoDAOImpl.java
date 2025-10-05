package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.VentaFiadaDTO;
import pe.edu.pucp.softbod.dao.VentaFiadaDAO;

public class VentaAlFiadoDAOImpl extends DAOImplBase implements VentaFiadaDAO {
    
    private VentaFiadaDTO ventaAlFiado; 
    
    public VentaAlFiadoDAOImpl() {
        super("BOD_VENTAS_FIADAS");
        this.ventaAlFiado = null;
        this.retornarLlavePrimaria = true;
    }
     
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VENTA_FIADA_ID", true, true));
        this.listaColumnas.add(new Columna("VENTA_ID", false, false));
        this.listaColumnas.add(new Columna("CLIENTE_ID", false, false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1,this.ventaAlFiado.getVenta().getVentaId());
        this.statement.setInt(2, this.ventaAlFiado.getCliente().getClienteId());
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.ventaAlFiado.getVentaFiadaId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        //instanciar resulsett

    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.ventaAlFiado = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.ventaAlFiado);
    }

    @Override
    public ArrayList<VentaFiadaDTO> listarTodos() {
        return (ArrayList<VentaFiadaDTO>) super.listarTodos();
    }

    @Override
    public Integer insertar(VentaFiadaDTO ventaFiada) {
        this.ventaAlFiado = ventaFiada;
        return super.insertar();
    }

    @Override
    public VentaFiadaDTO obtenerPorId(Integer ventaFiada_Id) {
        this.ventaAlFiado = new VentaFiadaDTO();
        this.ventaAlFiado.setVentaFiadaId(ventaFiada_Id);
        super.obtenerPorId();
        return this.ventaAlFiado;
    }
    
}