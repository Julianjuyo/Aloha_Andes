package AlohAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval_idAlojamiento (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqIdAlojamiento() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval_idReserva (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqIdReserva() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval_idServicio (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqIdServicio() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}
	
	
	
	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas ALOJAMIENTOS, OPERADORES, SERVICIOS,
	 * 		   VIVIENDA COMUNIDAD, HABITACIONES, MIEMBRO COMUNIDAD UNIVERSITARIA, RESERVA y  APARTAMENTOS. respectivamente
	 */
	public long [] limpiarAlohAndes (PersistenceManager pm)
	{
        Query qAlojamientos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlojamientos());          
        Query qoperadores = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperadores ());
        Query qServicios = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicios ());
        Query qViviendaComunidad = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendaComunidad ());
        Query qHabitaciones = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitaciones ());
        Query qmiembroComunidad = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMiemCoUniv ());
        Query qReserva = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservas ());
        Query qApartamentos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartamentos ());

        long alojamientosEliminados = (long) qAlojamientos.executeUnique ();
        long operadoresEliminados = (long) qoperadores.executeUnique ();
        long serviciosEliminadas = (long) qServicios.executeUnique ();
        long vieviendaComunidadEliminadas = (long) qViviendaComunidad.executeUnique ();
        long habitacionesEliminados = (long) qHabitaciones.executeUnique ();
        long miembroEliminados = (long) qmiembroComunidad.executeUnique ();
        long reservaEliminados = (long) qReserva.executeUnique ();
        long apartamentosEliminados = (long) qApartamentos.executeUnique ();
        return new long[] {alojamientosEliminados, operadoresEliminados, serviciosEliminadas, vieviendaComunidadEliminadas, 
        		habitacionesEliminados, miembroEliminados, reservaEliminados, apartamentosEliminados };
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC3 (PersistenceManager pm)
	{
        String sql = "SELECT t1 OfertaAlojamiento, NVL(((totales/reservados)*100), 0) IndiceOcupacion";
        sql += "FROM"; 
        sql += "(SELECT op.tipoOperador t1, count(*) totales\n" + 
        		"      FROM" + pp.darTablaAlojamientos()+" a,"+ pp.darTablaOperadores()+" op\n" + 
        		"            WHERE a.idoperador = op.id\n" + 
        		"      GROUP BY op.tipoOperador) LEFT JOIN";
        
        sql+=   "(SELECT op.tipoOperador t2, count(*) reservados\n" + 
        		"        FROM" + pp.darTablaAlojamientos()+ "a,"+ pp.darTablaOperadores()+ "op, " + pp.darTablaReservas()+ "r \n" + 
        		"        WHERE a.idoperador = op.id \n" + 
        		"        AND r.idalojamiento = a.id\n" + 
        		"        GROUP BY op.tipoOperador) ";
        sql += "ON t1 = t2;";
        
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar MOSTRAR LOS ALOJAMIENTOS DISPONIBLES EN UN RANGO DE FECHAS, QUE CUMPLEN CON UN CONJUNTO DE REQUERIMIENTOS DE DOTACIÓN O SERVICIOS.

	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC4 (PersistenceManager pm, Date rangoMenor, Date rangoMayor, String[] nombreServicio)
	{
        String sql = "SELECT a.id";
        sql += " FROM " +  pp.darTablaAlojamientos()+ "a,"+  pp.darTablaReservas() +"r,"+ pp.darTablaServicios()+" s\n" ;
       	sql	+= " WHERE r.idalojamiento = a.id";
       	sql += " AND  a.id= s.idalojamiento";
       	sql += " AND r.diareserva BETWEEN TO_DATE ("+rangoMenor+")AND TO_DATE ("+rangoMayor+")";
       	
       	for (int i = 0; i < nombreServicio.length; i++) {
       		
       		sql += " AND s.nombre ='"+nombreServicio+"'";			
		}
       	
    	sql += " AND s.tomaservicio = 'Y' ;";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para MOSTRAR EL USO DE ALOHANDES PARA CADA TIPO DE USUARIO DE LA COMUNIDAD
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC5 (PersistenceManager pm)
	{
        String sql = "SELECT miem.tipomiembro , COUNT(*)";
        sql += " FROM" + pp.darTablaMiemCoUniv()+" miem,"+ pp.darTablaReservas()+" re";
       	sql	+= " WHERE miem.id = re.idmiembro";
       	sql += " GROUP BY miem.tipomiembro ;";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para MOSTRAR EL USO DE ALOHANDES PARA UN USUARIO DADO(NÚMERO DE NOCHES O MESES CONTRATADOS, CARACTERÍSTICAS DEL ALOJAMIENTO UTILIZADO, DINERO PAGADO.

	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC6 (PersistenceManager pm, long idUsuario)
	{
        String sql = "SELECT mi.id, mi.tipoid, SUM(r.tiempodias) AS numeroNoches,  SUM(a.precio) AS DineroPagado, op.tipooperador";
        sql += " FROM"+ pp.darTablaReservas() +"r ,"+  pp.darTablaMiemCoUniv() +"mi,"+ pp.darTablaAlojamientos() +"a,"+ pp.darTablaOperadores() +"op";
       	sql	+= " WHERE mi.id = r.idmiembro";
       	sql += " AND r.numreserva = a.id";
       	sql += " AND op.id = a.idoperador";
       	sql += " AND mi.id ="+idUsuario;
       	sql += " GROUP BY mi.id , mi.tipoid, op.tipooperador ;";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para  ANALIZAR LA OPERACI�N DE ALOHANDES
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC7MayorDemanda (PersistenceManager pm, int tiempo, String tipo)
	{
        String sql = "SELECT to_char(r.diareserva, 'Month' ) mes, EXTRACT(year FROM r.diareserva) anio, count(*)";
        sql += " FROM"+ pp.darTablaReservas() +"r ,"+ pp.darTablaAlojamientos() +"a,"+ pp.darTablaOperadores() +"o";
       	sql	+= " Where r.idalojamiento = a.id "
       		  + "AND a.idoperador = o.id "
       		  + "AND o.tipooperador = '"+tipo+"' "
       		  + "AND EXTRACT(year FROM r.diareserva) <"+ tiempo;
       	sql += " group by to_char(r.diareserva, 'Month' ), EXTRACT(year FROM r.diareserva)";
       	sql += " order by count(*) desc";
       	sql += " FETCH FIRST ROWS ONLY;";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para  ANALIZAR LA OPERACI�N DE ALOHANDES
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC7MayorIngresos (PersistenceManager pm, int tiempo, String tipo)
	{
        String sql = "SELECT to_char(r.diareserva, 'Month' ) mes, EXTRACT(year FROM r.diareserva) anio, SUM(a.precio)";
        sql += " FROM"+ pp.darTablaReservas() +"r ,"+ pp.darTablaAlojamientos() +"a,"+ pp.darTablaOperadores() +"o";
       	sql	+= " Where r.idalojamiento = a.id "
       		  + "AND a.idoperador = o.id "
       		  + "AND o.tipooperador = '"+tipo+"' "
       		  + "AND EXTRACT(year FROM r.diareserva) <"+ tiempo;
       	sql += " group by to_char(r.diareserva, 'Month' ), EXTRACT(year FROM r.diareserva)";
       	sql += " order by SUM(a.precio) desc";
       	sql += " FETCH FIRST ROWS ONLY;";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para  ANALIZAR LA OPERACI�N DE ALOHANDES
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC7MayorOcupacion (PersistenceManager pm, int tiempo, String tipo)
	{
        String sql = "SELECT to_char(r.diareserva, 'Month' ) mes, EXTRACT(year FROM r.diareserva) anio, count(*)";
        sql += " FROM"+ pp.darTablaReservas() +"r ,"+ pp.darTablaAlojamientos() +"a,"+ pp.darTablaOperadores() +"o";
       	sql	+= " Where r.idalojamiento = a.id "
       		  + "AND a.idoperador = o.id "
       		  + "AND o.tipooperador = '"+tipo+"' "
       		  + "AND EXTRACT(year FROM r.diareserva) <"+ tiempo;
       	sql += " group by to_char(r.diareserva, 'Month' ), EXTRACT(year FROM r.diareserva)";
       	sql += " order by count(*) desc";
       	sql += " FETCH FIRST ROWS ONLY;";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar los clientes frecuentes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un operador,
	 * 	el segundo elemento representa el número de dinero ganado
	 */
	public List<Object []> darRFC8 (PersistenceManager pm )
	{
        String sql = "SELECT *";
        sql += "FROM"; 
        sql += "	(SELECT COUNT(*) AS numeroDeReservas , r.tiempodias AS tiempo, m.id AS id, m.nombre AS nom, m.tipoid AS tipo, m.tipomiembro AS timi\n" + 
        		"            FROM"+ pp.darTablaAlojamientos() +"a,"+ pp.darTablaReservas() +"r,"+ pp.darTablaMiemCoUniv() +"m \n" + 
        		"            WHERE a.id = r.idalojamiento\n" + 
        		"            AND r.idmiembro = m.id\n" + 
        		"            GROUP BY m.id, r.tiempodias , m.nombre, m.tipoid, m.tipomiembro) ";
        
        sql+= "WHERE numeroDeReservas >3 OR tiempo > 15 \n" + 
        	  "GROUP BY id,tiempo, nom, tipo, timi);" ;
        
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para ENCONTRAR LAS OFERTAS DE ALOJAMIENTO QUE NO TIENEN MUCHA DEMANDA

	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC9 (PersistenceManager pm)
	{
        String sql = "SELECT id ";
        sql += " FROM";
        sql	+= " 	   (SELECT a.id, sum(r.tiempodias) cantidad  \n" + 
        		"	        FROM"+ pp.darTablaReservas()+ "r,"+ pp.darTablaAlojamientos() +"a \n" + 
        		"	        WHERE r.idalojamiento = a.id  \n" + 
        		"	        group by a.id) ";
       	sql += "WHERE cantidad < 30;  ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para ENCONTRAR LAS OFERTAS DE ALOJAMIENTO QUE NO TIENEN MUCHA DEMANDA

	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC10 (PersistenceManager pm, long idAlojamiento, String TipoAgrupamiento, Date FechaMenor, Date FechaMayor)
	{
		String ResultadoSelect="";
		String GroupBY="";
		String OrderBy="";
		
		if(TipoAgrupamiento.equals("Cliente"))
		{
			ResultadoSelect= "mi.id , mi.nombre, mi.tipoid, mi.tipomiembro";
			GroupBY="mi.id , mi.nombre, mi.tipoid, mi.tipomiembro";
			OrderBy="mi.id ";		
		}
		else if(TipoAgrupamiento.equals("Alojamiento"))
		{
			ResultadoSelect= "r.idalojamiento";
			GroupBY="r.idalojamiento";
			OrderBy="r.idalojamiento";
			
		}
		else if(TipoAgrupamiento.equals("TipoAlojamiento"))
		{
			ResultadoSelect= "op.id, op.tipooperador";
			GroupBY="op.id, op.tipooperador  ";
			OrderBy="op.id";		
		}
		else 
		{
			return null;
		}

		
        String sql = "SELECT" + ResultadoSelect +",count(r.numreserva) ";
        sql += " FROM"+ pp.darTablaMiemCoUniv() +"mi ,"+ pp.darTablaReservas() +"r,"+ pp.darTablaAlojamientos() +"a";
        sql	+= " WHERE mi.id= r.idmiembro	";
       	sql += " AND r.idalojamiento = a.id ";
       	sql += " AND a.id ="+idAlojamiento;
       	sql += " AND r.diareserva BETWEEN '"+FechaMenor+"' AND  '"+FechaMayor+"' ";
       	sql += " GROUP BY"+GroupBY;
       	sql += " ORDER BY"+OrderBy+";";

       	
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para ENCONTRAR LAS OFERTAS DE ALOJAMIENTO QUE NO TIENEN MUCHA DEMANDA

	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un alojamiento,
	 * 	el segundo elemento representa el número de reservas que ha tenido
	 */
	public List<Object []> darRFC11 (PersistenceManager pm, long idAlojamiento, String TipoAgrupamiento, Date FechaMenor, Date FechaMayor)
	{
		String ResultadoSelect="";
		String GroupBY="";
		String OrderBy="";
		
		if(TipoAgrupamiento.equals("Cliente"))
		{
			ResultadoSelect= "mi.id , mi.nombre, mi.tipoid, mi.tipomiembro";
			GroupBY="mi.id , mi.nombre, mi.tipoid, mi.tipomiembro";
			OrderBy="mi.id ";		
		}
		else if(TipoAgrupamiento.equals("Alojamiento"))
		{
			ResultadoSelect= "r.idalojamiento";
			GroupBY="r.idalojamiento";
			OrderBy="r.idalojamiento";
			
		}
		else if(TipoAgrupamiento.equals("TipoAlojamiento"))
		{
			ResultadoSelect= "op.id, op.tipooperador";
			GroupBY="op.id, op.tipooperador  ";
			OrderBy="op.id";		
		}
		else 
		{
			return null;
		}

		
        String sql = "SELECT *";
        sql+= "FROM"+ pp.darTablaMiemCoUniv() +"miem LEFT JOIN";
        sql += "	(SELECT" + ResultadoSelect +",count(r.numreserva) ";
        sql += " 		FROM" + pp.darTablaMiemCoUniv() +"mi ,"+ pp.darTablaReservas() +"r,"+ pp.darTablaAlojamientos() +"a";
        sql	+= " 		WHERE mi.id= r.idmiembro	";
       	sql += " 		AND r.idalojamiento = a.id ";
       	sql += " 		AND a.id ="+idAlojamiento;
       	sql += " 		AND r.diareserva BETWEEN '"+FechaMenor+"' AND  '"+FechaMayor+"' ";
       	sql += " 		GROUP BY"+GroupBY;
       	sql += " 		ORDER BY"+OrderBy+")";
       	sql += " ON miem.id = ida";
       	sql += " WHERE ida IS NULL;";

       	
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	
	
	
	
	
	
	
	
	
	
}
