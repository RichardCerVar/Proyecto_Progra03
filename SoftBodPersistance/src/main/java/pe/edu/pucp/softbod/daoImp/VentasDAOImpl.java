
package pe.edu.pucp.softbod.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.dao.VentasDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.Tipo_de_pago;
import pe.edu.pucp.softbod.model.VentasDTO;


public class VentasDAOImpl extends DAOImplBase implements VentasDAO{
    
    private VentasDTO venta;    
    
    public VentasDAOImpl(){
        super("BOD_VENTAS");
        this.venta=null;
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("VENTA_ID",true,true));
        this.listaColumnas.add(new Columna("TOTAL",false,false));
        this.listaColumnas.add(new Columna("METODO_PAGO",false,false));
        this.listaColumnas.add(new Columna("FECHA",false,false));
        this.listaColumnas.add(new Columna("USUARIO_ID",false,false));
    }
    
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setDouble(1,this.venta.getTotal());
        this.statement.setString(2,this.venta.getMetodo_pago().name());
        this.statement.setDate(3, this.venta.getFecha());
        this.statement.setInt(4,this.venta.getUsuario_Id());
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException { 
        this.statement.setDouble(1,this.venta.getTotal());
        this.statement.setString(2,this.venta.getMetodo_pago().name());
        this.statement.setDate(3, this.venta.getFecha());
        this.statement.setInt(4,this.venta.getUsuario_Id());
        this.statement.setInt(5,this.venta.getVenta_Id());
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException{
        this.statement.setInt(1, this.venta.getVenta_Id());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.venta.getVenta_Id());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.venta = new VentasDTO();
        this.venta.setVenta_Id(this.resultSet.getInt("VENTA_ID"));
        this.venta.setTotal(this.resultSet.getDouble("TOTAL"));
        this.venta.setMetodo_pago(Tipo_de_pago.valueOf(this.resultSet.getString("METODO_PAGO")));
        this.venta.setFecha(this.resultSet.getDate("FECHA"));
        this.venta.setUsuario_Id(this.resultSet.getInt("USUARIO_ID"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.venta = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.venta);
    }
    
    @Override
    public Integer insertar(VentasDTO venta) {
        this.venta=venta;
        return super.insertar();
    }

    @Override
    public VentasDTO obtenerPorId(Integer venta_Id) {
        this.venta = new VentasDTO();
        this.venta.setVenta_Id(venta_Id);
        super.obtenerPorId(false);
        return this.venta;
    }

    @Override
    public ArrayList<VentasDTO> listarTodos() {
        return (ArrayList<VentasDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(VentasDTO venta) {
        this.venta=venta;
        return super.modificar();
    }

    @Override
    public Integer eliminar(VentasDTO venta) {
        this.venta=venta;
        return super.eliminar();
    }
    
}
