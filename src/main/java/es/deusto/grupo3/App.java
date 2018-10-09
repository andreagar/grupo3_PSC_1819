package es.deusto.grupo3;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LPresentacion.vistaPrincipal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BaseDeDatos.initBD("nuestroBD.db");
		BaseDeDatos.crearTablaBDUsuario();
		BaseDeDatos.crearTablaBDCoche();
		

		//menu de log in y registrarse
		vistaPrincipal frame = new vistaPrincipal();
		frame.setVisible(true);
    }
}
