package pe.edu.pucp.softbod.db.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CifradoTest {
    
    public CifradoTest() {
    }

    @Test
    public void testCifrarMD5() {
        System.out.println("cifrarMD5");
        String texto = "Programacion3.";
        String expResult = "GFvzT/oALwhziJrVcTssng==";
        String result = Cifrado.cifrarMD5(texto);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    @Test
    public void testDescifrarMD5() {
        System.out.println("descifrarMD5");
        String textoEncriptado = "GFvzT/oALwhziJrVcTssng==";
        String expResult = "Programacion3.";
        String result = Cifrado.descifrarMD5(textoEncriptado);
        assertEquals(expResult, result);
    }
    
}
