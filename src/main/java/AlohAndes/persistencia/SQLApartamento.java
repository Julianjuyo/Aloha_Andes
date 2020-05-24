package AlohAndes.persistencia;

import AlohAndes.negocio.Reserva;
import AlohAndes.negocio.Apartamento;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLApartamento
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
    public SQLApartamento (PersistenciaAlohAndes pp)
    {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una Apartamento a la base de datos de AlohAndes
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El identificador del alojamiento que se desea Apartamentor (Debe existir en la tabla ALOJAMIENTOS)
     * @param idMiembro - El identificador del miembro que desea realizar la Apartamento (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tipoId - El tipo de identificación del miembro que desea realizar la Apartamento (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tiempoDias - El número de días que se desea Apartamentor el alojamiento
     * @return EL número de tuplas insertadas
     */
    public long adicionarApartamento (PersistenceManager pm, long idAlojamietno, String direccion, Double valorAdmin, Boolean amobaldo)

    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaApartamentos() + "(IDALOJAMIENTO, DIRECCION, VALORADMIN, AMOBLADO  ) values (?, ?, ?, ?)");
        q.setParameters(idAlojamietno, direccion, valorAdmin, amobaldo);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UNA Apartamento de la base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idApartamento - El id de la Apartamento
     * @return EL número de tuplas eliminadas
     */
    public long eliminarApartamentoPorId (PersistenceManager pm, long idApartamento)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartamentos() + " WHERE IDALOJAMIENTO = ?");
        q.setParameters(idApartamento);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Apartamento de la
     * base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idApartamento - El identificador de la Apartamento
     * @return El objeto Apartamento que tiene el identificador dado
     */
    public Apartamento darApartamentoPorId (PersistenceManager pm, long idApartamento)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartamentos() + " WHERE IDALOJAMIENTO = ?");
        q.setResultClass(Apartamento.class);
        q.setParameters(idApartamento);
        return (Apartamento) q.executeUnique();
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
	public List<Apartamento> darApartamentos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartamentos());
		q.setResultClass(Reserva.class);
		return (List<Apartamento>) q.executeList();
	}
}
