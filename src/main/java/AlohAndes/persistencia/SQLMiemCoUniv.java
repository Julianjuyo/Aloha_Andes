package AlohAndes.persistencia;

import AlohAndes.negocio.MiembroComunidadUniversitaria;
import AlohAndes.negocio.Reserva;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLMiemCoUniv
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
    public SQLMiemCoUniv (PersistenciaAlohAndes pp)
    {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una MIEMBRO DE L COMUNIDAD UNIVERSITARIA a la base de datos de AlohAndes
     * @param pm - El manejador de persistencia
     * @param id - El identificador del miembro
     * @param tipoID - EEl tipo de identificación del miembro
     * @param tipoMiembroComunidadUniversitaria - El tipo de miembro 
     * @return EL número de tuplas insertadas
     */
    public long adicionarMiembroComunidadUniversitaria (PersistenceManager pm, long id, String  tipoID, String nombre, String  tipoMiembroComunidadUniversitaria)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaMiemCoUniv() + "(ID, TIPOID, NOMBRE, TIPOMIEMBRO) values (?, ?, ?, ?)");
        q.setParameters(id, tipoID, nombre, tipoMiembroComunidadUniversitaria);
        return (long) q.executeUnique();
    }

//    /**
//     * Crea y ejecuta la sentencia SQL para eliminar UNA RESERVA de la base de datos de AlohAndes, por su identificador
//     * @param pm - El manejador de persistencia
//     * @param idReserva - El id de la reserva
//     * @return EL número de tuplas eliminadas
//     */
//    public long eliminarReservaPorId (PersistenceManager pm, long idReserva)
//    {
//        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservas () + " WHERE NUMRESERVA = ?");
//        q.setParameters(idReserva);
//        return (long) q.executeUnique();
//    }

//    /**
//     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA RESERVA de la
//     * base de datos de AlohAndes, por su identificador
//     * @param pm - El manejador de persistencia
//     * @param idReserva - El identificador de la reserva
//     * @return El objeto RESERVA que tiene el identificador dado
//     */
//    public Reserva darReservaPorId (PersistenceManager pm, long idReserva)
//    {
//        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas () + " WHERE NUMRESERVA = ?");
//        q.setResultClass(Reserva.class);
//        q.setParameters(idReserva);
//        return (Reserva) q.executeUnique();
//    }

    
    
    
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS RESERVAS de la 
	 * base de datos de Alohaandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Reserva
	 */
	public List<MiembroComunidadUniversitaria> darMiembrosComunidadUniversitaria (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaMiemCoUniv() );
		q.setResultClass(Reserva.class);
		return (List<MiembroComunidadUniversitaria>) q.executeList();
	}
    
}
