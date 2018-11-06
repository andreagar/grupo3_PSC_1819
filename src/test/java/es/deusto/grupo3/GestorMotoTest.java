package es.deusto.grupo3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorMoto;
import es.deusto.grupo3.LNegocio.Moto;

public class GestorMotoTest {

	GestorMoto gestor;
	Moto moto;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		moto = new Moto("Yamaha", "XV950R", "1212ABA", 36200, false, false, false, "/es/deusto/grupo3/img/Yamaha-xv950r.jpg");
		gestor = new GestorMoto();
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
	public void testchequearTablaCoche() {
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
		
		assertNotSame(motoArray.get(1), comprobacion);
    }
	
	@Test
	public void AlquilarVehiculoUsuario(){
		boolean prueba = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), moto.getMatricula());
    	assertTrue(prueba);
	}

}
