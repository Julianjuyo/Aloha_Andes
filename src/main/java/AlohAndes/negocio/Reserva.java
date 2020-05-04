package AlohAndes.negocio;

import java.util.Date;

/**
 * Clase para modelar el concepto  RESERVAS  del negocio de los AlohaAndes
 *
 */
public class Reserva implements VOReserva
{

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/

    /**
     *  El numero de reserva de la reserva
     */
    private long numReserva;

    /**
     *  El idAlojamiento de la reserva
     */
    private long idAlojamiento;

    /**
     *  El id del miembro que hace la reserva
     */
    private long idMiembro;

    /**
     *  El tipo id  del miembro que hace la reserva
     */
    private String tipoID;

    /**
     *  El dia de la reserva
     */
    private Date diaReserva;

    /**
     *  El numero de dias que por los que tomara la reserva
     */
    private int tiempoDias;

    /* ****************************************************************
     * 			Métodos
     *****************************************************************/
    /**
     * Constructor por defecto
     */
    public Reserva()
    {
        this.setNumReserva(0);
        this.idAlojamiento = 0;
        this.idMiembro = 0;
        this.tipoID = "";
        this.diaReserva = new Date();
        this.tiempoDias = 0;

    }

    /**
     * Constructor con valores
     */
    public Reserva(long numReserva, long idAlojamiento, long idMiembro, String tipoID, Date diaReserva, int tiempoDias )
    {
        this.setNumReserva(numReserva);
        this.idAlojamiento = idAlojamiento;
        this.idMiembro = idMiembro;
        this.tipoID = tipoID;
        this.diaReserva = diaReserva;
        this.tiempoDias = tiempoDias;
    }

    public long getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(long numReserva) {
        this.numReserva = numReserva;
    }

    public long getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(long idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public long getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(long idMiembro) {
        this.idMiembro = idMiembro;
    }

    public String getTipoID() {
        return tipoID;
    }

    public void setTipoID(String tipoID) {
        this.tipoID = tipoID;
    }

    public Date getDiaReserva() {
        return diaReserva;
    }

    public void setDiaReserva(Date diaReserva) {
        this.diaReserva = diaReserva;
    }

    public int getTiempoDias() {
        return tiempoDias;
    }

    public void setTiempoDias(int tiempoDias) {
        this.tiempoDias = tiempoDias;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del bar
     */
    public String toString()
    {
        return "Reserva [numeroReserva=" + numReserva + ", idAlojamiento=" + idAlojamiento + ", idMiembro=" + idMiembro + ", tipoID=" + tipoID
                + ", diaReserva=" + diaReserva + ", tiempoDias=" + tiempoDias +"]";
    }
}