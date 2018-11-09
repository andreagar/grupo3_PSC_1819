package es.deusto.grupo3;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;

public class BaseDeDatosTest {

	private final static Logger log = Logger.getLogger(App.class.getName());
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
	}
	
	@Test
	public void testinitBD(){
		log.info(BaseDeDatos.initBD("nuestroBD.db"));
	}
	
	@Test
	public void testclose(){
		log.removeAllAppenders();		
	}

	@Test
	public void testcrearTablaBDUsuario(){
		BaseDeDatos.crearTablaBDCoche();
		assertNotNull(BaseDeDatos.getStatement());
	}
	@Test
	public void testcrearTablaBDCoche(){
		assertNotNull(BaseDeDatos.getStatement());
	}
	@Test
	public void testcrearTablaBDMoto(){
		assertNotNull(BaseDeDatos.getStatement());
	}
	@Test
	public void crearTablaBDAsignaciones(){
		assertNotNull(BaseDeDatos.getStatement());
	}
}
