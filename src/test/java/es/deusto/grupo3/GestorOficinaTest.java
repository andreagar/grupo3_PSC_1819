package es.deusto.grupo3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorOficina;
import es.deusto.grupo3.LNegocio.Oficina;

public class GestorOficinaTest {

	GestorOficina gestor;
	Oficina oficina;
	Oficina oficina2;
	Oficina oficina3;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		gestor = new GestorOficina();
		oficina = new Oficina (1, "HyraCar01", "Donostia", "España");
		oficina2 = new Oficina (2, "HyraCar02", "Bilbao", "España");
		oficina3 = new Oficina (3, "HyraCar03", "Londres", "Inglaterra");
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
	public void testanyadirFilaATablaOficina() {
		boolean comprobacion1 = gestor.anyadirFilaATablaOficina(BaseDeDatos.getStatement(), oficina);
		boolean comprobacion2 = gestor.anyadirFilaATablaOficina(BaseDeDatos.getStatement(), oficina2);
		boolean comprobacion3 = gestor.anyadirFilaATablaOficina(BaseDeDatos.getStatement(), oficina3);
		boolean comprobacion = false;
		if(comprobacion1==true && comprobacion2==true && comprobacion3==true){
			comprobacion = true;
		}
		assertTrue(comprobacion);
	}
	
	@Test
	public void testchequearYaEnTabla() {
		boolean comprobacion1 = true;
		boolean comprobacion2 = false;
		comprobacion2 = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), oficina.getId());
		assertSame(comprobacion1, comprobacion2);
	}
	
	
	@Test
	public void testGetArrayOficinas() {
		ArrayList<Oficina> arrayOficina = new ArrayList<Oficina>();
		arrayOficina = gestor.GetArrayOficinas(BaseDeDatos.getStatement());
		
		int tamañoArray = arrayOficina.size();
		
		String nombre1 = arrayOficina.get(tamañoArray-3).getNombre();
		String ciudad2 = arrayOficina.get(tamañoArray-2).getCiudad();
		int id3 = arrayOficina.get(tamañoArray-1).getId();
		
		assertEquals(nombre1, "HyraCar01");
		assertEquals(ciudad2, "Bilbao");
		assertSame(id3, 3);
	}
	
	@Test
	public void testeliminarOficina() {
		boolean comprobacion1 = gestor.eliminarOficina(BaseDeDatos.getStatement(), oficina);
		boolean comprobacion2 = gestor.eliminarOficina(BaseDeDatos.getStatement(), oficina2);
		boolean comprobacion3 = gestor.eliminarOficina(BaseDeDatos.getStatement(), oficina3);
		
		assertTrue(comprobacion1);
		assertTrue(comprobacion2);
		assertTrue(comprobacion3);
	}

}
