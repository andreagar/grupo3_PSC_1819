package es.deusto.grupo3.LNegocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;

public class GestorClima {
	private final static Logger log = Logger.getLogger(App.class.getName());
	
	/** Añade un clima a la tabla CLIMA de BD, 
	 * que debe estar abierta y tener el formato y los nombres 
	 * de campos apropiados: (String ciudad, double temperatura, String estado, int precipitacion)
	 * Usa la sentencia INSERT de SQL.
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al clima)
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean anyadirFilaATablaClima( Statement st, Clima clima ) {
	//INSERT

		if (chequearYaEnTabla(st, clima.getCiudad()) == false) {  // Si esta ya en la tabla
			// Insercion normal
			try {
				String sentSQL = "insert into CLIMA values(" + "'" + clima.ciudad + "', '" + clima.temperatura + "', '" 
															 + clima.estado + "', '" + clima.precipitacion + "')"; 
				log.info(sentSQL);
				int val = st.executeUpdate( sentSQL );
				if (val!=1) return false;  // Se tiene que aÃ±adir 1 - error si no
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
		
	}
	
	/** Comprueba si un clima ya esta en la tabla COCHE de BD,
	 * considerando la trayectoria completa del disco como informacion clave.
	 * @param st	Sentencia ya abierta de base de datos
	 * @return	true si el clima ya esta en la tabla, false en caso contrario
	 */
	public boolean chequearYaEnTabla( Statement st, String ciudad ) {
		//SELECT
			try {
				String sentSQL = "select * from CLIMA where (ciudad = '" + ciudad + "')";
				log.info(sentSQL);
				ResultSet rs = st.executeQuery( sentSQL );
				
				if (rs.next()) {  // Normalmente se recorre con un while, pero aqui solo hay que ver si ya existe
					rs.close();
					log.warn("El clima para "+ciudad+" ya existe, prueba con otro");
					//JOptionPane.showMessageDialog(null, "El clima ya existe, prueba con otro","Mensaje de error",JOptionPane.ERROR_MESSAGE);
					return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}
	
	public Clima getClima(Statement st, String ciudad){
		Clima clima = new Clima(null,0,null,0);
		
		String sentSQL = "select * from CLIMA where (ciudad = '" + ciudad + "')";
		log.info(sentSQL);
		try {
			ResultSet rs = st.executeQuery( sentSQL );
			log.info("El clima "+ciudad+" seleccionado correctamente");
			if (rs.next()) {  // Normalmente se recorre con un while, pero aqui solo hay que ver si ya existe
				clima = new Clima(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getInt(4)) ;
				
				log.info(clima.ciudad+", "+clima.estado+", "+clima.temperatura+", "+clima.precipitacion);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("error en select clima: "+ciudad);
		}
		return clima;
		
	}

}
