package AlohAndes.negocio;


/**
 * Interfaz para los métodos get de VIVIENDA COMUNIDAD.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOViviendaComunidad {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
     /**
	 * @return El idAlojamiento de la vivienda de comunidad 
	 */
	public long getIdAlojamiento();
	

	/**
	 * @return La direccion de la vivienda de comunidad
	 */
	public String getDireccion();
	
	/**
	 * @return El numero de habitaciones de la vivienda de comunidad
	 */	
	public Integer getNumHabitaciones();
	
	/**
	 * @return la menaje de la vivienda de comunidad
	 */
	public Boolean getMenaje();

	/**
	 * @return si se tiene seguro del arrendatario de la vivienda de comunidad
	 */
	public Boolean getSeguroArrendatario();
	
	/**
	 * @return La caracteristicas del seguro de la vivienda de comunidad
	 */
	public String getCaractSeguro();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la vivienda de comunidad
	 */
	public String toString();



}
