package negocio;

/**
 * Clase para modelar el concepto  APARTAMENTOS del negocio de los AlohaAndes
 *
 */
public class Apartamentos implements VOApartamentos {
	
	/* ****************************************************************
	 * 			atributos 
	 *****************************************************************/
	
	
    /**
	 *  El idAlojamiento del apartamento
	 */
	private int idAlojamiento;
	
	/**
	 *  La direccion del apartamento
	 */
	private String direccion;
	
	/**
	 *  El precio del apartamento
	 */
	private Double precio;
	
	
	/**
	 *  El dueno del apartamento
	 */
	private int dueno;
	
	/**
	 *  El valor de la administracion del apartamento
	 */
	private Double valorAdmin;

	/**
	 *  si se esta amoblado el apartamento
	 */
	private Boolean amobaldo;
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Apartamentos() 
    {
    	this.setIdAlojamiento(0);
		this.direccion = "";
		this.precio = 0.0;
		this.dueno = 0;
		this.valorAdmin = 0.0;
		this.amobaldo = false ;
	}

	/**
	 * Constructor con valores
	 */
    public Apartamentos(int idaloja, String direccion, Double precio, int dueno, Double valorAdmin, Boolean amobaldo ) 
    {
    	this.setIdAlojamiento(idaloja);
		this.direccion = direccion;
		this.precio = precio;
		this.dueno = dueno;
		this.valorAdmin = valorAdmin;
		this.amobaldo = amobaldo ;
	}

	public int getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(int idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
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

	public int getDueno() {
		return dueno;
	}

	public void setDueno(int dueno) {
		this.dueno = dueno;
	}

	public Double getValorAdmin() {
		return valorAdmin;
	}

	public void setValorAdmin(Double valorAdmin) {
		this.valorAdmin = valorAdmin;
	}

	public Boolean getAmobaldo() {
		return amobaldo;
	}

	public void setAmobaldo(Boolean amobaldo) {
		this.amobaldo = amobaldo;
	}
    
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Apartamentos [idAlojamiento=" + idAlojamiento +  ", direccion=" + direccion + ", precio=" + precio
				+ ", dueno=" + dueno + ", v=" + valorAdmin + ", amobaldo=" + amobaldo + "]";
	}

    

}
