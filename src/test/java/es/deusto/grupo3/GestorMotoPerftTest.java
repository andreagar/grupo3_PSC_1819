package es.deusto.grupo3;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class GestorMotoPerftTest {

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
	
	/**Activa Contiperf cuando se lanza JUnit*/
	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration=1500)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir número de iteraciones y los hilos que se disponen
	@Required(max = 200, average = 115, median=500)  //Required define las requisitos de rendimiento, si no los cumple --> rojo
    public void testanyadirFilaATablaCoche() throws Exception{
		Thread.sleep(100);
    }
	
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration=1500)   //PerfTest convierte un JUnit en una prueba Contiperf. A definir número de iteraciones y los hilos que se disponen
	@Required(max = 300, average = 120, median=400)  //Required define las requisitos de rendimiento, si no los cumple --> rojo
    public void testGetArrayCocheGlobal() throws Exception{
		Thread.sleep(100);
    }
	
	@Test
	@PerfTest(invocations = 500, threads = 20, duration=500)
	@Required(max = 200, average = 125, median=300)
    public void testGetArrayCochesDisponibles() throws Exception{
		Thread.sleep(100);
    }
	
	@Test
	@PerfTest(invocations = 1000, threads = 20, duration=1000)
	@Required(max = 180, average = 114, median=300)
	public void AlquilarVehiculoUsuario() throws Exception{
		Thread.sleep(100);
	}

}
