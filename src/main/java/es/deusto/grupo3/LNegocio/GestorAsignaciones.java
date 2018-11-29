package es.deusto.grupo3.LNegocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.deusto.grupo3.App;
import es.deusto.grupo3.LDatos.BaseDeDatos;

public class GestorAsignaciones {
	
	private final static Logger log = Logger.getLogger(App.class.getName());

	/** Recuperar las asignaciones realizadas a un usuario específico
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a asignaciones)
	 * @param nombre	Nombre del usuario logeado
	 * @return	Array de las asignaciones de un usaurio dado
	 */
	public ArrayList<Asignaciones> getUsuarioHistorial(Statement st, String nombre){
		
		ResultSet rs;
		ArrayList<Asignaciones> a = new ArrayList<Asignaciones>();
		try {
			String sentSQL = "select * from HISTORIAL_ASIGNACIONES where usuario = ?";
			log.info(sentSQL);
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
	
	/** Seleccionar asignaciones de la tabla ASIGNACIONES de BD
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a asignaciones)
	 * @return	Array de los coches disponibles
	 */
	public ArrayList<Asignaciones> GetArrayUsuarioAlquilados(Statement st, String nombre)
	{
		ArrayList<Asignaciones> asigGlobal = new ArrayList<Asignaciones>();
		ResultSet rs;
		try {
			String sentSQL = "select * from ASIGNACIONES where usuario = ?";
			log.info(sentSQL);
			PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentSQL);  
            // set the corresponding param
            pstmt.setString(1, nombre);
            // INSERT 
            rs = pstmt.executeQuery();

			while (rs.next()) {
				asigGlobal.add(new Asignaciones (rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getInt(6)));
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Asignaciones> alquilados = new ArrayList<Asignaciones>();
		
		for(int i=0; i<asigGlobal.size(); i++){
			if(asigGlobal.get(i).getAlquilado() == true){
				alquilados.add(asigGlobal.get(i));
			}
		}
		
		return alquilados;

	}
	
	/** Cancelar un alquiler realizado: 
	 * 			1. Modificar el atributo alquilado a FALSE en tabla COCHE o MOTO
	 * 			2. Borra la línea correspondiente al alquiler eliminado
	 * @param st	Sentencia ya abierta de Base de Datos (con la estructura de tabla correspondiente a asignaciones)
	 * @param matricula		Matricula del vehículo
	 * @param usuario		Usuario del alquiler
	 * @param vehiculo		Tipo de vehículo alquilado: Coche o Moto
	 * @return	Array de los coches disponibles
	 */
	public boolean CancelarAlquiler(Statement st, String matricula, String usuario, String vehiculo){
  	
        try {
        	int val1 = 0;
        	
        	if(vehiculo.equals("Coche")){
        		String sentUpdate = "update COCHE set alquilado = ? where matricula = ?";
    			PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentUpdate);  
                // set the corresponding param
                pstmt.setBoolean(1, false);
                pstmt.setString(2, matricula);
                log.info(sentUpdate);
                // update 
                val1 = pstmt.executeUpdate();
        	}
        	if(vehiculo.equals("Moto")){
        		String sentUpdate = "update MOTO set alquilado = ? where matricula = ?";
    			PreparedStatement pstmt = BaseDeDatos.getConnection().prepareStatement(sentUpdate);  
                // set the corresponding param
                pstmt.setBoolean(1, false);
                pstmt.setString(2, matricula);
                log.info(sentUpdate);
                // update 
                val1 = pstmt.executeUpdate();
                log.info(vehiculo);
        	}
        	
        	String sentSQL = "delete from ASIGNACIONES where usuario = ? AND matricula = ?";
    		PreparedStatement pstmt2 = BaseDeDatos.getConnection().prepareStatement(sentSQL);
    		log.info(sentSQL);
			pstmt2.setString(1, usuario);
			pstmt2.setString(2, matricula);
	        int val = pstmt2.executeUpdate();
	        
	        if (val1!=1 && val!=1) return false;  // Se tiene que añadir 1 - error si no
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}