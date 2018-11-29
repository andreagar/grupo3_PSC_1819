package es.deusto.grupo3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Clima;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorClima;
import es.deusto.grupo3.LNegocio.GestorCoche;

public class GestorClimaTest {

	GestorClima gestor;
	Clima clima;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		clima = new Clima("donostia", 15.5, "nublado", 60);
		gestor = new GestorClima();
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
	public void testanyadirFilaATablaClima() {
		boolean prueba = gestor.anyadirFilaATablaClima(BaseDeDatos.getStatement(), clima);
    	assertFalse(prueba);
	}
	
	@Test
	public void testgetClima(){
		Clima c = gestor.getClima(BaseDeDatos.getStatement(), "donostia");
		assertEquals(c.getCiudad(), clima.getCiudad());
		assertEquals(c.getClass(), clima.getClass());
		assertEquals(c.getEstado(), clima.getEstado());
		assertEquals(c.getPrecipitacion(), clima.getPrecipitacion());
		assertEquals(c.getTemperatura(), clima.getTemperatura(), 0);
	}
}
