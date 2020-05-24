package AlohAndes.persistencia;

import AlohAndes.negocio.ViviendaComunidad;
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
     * Crea y ejecuta la sentencia SQL para adicionar una ViviendaComunidad a la base de datos de AlohAndes
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El identificador del alojamiento que se desea ViviendaComunidadr (Debe existir en la tabla ALOJAMIENTOS)
     * @param idMiembro - El identificador del miembro que desea realizar la ViviendaComunidad (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tipoId - El tipo de identificación del miembro que desea realizar la ViviendaComunidad (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tiempoDias - El número de días que se desea ViviendaComunidadr el alojamiento
     * @return EL número de tuplas insertadas
     */
    public long adicionarViviendaComunidad (PersistenceManager pm, long idAlojamietno, String direccion, int numHabitaciones, String menajeString, String seguroArrendatarioString, String caractSeguro)

    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaViviendaComunidad() + "(IDALOJAMIENTO, IDOPERADOR, DIRECCION, PRECIO, NUMHABITACIONES, MENAJE, SEGUROARRIENDO,CARACTSEGURO  ) values (?, ?, ?, ?, ?, ?,?,?)");
        q.setParameters(idAlojamietno, direccion, numHabitaciones, menajeString, seguroArrendatarioString, caractSeguro);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UNA ViviendaComunidad de la base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idViviendaComunidad - El id de la ViviendaComunidad
     * @return EL número de tuplas eliminadas
     */
    public long eliminarViviendaComunidadPorId (PersistenceManager pm, long idViviendaComunidad)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendaComunidad() + " WHERE IDALOJAMIENTO = ?");
        q.setParameters(idViviendaComunidad);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA ViviendaComunidad de la
     * base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idViviendaComunidad - El identificador de la ViviendaComunidad
     * @return El objeto ViviendaComunidad que tiene el identificador dado
     */
    public ViviendaComunidad darViviendaComunidadPorId (PersistenceManager pm, long idViviendaComunidad)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaViviendaComunidad() + " WHERE IDALOJAMIENTO = ?");
        q.setResultClass(ViviendaComunidad.class);
        q.setParameters(idViviendaComunidad);
        return (ViviendaComunidad) q.executeUnique();
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
	public List<ViviendaComunidad> darViviendaComunidades (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaViviendaComunidad());
		q.setResultClass(Reserva.class);
		return (List<ViviendaComunidad>) q.executeList();
	}
}
