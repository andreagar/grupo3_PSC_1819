package es.deusto.grupo3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorCoche;

public class GestorCocheTest {

	GestorCoche gestor;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		gestor = new GestorCoche("Audi", "A7", "1234ABC");
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
	public void testchequearTablaCoche() {
    	boolean prueba = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), "1234ABC");
    	assertTrue(prueba);		
	}
    
	@Test
    public void testanyadirFilaATablaCoche(){
    	boolean prueba = gestor.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), "1234ABC");
    	assertFalse(prueba);
    }

}
