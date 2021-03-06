package AlohAndes.negocio;


/**
 * Clase para modelar el concepto servicios del negocio de los AlohaAndes
 *
 */
public class Servicio implements VOServicio{
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los Servico
	 */
	private long id;
	
	/**
	 * el idAlojamiento del Servicio
	 */
	private long idAlojamiento;
	
	/**
	 * la Descripcion del Servicio
	 */
	private String descripcion;


	/**
	 * El nombre del Servicio
	 */
	private String nombre;
	
	
	/**
	 * El Precio del Servicio
	*/
	private Double precio;
	
	
	/**
	 * si se TomaServicio del Servicio
	 */
	private Boolean tomaServicio;

	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Servicio() 
    {
    	this.setId(0);
		this.idAlojamiento = 0;
		this.descripcion = "";
		this.nombre = "";
		this.precio = 0.0;
		this.tomaServicio = false;
	}

	/**
	 * Constructor con valores
	 */
    public Servicio(long id, long idAlojamiento, String descripcion, String nombre, Double precio, Boolean tomaServicio) 
    {
    	this.setId(id);
		this.idAlojamiento = idAlojamiento;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.precio = precio;
		this.tomaServicio = false;
	}
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(long idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getTomaServicio() {
		return tomaServicio;
	}

	public void setTomaServicio(Boolean tomaServicio) {
		this.tomaServicio = tomaServicio;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Servicios [id=" + id + ", idAlojamiento=" + idAlojamiento + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ ", precio=" + precio + ", tomaServicio=" + tomaServicio + "]";
	}


}
