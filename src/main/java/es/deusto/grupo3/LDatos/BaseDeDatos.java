package es.deusto.grupo3.LDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;


public class BaseDeDatos {

	private static Connection connection = null;
	private static Statement statement = null;
	private final static Logger log = Logger.getLogger(App.class.getName());
	
	/** Inicializa una BD SQLITE y devuelve una conexion con ella. Debe llamarse a este 
	 * metodo antes que ningun otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexion con la base de datos indicada. Si hay algun error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC"); //.jar
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg. 
			//TIMEOUT: Si estamos esperando una respuesta y si no nos dan en 30 mseg (en este caso), dejamos de esperar.
			log.info("Nombre: " + nombreBD + " / Base de datos conectada");
			//JOptionPane.showMessageDialog( null, "Ondo!! Nombre: " + nombreBD + " / DATU BASEA KONEKTATU DA" , "BIEN", JOptionPane.INFORMATION_MESSAGE);
		    return connection;
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog( null, "Error!! Nombre: " + nombreBD + " / no se ha conectado la base de datos" , "ERROR", JOptionPane.ERROR_MESSAGE );
			log.fatal("Error!! Nombre: " + nombreBD + " / Contrasenya incorreta");
			return null;
		}
	}
	
	/** Cierra la conexion con la Base de Datos*/
	public static void close() {
		try {
			statement.close();
			connection.close();
			log.info("BD cerrada correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("no se ha cerrado bien la BD");
		}
	}
	
	/** Devuelve la conexion si ha sido establecida previamente (#initBD()).
	 * @return	Conexion con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la BD,
	 * si la conexion si ha sido establecida previamente (#initBD()).
	 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() {
		return statement;
	}
	
	/** Crea una tabla en una base de datos, si no existia ya.
	 * Debe haberse inicializado la conexion correctamente.
	 * 
	 * @param nombre
	 * @param contrasenya
	 */
	public static void crearTablaBDUsuario() {
		if (statement==null) return;
		try {
			statement.executeUpdate("create table if not exists USUARIO (nombre string, contrasenya string)");
		} catch (SQLException e) {
			// Si hay excepcion es que la tabla ya existï¿½a (lo cual es correcto)
			if (!e.getMessage().equals("table interaccion already exists"))
				e.printStackTrace();  
			log.info("NO se ha creado tabla USUARIO: ya existe");
		}
	}
	/**
	 * Crea la tabla Coche si no existe
	 * 
	 * @param marca
	 * @param modelo
	 * @param matricula
	 * @param precio
	 * @param alquilado
	 * @param comprado
	 * @param averiado
	 * @param imagen      
	 */
	 public static void crearTablaBDCoche(){
		if (statement==null)
			return ;
		 try {

			statement.executeUpdate("create table if not exists COCHE (marca string, modelo string, matricula string, precio double, alquilado boolean, comprado boolean, averiado boolean, imagen string)");

		} catch (SQLException e) {
			// Si hay excepcion es que la tabla ya existï¿½a (lo cual es correcto)
			if (!e.getMessage().equals("table interaccion already exists"))
				e.printStackTrace();  
			log.info("NO se ha creado tabla COCHE: ya existe");
		}
	 }
	 
	 /**
	 * Crea la tabla Moto si no existe
	 * 
	 * @param marca
	 * @param modelo
	 * @param matricula
	 * @param precio
	 * @param alquilado
	 * @param comprado
	 * @param averiado
	 * @param imagen
	 */
	 public static void crearTablaBDMoto(){
		if (statement==null)
			return ;
		 try {

			statement.executeUpdate("create table if not exists MOTO (marca string, modelo string, matricula string, precio double, alquilado boolean, comprado boolean, averiado boolean, imagen string)");

		} catch (SQLException e) {
			// Si hay excepcion es que la tabla ya existï¿½a (lo cual es correcto)
			if (!e.getMessage().equals("table interaccion already exists"))
				e.printStackTrace();  
			log.info("NO se ha creado tabla MOTO: ya existe");
		}
	 }
		 
	 /**
	 * Crea la tabla Asignaciones si no existe
	 * 
	 * @param usuario
	 * @param matricula
	 * @param alquilado
	 * @param comprado
	 * @param averiado
	 * @param vehiculo --> 1: coche, 2: moto
	 */
	 public static void crearTablaBDAsignaciones(){
		if (statement==null)
			return ;
		 try {

			statement.executeUpdate("create table if not exists ASIGNACIONES (usuario string, matricula string, alquilado boolean, comprado boolean, averiado boolean, vehiculo int)");

		} catch (SQLException e) {
			// Si hay excepcion es que la tabla ya existï¿½a (lo cual es correcto)
			if (!e.getMessage().equals("table interaccion already exists"))
				e.printStackTrace();  
			log.info("NO se ha creado tabla ASIGNACIONES: ya existe");
		}
	 }
			 
	 /**
	 * Crea la tabla Historial_Asignaciones si no existe
	 * 
	 * @param usuario
	 * @param matricula
	 * @param alquilado
	 * @param comprado
	 * @param averiado
	 * @param vehiculo --> 1: coche, 2: moto
	 */
	 public static void crearTablaBDHistorialAsignaciones(){
		if (statement==null)
			return ;
		 try {

			statement.executeUpdate("create table if not exists HISTORIAL_ASIGNACIONES (usuario string, matricula string, alquilado boolean, comprado boolean, averiado boolean, vehiculo int)");

		} catch (SQLException e) {
			// Si hay excepcion es que la tabla ya existï¿½a (lo cual es correcto)
			if (!e.getMessage().equals("table interaccion already exists"))
				e.printStackTrace();  
			log.info("NO se ha creado tabla HISTORIAL_ASIGNACIONES: ya existe");
		}
	 }
				 
	 /**
	 * Crea la tabla Oficina si no existe
	 * 
	 * @param usuario
	 * @param matricula
	 * @param alquilado
	 * @param comprado
	 * @param averiado
	 * @param vehiculo --> 1: coche, 2: moto
	 */
	 public static void crearTablaBDOficina(){
		if (statement==null)
			return ;
		 try {

			statement.executeUpdate("create table if not exists OFICINA (id int, nombre string, ciudad string, pais string)");

		} catch (SQLException e) {
			// Si hay excepcion es que la tabla ya existï¿½a (lo cual es correcto)
			if (!e.getMessage().equals("table interaccion already exists"))
				e.printStackTrace();  
			log.info("NO se ha creado tabla HISTORIAL_ASIGNACIONES: ya existe");
		}
	 }
	 
	 /**
		 * Crea la tabla Clima si no existe
		 * 
		 * @param pais
		 * @param ciudad
		 * @param temperatura
		 * @param estado
		 * @param precipitacion
		 */
		 public static void crearTablaBDClima(){
			if (statement==null)
				return ;
			 try {
				statement.executeUpdate("create table if not exists CLIMA (pais string, ciudad string, temperatura double, estado string, precipitacion boolean)");
			} catch (SQLException e) {
				// Si hay excepcion es que la tabla ya existï¿½a (lo cual es correcto)
				if (!e.getMessage().equals("table interaccion already exists"))
					e.printStackTrace();  
				log.info("NO se ha creado tabla CLIMA: ya existe");
			}
		 }
	
}
