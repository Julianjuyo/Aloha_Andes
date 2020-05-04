package AlohAndes.persistencia;

import AlohAndes.negocio.Habitacion;
import AlohAndes.negocio.Reserva;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLHabitacion
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
    public SQLHabitacion (PersistenciaAlohAndes pp)
    {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una Habitacion a la base de datos de AlohAndes
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El identificador del alojamiento que se desea Habitacionr (Debe existir en la tabla ALOJAMIENTOS)
     * @param idMiembro - El identificador del miembro que desea realizar la Habitacion (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tipoId - El tipo de identificación del miembro que desea realizar la Habitacion (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tiempoDias - El número de días que se desea Habitacionr el alojamiento
     * @return EL número de tuplas insertadas
     */
    public long adicionarHabitacion (PersistenceManager pm, long idAlojamietno, long idOperador, String direccion, Double precio, String numHabitacion, String tipoHabitacion, String tipoOperadorHabitacion)

    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitaciones() + "(IDALOJAMIENTO, IDOPERADOR, DIRECCION, PRECIO, NUMHABITACION, TIPOHABITACION, TIPOOPHAB ) values (?, ?, ?, ?, ?, ?,?)");
        q.setParameters(idAlojamietno, idOperador, direccion, precio, numHabitacion, tipoHabitacion, tipoOperadorHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UNA Habitacion de la base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabitacion - El id de la Habitacion
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionPorId (PersistenceManager pm, long idHabitacion)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitaciones() + " WHERE IDALOJAMIENTO = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Habitacion de la
     * base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idHabitacion - El identificador de la Habitacion
     * @return El objeto Habitacion que tiene el identificador dado
     */
    public Habitacion darHabitacionPorId (PersistenceManager pm, long idHabitacion)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitaciones () + " WHERE IDALOJAMIENTO = ?");
        q.setResultClass(Habitacion.class);
        q.setParameters(idHabitacion);
        return (Habitacion) q.executeUnique();
    }

//    /**
//     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA RESERVA de la
//     * base de datos de AlohAndes, por su alojamiento asociado
//     * @param pm - El manejador de persistencia
//     * @param idAlojamiento - El identificador del alojamiento asociado
//     * @return El objeto RESERVA que tiene el alojamiento asociado
//     */
//    public Reserva darReservaPorIdAlojamiento (PersistenceManager pm, long idAlojamiento)
//    {
//        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas () + " WHERE IDALOJAMIENTO = ?");
//        q.setResultClass(Reserva.class);
//        q.setParameters(idAlojamiento);
//        return (Reserva) q.executeUnique();
//    }
    
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS RESERVAS de la 
	 * base de datos de Alohaandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Reserva
	 */
	public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitaciones());
		q.setResultClass(Reserva.class);
		return (List<Habitacion>) q.executeList();
	}
    
}
