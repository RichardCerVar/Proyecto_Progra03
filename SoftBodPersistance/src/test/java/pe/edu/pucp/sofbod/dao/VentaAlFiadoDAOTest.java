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
public class VentaAlFiadoDAOTest {
    
    public VentaAlFiadoDAOTest() {
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
     * Test of insertar method, of class VentaAlFiadoDAO.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        Object ventaAlFiado = null;
        VentaAlFiadoDAO instance = new VentaAlFiadoDAOImpl();
        Integer expResult = null;
        Integer result = instance.insertar(ventaAlFiado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarTodos method, of class VentaAlFiadoDAO.
     */
    @Test
    public void testListarTodos() {
        System.out.println("listarTodos");
        VentaAlFiadoDAO instance = new VentaAlFiadoDAOImpl();
        ArrayList<Object> expResult = null;
        ArrayList<Object> result = instance.listarTodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class VentaAlFiadoDAOImpl implements VentaAlFiadoDAO {

        public Integer insertar(Object ventaAlFiado) {
            return null;
        }

        public ArrayList<Object> listarTodos() {
            return null;
        }
    }
    
}
