package es.deusto.grupo3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;


import org.apache.log4j.Logger;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Clima;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorClima;
import es.deusto.grupo3.LNegocio.GestorCoche;
import es.deusto.grupo3.LNegocio.GestorMoto;
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
    	///inicialización de BD
    	log.info("iniciando BD");
    	BaseDeDatos.initBD("nuestroBD.db");
    	log.info("BD iniciada");
    	
//    	String s = "drop table MOTO";
//    	try {
//			BaseDeDatos.getStatement().executeUpdate(s);
//			System.out.println(s);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	String a = "drop table COCHE";
//    	try {
//			BaseDeDatos.getStatement().executeUpdate(a);
//			System.out.println(a);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	String b = "drop table ASIGNACIONES";
//    	try {
//			BaseDeDatos.getStatement().executeUpdate(b);
//			System.out.println(b);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	String FF = "drop table HISTORIAL_ASIGNACIONES";
//    	try {
//			BaseDeDatos.getStatement().executeUpdate(FF);
//			System.out.println(FF);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	///Creación de tablas
		BaseDeDatos.crearTablaBDUsuario();
		BaseDeDatos.crearTablaBDCoche();
		BaseDeDatos.crearTablaBDMoto();
		BaseDeDatos.crearTablaBDAsignaciones();
		BaseDeDatos.crearTablaBDHistorialAsignaciones();
		BaseDeDatos.crearTablaBDOficina();
		BaseDeDatos.crearTablaBDClima();
		BaseDeDatos.crearTablaBDOpiniones();
	
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
		
		///Insertamos las motos predeterminados para poder trabajar con ellas
		GestorMoto gestorMoto = new GestorMoto();
		Moto moto;
		moto = new Moto ("Honda", "CB1000R", "3344PPS", 30000, false, false, false, "/es/deusto/grupo3/img/Honda_CB_1000_R.jpg");
		gestorMoto.anyadirFilaATablaMoto(BaseDeDatos.getStatement(), moto);
		moto = new Moto ("Yamaha", "XV950R", "1212ABA", 36200, false, false, false, "/es/deusto/grupo3/img/Yamaha-xv950r.jpg");
		gestorMoto.anyadirFilaATablaMoto(BaseDeDatos.getStatement(), moto);
		moto = new Moto ("Ducati", "Monster", "7892GFS", 18600, false, false, false, "/es/deusto/grupo3/img/Ducati_Moster.jpg");
		gestorMoto.anyadirFilaATablaMoto(BaseDeDatos.getStatement(), moto);
		
		
		GestorClima gestorClima = new GestorClima();
		Clima clima;
		
		clima = new Clima("donostia", 15.5, "nublado", 60);
		gestorClima.anyadirFilaATablaClima(BaseDeDatos.getStatement(), clima);
		
		clima = new Clima("bilbao", 17, "sol y nubes", 15);
		gestorClima.anyadirFilaATablaClima(BaseDeDatos.getStatement(), clima);
		
		//menu de log in y registrarse
	
		vistaPrincipal frame = new vistaPrincipal();
		frame.setVisible(true);
		
    }
}
