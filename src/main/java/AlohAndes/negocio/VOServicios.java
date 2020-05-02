package negocio;


/**
 * Interfaz para los métodos get de SERVICIOS.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOServicios {


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	/**
	 * @return El id del Servicio
	 */
	public int getId();

	/**
	 * @return el idAlojamiento del Servicio
	 */
	public int getIdAlojamiento();

	/**
	 * @return la Descripcion del Servicio
	 */
	public String getDescripcion();

	/**
	 * @return El nombre del Servicio
	 */
	public String getNombre();

	/**
	 * @return El Precio del Servicio
	 */
	public Double getPrecio();

	/**
	 * @return si se TomaServicio del Servicio
	 */
	public Boolean getTomaServicio();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Servicio
	 */
	public String toString();




}
