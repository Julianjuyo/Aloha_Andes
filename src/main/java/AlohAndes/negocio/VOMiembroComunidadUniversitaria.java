package AlohAndes.negocio;


/**
 * Interfaz para los métodos get de MIEMBRO COMUNIDAD UNIVERSITARIA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOMiembroComunidadUniversitaria {

	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
     /**
	 * @return El id del miembro de la comunidad universitaria
	 */
	public int getId();
	
	/**
	 * @return El tipo de id  de la vivienda de comunidad
	 */
	public String getTipoId();
	
	/**
	 * @return El nombre del miembro de la comunidad universitaria
	 */
	public String getNombre();
	
	/**
	 * @return El tipo de miembro del miembro de la comunidad universitaria
	 */
	public String getTipoMiembro();
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la vivienda de comunidad
	 */
	public String toString();



}
