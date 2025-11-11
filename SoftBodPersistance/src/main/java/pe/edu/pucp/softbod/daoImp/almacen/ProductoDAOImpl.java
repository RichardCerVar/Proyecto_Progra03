package pe.edu.pucp.softbod.daoImp.almacen;

import pe.edu.pucp.softbod.model.almacen.ProductoDTO;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softbod.dao.almacen.ProductoDAO;
import pe.edu.pucp.softbod.daoImp.DAOImplBase;
import pe.edu.pucp.softbod.daoImp.util.CargarTablas;
import pe.edu.pucp.softbod.daoImp.util.Columna;
import pe.edu.pucp.softbod.daoImp.util.ProductoParametrosBusqueda;
import pe.edu.pucp.softbod.daoImp.util.ProductoParametrosBusquedaBuilder;

public class ProductoDAOImpl extends DAOImplBase implements ProductoDAO {

    private ProductoDTO producto;
    private final CargarTablas cargaTablas;
    
    public ProductoDAOImpl() {
        super("BOD_PRODUCTO");
        this.producto=null;
        this.retornarLlavePrimaria=true;
        this.cargaTablas = new CargarTablas();
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
        this.statement.setBoolean(7, this.producto.getActivo());
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
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.producto.getProductoId());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.producto = this.cargaTablas.cargarProductoSinCategoria(resultSet);
        this.producto.getCategoria().setDescripcion(this.resultSet.getString("DESCRIPCION"));
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
    public Integer eliminar(ProductoDTO producto) {
        this.producto = producto;
        return super.eliminar();
    }
    
    @Override
    public ProductoDTO obtenerPorId(Integer productoId) {
        this.producto = new ProductoDTO();
        ArrayList<ProductoDTO> lista = listarTodos();
        int inicio = 0;
        int fin = lista.size() - 1;
        for (; inicio <= fin; ) {
            int medio = (inicio + fin) / 2;
            if (Objects.equals(lista.get(medio).getProductoId(), productoId)) {
                return lista.get(medio);
            }
            if (lista.get(medio).getProductoId() < productoId) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return null; 
    }
    
    @Override
    public ArrayList<ProductoDTO> listarTodos() {
        Boolean activo = null;
        String categoria = null;
        String nombreProducto = null;
        return (ArrayList<ProductoDTO>) listarProductosConFiltro(activo, categoria, nombreProducto);
    }

    @Override
    public Integer modificar(ProductoDTO producto) {
        this.producto = producto;
        return super.modificar();
    }
    
    @Override
    public ArrayList<ProductoDTO> listarProductosConFiltro(Boolean activo,
            String categoria, String nombreProducto) {
        String sql = "{ CALL SP_LISTAR_PRODUCTOS(?, ?, ?) }";

        Object parametros = new ProductoParametrosBusquedaBuilder()
                            .conActivo(activo)
                            .conCategoria(categoria)
                            .conNombreProducto(nombreProducto)
                            .buildProductoParametrosBusqueda();

        return (ArrayList<ProductoDTO>) super.listarTodos(sql, this::incluirValorDeParametrosParaBuscarProductos, parametros);
    }
    
    private void incluirValorDeParametrosParaBuscarProductos(Object parametros) {
        ProductoParametrosBusqueda parametrosProd = (ProductoParametrosBusqueda) parametros;
        try {
            // ---- 1. ACTIVO ----
            if (parametrosProd.getActivo() != null) {
                this.statement.setBoolean(1, parametrosProd.getActivo());
            } else {
                this.statement.setNull(1, Types.BOOLEAN);
            }

            // ---- 2. CATEGORÍA ----
            if (parametrosProd.getCategoria() != null) {
                this.statement.setString(2, parametrosProd.getCategoria());
            } else {
                this.statement.setNull(2, Types.VARCHAR);
            }

            // ---- 3. NOMBRE PRODUCTO ----
            if (parametrosProd.getNombreProducto() != null) {
                this.statement.setString(3, parametrosProd.getNombreProducto());
            } else {
                this.statement.setNull(3, Types.VARCHAR);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
