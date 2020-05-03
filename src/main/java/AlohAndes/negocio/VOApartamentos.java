package AlohAndes.negocio;

/**
 * Interfaz para los métodos get de APARTAMENTOS.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOApartamentos {

	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
     /**
	 * @return El idAlojamiento del apartamento
	 */
	public int getIdAlojamiento();
	
	/**
	 * @return La direccion del apartamento
	 */
	public String getDireccion();
	
	/**
	 * @return El precio del apartamento
	 */
	public Double getPrecio();
	
	
	/**
	 * @return El dueno del apartamento
	 */
	public int getDueno();
	
	/**
	 * @return El valor de la administracion del apartamento
	 */
	public Double getValorAdmin();

	/**
	 * @return si se esta amoblado el apartamento
	 */
	public Boolean getAmobaldo();
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la vivienda de comunidad
	 */
	public String toString();


}
