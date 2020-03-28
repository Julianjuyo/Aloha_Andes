package AlohAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
}
