package es.deusto.grupo3.LNegocio;


import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;

public class GestorCoche {

	ArrayList<Coche> listaCoche;
	
	private final static Logger log = Logger.getLogger(App.class.getName());
		
	public GestorCoche (){
		listaCoche = new ArrayList<Coche>();
		
	}
		
	/** Comprueba si un coche ya esta en la tabla COCHE de BD,
	 * considerando la trayectoria completa del disco como informacion clave.
	 * @param st	Sentencia ya abierta de base de datos
	 * @return	true si el usuario ya esta en la tabla, false en caso contrario
	 */
	public boolean chequearYaEnTabla( Statement st, String matricula ) {
		//SELECT
			try {

				String sentSQL = "select * from COCHE where (matricula = '" + matricula + "')";
				log.info(sentSQL);
				ResultSet rs = st.executeQuery( sentSQL );
				
				if (rs.next()) {  // Normalmente se recorre con un while, pero aqui solo hay que ver si ya existe
					rs.close();
					log.info("El coche ya existe, prueba con otro");
					//JOptionPane.showMessageDialog(null, "El coche ya existe, prueba con otro","Mensaje de error",JOptionPane.ERROR_MESSAGE);
					return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
			
	/** Añade un coche a la tabla COCHE de BD, 
	 * que debe estar abierta y tener el formato y los nombres 
	 * de campos apropiados: (int idUsuario, Sting nombre, String password)
	 * Usa la sentencia INSERT de SQL.
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean anyadirFilaATablaCoche( Statement st, Coche coche ) {
	//INSERT

		if (chequearYaEnTabla(st, coche.getMatricula()) == false) {  // Si esta ya en la tabla
			// Insercion normal
			try {
				String sentSQL = "insert into COCHE values(" + "'" + coche.marca + "', '" + coche.modelo + "', '" 
															 + coche.matricula + "', '" + coche.precio + "', '"
															 + coche.alquilado + "', '" + coche.comprado + "', '" 
															 + coche.averiado + "', '" + coche.imagen + "')"; 
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
	
	public ArrayList<Coche> GetArrayCoche(Statement st)
	{
		ResultSet rs;
		Coche cocheBD = null;
		Coche[] coch = null;
		ArrayList<Coche> c = new ArrayList();
		try {
			String sentSQL = "select * from COCHE";
			log.info(sentSQL);
			rs = st.executeQuery( sentSQL );
			int i=0;
			while (rs.next()) {
				
				c.add(new Coche (rs.getString(1),rs.getString(2), rs.getString(3), rs.getDouble(4), 
									rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getString(8)));
				
			} 
			
			for(int k=0; k<c.size();k++)  System.out.println(c);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return c;
		
	}
}
	
	
