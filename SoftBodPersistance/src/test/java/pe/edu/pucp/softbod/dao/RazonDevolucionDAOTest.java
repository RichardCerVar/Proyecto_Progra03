//package pe.edu.pucp.softbod.dao;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import pe.edu.pucp.softbod.daoImp.RazonDevolucionDAOImp;
//import pe.edu.pucp.softbod.model.RazonDevolucionDTO;
//
//public class RazonDevolucionDAOTest {
//    
//    private RazonDevolucionDAO razonDevoluciondDAO;
//    
//    public RazonDevolucionDAOTest() {
//        razonDevoluciondDAO = new RazonDevolucionDAOImp();
//    }
//    
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar-RazonDevolucionDAOTest");
//        
//        RazonDevolucionDTO razonDevolucion = new RazonDevolucionDTO();
//        razonDevolucion.setDescripcion("PRODUCTO DEFECTUOSO");
//        Integer resultado = this.razonDevoluciondDAO.insertar(razonDevolucion);
//        assertTrue(resultado!=0);
//        
//        System.out.println(resultado);
//        razonDevolucion.setRazonDevolucionId(resultado);
//        resultado = this.razonDevoluciondDAO.eliminar(razonDevolucion);
//        assertTrue(resultado != 0);
//    }
//
//
//}
