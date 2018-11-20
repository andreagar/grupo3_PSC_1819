package es.deusto.grupo3.LNegocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;

public class GestorMoto {

	ArrayList<Moto> listaMoto;
	
	private final static Logger log = Logger.getLogger(App.class.getName());
		
	public GestorMoto (){
		
		listaMoto = new ArrayList<Moto>();
	}
		
	/** Comprueba si una moto ya esta en la tabla MOTO de BD,
	 * considerando la trayectoria completa del disco como informacion clave.
	 * @param st	Sentencia ya abierta de base de datos
	 * @return	true si el usuario ya esta en la tabla, false en caso contrario
	 */
	public boolean chequearYaEnTabla( Statement st, String matricula ) {
		//SELECT
			try {

				String sentSQL = "select * from MOTO where (matricula = '" + matricula + "')";
				log.info(sentSQL);
				ResultSet rs = st.executeQuery( sentSQL );
				
				if (rs.next()) {  // Normalmente se recorre con un while, pero aqui solo hay que ver si ya existe
					rs.close();
					log.warn("La moto ya existe, prueba con otro");
					//JOptionPane.showMessageDialog(null, "El coche ya existe, prueba con otro","Mensaje de error",JOptionPane.ERROR_MESSAGE);
					return true;
				}
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
			
	/** Añade una moto a la tabla MOTO de BD, 
	 * que debe estar abierta y tener el formato y los nombres 
	 * de campos apropiados: (int idUsuario, Sting nombre, String password)
	 * Usa la sentencia INSERT de SQL.
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean anyadirFilaATablaMoto( Statement st, Moto moto ) {
	//INSERT

		if (chequearYaEnTabla(st, moto.matricula) == false) {  // Si esta ya en la tabla
			// Insercion normal
			try {
				String sentSQL = "insert into MOTO values(" + "'" + moto.marca + "', '" + moto.modelo + "', '" 
															 + moto.matricula + "', '" + moto.precio + "', '"
															 + moto.alquilado + "', '" + moto.comprado + "', '" 
															 + moto.averiado + "', '" + moto.imagen + "')";  
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
	public ArrayList<Moto> GetArrayMotoGlobal(Statement st)
	{
		ResultSet rs;
		ArrayList<Moto> m = new ArrayList<Moto>();
		try {
			String sentSQL = "select * from MOTO";
			log.info(sentSQL);
			rs = st.executeQuery( sentSQL );
			int i=0;
			while (rs.next()) {
				m.add(new Moto (rs.getString(1),rs.getString(2), rs.getString(3), rs.getDouble(4), 
									rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getString(8)));

			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m;

	}
	
	/** Seleccionar coches que estén disponibles (no alquilados, no comprados y no averiados)
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @return	Array de los coches disponibles
	 */
	public ArrayList<Moto> GetArrayMotosDisponibles(Statement st)
	{
		ArrayList<Moto> motosGlobal = new ArrayList<Moto>();
		motosGlobal = this.GetArrayMotoGlobal(st);
		ArrayList<Moto> motosDisp = new ArrayList<Moto>();
		
		for(int i=0; i<motosGlobal.size(); i++){
			if(motosGlobal.get(i).isAlquilado() == false && motosGlobal.get(i).isComprado() == false && motosGlobal.get(i).isAveriado() == false){
				motosDisp.add(motosGlobal.get(i));
			}
		}
		
		return motosDisp;

	}
	
	/** Seleccionar motos que estén sin comprar
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @return	Array de los motos disponibles
	 */
	public ArrayList<Moto> GetArrayMotosSinComprar(Statement st)
	{
		ArrayList<Moto> motosGlobal = new ArrayList<Moto>();
		motosGlobal = this.GetArrayMotoGlobal(st);
		ArrayList<Moto> cochesDisp = new ArrayList<Moto>();
		
		for(int i=0; i<motosGlobal.size(); i++){
			if(motosGlobal.get(i).isComprado() != true){
				cochesDisp.add(motosGlobal.get(i));
			}
		}
		
		return motosGlobal;

	}
	
	/** Alquilar una moto con un usuario específico: poner el atributo alquilado de MOTO a true e insertar una nueva línea en ASIGNACIONES
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @param asig	Objeto de clase Asignaciones
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean AlquilarVehiculoUsuario(Statement st, Asignaciones asig){
		
		try {
			//UPDATE
			String sentUpdate = "update MOTO set alquilado = ? where matricula = ?";
			PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentUpdate);  
            // set the corresponding param
            pstmt.setBoolean(1, asig.getAlquilado());
            pstmt.setString(2, asig.getMatricula());
            // update 
            int val1 = pstmt.executeUpdate();
			
          //INSERT HISTORIAL_ASIGNACIONES
			String sentSQL = "insert into HISTORIAL_ASIGNACIONES values(?, ?, ?, ?, ?, ?)"; 	
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
            
          //INSERT ASIGNACIONES
			String sentSQL2 = "insert into ASIGNACIONES values(?, ?, ?, ?, ?, ?)"; 	
			PreparedStatement pstmt3 = BaseDeDatos.getConnection().prepareStatement(sentSQL2);  
            // set the corresponding param
            pstmt3.setString(1, asig.getUsuario());
            pstmt3.setString(2, asig.getMatricula());
            pstmt3.setBoolean(3, true);
            pstmt3.setBoolean(4, false);
            pstmt3.setBoolean(5, false);
            pstmt3.setInt(6, asig.getVehiculo());
            // insert 
            int val3 = pstmt3.executeUpdate();
	        
			if (val1!=1 && val2!=1 && val3!=1) return false;  // Se tiene que aÃ±adir 1 - error si no
			return true;
		} catch (SQLException e) {
			log.error("Erro al alquilar moto");
			e.printStackTrace();
			return false;
		}
	}
	
	/** Comprar una moto con un usuario específico: poner el atributo comprado de MOTO a true e insertar una nueva línea en ASIGNACIONES
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al coche)
	 * @param asig	Objeto de clase Asignaciones
	 * @return	true si la inserciÃ³n es correcta, false en caso contrario
	 */
	public boolean ComprarVehiculoUsuario(Statement st, Asignaciones asig){
		
		try {
			//UPDATE
			String sentUpdate = "update MOTO set alquilado = ? where matricula = ?";
			PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentUpdate);  
            pstmt.setBoolean(1, asig.getAlquilado());
            pstmt.setString(2, asig.getMatricula());
            int val1 = pstmt.executeUpdate();
			
            //INSERT HISTORIAL_ASIGNACIONES
			String sentSQL = "insert into HISTORIAL_ASIGNACIONES values(?, ?, ?, ?, ?, ?)"; 	
			PreparedStatement pstmt2 = BaseDeDatos.getConnection().prepareStatement(sentSQL);  
            pstmt2.setString(1, asig.getUsuario());
            pstmt2.setString(2, asig.getMatricula());
            pstmt2.setBoolean(3, false);
            pstmt2.setBoolean(4, true);
            pstmt2.setBoolean(5, false);
            pstmt2.setInt(6, asig.getVehiculo());
            int val2 = pstmt2.executeUpdate();
            
            //INSERT ASIGNACIONES
			String sentSQL2 = "insert into ASIGNACIONES values(?, ?, ?, ?, ?, ?)"; 	
			PreparedStatement pstmt3 = BaseDeDatos.getConnection().prepareStatement(sentSQL2);  
            pstmt3.setString(1, asig.getUsuario());
            pstmt3.setString(2, asig.getMatricula());
            pstmt3.setBoolean(3, false);
            pstmt3.setBoolean(4, true);
            pstmt3.setBoolean(5, false);
            pstmt3.setInt(6, asig.getVehiculo());
            int val3 = pstmt3.executeUpdate();
	        
			if (val1!=1 && val2!=1 && val3!=1) return false;
			return true;
		} catch (SQLException e) {
			log.error("Erro al alquilar moto");
			e.printStackTrace();
			return false;
		}
	}
	
	/** Modifica los datos de un coche en la tabla MOTO de BD, 
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente al usuario)
	 * @param todos los demas parámetros son los nuevos datos para la moto
	 * @return	true si la modificacion es correcta, false en caso contrario
	 */
	public boolean modificarDatos (Statement st, String matricula, double precio, String imagen, boolean alquilada, boolean comprada, boolean averiada){
			
			try {
				//UPDATE
				String sentUpdate = "update MOTO set imagen = ?, precio = ?, alquilado = ?, comprado = ?, averiado = ?   where matricula = ?";
				PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentUpdate);  
	            // set the corresponding param
	            pstmt.setString(1, imagen);
	            pstmt.setDouble(2, precio);
	            pstmt.setBoolean(3, alquilada);
	            pstmt.setBoolean(4, comprada);
	            pstmt.setBoolean(5, averiada);
	            pstmt.setString(6, matricula);
	            // update 
	            int val1 = pstmt.executeUpdate();
	            
	            int val = 0;
	            ArrayList<Asignaciones> asig;
	            ResultSet rs;
	            
	            if((averiada == true && alquilada == false) || (averiada == true && alquilada == true) ||
	            	(averiada == false && alquilada == true) || (averiada == false && alquilada == false)){
	            	
	            	String sentSQL = "select * from ASIGNACIONES";
	        		asig = new ArrayList<Asignaciones>();
        			log.info(sentSQL);
        			rs = st.executeQuery( sentSQL );
        			while (rs.next()) {
        				asig.add(new Asignaciones (rs.getString(1),rs.getString(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getInt(6)));

        			}
        			
        			for(int i=0; i<asig.size(); i++){
        				if(asig.get(i).getMatricula().equals(matricula)){
        					String sentSQL2 = "delete from ASIGNACIONES where matricula = ?";
        		    		PreparedStatement pstmt2 = BaseDeDatos.getConnection().prepareStatement(sentSQL2);
        		    		log.info(sentSQL2);
        					pstmt2.setString(1, matricula);
        			        val = pstmt2.executeUpdate();
        				}
        			}
	            }
	            
	            if (val1!=1 && val!=1) return false;  // Se tiene que aÃ±adir 1 - error si no
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}

}
