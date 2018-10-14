package es.deusto.grupo3;

import static org.junit.Assert.*;

import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorCoche;

/**
 * Test para HyreCar
 */
public class AppTest 
{
    
    @Test
    public void testInsertMismoCoche()
    {
    	GestorCoche coche = new GestorCoche("Audi", "A7", "1234ABC");
		assertEquals(false, coche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), "1234ABC"));
    	;
    }
}