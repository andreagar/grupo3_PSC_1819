package es.deusto.grupo3;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorUsuario;
import es.deusto.grupo3.LNegocio.Usuario;

public class GestorUsuarioTest {

	GestorUsuario gestor;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		gestor = new GestorUsuario("andrea","andrea");
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
		gestor = null;
	}
	
    @Test
   	public void testchequearTablaUsuario() {
    	boolean prueba = gestor.chequearYaEnTabla(BaseDeDatos.getStatement(), "andrea");
       	assertTrue(prueba);		
   	}
    
    @Test
    public void testanyadirFilaATablauUsuario(){
    	boolean prueba = gestor.anyadirFilaATablauUsuario(BaseDeDatos.getStatement(), "Simon");
    	assertFalse(prueba);
    }
    
    @Test
    public void testcambiarContrasenya(){
    	boolean prueba = gestor.cambiarContrasenya(BaseDeDatos.getStatement(), "Simon", "contrasenyaNueva");
    	assertTrue(prueba);
    }
    
    @Test
    public void testchequearYaEnTablaLOGIN(){
    	boolean prueba;
    	int i;
    	for(i=0; i<10000; i++){
    		String aux  = Integer.toString(i);
    		System.out.println(aux);
	    	prueba = gestor.chequearYaEnTablaLOGIN(BaseDeDatos.getStatement(), aux, aux);
	    	assertFalse(prueba);
    	}
    }
    
}
