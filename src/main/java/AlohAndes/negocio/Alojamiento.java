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
	
	/**
	 *  El idOperador de la vivienda de comunidad
	 */
	private long idOperador;
	


	/**
	 *  El precio del apartamento
	 */
	private Double precio;
	
	

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
	public Alojamiento() 
    {
    	this.setId(0);
    	this.idOperador = 0;
    	this.precio = 0.0;
        this.fechaInicioDes = new Date();
        this.fechaFinDes = new Date();

	}
	
	/**
	 * Constructor con valores
	 * @param id - El id del alojamiento

	 */
    public Alojamiento(long id, long idOperador,   Double precio,  Date fechaInicioDes, Date fechaFinDeshabilitada) 
    {
    	this.setId(id);
    	this.setIdOperador(idOperador);
    	this.setPrecio(precio);
    	this.setFechaInicioDeshabilitada(fechaInicioDes);
    	this.setFechaFinDeshabilitada(fechaFinDeshabilitada);

	}

    
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(long idOperador) {
		this.idOperador = idOperador;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
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
		return "Alojamiento [id=" + id +", idOperador=" + idOperador +", precio=" + precio
				+ ", fechaInicioDes=" + fechaInicioDes + ", fechaFinDes=" + fechaFinDes + "]";
	}
}
