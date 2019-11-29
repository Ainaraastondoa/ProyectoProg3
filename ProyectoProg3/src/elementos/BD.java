package elementos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BD {
	
	
	static String url = "src/elementos/BaseDatosF1.db";
	
	/**Inicializa una BDSQLITE y devuelve una conexión con ella
	 * @param nombreBD
	 * @return Devuelve null si hay algun error con la conexión
	 */

	public static Connection initBD( String nombreBD) {
		try {
			//FOREIGN KEY
			//SQLiteConfig config = new SQLiteConfig();
			//config.enforcedForeignKeys(true);
		    Class.forName("org.sqlite.JDBC");
		    Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );	    
			log( Level.INFO, "Conectada base de datos " + nombreBD, null );
		    return con;
		} catch (ClassNotFoundException | SQLException e) {
			log( Level.SEVERE, "Error en conexiÃ³n de base de datos " + nombreBD, e );
			return null;
		}
	}
	
	/**Creamos las tablas de escuderia, pilotos, coches, componentes, atributos con sus respectivos atributos. 
	 * Además de las tablas relacionales necesarias para realizar las referencias necesarias
	 * Si ya existen, las deja tal cual. Devuelve un statement para trabajar con esa base de datos
	 * @param con Conexion ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	
	public static Statement usarCrearTablasBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			try {
				statement.executeUpdate("create table escuderia " +
					"(escuderia_id string primary key" +	// Codigo del piloto
					", nombre string" +           			// Nombre de la escuderia
					", director string" +           		// Nombre del director
					", piloto1_id string not null"  +       // Nombre del piloto1
					", piloto2_id string not null" +    	// Nombre del piloto2
					", presupuesto integer" +				// Presupuesto del equipo
					")");
				
				statement.executeUpdate("create table piloto " +
					"(piloto_id string primary key" +
					", nombre string" +
					", edad integer" +
					", nivel integer" +
					", regularidad integer" +
					", adelantar integer" +
					", defender integer" +
					", velocidad integer" +
					", mojado integer" +
					", coche_id string not null" +
					")");
				
				statement.executeUpdate("create table coche " +
					"(coche_id string primary key" +
					", nombre string" +
					", componente_id string" + 
					", porcentajeRuedas double" +
					")");
				
				statement.executeUpdate("create table componente " +
					", componente_id string primary key" +	//codigo de componente
					", nombre string" +						//nombre de componente
					", mejorasDisponibles_id string" +		//Lista de mejoras disponibles para el componente
					"(");
				
				statement.executeUpdate("create table mejora " +
					", mejora_id string primary key" +	//codigo de mejora
					", nombre string" +					//nombre de mejora
					", componente_id string" +			//Componente sobre el que se aplica la mejora
					", aumentorendimiento integer" +	//Incremento de rendimiento que implica el componente
					", coste integer" +					//Coste de la mejora
					", mejorasNecesarias_id string" + 	//Lista de nombres de mejoras previas que necesita para poder canjearse
					")");

								
			} catch (SQLException e) {} // Tabla ya existe. Nada que hacer
			try {
				statement.executeUpdate("create table escuderia " +
					"(escuderia_id string primary key" +    // Codigo de escuderia
					", nombre String " +   					// Nombre de escuderia
					")");
				
				statement.executeUpdate("create table piloto " +
					"(piloto_id string primary key" +	//codigo de piloto
					", nombre string" +					//nombre de piloto
					")");
				
				statement.executeUpdate("create table coche " +
				"(coche_id string primary key" +	//codigo de coche
				", nombre string" +					//nombre de coche
				")");
				
				statement.executeUpdate("create table componente " +
				"(componente_id string primary key" +	//codigo componente
				", nombre string" +						//nombre componente
				")");
				
				statement.executeUpdate("create table mejora " +
				"(mejora_id string primary key" + 	//codigo de mejora
				", nombre string" + 				//nombre de mejora
				")");
				
			} catch (SQLException e) {} // Si la tabla ya existe no hay nada que hacer
			try {
				statement.executeUpdate("create table relacionEscuderiaPilotos " +
					"(escuderia_id String primary key" +  	// Codigo de escuderia
					", piloto1_id String primary key " +  	// codigo del piloto1
					", piloto2_id String primary key " +  	// codigo del piloto2
					")");
				
				statement.executeUpdate("create table relacionPilotoCoche" +
					"(piloto_id string primary key" +	//codigo de piloto
					", coche_id string primary key" +	//codigo del coche
					")");
				
				statement.executeUpdate("create table relacionCocheComponente" +
					", coche_id string primary key" + 		//codigo de coche
					", componente_id string primary key" +	//codigo de componente
					")");
				
				statement.executeUpdate("create table relacionComponenteMejoras" +
					", componente_id string primary key" +		//codigo de componente
					", mejorasDisponibles_id string primary key" +			//codigo de mejora
					")");
				
				statement.executeUpdate("create table relacionMejorasMejorasNecesarias" +
					", mejora_id string primary key" +				//codigo de mejora
					", mejoraNecesaria_id string primary key" +		//codigo de mejora previa necesaria
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