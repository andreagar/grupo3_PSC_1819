package es.deusto.grupo3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorAsignaciones;
import es.deusto.grupo3.LNegocio.GestorCoche;

public class GestorAsignacionesTest {

	GestorAsignaciones gestor;
	GestorCoche gestorCoche;
	Asignaciones asig;
	Coche coche;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		gestor = new GestorAsignaciones();
		gestorCoche = new GestorCoche();
		coche = new Coche("Audi", "A7", "1234ABC", 74000, false, false, false, "/es/deusto/grupo3/img/Audi_A7.jpg");
		asig = new Asignaciones ("Ainhoa", "1234ABC", true, false, false, 1);
		
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
	public void getUsuarioHistorial(){
		ArrayList<Asignaciones> asigArray = new ArrayList<Asignaciones>();
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig);
		asigArray = gestor.getUsuarioHistorial(BaseDeDatos.getStatement(), "Ainhoa");
		int comprobacion = 0;
		
		assertNotSame(asigArray.size(), comprobacion);
	}

	@Test
	public void GetArrayUsuarioAlquilados(){
		ArrayList<Asignaciones> asigArray = new ArrayList<Asignaciones>();
		asigArray = gestor.GetArrayUsuarioAlquilados(BaseDeDatos.getStatement(), "Ainhoa");
		boolean comprobacion = true;
		
		assertSame(asigArray.get(0).getAlquilado(), comprobacion);
	}
	
	@Test
	public void CancelarAlquiler(){
	
		//CONTAR ASIGNACIONES
		Asignaciones asigCambio = new Asignaciones ("Ainhoa", "9999BBB", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asigCambio);
		ArrayList<Asignaciones> asignaciones1 = new ArrayList<Asignaciones>();
		asignaciones1 = gestor.GetArrayUsuarioAlquilados(BaseDeDatos.getStatement(), "Ainhoa");
		
		//CONTAR ASIGNACIONES DESPUÉS DE BORRAR UNA ASIGNACIÓN
		gestor.CancelarAlquiler(BaseDeDatos.getStatement(), "9999BBB", "Ainhoa", "Coche");
		ArrayList<Asignaciones> asignaciones2 = new ArrayList<Asignaciones>();
		asignaciones2 = gestor.GetArrayUsuarioAlquilados(BaseDeDatos.getStatement(), "Ainhoa");
		
		//COMPARAR TAMAÑOS DE LOS ARRAYS
		assertSame((asignaciones1.size()-1), asignaciones2.size());
	}
}
