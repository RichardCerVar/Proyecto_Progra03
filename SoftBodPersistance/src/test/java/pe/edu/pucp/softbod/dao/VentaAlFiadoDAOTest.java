//package pe.edu.pucp.softbod.dao;
//
//import java.util.ArrayList;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import pe.edu.pucp.softbod.daoImp.VentaAlFiadoDAOImpl;
//import pe.edu.pucp.softbod.model.VentasFiadasDTO;
//
//
//public class VentaAlFiadoDAOTest extends VentasDAOTest {
//    
//    private VentaAlFiadoDAO ventaAlFiadoDAO;    
//    
//    public VentaAlFiadoDAOTest() {
//        this.ventaAlFiadoDAO = new VentaAlFiadoDAOImpl();        
//    }
//    
//    @Override
//    @Test
//    public void testListarTodos() {
//        System.out.println("listarTodos");
//        ArrayList<Integer> listaVentasFiadasId = new ArrayList<>();
//        super.insertarVentas(listaVentasFiadasId );
//        
//        ArrayList<VentasFiadasDTO> listaUsuarios = this.ventaAlFiadoDAO.listarTodos();
//        assertEquals(listaVentasFiadasId .size(), listaUsuarios.size());
//        for (Integer i = 0; i < listaVentasFiadasId .size(); i++) {
//            assertEquals(listaVentasFiadasId.get(i), listaUsuarios.get(i).getUsuario_Id());
//        }
//        super.eliminarTodo();
//    }
//    
//}
