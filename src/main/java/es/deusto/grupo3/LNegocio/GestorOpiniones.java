package es.deusto.grupo3.LNegocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;

public class GestorOpiniones {

	private final static Logger log = Logger.getLogger(App.class.getName());
	
	/** Recuperar las matriculas de los coches/motos alquiladas por un usuario
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a asignaciones)
	 * @param nombre	Nombre del usuario logeado
	 * @return	Array de las matriculas de un usaurio dado
	 */
	public ArrayList<String> getUsuarioMatriculas(Statement st, String nombre){
		
		ArrayList<String> alquilados = new ArrayList<String>();
		ResultSet rs;
		try {
			String sentSQL = "select matricula from ASIGNACIONES where usuario = ?";
			log.info(sentSQL);
			PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentSQL);  
            // set the corresponding param
            pstmt.setString(1, nombre);
            // INSERT 
            rs = pstmt.executeQuery();

			while (rs.next()) {
				alquilados.add((rs.getString(1)));
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alquilados;
	}
	
	public boolean chequearYaEnTabla( Statement st, String matricula, String nombre ) {
		//SELECT
			try {

				String sentSQL = "select * from OPINIONES where (matricula = '" + matricula + ", usuario = '" + nombre + "')";
				log.info(sentSQL);
				ResultSet rs = st.executeQuery( sentSQL );
				
				if (rs.next()) {  // Normalmente se recorre con un while, pero aqui solo hay que ver si ya existe
					rs.close();
					log.warn("La reseña ya existe");
					//JOptionPane.showMessageDialog(null, "El coche ya existe, prueba con otro","Mensaje de error",JOptionPane.ERROR_MESSAGE);
					return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	
	/** Guardar las reseñas
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a asignaciones)
	 * @param nombre	Nombre del usuario logeado
	 */
	public boolean guardarOpinion(Statement st, String nombre, String matricula, int puntuacion, String comentario){
		
		if (chequearYaEnTabla(st, matricula, nombre) == false) {  // Si esta ya en la tabla
			// Insercion normal
			try {
				String sentSQL = "insert into OPINIONES values(" + "'" + nombre + "', '" + matricula + "', '" 
															 + puntuacion + "', '" + comentario + "')";  
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
}
