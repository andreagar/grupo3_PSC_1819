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
import es.deusto.grupo3.LNegocio.GestorMoto;
import es.deusto.grupo3.LNegocio.Moto;

public class GestorMotoTest {

	GestorMoto gestor;
	Moto moto;
	Asignaciones asig;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		moto = new Moto("Yamaha", "XV950R", "1212ABA", 36200, false, false, false, "/es/deusto/grupo3/img/Yamaha-xv950r.jpg");
		gestor = new GestorMoto();
		asig = new Asignaciones ("Ainhoa", "1234ABC", true, false, false, 1);
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

	@Test
    public void testGetArrayMotoGlobal(){
		ArrayList<Moto> motoArray = new ArrayList<Moto>();
		motoArray = gestor.GetArrayMotoGlobal(BaseDeDatos.getStatement());
		boolean comprobacion = false;
		
		for (int i=0; i<motoArray.size(); i++){
			if (motoArray.get(i).getMatricula().equals(moto.getMatricula())){
				comprobacion = true;
			}
		}
		
		assertTrue(comprobacion);
    }
	

	@Test
    public void testGetArrayMotosDisponibles(){
		ArrayList<Moto> motoArray = new ArrayList<Moto>();
		motoArray = gestor.GetArrayMotosDisponibles(BaseDeDatos.getStatement());
		boolean comprobacion = false;
		
		if(motoArray.get(0).isAlquilado()==false && 
				motoArray.get(0).isAveriado()==false && 
						motoArray.get(0).isComprado()==false){
			comprobacion = true;
		}
		
		assertTrue(comprobacion);
    }
	
	@Test
    public void testGetArraySinComprar(){
		ArrayList<Moto> motoArray = new ArrayList<Moto>();
		motoArray = gestor.GetArrayMotosSinComprar(BaseDeDatos.getStatement());
		boolean comprobacion = true;
		
		for (int i=0; i<motoArray.size(); i++){
			if(motoArray.get(i).isComprado()==true){
				comprobacion = false;
			}
		}
		
		assertTrue(comprobacion);
    }
	
	@Test
	public void testAlquilarVehiculoUsuario(){
		boolean prueba = gestor.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig);
		assertTrue(prueba);	
	}
	
	@Test
	public void testComprarVehiculoUsuario(){
		boolean prueba = gestor.ComprarVehiculoUsuario(BaseDeDatos.getStatement(), asig);
		assertTrue(prueba);	
	}
	
	@Test
    public void testModificarDatos(){
		boolean prueba = gestor.modificarDatos(BaseDeDatos.getStatement(), "1212ABA", 36200, "/es/deusto/grupo3/img/Yamaha-xv950r.jpg", true, false, false);
		assertTrue(prueba);
    }

}
