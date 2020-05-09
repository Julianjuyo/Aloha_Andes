package AlohAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import AlohAndes.negocio.Servicio;
import AlohAndes.negocio.Servicio;

public class SQLServicio
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
    public SQLServicio (PersistenciaAlohAndes pp)
    {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una Servicio a la base de datos de AlohAndes
     * @param pm - El manejador de persistencia
	 * @param idAlojamiento id del alojamiento
	 * @param descripcion descripcion del servicio
	 * @param nombre nombre que tiene el servicio 
	 * @param precio El precio del servicio puede ser 0
	 * @param TomaServicio Indica si se toma o no el sercio
     * @return EL número de tuplas insertadas
     */
    public long adicionarServicio (PersistenceManager pm, long idServicio, long idAlojamiento, String Descripcion, String nombre, Double precio, String TomaServicio)
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicios () + "(id, IDALOJAMIENTO, DESCRIPCION, NOMBRE, PRECIO, TOMASERVICIO) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idServicio, idAlojamiento, Descripcion, nombre, precio, TomaServicio);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UNA Servicio de la base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idServicio - El id de la Servicio
     * @return EL número de tuplas eliminadas
     */
    public long eliminarServicioPorId (PersistenceManager pm, long idServicio)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicios () + " WHERE id = ?");
        q.setParameters(idServicio);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Servicio de la
     * base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idServicio - El identificador de la Servicio
     * @return El objeto Servicio que tiene el identificador dado
     */
    public Servicio darServicioPorId (PersistenceManager pm, long idServicio)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicios () + " WHERE id = ?");
        q.setResultClass(Servicio.class);
        q.setParameters(idServicio);
        return (Servicio) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Servicio de la
     * base de datos de AlohAndes, por su alojamiento asociado
     * @param pm - El manejador de persistencia
     * @param idAlojamiento - El identificador del alojamiento asociado
     * @return El objeto Servicio que tiene el alojamiento asociado
     */
    public List<Servicio> darLosServiciosDeUnIdAlojamiento (PersistenceManager pm, long idAlojamiento)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicios () + " WHERE IDALOJAMIENTO = ?");
        q.setResultClass(Servicio.class);
        q.setParameters(idAlojamiento);
        return (List<Servicio>) q.executeUnique();
    }
    
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS ServicioS de la 
	 * base de datos de Alohaandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Servicio
	 */
	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicios  ());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el Descripcion de un Servicio en la 
	 * base de datos de alohaandes
	 * @param pm - El manejador de persistencia
	 * @param idServicio - El identificador del miembro
	 * @param Descripcion - La nueva habilitado del miembro
	 * @return El número de tuplas modificadas
	 */
	public long cambiarLaDescripcionDeUnServicio (PersistenceManager pm, long idServicio, String Descripcion) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAlojamientos() + " SET DESCRIPCION = ? WHERE ID = ?");
	     q.setParameters( Descripcion, idServicio);
	     return (long) q.executeUnique();      
	     
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el Precio de un Servicio en la 
	 * base de datos de alohaandes
	 * @param pm - El manejador de persistencia
	 * @param idServicio - El identificador del miembro
	 * @param precio - La nueva habilitado del miembro
	 * @return El número de tuplas modificadas
	 */
	public long cambiarElPrecioDeUnServicio (PersistenceManager pm, long idServicio, Double precio) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAlojamientos() + " SET PRECIO = ? WHERE ID = ?");
	     q.setParameters( precio, idServicio);
	     return (long) q.executeUnique();      
	     
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el TomaServicio de un Servicio en la 
	 * base de datos de alohaandes
	 * @param pm - El manejador de persistencia
	 * @param idServicio - El identificador del miembro
	 * @param TomaServicio - La nueva habilitado del miembro
	 * @return El número de tuplas modificadas
	 */
	public long cambiarTomaServicioDeUnServicio (PersistenceManager pm, long idServicio, String TomaServicio) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAlojamientos() + " SET TOMASERVICIO = ? WHERE ID = ?");
	     q.setParameters( TomaServicio, idServicio);
	     return (long) q.executeUnique();      
	     
	}

}
