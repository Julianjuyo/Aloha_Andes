package AlohAndes.negocio;

import java.util.Date;

/**
 * Interfaz para los métodos get de Alojamientos.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOAlojamiento {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del Alojamiento
	 */
	public long getId();
	
	/*
	 * @return si esta disponible o no un alojamientos
	 */
	public boolean getHabilitada();
	
	
	/*
	 * @return la fecha incial Del periodo de no disponibilidad
	 */
	public Date getFechaInicioDeshabilitada();

	/*
	 * @return la fecha incial Del periodo de no disponibilidad
	 */
	public Date getFechaFinDeshabilitada();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Alojamiento
	 */
	public String toString();
	
}
