//package pe.edu.pucp.sofbod.dao;
//
//import java.util.ArrayList;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import pe.edu.pucp.softbod.dao.VentaAlFiadoDAO;
//import pe.edu.pucp.softbod.daoImp.VentaAlFiadoDAOImpl;
//import pe.edu.pucp.softbod.model.VentasFiadasDTO;
//
//
//public class VentaAlFiadoDAOTest {
//    
//    private VentaAlFiadoDAO ventaAlFiadoDAO;    
//    
//    public VentaAlFiadoDAOTest() {
//        this.ventaAlFiadoDAO = new VentaAlFiadoDAOImpl();        
//    }
//
//    @Test
//    public void testListarTodos() {
//        System.out.println("listarTodos");
//        ArrayList<Integer> listaVentasFiadasId = new ArrayList<>();
//        insertarVentas(listaVentasFiadasId );
//        
//        ArrayList<VentasFiadasDTO> listaUsuarios = this.ventaAlFiadoDAO.listarTodos();
//        assertEquals(listaVentasFiadasId .size(), listaUsuarios.size());
//        for (Integer i = 0; i < listaVentasFiadasId .size(); i++) {
//            assertEquals(listaVentasFiadasId .get(i), listaUsuarios.get(i).getUsuario_id());
//        }
//        eliminarTodo();
//    }
//    
//    private void eliminarTodo(){                
//        ArrayList<VentasFiadasDTO> listaUsuarios = this.ventaAlFiadoDAO.listarTodos();
//        for (Integer i = 0; i < listaUsuarios.size(); i++) {
//            Integer resultado = this.ventaAlFiadoDAO.eliminar(listaUsuarios.get(i));
//            assertNotEquals(0, resultado);
//            VentasFiadasDTO usuario = this.ventaAlFiadoDAO.obtenerPorId(listaUsuarios.get(i).getUsuario_id());
//            assertNull(usuario);
//        }
//    }
//    
//}
