package pe.edu.pucp.softbod.dao;

import java.sql.Array;
import java.util.ArrayList;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softbod.daoImp.ClienteAlFiadoDAOImp;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public class ClienteAlFiadoDAOTest { 
    /*Si bien El DTO y la Base de datos tienen id, se usa como ID para la busqueda por id 
        el Alias. este emula una pk pues es unica en la respectiva tabla*/
    
    private ClienteAlFiadoDAO clienteAlFiadoDAO;
    private ArrayList<String> listaClientesAlFiadoId;
    
    public ClienteAlFiadoDAOTest() {
        this.clienteAlFiadoDAO = new ClienteAlFiadoDAOImp();
        this.listaClientesAlFiadoId = new ArrayList<>();
    }
    
    @Test
    public void testInsertar() {
        System.out.println("----insertar-Cliente-Al-Fiado----");
        insertarClientesAlFiado(this.listaClientesAlFiadoId);
        eliminarTodo();
    }
    
    private void insertarClientesAlFiado(ArrayList<String> listaClientesAlFiadoId){
        
        ClienteAlFiadoDTO clienteAlFiado = new ClienteAlFiadoDTO();
        for (Integer i = 0; i < 3; i++) {
            clienteAlFiado.setAlias("Chino"+i.toString());
            clienteAlFiado.setNombre("Alexis"+i.toString());
            clienteAlFiado.setTelefono("987654"+i.toString());
            clienteAlFiado.setFecha_de_pago(new Date(System.currentTimeMillis()));
            Integer resultado = this.clienteAlFiadoDAO.insertar(clienteAlFiado);
            assert(resultado!=0);
            
            listaClientesAlFiadoId.add(clienteAlFiado.getAlias());
            System.out.println("Insertado Cliente :" + clienteAlFiado.getAlias());
        }
    }

    @Test
    public void testListarTodos() {
        System.out.println("----listarTodos-Cliente-Al-Fiado----");
        
        insertarClientesAlFiado(this.listaClientesAlFiadoId);
        
        ArrayList<ClienteAlFiadoDTO> listaClientesAlFiado = this.clienteAlFiadoDAO.listarTodos();
        
        for (Integer i = 0; i <listaClientesAlFiado.size(); i++) {
            System.out.println("Cliente: "+listaClientesAlFiado.get(i).getAlias());
        }
        eliminarTodo();
    }
    
    @Test
    public void testObtenerPorId() {
        System.out.println("----obtenerPorId-Cliente-Al-Fiado----");
        insertarClientesAlFiado(this.listaClientesAlFiadoId);
        
        ClienteAlFiadoDTO clienteAlFiado = this.clienteAlFiadoDAO.obtenerPorId(this.listaClientesAlFiadoId.get(0));
        assertEquals(clienteAlFiado.getAlias(), this.listaClientesAlFiadoId.get(0));
        
        clienteAlFiado = this.clienteAlFiadoDAO.obtenerPorId(this.listaClientesAlFiadoId.get(1));
        assertEquals(clienteAlFiado.getAlias(), this.listaClientesAlFiadoId.get(1));
        
        clienteAlFiado = this.clienteAlFiadoDAO.obtenerPorId(this.listaClientesAlFiadoId.get(2));
        assertEquals(clienteAlFiado.getAlias(), this.listaClientesAlFiadoId.get(2));
        eliminarTodo();
    }

//
    @Test
    public void testModificar() {
        System.out.println("----modificar-Cliente-Al-Fiado----");
        //listar Id clientes
        insertarClientesAlFiado(this.listaClientesAlFiadoId);
        //listar clientes
        ArrayList<ClienteAlFiadoDTO> listaClientesAlFiado = this.clienteAlFiadoDAO.listarTodos();
        //MODIFICA 1
        listaClientesAlFiado.get(2).setAlias("Chinito");
        this.clienteAlFiadoDAO.modificar(listaClientesAlFiado.get(2));
        //MODIFICA 2
        listaClientesAlFiado.get(3).setAlias("juan");
        this.clienteAlFiadoDAO.modificar(listaClientesAlFiado.get(3));
        //MODIFICA 3
        listaClientesAlFiado.get(4).setAlias("chamito");
        this.clienteAlFiadoDAO.modificar(listaClientesAlFiado.get(4));
        
        ArrayList<ClienteAlFiadoDTO> clientesModificados = this.clienteAlFiadoDAO.listarTodos();
        for (Integer i = 0; i < clientesModificados.size(); i++) {//verificar lo cambiado
            System.out.println("Alias Modificado(o No): " + clientesModificados.get(i).getAlias());
        }
        eliminarTodo();
    }

//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar-Cliente-Al-Fiado");
//        ArrayList<String> listaClientesAlFiadoId = new ArrayList<>();
//        insertarClientesAlFiado(listaClientesAlFiadoId);
//        
//        eliminarTodo();
//    }

    private void eliminarTodo() {//eliminarLoinsertado
        ArrayList<ClienteAlFiadoDTO> listaClientesAlFiado = this.clienteAlFiadoDAO.listarTodos();
        for (Integer i = 2; i < listaClientesAlFiado.size(); i++) {
            System.out.println("Se elimino: "+ listaClientesAlFiado.get(i).getAlias());
            Integer resultado = this.clienteAlFiadoDAO.eliminar(listaClientesAlFiado.get(i));
            assert(resultado!=0);
            ClienteAlFiadoDTO clienteAlFiado = this.clienteAlFiadoDAO.obtenerPorId(listaClientesAlFiado.get(i).getAlias());
            assertNull(clienteAlFiado);
        }

    }
    
}

