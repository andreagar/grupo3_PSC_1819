package es.deusto.grupo3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;

public class GestorCocheTest {

	GestorCoche gestor;
	Coche coche;
	Asignaciones asig;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		coche = new Coche("Audi", "A7", "1234ABC", 74000, false, false, false, "/es/deusto/grupo3/img/Audi_A7.jpg");
		gestor = new GestorCoche();
		asig = new Asignaciones ("Ainhoa", "1234ABC", true, false, false, 1);
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
	public void testchequearTablaCoche() {
    	boolean prueba = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), coche.getMatricula());
    	assertTrue(prueba);		
	}
    
	@Test
    public void testanyadirFilaATablaCoche(){
    	boolean prueba = gestor.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), coche);
    	assertFalse(prueba);
    }
	
	@Test
    public void testGetArrayCocheGlobal(){
		ArrayList<Coche> cocheArray = new ArrayList<Coche>();
		cocheArray = gestor.GetArrayCocheGlobal(BaseDeDatos.getStatement());
		boolean comprobacion = false;
		
		for (int i=0; i<cocheArray.size(); i++){
			if (cocheArray.get(i).getMatricula().equals(coche.getMatricula())){
				comprobacion = true;
			}
		}
		
		assertTrue(comprobacion);
    }
	
	@Test
    public void testGetArrayCochesDisponibles(){
		ArrayList<Coche> cocheArray = new ArrayList<Coche>();
		cocheArray = gestor.GetArrayCochesDisponibles(BaseDeDatos.getStatement());
		boolean comprobacion = false;
		
		assertNotSame(cocheArray.get(1), comprobacion);
    }
	
	@Test
	public void AlquilarVehiculoUsuario(){
		boolean prueba = gestor.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig);
		assertTrue(prueba);	
	}
	
	@Test
	public void getUsuarioHistorial(){
		ArrayList<Asignaciones> asigArray = new ArrayList<Asignaciones>();
		gestor.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig);
		asigArray = gestor.getUsuarioHistorial(BaseDeDatos.getStatement(), "Ainhoa");
		boolean comprobacion = true;
		
		assertSame(asigArray.get(1).getAlquilado(), comprobacion);
	}

}
