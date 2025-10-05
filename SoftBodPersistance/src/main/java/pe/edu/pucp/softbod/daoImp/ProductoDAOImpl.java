package pe.edu.pucp.softbod.daoImp;

import pe.edu.pucp.softbod.model.ProductoDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.softbod.dao.ProductoDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;

public class ProductoDAOImpl extends DAOImplBase implements ProductoDAO {

    private ProductoDTO producto;
    
    public ProductoDAOImpl() {
        super("BOD_PRODUCTO");
        this.producto=null;
        this.retornarLlavePrimaria=true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("PRODUCTO_ID",true,true));
        this.listaColumnas.add(new Columna("CATEGORIA_ID",false,false));
        this.listaColumnas.add(new Columna("NOMBRE",false,false));
        this.listaColumnas.add(new Columna("PRECIO_UNITARIO",false,false));
        this.listaColumnas.add(new Columna("UNIDAD_MEDIDA",false,false));
        this.listaColumnas.add(new Columna("STOCK",false,false));
        this.listaColumnas.add(new Columna("STOCK_MINIMO",false,false));
        this.listaColumnas.add(new Columna("ACTIVO",false,false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.producto.getCategoria().getCategoriaId());
        this.statement.setString(2, this.producto.getNombre());
        this.statement.setDouble(3, this.producto.getPrecioUnitario());
        this.statement.setString(4, this.producto.getUnidadMedida().name());  // enum -> String
        this.statement.setDouble(5, this.producto.getStock());
        this.statement.setDouble(6, this.producto.getStockMinimo());
        this.statement.setBoolean(6, this.producto.getActivo());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setInt(1, this.producto.getCategoria().getCategoriaId());
        this.statement.setString(2, this.producto.getNombre());
        this.statement.setDouble(3, this.producto.getPrecioUnitario());
        this.statement.setString(4, this.producto.getUnidadMedida().name());
        this.statement.setDouble(5, this.producto.getStock());
        this.statement.setDouble(6, this.producto.getStockMinimo());
        this.statement.setBoolean(7, this.producto.getActivo());
        this.statement.setInt(8, this.producto.getProductoId()); 
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.producto.getProductoId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        //RESULSET
//        this.producto = new ProductoDTO();
//        this.producto.setProductoId(this.resultSet.getInt("PRODUCTO_ID"));
//        this.producto.setCategoriaId(this.resultSet.getInt("CATEGORIA_ID"));
//        this.producto.setNombre(this.resultSet.getString("NOMBRE"));
//        this.producto.setPrecio_unitario(this.resultSet.getDouble("PRECIO_UNITARIO"));
//        this.producto.setUnidad_medida(Unidad_Medida.valueOf(this.resultSet.getString("UNIDAD_MEDIDA")));
//        this.producto.setStock(this.resultSet.getDouble("STOCK"));
//        this.producto.setStockMinimo(this.resultSet.getDouble("STOCK_MINIMO"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.producto = null;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException{
        this.instanciarObjetoDelResultSet();
        lista.add(this.producto);
    }
    
    @Override
    public Integer insertar(ProductoDTO producto){
        this.producto = producto;
        return super.insertar();
    }
    
    @Override
    public ProductoDTO obtenerPorId(Integer productoId) {
        this.producto = new ProductoDTO();
        this.producto.setProductoId(productoId);
        super.obtenerPorId();
        return this.producto;
    }
    
    @Override
    public ArrayList<ProductoDTO> listarTodos() {
        return (ArrayList<ProductoDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(ProductoDTO producto) {
        this.producto = producto;
        return super.modificar();
    }
    
}
