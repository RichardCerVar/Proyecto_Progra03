//package pe.edu.pucp.softbod.dao;
//
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//import pe.edu.pucp.softbod.daoImp.ProductoDAOImpl;
//import pe.edu.pucp.softbod.model.CategoriaDTO;
//import pe.edu.pucp.softbod.model.ProductoDTO;
//import pe.edu.pucp.softbod.model.util.Unidad_Medida;
//
//
//public class ProductoDAOTest {
//    
//    private final ProductoDAO productoDAO;
//    
//    public ProductoDAOTest() {
//        this.productoDAO = new ProductoDAOImpl();
//    }
//    
//    private void imprimeProducto(ProductoDTO producto) {
//        System.out.println("id: " + producto.getProductoId());
//        System.out.println("Categoria ID: " + producto.getCategoria().getCategoriaId());
//        System.out.println("Nombre: " + producto.getNombre());
//        System.out.println("Precio Unitario: " + producto.getPrecioUnitario());
//        System.out.println("Unidad Medida: " + producto.getUnidadMedida());
//        System.out.println("Stock: " + producto.getStock());
//        System.out.println("Stock Minimo: " + producto.getStockMinimo());
//        System.out.println("Activo: " + producto.getActivo());
//    }
//    
//    //@Test
//    public void testInsertar() {
//        System.out.println("insertar-ProductoDAOTest");
//        System.out.println("------------------------");
//
//        ProductoDTO producto = new ProductoDTO();
//        CategoriaDTO categ = new CategoriaDTO();
//        categ.setCategoriaId(18);
//        
//        producto.setCategoria(categ);
//        producto.setNombre("Arroz Costeño");
//        producto.setPrecioUnitario(5.50);
//        producto.setUnidadMedida(Unidad_Medida.KILOGRAMO);
//        producto.setStock(100);
//        producto.setStockMinimo(20);
//        producto.setActivo(Boolean.TRUE);
//        
//        Integer resultado = this.productoDAO.insertar(producto);
//        assert(resultado!=0);
//        
//    }
//
//    @Test
//    public void testObtenerPorId() {
//        System.out.println("obtenerPorId-ProductoDAOTest");
//        System.out.println("-----------------------------");
//
//        ProductoDTO producto = this.productoDAO.obtenerPorId(17);
//        assertNotNull(producto);
//        imprimeProducto(producto);
//        
//    }
////
////    /**
////     * Test of listarTodos method, of class ProductoDAO.
////     */
////    @Test
////    public void testListarTodos() {
////        System.out.println("ListarTodos-ProductoDAOTest");
////        ArrayList<Integer> listaProductosId = new ArrayList<>();
////        insertarProductos(listaProductosId);
////        
////        verificacion
////        ArrayList<ProductoDTO> listaProductos = this.productoDAO.listarTodos();
////        
////         impresión de unos cuantos atributos
////        System.out.println("Lista de productos: ");
////        for (ProductoDTO p : listaProductos) {
////            System.out.println(p.getProductoId() + " - " + p.getNombre() + " - " + p.getPrecio_unitario());
////        }
////        
////        eliminarTodo();
////    }
////
////    /**
////     * Test of modificar method, of class ProductoDAO.
////     */
////    @Test
////    public void testModificar() {
////        System.out.println("Modificar-ProductoDAOTest");
////        ArrayList<Integer> listaProductosId= new ArrayList<>();
////        insertarProductos(listaProductosId);
////        
////        ArrayList<ProductoDTO> listaProductos = this.productoDAO.listarTodos();
////        
////        antes
////        System.out.println("Lista de productos antes");
////        for(ProductoDTO p : listaProductos){
////            System.out.println(p.getProductoId() + " - " + p.getNombre() + " - " + p.getPrecio_unitario());
////        }
////        
////        for(Integer i=0; i< listaProductos.size(); i++){
////            listaProductos.get(i).setNombre("NuevoNombreProducto" + i);
////            listaProductos.get(i).setPrecio_unitario(99.99);
////            listaProductos.get(i).setCategoriaId(20);
////            this.productoDAO.modificar(listaProductos.get(i));
////        }
////        
////        ArrayList<ProductoDTO> listaProductosModificado = this.productoDAO.listarTodos();
////        
////        despues
////        System.out.println("Lista de productos despues");
////        for(ProductoDTO p : listaProductosModificado){
////            System.out.println(p.getProductoId() + " - " + p.getNombre() + " - " + p.getPrecio_unitario());
////        }
////        
////        Verificacion de que la lista de productos modificados sea igual a la de la lista de productos antigua
////        for(Integer i=0; i<listaProductos.size(); i++){
////            assertEquals(listaProductos.get(i).getNombre(),listaProductosModificado.get(i).getNombre());
////            assertEquals(listaProductos.get(i).getPrecio_unitario(),listaProductosModificado.get(i).getPrecio_unitario());
////            assertEquals(listaProductos.get(i).getCategoriaId(),listaProductosModificado.get(i).getCategoriaId());
////        }
////        eliminarTodo();
////    }
////
//// 
//    
//}
