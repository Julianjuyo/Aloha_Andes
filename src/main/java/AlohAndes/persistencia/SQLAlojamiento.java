package AlohAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

public class SQLAlojamiento
{
    /* ****************************************************************
     * 			Constantes
     *****************************************************************/
    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
     * Se renombra acá para facilitar la escritura de las sentencias
     */
    private final static String SQL = PersistenciaParranderos.SQL;

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
     * Crea y ejecuta la sentencia SQL para adicionar un Alojamiento a la base de datos de Parranderos
     * @param pm - El manejador de persistencia
     * @return El número de tuplas insertadas
     */
    public long adicionarAlojamiento (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAlojamientos () + "(id) values (?)");
        q.setParameters(idBar, nombre, ciudad, presupuesto, sedes);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su nombre
     * @param pm - El manejador de persistencia
     * @param nombreBar - El nombre del bar
     * @return EL número de tuplas eliminadas
     */
    public long eliminarBaresPorNombre (PersistenceManager pm, String nombreBar)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE nombre = ?");
        q.setParameters(nombreBar);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
     * @param pm - El manejador de persistencia
     * @param idBar - El identificador del bar
     * @return EL número de tuplas eliminadas
     */
    public long eliminarBarPorId (PersistenceManager pm, long idBar)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE id = ?");
        q.setParameters(idBar);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la
     * base de datos de Parranderos, por su identificador
     * @param pm - El manejador de persistencia
     * @param idBar - El identificador del bar
     * @return El objeto BAR que tiene el identificador dado
     */
    public Bar darBarPorId (PersistenceManager pm, long idBar)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE id = ?");
        q.setResultClass(Bar.class);
        q.setParameters(idBar);
        return (Bar) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la
     * base de datos de Parranderos, por su nombre
     * @param pm - El manejador de persistencia
     * @param nombreBar - El nombre de bar buscado
     * @return Una lista de objetos BAR que tienen el nombre dado
     */
    public List<Bar> darBaresPorNombre (PersistenceManager pm, String nombreBar)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE nombre = ?");
        q.setResultClass(Bar.class);
        q.setParameters(nombreBar);
        return (List<Bar>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la
     * base de datos de Parranderos
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos BAR
     */
    public List<Bar> darBares (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar ());
        q.setResultClass(Bar.class);
        return (List<Bar>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para aumentar en uno el número de sedes de los bares de la
     * base de datos de Parranderos
     * @param pm - El manejador de persistencia
     * @param ciudad - La ciudad a la cual se le quiere realizar el proceso
     * @return El número de tuplas modificadas
     */
    public long aumentarSedesBaresCiudad (PersistenceManager pm, String ciudad)
    {
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaBar () + " SET cantsedes = cantsedes + 1 WHERE ciudad = ?");
        q.setParameters(ciudad);
        return (long) q.executeUnique();
    }
}
