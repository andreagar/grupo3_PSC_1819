package es.deusto.grupo3;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorUsuario;
import es.deusto.grupo3.LPresentacion.vistaPrincipal;

/**
 * Main de nuestro proyecto donde se incializa la BD y se crean coches además de visualizar la pantalla principal
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//inicialización de BD
        BaseDeDatos.initBD("nuestroBD.db");
		BaseDeDatos.crearTablaBDUsuario();
		BaseDeDatos.crearTablaBDCoche();
		
		/*
		 * 
		 * Insertamos los coches predeterminados para poder trabajar con ellos
		 */
		GestorCoche coche;
		
		coche = new GestorCoche("Audi", "A7", "1234ABC");
		coche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), "1234ABC");
		
		coche = new GestorCoche("Audi", "Q3", "9999BBB");
		coche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), "9999BBB");
		
		coche = new GestorCoche("Ford", "Kuga", "5678HHH");
		coche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), "5678HHH");

		//menu de log in y registrarse
		vistaPrincipal frame = new vistaPrincipal();
		frame.setVisible(true);
    }
}
