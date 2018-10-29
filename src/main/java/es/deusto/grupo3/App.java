package es.deusto.grupo3;

import java.sql.SQLException;
import java.sql.Statement;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Coche;
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
		BaseDeDatos.crearTablaBDMoto();
		
		/*
		 * 
		 * Insertamos los coches predeterminados para poder trabajar con ellos
		 */
		GestorCoche gestorCoche  = new GestorCoche();
		Coche coche;
		
		coche = new Coche ("Audi", "A7", "1234ABC", false, false, false);
		System.out.println(coche.toString());
		gestorCoche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), coche);
		
		coche = new Coche ("Audi", "Q3", "9999BBB", false, false, false);
		gestorCoche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), coche);
		
		coche = new Coche ("Ford", "Kuga", "5678HHH", false, false, false);
		gestorCoche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), coche);

		//menu de log in y registrarse
		vistaPrincipal frame = new vistaPrincipal();
		frame.setVisible(true);
    }
}
