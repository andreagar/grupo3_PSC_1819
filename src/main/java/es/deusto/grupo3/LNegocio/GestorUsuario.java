package es.deusto.grupo3.LNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;

public class GestorUsuario {
	
	///GESTOR USUARIO PARA LA GESTIÓN DE USUARIOS

	
	private final static Logger log = Logger.getLogger(App.class.getName());
		
	public GestorUsuario (){
		
	}
	
	/** Comprueba si un usuario ya esta en la tabla USUARIO de BD para su registro, considerando la trayectoria completa del disco como informacion clave.
	 * @param st	Sentencia ya abierta de base de datos
	 * @return	true si el usuario ya esta en la tabla, false en caso contrario
	 */
	public boolean chequearYaEnTabla( Statement st, String nombre ) {
		//SELECT
			try {

				String sentSQL = "select * from USUARIO where (nombre = '" + nombre + "')";
				log.info(sentSQL); 
				
				ResultSet rs = st.executeQuery( sentSQL );
				
				if (rs.next()) {  // Normalmente se recorre con un while, pero aqui solo hay que ver si ya existe
					rs.close();
					log.warn("El usuario ya existe, prueba con otro");
					JOptionPane.showMessageDialog(null, "El usuario ya existe, prueba con otro","Mensaje de error",JOptionPane.ERROR_MESSAGE);
					return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	
	/** Comprueba si un usuario ya esta en la tabla USUARIO de BD para logearse,considerando la trayectoria completa del disco como informacion clave.
	 * @param st	Sentencia ya abierta de base de datos
	 * @return	true si el usuario ya esta en la tabla, false en caso contrario
	 */
	public boolean chequearYaEnTablaLOGIN( Statement st, String nombre, String contrasenya ) {
		//SELECT
			try {

				String sentSQL = "select * from USUARIO where ( nombre = '" + nombre + "' and contrasenya = '" + contrasenya + "')";
				log.info(sentSQL); 
				
				ResultSet rs = st.executeQuery( sentSQL );
				
				if (rs.next()) { 
					rs.close();
					log.warn("Nombre de usuario y contrasenya correctas");
					JOptionPane.showMessageDialog(null, "Nombre de usuario y contrasenya correctas","Correcto",JOptionPane.INFORMATION_MESSAGE);
					return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	
		
	/** Añade un usuario a la tabla USUARIO de BD, 
	 * que debe estar abierta y tener el formato y los nombres de campos apropiados: (int idUsuario, Sting nombre, String password)
	 * Usa la sentencia INSERT de SQL.
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al usuario)
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean anyadirFilaATablauUsuario( Statement st, String nombre, String password ) {
	//INSERT

		if (chequearYaEnTabla(st, nombre) == false) {  // Si esta ya en la tabla
			// Insercion normal
			try {
				String sentSQL = "insert into USUARIO values(" + "'" + nombre + "', " + "'" + password + "')"; 
				log.info(sentSQL); 
				int val = st.executeUpdate( sentSQL );
				if (val!=1) return false;  // Se tiene que añadir 1 - error si no
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
		
	}
	
	/** Modifica los datos de un usuario en la tabla USUARIO de BD, 
	 * que debe estar abierta y tener el formato y los nombres de campos apropiados:(int numJuego, Sting nombre, String contrasenya)
	 * Usa la sentencia UPDATE de SQL.
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al usuario)
	 * @return	true si la modificacion es correcta, false en caso contrario
	 */
	public boolean cambiarContrasenya (Statement st, String nombre, String nueva){
			
			try {
				String sentSQL = "update USUARIO set "+
									"contrasenya = '" + nueva + "'" +
									" WHERE nombre = '" + nombre + "'";
				
				log.info(sentSQL); 
				int val = st.executeUpdate( sentSQL );
				if (val!=1) return false;  // Se tiene que modificar 1, error si no
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	}
	
	/** Bloquea un usuario (Funcionalidad para Mockito)
	 * @param string	Nombre de usuario
	 * @return	false si el usuario no ha sido bloqueado
	 */
	public boolean bloquearUsuario(String string) {
		return false;
		// TODO Auto-generated method stub
		
	}
}
