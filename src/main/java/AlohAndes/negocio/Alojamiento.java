package AlohAndes.negocio;

import java.util.Date;

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
	private long id;
	
	/*
	 * Verifica si esta disponible o no un alojamientos
	 */
	private boolean habilitada;
	
	

	/*
	 * En caso de no estar disponible se debe se indica la fecha incial Del periodo de no disponibilidad
	 */
	private Date fechaInicioDes;

	/*
	 * En caso de no estar disponible se debe se indica la fecha incial Del periodo de no disponibilidad
	 */
	private Date fechaFinDes;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

    /**
     * Constructor por defecto
     * @param fechaFin 
     * @param fechaInicio 
     * @param habilitada2 
     * @param idAlojamiento 
     */
	public Alojamiento(long idAlojamiento, String habilitada2, Date fechaInicio, Date fechaFin) 
    {
    	this.setId(0);
    	this.habilitada=true;
        this.fechaInicioDes = new Date();
        this.fechaFinDes = new Date();

	}
	
	/**
	 * Constructor con valores
	 * @param id - El id del alojamiento

	 */
    public Alojamiento(long id, boolean habilitada, Date fechaInicioDes, Date fechaFinDeshabilitada) 
    {
    	this.setId(id);
    	this.setHabilitada(habilitada);
    	this.setFechaInicioDeshabilitada(fechaInicioDes);
    	this.setFechaFinDeshabilitada(fechaFinDeshabilitada);

	}

    
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	public Date getFechaInicioDeshabilitada() {
		return fechaInicioDes;
	}

	public void setFechaInicioDeshabilitada(Date fechaInicioDeshabilitada) {
		this.fechaInicioDes = fechaInicioDeshabilitada;
	}

	public Date getFechaFinDeshabilitada() {
		return fechaFinDes;
	}

	public void setFechaFinDeshabilitada(Date fechaFinDeshabilitada) {
		this.fechaFinDes = fechaFinDeshabilitada;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del alojamiento
	 */
	public String toString() 
	{
		return "Alojamiento [id=" + id + ", fechaInicioDes=" + fechaInicioDes + ", fechaFinDes=" + fechaFinDes + "]";
	}
}
