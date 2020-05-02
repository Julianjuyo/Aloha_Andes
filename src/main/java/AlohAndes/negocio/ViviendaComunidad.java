package negocio;

/**
 * Clase para modelar el concepto  VIVIENDA COMUNIDAD del negocio de los AlohaAndes
 *
 */
public class ViviendaComunidad implements VOViviendaComunidad {
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 *  El idAlojamiento de la vivienda de comunidad
	 */
	private int idAlojamiento;
	
	/**
	 *  El idOperador de la vivienda de comunidad
	 */
	private int idOperador;
	
	/**
	 *  La direccion de la vivienda de comunidad
	 */
	private String direccion;
	
	/**
	 *  El precio de la vivienda de comunidad
	 */
	private Double precio;
	
	/**
	 *  El numero de habitaciones de la vivienda de comunidad
	 */
	private int numHabitaciones;
	
	/**
	 *  la menaje de la vivienda de comunidad
	 */
	private Boolean menaje;

	/**
	 *  si se tiene seguro del arrendatario de la vivienda de comunidad
	 */
	private Boolean seguroArrendatario;
	
	/**
	 *  La caracteristicas del seguro de la vivienda de comunidad
	 */
	private String caractSeguro;
	
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public ViviendaComunidad() 
    {
    	this.setIdAlojamiento(0);
		this.idOperador = 0;
		this.direccion = "";
		this.precio = 0.0;
		this.numHabitaciones = 0;
		this.menaje = false;
		this.seguroArrendatario = false ;
		this.caractSeguro = "";
	}

	/**
	 * Constructor con valores
	 */
    public ViviendaComunidad(int idaloja, int idOperador, String direccion, Double precio, int numHabitaciones, Boolean menaje, Boolean seguroArrendatario, String caractSeguro ) 
    {
    	this.setIdAlojamiento(idaloja);
		this.idOperador = idOperador;
		this.direccion = direccion;
		this.precio = precio;
		this.numHabitaciones = numHabitaciones;
		this.menaje = menaje;
		this.seguroArrendatario = seguroArrendatario ;
		this.caractSeguro = caractSeguro;
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

	public Integer getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(Integer numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

	public Boolean getMenaje() {
		return menaje;
	}

	public void setMenaje(Boolean menaje) {
		this.menaje = menaje;
	}

	public Boolean getSeguroArrendatario() {
		return seguroArrendatario;
	}

	public void setSeguroArrendatario(Boolean seguroArrendatario) {
		this.seguroArrendatario = seguroArrendatario;
	}

	public String getCaractSeguro() {
		return caractSeguro;
	}

	public void setCaractSeguro(String caractSeguro) {
		this.caractSeguro = caractSeguro;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "ViviendaComunidad [idAlojamiento=" + idAlojamiento + ", idOperador=" + idOperador + ", direccion=" + direccion + ", precio=" + precio
				+ ", numHabitaciones=" + numHabitaciones + ", menaje=" + menaje + ", seguroArrendatario=" + seguroArrendatario + ", caractSeguro=" + caractSeguro +"]";
	}
    

}
