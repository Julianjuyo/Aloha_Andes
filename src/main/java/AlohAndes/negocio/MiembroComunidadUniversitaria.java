package negocio;


/**
 * Clase para modelar el concepto MIEMBRO COMUNIDAD UNIVERSITARIA del negocio de los AlohaAndes
 *
 */
public class MiembroComunidadUniversitaria implements VOMiembroComunidadUniversitaria {
	
	/* ****************************************************************
	 * 			atributos 
	 *****************************************************************/
	
     /**
	 *  El id del miembro de la comunidad universitaria
	 */
	private int id;
	
	/**
	 *  El tipo de id  de la vivienda de comunidad
	 */
	private String tipoId;
	
	/**
	 *  El nombre del miembro de la comunidad universitaria
	 */
	private String nombre;
	
	/**
	 *  El tipo de miembro del miembro de la comunidad universitaria
	 */
	private String tipoMiembro;
	


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public MiembroComunidadUniversitaria() 
    {
    	this.setId(0);
		this.tipoId = "";
		this.nombre = "" ;
		this.tipoMiembro = "";
	}

	/**
	 * Constructor con valores
	 */
    public MiembroComunidadUniversitaria(int id , String tipoId, String nombre, String tipoMiembro )
    {
    	
    	this.setId(id);
		this.tipoId = tipoId;
		this.nombre = nombre ;
		this.tipoMiembro = tipoMiembro;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoMiembro() {
		return tipoMiembro;
	}

	public void setTipoMiembro(String tipoMiembro) {
		this.tipoMiembro = tipoMiembro;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "MiembroComunidadUniversitaria [id=" + id + ", tipoId=" + tipoId + ", nombre=" + nombre + ", tipoMiembro=" + tipoMiembro+"]";
	}

}
