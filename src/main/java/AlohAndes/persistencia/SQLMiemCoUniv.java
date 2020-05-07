package AlohAndes.persistencia;

import AlohAndes.negocio.MiembroComunidadUniversitaria;
import AlohAndes.negocio.MiembroComunidadUniversitaria;

import java.util.Date;
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

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UNA MiembroComunidadUniversitaria de la base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idMiembroComunidadUniversitaria - El id de la MiembroComunidadUniversitaria
     * @return EL número de tuplas eliminadas
     */
    public long eliminarMiembroComunidadUniversitariaPorId (PersistenceManager pm, long idMiembroComunidadUniversitaria)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMiemCoUniv() + " WHERE ID = ?");
        q.setParameters(idMiembroComunidadUniversitaria);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA MiembroComunidadUniversitaria de la
     * base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idMiembroComunidadUniversitaria - El identificador de la MiembroComunidadUniversitaria
     * @return El objeto MiembroComunidadUniversitaria que tiene el identificador dado
     */
    public MiembroComunidadUniversitaria darMiembroComunidadUniversitariaPorId (PersistenceManager pm, long idMiembroComunidadUniversitaria)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaMiemCoUniv () + " WHERE ID = ?");
        q.setResultClass(MiembroComunidadUniversitaria.class);
        q.setParameters(idMiembroComunidadUniversitaria);
        return (MiembroComunidadUniversitaria) q.executeUnique();
    }

    
    
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS MiembroComunidadUniversitariaS de la 
	 * base de datos de Alohaandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos MiembroComunidadUniversitaria
	 */
	public List<MiembroComunidadUniversitaria> darMiembrosComunidadUniversitaria (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaMiemCoUniv() );
		q.setResultClass(MiembroComunidadUniversitaria.class);
		return (List<MiembroComunidadUniversitaria>) q.executeList();
	}
	
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar el nomnbre de un mien=mbro en la 
	 * base de datos de alohaandes
	 * @param pm - El manejador de persistencia
	 * @param idMiembroComunidadUniversitaria - El identificador del miembro
	 * @param tipoMiembro - La nueva habilitado del miembro
	 * @return El número de tuplas modificadas
	 */
	public long cambiarElTipoMiembro (PersistenceManager pm, long idMiembroComunidadUniversitaria, String tipoMiembro) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAlojamientos() + " SET TIPOMIEMBRO = ? WHERE ID = ?");
	     q.setParameters( tipoMiembro, idMiembroComunidadUniversitaria);
	     return (long) q.executeUnique();      
	     
	}
    
}
