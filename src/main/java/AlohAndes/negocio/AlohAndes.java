package AlohAndes.negocio;

import AlohAndes.persistencia.PersistenciaAlohAndes;

import java.sql.Array;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

public class AlohAndes
{

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(AlohAndes.class.getName());

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public AlohAndes ()
	{
		pp = PersistenciaAlohAndes.getInstance();
	}




	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}


	/* ****************************************************************
	 * 			Métodos para manejar las RESERVAS COLECTIVAS
	 *****************************************************************/
	
	/**
	 * Adiciona de manera persistente las reservas colectivas.
	 * Adiciona entradas al log de la aplicación
	 * @param tipoDeAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
	 * @param cantidadDeAlojamientos - El numero de alojamientos que desea reservas
	 * @param servicios - Los servicios que se desean t realizar en la reserva (Debe existir en la tabla Servicios)
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public Array adicionarReservaColectiva (String tipoDeAlojamiento, int cantidadDeAlojamientos, String[] servicios, long  idMiembro,String tipoId,int tiempoDias )
	{
		log.info ("Verificando si se puede satisfacer la solicitud ");   
		boolean SiHayCapacidad = false;
		
		for (int i = 0; i < cantidadDeAlojamientos; i++) 
		{
			 pp.darAlojamientos();
			
		}
		
		 

		List<VOReserva> voTipos = new LinkedList<VOReserva> ();

		for (Reserva tb : pp.darReservas() )
		{
			voTipos.add (tb);
		}
		
		
		log.info ("Adicionando Reserva del alojamiento: " );
		Reserva reserva = pp.adicionarReserva(idAlojamiento, idMiembro, tipoId, tiempoDias);
		log.info ("Adicionanda reserva: " + reserva);
		return reserva;
	}
	
	/**
	 * Elimina una reserva Colectiva por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idReserva - El id de la reserva
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReservaColectiva (long idReservaColectiva)
	{
		
		
		log.info ("Eliminando reserva por id: " + idReservaColectiva);
		long resp = pp.eliminarReservaPorId (idReservaColectiva);
		log.info ("Eliminando reserva por id: " + resp + " tuplas eliminadas");
		return resp;
	}
	
	
	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar las RESERVAS
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente una reserva
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea reservar el alojamiento
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public Reserva adicionarReserva (long idAlojamiento, long idMiembro, String tipoId, int tiempoDias)
	{
		log.info ("Adicionando Reserva del alojamiento: " + idAlojamiento);
		Reserva reserva = pp.adicionarReserva(idAlojamiento, idMiembro, tipoId, tiempoDias);
		log.info ("Adicionanda reserva: " + reserva);
		return reserva;
	}

	/**
	 * Elimina una reserva por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idReserva - El id de la reserva
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReservaPorId (long idReserva)
	{
		log.info ("Eliminando reserva por id: " + idReserva);
		long resp = pp.eliminarReservaPorId (idReserva);
		log.info ("Eliminando reserva por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una reserva y su información básica, según su identificador
	 * @param idReserva - El identificador de la reserva buscada
	 * @return Un objeto Reserva que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public Reserva darReservaPorId (long idReserva)
	{
		log.info ("Dar información de una reserva por id: " + idReserva);
		Reserva reserva = pp.darReservaPorId (idReserva);
		log.info ("Buscando reserva por Id: " + reserva != null ? reserva : "NO EXISTE");
		return reserva;
	}

	/**
	 * Encuentra todos las reservas en AlohAndes y los devuelve como una lista de VOReserva
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOReserva con todos las reservas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOReserva> darVORerserva ()
	{
		log.info ("Generando los VO de reserva");    

		List<VOReserva> voTipos = new LinkedList<VOReserva> ();

		for (Reserva tb : pp.darReservas() )
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Reservas: " + voTipos.size() + " existentes");
		return voTipos;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los ALOJAMIENTOS
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una Alojamiento
	 * Adiciona entradas al log de la aplicación
	 * @param habilitada - El identificador del alojamiento que se desea Alojamientor 
	 * @param fechaInicio - El identificador del miembro que desea realizar la Alojamiento 
	 * @param fechaFin -  si esta subscrito o tiene el certitificado.

	 * @return El objeto Alojamiento adicionado. null si ocurre alguna Excepción
	 */
	public Alojamiento adicionarAlojamiento (Boolean habilitada, Date  fechaInicio, Date fechaFin ) {

		Alojamiento Alojamiento = pp.adicionarAlojamiento(habilitada, fechaInicio, fechaFin);

		log.info ("Adicionanda Alojamiento: " + Alojamiento);


		return Alojamiento;
	}



	/**
	 * Elimina un alojamiento por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El id de la reserva
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAlojamientoPorID (long idAlojamiento)
	{
		log.info ("Eliminando alojamiento por id: " + idAlojamiento);
		long resp = pp.eliminarAlojamiento (idAlojamiento);
		log.info ("Eliminando alojamiento por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra todos las reservas en AlohAndes y los devuelve como una lista de VOReserva
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOReserva con todos las reservas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAlojamiento> darVOAlojamiento ()
	{
		log.info ("Generando los VO de reserva");    

		List<VOAlojamiento> voTipo = new LinkedList<VOAlojamiento> ();

		for (Alojamiento alo : pp.darAlojamientos() )
		{
			voTipo.add(alo);
		}
		log.info ("Generando los VO de Alojamientos: " + voTipo.size() + " existentes");
		return voTipo;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los Operadores
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una operador
	 * Adiciona entradas al log de la aplicación
	 * @param idOperador - El identificador del alojamiento que se desea Operadorr 
	 * @param nombre - El identificador del miembro que desea realizar la Operador 
	 * @param registroDeCamaraYComercio -  si esta subscrito o tiene el certitificado.
	 * @param SuperIntendensiaDeTurismo - si esta subscrito o tiene el certitificado.
	 * @param calidad - La calidad
	 * @param tipoOperador - El tipo operador 
	 * @return El objeto operador adicionado. null si ocurre alguna Excepción
	 */
	public Operador adicionarOperador (String nombre, Boolean  registroDeCamaraYComercio, Boolean SuperIntendensiaDeTurismo, int  calidad, String  tipoOperador)
	{

		Operador operador = pp.adicionarOperador(nombre, registroDeCamaraYComercio, SuperIntendensiaDeTurismo, calidad, tipoOperador);
		log.info ("Adicionanda operador: " + operador);
		return operador;
	}

	/**
	 * Elimina una operador por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idoperador - El id de la operador
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminaroperadorPorId (long idoperador)
	{
		log.info ("Eliminando operador por id: " + idoperador);
		long resp = pp.eliminarOperadorPorId (idoperador);
		log.info ("Eliminando operador por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una operador y su información básica, según su identificador
	 * @param idoperador - El identificador de la operador buscada
	 * @return Un objeto operador que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public Operador darOperadorPorNombre (long idoperador)
	{
		log.info ("Dar información de una operador por id: " + idoperador);
		Operador operador = pp.darOperadorPorId (idoperador);
		log.info ("Buscando operador por Id: " + operador != null ? operador : "NO EXISTE");
		return operador;
	}

	/**
	 * Encuentra todos las operadors en AlohAndes y los devuelve como una lista de VOoperador
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOoperador con todos las operadors que conoce la aplicación, llenos con su información básica
	 */
	public List<VOOperador> darVOOperador ()
	{
		log.info ("Generando los VO de operador");    

		List<VOOperador> voTipos = new LinkedList<VOOperador> ();

		for (Operador tb : pp.darOperadores() )
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de operadors: " + voTipos.size() + " existentes");
		return voTipos;
	}


	/* ****************************************************************
	 * 			Métodos para manejar los Miembros de la comunidad
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una Miembros de la comunidad
	 * Adiciona entradas al log de la aplicación
	 * @param id de la comunidad - El identificador del Miembros de la comunidad que se desea Miembros de la comunidadr 
	 * @param nombre - El identificador del miembro que desea realizar la Miembros de la comunidad 
	 * @param tipoID -  si esta subscrito o tiene el certitificado.
	 * @param tipoMiembroComunidadUniversitaria - si esta subscrito o tiene el certitificado.
	 * @return El objeto Miembros de la comunidad adicionado. null si ocurre alguna Excepción
	 */
	public MiembroComunidadUniversitaria adicionarMiembroComunidadUniversitaria (long id, String  tipoID, String nombre, String  tipoMiembroComunidadUniversitaria)
	{

		MiembroComunidadUniversitaria miembroComunidadUniversitaria = pp.adicionarMiembroComunidadUniversitaria(id, tipoID, nombre, tipoMiembroComunidadUniversitaria);
		log.info ("Adicionanda MiembroComunidadUniversitaria: " + miembroComunidadUniversitaria);


		return miembroComunidadUniversitaria;
	}



	/**
	 * Encuentra todos las reservas en AlohAndes y los devuelve como una lista de VOReserva
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOReserva con todos las reservas que conoce la aplicación, llenos con su información básica
	 */
	public List<VOMiembroComunidadUniversitaria> darVOMiembroComunidadUniversitaria ()
	{
		log.info ("Generando los VO de miembros");    

		List<VOMiembroComunidadUniversitaria> voTipo = new LinkedList<VOMiembroComunidadUniversitaria> ();

		for (MiembroComunidadUniversitaria alo : pp.darMiembrosComunidadUniversitaria() )
		{
			voTipo.add(alo);
		}
		log.info ("Generando los VO de MiembroComunidadUniversitarias: " + voTipo.size() + " existentes");
		return voTipo;
	}


	/* ****************************************************************
	 * 		Requerimientos funcionales de consulta
	 *****************************************************************/


	/**
	 * RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO
	 *  
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de parejas [id, dineroRecibido]
	 */
	public List<long []> darRFC1paraHabitaciones ()
	{
		log.info ("Listando Alojamientos y cuantas veces ha sido reservado");
		List<long []> tuplas = pp.darRFC1paraHabitaciones();
		log.info ("Listando Alojamientos y cuantas veces ha sido reservado: Listo!");
		return tuplas;
	}
	
	/**
	 * RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO
	 *  
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de parejas [id, dineroRecibido]
	 */
	public List<long []> darRFC1paraApartamentos ()
	{
		log.info ("Listando Alojamientos y cuantas veces ha sido reservado");
		List<long []> tuplas = pp.darRFC1paraApartamentos();
		log.info ("Listando Alojamientos y cuantas veces ha sido reservado: Listo!");
		return tuplas;
	}
	
	/**
	 * RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO
	 *  
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de parejas [id, dineroRecibido]
	 */
	public List<long []> darRFC1paraViviendaComunidad ()
	{
		log.info ("Listando Alojamientos y cuantas veces ha sido reservado");
		List<long []> tuplas = pp.darRFC1paraViviendaComunidad();
		log.info ("Listando Alojamientos y cuantas veces ha sido reservado: Listo!");
		return tuplas;
	}


	/**
	 * RFC2- MOSTRAR LAS 20 OFERTAS MÁS POPULARES
	 * 
	 * Encuentra los alojamientos que conoce la aplicación y el número que han sido reservados cada uno, 
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de parejas [IDalojamiento, numreservas]
	 */
	public List<long []> darRFC2 ()
	{
		log.info ("Listando Alojamientos y cuantas veces ha sido reservado");
		List<long []> tuplas = pp.darRFC2();
		log.info ("Listando Alojamientos y cuantas veces ha sido reservado: Listo!");
		return tuplas;
	}

