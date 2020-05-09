package AlohAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import AlohAndes.negocio.Alojamiento;


public class SQLAlojamiento
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
	public SQLAlojamiento(PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Alojamiento a la base de datos de AlohAndes
	 * @param pm - El manejador de persistencia
	 * @param idAlojamiento - El identificador del alojamiento que se desea Alojamientor (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la Alojamiento (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la Alojamiento (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea Alojamientor el alojamiento
	 * @return EL número de tuplas insertadas
	 */


	public long adicionarAlojamiento (PersistenceManager pm, long idAlojamiento, String habilitada, Date fechaInicio, Date fechaFin)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darSeqIdAlojamiento () + "(ID, HABILITADO, FECHAINICIODES, FECHAFINDES) values (?, ?, ?, ?)");
		q.setParameters(idAlojamiento, habilitada, fechaInicio, fechaFin);
		return (long) q.executeUnique();
	}



	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idAlojamiento - El id del alojamiento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarAlojamientoPorId (PersistenceManager pm, long idAlojamiento)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamientos () + " WHERE id = ?");
		q.setParameters(idAlojamiento);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Alojamiento de la
	 * base de datos de AlohAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idAlojamiento - El identificador de la Alojamiento
	 * @return El objeto Alojamiento que tiene el identificador dado
	 */
	public Alojamiento darAlojamientoPorId (PersistenceManager pm, long idAlojamiento)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamientos() + " WHERE ID = ?");
		q.setResultClass(Alojamiento.class);
		q.setParameters(idAlojamiento);
		return (Alojamiento) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Alojamientos de la 
	 * base de datos de Alohaandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Alojamientos
	 */
	public List<Alojamiento> darAlojamientos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlojamientos());
		q.setResultClass(Alojamiento.class);
		return (List<Alojamiento>) q.executeList();
	}
	
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la habilitado de un alojamiento en la 
	 * base de datos de AlohaAndes
	 * @param pm - El manejador de persistencia
	 * @param idAlojamiento id del alojmienrto
	 * @param habilitado fecha de habiliatdo 
	 * @param fechaInicio fecha iniciap habilitados
	 * @param fechaFin fecha fin habilitado
	 * @return El número de tuplas modificadas
	 */
	public long cambiarhabilitadoDeUnAlojamiento (PersistenceManager pm, long id, String habilitado, Date fechaInicio, Date fechafin) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAlojamientos() + " SET HABILITADO = ?, FECHAINICIODES= ?, FECHAFINDES= ? WHERE ID = ?");
	     q.setParameters(habilitado, fechaInicio, fechafin, id);
	     return (long) q.executeUnique();      
	     
	}

}