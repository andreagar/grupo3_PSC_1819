package es.deusto.grupo3;

import static org.junit.Assert.*;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> Alquiler2
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
<<<<<<< HEAD
import es.deusto.grupo3.LNegocio.Moto;
import es.deusto.grupo3.LNegocio.GestorMoto;
=======
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorMoto;
import es.deusto.grupo3.LNegocio.Moto;
>>>>>>> Alquiler2

public class GestorMotoTest {

	GestorMoto gestor;
	Moto moto;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
<<<<<<< HEAD
		moto = new Moto("Harley", "Iron 883", "4545ABA", 10000, false, false, false, "/es/deusto/grupo3/img/Harley_883.jpg");
=======
		moto = new Moto("Yamaha", "XV950R", "1212ABA", 36200, false, false, false, "/es/deusto/grupo3/img/Yamaha-xv950r.jpg");
>>>>>>> Alquiler2
		gestor = new GestorMoto();
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
<<<<<<< HEAD
	public void testchequearTablaMoto() {
=======
	public void testchequearTablaCoche() {
>>>>>>> Alquiler2
    	boolean prueba = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), moto.getMatricula());
    	assertTrue(prueba);		
	}
    
	@Test
    public void testanyadirFilaATablaMoto(){
    	boolean prueba = gestor.anyadirFilaATablaMoto(BaseDeDatos.getStatement(), moto);
    	assertFalse(prueba);
    }
<<<<<<< HEAD
=======
	
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
>>>>>>> Alquiler2

}
