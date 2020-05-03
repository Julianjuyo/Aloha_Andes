package AlohAndes.persistencia;

import AlohAndes.negocio.Reserva;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLViviendaComunidad
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
    public SQLViviendaComunidad (PersistenciaAlohAndes pp)
    {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una RESERVA a la base de datos de AlohAndes
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
     * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tiempoDias - El número de días que se desea reservar el alojamiento
     * @return EL número de tuplas insertadas
     */
    public long adicionarReserva (PersistenceManager pm, long idReserva, long idAlojamiento, long idMiembro, String tipoId, String diaReserva, int tiempoDias)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darSeqIdReserva () + "(NUMRESERVA, IDALOJAMIENTO, IDMIEMBRO, TIPOID, DIARESERVA, TIEMPODIAS) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idReserva, idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UNA RESERVA de la base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idReserva - El id de la reserva
     * @return EL número de tuplas eliminadas
     */
    public long eliminarReservaPorId (PersistenceManager pm, long idReserva)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservas () + " WHERE NUMRESERVA = ?");
        q.setParameters(idReserva);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA RESERVA de la
     * base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idReserva - El identificador de la reserva
     * @return El objeto RESERVA que tiene el identificador dado
     */
    public Reserva darReservaPorId (PersistenceManager pm, long idReserva)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas () + " WHERE NUMRESERVA = ?");
        q.setResultClass(Reserva.class);
        q.setParameters(idReserva);
        return (Reserva) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA RESERVA de la
     * base de datos de AlohAndes, por su alojamiento asociado
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El identificador del alojamiento asociado
     * @return El objeto RESERVA que tiene el alojamiento asociado
     */
    public Reserva darReservaPorIdAlojamiento (PersistenceManager pm, long idAlojamiento)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas () + " WHERE IDALOJAMIENTO = ?");
        q.setResultClass(Reserva.class);
        q.setParameters(idAlojamiento);
        return (Reserva) q.executeUnique();
    }
    
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS RESERVAS de la 
	 * base de datos de Alohaandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Reserva
	 */
	public List<Reserva> darReservas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas  ());
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}
    
}
