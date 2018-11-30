package es.deusto.grupo3;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorOpiniones;
import es.deusto.grupo3.LNegocio.Opinion;

public class GestorOpinionesTest {

	GestorOpiniones gestor;
	Opinion opinion;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		gestor = new GestorOpiniones();
		opinion = new Opinion ("Ekaitz", "1121WSA", "4", "Buen coche y comodo");
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
	@Test
	public void testguardarOpinion (){
		boolean comprobacion = gestor.guardarOpinion(BaseDeDatos.getStatement(), opinion);
		assertTrue(comprobacion);
	}
	
	@Test
	public void testZgetOpiniones(){
		ArrayList<Opinion> array = new ArrayList<Opinion>();
		array = gestor.getOpiniones(BaseDeDatos.getStatement());
		boolean comprobacion = false;
		for (int i=0; i<array.size(); i++){
			if (array.get(i).getMatricula().equals("1121WSA") && array.get(i).getUsuario().equals("Ekaitz")){
				comprobacion = true;
			}
		}
		
		assertTrue(comprobacion);
	}
	
	@Test
	public void testZgetUsuarioMatriculas(){
		int size = 0;
		size = gestor.getUsuarioMatriculas(BaseDeDatos.getStatement(), "aaa").size();
		boolean comprobacion = false;
		if (size == 0){
			comprobacion = false;
		}
		if (size > 0){
			comprobacion = true;
		}
		assertTrue(comprobacion);
	}

}
