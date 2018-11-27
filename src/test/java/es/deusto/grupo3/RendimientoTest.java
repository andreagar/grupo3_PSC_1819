package es.deusto.grupo3;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.grupo3.LDatos.BaseDeDatos;
import es.deusto.grupo3.LNegocio.Asignaciones;
import es.deusto.grupo3.LNegocio.Coche;
import es.deusto.grupo3.LNegocio.GestorAsignaciones;
import es.deusto.grupo3.LNegocio.GestorCoche;

public class RendimientoTest {

	/** TIPOS DE PERFEST
	 * invocations: número de veces que se invocará el @Test
	 * threads: hebras concurrentes sobre las que se distribuirán los invocations
	 * duration: tiempo durante el que se ejecutará el test en bucle (ms)
	 */
	
	
	/** TIPOS DE REQUIRED
	 * throughput: número de ejecuciones por segundo menor que …
	 * average: media del tiempo de ejecución menor que …
	 * median: al menos el 50% de las ejecuciones con un tiempo de ejecución menor 
	 * max: tiempo de ejecución de un test menor que …
	 * totalTime: tiempo total de la ejecución de todos los test menor que …
	 * percentileX: al menos el X% de las ejecuciones con un tiempo de ejecución menor
	 */
	
	GestorCoche gestorCoche;
	GestorAsignaciones gestorAsig;
	Coche coche;
	Asignaciones asig;
	String nombre;
	
	@Before
	public void setUp() throws Exception {
		BaseDeDatos.initBD("nuestroBD.db");
		coche = new Coche("Audi", "A7", "1234ABC", 74000, false, false, false, "/es/deusto/grupo3/img/Audi_A7.jpg");
		gestorCoche = new GestorCoche();
		gestorAsig = new GestorAsignaciones();
		nombre = "Aiora";
		asig = new Asignaciones (nombre, "1234ABC", true, false, false, 1);
	}
	
	@After
	public void tearDown() throws Exception {
		BaseDeDatos.close();
	}
	
	/**Activa Contiperf cuando se lanza JUnit*/
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	@Test
	@PerfTest(invocations = 500, threads = 10, duration = 4000)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir número de iteraciones y los hilos que se disponen
	@Required(max = 4000, average = 2000, median = 1900)  //Required define las requisitos de rendimiento, si no los cumple --> rojo
	public void testAlquilarVehiculoUsuario (){	
		
		/** TEST OK! */
		
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig);
		Asignaciones asig2 = new Asignaciones(nombre, "2233JDH", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig2);
		Asignaciones asig3 = new Asignaciones(nombre, "0990UUU", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig3);
		Asignaciones asig4 = new Asignaciones(nombre, "2212JAA", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig4);
		Asignaciones asig5 = new Asignaciones(nombre, "8090FKH", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig5);
		Asignaciones asig6 = new Asignaciones(nombre, "8090FKH", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig6);
		Asignaciones asig7 = new Asignaciones(nombre, "1342KPL", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig7);
		Asignaciones asig8 = new Asignaciones(nombre, "6534KLL", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig8);
		Asignaciones asig9 = new Asignaciones(nombre, "1231YUE", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig9);
		Asignaciones asig10 = new Asignaciones(nombre, "8889ERE", true, false, false, 1);
		gestorCoche.AlquilarVehiculoUsuario(BaseDeDatos.getStatement(), asig10);
	}
	
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration = 1000)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir número de iteraciones y los hilos que se disponen
	@Required(max = 800, average = 400, median = 500)  //Required define las requisitos de rendimiento, si no los cumple --> rojo
	public void testgetUsuarioHistorial (){
		
		/** TEST ERROR! 
		 * 
		 * No es capaz de procesar la consulta en el tiempo deseado cuando el número de 
		 * inserciones a la tabla HISTORIA_ASIGNACIONES es considerable 
		*/
		gestorAsig.getUsuarioHistorial(BaseDeDatos.getStatement(), nombre);
	}

	@Test
	@PerfTest(invocations = 1000, threads = 20, duration = 1000)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir número de iteraciones y los hilos que se disponen
	@Required(max = 1200, average = 300, median = 500)  //Required define las requisitos de rendimiento, si no los cumple --> rojo
	public void getVehiculos (){
		
		/** TEST OK! 
		 * 
		 * Si se recogen coches + motos en un mismo método, nos da error con ResulSet porque debe haber 
		 * un solo ResultSet por cada método: "SQLite JDBC internal error: rs.isOpen() on exec."
		*/
		
		gestorCoche.getVehiculosCoches(BaseDeDatos.getStatement());
		gestorCoche.getVehiculosMotos(BaseDeDatos.getStatement());
		gestorCoche.vehiculos();
	}
	
}
