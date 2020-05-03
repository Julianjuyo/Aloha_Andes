package AlohAndes.negocio;

/**
 * Clase para modelar el concepto HABITACIONES del negocio de los AlohaAndes
 *
 */
public class Habitaciones implements VOHabitaciones {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	
    /**
	 *  El idAlojamiento de la habitaction
	 */
	private int idAlojamiento;
	
	/**
	 *  El idOperador de la habitaction
	 */
	private int idOperador;
	
	/**
	 *  La direccion de la habitaction
	 */
	private String direccion;
	
	/**
	 *  El precio de la habitaction
	 */
	private Double precio;
	
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
	public Habitaciones() 
    {
    	this.setIdAlojamiento(0);
		this.idOperador = 0;
		this.direccion = "";
		this.precio = 0.0;
		this.numHabitacion = "";
		this.tipoHabitacion = "";
		this.tipoOperadorHabitacion = "" ;
	}

	/**
	 * Constructor con valores
	 */
    public Habitaciones(int idaloja, int idOperador, String direccion, Double precio, String numHabitacion, String tipoHabitacion, String tipoOperadorHabitacion ) 
    {
    	this.setIdAlojamiento(idaloja);
		this.idOperador = idOperador;
		this.direccion = direccion;
		this.precio = precio;
		this.numHabitacion = numHabitacion;
		this.tipoHabitacion = tipoHabitacion;
		this.tipoOperadorHabitacion = tipoOperadorHabitacion;
	}

	public int getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(int idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}

	public int getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(int idOperador) {
		this.idOperador = idOperador;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
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
		return "Habitaciones [idAlojamiento=" + idAlojamiento + ", idOperador=" + idOperador + ", direccion=" + direccion + ", precio=" + precio
				+ ", numHabitacion=" + numHabitacion + ", tipoHabitacion=" + tipoHabitacion + ", tipoOperadorHabitacion=" + tipoOperadorHabitacion +"]";
	}
    
	
	
	
	

}
