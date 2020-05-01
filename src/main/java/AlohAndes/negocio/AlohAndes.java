package AlohAndes.negocio;

import AlohAndes.persistencia.PersistenciaAlohAndes;
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
}
