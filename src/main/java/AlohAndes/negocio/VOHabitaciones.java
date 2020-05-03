package AlohAndes.negocio;

/**
 * Interfaz para los métodos get de HABITACIONES.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOHabitaciones {
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
    /**
	 * @return El idAlojamiento de la habitaction
	 */
	public int getIdAlojamiento();
	
	/**
	 * @return El idOperador de la habitaction
	 */
	public int getIdOperador();
	
	/**
	 * @return La direccion de la habitaction
	 */
	public String getDireccion();
	
	/**
	 * @return El precio de la habitaction
	 */
	public Double getPrecio();
	
	/**
	 * @return El numero de habitacion de la habitaction
	 */
	public String getNumHabitacion();
	
	/**
	 * @return el tipo de habitacion de la habitacion
	 */
	public String getTipoHabitacion();

	/**
	 * @return el tipo de operador de la habitacion de la vivienda de comunidad
	 */
	public String getTipoOperadorHabitacion();
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la vivienda de comunidad
	 */
	public String toString();


}
