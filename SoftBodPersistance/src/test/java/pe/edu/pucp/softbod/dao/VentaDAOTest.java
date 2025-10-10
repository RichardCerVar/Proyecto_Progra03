package pe.edu.pucp.softbod.dao;

import pe.edu.pucp.softbod.dao.ventas.VentaDAO;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.softbod.daoImp.ventas.VentaDAOImpl;
import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
import pe.edu.pucp.softbod.model.ventas.VentaDTO;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;

public class VentaDAOTest {
    
    private final VentaDAO ventasDAO;
    
    public VentaDAOTest(){
        this.ventasDAO= new VentaDAOImpl();
    }


//    @Test
    public void testInsertar() {
        System.out.println("insertar-VentasDAOTest");
        System.out.println("----------------------");
        
        VentaDTO venta = new VentaDTO();
        venta.setFecha(new Date(19,11,12));
        venta.setMetodoPago(Tipo_de_pago.TRANSFERENCIA);
        venta.setTotal(40.23);

        UsuarioDTO user = new UsuarioDTO();
        user.setUsuarioId(308);
        venta.setUsuario(user);
        
        Integer res = this.ventasDAO.insertar(venta);
        assert(res!=0);
    }

//    @Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId-VentasDAOTest");
        System.out.println("----------------------");
        
        VentaDTO venta = this.ventasDAO.obtenerPorId(42);
        assertNotNull(venta);
        System.out.println("--- Datos de Venta ---");
        System.out.println("ID Venta: " + venta.getVentaId());
        System.out.println("Total: " + venta.getTotal());
        System.out.println("Método Pago: " + venta.getMetodoPago());
        System.out.println("Fecha: " + venta.getFecha());
    }

//    @Test
    public void testListarTodos() {
        System.out.println("listarTodos-VentasDAOTest");
        System.out.println("----------------------");
        
        ArrayList<VentaDTO> listaVentas = this.ventasDAO.listarTodos();
        System.out.println(listaVentas.size());
        assert(!listaVentas.isEmpty());
        for (Integer i = 0; i < listaVentas.size(); i++) {
            VentaDTO venta = listaVentas.get(i);
            UsuarioDTO usuario = venta.getUsuario();
            System.out.println("--- Datos de Venta ---");
            System.out.println("ID Venta: " + venta.getVentaId());
            System.out.println("Total: " + venta.getTotal());
            System.out.println("Método Pago: " + venta.getMetodoPago());
            System.out.println("Fecha: " + venta.getFecha());

        }
    }
    
}