//	/*
//	 * RFC3- MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS
//	 */
//	public double RFC3 ()
//	{
//
//	}
//
//	/*
//	 * RFC4 - MOSTRAR LOS ALOJAMIENTOS DISPONIBLES EN UN RANGO DE FECHAS, QUE CUMPLEN CON UN CONJUNTO DE REQUERIMIENTOS DE DOTACIÓN O SERVICIOS. 
//	 * 		  POR EJEMPLO, COCINETA, TV CABLE, INTERNET, SALA.
//	 */
//	public VOAlojamiento RFC4 ()
//	{
//
//	}
//
//	/*
//	 * RFC5- MOSTRAR EL USO DE ALOHANDES PARA CADA TIPO DE USUARIO DE LA COMUNIDAD
//	 */
//	public double RFC5 ()
//	{
//
//	}
//
//	/*
//	 * RFC6- MOSTRAR EL USO DE ALOHANDES PARA UN USUARIO DADO (NÚMERO DE NOCHES O MESES CONTRATADOS, CARACTERÍSTICAS DEL ALOJAMIENTO UTILIZADO, DINERO PAGADO.
//	 */
//	public double RFC6 ()
//	{
//
//	}
//
//	/*
//	 * RFC7- ANALIZAR LA OPERACIÓN DE ALOHANDES
//	 * Para una unidad de tiempo definido (por ejemplo, semana o mes) y un tipo de alojamiento, considerando todo el tiempo de operación de AloHandes, 
//	 * indicar cuáles fueron las fechas de mayor demanda (mayor cantidad de alojamientos ocupados), 
//	 * las de mayores ingresos (mayor cantidad de dinero recibido) y las de menor ocupación.
//	 */
//	public double RFC7 ()
//	{
//
//	}
//
//	/*
//	 * RFC8- ENCONTRAR LOS CLIENTES FRECUENTES
//		Para un alojamiento dado, encontrar la información de sus clientes frecuentes. 
//		se considera frecuente a un cliente si ha utilizado (o tiene reservado) 
//		ese alojamiento por lo menos en tres ocasiones o por lo menos 15 noches, durante todo el periodo de operación de AlohAndes.
//	 */
//	public double RFC8 ()
//	{
//
//	}
//
//	/*
//	 * RFC9- ENCONTRAR LAS OFERTAS DE ALOJAMIENTO QUE NO TIENEN MUCHA DEMANDA
//	 * 	Encontrar las ofertas de alojamiento que no han recibido clientes en periodos superiores a 
//	 * 1 mes, durante todo el periodo de operación de AlohAndes.
//	 */
//	public double RFC9 ()
//	{
//
//	}





	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AlohAndes
	 * 
	 * @return Un arreglo con 8 números que indican el número de tuplas borradas en las tablas ALOJAMIENTOS, APARTAMENTOS, HABITACIONES, 
	 * MIEMBRO COMUNIDAD UNIVERSITARIA, OPERADORES, RESERVA y SERVIVICIOS, VIVIENDA COMUNIDAD, respectivamente
	 */
	public long [] limpiarAlohAndes ()
	{
		log.info ("Limpiando la BD de ALOHAANDES");
		long [] borrrados = pp.limpiarAlohAndes();	
		log.info ("Limpiando la BD de ALOHAANDES: Listo!");
		return borrrados;
	}

}
