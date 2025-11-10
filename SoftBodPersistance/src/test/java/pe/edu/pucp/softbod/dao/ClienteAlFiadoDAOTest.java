//package pe.edu.pucp.softbod.dao;
//
//import pe.edu.pucp.softbod.dao.gestclientes.ClienteAlFiadoDAO;
//import java.util.ArrayList;
//import java.sql.Date;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import pe.edu.pucp.softbod.daoImp.gestclientes.ClienteAlFiadoDAOImpl;
//import pe.edu.pucp.softbod.model.gestclientes.ClienteAlFiadoDTO;
//
//public class ClienteAlFiadoDAOTest { 
//    
//    private ClienteAlFiadoDAO clienteAlFiadoDAO;
//    
//    public ClienteAlFiadoDAOTest() {
//        this.clienteAlFiadoDAO = new ClienteAlFiadoDAOImpl();
//    }
//    
//    private void imprimeClienteAlFiado(ClienteAlFiadoDTO cliente) {
//        System.out.println("id: " + cliente.getClienteId());
//        System.out.println("Alias: " + cliente.getAlias());
//        System.out.println("Nombre: " + cliente.getNombre());
//        System.out.println("Telefono: " + cliente.getTelefono());
//        System.out.println("Fecha de Pago: " + cliente.getFechaDePago());
//        System.out.println("Activo: " + cliente.getActivo());
//    }
//    
//    //@Test
//    public void testInsertar() {
//        System.out.println("insertar-ClienteAlFiadoDAOTest");
//        System.out.println("------------------------------");
//
//        ClienteAlFiadoDTO cliente = new ClienteAlFiadoDTO();
//        cliente.setAlias("Juanito");
//        cliente.setNombre("Juan Carlos Pérez García");
//        cliente.setTelefono("987654321");
//        cliente.setFechaDePago(new Date(20, 03, 22)); // O usar LocalDate si prefieres
//        cliente.setActivo(Boolean.TRUE);
//
//        Integer resultado = this.clienteAlFiadoDAO.insertar(cliente);
//        assert(resultado!=0);
//    }
//
//    //@Test
//    public void testListarTodos() {
//        System.out.println("listarTodos-ClienteAlFiadoDAOTest");
//        System.out.println("---------------------------------");
//        
//        ArrayList<ClienteAlFiadoDTO> listaClientes = this.clienteAlFiadoDAO.listarTodos();
//        assert(!listaClientes.isEmpty());
//        for (Integer i = 0; i < listaClientes.size(); i++) {
//            imprimeClienteAlFiado(listaClientes.get(i));
//            System.out.println("----------------------------------");
//        }
//    }
//    
//    //@Test
//    public void testObtenerPorId() {
//        System.out.println("obtenerPorId-ClienteAlFiadoDAOTest");
//        System.out.println("-----------------------------------");
//
//        ClienteAlFiadoDTO cliente = this.clienteAlFiadoDAO.obtenerPorId(264);
//        assertNotNull(cliente);
//        imprimeClienteAlFiado(cliente);
//    }
//
//    //@Test
//    public void testModificar() {
//        System.out.println("modificar-ClienteAlFiadoDAOTest");
//        System.out.println("--------------------------------");
//
//        ClienteAlFiadoDTO cliente = this.clienteAlFiadoDAO.obtenerPorId(264);
//        assertNotNull(cliente);
//        System.out.println("CLIENTE AL FIADO Antes: ");
//        imprimeClienteAlFiado(cliente);
//
//        cliente.setAlias("Alexito Pro (Actualizado)");
//        cliente.setTelefono("912345678");
//
//        Integer resultado = this.clienteAlFiadoDAO.modificar(cliente);
//        assert(resultado!=0);
//        
//        System.out.println("CLIENTE AL FIADO Despues: ");
//        imprimeClienteAlFiado(cliente);
//    }
//
//    
//}
//
