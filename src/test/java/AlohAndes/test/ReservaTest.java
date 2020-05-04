/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package AlohAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import AlohAndes.negocio.AlohAndes;
import AlohAndes.negocio.VOReserva;

/**
 * Clase con los métdos de prueba de funcionalidad sobre Reserva 
 * @author Germán Bravo
 *
 */
public class ReservaTest
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ReservaTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private AlohAndes alohAndes;
	
    /* ****************************************************************
	 * 			Métodos de prueba para la tabla Reserva - Creación y borrado
	 *****************************************************************/
	/**
	 * Método que prueba las operaciones sobre la tabla Reserva
	 * 1. Resgistar una reserva
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un tipo de bebida por su identificador
	 * 4. Borrar un tipo de bebida por su nombre
	 */
    @Test
	public void CRDTReservaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Reserva");
			
			alohAndes = new AlohAndes ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Reserva incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Reserva incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de las rservas con la tabla vacía
			List <VOReserva> lista = alohAndes.darVORerserva();
			assertEquals ("No debe Reservas creados!!", 0, lista.size ());

			
			// Lectura de las reservas con una reserva adicionado
			long idAlojamiento = 333;
			long idMiembro = 2123;
			String tipoId = "CC";
			int tiempoDias = 4;
			
			VOReserva Reserva1 = alohAndes.adicionarReserva(idAlojamiento, idMiembro, tipoId, tiempoDias);
			lista = alohAndes.darVORerserva();
			assertEquals ("Debe haber una reserva creada creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", Reserva1, lista.get (0));

			
			// Lectura de las reservas con dos reservas adicionados
			long idAlojamiento2 = 444;
			long idMiembro2 = 3234;
			String tipoId2 = "CC";
			int tiempoDias2 = 3;
			
			VOReserva Reserva2 = alohAndes.adicionarReserva(idAlojamiento2, idMiembro2, tipoId2, tiempoDias2);
			lista = alohAndes.darVORerserva();
			assertEquals ("Debe haber dos Reservas creados !!", 2, lista.size ());
			
			assertTrue ("la primera Rserva adicionado debe estar en la tabla", Reserva1.equals (lista.get (0)) || Reserva1.equals (lista.get (1)));
			assertTrue ("la segunda Reserva adicionado debe estar en la tabla", Reserva2.equals (lista.get (0)) || Reserva2.equals (lista.get (1)));

			
			// Prueba de eliminación de una Reserva, dado su id 
			long tbEliminados = alohAndes.eliminarReservaPorId (Reserva1.getNumReserva());
			assertEquals ("Debe haberse eliminado una bebida  !!", 1, tbEliminados);
			
			lista = alohAndes.darVORerserva();
			assertEquals ("Debe haber un solo uan reserva !!", 1, lista.size ());
			assertFalse ("la primera reserva adicionado NO debe estar en la tabla", Reserva1.equals (lista.get (0)));
			assertTrue ("la segunda reserva adicionado debe estar en la tabla", Reserva2.equals (lista.get (0)));
			
			
			//prueba el metodo de dar Reserva por id
			VOReserva Reservabuscada = alohAndes.darReservaPorId(Reserva2.getNumReserva());
			assertTrue ("la segunda reserva adicionado debe estar en la tabla y es", Reserva2.equals(Reservabuscada));
			
			
			
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Reserva.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Reserva");
		}
		finally
		{
			alohAndes.limpiarAlohAndes();
    		alohAndes.cerrarUnidadPersistencia ();    		
		}
	}
    
    

    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de Reserva
     */
	@Test
	public void unicidadReservaTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del nombre del tipo de bebida");
			alohAndes = new AlohAndes ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Reserva incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Reserva incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los Reservas con la tabla vacía
			List <VOReserva> lista = alohAndes.darVORerserva();
			assertEquals ("No debe haber Reservas creados!!", 0, lista.size ());

			// Lectura de los Reservas con un tipo de bebida adicionado
			long idAlojamiento = 333;
			long idMiembro = 2123;
			String tipoId = "CC";
			int tiempoDias = 4;
			VOReserva Reserva1 = alohAndes.adicionarReserva(idAlojamiento, idMiembro, tipoId, tiempoDias);
			lista = alohAndes.darVORerserva();
			assertEquals ("Debe haber un tipo de bebida creado !!", 1, lista.size ());

			VOReserva Reserva2 = alohAndes.adicionarReserva(idAlojamiento, idMiembro, tipoId, tiempoDias);
			assertNull ("No puede adicionar dos Reservas con el mismo nombre !!", Reserva2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Reserva.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Reserva");
		}    				
		finally
		{
			alohAndes.limpiarAlohAndes();
    		alohAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "ReservaTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
