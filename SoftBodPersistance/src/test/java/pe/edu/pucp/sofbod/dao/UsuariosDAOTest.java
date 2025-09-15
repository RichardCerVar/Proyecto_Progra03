/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.sofbod.dao;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Cervera Vargas
 */
public class UsuariosDAOTest {
    
    public UsuariosDAOTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of insertar method, of class UsuariosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testInsertar() {
        System.out.println("insertar");
        Object usuario = null;
        UsuariosDAO instance = new UsuariosDAOImpl();
        Integer expResult = null;
        Integer result = instance.insertar(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPorId method, of class UsuariosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        Integer usuarioId = null;
        UsuariosDAO instance = new UsuariosDAOImpl();
        Object expResult = null;
        Object result = instance.obtenerPorId(usuarioId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarTodos method, of class UsuariosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testListarTodos() {
        System.out.println("listarTodos");
        UsuariosDAO instance = new UsuariosDAOImpl();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.listarTodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificar method, of class UsuariosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testModificar() {
        System.out.println("modificar");
        Object usuario = null;
        UsuariosDAO instance = new UsuariosDAOImpl();
        Integer expResult = null;
        Integer result = instance.modificar(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class UsuariosDAO.
     */
    @org.junit.jupiter.api.Test
    public void testEliminar() {
        System.out.println("eliminar");
        Object usuario = null;
        UsuariosDAO instance = new UsuariosDAOImpl();
        Integer expResult = null;
        Integer result = instance.eliminar(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class UsuariosDAOImpl implements UsuariosDAO {

        public Integer insertar(Object usuario) {
            return null;
        }

        public Object obtenerPorId(Integer usuarioId) {
            return null;
        }

        public ArrayList<Object> listarTodos() {
            return null;
        }

        public Integer modificar(Object usuario) {
            return null;
        }

        public Integer eliminar(Object usuario) {
            return null;
        }
    }
    
}
