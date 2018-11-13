package es.deusto.grupo3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorMoto;
import es.deusto.grupo3.LNegocio.GestorUsuario;
import es.deusto.grupo3.LNegocio.Moto;
import es.deusto.grupo3.LPresentacion.vistaPrincipal;

/**
 * Main de nuestro proyecto donde se incializa la BD y se crean coches además de visualizar la pantalla principal
 *
 */
public class App 
{
	private final static Logger log = Logger.getLogger(App.class.getName());
    public static void main( String[] args )
    {
    	//inicialización de BD
    	BaseDeDatos.initBD("./nuestroBD.db");
    	/*String s = "drop table MOTO";
    	try {
			BaseDeDatos.getStatement().executeUpdate(s);
			System.out.println(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String a = "drop table COCHE";
    	try {
			BaseDeDatos.getStatement().executeUpdate(a);
			System.out.println(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
		BaseDeDatos.crearTablaBDUsuario();
		BaseDeDatos.crearTablaBDCoche();
		BaseDeDatos.crearTablaBDMoto();
		BaseDeDatos.crearTablaBDAsignaciones();
	
		/*
		 * 
		 * Insertamos los coches predeterminados para poder trabajar con ellos
		 */
		GestorCoche gestorCoche  = new GestorCoche();
		Coche coche;
		
		coche = new Coche ("Audi", "A7", "1234ABC", 74000, false, false, false, "/es/deusto/grupo3/img/Audi_A7.jpg");
		gestorCoche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), coche);
		
		coche = new Coche ("Audi", "Q3", "9999BBB", 43000, false, false, false, "/es/deusto/grupo3/img/Audi_Q3.jpg");
		gestorCoche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), coche);
		
		coche = new Coche ("Ford", "Kuga", "5678HHH", 24600, false, false, false, "/es/deusto/grupo3/img/Ford_Kuga.png");
		gestorCoche.anyadirFilaATablaCoche(BaseDeDatos.getStatement(), coche);
		
		GestorMoto gestorMoto = new GestorMoto();
		Moto moto;
		moto = new Moto ("Honda", "CB1000R", "3344PPS", 30000, false, false, false, "/es/deusto/grupo3/img/Honda_CB_1000_R.jpg");
		gestorMoto.anyadirFilaATablaMoto(BaseDeDatos.getStatement(), moto);
		log.warn("1");
		moto = new Moto ("Yamaha", "XV950R", "1212ABA", 36200, false, false, false, "/es/deusto/grupo3/img/Yamaha-xv950r.jpg");
		gestorMoto.anyadirFilaATablaMoto(BaseDeDatos.getStatement(), moto);
		log.warn("2");
		moto = new Moto ("Ducati", "Monster", "7892GFS", 18600, false, false, false, "/es/deusto/grupo3/img/Ducati_Moster.jpg");
		gestorMoto.anyadirFilaATablaMoto(BaseDeDatos.getStatement(), moto);
		log.warn("3");
		
		log.warn("Antes de visualizar");
		
		
		//menu de log in y registrarse
		
		vistaPrincipal frame = new vistaPrincipal();
		
		frame.setVisible(true);
		
		log.warn("despues de visualizar");
    }
}
