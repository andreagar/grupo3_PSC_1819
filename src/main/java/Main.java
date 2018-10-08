import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LPresentacion.vistaPrincipal;

public class Main {

	public static void main(String[] args) {

		BaseDeDatos.initBD("nuestrosDatos.bd");

		//menu de log in y registrarse
		vistaPrincipal frame = new vistaPrincipal();
		frame.setVisible(true);
		

	}
		
		
}