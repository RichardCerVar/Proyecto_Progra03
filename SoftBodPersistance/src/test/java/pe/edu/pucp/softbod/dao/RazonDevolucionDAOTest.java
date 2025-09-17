package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softbod.daoImp.RazonDevolucionDAOImp;
import pe.edu.pucp.softbod.model.RazonDevolucionDTO;

public class RazonDevolucionDAOTest {
    
    private RazonDevolucionDAO razonDevoluciondDAO;
    
    public RazonDevolucionDAOTest() {
        razonDevoluciondDAO = new RazonDevolucionDAOImp();
    }
    
    @Test
    public void testInsertar() {
        System.out.println("insertar-razonDevoluciond");
        ArrayList<Integer> listaRazonDevolucion = new ArrayList<>();
        insertarRazonDevoluciones(listaRazonDevolucion);
        eliminarTodo();
    }

    private void insertarRazonDevoluciones(ArrayList<Integer> listaRazonDevolucion){
        
        RazonDevolucionDTO razonDevolucion = new RazonDevolucionDTO();
        razonDevolucion.setDescripcion("PRODUCTO DEFECTUOSO");
        Integer resultado = this.razonDevoluciondDAO.insertar(razonDevolucion);
        assertTrue(resultado!=0);
        listaRazonDevolucion.add(resultado);
        //
        razonDevolucion.setDescripcion("PRODUCTO VENCIDO");
        resultado = this.razonDevoluciondDAO.insertar(razonDevolucion);
        assertTrue(resultado!=0);
        listaRazonDevolucion.add(resultado);
        //
        razonDevolucion.setDescripcion("PRODUCTO EN MAL ESTADO");
        resultado = this.razonDevoluciondDAO.insertar(razonDevolucion);
        assertTrue(resultado!=0);
        listaRazonDevolucion.add(resultado);
    }
    
    @Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId-razonDevolucion");
        ArrayList<Integer> listaRazonDevolucionId = new ArrayList<>();
        insertarRazonDevoluciones(listaRazonDevolucionId);
        
        RazonDevolucionDTO razonDevolucion;
        for (Integer i = 0; i < listaRazonDevolucionId.size(); i++) {
            razonDevolucion = this.razonDevoluciondDAO.obtenerPorId(listaRazonDevolucionId.get(i));
            assertEquals(razonDevolucion.getRazonDevolucionId(), listaRazonDevolucionId.get(i));
        }
        eliminarTodo();
    }

    @Test
    public void testListarTodos() {
        System.out.println("listarTodos-razonDevolucion");
        
        ArrayList<Integer> listaRazonDevolucionId = new ArrayList<>();
        insertarRazonDevoluciones(listaRazonDevolucionId);
        
        ArrayList<RazonDevolucionDTO> listaRazonDevolucion = this.razonDevoluciondDAO.listarTodos();
        assertEquals(listaRazonDevolucion.size(),listaRazonDevolucionId.size());
       
        for (Integer i = 0; i < listaRazonDevolucion.size(); i++) {
            assertEquals(listaRazonDevolucionId.get(i), listaRazonDevolucion.get(i).getRazonDevolucionId()); 
        }
        eliminarTodo();
    }

    @Test
    public void testModificar() {
        System.out.println("modificar-razonDevolucion");
        //listar Id Registros(insertando)
        ArrayList<Integer> listaRazonDevolucionId = new ArrayList<>();
        insertarRazonDevoluciones(listaRazonDevolucionId);
        //listartodo RegistrosAlFIado
        ArrayList<RazonDevolucionDTO> listaRazonDevolucion = this.razonDevoluciondDAO.listarTodos();
        assertEquals(listaRazonDevolucion.size(),listaRazonDevolucionId.size());
        
        for (Integer i = 0; i < listaRazonDevolucionId.size(); i++) {
            listaRazonDevolucion.get(i).setDescripcion("CAMBIASO"+i.toString());
            this.razonDevoluciondDAO.modificar(listaRazonDevolucion.get(i));
        }
        //listar clientes modificados para verificar;
        ArrayList<RazonDevolucionDTO> listaRazonDevolucionModific = this.razonDevoluciondDAO.listarTodos();
        assertEquals(listaRazonDevolucion.size(),listaRazonDevolucionModific.size());
        for (Integer i = 0; i < listaRazonDevolucion.size(); i++) {//verificar lo cambiado
            assertEquals(listaRazonDevolucion.get(i).getDescripcion(),listaRazonDevolucionModific.get(i).getDescripcion());
        }
        eliminarTodo();
    }
    
    @Test
    public void testEliminar() {
        System.out.println("eliminar-razonDevolucion");
        ArrayList<Integer> listaRazonDevolucionId = new ArrayList<>();
        insertarRazonDevoluciones(listaRazonDevolucionId);
        
        eliminarTodo();
    }
    
    private void eliminarTodo() {
        ArrayList<RazonDevolucionDTO> listaRazonDevolucion = this.razonDevoluciondDAO.listarTodos();
        for (Integer i = 0; i < listaRazonDevolucion.size(); i++) {
            Integer resultado = this.razonDevoluciondDAO.eliminar(listaRazonDevolucion.get(i));
            assert(resultado!=0);
            RazonDevolucionDTO razonDevolucion = this.razonDevoluciondDAO.obtenerPorId(listaRazonDevolucion.get(i).getRazonDevolucionId());
            assertNull(razonDevolucion);
        }
    }

}
