package AlohAndes.persistencia;

import AlohAndes.negocio.Alojamiento;
import AlohAndes.negocio.MiembroComunidadUniversitaria;
import AlohAndes.negocio.Operador;
import AlohAndes.negocio.Reserva;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.jdo.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PersistenciaAlohAndes
{
    /* ****************************************************************
     * 			Constantes
     *****************************************************************/
    /**
     * Logger para escribir la traza de la ejecución
     */
    private static Logger log = Logger.getLogger(PersistenciaAlohAndes.class.getName());

    /**
     * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
     */
    public final static String SQL = "javax.jdo.query.SQL";

    /* ****************************************************************
     * 			Atributos
     *****************************************************************/
    /**
     * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
     */
    private static PersistenciaAlohAndes instance;

    /**
     * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
     */
    private PersistenceManagerFactory pmf;

    /**
     * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
     * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
     */
    private List<String> tablas;

    /**
     * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
     */
    private SQLUtil sqlUtil;

    /**
     * Atributo para el acceso a la tabla OPERADORES de la base de datos
     */
    private SQLOperador sqlOperador;

    /**
     * Atributo para el acceso a la tabla ALOJAMIENTOS de la base de datos
     */
    private SQLAlojamiento sqlAlojamiento;

    /**
     * Atributo para el acceso a la tabla APARTAMENTOS de la base de datos
     */
    private SQLApartamento sqlApartamento;

    /**
     * Atributo para el acceso a la tabla HABITACIONES de la base de datos
     */
    private SQLHabitacion sqlHabitacion;

    /**
     * Atributo para el acceso a la tabla VIVIENDACMUNIDAD de la base de datos
     */
    private SQLViviendaComunidad sqlViviendaComunidad;

    /**
     * Atributo para el acceso a la tabla MIEM_CO_UNIV de la base de datos
     */
    private SQLMiemCoUniv sqlMiemCoUniv;

    /**
     * Atributo para el acceso a la tabla RESERVAS de la base de datos
     */
    private SQLReserva sqlReserva;

    /**
     * Atributo para el acceso a la tabla SERVICIOS de la base de datos
     */
    private SQLServicio sqlServicio;

    /* ****************************************************************
     * 			Métodos del MANEJADOR DE PERSISTENCIA
     *****************************************************************/

    /**
     * Constructor privado con valores por defecto - Patrón SINGLETON
     */
    private PersistenciaAlohAndes ()
    {
        pmf = JDOHelper.getPersistenceManagerFactory("AlohAndes");
        crearClasesSQL ();

        // Define los nombres por defecto de las tablas de la base de datos
        tablas = new LinkedList<String>();
        //Secuencia de id de los alojamientos
        tablas.add ("ID_ALOJAMIENTO");
        //Secuencia de id de las reservas
        tablas.add ("ID_RESERVA");
        //Tablas de la base de datos
        tablas.add ("OPERADORES");
        tablas.add ("ALOJAMIENTOS");
        tablas.add ("APARTAMENTOS");
        tablas.add ("HABITACIONES");
        tablas.add ("VIVIENDACMUNIDAD");
        tablas.add ("MIEM_CO_UNIV");
        tablas.add ("RESERVAS");
        tablas.add("SERVICIOS");
    }

    /**
     * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
     */
    public static PersistenciaAlohAndes getInstance ()
    {
        if (instance == null)
        {
            instance = new PersistenciaAlohAndes ();
        }
        return instance;
    }

    /**
     * Cierra la conexión con la base de datos
     */
    public void cerrarUnidadPersistencia ()
    {
        pmf.close ();
        instance = null;
    }

    /**
     * Genera una lista con los nombres de las tablas de la base de datos
     * @param tableConfig - El objeto Json con los nombres de las tablas
     * @return La lista con los nombres del secuenciador y de las tablas
     */
    private List <String> leerNombresTablas (JsonObject tableConfig)
    {
        JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

        List <String> resp = new LinkedList <String> ();
        for (JsonElement nom : nombres)
        {
            resp.add (nom.getAsString ());
        }

        return resp;
    }

    /**
     * Crea los atributos de clases de apoyo SQL
     */
    private void crearClasesSQL ()
    {
        sqlOperador = new SQLOperador(this);
        sqlAlojamiento = new SQLAlojamiento(this);
        sqlApartamento = new SQLApartamento(this);
        sqlHabitacion = new SQLHabitacion(this);
        sqlViviendaComunidad = new SQLViviendaComunidad(this);
        sqlMiemCoUniv = new SQLMiemCoUniv (this);
        sqlReserva = new SQLReserva(this);
        sqlServicio = new SQLServicio(this);
        sqlUtil = new SQLUtil(this);
    }

    /**
     * @return La cadena de caracteres con el nombre del secuenciador de idAlojamiento
     */
    public String darSeqIdAlojamiento ()
    {
        return tablas.get (0);
    }

    /**
     * @return La cadena de caracteres con el nombre del secuenciador de idReserva
     */
    public String darSeqIdReserva ()
    {
        return tablas.get (1);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de OPERADORES de AlohAndes
     */
    public String darTablaOperadores ()
    {
        return tablas.get (2);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de ALOJAMIENTOS de AlohAndes
     */
    public String darTablaAlojamientos ()
    {
        return tablas.get (3);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de APARTAMENTOS de AlohAndes
     */
    public String darTablaApartamentos ()
    {
        return tablas.get (4);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de HABITACIONES de AlohAndes
     */
    public String darTablaHabitaciones ()
    {
        return tablas.get (5);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de VIVIENDACOMUNIDAD de AlohAndes
     */
    public String darTablaViviendaComunidad ()
    {
        return tablas.get (6);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de MIEM_CO_UNIV de AlohAndes
     */
    public String darTablaMiemCoUniv ()
    {
        return tablas.get (7);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de RESERVAS de AlohAndes
     */
    public String darTablaReservas ()
    {
        return tablas.get (8);
    }

    /**
     * @return La cadena de caracteres con el nombre de la tabla de SERVICIOS de AlohAndes
     */
    public String darTablaServicios ()
    {
        return tablas.get (9);
    }

    /**
     * Transacción para el generador de secuencia de idAlojamiento
     * Adiciona entradas al log de la aplicación
     * @return El siguiente número del secuenciador de idAlojamiento
     */
    private long nextval_idAlojamiento ()
    {
        long resp = sqlUtil.nextval_idAlojamiento (pmf.getPersistenceManager());
        log.trace ("Generando secuencia idAlojamiento: " + resp);
        return resp;
    }

    /**
     * Transacción para el generador de secuencia de Parranderos
     * Adiciona entradas al log de la aplicación
     * @return El siguiente número del secuenciador de Parranderos
     */
    private long nextval_idReserva()
    {
        long resp = sqlUtil.nextval_idReserva (pmf.getPersistenceManager());
        log.trace ("Generando secuenci idReserva: " + resp);
        return resp;
    }
    
    /**
     * Transacción para el generador de secuencia de Parranderos
     * Adiciona entradas al log de la aplicación
     * @return El siguiente número del secuenciador de Parranderos
     */
    private long nextval_idOperador()
    {
        long resp = sqlUtil.nextval_idReserva (pmf.getPersistenceManager());
        log.trace ("Generando secuenci idReserva: " + resp);
        return resp;
    }

    /**
     * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
     * @param e - La excepción que ocurrio
     * @return El mensaje de la excepción JDO
     */
    private String darDetalleException(Exception e)
    {
        String resp = "";
        if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
        {
            JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
            return je.getNestedExceptions() [0].getMessage();
        }
        return resp;
    }

    /* ****************************************************************
     * 			Métodos para manejar los OPERADORES
     *****************************************************************/

    /**
     * Método que inserta, de manera transaccional, una tupla en la tabla Operadores
     * Adiciona entradas al log de la aplicación
     * @param idOperador - El identificador del alojamiento que se desea Operadorr 
     * @param nombre - El identificador del miembro que desea realizar la Operador 
     * @param registroDeCamaraYComercio -  si esta subscrito o tiene el certitificado.
     * @param SuperIntendensiaDeTurismo - si esta subscrito o tiene el certitificado.
     * @param calidad - La calidad
     * @param tipoOperador - El tipo operador 
     * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
     */
    public Operador adicionarOperador( String nombre, Boolean  registroDeCamaraYComercio, Boolean SuperIntendensiaDeTurismo, int  calidad, String  tipoOperador)
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
        
            long idOperador = nextval_idReserva();
            
            String registroDeCamaraYComercioCHAR ="";
            if(registroDeCamaraYComercio== true ){
             registroDeCamaraYComercioCHAR = "Y";
            }
            else{
            registroDeCamaraYComercioCHAR = "N";	
            }
            
            String SuperIntendensiaDeTurismoChar ="";
            if(SuperIntendensiaDeTurismo == true ){
            	SuperIntendensiaDeTurismoChar = "Y";
            }
            else{
            	SuperIntendensiaDeTurismoChar = "N";	
            }
            
            long tuplasInsertadas = sqlOperador.adicionarOperador(pm, idOperador , nombre, registroDeCamaraYComercioCHAR, SuperIntendensiaDeTurismoChar, calidad, tipoOperador);
            tx.commit();

            log.trace ("Inserción Operador: " + idOperador + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Operador(idOperador, nombre, registroDeCamaraYComercio, SuperIntendensiaDeTurismo, calidad, tipoOperador);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
            log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    /**
     * Método que elimina, de manera transaccional, una tupla en la tabla Operadores, dado el id del operador
     * Adiciona entradas al log de la aplicación
     * @param idReserva - El id de la reserva
     * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
     */
    public long eliminarOperadorPorId (long idoperador)
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOperador.eliminarOperadorPorId(pm, idoperador);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
            log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    
    
    /**
     * Método que consulta todas las tuplas en la tabla OPERADORES
     * @return La lista de objetos Operador, construidos con base en las tuplas de la tabla OPERADORES
     */
    public List<Operador> darOperadores ()
    {
        return sqlOperador.darOperadores (pmf.getPersistenceManager());
    }

//    /**
//     * Método que consulta todas las tuplas en la tabla OPERADORES que tienen el nombre dado
//     * @param nombre - El nombre del de operador
//     * @return La lista de objetos Operador, construidos con base en las tuplas de la tabla OPERADORES
//     */
//    public Operador darOperadorPorNombre (String nombre)
//    {
//        return sqlOperador.darOperadorPorNombre (pmf.getPersistenceManager(), nombre);
//    }

    
    /**
     * Método que consulta todas las tuplas en la tabla OPERADORES con un identificador dado
     * @param idOperador - El identificador del operador
     * @return El objeto Operador, construido con base en las tuplas de la tabla OPERADORES con el identificador dado
     */
    public Operador darOperadorPorId (long idOperador)
    {
        return sqlOperador.darOperadorPorId (pmf.getPersistenceManager(), idOperador);
    }

    /* ****************************************************************
     * 			Métodos para manejar las RESERVAS
     *****************************************************************/

    /**
     * Método que inserta, de manera transaccional, una tupla en la tabla RESERVAS
     * Adiciona entradas al log de la aplicación
     * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
     * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tiempoDias - El número de días que se desea reservar el alojamiento
     * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
     */
    public Reserva adicionarReserva(long idAlojamiento, long idMiembro, String tipoId, int tiempoDias)
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idReserva = nextval_idReserva();
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
            String diaReserva = dateFormat.format(date);
            long tuplasInsertadas = sqlReserva.adicionarReserva(pm, idReserva, idAlojamiento, idMiembro, tipoId, diaReserva, tiempoDias);
            tx.commit();

            log.trace ("Inserción reserva: " + idReserva + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Reserva (idReserva,idAlojamiento, idMiembro, tipoId, date, tiempoDias);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
            log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

    /**
     * Método que elimina, de manera transaccional, una tupla en la tabla RESERVAS, dado el id de la reserva
     * Adiciona entradas al log de la aplicación
     * @param idReserva - El id de la reserva
     * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
     */
    public long eliminarReservaPorId (long idReserva)
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReservaPorId(pm, idReserva);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
            log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }
    
    
	/**
	 * Método que consulta todas las tuplas en la Reserva
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Reserva> darReservas ()
	{
		return sqlReserva.darReservas (pmf.getPersistenceManager());
	}
	

    /**
     * Método que consulta la tupla en la tabla RESERVAS que tiene el identificador dado
     * @param idReserva - El identificador de la reserva
     * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
     */
    public Reserva darReservaPorId (long idReserva)
    {
        return (Reserva) sqlReserva.darReservaPorId (pmf.getPersistenceManager(), idReserva);
    }

    /**
     * Método que consulta la tupla en la tabla RESERVAS que tiene el alojamiento dado
     * @param idAlojamiento - El identificador del alojamiento
     * @return La lista de objetos Bebida, construidos con base en las tuplas de la tabla BEBIDA
     */
    public Reserva darReservaPorIdAlojamiento (long idAlojamiento)
    {
        return (Reserva) sqlReserva.darReservaPorIdAlojamiento (pmf.getPersistenceManager(), idAlojamiento);
    }

    /* ****************************************************************
     * 			Métodos para manejar los ALOJAMIENTOS
     *****************************************************************/

    
    /**
     * Método que inserta, de manera transaccional, una tupla en la tabla Alojamientos
     * Adiciona entradas al log de la aplicación
     * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
     * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tiempoDias - El número de días que se desea reservar el alojamiento
     * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
     */
    public Alojamiento adicionarAlojamiento(Boolean habilitada, Date  fechaInicio, Date fechaFin)
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idAlojamiento = nextval_idAlojamiento();
            Date date = Calendar.getInstance().getTime();
            

            String habilitadoString ;
            if(habilitada == true)
            {
            	habilitadoString= "Y";
            }
            else
            {
            	habilitadoString= "N";
            }
            
            long tuplasInsertadas = sqlAlojamiento.adicionarAlojamiento(pm, idAlojamiento, habilitadoString, fechaInicio, fechaFin);
            tx.commit();

            log.trace ("Inserción Alojamiento: " + idAlojamiento + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Alojamiento (idAlojamiento, habilitada, fechaInicio, fechaFin);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
            log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }
    
	/**
	 * Método que consulta todas las tuplas en la Alojamientos
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla Alojamientos
	 */
	public List<Alojamiento> darAlojamientos ()
	{
		return sqlAlojamiento.darAlojamientos (pmf.getPersistenceManager());
	}
    
    
    /**
     * Método que elimina, de manera transaccional, una tupla en la tabla ALOJAMENTO, dado el id del alojamiento. El alojamento no debe estar Alojamientodo
     * Adiciona entradas al log de la aplicación
     * @param idAlojamiento - El id del alojamiento
     * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción o el alojamiento está Alojamientodo
     */
    public long eliminarAlojamiento (long idAlojamiento)
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        Reserva reserva = darReservaPorIdAlojamiento(idAlojamiento);
        if(reserva != null)
        {
            try {
                tx.begin();
                long resp = sqlAlojamiento.eliminarAlojamiento(pm, idAlojamiento);
                tx.commit();

                return resp;
            } catch (Exception e) {
//        	e.printStackTrace();
                log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
                return -1;
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
                pm.close();
            }
        }
        else
        {
            return -1;
        }
    }
    
    
    /* ****************************************************************
     * 			Métodos para manejar los Miembros de la comunidad
     *****************************************************************/
    
    /**
     * Método que inserta, de manera transaccional, una tupla en la tabla MiembroComunidadUniversitaria
     * Adiciona entradas al log de la aplicación
     * @param idAlojamiento - El identificador del alojamiento que se desea reservar (Debe existir en la tabla ALOJAMIENTOS)
     * @param idMiembro - El identificador del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tipoId - El tipo de identificación del miembro que desea realizar la reserva (Debe existir en la tabla MIEM_CO_UNIV)
     * @param tiempoDias - El número de días que se desea reservar el alojamiento
     * @return El objeto Reserva adicionado. null si ocurre alguna Excepción
     */
    public MiembroComunidadUniversitaria adicionarMiembroComunidadUniversitaria(long id, String  tipoID, String nombre, String  tipoMiembroComunidadUniversitaria)
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
          
            //VERIFICAR LAS REGLAS DE QUE SEA DE TAL TIPO
            
            long tuplasInsertadas = sqlMiemCoUniv.adicionarMiembroComunidadUniversitaria(pm, id,tipoID, nombre ,tipoMiembroComunidadUniversitaria);
            tx.commit();

            log.trace ("Inserción MiembroComunidadUniversitaria: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            return new MiembroComunidadUniversitaria (id, tipoID, nombre, tipoMiembroComunidadUniversitaria);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
            log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

    
    
	/**
	 * Método que consulta todas las tuplas en la MiembroComunidadUniversitaria
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<MiembroComunidadUniversitaria> darMiembrosComunidadUniversitaria ()
	{
		return sqlMiemCoUniv.darMiembrosComunidadUniversitaria (pmf.getPersistenceManager());
	}
    
	
	
	
    /* ****************************************************************
     * 			Método para limpiar la base de datos
     *****************************************************************/
    
	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AlohaAndes
	 * 
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 8 números que indican el número de tuplas borradas en las tablas ALOJAMIENTOS, APARTAMENTOS, HABITACIONES, 
	 * MIEMBRO COMUNIDAD UNIVERSITARIA, OPERADORES, RESERVA y SERVIVICIOS, VIVIENDA COMUNIDAD, respectivamente
	 */
	public long [] limpiarAlohAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarAlohAndes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1,-1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
    
}
