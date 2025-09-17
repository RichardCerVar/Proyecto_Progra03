package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.softbod.daoImp.VentasDAOImpl;
import pe.edu.pucp.softbod.model.Tipo_de_pago;
import pe.edu.pucp.softbod.model.VentasDTO;
import java.util.Date;

public class VentasDAOTest {
    
    private VentasDAO ventasDAO;
    
    public VentasDAOTest(){
        this.ventasDAO= new VentasDAOImpl();
    }


    @Test
    public void testInsertar() {
        System.out.println("insertar-VentasDAOTest");
        ArrayList<Integer> listaVentaId = new ArrayList<>();
        insertarVentas(listaVentaId);        
        eliminarTodo();
    }
    
    public void insertarVentas(ArrayList<Integer> listaVentaId){
        //cambiar ids usuario
        VentasDTO venta= new VentasDTO();
        venta.setTotal(103.0);
        venta.setMetodo_pago(Tipo_de_pago.EFECTIVO);
        venta.setFecha(new Date());
        venta.setUsuario_Id(320);
        Integer resultado=this.ventasDAO.insertar(venta);
        assertTrue(resultado != 0);
        listaVentaId.add(resultado);
        
        
        venta.setTotal(123.02);
        venta.setMetodo_pago(Tipo_de_pago.TRANSFERENCIA);
        venta.setFecha(new Date());
        venta.setUsuario_Id(321);
        resultado=this.ventasDAO.insertar(venta);
        assertTrue(resultado != 0);
        listaVentaId.add(resultado);
        
        venta.setTotal(4133.32);
        venta.setMetodo_pago(Tipo_de_pago.EFECTIVO);
        venta.setFecha(new Date());
        venta.setUsuario_Id(322);
        resultado=this.ventasDAO.insertar(venta);
        assertTrue(resultado != 0);
        listaVentaId.add(resultado);
        
    }

    @Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId-VentasDAOTest");
        ArrayList<Integer> listaVentaId = new ArrayList<>();
        insertarVentas(listaVentaId);
        
        VentasDTO venta=this.ventasDAO.obtenerPorId(listaVentaId.get(0));
        assertEquals(venta.getVenta_Id(),listaVentaId.get(0));
        
        venta=this.ventasDAO.obtenerPorId(listaVentaId.get(1));
        assertEquals(venta.getVenta_Id(),listaVentaId.get(1));
        
        venta=this.ventasDAO.obtenerPorId(listaVentaId.get(2));
        assertEquals(venta.getVenta_Id(),listaVentaId.get(2));
        
        eliminarTodo();
    }


    @Test
    public void testListarTodos() {
        System.out.println("listarTodos-VentasDAOTest");
        ArrayList<Integer> listaVentaId = new ArrayList<>();
        insertarVentas(listaVentaId);
        
        ArrayList<VentasDTO> listaVentas=this.ventasDAO.listarTodos();
        assertEquals(listaVentaId.size(),listaVentas.size());
        for (Integer i = 0; i < listaVentaId.size(); i++) {
            assertEquals(listaVentaId.get(i), listaVentas.get(i).getVenta_Id());
        }
        
        eliminarTodo();
    }


    @Test
    public void testModificar() {
        System.out.println("modificar-VentasDAOTest");
        ArrayList<Integer> listaVentaId = new ArrayList<>();
        insertarVentas(listaVentaId);
        
        ArrayList<VentasDTO> listaVentas=this.ventasDAO.listarTodos();
        assertEquals(listaVentaId.size(),listaVentas.size());
        for (Integer i = 0; i < listaVentaId.size(); i++) {
            listaVentas.get(i).setMetodo_pago(Tipo_de_pago.EFECTIVO);
            listaVentas.get(i).setTotal(1000.0);
            //cambiar id aqui
            listaVentas.get(i).setUsuario_Id(323);
            this.ventasDAO.modificar(listaVentas.get(i));
        }
        
        ArrayList<VentasDTO> listaVentasModificados=this.ventasDAO.listarTodos();
        assertEquals(listaVentas.size(),listaVentasModificados.size());
        for (Integer i = 0; i < listaVentas.size(); i++) {
             assertEquals(listaVentas.get(i).getUsuario_Id(),listaVentasModificados.get(i).getUsuario_Id());
             assertEquals(listaVentas.get(i).getTotal(),listaVentasModificados.get(i).getTotal());
             assertEquals(listaVentas.get(i).getMetodo_pago(),listaVentasModificados.get(i).getMetodo_pago());
        }
        eliminarTodo();
    }


    @Test
    public void testEliminar() {
        System.out.println("eliminar-VentasDAOTest");
        ArrayList<Integer> listaVentaId = new ArrayList<>();
        insertarVentas(listaVentaId);
        eliminarTodo();
    }
    
    

    public void eliminarTodo(){ 
        ArrayList<VentasDTO> listaVentas = this.ventasDAO.listarTodos();
        for (Integer i = 0; i < listaVentas.size(); i++) {
            Integer resultado = this.ventasDAO.eliminar(listaVentas.get(i));
            assertNotEquals(0, resultado);
            VentasDTO venta = this.ventasDAO.obtenerPorId(listaVentas.get(i).getVenta_Id());
            assertNull(venta);
        }
        
    }
    
}
