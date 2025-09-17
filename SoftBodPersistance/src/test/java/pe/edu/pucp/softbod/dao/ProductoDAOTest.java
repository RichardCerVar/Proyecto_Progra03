/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.softbod.dao;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softbod.model.ProductoDTO;

/**
 *
 * @author Cervera Vargas
 */
public class ProductoDAOTest {
    
    public ProductoDAOTest() {
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
     * Test of insertar method, of class ProductoDAO.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        ProductoDTO producto = null;
        ProductoDAO instance = new ProductoDAOImpl();
        Integer expResult = null;
        Integer result = instance.insertar(producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPorId method, of class ProductoDAO.
     */
    @Test
    public void testObtenerPorId() {
        System.out.println("obtenerPorId");
        Integer productoId = null;
        ProductoDAO instance = new ProductoDAOImpl();
        ProductoDTO expResult = null;
        ProductoDTO result = instance.obtenerPorId(productoId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarTodos method, of class ProductoDAO.
     */
    @Test
    public void testListarTodos() {
        System.out.println("listarTodos");
        ProductoDAO instance = new ProductoDAOImpl();
        ArrayList<ProductoDTO> expResult = null;
        ArrayList<ProductoDTO> result = instance.listarTodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificar method, of class ProductoDAO.
     */
    @Test
    public void testModificar() {
        System.out.println("modificar");
        ProductoDTO producto = null;
        ProductoDAO instance = new ProductoDAOImpl();
        Integer expResult = null;
        Integer result = instance.modificar(producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class ProductoDAO.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        ProductoDTO producto = null;
        ProductoDAO instance = new ProductoDAOImpl();
        Integer expResult = null;
        Integer result = instance.eliminar(producto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ProductoDAOImpl implements ProductoDAO {

        public Integer insertar(ProductoDTO producto) {
            return null;
        }

        public ProductoDTO obtenerPorId(Integer productoId) {
            return null;
        }

        public ArrayList<ProductoDTO> listarTodos() {
            return null;
        }

        public Integer modificar(ProductoDTO producto) {
            return null;
        }

        public Integer eliminar(ProductoDTO producto) {
            return null;
        }
    }
    
}
