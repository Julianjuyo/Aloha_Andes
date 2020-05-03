package AlohAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import AlohAndes.negocio.Alojamiento;
import AlohAndes.negocio.Reserva;

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
     * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El id del alojamiento
     * @return EL número de tuplas eliminadas
     */
    public long eliminarAlojamiento (PersistenceManager pm, long idAlojamiento)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamientos () + " WHERE id = ?");
        q.setParameters(idAlojamiento);
        return (long) q.executeUnique();
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
    
 /*
    public long adicionarAlojamiento (PersistenceManager pm, long idAlojamiento, long idAlojamiento, long idMiembro, String tipoId, String diaAlojamiento, int tiempoDias)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darSeqIdAlojamiento () + "(NUMAlojamiento, IDALOJAMIENTO, IDMIEMBRO, TIPOID, DIAAlojamiento, TIEMPODIAS) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idAlojamiento, idAlojamiento, idMiembro, tipoId, diaAlojamiento, tiempoDias);
        return (long) q.executeUnique();
    }
    
  */
    
    
    
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
	
}
