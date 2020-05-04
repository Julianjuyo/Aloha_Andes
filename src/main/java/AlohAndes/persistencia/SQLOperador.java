package AlohAndes.persistencia;

import AlohAndes.negocio.Operador;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOperador
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
    public SQLOperador (PersistenciaAlohAndes pp)
    {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una Operador a la base de datos de AlohAndes
     * @param pm - El manejador de persistencia
     * @param idOperador - El identificador del alojamiento que se desea Operadorr (Debe existir en la tabla ALOJAMIENTOS)
     * @param nombre - El identificador del miembro que desea realizar la Operador (Debe existir en la tabla MIEM_CO_UNIV)
     * @param registroDeCamaraYComercio -  si esta subscrito o tiene el certitificado.
     * @param SuperIntendensiaDeTurismo - si esta subscrito o tiene el certitificado.
     * @param calidad - La calidad
     * @param tipoOperador - El tipo operador 
     * @return EL número de tuplas insertadas
     */
    public long adicionarOperador (PersistenceManager pm, long idOperador, String nombre, String  registroDeCamaraYComercio, String SuperIntendensiaDeTurismo, int  calidad, String  tipoOperador)    
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOperadores() + "(ID, NOMBRE, REGCAMARACOMERCIO, SUPERTURISMO, CALIDAD, TIPOOPERADOR) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idOperador, nombre, registroDeCamaraYComercio, SuperIntendensiaDeTurismo, calidad, tipoOperador);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UNA Operador de la base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idOperador - El id de la Operador
     * @return EL número de tuplas eliminadas
     */
    public long eliminarOperadorPorId (PersistenceManager pm, long idOperador)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperadores () + " WHERE ID = ?");
        q.setParameters(idOperador);
        return (long) q.executeUnique();
    }
    
    
    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de UNA Operador de la
     * base de datos de AlohAndes, por su identificador
     * @param pm - El manejador de persistencia
     * @param idOperador - El identificador de la Operador
     * @return El objeto Operador que tiene el identificador dado
     */
    public Operador darOperadorPorId (PersistenceManager pm, long idOperador)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperadores () + " WHERE ID = ?");
        q.setResultClass(Operador.class);
        q.setParameters(idOperador);
        return (Operador) q.executeUnique();
    }

    
    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de un Operador de la
     * base de datos de AlohAndes, por su nombre
     * 
     * @param pm - El manejador de persistencia
     * @param nombre - El identificador del alojamiento asociado
     * @return El objeto Operador que tiene el alojamiento asociado
     */
    public Operador darOperadorPorNombre (PersistenceManager pm, String nombre)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperadores () + " WHERE NOMBRE = ?");
        q.setResultClass(Operador.class);
        q.setParameters(nombre);
        return (Operador) q.executeUnique();
    }
    
    
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS Operadores de la 
	 * base de datos de Alohaandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Operador
	 */
	public List<Operador> darOperadores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperadores());
		q.setResultClass(Operador.class);
		return (List<Operador>) q.executeList();
	}



    
}
