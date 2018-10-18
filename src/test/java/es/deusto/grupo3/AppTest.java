package es.deusto.grupo3;

import static org.junit.Assert.assertTrue;

import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorUsuario;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    @Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
	}
    
    @Test
	public void testchequearTablaCoche() {
    	GestorCoche gestor = new GestorCoche("Audi", "A7", "1234ABC");
    	boolean prueba = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), "1234ABC");
    	assertTrue(prueba);		
	}
    
    @Test
   	public void testchequearTablaUsuario() {
    	GestorUsuario gestor = new GestorUsuario("andrea","andrea");
    	boolean prueba = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), "andrea");
       	assertTrue(prueba);		
   	}
}
