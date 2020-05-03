package AlohAndes.negocio;

/**
 * Interfaz para los métodos get de OPERADORES.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOOperador {
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
     /**
	 * @return El id del Operador
	 */
	public long getId();
	
	/**
	 * @return el nombre del Operador
	 */
	public String getNombre();
	
	/**
	 * @return la camara y comercio del Operador
	 */
	public Boolean getRegCamaraYComercio();
	
	/**
	 * @return El super turismo del Operador
	 */
	public Boolean getSuperTurismo();
	
	/**
	 * @return la calidad del Operador
	 */
	public int getCalidad();

	/**
	 * @return El tipo de operador del Operador
	 */
	public String getTipoOperador();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Operador
	 */
	public String toString();


}
