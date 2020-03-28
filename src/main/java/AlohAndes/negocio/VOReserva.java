package AlohAndes.negocio;

import java.util.Date;

/**
 * Interfaz para los métodos get de reservas
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 *
 */
public interface VOReserva
{
    /* ****************************************************************
     * 			Métodos
     *****************************************************************/

    /**
     * @return El numero de reserva de la reserva
     */
    public long getNumReserva();

    /**
     * @return El idAlojamiento de la reserva
     */
    public long getIdAlojamiento();

    /**
     * @return El id del miembro que hace la reserva
     */
    public long getIdMiembro();

    /**
     * @return El tipo id  del miembro que hace la reserva
     */
    public String getTipoID();

    /**
     * @return El dia de la reserva
     */
    public Date getDiaReserva();

    /**
     * @return El numero de dias que por los que tomara la reserva
     */
    public int getTiempoDias();


    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos de la vivienda de comunidad
     */
    public String toString();

}
