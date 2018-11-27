package es.deusto.grupo3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import es.deusto.grupo3.LNegocio.DGTService;
import es.deusto.grupo3.LNegocio.IDGT;

public class DGTMockTest {

	DGTService dgtService;
	
	@Before
	public void setup(){
		IDGT cal = Mockito.mock(IDGT.class);
		Mockito.when(cal.alerta(200, 120)).thenReturn(4);
		Mockito.when(cal.estado()).thenReturn(true);
		Mockito.when(cal.estadoCarreteras("bilbao")).thenReturn("grave");
				
		dgtService = new DGTService();
		dgtService.setCal(cal);
	}
	
	@Test
	public void testAviso(){
		Assert.assertEquals(4, dgtService.aviso(200, 120));
	}
	
	@Test
	public void testEstadoGral(){
		Assert.assertTrue(dgtService.estadoGeneral());
	}
	
	@Test
	public void testEstadoCiudad(){
		Assert.assertEquals("grave", dgtService.estadoCarreterasCiudad("bilbao"));
	}
}
