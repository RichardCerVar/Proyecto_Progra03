package pe.edu.pucp.softbod.dao;

import java.sql.Date;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.softbod.daoImp.VentaAlFiadoDAOImpl;
import pe.edu.pucp.softbod.daoImp.VentaDAOImpl;
import pe.edu.pucp.softbod.model.*;
import pe.edu.pucp.softbod.model.util.Tipo_de_pago;

public class VentaFiadaDAOTest {
    private final VentaFiadaDAO ventaFiadaDao;
    private final VentasDAO ventaDao;
    
    public VentaFiadaDAOTest() {
        this.ventaFiadaDao = new VentaAlFiadoDAOImpl();
        this.ventaDao = new VentaDAOImpl();
    }
    
    private void imprimeVentaFiada(VentaFiadaDTO ventaFiada) {
        System.out.println("=== VENTA FIADA ===");
        System.out.println("ID Venta Fiada: " + ventaFiada.getVentaFiadaId());
        // Información de la Venta
        System.out.println("\n--- DATOS DE LA VENTA ---");
        System.out.println("Venta ID: " + ventaFiada.getVenta().getVentaId());
        System.out.println("Total: " + ventaFiada.getVenta().getTotal());
        System.out.println("Metodo Pago: " + ventaFiada.getVenta().getMetodoPago());
        System.out.println("Fecha: " + ventaFiada.getVenta().getFecha());

    }

//    @Test
    public void testInsertar() {
        System.out.println("insertar-VentaFiadaDAOTest");
        System.out.println("---------------------");

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioId(308); 

        VentaDTO venta = new VentaDTO();
        venta.setTotal(120.50);
        venta.setMetodoPago(Tipo_de_pago.EFECTIVO);
        venta.setFecha(new Date(19,8,12));
        venta.setUsuario(usuario);
        Integer idVenta = this.ventaDao.insertar(venta);
        venta.setVentaId(idVenta);
        
        ClienteAlFiadoDTO cliAlFiado = new ClienteAlFiadoDTO();
        cliAlFiado.setClienteId(265);
        
        VentaFiadaDTO ventaFiada = new VentaFiadaDTO();
        ventaFiada.setVenta(venta);
        ventaFiada.setCliente(cliAlFiado);
        
        Integer resultado = this.ventaFiadaDao.insertar(ventaFiada);
        
        assert(resultado!=0);
    }

   
    @Test   
    public void testObtenerPorId() {
        System.out.println("obtenerPorId-VentaFiadaDAOTest");
        System.out.println("------------------------------");

        VentaFiadaDTO ventaFiada = this.ventaFiadaDao.obtenerPorId(3);
        assertNotNull(ventaFiada);
        imprimeVentaFiada(ventaFiada);
    }

//    @Test
    public void testListarTodos() {
        System.out.println("listarTodos-VentaFiadaDAOTest");
        System.out.println("------------------------------");

        ArrayList<VentaFiadaDTO> listaVentaFiada = this.ventaFiadaDao.listarTodos();
        assert(!listaVentaFiada.isEmpty());
        for (Integer i = 0;  i< listaVentaFiada.size(); i++) {
            imprimeVentaFiada(listaVentaFiada.get(i));
        }
    }
    
//    @Test
    public void testListarTodosPorAliasCliente() {
        System.out.println("listarTodos-VentaFiadaDAOTest");
        System.out.println("------------------------------");

        ArrayList<VentaFiadaDTO> listaVentaFiadaCliente = this.ventaFiadaDao.listarTodosPorAliasCliente("Cj");
        for (Integer i = 0;  i< listaVentaFiadaCliente.size(); i++) {
            imprimeVentaFiada(listaVentaFiadaCliente.get(i));
        }
    }

}
