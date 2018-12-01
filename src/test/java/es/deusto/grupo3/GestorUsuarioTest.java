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
		gestor = new GestorUsuario();
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
       	
       	boolean prueba1 = gestor.anyadirFilaATablauUsuario(BaseDeDatos.getStatement(), "Simon", "xxx");
    	assertFalse(prueba1);
    	
    	boolean prueba11 = gestor.cambiarContrasenya(BaseDeDatos.getStatement(), "Simon", "contrasenyaNueva");
    	assertTrue(prueba11);
    	
    	boolean prueba111;
    	int i;
    	for(i=0; i<1000; i++){
    		String aux  = Integer.toString(i);
	    	prueba111 = gestor.chequearYaEnTablaLOGIN(BaseDeDatos.getStatement(), aux, aux);
	    	assertFalse(prueba111);
    	}
    	    	
   	}
        
}
