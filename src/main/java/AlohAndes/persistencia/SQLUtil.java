package AlohAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval_idAlojamiento (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqIdAlojamiento() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval_idReserva (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqIdReserva() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}
	
	
	
	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas ALOJAMIENTOS, OPERADORES, SERVICIOS,
	 * 		   VIVIENDA COMUNIDAD, HABITACIONES, MIEMBRO COMUNIDAD UNIVERSITARIA, RESERVA y  APARTAMENTOS. respectivamente
	 */
	public long [] limpiarAlohAndes (PersistenceManager pm)
	{
        Query qAlojamientos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamientos());          
        Query qoperadores = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperadores ());
        Query qServicios = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicios ());
        Query qViviendaComunidad = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendaComunidad ());
        Query qHabitaciones = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitaciones ());
        Query qmiembroComunidad = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMiemCoUniv ());
        Query qReserva = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservas ());
        Query qApartamentos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartamentos ());

        long alojamientosEliminados = (long) qAlojamientos.executeUnique ();
        long operadoresEliminados = (long) qoperadores.executeUnique ();
        long serviciosEliminadas = (long) qServicios.executeUnique ();
        long vieviendaComunidadEliminadas = (long) qViviendaComunidad.executeUnique ();
        long habitacionesEliminados = (long) qHabitaciones.executeUnique ();
        long miembroEliminados = (long) qmiembroComunidad.executeUnique ();
        long reservaEliminados = (long) qReserva.executeUnique ();
        long apartamentosEliminados = (long) qApartamentos.executeUnique ();
        return new long[] {alojamientosEliminados, operadoresEliminados, serviciosEliminadas, vieviendaComunidadEliminadas, 
        		habitacionesEliminados, miembroEliminados, reservaEliminados, apartamentosEliminados };
	}
	
}
