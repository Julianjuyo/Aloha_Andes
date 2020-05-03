package AlohAndes.negocio;

import AlohAndes.persistencia.PersistenciaAlohAndes;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

public class AlohAndes
{

    /* ****************************************************************
     * 			Constantes
     *****************************************************************/
    /**
     * Logger para escribir la traza de la ejecución
     */
    private static Logger log = Logger.getLogger(AlohAndes.class.getName());

    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    /**
     * El manejador de persistencia
     */
    private PersistenciaAlohAndes pp;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/
    /**
     * El constructor por defecto
     */
    public AlohAndes ()
    {
        pp = PersistenciaAlohAndes.getInstance();
    }

	
	
    
    /**
     * Cierra la conexión con la base de datos (Unidad de persistencia)
     */
    public void cerrarUnidadPersistencia ()
    {
        pp.cerrarUnidadPersistencia ();
    }
    

    /* ****************************************************************
     * 			Métodos para manejar las RESERVAS
     *****************************************************************/
    /**
     * Adiciona de manera persistente una reserva
     * Adiciona entradas al log de la aplicación
     * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
     * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tiempoDias - El número de días que se desea reservar el alojamiento
     * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
     */
    public Reserva adicionarReserva (long idAlojamiento, long idMiembro, String tipoId, int tiempoDias)
    {
        log.info ("Adicionando Reserva del alojamiento: " + idAlojamiento);
        Reserva reserva = pp.adicionarReserva(idAlojamiento, idMiembro, tipoId, tiempoDias);
        log.info ("Adicionanda reserva: " + reserva);
        return reserva;
    }

    /**
     * Elimina una reserva por su identificador
     * Adiciona entradas al log de la aplicación
     * @param idReserva - El id de la reserva
     * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
     */
    public long eliminarReservaPorId (long idReserva)
    {
        log.info ("Eliminando reserva por id: " + idReserva);
        long resp = pp.eliminarReservaPorId (idReserva);
        log.info ("Eliminando reserva por id: " + resp + " tuplas eliminadas");
        return resp;
    }

    /**
     * Encuentra una reserva y su información básica, según su identificador
     * @param idReserva - El identificador de la reserva buscada
     * @return Un objeto Reserva que corresponde con el identificador buscado y lleno con su información básica
     * 		   Null, si un bebedor con dicho identificador no existe
     */
    public Reserva darReservaPorId (long idReserva)
    {
        log.info ("Dar información de una reserva por id: " + idReserva);
        Reserva reserva = pp.darReservaPorId (idReserva);
        log.info ("Buscando reserva por Id: " + reserva != null ? reserva : "NO EXISTE");
        return reserva;
    }
    
	/**
	 * Encuentra todos las reservas en AlohAndes y los devuelve como una lista de VOReserva
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOReserva con todos las reservas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOReserva> darVORerserva ()
	{
		log.info ("Generando los VO de reserva");    
		
        List<VOReserva> voTipos = new LinkedList<VOReserva> ();
        
        for (Reserva tb : pp.darReservas() )
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Reservas: " + voTipos.size() + " existentes");
        return voTipos;
	}

    /* ****************************************************************
     * 			Métodos para manejar los ALOJAMIENTOS
     *****************************************************************/

    /**
     * Elimina un alojamiento por su identificador
     * Adiciona entradas al log de la aplicación
     * @param idAlojamiento - El id de la reserva
     * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
     */
    public long eliminarAlojamiento (long idAlojamiento)
    {
        log.info ("Eliminando alojamiento por id: " + idAlojamiento);
        long resp = pp.eliminarAlojamiento (idAlojamiento);
        log.info ("Eliminando alojamiento por id: " + resp + " tuplas eliminadas");
        return resp;
    }
    
	/**
	 * Encuentra todos las reservas en AlohAndes y los devuelve como una lista de VOReserva
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOReserva con todos las reservas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAlojamiento> darVOAlojamiento ()
	{
		log.info ("Generando los VO de reserva");    
		
        List<VOAlojamiento> voTipo = new LinkedList<VOAlojamiento> ();
        
        for (Alojamiento alo : pp.darAlojamientos() )
        {
        	voTipo.add(alo);
        }
        log.info ("Generando los VO de Alojamientos: " + voTipo.size() + " existentes");
        return voTipo;
	}
	
    /* ****************************************************************
     * 			Métodos para manejar los Operadores
     *****************************************************************/
	
    /**
     * Adiciona de manera persistente una operador
     * Adiciona entradas al log de la aplicación
     * @param idOperador - El identificador del alojamiento que se desea Operadorr 
     * @param nombre - El identificador del miembro que desea realizar la Operador 
     * @param registroDeCamaraYComercio -  si esta subscrito o tiene el certitificado.
     * @param SuperIntendensiaDeTurismo - si esta subscrito o tiene el certitificado.
     * @param calidad - La calidad
     * @param tipoOperador - El tipo operador 
     * @return El objeto operador adicionado. null si ocurre alguna Excepción
     */
    public Operador adicionarOperador (String nombre, Boolean  registroDeCamaraYComercio, Boolean SuperIntendensiaDeTurismo, int  calidad, String  tipoOperador)
    {
    	
        Operador operador = pp.adicionarOperador(nombre, registroDeCamaraYComercio, SuperIntendensiaDeTurismo, calidad, tipoOperador);
        log.info ("Adicionanda operador: " + operador);
        return operador;
    }

    /**
     * Elimina una operador por su identificador
     * Adiciona entradas al log de la aplicación
     * @param idoperador - El id de la operador
     * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
     */
    public long eliminaroperadorPorId (long idoperador)
    {
        log.info ("Eliminando operador por id: " + idoperador);
        long resp = pp.eliminarOperadorPorId (idoperador);
        log.info ("Eliminando operador por id: " + resp + " tuplas eliminadas");
        return resp;
    }

    /**
     * Encuentra una operador y su información básica, según su identificador
     * @param idoperador - El identificador de la operador buscada
     * @return Un objeto operador que corresponde con el identificador buscado y lleno con su información básica
     * 		   Null, si un bebedor con dicho identificador no existe
     */
    public Operador darOperadorPorNombre (long idoperador)
    {
        log.info ("Dar información de una operador por id: " + idoperador);
        Operador operador = pp.darOperadorPorId (idoperador);
        log.info ("Buscando operador por Id: " + operador != null ? operador : "NO EXISTE");
        return operador;
    }
    
	/**
	 * Encuentra todos las operadors en AlohAndes y los devuelve como una lista de VOoperador
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOoperador con todos las operadors que conoce la aplicación, llenos con su información básica
	 */
	public List<VOOperador> darVOOperador ()
	{
		log.info ("Generando los VO de operador");    
		
        List<VOOperador> voTipos = new LinkedList<VOOperador> ();
        
        for (Operador tb : pp.darOperadores() )
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de operadors: " + voTipos.size() + " existentes");
        return voTipos;
	}
    
    
    
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AlohAndes
	 * 
	 * @return Un arreglo con 8 números que indican el número de tuplas borradas en las tablas ALOJAMIENTOS, APARTAMENTOS, HABITACIONES, 
	 * MIEMBRO COMUNIDAD UNIVERSITARIA, OPERADORES, RESERVA y SERVIVICIOS, VIVIENDA COMUNIDAD, respectivamente
	 */
	public long [] limpiarAlohAndes ()
	{
        log.info ("Limpiando la BD de ALOHAANDES");
        long [] borrrados = pp.limpiarAlohAndes();	
        log.info ("Limpiando la BD de ALOHAANDES: Listo!");
        return borrrados;
	}
	
}
