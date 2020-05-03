package AlohAndes.negocio;


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
	public int getId();
	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Alojamiento
	 */
	public String toString();
	
}
