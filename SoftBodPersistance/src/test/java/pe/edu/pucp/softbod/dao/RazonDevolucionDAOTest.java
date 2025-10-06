package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softbod.daoImp.RazonDevolucionDAOImp;
import pe.edu.pucp.softbod.model.RazonDevolucionDTO;

public class RazonDevolucionDAOTest {
    
    private final RazonDevolucionDAO razonDevolucionDAO;
    
    public RazonDevolucionDAOTest() {
        razonDevolucionDAO = new RazonDevolucionDAOImp();
    }
    
    private void imprimeRazonDevolucion(RazonDevolucionDTO razonDev) {
        System.out.println("id: " + razonDev.getRazonDevolucionId());
        System.out.println("Descripcion: " + razonDev.getDescripcion());
    }
    
    //@Test
    public void testInsertar() {
        System.out.println("insertar-RazonDevolucionDAOTest");
        System.out.println("-------------------------------");

        RazonDevolucionDTO razonDev = new RazonDevolucionDTO();
        razonDev.setDescripcion("Producto en mal estado");
        Integer resultado = this.razonDevolucionDAO.insertar(razonDev);
        assert(resultado!=0);
        
        razonDev.setDescripcion("Producto Defectuoso");
        resultado = this.razonDevolucionDAO.insertar(razonDev);
        assert(resultado!=0);
        
        razonDev.setDescripcion("Producto Vencido");
        resultado = this.razonDevolucionDAO.insertar(razonDev);
        assert(resultado!=0);
        
    }
    
    //@Test
    public void testObtenerPorId(){
        System.out.println("obtenerPorId-RazonDevolucionDAOTest");
        System.out.println("------------------------------------");

        RazonDevolucionDTO razonDev = this.razonDevolucionDAO.obtenerPorId(133);
        assertNotNull(razonDev);
        imprimeRazonDevolucion(razonDev);
    }
    
    //@Test
    public void testEliminar(){
        System.out.println("testEliminar-RazonDevolucionDAOTest");
        System.out.println("-----------------------------------");
        
        RazonDevolucionDTO razonDev = this.razonDevolucionDAO.obtenerPorId(132);
        assertNotNull(razonDev);
        System.out.println("Razon a eliminar: ");
        imprimeRazonDevolucion(razonDev);
        
        Integer resultado = this.razonDevolucionDAO.eliminar(razonDev);
        assert(resultado!=0);
        
        razonDev = this.razonDevolucionDAO.obtenerPorId(132);
        assertNull(razonDev);
        
    }
    
    //@Test
    public void testListarTodos(){
        System.out.println("listarTodos-RazonDevolucionDAOTest");
        System.out.println("-----------------------------------");

        ArrayList<RazonDevolucionDTO> listaRazones = this.razonDevolucionDAO.listarTodos();
        assert(!listaRazones.isEmpty());
        for (Integer i = 0; i < listaRazones.size(); i++) {
            imprimeRazonDevolucion(listaRazones.get(i));
            System.out.println("-----------------------------------");
        }
    }

}
