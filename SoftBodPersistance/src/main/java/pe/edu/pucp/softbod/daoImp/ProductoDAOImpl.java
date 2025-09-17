package pe.edu.pucp.softbod.daoImp;

import pe.edu.pucp.softbod.model.ProductoDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.softbod.dao.ProductoDAO;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.model.Unidad_Medida;
import pe.edu.pucp.softbod.db.DBManager;

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
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.producto.getCategoriaId());
        this.statement.setString(2, this.producto.getNombre());
        this.statement.setDouble(3, this.producto.getPrecio_unitario());
        this.statement.setString(4, this.producto.getUnidad_medida().name());  // enum -> String
        this.statement.setDouble(5, this.producto.getStock());
        this.statement.setDouble(6, this.producto.getStockMinimo());
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setInt(1, this.producto.getCategoriaId());
        this.statement.setString(2, this.producto.getNombre());
        this.statement.setDouble(3, this.producto.getPrecio_unitario());
        this.statement.setString(4, this.producto.getUnidad_medida().name());
        this.statement.setDouble(5, this.producto.getStock());
        this.statement.setDouble(6, this.producto.getStockMinimo());
        this.statement.setInt(7, this.producto.getProductoId()); 
    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.producto.getProductoId()); 
    }
    
     @Override
    public Integer insertar(ProductoDTO producto){
        this.producto = producto;
        return super.insertar();
    }
    
    @Override
    public ProductoDTO obtenerPorId(Integer productoId) {
        ProductoDTO producto = null;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT PRODUCTO_ID, CATEGORIA_ID, NOMBRE, PRECIO_UNITARIO, UNIDAD_MEDIDA, "
                    + "STOCK, STOCK_MINIMO FROM BOD_USUARIO WHERE PRODUCTO_ID = ?";
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, productoId);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                producto = new ProductoDTO();
                producto.setProductoId(this.resultSet.getInt("PRODUCTO_ID"));
                producto.setCategoriaId(this.resultSet.getInt("CATEGORIA_ID"));
                producto.setNombre(this.resultSet.getString("NOMBRE"));
                producto.setPrecio_unitario(this.resultSet.getDouble("PRECIO_UNITARIO"));
                producto.setUnidad_medida(Unidad_Medida.valueOf(this.resultSet.getString("UNIDAD_MEDIDA")));
                producto.setStock(this.resultSet.getDouble("STOCK"));
                producto.setStockMinimo(this.resultSet.getDouble("STOCK_MINIMO"));
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
        return producto;
    }
    
    @Override
    public ArrayList<ProductoDTO> listarTodos() {
        ArrayList<ProductoDTO> listaProductos = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = "SELECT PRODUCTO_ID, CATEGORIA_ID, NOMBRE, PRECIO_UNITARIO, UNIDAD_MEDIDA, "
                    + "STOCK, STOCK_MINIMO FROM BOD_USUARIO";
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                ProductoDTO producto = new ProductoDTO();
                producto.setProductoId(this.resultSet.getInt("PRODUCTO_ID"));
                producto.setCategoriaId(this.resultSet.getInt("CATEGORIA_ID"));
                producto.setNombre(this.resultSet.getString("NOMBRE"));
                producto.setPrecio_unitario(this.resultSet.getDouble("PRECIO_UNITARIO"));
                producto.setUnidad_medida(Unidad_Medida.valueOf(this.resultSet.getString("UNIDAD_MEDIDA")));
                producto.setStock(this.resultSet.getDouble("STOCK"));
                producto.setStockMinimo(this.resultSet.getDouble("STOCK_MINIMO"));
                listaProductos.add(producto);
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
        return listaProductos;
    }

    @Override
    public Integer modificar(ProductoDTO producto) {
        this.producto = producto;
        return super.modificar();
    }

    @Override
    public Integer eliminar(ProductoDTO producto) {
        this.producto = producto;
        return super.eliminar();
    }

    
}
