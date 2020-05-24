package AlohAndes.negocio;

/**
 * Clase para modelar el concepto  APARTAMENTOS del negocio de los AlohaAndes
 *
 */
public class Apartamento implements VOApartamento {
	
	/* ****************************************************************
	 * 			atributos 
	 *****************************************************************/
	
	
    /**
	 *  El idAlojamiento del apartamento
	 */
	private long idAlojamiento;
	
	/**
	 *  La direccion del apartamento
	 */
	private String direccion;
	

	
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
	public Apartamento() 
    {
    	this.setIdAlojamiento(0);
		this.direccion = "";

		this.valorAdmin = 0.0;
		this.amobaldo = false ;
	}

	/**
	 * Constructor con valores
	 */
    public Apartamento(long idaloja, String direccion,  Double valorAdmin, Boolean amobaldo ) 
    {
    	this.setIdAlojamiento(idaloja);
		this.direccion = direccion;

		this.valorAdmin = valorAdmin;
		this.amobaldo = amobaldo ;
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
		return "Apartamentos [idAlojamiento=" + idAlojamiento +  ", direccion=" + direccion +", valorAdmin=" + valorAdmin + ", amobaldo=" + amobaldo + "]";
	}

    

}
