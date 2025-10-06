package pe.edu.pucp.softbod.dao;

import java.sql.Date;
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

        // Información del Usuario que registró la venta
        System.out.println("\n--- USUARIO QUE REGISTRO LA VENTA ---");
        System.out.println("Usuario ID: " + ventaFiada.getVenta().getUsuario().getUsuarioId());
        System.out.println("Usuario: " + ventaFiada.getVenta().getUsuario().getUsuario());
        System.out.println("Nombre: " + ventaFiada.getVenta().getUsuario().getNombre());
        System.out.println("Tipo: " + ventaFiada.getVenta().getUsuario().getTipoUsuarios());
        System.out.println("Correo: " + ventaFiada.getVenta().getUsuario().getCorreo());
        System.out.println("Telefono: " + ventaFiada.getVenta().getUsuario().getTelefono());
        System.out.println("Activo: " + ventaFiada.getVenta().getUsuario().getActivo());

        // Información del Cliente
        System.out.println("\n--- DATOS DEL CLIENTE ---");
        System.out.println("Cliente ID: " + ventaFiada.getCliente().getClienteId());
        System.out.println("Alias: " + ventaFiada.getCliente().getAlias());
        System.out.println("Nombre: " + ventaFiada.getCliente().getNombre());
        System.out.println("Telefono: " + ventaFiada.getCliente().getTelefono());
        System.out.println("Fecha de Pago: " + ventaFiada.getCliente().getFechaDePago());
        System.out.println("Activo: " + ventaFiada.getCliente().getActivo());
    }

    //@Test
    public void testInsertar() {
        System.out.println("insertar-VentaFiadaDAOTest");
        System.out.println("---------------------");

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioId(308); 

        VentaDTO venta = new VentaDTO();
        venta.setTotal(200.50);
        venta.setMetodoPago(Tipo_de_pago.EFECTIVO);
        venta.setFecha(new Date(19,8,12));
        venta.setUsuario(usuario);
        Integer idVenta = this.ventaDao.insertar(venta);
        venta.setVentaId(idVenta);
        
        ClienteAlFiadoDTO cliAlFiado = new ClienteAlFiadoDTO();
        cliAlFiado.setClienteId(264);
        
        VentaFiadaDTO ventaFiada = new VentaFiadaDTO();
        ventaFiada.setVenta(venta);
        ventaFiada.setCliente(cliAlFiado);
        
        Integer resultado = this.ventaFiadaDao.insertar(ventaFiada);
        
        assert(resultado!=0);
    }

   
    //@Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId-VentaFiadaDAOTest");
        System.out.println("--------------------------");

//        VentaDTO venta = this.ventaDAO.obtenerPorId(1);
//        assertNotNull(venta);
//        imprimeVenta(venta);
    }

    @Test
    public void testListarTodos() {
        
    }

}
