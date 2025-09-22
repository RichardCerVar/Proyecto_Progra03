package pe.edu.pucp.softbod.softbodbusiness;

import java.sql.Date;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softbod.model.ClienteAlFiadoDTO;

public class ClienteAlFiadoBOTest {
    
    private ClienteAlFiadoBO clienteAlFiadoBO;
    
    public ClienteAlFiadoBOTest() {
        this.clienteAlFiadoBO = new ClienteAlFiadoBO();
    }

    //@Test 
    public void testInsertar() {
        System.out.println("insertar - Cliente al fiado");
        int resultado = this.clienteAlFiadoBO.insertar("Cuchito","Jesus Andres Lujan Carrion", 
                                        "987654321",Date.valueOf("2018-12-18"));
        assert(resultado!=0);
        
    }

    //@Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId - Cliente al fiado");
        ClienteAlFiadoDTO clienteAlFiadoDto = this.clienteAlFiadoBO.obtenerPorId("Cuchito");
        assertEquals(clienteAlFiadoDto.getAlias(),"Cuchito");
        System.out.println(clienteAlFiadoDto.getAlias() + " Fue encontrado");
    }

    //@Test
    public void testListarTodos() {
        System.out.println("listarTodos - Cliente al fiado");
        ArrayList<ClienteAlFiadoDTO> listaClientes = this.clienteAlFiadoBO.listarTodos();
        for (Integer i = 0; i < listaClientes.size(); i++) {
            System.out.println("- " + listaClientes.get(i).getAlias());
        }
    }

    //@Test
    public void testModificar() {
        System.out.println("Modificar - Cliente al fiado");
        System.out.println("Modificar Alias de juanito23");
        
        this.clienteAlFiadoBO.modificar("juanito23","Juancito3eroPro");
        ClienteAlFiadoDTO clienteAlFiadoDto = this.clienteAlFiadoBO.obtenerPorId("Juancito3eroPro");
        assertNotNull(clienteAlFiadoDto);
    }

    //@Test
    public void testEliminar() {
        System.out.println("Eliminar - Cliente al fiado");
        System.out.println("-Eliminando al cliente Cuchito");
        this.clienteAlFiadoBO.eliminar("Cuchito");
        ClienteAlFiadoDTO clienteAlFiadoDto = this.clienteAlFiadoBO.obtenerPorId("Cuchito");
        assertNull(clienteAlFiadoDto);
    }

}
