package elementos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BD {
	 
	
	static String url = "src/elementos/F1BaseDatos.db";
	
	/**Inicializa una BDSQLITE y devuelve una conexi�n con ella
	 * @param nombreBD
	 * @return Devuelve null si hay algun error con la conexi�n
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
			log( Level.SEVERE, "Error en conexión de base de datos " + nombreBD, e );
			return null;
		}
	}
	
	/**Creamos las tablas de escuderia, pilotos, coches, componentes, atributos con sus respectivos atributos. 
	 * Adem�s de las tablas relacionales necesarias para realizar las referencias necesarias
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
					"(escuderia_id string primary key not null" +	// Codigo de la escuderia
					", nombre string" +           					// Nombre de la escuderia
					", director string" +           				// Nombre del director
					", piloto1_id string not null"  +       		// Nombre del piloto1
					", piloto2_id string not null" +    			// Nombre del piloto2
					", presupuesto integer" +						// Presupuesto del equipo
					")");
				
				statement.executeUpdate("create table piloto " +
					"(piloto_id string primary key not null" +
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
					"(coche_id string primary key not null" +
					", nombre string" +
					", componente_id string" + 
					", porcentajeRuedas real" +
					")");
				
				statement.executeUpdate("create table componente " +
					"(componente_id string primary key not null" +	//codigo de componente
					", nombre string" +						//nombre de componente
					", mejorasDisponibles_id string" +		//Lista de mejoras disponibles para el componente
					")");
				
				statement.executeUpdate("create table mejora " +
					"(mejora_id string primary key not null" +	//codigo de mejora
					", nombre string" +					//nombre de mejora
					", componente_id string" +			//Componente sobre el que se aplica la mejora
					", aumentorendimiento integer" +	//Incremento de rendimiento que implica el componente
					", coste integer" +					//Coste de la mejora
					", mejorasNecesarias_id string" + 	//Lista de nombres de mejoras previas que necesita para poder canjearse
					")");
				
//				//TABLA clasificacioncarrera
//				statement.executeUpdate("create tabla clasificacioncarrera " +
//						"(puesto integer " +			//puesto en la clasificacion
//						", piloto_id string " +			//codigo del piloto
//						", nombre string" +				//nombre del piloto
//						", puntos integer " +		//puntos conseguidos
//						")");
				
				//TABLA clasificacionpilotos
				statement.executeUpdate("create tabla clasificacionpilotos " +
					"(puesto integer primary key not null" +			//puesto en la clasificacion
					", piloto_id string " +			//codigo del piloto
					", nombre string" +				//nombre del piloto
					", puntos integer " +		//puntos conseguidos
					")");
				
				//TABLA clasificacionescuderias
				statement.executeUpdate("create tabla clasificacionescuderias " +
					"(puesto integer primary key not null" +			//puesto en la clasificacion
					", escuderia_id string " +		//codigo de la escuderia
					", nombre string" +				//nombre de la escuderia
					", puntos integer " +			//puntos conseguidos
					")");
				
				//TABLA temporada
				statement.executeUpdate("create tabla temporada " +
					"(a�o string primary key not null" +			//a�o de temporada
					", circuitos string " +		//codigo del piloto
					", escuderias string" +		//nombre del piloto
					", pilotos integer " +			//puntos conseguidos
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
			log( Level.SEVERE, "Error en creación de base de datos", e );
			return null;
		}		
	}
	
	public static boolean insertEscuderia( Statement st) {
		String sentSQL = "";
		try {
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					1 + ", " +
					"'" + "Mercedes" + "', '" +
					"Toto Wolf" + "', '" +
					initDatos.initPilotos().get(0).toString() + "', '" +
					initDatos.initPilotos().get(1).toString() + "', " +
					25000000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					2 + ", " +
					"'" + "Ferrari" + "', '" +
					"Matia Binotto" + "', '" +
					initDatos.initPilotos().get(2).toString() + "', '" +
					initDatos.initPilotos().get(3).toString() + "', " +
					25000000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					3 + ", " +
					"'" + "Red Bull" + "', '" +
					"Christian Horner" + "', '" +
					initDatos.initPilotos().get(4) + "', '" +
					initDatos.initPilotos().get(5) + "'," +
					25000000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					4 + ", " +
					"'" + "Mclaren" + "', '" +
					"Andrea Seidl" + "', '" +
					initDatos.initPilotos().get(6) + "', '" +
					initDatos.initPilotos().get(7) + "'," +
					20000000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					5 + ", " +
					"'" + "Renault" + "', '" +
					"Cyril Abditeboul" + "', '" +
					initDatos.initPilotos().get(8) + "', '" +
					initDatos.initPilotos().get(9) + "'," +
					17500000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					6 + ", " +
					"'" + "Toro Rosso" + "', '" +
					"Franz Tost" + "', '" +
					initDatos.initPilotos().get(10) + "', '" +
					initDatos.initPilotos().get(11) + "'," +
					15000000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					7 + ", " +
					"'" + "Racing Point" + "', '" +
					"Andrew Green" + "', '" +
					initDatos.initPilotos().get(12) + "', '" +
					initDatos.initPilotos().get(13) + "'," +
					15000000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					8 + ", " +
					"'" + "Haas" + "', '" +
					"Guenther Steiner" + "', '" +
					initDatos.initPilotos().get(14) + "', '" +
					initDatos.initPilotos().get(15) + "'," +
					10000000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					9 + ", " +
					"'" + "Alfa Romeo" + "', '" +
					"Frank Vasseur" + "', '" +
					initDatos.initPilotos().get(16) + "', '" +
					initDatos.initPilotos().get(17) + "'," +
					10000000 +
					")";
			st.executeUpdate(sentSQL);
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					10 + ", " +
					"'" + "Williams" + "', '" +
					"Claire Williams" + "', '" +
					initDatos.initPilotos().get(18) + "', '" +
					initDatos.initPilotos().get(19) + "'," +
					8000000 +
					")";
			st.executeUpdate(sentSQL);

			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que añadir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			return false;
		}
	}
	
	public static boolean insertclasificacionpilotos( Statement st, Integer puesto, Integer piloto_id, String nombre, Integer puntos) {
		String sentSQL = "";
		try {
			sentSQL = "insert into clasificacionpilotos(puesto, piloto_id, nombre, puntos) values (" +
					puesto + ", " +
					"" + piloto_id + ", " +
					"'" + nombre + "', " +
					puntos +
					")";
			st.executeUpdate(sentSQL);
					
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que añadir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			return false;
		}
	}
	
	public static boolean insertclasificacionescuderias( Statement st, Integer puesto, Integer escuderia_id, String nombre, Integer puntos) {
		String sentSQL = "";
		try {
			sentSQL = "insert into clasificacionpilotos(puesto, piloto_id, nombre, puntos) values (" +
					puesto + ", " +
					"" + escuderia_id + ", " +
					"'" + initDatos.initPilotos().get(escuderia_id - 1) + "', " +
					puntos +
					")";
			st.executeUpdate(sentSQL);
					
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que añadir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			return false;
		}
	}
	
	private static Logger logger = null;
	
	// Método local para loggear
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
	
	public static void main(String[] args) throws SQLException {
		
		initBD(url);
		Connection con = initBD(url);
		usarCrearTablasBD(con);
		Statement st = con.createStatement();
		insertEscuderia(st);
	}
}