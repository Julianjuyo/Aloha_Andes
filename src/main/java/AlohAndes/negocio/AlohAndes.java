package AlohAndes.negocio;

import AlohAndes.persistencia.PersistenciaAlohAndes;

import java.sql.Array;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
	 * 			Métodos para manejar las Desabilitaciones de Alojamiento
	 *****************************************************************/


	public void DeshabilitarAlojamiento( long idAlojamiento, Date fechaInicio, Date fechaFin)
	{

		String habilitado ="N";
		pp.cambiarhabilitadoDeUnAlojamiento(idAlojamiento, habilitado, fechaInicio, fechaFin);
		
		
		List<Reserva> reAntigua = pp.darReservasPorIdAlojamiento(idAlojamiento);
		
		for (int i = 0; i < reAntigua.size(); i++) {

			
			Date FechaInicioYAHecha = reAntigua.get(i).getDiaReserva();
			Date FechaFinYAHecha = pp.sumarDiasFecha(FechaInicioYAHecha, reAntigua.get(i).getTiempoDias());
			
			if(fechaInicio.after(FechaFinYAHecha) || fechaFin.before(FechaInicioYAHecha)){ 
				log.info ("Esta reserva no se vio afectada");  
			}
			else {
				
				ProcesoDeRelocalizacion(reAntigua.get(i));
				
			}
		}
		
		
	}

	/**
	 * Me retorna el tipo del alojamientos
	 * @param id
	 * @return
	 */
	public String darTipo (long id){
		String t =" ";

		if(pp.darHabitacionPorId(id).getTipoOperadorHabitacion()== "Hotel" ) {
			t= "Hotel";
		}
		else if(pp.darHabitacionPorId(id).getTipoOperadorHabitacion()== "Hostal" ) {
			t= "Hostal";
		}
		else if(pp.darHabitacionPorId(id).getTipoOperadorHabitacion()== "ViviendaUniv" ) 
		{
			t = "ViviendaUniv";
		}
		else if(pp.darHabitacionPorId(id).getTipoOperadorHabitacion()== "PersonaNatural" ) {
			t= "PersonaNatural";
		}
		else if(pp.darViviendaComunidadPorId(id).getIdAlojamiento()== id ) {
			t= "ViviendaComunidad";
		}
		else if(pp.darApartamentoPorId(id).getIdAlojamiento() ==id ) {
			t= "Apartamento";
		}
		return t;
	}


	public void habilitarAlojamiento( long idAlojamiento)
	{
		String habilitado ="Y";
		pp.cambiarhabilitadoDeUnAlojamiento(idAlojamiento, habilitado, null, null);


	}


		public void  ProcesoDeRelocalizacion (Reserva re)
		{
			String tipo=  darTipo(re.getIdAlojamiento());
			
			LinkedList<Integer> ids =  idDisponibles(tipo);
			
			long idNuevo = ids.getFirst();
			
			adicionarReserva(idNuevo , re.getIdMiembro(), re.getTipoID(), re.getDiaReserva(), re.getTiempoDias());
			
			eliminarReservaPorId(re.getIdAlojamiento());
			
		}








	/* ****************************************************************
	 * 			Métodos para manejar las RESERVAS COLECTIVAS
	 *****************************************************************/


	/**
	 * Este metodo lo que hace es dar el numero de la habitaciones ocupadas
	 * @param tipoDeAlojamiento
	 * @return
	 */
	public int numerDeOcupadas(String tipoDeAlojamiento )
	{

		List<Reserva> listaReservas= pp.darReservas();

		int num= 0;

		if(tipoDeAlojamiento.equals("Hotel")){

			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Habitacion> hab = pp.darHabitaciones();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(j).getIdAlojamiento() == actual && hab.get(j).getTipoHabitacion().equals("Hotel")){
						num++;
					}

				}
			}
		}

		else if(tipoDeAlojamiento.equals("Hostal")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Habitacion> hab = pp.darHabitaciones();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual&& hab.get(j).getTipoHabitacion().equals("Hostal")){
						num++;
					}

				}
			}

		}
		else if(tipoDeAlojamiento.equals("ViviendaUniv")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Habitacion> hab = pp.darHabitaciones();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual&&hab.get(j).getTipoHabitacion().equals("ViviendaUniv")){
						num++;
					}

				}
			}

		}
		else if(tipoDeAlojamiento.equals("PersonaNatural")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Habitacion> hab = pp.darHabitaciones();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual&&hab.get(j).getTipoHabitacion().equals("PersonaNatural")){
						num++;
					}

				}
			}

		}
		else if(tipoDeAlojamiento.equals("Apartamento")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Apartamento> hab = pp.darApartamentos();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual ){
						num++;
					}

				}
			}

		}
		else if(tipoDeAlojamiento.equals("ViviendaComunidad")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<ViviendaComunidad> hab = pp.darViviendaComunidades();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual ){
						num++;
					}

				}
			}

		}


		return num;
	}


	/**
	 * Verifica que idAlojamientos estan disponibles
	 * @param tipo
	 * @return
	 */
	public LinkedList idDisponibles(String tipoDeAlojamiento){

		List<Reserva> listaReservas= pp.darReservas();


		LinkedList<Integer> ids = new LinkedList<Integer>();


		if(tipoDeAlojamiento.equals("Hotel")){

			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Habitacion> hab = pp.darHabitaciones();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(j).getIdAlojamiento() == actual && hab.get(j).getTipoHabitacion().equals("Hotel")){
					}
					else {
						ids.add((int) hab.get(i).getIdAlojamiento());
					}

				}
			}
		}

		else if(tipoDeAlojamiento.equals("Hostal")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Habitacion> hab = pp.darHabitaciones();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual&& hab.get(j).getTipoHabitacion().equals("Hostal")){

					}
					else {
						ids.add((int) hab.get(i).getIdAlojamiento());
					}

				}
			}

		}
		else if(tipoDeAlojamiento.equals("ViviendaUniv")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Habitacion> hab = pp.darHabitaciones();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual&&hab.get(j).getTipoHabitacion().equals("ViviendaUniv")){

					}
					else {
						ids.add((int) hab.get(i).getIdAlojamiento());
					}

				}
			}

		}
		else if(tipoDeAlojamiento.equals("PersonaNatural")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Habitacion> hab = pp.darHabitaciones();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual&&hab.get(j).getTipoHabitacion().equals("PersonaNatural")){

					}
					else {
						ids.add((int) hab.get(i).getIdAlojamiento());
					}

				}
			}

		}
		else if(tipoDeAlojamiento.equals("Apartamento")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<Apartamento> hab = pp.darApartamentos();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual ){

					}
					else {
						ids.add((int) hab.get(i).getIdAlojamiento());
					}

				}
			}

		}
		else if(tipoDeAlojamiento.equals("ViviendaComunidad")) {
			for (int i = 0; i < listaReservas.size(); i++){

				long actual = listaReservas.get(i).getIdAlojamiento();
				List<ViviendaComunidad> hab = pp.darViviendaComunidades();

				for (int j = 0; j < hab.size(); j++){

					if(hab.get(i).getIdAlojamiento()== actual ){

					}
					else {
						ids.add((int) hab.get(i).getIdAlojamiento());
					}

				}
			}

		}


		return ids;

	}

	
	/**
	 * Adiciona de manera persistente las reservas colectivas.
	 * Adiciona entradas al log de la aplicación
	 * @param tipoDeAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
	 * @param cantidadDeAlojamientos - El numero de alojamientos que desea reservas
	 * @param servicios - Los servicios que se desean t realizar en la reserva (Debe existir en la tabla Servicios)
	 * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
	 */
	public LinkedList<Integer> adicionarReservaColectiva (String tipoDeAlojamiento, int cantidadDeAlojamientos, String[] servicios, Date diaReserva, long  idMiembro,String tipoId,int tiempoDias )
	{
		log.info ("Verificando si se puede satisfacer la solicitud ");   
		int num = numerDeOcupadas(tipoDeAlojamiento);



		if(tipoDeAlojamiento.equals("Hotel") ){

			List<Habitacion> lista =  pp.darHabitaciones();

			int disponibles = lista.size()- num;
			if(disponibles>cantidadDeAlojamientos||disponibles==cantidadDeAlojamientos){
				LinkedList<Integer> ids = idDisponibles(tipoDeAlojamiento);
				for (int i = 0; i < cantidadDeAlojamientos; i++) {
					int idAlojamiento = ids.get(i);	
					adicionarReserva(idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);		
				}			
				return ids;
			}
			else {
				return null;
			}
		}

		else if(tipoDeAlojamiento.equals("Hostal")) {
			List<Habitacion> lista =  pp.darHabitaciones();

			int disponibles = lista.size()- num;
			if(disponibles>cantidadDeAlojamientos||disponibles==cantidadDeAlojamientos){
				LinkedList<Integer> ids = idDisponibles(tipoDeAlojamiento);
				for (int i = 0; i < cantidadDeAlojamientos; i++) {
					int idAlojamiento = ids.get(i);	
					adicionarReserva(idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);		

				}
				return ids;
			}
			else {
				return null;
			}

		}

		else if(tipoDeAlojamiento.equals("ViviendaUniv")) {
			List<Habitacion> lista =  pp.darHabitaciones();

			int disponibles = lista.size()- num;
			if(disponibles>cantidadDeAlojamientos||disponibles==cantidadDeAlojamientos){
				LinkedList<Integer> ids = idDisponibles(tipoDeAlojamiento);
				for (int i = 0; i < cantidadDeAlojamientos; i++) {
					int idAlojamiento = ids.get(i);	
					adicionarReserva(idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);		

				}
				return ids;
			}
			else {
				return null;
			}

		}

		else if(tipoDeAlojamiento.equals("PersonaNatural")) {
			List<Habitacion> lista =  pp.darHabitaciones();

			int disponibles = lista.size()- num;
			if(disponibles>cantidadDeAlojamientos||disponibles==cantidadDeAlojamientos){
				LinkedList<Integer> ids = idDisponibles(tipoDeAlojamiento);
				for (int i = 0; i < cantidadDeAlojamientos; i++) {
					int idAlojamiento = ids.get(i);	
					adicionarReserva(idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);		

				}
				return ids;
			}
			else {
				return null;
			}

		}

		else if(tipoDeAlojamiento.equals("Apartamento")) {
			List<Apartamento> lista =  pp.darApartamentos();

			int disponibles = lista.size()- num;
			if(disponibles>cantidadDeAlojamientos||disponibles==cantidadDeAlojamientos){
				LinkedList<Integer> ids = idDisponibles(tipoDeAlojamiento);
				for (int i = 0; i < cantidadDeAlojamientos; i++) {
					int idAlojamiento = ids.get(i);	
					adicionarReserva(idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);		

				}
				return ids;
			}
			else {
				return null;
			}

		}

		else if(tipoDeAlojamiento.equals("ViviendaComunidad")) {
			List<ViviendaComunidad> lista =  pp.darViviendaComunidades();

			int disponibles = lista.size()- num;
			if(disponibles>cantidadDeAlojamientos||disponibles==cantidadDeAlojamientos){
				LinkedList<Integer> ids = idDisponibles(tipoDeAlojamiento);
				for (int i = 0; i < cantidadDeAlojamientos; i++) {
					int idAlojamiento = ids.get(i);	
					adicionarReserva(idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);		

				}
				return ids;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}


	/**
	 * Elimina una reserva Colectiva por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idReserva - El id de la reserva
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public double eliminarReservaColectiva (LinkedList<Integer> idReservasColectiva)
	{

		double precioPenalizacion =0;


		for (int i = 0; i < idReservasColectiva.size(); i++) 
		{
			long id = idReservasColectiva.get(i);
			log.info ("Eliminando reserva por id: " + id);

			long resp = pp.eliminarReservaPorId (id);

			precioPenalizacion += 20000;

			log.info ("Eliminando reserva por id: " + id + " tuplas eliminadas");
		}

		return precioPenalizacion;
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
	public Operador darOperadorPorId (long idoperador)
	{
		log.info ("Dar información de una operador por id: " + idoperador);
		Operador operador = pp.darOperadorPorId (idoperador);
		log.info ("Buscando operador por Id: " + operador != null ? operador : "NO EXISTE");
		return operador;
	}
	
	/**
	 * Encuentra una operador y su información básica, según su identificador
	 * @param nombre - El nombre de la operador buscada
	 * @return Un objeto operador que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public Operador darOperadorPorNombre (String nombre)
	{
		log.info ("Dar información de una operador por nombre: " + nombre);
		Operador operador = pp.darOperadorPorNombre (nombre);
		log.info ("Buscando operador por Id: " + operador != null ? operador : "NO EXISTE");
		return operador;
	}
	

	/**
	 * Encuentra todas las Operadors en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Operadores con todos las Operadoress que conoce la aplicación, llenos con su información básica
	 */
	public List<Operador> darOperadores ()
	{
        log.info ("Consultando Operadores");
        List<Operador> Operadores = pp.darOperadores ();	
        log.info ("Consultando Operadores: " + Operadores.size() + " Operadores existentes");
        return Operadores;
	}
	
	/**
	 * Encuentra todos las Operadores en AlohAndes y los devuelve como una lista de VOoperador
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOoperador con todos las Operadores que conoce la aplicación, llenos con su información básica
	 */
	public List<VOOperador> darVOOperador ()
	{
		log.info ("Generando los VO de operador");    

		List<VOOperador> voTipos = new LinkedList<VOOperador> ();

		for (Operador tb : pp.darOperadores() )
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de operadores: " + voTipos.size() + " existentes");
		return voTipos;
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
	public Reserva adicionarReserva (long idAlojamiento, long idMiembro, String tipoId, Date diaReserva, int tiempoDias)
	{
		log.info ("Adicionando Reserva del alojamiento: " + idAlojamiento);
		Reserva reserva = pp.adicionarReserva(idAlojamiento, idMiembro, tipoId,  diaReserva, tiempoDias);
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
	 * Encuentra las reservas y su información básica, según su identificador de alojamiento
	 * @param idReserva - El identificador de la reserva buscada
	 * @return Un objeto Reserva que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public List<Reserva>  darReservaPorIdAlojamiento (long idAlojamiento)
	{
		log.info ("Dar información de una reserva por id: " + idAlojamiento);
		List<Reserva> reservas = pp.darReservasPorIdAlojamiento(idAlojamiento);
		log.info ("Buscando reserva por Id: " + reservas != null ? reservas : "NO EXISTE");
		return reservas;
	}
	
	/**
	 * Encuentra todas las Reservas en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Reservas con todos las Reservass que conoce la aplicación, llenos con su información básica
	 */
	public List<Reserva> darReservas ()
	{
        log.info ("Consultando Reservas");
        List<Reserva> Reservas = pp.darReservas ();	
        log.info ("Consultando Reservas: " + Reservas.size() + " Reservas existentes");
        return Reservas;
	}
	

	/**
	 * Encuentra todos los tipos de Reserva en Parranderos y los devuelve como una lista de VOTipoReserva
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOReserva con todos las Reservas que conoce la aplicación, llenos con su información básica
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
	
	
	/**
	 * Cambiar reserva dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idReserva el id de l reseva a modificiar 
	 * @param idAlojamiento el id del alojamiento que se tiene
	 * @param idMiembro id de la persona que reservo
	 * @param tipoId el tipo id de la persona que reservo
	 * @param diaReserva el nuevo dia de la reserva
	 * @param tiempoDias el tiempo de hospejade.
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que una reserva con ese identificador no existe
	 */
	public long cambiarUnaReserva (long idReserva, long idAlojamiento, long idMiembro, String tipoId, Date diaReserva, int tiempoDias)
	{
        log.info ("Cambiando reserva numero: " + idReserva );
        long cambios = pp.cambiarUnaReserva(idReserva, idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);
        return cambios;
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
	 * @param idAlojamiento - El id de la Alojamiento
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
	 * Encuentra todos las Alojamientos en AlohAndes y los devuelve como una lista de VOAlojamiento
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOAlojamiento con todos las Alojamientos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAlojamiento> darVOAlojamiento ()
	{
		log.info ("Generando los VO de Alojamiento");    

		List<VOAlojamiento> voTipo = new LinkedList<VOAlojamiento> ();

		for (Alojamiento alo : pp.darAlojamientos() )
		{
			voTipo.add(alo);
		}
		log.info ("Generando los VO de Alojamientos: " + voTipo.size() + " existentes");
		return voTipo;
	}
	
	/**
	 * Encuentra todas las Alojamientos en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Alojamientos con todos las Alojamientoss que conoce la aplicación, llenos con su información básica
	 */
	public List<Alojamiento> darAlojamientos ()
	{
        log.info ("Consultando Alojamientos");
        List<Alojamiento> Alojamientos = pp.darAlojamientos ();	
        log.info ("Consultando Alojamientos: " + Alojamientos.size() + " Alojamientos existentes");
        return Alojamientos;
	}
	
	/**
	 * Cambiar Alojamiento dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento el id de l reseva a modificiar 
	 * @param idAlojamiento el id del alojamiento que se tiene
	 * @param idMiembro id de la persona que reservo
	 * @param tipoId el tipo id de la persona que reservo
	 * @param diaAlojamiento el nuevo dia de la Alojamiento
	 * @param tiempoDias el tiempo de hospejade.
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que una Alojamiento con ese identificador no existe
	 */
	public long cambiarUnaAlojamiento ( long idAlojamiento, String habilitado ,  Date  fechaInicio, Date fechaFin)
	{
        log.info ("Cambiando Alojamiento numero: " + idAlojamiento );
        long cambios = pp.cambiarhabilitadoDeUnAlojamiento(idAlojamiento, habilitado, fechaInicio, fechaFin);
        return cambios;
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
	 * Elimina una MiembroComunidadUniversitaria por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idMiembroComunidadUniversitaria - El id de la MiembroComunidadUniversitaria
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarMiembroComunidadUniversitariaPorId (long idMiembroComunidadUniversitaria)
	{
		log.info ("Eliminando MiembroComunidadUniversitaria por id: " + idMiembroComunidadUniversitaria);
		long resp = pp.eliminarMiembroComunidadUniversitaria(idMiembroComunidadUniversitaria);
		log.info ("Eliminando MiembroComunidadUniversitaria por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una MiembroComunidadUniversitaria y su información básica, según su identificador
	 * @param idMiembroComunidadUniversitaria - El identificador de la MiembroComunidadUniversitaria buscada
	 * @return Un objeto MiembroComunidadUniversitaria que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public MiembroComunidadUniversitaria darMiembroComunidadUniversitariaPorId (long idMiembroComunidadUniversitaria)
	{
		log.info ("Dar información de una MiembroComunidadUniversitaria por id: " + idMiembroComunidadUniversitaria);
		MiembroComunidadUniversitaria MiembroComunidadUniversitaria = pp.darMiembroComunidadUniversitariaPorId (idMiembroComunidadUniversitaria);
		log.info ("Buscando MiembroComunidadUniversitaria por Id: " + MiembroComunidadUniversitaria != null ? MiembroComunidadUniversitaria : "NO EXISTE");
		return MiembroComunidadUniversitaria;
	}

	
	/**
	 * Encuentra todas las MiembroComunidadUniversitarias en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos MiembroComunidadUniversitarias con todos las MiembroComunidadUniversitariass que conoce la aplicación, llenos con su información básica
	 */
	public List<MiembroComunidadUniversitaria> darMiembrosComunidadUniversitaria ()
	{
        log.info ("Consultando MiembroComunidadUniversitarias");
        List<MiembroComunidadUniversitaria> MiembroComunidadUniversitarias = pp.darMiembrosComunidadUniversitaria ();	
        log.info ("Consultando MiembroComunidadUniversitarias: " + MiembroComunidadUniversitarias.size() + " MiembroComunidadUniversitarias existentes");
        return MiembroComunidadUniversitarias;
	}
	


	
	
	/**
	 * Cambiar MiembroComunidadUniversitaria dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idMiembroComunidadUniversitaria el id de l reseva a modificiar 
	 * @param id del miebro que puede ser CC, CE, TI
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que una MiembroComunidadUniversitaria con ese identificador no existe
	 */
	public long cambiarUnaMiembroComunidadUniversitaria (long idMiembroComunidadUniversitaria,  String id)
	{
        log.info ("Cambiando MiembroComunidadUniversitaria numero: " + idMiembroComunidadUniversitaria );
        long cambios = pp.cambiarIdDeUnMiembroComunidadUniversitaria(idMiembroComunidadUniversitaria, id);
        return cambios;
	}



	/**
	 * Encuentra todos las MiembroComunidadUniversitarias en AlohAndes y los devuelve como una lista de VOMiembroComunidadUniversitaria
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOMiembroComunidadUniversitaria con todos las MiembroComunidadUniversitarias que conoce la aplicación, llenos con su información básica
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
	 * 		Meotodos para la clase de Habitacion
	 *****************************************************************/

	/**
	 * Adiciona de manera persistente una Habitacion
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento que se desea Habitacionr (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la Habitacion (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la Habitacion (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea Habitacionr el alojamiento
	 * @return El objeto Habitacion adicionado. null si ocurre alguna Excepción
	 */
	public Habitacion adicionarHabitacion (Boolean habilitada, Date  fechaInicio, Date fechaFin, long idOperador, String direccion, Double precio, String numHabitacion, String tipoHabitacion, String tipoOperadorHabitacion)
{
		log.info ("Adicionando Habitacion del alojamiento: ");
		Habitacion Habitacion = pp.adicionarHabitacion(habilitada, fechaInicio, fechaFin, idOperador, direccion, precio, numHabitacion, tipoHabitacion, tipoOperadorHabitacion);
		log.info ("Adicionanda Habitacion: " + Habitacion);
		return Habitacion;
	}

	/**
	 * Elimina una Habitacion por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idHabitacion - El id de la Habitacion
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionPorId (long idHabitacion)
	{
		log.info ("Eliminando Habitacion por id: " + idHabitacion);
		long resp = pp.eliminarHabitacionPorId (idHabitacion);
		log.info ("Eliminando Habitacion por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una Habitacion y su información básica, según su identificador
	 * @param idHabitacion - El identificador de la Habitacion buscada
	 * @return Un objeto Habitacion que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public Habitacion darHabitacionPorId (long idHabitacion)
	{
		log.info ("Dar información de una Habitacion por id: " + idHabitacion);
		Habitacion Habitacion = pp.darHabitacionPorId (idHabitacion);
		log.info ("Buscando Habitacion por Id: " + Habitacion != null ? Habitacion : "NO EXISTE");
		return Habitacion;
	}
	
	
	/**
	 * Encuentra todas las Habitaciones en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Habitaciones con todos las Habitacioness que conoce la aplicación, llenos con su información básica
	 */
	public List<Habitacion> darHabitaciones ()
	{
        log.info ("Consultando Habitaciones");
        List<Habitacion> Habitaciones = pp.darHabitaciones ();	
        log.info ("Consultando Habitaciones: " + Habitaciones.size() + " Habitaciones existentes");
        return Habitaciones;
	}
	
	
	/**
	 * Encuentra todos las Habitaciones en AlohAndes y los devuelve como una lista de VOHabitacion
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOHabitacion con todos las Habitaciones que conoce la aplicación, llenos con su información básica
	 */
	public List<VOHabitacion> darVOHabitacion ()
	{
		log.info ("Generando los VO de Habitacion");    

		List<VOHabitacion> voTipos = new LinkedList<VOHabitacion> ();

		for (Habitacion tb : pp.darHabitaciones() )
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Habitaciones: " + voTipos.size() + " existentes");
		return voTipos;
	}

	/* ****************************************************************
	 * 		Meotodos para la clase de Vivienda comunidad
	 *****************************************************************/

	

	/**
	 * Adiciona de manera persistente una ViviendaComunidad
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento que se desea ViviendaComunidadr (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la ViviendaComunidad (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la ViviendaComunidad (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea ViviendaComunidadr el alojamiento
	 * @return El objeto ViviendaComunidad adicionado. null si ocurre alguna Excepción
	 */
	public ViviendaComunidad adicionarViviendaComunidad (Boolean habilitada, Date  fechaInicio, Date fechaFin, long idOperador, String direccion, Double precio, int numHabitaciones, Boolean menaje, Boolean seguroArrendatario, String caractSeguro)
	{
		log.info ("Adicionando ViviendaComunidad del alojamiento: " );
		ViviendaComunidad ViviendaComunidad = pp.adicionarViviendaComunidad(habilitada, fechaInicio, fechaFin, idOperador, direccion, precio, numHabitaciones, menaje, seguroArrendatario, caractSeguro);
		log.info ("Adicionanda ViviendaComunidad: " + ViviendaComunidad);
		return ViviendaComunidad;
	}

	/**
	 * Elimina una ViviendaComunidad por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idViviendaComunidad - El id de la ViviendaComunidad
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarViviendaComunidadPorId (long idViviendaComunidad)
	{
		log.info ("Eliminando ViviendaComunidad por id: " + idViviendaComunidad);
		long resp = pp.eliminarViviendaComunidadPorId (idViviendaComunidad);
		log.info ("Eliminando ViviendaComunidad por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una ViviendaComunidad y su información básica, según su identificador
	 * @param idViviendaComunidad - El identificador de la ViviendaComunidad buscada
	 * @return Un objeto ViviendaComunidad que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public ViviendaComunidad darViviendaComunidadPorId (long idViviendaComunidad)
	{
		log.info ("Dar información de una ViviendaComunidad por id: " + idViviendaComunidad);
		ViviendaComunidad ViviendaComunidad = pp.darViviendaComunidadPorId (idViviendaComunidad);
		log.info ("Buscando ViviendaComunidad por Id: " + ViviendaComunidad != null ? ViviendaComunidad : "NO EXISTE");
		return ViviendaComunidad;
	}
	
	
	/**
	 * Encuentra todas las darViviendaComunidades en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos darViviendaComunidades con todos las darViviendaComunidadess que conoce la aplicación, llenos con su información básica
	 */
	public List<ViviendaComunidad> dardarViviendaComunidades ()
	{
        log.info ("Consultando darViviendaComunidades");
        List<ViviendaComunidad> darViviendaComunidades = pp.darViviendaComunidades ();	
        log.info ("Consultando darViviendaComunidades: " + darViviendaComunidades.size() + " darViviendaComunidades existentes");
        return darViviendaComunidades;
	}
	
	
	/**
	 * Encuentra todos las darViviendaComunidades en AlohAndes y los devuelve como una lista de VOViviendaComunidad
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOViviendaUniversitaria con todos las darViviendaComunidades que conoce la aplicación, llenos con su información básica
	 */
	public List<VOViviendaComunidad> darVOViviendaComunidad ()
	{
		log.info ("Generando los VO de ViviendaComunidad");    

		List<VOViviendaComunidad> voTipos = new LinkedList<VOViviendaComunidad> ();

		for (ViviendaComunidad tb : pp.darViviendaComunidades() )
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de darViviendaComunidades: " + voTipos.size() + " existentes");
		return voTipos;
	}



	/* ****************************************************************
	 * 		Meotodos para la clase de Apartamentos
	 *****************************************************************/

	

	/**
	 * Adiciona de manera persistente una Apartamento
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento que se desea Apartamentor (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la Apartamento (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la Apartamento (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea Apartamentor el alojamiento
	 * @return El objeto Apartamento adicionado. null si ocurre alguna Excepción
	 */
	public Apartamento adicionarApartamento (Boolean habilitada, Date  fechaInicio, Date fechaFin, String direccion, Double precio, long dueno, Double valorAdmin, Boolean amobaldo)
	{
		log.info ("Adicionando Apartamento del alojamiento: ");
		Apartamento Apartamento = pp.adicionarApartamento(habilitada, fechaInicio, fechaFin, direccion, precio, dueno, valorAdmin, amobaldo);
		log.info ("Adicionanda Apartamento: " + Apartamento);
		return Apartamento;
	}

	/**
	 * Elimina una Apartamento por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idApartamento - El id de la Apartamento
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarApartamentoPorId (long idApartamento)
	{
		log.info ("Eliminando Apartamento por id: " + idApartamento);
		long resp = pp.eliminarApartamentoPorId (idApartamento);
		log.info ("Eliminando Apartamento por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una Apartamento y su información básica, según su identificador
	 * @param idApartamento - El identificador de la Apartamento buscada
	 * @return Un objeto Apartamento que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public Apartamento darApartamentoPorId (long idApartamento)
	{
		log.info ("Dar información de una Apartamento por id: " + idApartamento);
		Apartamento Apartamento = pp.darApartamentoPorId (idApartamento);
		log.info ("Buscando Apartamento por Id: " + Apartamento != null ? Apartamento : "NO EXISTE");
		return Apartamento;
	}
	
	
	/**
	 * Encuentra todas las Apartamentos en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Apartamentos con todos las Apartamentoss que conoce la aplicación, llenos con su información básica
	 */
	public List<Apartamento> darApartamentos ()
	{
        log.info ("Consultando Apartamentos");
        List<Apartamento> Apartamentos = pp.darApartamentos ();	
        log.info ("Consultando Apartamentos: " + Apartamentos.size() + " Apartamentos existentes");
        return Apartamentos;
	}
	
	
	/**
	 * Encuentra todos las Apartamentos en AlohAndes y los devuelve como una lista de VOApartamento
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOViviendaUniversitaria con todos las Apartamentos que conoce la aplicación, llenos con su información básica
	 */
	public List<VOApartamento> darVOApartamento ()
	{
		log.info ("Generando los VO de Apartamento");    

		List<VOApartamento> voTipos = new LinkedList<VOApartamento> ();

		for (Apartamento tb : pp.darApartamentos() )
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Apartamentos: " + voTipos.size() + " existentes");
		return voTipos;
	}

	
	
	/* ****************************************************************
	 * 			Métodos para manejar las SERVICIOS
	 *****************************************************************/
	
	
	
	/**
	 * Adiciona de manera persistente una Servicio
	 * Adiciona entradas al log de la aplicación
	 * @param idAlojamiento - El identificador del alojamiento que se desea Servicior (Debe existir en la tabla ALOJAMIENTOS)
	 * @param idMiembro - El identificador del miembro que desea realizar la Servicio (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tipoId - El tipo de identificación del miembro que desea realizar la Servicio (Debe existir en la tabla MIEM_CO_UNIV)
	 * @param tiempoDias - El número de días que se desea Servicior el alojamiento
	 * @return El objeto Servicio adicionado. null si ocurre alguna Excepción
	 */
	public Servicio adicionarServicio (long idAlojamiento, String descripcion, String nombre, Double precio,  Boolean TomaServicio)
	{
		log.info ("Adicionando Servicio del alojamiento: " + idAlojamiento);
		Servicio Servicio = pp.adicionarServicio(idAlojamiento, descripcion, nombre, precio, TomaServicio);
		log.info ("Adicionanda Servicio: " + Servicio);
		return Servicio;
	}

	/**
	 * Elimina una Servicio por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idServicio - El id de la Servicio
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioPorId (long idServicio)
	{
		log.info ("Eliminando Servicio por id: " + idServicio);
		long resp = pp.eliminarServicioPorId (idServicio);
		log.info ("Eliminando Servicio por id: " + resp + " tuplas eliminadas");
		return resp;
	}

	/**
	 * Encuentra una Servicio y su información básica, según su identificador
	 * @param idServicio - El identificador de la Servicio buscada
	 * @return Un objeto Servicio que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public Servicio darServicioPorId (long idServicio)
	{
		log.info ("Dar información de una Servicio por id: " + idServicio);
		Servicio Servicio = pp.darServicioPorId (idServicio);
		log.info ("Buscando Servicio por Id: " + Servicio != null ? Servicio : "NO EXISTE");
		return Servicio;
	}
	
	/**
	 * Encuentra las Servicios y su información básica, según su identificador de alojamiento
	 * @param idServicio - El identificador de la Servicio buscada
	 * @return Un objeto Servicio que corresponde con el identificador buscado y lleno con su información básica
	 * 		   Null, si un bebedor con dicho identificador no existe
	 */
	public List<Servicio>  darServicioPorIdAlojamiento (long idAlojamiento)
	{
		log.info ("Dar información de una Servicio por id: " + idAlojamiento);
		List<Servicio> Servicios = pp.darLosServiciosDeUnIdAlojamiento(idAlojamiento);
		log.info ("Buscando Servicio por Id: " + Servicios != null ? Servicios : "NO EXISTE");
		return Servicios;
	}
	
	/**
	 * Encuentra todas las Servicios en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Servicios con todos las Servicioss que conoce la aplicación, llenos con su información básica
	 */
	public List<Servicio> darServicios ()
	{
        log.info ("Consultando Servicios");
        List<Servicio> Servicios = pp.darServicios ();	
        log.info ("Consultando Servicios: " + Servicios.size() + " Servicios existentes");
        return Servicios;
	}
	

	/**
	 * Encuentra todos los tipos de Servicio en Parranderos y los devuelve como una lista de VOTipoServicio
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOServicio con todos las Servicios que conoce la aplicación, llenos con su información básica
	 */
	public List<VOServicio> darVOServicio ()
	{
		log.info ("Generando los VO de Servicio");    

		List<VOServicio> voTipos = new LinkedList<VOServicio> ();

		for (Servicio tb : pp.darServicios() )
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Servicios: " + voTipos.size() + " existentes");
		return voTipos;
	}
	
	
	/**
	 * Cambiar Servicio dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idServicio el id de l reseva a modificiar 
	 * @param descripcion
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que una Servicio con ese identificador no existe
	 */
	public long cambiarLaDescripcionDeUnServicio (long idServicio,  String Descripcion )
	{
        log.info ("Cambiando Servicio numero: " + idServicio );
        long cambios = pp.cambiarLaDescripcionDeUnServicio(idServicio, Descripcion);
        return cambios;
	}
	
	
	/**
	 * Cambiar Servicio dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idServicio el id de l reseva a modificiar 
	 * @param precio
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que una Servicio con ese identificador no existe
	 */
	public long cambiarElPrecioDeUnServicio (long idServicio,  Double precio )
	{
        log.info ("Cambiando el precio de un Servicio: " + idServicio );
        long cambios = pp.cambiarElPrecioDeUnServicio(idServicio, precio);
        return cambios;
	}
	
	/**
	 * Cambiar Servicio dado su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idServicio el id de l reseva a modificiar 
	 * @param toma
	 * @return El número de tuplas modificadas: 1 o 0. 0 significa que una Servicio con ese identificador no existe
	 */
	public long cambiarTomaServicio (long idServicio,  Boolean toma )
	{
        log.info ("Cambiando Servicio numero: " + idServicio );
        long cambios = pp.cambiarTomaServicio(idServicio, toma);
        return cambios;
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
