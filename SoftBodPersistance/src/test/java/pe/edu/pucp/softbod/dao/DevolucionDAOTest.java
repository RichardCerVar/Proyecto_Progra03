//package pe.edu.pucp.softbod.dao;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import pe.edu.pucp.softbod.daoImp.DevolucionDAOImpl;
//import pe.edu.pucp.softbod.model.DevolucionDTO;
//
//
//public class DevolucionDAOTest {
//    
//    private DevolucionDAO devolucionDAO;
//    
//    public DevolucionDAOTest() {
//        this.devolucionDAO = new DevolucionDAOImpl();
//    }
//
//    /**
//     * Test of insertar method, of class DevolucionDAO.
//     */
//    @Test
//    public void testInsertar() {
//        System.out.println("----Insertar----");
//        ArrayList<Integer> listaDevolucionesId= new ArrayList<>();
//        insertarDevoluciones(listaDevolucionesId);
//        eliminarTodo();
//    }
//
//    private void insertarDevoluciones(ArrayList<Integer> listaDevolucionesId){
//        DevolucionDTO devolucion = new DevolucionDTO();
//        devolucion.setTotal(50.00);
//        devolucion.setFecha(new Date(System.currentTimeMillis()));
//        devolucion.setUsuario(305);
//        Integer resultado = this.devolucionDAO.insertar(devolucion);
//        listaDevolucionesId.add(resultado);
//        System.out.println("Insertando la devolucion con id " + resultado);
//        
//        devolucion = new DevolucionDTO();
//        devolucion.setTotal(80.00);
//        devolucion.setFecha(new Date(System.currentTimeMillis()));
//        devolucion.setUsuario(306);
//        resultado = this.devolucionDAO.insertar(devolucion);
//        listaDevolucionesId.add(resultado);
//        System.out.println("Insertando la devolucion con id " + resultado);
//    }
//    
//    /**
//     * Test of obtenerPorId method, of class DevolucionDAO.
//     */
//    @Test
//    public void testObtenerPorId() {
//        System.out.println("----Obtener por Id----");
//        ArrayList<Integer> listaDevolucionesId = new ArrayList<>();
//        insertarDevoluciones(listaDevolucionesId);
//        
//        DevolucionDTO devolucion = this.devolucionDAO.obtenerPorId(listaDevolucionesId.get(0));
//        System.out.println("Devolucion obtenida de la id "+ devolucion.getDevolucionId());
//        assertEquals(devolucion.getDevolucionId(),listaDevolucionesId.get(0));
//        
//        devolucion = this.devolucionDAO.obtenerPorId(listaDevolucionesId.get(1));
//        System.out.println("Devolucion obtenida de la id "+ devolucion.getDevolucionId());
//        assertEquals(devolucion.getDevolucionId(),listaDevolucionesId.get(1));
//        
//        eliminarTodo();
//    }
//
//    /**
//     * Test of listarTodos method, of class DevolucionDAO.
//     */
//    @Test
//    public void testListarTodos() {
//        System.out.println("----Listar todos----");
//        ArrayList<Integer> listaDevolucionesId= new ArrayList<>();
//        insertarDevoluciones(listaDevolucionesId);
//        
//        ArrayList<DevolucionDTO> listaDevoluciones = this.devolucionDAO.listarTodos();
//        assertEquals(listaDevolucionesId.size(),listaDevoluciones.size());
//        for(Integer i=0; i<listaDevoluciones.size();i++){
//            System.out.println("Modificador : " + listaDevoluciones.get(i).getDevolucionId() + " " + listaDevolucionesId.get(i));
//            assertEquals(listaDevoluciones.get(i).getDevolucionId(),listaDevolucionesId.get(i));
//        }
//        
//        //impresion
//        System.out.println("Lista de devoluciones: ");
//        for(DevolucionDTO dev : listaDevoluciones){
//            System.out.println(dev.getDevolucionId() + " - " + dev.getFecha() + " - " + dev.getTotal());
//        }
//        
//        eliminarTodo();
//    }
//
//    /**
//     * Test of modificar method, of class DevolucionDAO.
//     */
//    @Test
//    public void testModificar() {
//        System.out.println("----Modificar----");
//        ArrayList<Integer> listaDevolucionesId= new ArrayList<>();
//        insertarDevoluciones(listaDevolucionesId);
//        
//        ArrayList<DevolucionDTO> listaDevolucion = this.devolucionDAO.listarTodos();
////        assertEquals(listaDevolucionesId.size(),listaDevolucion.size());
//        
//        //antes
//        System.out.println("Lista de devoluciones Antes: ");
//        for(DevolucionDTO dev : listaDevolucion){
//            System.out.println(dev.getDevolucionId() + " - " + dev.getFecha() + " - " + dev.getTotal());
//        }
//        
//        for(Integer i=0; i<listaDevolucion.size(); i++){
//            listaDevolucion.get(i).setTotal(99.99);
//            listaDevolucion.get(i).setFecha(Date.valueOf(LocalDate.of(2025,Month.MAY,15)));
//            listaDevolucion.get(i).setUsuario(306);
//            this.devolucionDAO.modificar(listaDevolucion.get(i));
//        }
//        
//        ArrayList<DevolucionDTO> listaDevolucionModificado = this.devolucionDAO.listarTodos();
//        
//        //despues
//        System.out.println("Lista de devoluciones Despues: ");
//        for(DevolucionDTO dev : listaDevolucionModificado){
//            System.out.println(dev.getDevolucionId() + " - " + dev.getFecha() + " - " + dev.getTotal());
//        }
//        
//        // Verificación de que la lista de devoluciones modificadas sea igual a la de la lista original en tamaño
//        assertEquals(listaDevolucion.size(), listaDevolucionModificado.size());
//
//        // Verificación campo por campo
//        for (int i = 0; i < listaDevolucion.size(); i++) {
//            assertEquals(listaDevolucion.get(i).getTotal(), listaDevolucionModificado.get(i).getTotal());
//            assertEquals(listaDevolucion.get(i).getFecha(), listaDevolucionModificado.get(i).getFecha());
//        }
//        
//        eliminarTodo();
//    }
//
//    /**
//     * Test of eliminar method, of class DevolucionDAO.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("----Eliminar----");
//        ArrayList<Integer> listaDevolucionesId= new ArrayList<>();
//        insertarDevoluciones(listaDevolucionesId);
//        for(Integer dev : listaDevolucionesId){
//            System.out.println("Eliminado la devolucion con id : "+dev);
//        }
//        eliminarTodo();
//    }
//
//    private void eliminarTodo(){
//        ArrayList<DevolucionDTO> listaDevoluciones = this.devolucionDAO.listarTodos();
//        for(Integer i=0; i<listaDevoluciones.size(); i++){
//            Integer resultado = this.devolucionDAO.eliminar(listaDevoluciones.get(i));
//            assertNotEquals(0,resultado);
//            DevolucionDTO devolucion = this.devolucionDAO.obtenerPorId(listaDevoluciones.get(i).getDevolucionId());
//            assertNull(devolucion);
//        }
//    }
//    
//}
//
