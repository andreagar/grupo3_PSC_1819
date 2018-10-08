package es.deusto.grupo3.LDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BaseDeDatos {

	private static Connection connection = null;
	private static Statement statement = null;
	
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
			JOptionPane.showMessageDialog( null, "Ondo!! Nombre: " + nombreBD + " / DATU BASEA KONEKTATU DA" , "BIEN", JOptionPane.INFORMATION_MESSAGE);
		    return connection;
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog( null, "Error!! Nombre: " + nombreBD + " / no se ha conectado la base de datos" , "ERROR", JOptionPane.ERROR_MESSAGE );
			System.out.println( "Error!! Nombre: " + nombreBD + " / Contrasenya incorreta");
			return null;
		}
	}
	
	/** Cierra la conexion con la Base de Datos*/
	public static void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
