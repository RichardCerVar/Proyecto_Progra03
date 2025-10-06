package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.softbod.daoImp.UsuarioDAOImpl;
import pe.edu.pucp.softbod.model.UsuarioDTO;
import pe.edu.pucp.softbod.model.util.Tipo_Usuario;

public class UsuariosDAOTest {
    
    private final UsuariosDAO usuarioDAO;
    
    public UsuariosDAOTest() {
        this.usuarioDAO = new UsuarioDAOImpl(); 
    }
    
    //@Test
    public void testInsertar() {
        System.out.println("insertar-UsuariosDAOTest");
        System.out.println("------------------------");
        
        UsuarioDTO user = new UsuarioDTO();
        user.setUsuario("Chichico");
        user.setTipoUsuarios(Tipo_Usuario.OPERARIO);
        user.setActivo(Boolean.TRUE);
        user.setContrasenha("jjdajsdjasd123");
        user.setCorreo("Holacomoestas@outlook.com");
        user.setNombre("Jose Carlos");
        user.setTelefono("987654321");
        
        Integer resultado = this.usuarioDAO.insertar(user);
        assert(resultado!=0);
    }

    @Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId-UsuariosDAOTest");
        System.out.println("----------------------------");
        
        UsuarioDTO usuario = this.usuarioDAO.obtenerPorId(308);
        assertNotNull(usuario);
        System.out.println("id: " + usuario.getUsuarioId() );
        System.out.println("nombreUsuario: " + usuario.getUsuario() );
        System.out.println("Tipo usuario: " + usuario.getTipoUsuarios());
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Contraseha: " + usuario.getContrasenha());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Telefono: " + usuario.getTelefono());
    }
    
    //@Test
    public void testListarTodos() {
        System.out.println("listarTodos-UsuariosDAOTest");
        System.out.println("--------------------------");
        
        ArrayList<UsuarioDTO> listaUsuarios = this.usuarioDAO.listarTodos();
        assert(!listaUsuarios.isEmpty());
        for (Integer i = 0; i < listaUsuarios.size(); i++) {
            imprimeUser(listaUsuarios.get(i));
            System.out.println("--------------------------");
        }
        
    }
    
    public void imprimeUser(UsuarioDTO usuario){
        System.out.println("id: " + usuario.getUsuarioId() );
        System.out.println("nombreUsuario: " + usuario.getUsuario() );
        System.out.println("Tipo usuario: " + usuario.getTipoUsuarios());
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Contraseha: " + usuario.getContrasenha());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Telefono: " + usuario.getTelefono());
    }
    
    //@Test
    public void testModificar() {
        System.out.println("modificar-UsuariosDAOTest");
        System.out.println("--------------------------");
        
        UsuarioDTO usuario = this.usuarioDAO.obtenerPorId(308);
        assertNotNull(usuario);
        System.out.println("USUARIO Antes: ");
        imprimeUser(usuario);
        
        usuario.setActivo(Boolean.FALSE);
        usuario.setUsuario("CHICHICO REFORMED");
        usuario.setTelefono("955882323");
        
        Integer resultado = this.usuarioDAO.modificar(usuario);
        assert(resultado!=0);
        
        System.out.println("USUARIO Despues: ");
        imprimeUser(usuario);
        
    }
    
}
