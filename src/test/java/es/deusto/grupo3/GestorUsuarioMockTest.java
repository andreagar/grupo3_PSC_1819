package es.deusto.grupo3;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorUsuario;
import es.deusto.grupo3.LNegocio.Usuario;

public class GestorUsuarioMockTest {

	@Mock
	GestorUsuario gesUsu;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
    	Mockito.when(gesUsu.bloquearUsuario("grupo3")).thenReturn(true);
	}
		    
    @Test
    public void testbloquearUsuario(){
    	assertTrue(gesUsu.bloquearUsuario("grupo3"));
    	Mockito.verify(gesUsu, Mockito.times(1)).bloquearUsuario("grupo3");
    	Mockito.verify(gesUsu, Mockito.never()).bloquearUsuario("oscar");
    }

}
