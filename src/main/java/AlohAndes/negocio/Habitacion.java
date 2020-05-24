package AlohAndes.negocio;

/**
 * Clase para modelar el concepto HABITACIONES del negocio de los AlohaAndes
 *
 */
public class Habitacion implements VOHabitacion {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	
    /**
	 *  El idAlojamiento de la habitaction
	 */
	private long idAlojamiento;
	

	
	/**
	 *  La direccion de la habitaction
	 */
	private String direccion;
	

	/**
	 *  El numero de habitacion de la habitaction
	 */
	private String numHabitacion;
	
	/**
	 *  el tipo de habitacion de la habitacion
	 */
	private String tipoHabitacion;

	/**
	 *  el tipo de operador de la habitacion de la vivienda de comunidad
	 */
	private String tipoOperadorHabitacion;

	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Habitacion() 
    {
    	this.setIdAlojamiento(0);

		this.direccion = "";

		this.numHabitacion = "";
		this.tipoHabitacion = "";
		this.tipoOperadorHabitacion = "" ;
	}

	/**
	 * Constructor con valores
	 */
    public Habitacion(long idaloja, String direccion, String numHabitacion, String tipoHabitacion, String tipoOperadorHabitacion ) 
    {
    	this.setIdAlojamiento(idaloja);
		this.direccion = direccion;
		this.numHabitacion = numHabitacion;
		this.tipoHabitacion = tipoHabitacion;
		this.tipoOperadorHabitacion = tipoOperadorHabitacion;
	}

	public long getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(long idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}



	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(String numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public String getTipoOperadorHabitacion() {
		return tipoOperadorHabitacion;
	}

	public void setTipoOperadorHabitacion(String tipoOperadorHabitacion) {
		this.tipoOperadorHabitacion = tipoOperadorHabitacion;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Habitaciones [idAlojamiento=" + idAlojamiento +  ", direccion=" + direccion 
				+ ", numHabitacion=" + numHabitacion + ", tipoHabitacion=" + tipoHabitacion + ", tipoOperadorHabitacion=" + tipoOperadorHabitacion +"]";
	}
    
	
	
	
	

}
