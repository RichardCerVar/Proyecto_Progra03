package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softbod.daoImp.ClienteAlFiadoDAOImp;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public class ClienteAlFiadoDAOTest { 
    /*Si bien El DTO y la Base de datos tienen id, se usa como ID para la busqueda por id 
        el Alias. este emula una pk pues es unica en la respectiva tabla*/
    
    private ClienteAlFiadoDAO clienteAlFiadoDAO;
    
    public ClienteAlFiadoDAOTest() {
        this.clienteAlFiadoDAO = new ClienteAlFiadoDAOImp();
    }
    
    @Test
    public void testInsertar() {
        System.out.println("insertar-Cliente-Al-Fiado");
        ArrayList<String> listaClientesAlFiadoId = new ArrayList<>();
        insertarClientesAlFiado(listaClientesAlFiadoId);
        eliminarTodo();
    }
    
    private void insertarClientesAlFiado(ArrayList<String> listaClientesAlFiadoId){
        
        ClienteAlFiadoDTO clienteAlFiado = new ClienteAlFiadoDTO();
        clienteAlFiado.setAlias("Chino");
        clienteAlFiado.setNombre("Alexis");
        clienteAlFiado.setTelefono("987654321");
        clienteAlFiado.setFecha_de_pago(new Date(System.currentTimeMillis()));
        Integer resultado = this.clienteAlFiadoDAO.insertar(clienteAlFiado);
        assert(resultado!=0);
        listaClientesAlFiadoId.add(clienteAlFiado.getAlias());
        //
        clienteAlFiado.setAlias("JuancitoPerez");
        clienteAlFiado.setNombre("Juan");
        clienteAlFiado.setTelefono("987654321");
        clienteAlFiado.setFecha_de_pago(Date.valueOf(LocalDate.of(2025, Month.NOVEMBER, 19)));
        resultado = this.clienteAlFiadoDAO.insertar(clienteAlFiado);
        assert(resultado!=0);
        listaClientesAlFiadoId.add(clienteAlFiado.getAlias());
        //
        clienteAlFiado.setAlias("Chamo");
        clienteAlFiado.setNombre("Noriel");
        clienteAlFiado.setTelefono("987654321");
        clienteAlFiado.setFecha_de_pago(Date.valueOf(LocalDate.of(2025, Month.DECEMBER, 12)));
        resultado = this.clienteAlFiadoDAO.insertar(clienteAlFiado);
        assert(resultado!=0);
        listaClientesAlFiadoId.add(clienteAlFiado.getAlias());
        
    }

    @Test
    public void testListarTodos() {
        System.out.println("listarTodos-Cliente-Al-Fiado");
        
        ArrayList<String> listaClientesAlFiadoId = new ArrayList<>();
        insertarClientesAlFiado(listaClientesAlFiadoId);
        
        ArrayList<ClienteAlFiadoDTO> listaClientesAlFiado = this.clienteAlFiadoDAO.listarTodos();
        assertEquals(listaClientesAlFiado.size(),listaClientesAlFiadoId.size());
       
        for (Integer i = 0; i < listaClientesAlFiadoId.size(); i++) {
           assertEquals(listaClientesAlFiadoId.get(i), listaClientesAlFiado.get(i).getAlias()); //<- ES VALIDO, PERO COMO NUESTRA PK ES STRING SE ORDENA DIFERENTE EN EL SQL
        }
        eliminarTodo();
    }
    
    @Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId-Cliente-Al-Fiado");
        ArrayList<String> listaClientesAlfiadoId = new ArrayList<>();
        insertarClientesAlFiado(listaClientesAlfiadoId);
        
        ClienteAlFiadoDTO clienteAlFiado = this.clienteAlFiadoDAO.obtenerPorId(listaClientesAlfiadoId.get(0));
        assertEquals(clienteAlFiado.getAlias(), listaClientesAlfiadoId.get(0));
        
        clienteAlFiado = this.clienteAlFiadoDAO.obtenerPorId(listaClientesAlfiadoId.get(1));
        assertEquals(clienteAlFiado.getAlias(), listaClientesAlfiadoId.get(1));
        
        clienteAlFiado = this.clienteAlFiadoDAO.obtenerPorId(listaClientesAlfiadoId.get(2));
        assertEquals(clienteAlFiado.getAlias(), listaClientesAlfiadoId.get(2));
        eliminarTodo();
    }


    @Test
    public void testModificar() {
        System.out.println("modificar-Cliente-Al-Fiado");
        //listar Id clientes
        ArrayList<String> listaClientesAlFiadoId = new ArrayList<>();
        insertarClientesAlFiado(listaClientesAlFiadoId);
        //listar clientes
        ArrayList<ClienteAlFiadoDTO> listaClientesAlFiado = this.clienteAlFiadoDAO.listarTodos();
        assertEquals(listaClientesAlFiado.size(),listaClientesAlFiadoId.size());
        //MODIFICA 1
        listaClientesAlFiado.get(0).setAlias("Chinito");
        this.clienteAlFiadoDAO.modificar(listaClientesAlFiado.get(0));
        //MODIFICA 2
        listaClientesAlFiado.get(1).setAlias("juan");
        this.clienteAlFiadoDAO.modificar(listaClientesAlFiado.get(1));
        //MODIFICA 3
        listaClientesAlFiado.get(2).setAlias("chamito");
        this.clienteAlFiadoDAO.modificar(listaClientesAlFiado.get(2));
        
        //listar clientes modificados para verificar;
        ArrayList<ClienteAlFiadoDTO> listaClientesAlFiadoModific = this.clienteAlFiadoDAO.listarTodos();
        assertEquals(listaClientesAlFiado.size(),listaClientesAlFiadoModific.size());
        for (Integer i = 0; i < listaClientesAlFiado.size(); i++) {//verificar lo cambiado
            assertEquals(listaClientesAlFiado.get(i).getAlias(),listaClientesAlFiadoModific.get(i).getAlias());
        }
        eliminarTodo();
    }

    @Test
    public void testEliminar() {
        System.out.println("eliminar-Cliente-Al-Fiado");
        ArrayList<String> listaClientesAlFiadoId = new ArrayList<>();
        insertarClientesAlFiado(listaClientesAlFiadoId);
        
        eliminarTodo();
    }

    private void eliminarTodo() {
        ArrayList<ClienteAlFiadoDTO> listaClientesAlFiado = this.clienteAlFiadoDAO.listarTodos();
        for (Integer i = 0; i < listaClientesAlFiado.size(); i++) {
            Integer resultado = this.clienteAlFiadoDAO.eliminar(listaClientesAlFiado.get(i));
            assert(resultado!=0);
            ClienteAlFiadoDTO clienteAlFiado = this.clienteAlFiadoDAO.obtenerPorId(listaClientesAlFiado.get(i).getAlias());
            assertNull(clienteAlFiado);
        }

    }
    
}
