//package pe.edu.pucp.softbod.dao;
//
//import java.util.ArrayList;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import pe.edu.pucp.softbod.dao.UsuariosDAO;
//import pe.edu.pucp.softbod.daoImp.UsuariosDAOImpl;
//import pe.edu.pucp.softbod.model.Tipo_Usuario;
//import pe.edu.pucp.softbod.model.UsuarioDTO;
//
//public class UsuariosDAOTest {
//    
//    private UsuariosDAO usuarioDAO;
//    
//    public UsuariosDAOTest() {
//        this.usuarioDAO = new UsuariosDAOImpl(); 
//    }
//    
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar");
//        ArrayList<Integer> listaUsuarioId = new ArrayList<>();
//        insertarUsuarios(listaUsuarioId );
//        eliminarTodo();
//    }
//    
//    private void insertarUsuarios(ArrayList<Integer> listaUsuarioId ) {
//        UsuarioDTO usuario = new UsuarioDTO();
//        
//        usuario.setUsuario("cachin246");
//        usuario.setTipo_usuario(Tipo_Usuario.OPERARIO);
//        usuario.setNombre("Carlos Alcantara");
//        usuario.setCorreo("carlos.24@hotmail.com");
//        usuario.setContrasenha("Cachin24");
//        usuario.setTelefono("999888777");
//        Integer resultado = this.usuarioDAO.insertar(usuario);
//        assertTrue(resultado != 0);
//        listaUsuarioId.add(resultado);
//    
//        usuario.setUsuario("ricardo126");
//        usuario.setTipo_usuario(Tipo_Usuario.ADMINISTRADOR);
//        usuario.setNombre("Ricardo Moran");
//        usuario.setCorreo("ricardo.12@hotmail.com");
//        usuario.setContrasenha("ricardo12");
//        usuario.setTelefono("666555444");
//        resultado = this.usuarioDAO.insertar(usuario);
//        assertTrue(resultado != 0);
//        listaUsuarioId.add(resultado);   
//   
//    }
//    
//    @Test
//    public void testObtenerPorId() {
//        System.out.println("obtenerPorId");
//        ArrayList<Integer> listaUsuarioId = new ArrayList<>();
//        insertarUsuarios(listaUsuarioId);
//        
//        UsuarioDTO usuario = this.usuarioDAO.obtenerPorId(listaUsuarioId.get(0));
//        assertEquals(usuario.getUsuario_id(), listaUsuarioId.get(0));
//        
//        usuario = this.usuarioDAO.obtenerPorId(listaUsuarioId.get(1));
//        assertEquals(usuario.getUsuario_id(), listaUsuarioId.get(1));
//        
//        eliminarTodo();
//    }
//    
//    @Test
//    public void testListarTodos() {
//        System.out.println("listarTodos");
//        ArrayList<Integer> listaUsuarioId = new ArrayList<>();
//        insertarUsuarios(listaUsuarioId );
//        
//        ArrayList<UsuarioDTO> listaUsuarios = this.usuarioDAO.listarTodos();
//        assertEquals(listaUsuarioId .size(), listaUsuarios.size());
//        for (Integer i = 0; i < listaUsuarioId.size(); i++) {
//            assertEquals(listaUsuarioId.get(i), listaUsuarios.get(i).getUsuario_id());
//        }
//        eliminarTodo();
//    }
//    
//    @Test
//    public void testModificar() {
//        System.out.println("modificar");
//        ArrayList<Integer> listaUsuarioId = new ArrayList<>();
//        insertarUsuarios(listaUsuarioId );
//        
//        ArrayList<UsuarioDTO> listaUsuarios = this.usuarioDAO.listarTodos();
//        assertEquals(listaUsuarioId.size(), listaUsuarios.size());
//        for (Integer i = 0; i < listaUsuarioId .size(); i++) {
//            listaUsuarios.get(i).setNombre("NuevoNombre" + i.toString());
//            listaUsuarios.get(i).setCorreo("NuevoCorreo" + i.toString());
//            this.usuarioDAO.modificar(listaUsuarios.get(i));
//        }
//        
//        ArrayList<UsuarioDTO> listaUsuariosModificados = this.usuarioDAO.listarTodos();
//        assertEquals( listaUsuarios.size(), listaUsuariosModificados.size());
//        for (Integer i = 0; i < listaUsuarios.size(); i++) {
//            assertEquals(listaUsuarios.get(i).getNombre(), listaUsuariosModificados.get(i).getNombre());
//            assertEquals(listaUsuarios.get(i).getCorreo(), listaUsuariosModificados.get(i).getCorreo());
//        }
//        
//        eliminarTodo();
//    }
//    
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        ArrayList<Integer> listaUsuarioId = new ArrayList<>();
//        insertarUsuarios(listaUsuarioId );
//        eliminarTodo();
//    }
//    
//    private void eliminarTodo(){
//        
//        ArrayList<UsuarioDTO> listaUsuarios = this.usuarioDAO.listarTodos();
//        for (Integer i = 0; i < listaUsuarios.size(); i++) {
//            Integer resultado = this.usuarioDAO.eliminar(listaUsuarios.get(i));
//            assertNotEquals(0, resultado);
//            UsuarioDTO usuario = this.usuarioDAO.obtenerPorId(listaUsuarios.get(i).getUsuario_id());
//            assertNull(usuario);
//        }
//    }
//    
//}
