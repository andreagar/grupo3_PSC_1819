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
	
	/** Seleccionar todos los coches de la BD
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @return	Array de los coches de la BD
	 */
	public ArrayList<Coche> GetArrayCocheGlobal(Statement st){
		ResultSet rs;
		ArrayList<Coche> c = new ArrayList<Coche>();
		try {
			String sentSQL = "select * from COCHE";
			log.info(sentSQL);
			rs = st.executeQuery( sentSQL );
			int i=0;
			while (rs.next()) {
				c.add(new Coche (rs.getString(1),rs.getString(2), rs.getString(3), rs.getDouble(4), 
									rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getString(8)));

			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;

	}
	
	/** Seleccionar coches que estén disponibles (no alquilados, no comprados y no averiados)
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @return	Array de los coches disponibles
	 */
	public ArrayList<Coche> GetArrayCochesDisponibles(Statement st)
	{
		ArrayList<Coche> cochesGlobal = new ArrayList<Coche>();
		cochesGlobal = this.GetArrayCocheGlobal(st);
		ArrayList<Coche> cochesDisp = new ArrayList<Coche>();
		
		for(int i=0; i<cochesGlobal.size(); i++){
			if(cochesGlobal.get(i).getAlquilado() == false && cochesGlobal.get(i).getComprado() == false && cochesGlobal.get(i).getAveriado() == false){
				cochesDisp.add(cochesGlobal.get(i));
			}
		}
		
		return cochesDisp;

	}
	
	/** Alquilar un coche con un usuario específico: poner el atributo alquilado de COCHE a true e insertar una nueva línea en ASIGNACIONES
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @param asig	Objeto de clase Asignaciones
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean AlquilarVehiculoUsuario(Statement st, Asignaciones asig){
		
		try {
			//UPDATE
			String sentUpdate = "update COCHE set alquilado = ? where matricula = ?";
			PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentUpdate);  
            // set the corresponding param
            pstmt.setBoolean(1, asig.getAlquilado());
            pstmt.setString(2, asig.getMatricula());
            // update 
            int val1 = pstmt.executeUpdate();
			
	        //INSERT
			String sentSQL = "insert into ASIGNACIONES values(?, ?, ?, ?, ?, ?)"; 	
			PreparedStatement pstmt2 = BaseDeDatos.getConnection().prepareStatement(sentSQL);  
            // set the corresponding param
            pstmt2.setString(1, asig.getUsuario());
            pstmt2.setString(2, asig.getMatricula());
            pstmt2.setBoolean(3, true);
            pstmt2.setBoolean(4, false);
            pstmt2.setBoolean(5, false);
            pstmt2.setInt(6, asig.getVehiculo());
            // insert 
            int val2 = pstmt2.executeUpdate();
	        
			if (val1!=1 && val2!=1) return false;  // Se tiene que aÃ±adir 1 - error si no
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** Recuperar las asignaciones hechas a un usuario específico
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @param nombre	Nombre del usuario logeado
	 * @return	Array de las asignaciones de un usaurio dado
	 */
	public ArrayList<Asignaciones> getUsuarioHistorial(Statement st, String nombre){
		
		ResultSet rs;
		ArrayList<Asignaciones> a = new ArrayList<Asignaciones>();
		try {
			String sentSQL = "select * from ASIGNACIONES where usuario = ?";
			
			PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentSQL);  
            // set the corresponding param
            pstmt.setString(1, nombre);
            // INSERT 
            rs = pstmt.executeQuery();

			while (rs.next()) {
				a.add(new Asignaciones (rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getInt(6)));
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;
	}
	
	/** Modifica los datos de un coche en la tabla COCHE de BD, 
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al usuario)
	 * @param precio nuevo precio
	 * @param imagen nueva imagen
	 * @return	true si la modificacion es correcta, false en caso contrario
	 */
	public boolean modificarDatos (Statement st, String matricula, double precio, String imagen){
			
			try {
				//UPDATE
				String sentUpdate = "update COCHE set imagen = ?, precio = ? where matricula = ?";
				PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentUpdate);  
	            // set the corresponding param
	            pstmt.setString(1, imagen);
	            pstmt.setDouble(2, precio);
	            pstmt.setString(3, matricula);
	            // update 
	            int val1 = pstmt.executeUpdate();
	            if (val1!=1) return false;  // Se tiene que aÃ±adir 1 - error si no
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}
}
	
	
