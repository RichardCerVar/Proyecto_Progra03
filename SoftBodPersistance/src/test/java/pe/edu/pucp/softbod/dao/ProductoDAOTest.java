//package pe.edu.pucp.softbod.dao;
//
//import java.util.ArrayList;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import pe.edu.pucp.softbod.daoImp.ProductoDAOImpl;
//import pe.edu.pucp.softbod.model.ProductoDTO;
//import pe.edu.pucp.softbod.model.Unidad_Medida;
//
//
//public class ProductoDAOTest {
//    
//    private ProductoDAO productoDAO;
//    
//    public ProductoDAOTest() {
//        this.productoDAO = new ProductoDAOImpl();
//    }
//
//
//    @Test
//    public void testInsertar() {
//        System.out.println("Insertar-ProductoDAOTest");
//        ArrayList<Integer> listaProductosId = new ArrayList<>();
//        insertarProductos(listaProductosId);
//        eliminarTodo();
//    }
//
//    
//    public void insertarProductos(ArrayList<Integer> listaProductosId){       
//        // Primer producto
//        ProductoDTO p1 = new ProductoDTO();
//        p1.setNombre("Azúcar ORO 1kg");
//        p1.setPrecio_unitario(6.00);
//        p1.setCategoriaId(19);
//        p1.setUnidad_medida(Unidad_Medida.UNIDAD);
//        p1.setStock(100.0);
//        p1.setStockMinimo(20.0);
//        Integer r1 = this.productoDAO.insertar(p1);
//        assertTrue(r1 != 0);
//        listaProductosId.add(r1);
//        System.out.println("Insertado el producto con id "+ r1);
//        
//        
//        // Segundo producto
//        p1 = new ProductoDTO();
//        p1.setNombre("Jugo Naranja 1L");
//        p1.setPrecio_unitario(4.50);
//        p1.setCategoriaId(20);
//        p1.setUnidad_medida(Unidad_Medida.LITRO);
//        p1.setStock(50.0);
//        p1.setStockMinimo(10.0);
//        Integer r2 = this.productoDAO.insertar(p1);
//        assertTrue(r2 != 0);
//        listaProductosId.add(r2);
//        System.out.println("Insertado el producto con id "+ r2);
//    }
//    
//    /**
//     * Test of obtenerPorId method, of class ProductoDAO.
//     */
//    @Test
//    public void testObtenerPorId() {
//        System.out.println("ObtenerPorID-ProductoDAOTest");
//        ArrayList<Integer> listaProductosId = new ArrayList<>();
//        insertarProductos(listaProductosId);
//        
//        ProductoDTO producto = this.productoDAO.obtenerPorId(listaProductosId.get(0));
//        System.out.println("Producto obtenido de la id "+ producto.getProductoId());
//        assertEquals(producto.getProductoId(),listaProductosId.get(0));
//        
//        producto = this.productoDAO.obtenerPorId(listaProductosId.get(1));
//        System.out.println("Producto obtenido de la id "+ producto.getProductoId());
//        assertEquals(producto.getProductoId(),listaProductosId.get(1));
//        
//        eliminarTodo();
//        
//    }
//
//    /**
//     * Test of listarTodos method, of class ProductoDAO.
//     */
//    @Test
//    public void testListarTodos() {
//        System.out.println("ListarTodos-ProductoDAOTest");
//        ArrayList<Integer> listaProductosId = new ArrayList<>();
//        insertarProductos(listaProductosId);
//        
//        //verificacion
//        ArrayList<ProductoDTO> listaProductos = this.productoDAO.listarTodos();
//        
//        // impresión de unos cuantos atributos
//        System.out.println("Lista de productos: ");
//        for (ProductoDTO p : listaProductos) {
//            System.out.println(p.getProductoId() + " - " + p.getNombre() + " - " + p.getPrecio_unitario());
//        }
//        
//        eliminarTodo();
//    }
//
//    /**
//     * Test of modificar method, of class ProductoDAO.
//     */
//    @Test
//    public void testModificar() {
//        System.out.println("Modificar-ProductoDAOTest");
//        ArrayList<Integer> listaProductosId= new ArrayList<>();
//        insertarProductos(listaProductosId);
//        
//        ArrayList<ProductoDTO> listaProductos = this.productoDAO.listarTodos();
//        
//        //antes
//        System.out.println("Lista de productos antes");
//        for(ProductoDTO p : listaProductos){
//            System.out.println(p.getProductoId() + " - " + p.getNombre() + " - " + p.getPrecio_unitario());
//        }
//        
//        for(Integer i=0; i< listaProductos.size(); i++){
//            listaProductos.get(i).setNombre("NuevoNombreProducto" + i);
//            listaProductos.get(i).setPrecio_unitario(99.99);
//            listaProductos.get(i).setCategoriaId(20);
//            this.productoDAO.modificar(listaProductos.get(i));
//        }
//        
//        ArrayList<ProductoDTO> listaProductosModificado = this.productoDAO.listarTodos();
//        
//        //despues
//        System.out.println("Lista de productos despues");
//        for(ProductoDTO p : listaProductosModificado){
//            System.out.println(p.getProductoId() + " - " + p.getNombre() + " - " + p.getPrecio_unitario());
//        }
//        
//        //Verificacion de que la lista de productos modificados sea igual a la de la lista de productos antigua
//        for(Integer i=0; i<listaProductos.size(); i++){
//            assertEquals(listaProductos.get(i).getNombre(),listaProductosModificado.get(i).getNombre());
//            assertEquals(listaProductos.get(i).getPrecio_unitario(),listaProductosModificado.get(i).getPrecio_unitario());
//            assertEquals(listaProductos.get(i).getCategoriaId(),listaProductosModificado.get(i).getCategoriaId());
//        }
//        eliminarTodo();
//    }
//
//    /**
//     * Test of eliminar method, of class ProductoDAO.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("Eliminar-ProductoDAOTest");
//        ArrayList<Integer> listaProductosId = new ArrayList<>();
//        insertarProductos(listaProductosId);
//        // impresión de unos cuantos atributos
//        for (Integer pid : listaProductosId) {
//            System.out.println("Eliminado este producto con ID "+ pid);
//        }
//        eliminarTodo();
//    }
//    
//    private void eliminarTodo(){
//        ArrayList<ProductoDTO> listaProductos = this.productoDAO.listarTodos();
//        for( Integer i=0; i < listaProductos.size(); i++){
//            Integer resultado = this.productoDAO.eliminar(listaProductos.get(i));
//            assertNotEquals(0,resultado);
//            ProductoDTO producto = this.productoDAO.obtenerPorId(listaProductos.get(i).getProductoId());
//            assertNull(producto);
//        }
//    }
//}
