/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: AlohAndes Uniandes
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
import AlohAndes.negocio.VOMiembroComunidadUniversitaria;

/**
 * Clase con los métdos de prueba de funcionalidad sobre miembroComunidad
 * @author Germán Bravo
 *
 */
public class MiemCoUnivTest
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(MiemCoUnivTest.class.getName());
	
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
    private AlohAndes AlohAndes;
	
    /* ****************************************************************
	 * 			Métodos de prueba para la tabla miembroComunidad - Creación y borrado
	 *****************************************************************/
	/**
	 * Método que prueba las operaciones sobre la tabla miembroComunidad
	 * 1. Adicionar un tipo de bebida
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un tipo de bebida por su identificador
	 * 4. Borrar un tipo de bebida por su nombre
	 */
    @Test
	public void CRDmiembroComunidadTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre miembroComunidad");
			AlohAndes = new AlohAndes ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de miembroComunidad incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de miembroComunidad incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de AlohAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los miembroscon la tabla vacía
			List <VOMiembroComunidadUniversitaria> lista = AlohAndes.darVOMiembroComunidadUniversitaria();
			assertEquals ("No debe haber miembroscreados!!", 0, lista.size ());

			// Lectura de los miembroscon un tipo de bebida adicionado
			long id = 88921;
			String tipoID = "CC";
			String nombre = "juan";
			String tipoMiembroComunidadUniversitaria = "Estudiante";
			
			VOMiembroComunidadUniversitaria miembroComunidad1 = AlohAndes.adicionarMiembroComunidadUniversitaria(id, tipoID, nombre, tipoMiembroComunidadUniversitaria);
			lista = AlohAndes.darVOMiembroComunidadUniversitaria();
			assertEquals ("Debe haber un tipo de bebida creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", miembroComunidad1, lista.get (0));

			
			// Lectura de los miembroscon dos miembros adicionados
			long id2 = 84921;
			String tipoID2 = "CC";
			String nombre2 = "Andres";
			String tipoMiembroComunidadUniversitaria2 = "Profesor";
			VOMiembroComunidadUniversitaria miembroComunidad2 = AlohAndes.adicionarMiembroComunidadUniversitaria(id2, tipoID2, nombre2, tipoMiembroComunidadUniversitaria2);
			lista = AlohAndes.darVOMiembroComunidadUniversitaria();
			assertEquals ("Debe haber dos miembroscreados !!", 2, lista.size ());
			assertTrue ("El primer tipo de bebida adicionado debe estar en la tabla", miembroComunidad1.equals (lista.get (0)) || miembroComunidad1.equals (lista.get (1)));
			assertTrue ("El segundo tipo de bebida adicionado debe estar en la tabla", miembroComunidad2.equals (lista.get (0)) || miembroComunidad2.equals (lista.get (1)));

			
//			// Prueba de eliminación de un tipo de bebida, dado su identificador
//			long tbEliminados = AlohAndes.eli (miembroComunidad1.getId ());
//			assertEquals ("Debe haberse eliminado un tipo de bebida !!", 1, tbEliminados);
//			lista = AlohAndes.darVOMiembroComunidadUniversitaria();
//			assertEquals ("Debe haber un solo tipo de bebida !!", 1, lista.size ());
//			assertFalse ("El primer tipo de bebida adicionado NO debe estar en la tabla", miembroComunidad1.equals (lista.get (0)));
//			assertTrue ("El segundo tipo de bebida adicionado debe estar en la tabla", miembroComunidad2.equals (lista.get (0)));
			

		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla miembroComunidad.\n";
			msg += "Revise el log de AlohAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla miembroComunidad");
		}
		finally
		{
			AlohAndes.limpiarAlohAndes ();
    		AlohAndes.cerrarUnidadPersistencia ();    		
		}
	}

    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de miembroComunidad
     */
	@Test
	public void unicidadmiembroComunidadTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del nombre del tipo de bebida");
			AlohAndes = new AlohAndes ();
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de miembroComunidad incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de miembroComunidad incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de AlohAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los miembroscon la tabla vacía
			List <VOMiembroComunidadUniversitaria> lista = AlohAndes.darVOMiembroComunidadUniversitaria();
			assertEquals ("No debe haber miembroscreados!!", 0, lista.size ());

			// Lectura de los miembro con un miembro adicionado
			long id = 88921;
			String tipoID = "CC";
			String nombre = "juan";
			String tipoMiembroComunidadUniversitaria = "Estudiante";
			
			VOMiembroComunidadUniversitaria miembroComunidad1 = AlohAndes.adicionarMiembroComunidadUniversitaria(id, tipoID, nombre, tipoMiembroComunidadUniversitaria);
			lista = AlohAndes.darVOMiembroComunidadUniversitaria();
			assertEquals ("Debe haber un tipo de bebida creado !!", 1, lista.size ());

			
			long id2 = 88921;
			String tipoID2 = "CC";
			String nombre2 = "Andres";
			String tipoMiembroComunidadUniversitaria2 = "Profesor";
			VOMiembroComunidadUniversitaria miembroComunidad2 = AlohAndes.adicionarMiembroComunidadUniversitaria(id2, tipoID2, nombre2, tipoMiembroComunidadUniversitaria2);
			assertNull ("No puede adicionar dos miembroscon el mismo id y tipo de id !!", miembroComunidad2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla miembroComunidad.\n";
			msg += "Revise el log de AlohAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla miembroComunidad");
		}    				
		finally
		{
			AlohAndes.limpiarAlohAndes ();
    		AlohAndes.cerrarUnidadPersistencia ();    		
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
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "miembroComunidadTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
