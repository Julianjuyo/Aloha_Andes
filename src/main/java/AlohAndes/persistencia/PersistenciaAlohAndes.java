package AlohAndes.persistencia;

import AlohAndes.negocio.Alojamiento;
import AlohAndes.negocio.Apartamento;
import AlohAndes.negocio.Habitacion;
import AlohAndes.negocio.MiembroComunidadUniversitaria;
import AlohAndes.negocio.Operador;
import AlohAndes.negocio.Reserva;
import AlohAndes.negocio.ViviendaComunidad;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import org.apache.log4j.Logger;

import javax.jdo.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PersistenciaAlohAndes
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List<String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;

	/**
	 * Atributo para el acceso a la tabla OPERADORES de la base de datos
	 */
	private SQLOperador sqlOperador;

	/**
	 * Atributo para el acceso a la tabla ALOJAMIENTOS de la base de datos
	 */
	private SQLAlojamiento sqlAlojamiento;

	/**
	 * Atributo para el acceso a la tabla APARTAMENTOS de la base de datos
	 */
	private SQLApartamento sqlApartamento;

	/**
	 * Atributo para el acceso a la tabla HABITACIONES de la base de datos
	 */
	private SQLHabitacion sqlHabitacion;

	/**
	 * Atributo para el acceso a la tabla VIVIENDACMUNIDAD de la base de datos
	 */
	private SQLViviendaComunidad sqlViviendaComunidad;

	/**
	 * Atributo para el acceso a la tabla MIEM_CO_UNIV de la base de datos
	 */
	private SQLMiemCoUniv sqlMiemCoUniv;

	/**
	 * Atributo para el acceso a la tabla RESERVAS de la base de datos
	 */
	private SQLReserva sqlReserva;

	/**
	 * Atributo para el acceso a la tabla SERVICIOS de la base de datos
	 */
	private SQLServicio sqlServicio;

	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("AlohAndes");
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String>();
		//Secuencia de id de los alojamientos
		tablas.add ("ID_ALOJAMIENTO");
		//Secuencia de id de las reservas
		tablas.add ("ID_RESERVA");
		//Tablas de la base de datos
		tablas.add ("OPERADORES");
		tablas.add ("ALOJAMIENTOS");
		tablas.add ("APARTAMENTOS");
		tablas.add ("HABITACIONES");
		tablas.add ("VIVIENDACMUNIDAD");
		tablas.add ("MIEM_CO_UNIV");
		tablas.add ("RESERVAS");
		tablas.add("SERVICIOS");
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohAndes ();
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlOperador = new SQLOperador(this);
		sqlAlojamiento = new SQLAlojamiento(this);
		sqlApartamento = new SQLApartamento(this);
		sqlHabitacion = new SQLHabitacion(this);
		sqlViviendaComunidad = new SQLViviendaComunidad(this);
		sqlMiemCoUniv = new SQLMiemCoUniv (this);
		sqlReserva = new SQLReserva(this);
		sqlServicio = new SQLServicio(this);
		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de idAlojamiento
	 */
	public String darSeqIdAlojamiento ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de idReserva
	 */
	public String darSeqIdReserva ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de OPERADORES de AlohAndes
	 */
	public String darTablaOperadores ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de ALOJAMIENTOS de AlohAndes
	 */
	public String darTablaAlojamientos ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de APARTAMENTOS de AlohAndes
	 */
	public String darTablaApartamentos ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de HABITACIONES de AlohAndes
	 */
	public String darTablaHabitaciones ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de VIVIENDACOMUNIDAD de AlohAndes
	 */
	public String darTablaViviendaComunidad ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de MIEM_CO_UNIV de AlohAndes
	 */
	public String darTablaMiemCoUniv ()
	{
		return tablas.get (7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de RESERVAS de AlohAndes
	 */
	public String darTablaReservas ()
	{
		return tablas.get (8);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de SERVICIOS de AlohAndes
	 */
	public String darTablaServicios ()
	{
		return tablas.get (9);
	}

	/**
	 * Transacción para el generador de secuencia de idAlojamiento
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de idAlojamiento
	 */
	private long nextval_idAlojamiento ()
	{
		long resp = sqlUtil.nextval_idAlojamiento (pmf.getPersistenceManager());
		log.trace ("Generando secuencia idAlojamiento: " + resp);
		return resp;
	}

	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval_idReserva()
	{
		long resp = sqlUtil.nextval_idReserva (pmf.getPersistenceManager());
		log.trace ("Generando secuenci idReserva: " + resp);
		return resp;
	}

	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval_idOperador()
	{
		long resp = sqlUtil.nextval_idReserva (pmf.getPersistenceManager());
		log.trace ("Generando secuenci idReserva: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e)
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los OPERADORES
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Operadores
	 * Adiciona entradas al log de la aplicación
	 * @param idOperador - El identificador del alojamiento que se desea Operadorr 
	 * @param nombre - El identificador del miembro que desea realizar la Operador 
	 * @param registroDeCamaraYComercio -  si esta subscrito o tiene el certitificado.
	 * @param SuperIntendensiaDeTurismo - si esta subscrito o tiene el certitificado.
	 * @param calidad - La calidad
	 * @param tipoOperador - El tipo operador 
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public Operador adicionarOperador( String nombre, Boolean  registroDeCamaraYComercio, Boolean SuperIntendensiaDeTurismo, int  calidad, String  tipoOperador)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long idOperador = nextval_idReserva();

			String registroDeCamaraYComercioCHAR ="";
			if(registroDeCamaraYComercio== true ){
				registroDeCamaraYComercioCHAR = "Y";
			}
			else{
				registroDeCamaraYComercioCHAR = "N";	
			}

			String SuperIntendensiaDeTurismoChar ="";
			if(SuperIntendensiaDeTurismo == true ){
				SuperIntendensiaDeTurismoChar = "Y";
			}
			else{
				SuperIntendensiaDeTurismoChar = "N";	
			}

			long tuplasInsertadas = sqlOperador.adicionarOperador(pm, idOperador , nombre, registroDeCamaraYComercioCHAR, SuperIntendensiaDeTurismoChar, calidad, tipoOperador);
			tx.commit();

			log.trace ("Inserción Operador: " + idOperador + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Operador(idOperador, nombre, registroDeCamaraYComercio, SuperIntendensiaDeTurismo, calidad, tipoOperador);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Operadores, dado el id del operador
	 * Adiciona entradas al log de la aplicación
	 * @param idReserva - El id de la reserva
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarOperadorPorId (long idoperador)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlOperador.eliminarOperadorPorId(pm, idoperador);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	/**
	 * Método que consulta todas las tuplas en la tabla OPERADORES
	 * @return La lista de objetos Operador, construidos con base en las tuplas de la tabla OPERADORES
	 */
	public List<Operador> darOperadores ()
	{
		return sqlOperador.darOperadores (pmf.getPersistenceManager());
	}

	//    /**
	//     * Método que consulta todas las tuplas en la tabla OPERADORES que tienen el nombre dado
	//     * @param nombre - El nombre del de operador
	//     * @return La lista de objetos Operador, construidos con base en las tuplas de la tabla OPERADORES
	//     */
	//    public Operador darOperadorPorNombre (String nombre)
	//    {
	//        return sqlOperador.darOperadorPorNombre (pmf.getPersistenceManager(), nombre);
	//    }


	/**
	 * Método que consulta todas las tuplas en la tabla OPERADORES con un identificador dado
	 * @param idOperador - El identificador del operador
	 * @return El objeto Operador, construido con base en las tuplas de la tabla OPERADORES con el identificador dado
	 */
	public Operador darOperadorPorId (long idOperador)
	{
		return sqlOperador.darOperadorPorId (pmf.getPersistenceManager(), idOperador);
	}

	/* ****************************************************************
	 * 			Métodos para manejar las RESERVAS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla RESERVAS
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea reservar el alojamiento
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public Reserva adicionarReserva(long idAlojamiento, long idMiembro, String tipoId, int tiempoDias)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long idReserva = nextval_idReserva();
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			String diaReserva = dateFormat.format(date);
			long tuplasInsertadas = sqlReserva.adicionarReserva(pm, idReserva, idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);
			tx.commit();

			log.trace ("Inserción reserva: " + idReserva + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Reserva (idReserva,idAlojamiento, idMiembro, tipoId, date, tiempoDias);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla RESERVAS, dado el id de la reserva
	 * Adiciona entradas al log de la aplicación
	 * @param idReserva - El id de la reserva
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReservaPorId (long idReserva)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReserva.eliminarReservaPorId(pm, idReserva);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que consulta todas las tuplas en la Reserva
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Reserva> darReservas ()
	{
		return sqlReserva.darReservas (pmf.getPersistenceManager());
	}


	/**
	 * Método que consulta la tupla en la tabla RESERVAS que tiene el identificador dado
	 * @param idReserva - El identificador de la reserva
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public Reserva darReservaPorId (long idReserva)
	{
		return (Reserva) sqlReserva.darReservaPorId (pmf.getPersistenceManager(), idReserva);
	}

	/**
	 * Método que consulta la tupla en la tabla RESERVAS que tiene el alojamiento dado
	 * @param idAlojamiento - El identificador del alojamiento
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public Reserva darReservaPorIdAlojamiento (long idAlojamiento)
	{
		return (Reserva) sqlReserva.darReservaPorIdAlojamiento (pmf.getPersistenceManager(), idAlojamiento);
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ALOJAMIENTOS
	 *****************************************************************/


	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Alojamientos
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea reservar el alojamiento
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public Alojamiento adicionarAlojamiento(Boolean habilitada, Date  fechaInicio, Date fechaFin)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long idAlojamiento = nextval_idAlojamiento();
			Date date = Calendar.getInstance().getTime();


			String habilitadoString ;
			if(habilitada == true)
			{
				habilitadoString= "Y";
			}
			else
			{
				habilitadoString= "N";
			}

			long tuplasInsertadas = sqlAlojamiento.adicionarAlojamiento(pm, idAlojamiento, habilitadoString, fechaInicio, fechaFin);
			tx.commit();

			log.trace ("Inserción Alojamiento: " + idAlojamiento + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Alojamiento (idAlojamiento, habilitada, fechaInicio, fechaFin);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la Alojamientos
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla Alojamientos
	 */
	public List<Alojamiento> darAlojamientos ()
	{
		return sqlAlojamiento.darAlojamientos (pmf.getPersistenceManager());
	}
	
	/**
	 * Método que consulta la tupla en la tabla Aloajmiento que tiene el identificador dado
	 * @param idReserva - El identificador de la Alojamiento
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public Alojamiento darAlojamientoPorId (long idAlojamiento)
	{
		return (Alojamiento) sqlAlojamiento.darAlojamientoPorId(pmf.getPersistenceManager(), idAlojamiento);
	}
	
	/**
	 * Método que consulta la tupla en la tabla Aloajmiento que tiene el identificador dado
	 * @param idReserva - El identificador de la Alojamiento
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public void ActualizarAlojamientoPorId (long idAlojamiento,  String habilitado, Date  fechaInicio, Date fechaFin )
	{
		
		sqlAlojamiento.darAlojamientoPorId(pmf.getPersistenceManager(), idAlojamiento);
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ALOJAMENTO, dado el id del alojamiento. El alojamento no debe estar Alojamientodo
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El id del alojamiento
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción o el alojamiento está Alojamientodo
	 */
	public long eliminarAlojamiento (long idAlojamiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		Reserva reserva = darReservaPorIdAlojamiento(idAlojamiento);
		if(reserva != null)
		{
			try {
				tx.begin();
				long resp = sqlAlojamiento.eliminarAlojamientoPorId(pm, idAlojamiento);
				tx.commit();

				return resp;
			} catch (Exception e) {
				//        	e.printStackTrace();
				log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
				return -1;
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
		else
		{
			return -1;
		}
	}


	/* ****************************************************************
	 * 			Métodos para manejar los Miembros de la comunidad
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla MiembroComunidadUniversitaria
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea reservar el alojamiento
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public MiembroComunidadUniversitaria adicionarMiembroComunidadUniversitaria(long id, String  tipoID, String nombre, String  tipoMiembroComunidadUniversitaria)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			//VERIFICAR LAS REGLAS DE QUE SEA DE TAL TIPO

			long tuplasInsertadas = sqlMiemCoUniv.adicionarMiembroComunidadUniversitaria(pm, id,tipoID, nombre ,tipoMiembroComunidadUniversitaria);
			tx.commit();

			log.trace ("Inserción MiembroComunidadUniversitaria: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
			return new MiembroComunidadUniversitaria (id, tipoID, nombre, tipoMiembroComunidadUniversitaria);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}



	/**
	 * Método que consulta todas las tuplas en la MiembroComunidadUniversitaria
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<MiembroComunidadUniversitaria> darMiembrosComunidadUniversitaria ()
	{
		return sqlMiemCoUniv.darMiembrosComunidadUniversitaria (pmf.getPersistenceManager());
	}




	/* ****************************************************************
	 * 		Métodos para manejar Habitaciones
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Habitacion
	 * Adiciona entradas al log de la aplicación
	 * @param idHabitacion - El identificador del Habitacion que se desea reservar (Debe existir en la tabla HabitacionS)
	 * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea reservar el Habitacion
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public Habitacion adicionarHabitacion(Boolean habilitada, Date  fechaInicio, Date fechaFin, long idOperador, String direccion, Double precio, String numHabitacion, String tipoHabitacion, String tipoOperadorHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();


			Alojamiento Alojamiento1 = adicionarAlojamiento(habilitada, fechaInicio, fechaFin);

			if( darOperadorPorId(idOperador) != null)
			{


				long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, Alojamiento1.getId(), idOperador, direccion, precio, numHabitacion, tipoHabitacion, tipoOperadorHabitacion);
				tx.commit();

				log.trace ("Inserción Habitacion: " + Alojamiento1.getId() + ": " + tuplasInsertadas + " tuplas insertadas");
				return new Habitacion(Alojamiento1.getId(), idOperador, direccion, precio, numHabitacion, tipoHabitacion, tipoOperadorHabitacion);
			}
			else {
				return null;
			}



		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ALOJAMENTO, dado el id del Habitacion. El alojamento no debe estar Habitaciondo
	 * Adiciona entradas al log de la aplicación
	 * @param idHabitacion - El id del Habitacion
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción o el Habitacion está Habitaciondo
	 */

	public long eliminarHabitacionPorId (long idAlojaHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long eliminar = eliminarAlojamiento(idAlojaHabitacion);
			
			long resp = sqlHabitacion.eliminarHabitacionPorId (pm, eliminar);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la habiutaciones
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Habitacion> darHabitaciones ()
	{
		return sqlHabitacion.darHabitaciones(pmf.getPersistenceManager());
	}
	
	/**
	 * Método que consulta la tupla en la tabla Habitacion que tiene el identificador dado
	 * @param idReserva - El identificador de la Habitacion
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public Habitacion darHabitacionPorId (long idHabitacion)
	{
		return (Habitacion) sqlHabitacion.darHabitacionPorId(pmf.getPersistenceManager(), idHabitacion);
	}


	/* ****************************************************************
	 * 		Métodos para manejar Viviendas comunidad
	 *****************************************************************/


	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla ViviendaComunidad
	 * Adiciona entradas al log de la aplicación
	 * @param idViviendaComunidad - El identificador del ViviendaComunidad que se desea reservar (Debe existir en la tabla ViviendaComunidadS)
	 * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea reservar el ViviendaComunidad
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public ViviendaComunidad adicionarViviendaComunidad(Boolean habilitada, Date  fechaInicio, Date fechaFin, long idOperador, String direccion, Double precio, int numHabitaciones, Boolean menaje, Boolean seguroArrendatario, String caractSeguro)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();


			Alojamiento Alojamiento1 = adicionarAlojamiento(habilitada, fechaInicio, fechaFin);

			if( darOperadorPorId(idOperador) != null)
			{
				String menajeString ;
				if(menaje == true){
					menajeString= "Y";}
				else{
					menajeString= "N";}

				String seguroArrendatarioString ;
				if(seguroArrendatario == true){
					seguroArrendatarioString= "Y";}
				else{
					seguroArrendatarioString= "N";}
				
				long tuplasInsertadas = sqlViviendaComunidad.adicionarViviendaComunidad(pm, Alojamiento1.getId(), idOperador, direccion, precio, numHabitaciones, menajeString, seguroArrendatarioString, caractSeguro);
				tx.commit();

				log.trace ("Inserción ViviendaComunidad: " + Alojamiento1.getId() + ": " + tuplasInsertadas + " tuplas insertadas");
				return new ViviendaComunidad(Alojamiento1.getId(), idOperador, direccion, precio, numHabitaciones, menaje, seguroArrendatario, caractSeguro);
			}
			else {
				return null;
			}



		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ALOJAMENTO, dado el id del ViviendaComunidad. El alojamento no debe estar ViviendaComunidaddo
	 * Adiciona entradas al log de la aplicación
	 * @param idViviendaComunidad - El id del ViviendaComunidad
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción o el ViviendaComunidad está ViviendaComunidaddo
	 */

	public long eliminarViviendaComunidadPorId (long idAlojaViviendaComunidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long eliminar = eliminarAlojamiento(idAlojaViviendaComunidad);
			
			long resp = sqlViviendaComunidad.eliminarViviendaComunidadPorId(pm, eliminar);
			
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la habiutaciones
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<ViviendaComunidad> darViviendaComunidades ()
	{
		return sqlViviendaComunidad.darViviendaComunidades(pmf.getPersistenceManager());
	}

	/**
	 * Método que consulta la tupla en la tabla ViviendaComunidad que tiene el identificador dado
	 * @param idReserva - El identificador de la ViviendaComunidad
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public ViviendaComunidad darViviendaComunidadPorId (long idViviendaComunidad)
	{
		return (ViviendaComunidad) sqlViviendaComunidad.darViviendaComunidadPorId(pmf.getPersistenceManager(), idViviendaComunidad);
	}



	/* ****************************************************************
	 * 		Métodos para manejar Apartamentos
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Apartamento
	 * Adiciona entradas al log de la aplicación
	 * @param idApartamento - El identificador del Apartamento que se desea reservar (Debe existir en la tabla ApartamentoS)
	 * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea reservar el Apartamento
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public Apartamento adicionarApartamento(Boolean habilitada, Date  fechaInicio, Date fechaFin, String direccion, Double precio, long dueno, Double valorAdmin, Boolean amobaldo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();


			Alojamiento Alojamiento1 = adicionarAlojamiento(habilitada, fechaInicio, fechaFin);

			if( darOperadorPorId(dueno) != null)
			{
				String menajeString ;
				if(amobaldo == true){
					menajeString= "Y";}
				else{
					menajeString= "N";}

				
				long tuplasInsertadas = sqlApartamento.adicionarApartamento(pm, Alojamiento1.getId(), direccion, precio, dueno, valorAdmin, amobaldo);
				tx.commit();

				log.trace ("Inserción Apartamento: " + Alojamiento1.getId() + ": " + tuplasInsertadas + " tuplas insertadas");
				return new Apartamento(Alojamiento1.getId(), direccion, precio, dueno, valorAdmin, amobaldo);
						
			}
			else {
				return null;
			}



		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla ALOJAMENTO, dado el id del Apartamento. El alojamento no debe estar Apartamentodo
	 * Adiciona entradas al log de la aplicación
	 * @param idApartamento - El id del Apartamento
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción o el Apartamento está Apartamentodo
	 */

	public long eliminarApartamentoPorId (long idAlojaApartamento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long eliminar = eliminarAlojamiento(idAlojaApartamento);
			
			long resp = sqlApartamento.eliminarApartamentoPorId (pm, eliminar);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la habiutaciones
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Apartamento> darApartamentos ()
	{
		return sqlApartamento.darApartamentos(pmf.getPersistenceManager());
	}
	
	/**
	 * Método que consulta la tupla en la tabla Apartamento que tiene el identificador dado
	 * @param idReserva - El identificador de la Apartamento
	 * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
	 */
	public Apartamento darApartamentoPorId (long idApartamento)
	{
		return (Apartamento) sqlApartamento.darApartamentoPorId(pmf.getPersistenceManager(), idApartamento);
	}





	/* ****************************************************************
	 * 		Requerimientos funcionales de consulta
	 *****************************************************************/


	/**
	 * RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO
	 * @return Una lista de arreglos de 2 números. El primero corresponde al identificador del operador, el segundo corresponde al numero de dinero ganado.
	 */
	public List<long []> darRFC1paraHabitaciones ()
	{
		List<long []> resp = new LinkedList<long []> ();

		List<Object []> tuplas =  sqlReserva.darRFC1paraHabitaciones(pmf.getPersistenceManager());
		for ( Object [] tupla : tuplas)
		{
			long [] datosResp = new long [2];
			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;
	}

	/**
	 * RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO
	 * @return Una lista de arreglos de 2 números. El primero corresponde al identificador del operador, el segundo corresponde al numero de dinero ganado.
	 */
	public List<long []> darRFC1paraApartamentos ()
	{
		List<long []> resp = new LinkedList<long []> ();

		List<Object []> tuplas =  sqlReserva.darRFC1paraApartamentos(pmf.getPersistenceManager());
		for ( Object [] tupla : tuplas)
		{
			long [] datosResp = new long [2];
			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;
	}

	/**
	 * RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO
	 * @return Una lista de arreglos de 2 números. El primero corresponde al identificador del operador, el segundo corresponde al numero de dinero ganado.
	 */
	public List<long []> darRFC1paraViviendaComunidad ()
	{
		List<long []> resp = new LinkedList<long []> ();

		List<Object []> tuplas =  sqlReserva.darRFC1paraViviendaComunidad(pmf.getPersistenceManager());
		for ( Object [] tupla : tuplas)
		{
			long [] datosResp = new long [2];
			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;
	}






	/**
	 * RFC2- MOSTRAR LAS 20 OFERTAS MÁS POPULARES
	 * @return Una lista de arreglos de 2 números. El primero corresponde al identificador del alojamiento, el segundo corresponde al numero de veces que ha sido reservado.
	 */
	public List<long []> darRFC2 ()
	{
		List<long []> resp = new LinkedList<long []> ();

		List<Object []> tuplas =  sqlReserva.darRFC2(pmf.getPersistenceManager());
		for ( Object [] tupla : tuplas)
		{
			long [] datosResp = new long [2];
			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;
	}


	//	/*
	//	 * RFC3- MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS
	//	 */
	//	public double RFC3 ()
	//	{
	//
	//	}
	//
	//	/*
	//	 * RFC4 - MOSTRAR LOS ALOJAMIENTOS DISPONIBLES EN UN RANGO DE FECHAS, QUE CUMPLEN CON UN CONJUNTO DE REQUERIMIENTOS DE DOTACIÓN O SERVICIOS. 
	//	 * 		  POR EJEMPLO, COCINETA, TV CABLE, INTERNET, SALA.
	//	 */
	//	public VOAlojamiento RFC4 ()
	//	{
	//
	//	}
	//
	//	/*
	//	 * RFC5- MOSTRAR EL USO DE ALOHANDES PARA CADA TIPO DE USUARIO DE LA COMUNIDAD
	//	 */
	//	public double RFC5 ()
	//	{
	//
	//	}
	//
	//	/*
	//	 * RFC6- MOSTRAR EL USO DE ALOHANDES PARA UN USUARIO DADO (NÚMERO DE NOCHES O MESES CONTRATADOS, CARACTERÍSTICAS DEL ALOJAMIENTO UTILIZADO, DINERO PAGADO.
	//	 */
	//	public double RFC6 ()
	//	{
	//
	//	}
	//
	//	/*
	//	 * RFC7- ANALIZAR LA OPERACIÓN DE ALOHANDES
	//	 * Para una unidad de tiempo definido (por ejemplo, semana o mes) y un tipo de alojamiento, considerando todo el tiempo de operación de AloHandes, 
	//	 * indicar cuáles fueron las fechas de mayor demanda (mayor cantidad de alojamientos ocupados), 
	//	 * las de mayores ingresos (mayor cantidad de dinero recibido) y las de menor ocupación.
	//	 */
	//	public double RFC7 ()
	//	{
	//
	//	}
	//
	//	/*
	//	 * RFC8- ENCONTRAR LOS CLIENTES FRECUENTES
	//		Para un alojamiento dado, encontrar la información de sus clientes frecuentes. 
	//		se considera frecuente a un cliente si ha utilizado (o tiene reservado) 
	//		ese alojamiento por lo menos en tres ocasiones o por lo menos 15 noches, durante todo el periodo de operación de AlohAndes.
	//	 */
	//	public double RFC8 ()
	//	{
	//
	//	}
	//
	//	/*
	//	 * RFC9- ENCONTRAR LAS OFERTAS DE ALOJAMIENTO QUE NO TIENEN MUCHA DEMANDA
	//	 * 	Encontrar las ofertas de alojamiento que no han recibido clientes en periodos superiores a 
	//	 * 1 mes, durante todo el periodo de operación de AlohAndes.
	//	 */
	//	public double RFC9 ()
	//	{
	//
	//	}



	/* ****************************************************************
	 * 			Método para limpiar la base de datos
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AlohaAndes
	 * 
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 8 números que indican el número de tuplas borradas en las tablas ALOJAMIENTOS, APARTAMENTOS, HABITACIONES, 
	 * MIEMBRO COMUNIDAD UNIVERSITARIA, OPERADORES, RESERVA y SERVIVICIOS, VIVIENDA COMUNIDAD, respectivamente
	 */
	public long [] limpiarAlohAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long [] resp = sqlUtil.limpiarAlohAndes (pm);
			tx.commit ();
			log.info ("Borrada la base de datos");
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1, -1, -1, -1, -1,-1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}

}
