//package pe.edu.pucp.softbod.dao;
//
//import pe.edu.pucp.softbod.dao.ventas.VentaAlFiadoDAO;
//import pe.edu.pucp.softbod.dao.ventas.VentaDAO;
//import pe.edu.pucp.softbod.model.rrhh.UsuarioDTO;
//import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;
//import pe.edu.pucp.softbod.model.ventas.VentaAlFiadoDTO;
//import pe.edu.pucp.softbod.model.ventas.VentaDTO;
//import java.sql.Date;
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//import pe.edu.pucp.softbod.daoImp.ventas.VentaAlFiadoDAOImpl;
//import pe.edu.pucp.softbod.daoImp.ventas.VentaDAOImpl;
//import pe.edu.pucp.softbod.model.util.Tipo_de_pago;
//
//public class VentaAlFiadoDAOTest {
//    private final VentaAlFiadoDAO ventaFiadaDao;
//    private final VentaDAO ventaDao;
//    
//    public VentaAlFiadoDAOTest() {
//        this.ventaFiadaDao = new VentaAlFiadoDAOImpl();
//        this.ventaDao = new VentaDAOImpl();
//    }
//    
//    private void imprimeVentaFiada(VentaAlFiadoDTO ventaFiada) {
//        System.out.println("=== VENTA FIADA ===");
//        System.out.println("ID Venta Fiada: " + ventaFiada.getVentaFiadaId());
//        // Información de la Venta
//        System.out.println("\n--- DATOS DE LA VENTA ---");
//        System.out.println("Venta ID: " + ventaFiada.getVenta().getVentaId());
//        System.out.println("Total: " + ventaFiada.getVenta().getTotal());
//        System.out.println("Metodo Pago: " + ventaFiada.getVenta().getMetodoPago());
//        System.out.println("Fecha: " + ventaFiada.getVenta().getFecha());
//
//    }
//
////    @Test
//    public void testInsertar() {
//        System.out.println("insertar-VentaFiadaDAOTest");
//        System.out.println("---------------------");
//
//        UsuarioDTO usuario = new UsuarioDTO();
//        usuario.setUsuarioId(308); 
//
//        VentaDTO venta = new VentaDTO();
//        venta.setTotal(120.50);
//        venta.setMetodoPago(Tipo_de_pago.EFECTIVO);
//        venta.setFecha(new Date(19,8,12));
//        venta.setUsuario(usuario);
//        Integer idVenta = this.ventaDao.insertar(venta);
//        venta.setVentaId(idVenta);
//        
//        ClienteAlFiadoDTO cliAlFiado = new ClienteAlFiadoDTO();
//        cliAlFiado.setClienteId(265);
//        
//        VentaAlFiadoDTO ventaFiada = new VentaAlFiadoDTO();
//        ventaFiada.setVenta(venta);
//        ventaFiada.setCliente(cliAlFiado);
//        
//        Integer resultado = this.ventaFiadaDao.insertar(ventaFiada);
//        
//        assert(resultado!=0);
//    }
//
//   
////    @Test   
//    public void testObtenerPorId() {
//        System.out.println("obtenerPorId-VentaFiadaDAOTest");
//        System.out.println("------------------------------");
//
//        VentaAlFiadoDTO ventaFiada = this.ventaFiadaDao.obtenerPorId(3);
//        assertNotNull(ventaFiada);
//        imprimeVentaFiada(ventaFiada);
//    }
//
////    @Test
//    public void testListarTodos() {
//        System.out.println("listarTodos-VentaFiadaDAOTest");
//        System.out.println("------------------------------");
//
//        ArrayList<VentaAlFiadoDTO> listaVentaFiada = this.ventaFiadaDao.listarTodos();
//        assert(!listaVentaFiada.isEmpty());
//        for (Integer i = 0;  i< listaVentaFiada.size(); i++) {
//            imprimeVentaFiada(listaVentaFiada.get(i));
//        }
//    }
//    
////    @Test
//    public void testListarTodosPorAliasCliente() {
//        System.out.println("listarTodos-VentaFiadaDAOTest");
//        System.out.println("------------------------------");
//
//        ArrayList<VentaAlFiadoDTO> listaVentaFiadaCliente = this.ventaFiadaDao.listarTodosPorAliasCliente("Cj");
//        for (Integer i = 0;  i< listaVentaFiadaCliente.size(); i++) {
//            imprimeVentaFiada(listaVentaFiadaCliente.get(i));
//        }
//    }
//
//}
