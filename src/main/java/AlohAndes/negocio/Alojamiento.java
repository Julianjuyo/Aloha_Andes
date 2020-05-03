package AlohAndes.negocio;


/**
 * Clase para modelar el concepto ALOJAMIENTO del negocio de los ALOHAANDES
 *
 */
public class Alojamiento implements VOAlojamiento{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	 /**
	 * El identificador ÚNICO de los alojamientos
	 */
	private int id;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

    /**
     * Constructor por defecto
     */
	public Alojamiento() 
    {
    	this.setId(0);

	}
	
	/**
	 * Constructor con valores
	 * @param id - El id del alojamiento

	 */
    public Alojamiento(int id) 
    {
    	this.setId(id);

	}

    
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del alojamiento
	 */
	public String toString() 
	{
		return "Alojamientos [id=" + id + "]";
	}
}
