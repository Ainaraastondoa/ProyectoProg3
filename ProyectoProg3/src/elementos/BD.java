package elementos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BD {
	
	/**Inicializa una BDSQLITE y devuelve una conexión con ella
	 * @param nombreBD
	 * @return Devuelve null si hay algun error con la conexión
	 */

	public static Connection initBD( String nombreBD) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			log( Level.INFO, "Conectada base de datos " + nombreBD, null );
		    return con;
		} catch (ClassNotFoundException | SQLException e) {
			log( Level.SEVERE, "Error en conexiÃ³n de base de datos " + nombreBD, e );
			return null;
		}
	}
	
	/**Creamos las tablas de coche y pilotos con sus respectivos atributos. Creamos además 
	 * una tabla relacionCochePiloto
	 * Si ya existen, las deja tal cual. Devuelve un statement para trabajar con esa base de datos
	 * @param con Conexion ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	
	public static Statement usarCrearTablasBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			try {
				statement.executeUpdate("create table piloto " +
					"(piloto_id string primary key" +                     // Codigo del piloto
					", nombre String" +           //Nombre del piloto
					", edad integer" +           // Edad del piloto
					", nivel integer"  +         //Nivel del piloto (0-10)
					", regularidad integer" +    //Regularidad/consistencia (0-100)
					", adelantar integer" +		//Capacidad de adelantamiento (0-100)
					", defender integer" +      //Defensa (0-100)
					", velocidad integer" +     //Rendimiento/velocidad del piloto a 1 vuelta (0-100)
					", mojado integer" +   //Nivel del piloto en mojado (0-100)
					", coche_id string not null" +     //Coche que utiliza el piloto
					")");
				
			} catch (SQLException e) {} // Tabla ya existe. Nada que hacer
			try {
				statement.executeUpdate("create table coche " +
					"(coche_id string primary key" +         // Codigo del coche
					", nombre String " +   // Nombre del coche
					//", porcentajeRuedas integer" + //Porcentaje restante de duración de las ruedas en carrera
					")");
			} catch (SQLException e) {} // Si la tabla ya existe no hay nada que hacer
			try {
				statement.executeUpdate("create table relacionCochePiloto " +
						"(piloto_id String primary key" +         // Codigo del coche
						", coche_id String primary key " +   // Nombre del coche
						//", porcentajeRuedas integer" + //Porcentaje restante de duración de las ruedas en carrera
						")");
			} catch (SQLException e) {} // Si la tabla ya existe no hay nada que hacer
			log( Level.INFO, "Creada base de datos", null );
			return statement;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en creaciÃ³n de base de datos", e );
			return null;
		}
	}
	

	private static Logger logger = null;
	
	// MÃ©todo local para loggear
	private static void log( Level level, String msg, Throwable excepcion ) {
		if (logger==null) {  // Logger por defecto local:
			logger = Logger.getLogger( BD.class.getName() );  // Nombre del logger - el de la clase
		logger.setLevel( Level.ALL );  // Loguea todos los niveles
		}
		if (excepcion==null)
			logger.log( level, msg );
		else
			logger.log( level, msg, excepcion );
	}
	
	

	}

	



