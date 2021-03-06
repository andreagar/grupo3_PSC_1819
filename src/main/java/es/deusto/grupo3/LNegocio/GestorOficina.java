package es.deusto.grupo3.LNegocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;

public class GestorOficina {
	
	///GESTOR OFICINA PARA LA GESTIÓN DE OFICINAS
	
	private final static Logger log = Logger.getLogger(App.class.getName());

	/** Comprueba si una oficina ya esta en la tabla OFICINA de BD,
	 * considerando la trayectoria completa del disco como informacion clave.
	 * @param st	Sentencia ya abierta de base de datos
	 * @return	true si la oficina ya esta en la tabla, false en caso contrario
	 */
	public boolean chequearYaEnTabla( Statement st, int id ) {
		//SELECT
			try {
				String sentSQL = "select * from OFICINA where (id = '" + id + "')";
				log.info(sentSQL);
				ResultSet rs = st.executeQuery( sentSQL );
				
				if (rs.next()) {  // Normalmente se recorre con un while, pero aqui solo hay que ver si ya existe
					rs.close();
					log.warn("La oficina ya existe, prueba con otro");
					return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}
			
	/** Añade una oficina a la tabla OFICINA de BD, 
	 * que debe estar abierta y tener el formato y los nombres 
	 * de campos apropiados: (int id, Sting nombre, String ciudad, String pais)
	 * Usa la sentencia INSERT de SQL.
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a la oficina)
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean anyadirFilaATablaOficina( Statement st, Oficina oficina ) {
	//INSERT

		if (chequearYaEnTabla(st, oficina.getId()) == false) {  // Si esta ya en la tabla
			// Insercion normal
			try {
				String sentSQL = "insert into OFICINA values(" + "'" + oficina.id + "', '" + oficina.nombre + "', '" 
															 + oficina.ciudad + "', '" + oficina.pais + "')"; 
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
	
	/** Seleccionar todos las oficinas de la BD
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a oficina)
	 * @return	Array de oficinas de la BD
	 */
	public ArrayList<Oficina> GetArrayOficinas(Statement st){
		ResultSet rs;
		ArrayList<Oficina> of = new ArrayList<Oficina>();
		try {
			String sentSQL = "select * from OFICINA";
			log.info(sentSQL);
			rs = st.executeQuery( sentSQL );
			int i=0;
			while (rs.next()) {
				of.add(new Oficina (rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4)));
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return of;

	}
	
	/** Eliminar una oficina de la tabla OFICINA de BD seleccionada por el usuario admin
	 * Usa la sentencia INSERT de SQL.
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a la oficina)
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean eliminarOficina( Statement st, Oficina oficina ) {
	//DELETE 
		try {
        	String sentSQL = "delete from OFICINA where id = ? and nombre = ?";
    		PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentSQL);
    		log.info(sentSQL);
			pstmt.setInt(1, oficina.getId());
			pstmt.setString(2, oficina.getNombre());;
	        int val = pstmt.executeUpdate();
	        if (val!=1) return false;  // Se tiene que añadir 1 - error si no
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
