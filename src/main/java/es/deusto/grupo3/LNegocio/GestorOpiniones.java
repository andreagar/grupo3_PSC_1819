package es.deusto.grupo3.LNegocio;

//import static java.awt.Window.log;

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
	
	/** Guardar las reseñas
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a asignaciones)
	 * @param nombre	Nombre del usuario logeado
	 */
	public boolean guardarOpinion(Statement st, Opinion opinion){
		
		// Insercion normal
			try {
				String sentSQL = "insert into OPINIONES values(" + "'" + opinion.getUsuario() + "', '" + opinion.getMatricula() + "', '" 
															 + opinion.getPuntuacion() + "', '" + opinion.getComentario() + "')";  
				log.info(sentSQL);
				int val = st.executeUpdate( sentSQL );
				if (val!=1) return false;  // Se tiene que añadir 1 - error si no
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

	}
	
	
	/** Recuperar las opiniones realizadas por los usuarios
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a asignaciones)
	 * @return	Array de las opiniones
	 */
	public ArrayList<Opinion> getOpiniones(Statement st){
		
		ResultSet rs;
		ArrayList<Opinion> of = new ArrayList<Opinion>();
		try {
			String sentSQL = "select * from OPINIONES";
			log.info(sentSQL);
			rs = st.executeQuery( sentSQL );
			int i=0;
			while (rs.next()) {
				of.add(new Opinion (rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return of;
	}
}
