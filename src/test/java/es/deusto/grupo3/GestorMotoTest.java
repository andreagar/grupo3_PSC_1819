package es.deusto.grupo3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Moto;
import es.deusto.grupo3.LNegocio.GestorMoto;

public class GestorMotoTest {

	GestorMoto gestor;
	Moto moto;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		moto = new Moto("Harley", "Iron 883", "4545ABA", 10000, false, false, false, "/es/deusto/grupo3/img/Harley_883.jpg");
		gestor = new GestorMoto();
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
	public void testchequearTablaMoto() {
    	boolean prueba = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), moto.getMatricula());
    	assertTrue(prueba);		
	}
    
	@Test
    public void testanyadirFilaATablaMoto(){
    	boolean prueba = gestor.anyadirFilaATablaMoto(BaseDeDatos.getStatement(), moto);
    	assertFalse(prueba);
    }

}
