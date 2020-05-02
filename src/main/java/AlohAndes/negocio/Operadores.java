package negocio;

/**
 * Clase para modelar el concepto OPERADORES del negocio de los AlohaAndes
 *
 */
public class Operadores implements VOOperadores 
{
	
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los operadores
	 */
	private int id;
	
	/**
	 * El nombre del operador
	 */
	private String nombre;


	/**
	 * si el operador tiene registro de camara y comercio
	 */
	private Boolean regCamaraYComercio;
	
	/**
	 * si el opreador tiene super intendencia de turismo	 
	*/
	private Boolean superTurismo;
	
	/**
	 * la calidad del operador
	 */
	private int calidad;
	
	/**
	 * El tipooperador del bar ('Hostal', 'Hotel', 'empViniendaUniv','MiembroComunidad','PersonaNatural')

	 */
	private String tipoOperador;

	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Operadores() 
    {
    	this.setId(0);
		this.nombre = "";
		this.regCamaraYComercio = false;
		this.superTurismo = false;
		this.calidad = 0;
		this.tipoOperador = "";
	}

	/**
	 * Constructor con valores
	 */
    public Operadores(int id, String nombre, Boolean regCamaraYComercio, Boolean superTurismo, int calidad, String tipooperador) 
    {
    	this.setId(id);
		this.nombre = nombre;
		this.regCamaraYComercio = regCamaraYComercio;
		this.superTurismo = superTurismo;
		this.calidad = calidad;
		this.tipoOperador = tipooperador ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getRegCamaraYComercio() {
		return regCamaraYComercio;
	}

	public void setRegCamaraYComercio(Boolean regCamaraYComercio) {
		this.regCamaraYComercio = regCamaraYComercio;
	}

	public Boolean getSuperTurismo() {
		return superTurismo;
	}

	public void setSuperTurismo(Boolean superTurismo) {
		this.superTurismo = superTurismo;
	}

	public int getCalidad() {
		return calidad;
	}

	public void setCalidad(int calidad) {
		this.calidad = calidad;
	}

	public String getTipoOperador() {
		return tipoOperador;
	}

	public void setTipoOperador(String tipoOperador) {
		this.tipoOperador = tipoOperador;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Operadores [id=" + id + ", nombre=" + nombre + ", regCamaraYComercio=" + regCamaraYComercio + ", superTurismo=" + superTurismo
				+ ", calidad=" + calidad + ", tipoOperador=" +tipoOperador +"]";
	}
	
	

}
