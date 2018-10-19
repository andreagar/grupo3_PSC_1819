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
    
   
}
