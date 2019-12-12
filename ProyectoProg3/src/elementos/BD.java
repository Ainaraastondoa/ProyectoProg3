package elementos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sqlite.SQLiteConfig;


public class BD {
	 
	
	static String url = "src/datos/F1BaseDatos.db";
	
	/**Inicializa una BDSQLITE y devuelve una conexión con ella
	 * @param nombreBD
	 * @return Devuelve null si hay algun error con la conexión
	 */

	public static Connection initBD( String nombreBD) {
		try {
			//FOREIGN KEY
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
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
	 * Además de las tablas relacionales necesarias para realizar las referencias necesarias
	 * Si ya existen, las deja tal cual. Devuelve un statement para trabajar con esa base de datos
	 * @param con Conexion ya creada y abierta a la base de datos
	 * @return sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	public static Statement usarCrearTablasBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30); 	// poner timeout 30 msg
			try {
				statement.executeUpdate("create table if not exists componente " +
					"(componente_id integer PRIMARY KEY not null" +	//codigo de componente
					", nombre string" +								//nombre de componente
					", rendimiento integer" +						//Rendimiento del componente
					")");
			} catch (SQLException e) {}	
			try {				
				statement.executeUpdate("create table if not exists coche " +
						"(coche_id integer PRIMARY KEY not null" +
						", nombre string" +
						", componente_id1 integer" + 
						", componente_id2 integer" + 
						", componente_id3 integer" + 
						", porcentajeRuedas real" +
						")");
			} catch (SQLException e) {}	
			try {	
				statement.executeUpdate("create table if not exists piloto " +
						"(piloto_id integer PRIMARY KEY not null" +
						", nombre string" +
						", edad integer" +
						", nivel integer" +
						", regularidad integer" +
						", adelantar integer" +
						", defender integer" +
						", velocidad integer" +
						", mojado integer" +
						", coche_id integer" +
						")");
			} catch (SQLException e) {}	
			try {
				statement.executeUpdate("create table if not exists escuderia " +
					"(escuderia_id integer PRIMARY KEY not null" +
					", nombre string" +
					", director string" +
					", piloto1_id integer" +
					", piloto2_id integer" +
					", presupuesto integer"+
					")");
			} catch (SQLException e) {}
			try {	
				statement.executeUpdate("create table if not exists circuito " +
						"(circuito_id integer PRIMARY KEY not null" +	
						", nombre string" +	
						", pais string" +
						", nivDegradacion integer" +					
						", tiempoRefClas float" +	
						", tiempoRefCar float" +					
						", tiempoRefBox float" +					
						", vueltas integer" +
						")");
			} catch (SQLException e) {}	
			try {
				statement.executeUpdate("create table if not exists carrera " +
					"(carrera_id integer PRIMARY KEY not null" +
					", circuito_id integer" +
					", piloto_id integer PRIMARY KEY" +
					")");
			} catch(SQLException e) {}
			try {	
				statement.executeUpdate("create table if not exists clasificacionpilotos " +
					"(puesto integer PRIMARY KEY not null" +										//puesto en la clasificacion
					", piloto_id integer" +															//codigo del piloto
					", puntos integer" +															//puntos conseguidos
					")");
			} catch (SQLException e) {}	
			try {	
				statement.executeUpdate("create table if not exists clasificacionescuderias " +
					"(puesto integer PRIMARY KEY not null" +									//puesto en la clasificacion
					", escuderia_id integer" +													//codigo de la escuderia
					", puntos integer" +														//puntos conseguidos
					")");
			} catch (SQLException e) {} // Si la tabla ya existe no hay nada que hacer
			log( Level.INFO, "Creada base de datos", null );
			return statement;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en creación de base de datos", e );
			return null;
		}		
	}
	
	
	//INSERTA DATOS POR DEFECTO - Componente-Coche-Piloto-Escuderia-Circuito	
	public static boolean insertDatos( Statement st) {
		String sentSQL = "";
		try {
			// DATOS TABLA COMPONENTE
			//DATOS MOTOR
			sentSQL = "insert into componente values(" +
					1 + ", " + "'" + "mercedes.m" + "', " + 88 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					2 + ", " + "'" + "ferrari.m" + "', " + 91 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					3 + ", " + "'" + "renault.m" + "', " + 81 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente values(" +
					4 + ", " + "'" + "honda.m" + "', " + 80 + ")";
			st.executeUpdate(sentSQL);
			
			// DATOS AERODINAMICA
			sentSQL = "insert into componente values(" +
					5 + ", " + "'" + "mercedes.a" + "', " + 88 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					6 + ", " + "'" + "ferrari.a" + "', " + 88 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente values(" +
					7 + ", " + "'" + "redbull.a" + "', " + 89 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					8 + ", " + "'" + "mclaren.a" + "', " + 82 + ")";
			st.executeUpdate(sentSQL);		
			sentSQL = "insert into componente values(" +
					9 + ", " + "'" + "renault.a" + "', " + 78 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente values(" +
					10 + ", " + "'" + "tororosso.a" + "', " + 76 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					11 + ", " + "'" + "racingpoint.a" + "', " + 75 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					12 + ", " + "'" + "haas.a" + "', " + 71 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente values(" +
					13 + ", " + "'" + "alfaromeo.a" + "', " + 70 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					14 + ", " + "'" + "williams.a" + "', " + 51 + ")";
			st.executeUpdate(sentSQL);	
			
			//DATOS CHASIS
			sentSQL = "insert into componente values(" +
					15 + ", " + "'" + "mercedes.c" + "', " + 90 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					16 + ", " + "'" + "ferrari.c" + "', " + 85 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente values(" +
					17 + ", " + "'" + "redbull.c" + "', " + 91 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					18 + ", " + "'" + "mclaren.c" + "', " + 79 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					19 + ", " + "'" + "renault.c" + "', " + 76 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente values(" +
					20 + ", " + "'" + "tororosso.c" + "', " + 77 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					21 + ", " + "'" + "racingpoint.c" + "', " + 73 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					22 + ", " + "'" + "haas.c" + "', " + 72 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente values(" +
					23 + ", " + "'" + "alfaromeo.c" + "', " + 68 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente values(" +
					24 + ", " + "'" + "williams.c" + "', " + 55 + ")";
			st.executeUpdate(sentSQL);

			//DATOS TABLA COCHE			
			sentSQL = "insert into coche values(" +
					1 + ", " + "'" + "mercedes" + "', " + 1 + ", " + 5 + ", " + 15 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					2 + ", " + "'" + "mercedes" + "', " + 1 + ", " + 5 + ", " + 15 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					3 + ", " + "'" + "ferrari" + "', " + 2 + ", " + 6 + ", " + 16 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					4 + ", " + "'" + "ferrari" + "', " + 2 + ", " + 6 + ", " + 16 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					5 + ", " + "'" + "red bull" + "', " + 4 + ", " + 7 + ", " + 17 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					6 + ", " + "'" + "red bull" + "', " + 4 + ", " + 7 + ", " + 17 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					7 + ", " + "'" + "mclaren" + "', " + 3 + ", " + 8 + ", " + 18 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					8 + ", " + "'" + "mclaren" + "', " + 3 + ", " + 8 + ", " + 18 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					9 + ", " + "'" + "renault" + "', " + 3 + ", " + 9 + ", " + 19 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					10 + ", " + "'" + "renault" + "', " + 3 + ", " + 9 + ", " + 19 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					11 + ", " + "'" + "toro rosso" + "', " + 4 + ", " + 10 + ", " + 20 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					12 + ", " + "'" + "toro rosso" + "', " + 4 + ", " + 10 + ", " + 20 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					13 + ", " + "'" + "racing point" + "', " + 1 + ", " + 11 + ", " + 21 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					14 + ", " + "'" + "racing point" + "', " + 1 + ", " + 11 + ", " + 21 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					15 + ", " + "'" + "haas" + "', " + 2 + ", " + 12 + ", " + 22 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					16 + ", " + "'" + "haas" + "', " + 2 + ", " + 12 + ", " + 22 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					17 + ", " + "'" + "alfa romeo" + "', " + 2 + ", " + 13 + ", " + 23 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					18 + ", " + "'" + "alfa romeo" + "', " + 2 + ", " + 13 + ", " + 23 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					19 + ", " + "'" + "williams" + "', " + 1 + ", " + 14 + ", " + 24 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche values(" +
					20 + ", " + "'" + "williams" + "', " + 1 + ", " + 14 + ", " + 24 + ", " + 1.0 + ")";
			st.executeUpdate(sentSQL);
		
			//DATOS TABLA PILOTO
	
			sentSQL = "insert into piloto values(" +
					1 + ", " + "'" + "Hamilton" + "', " + 34 + ", " + 10 + ", " + 75 + ", " + 81 + ", " + 75 + ", " + 85 + ", " + 88 + ", " + 1 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					2 + ", " + "'" + "Bottas" + "', " + 30 + ", " + 8 + ", " + 60 + ", " + 47 + ", " + 52 + ", " + 66 + ", " + 40 + ", " + 2 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					3 + ", " + "'" + "Vettel" + "', " + 32 + ", " + 9 + ", " + 62 + ", " + 69 + ", " + 66 + ", " + 77 + ", " + 60 + ", " + 3 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					4 + ", " + "'" + "Leclerc" + "', " + 22 + ", " + 9 + ", " + 55 + ", " + 79 + ", " + 77 + ", " + 80 + ", " + 68 + ", " + 4 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					5 + ", " + "'" + "Verstappen" + "', " + 22 + ", " + 9 + ", " + 50 + ", " + 75 + ", " + 70 + ", " + 77 + ", " + 90 + ", " + 5 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					6 + ", " + "'" + "Albon" + "', " + 23 + ", " + 7 + ", " + 60 + ", " + 65 + ", " + 55 + ", " + 66 + ", " + 45 + ", " + 6 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					7 + ", " + "'" + "Sainz" + "', " + 25 + ", " + 8 + ", " + 70 + ", " + 50 + ", " + 70 + ", " + 66 + ", " + 25 + ", " + 7 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					8 + ", " + "'" + "Norris" + "', " + 19 + ", " + 7 + ", " + 52 + ", " + 68 + ", " + 45 + ", " + 72 + ", " + 60 + ", " + 8 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					9 + ", " + "'" + "Ricciardo" + "', " + 30 + ", " + 9 + ", " + 55 + ", " + 79 + ", " + 70 + ", " + 66 + ", " + 25 + ", " + 9 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					10 + ", " + "'" + "Hulkenberg" + "', " + 32 + ", " + 8 + ", " + 65 + ", " + 55 + ", " + 70 + ", " + 68 + ", " + 40 + ", " + 10 + ")";
			st.executeUpdate(sentSQL);		
			sentSQL = "insert into piloto values(" +
					11 + ", " + "'" + "Gasly" + "', " + 23 + ", " + 7 + ", " + 60 + ", " + 57 + ", " + 55 + ", " + 58 + ", " + 34 + ", " + 11 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					12 + ", " + "'" + "Kvyat" + "', " + 25 + ", " + 6 + ", " + 42 + ", " + 35 + ", " + 50 + ", " + 55 + ", " + 41 + ", " + 12 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					13 + ", " + "'" + "Perez" + "', " + 29 + ", " + 7 + ", " + 75 + ", " + 47 + ", " + 55 + ", " + 48 + ", " + 42 + ", " + 13 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					14 + ", " + "'" + "Stroll" + "', " + 20 + ", " + 6 + ", " + 36 + ", " + 50 + ", " + 55 + ", " + 53 + ", " + 39 + ", " + 14 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					15 + ", " + "'" + "Magnussen" + "', " + 27 + ", " + 7 + ", " + 30 + ", " + 55 + ", " + 78 + ", " + 63 + ", " + 30 + ", " + 15 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					16 + ", " + "'" + "Grosjean" + "', " + 33 + ", " + 5 + ", " + 4 + ", " + 44 + ", " + 44 + ", " + 48 + ", " + 45 + ", " + 16 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					17 + ", " + "'" + "Raikkonen" + "', " + 40 + ", " + 7 + ", " + 68 + ", " + 58 + ", " + 64 + ", " + 65 + ", " + 25 + ", " + 17 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					18 + ", " + "'" + "Giovinazzi" + "', " + 25 + ", " + 6 + ", " + 35 + ", " + 36 + ", " + 45 + ", " + 58 + ", " + 44 + ", " + 18 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					19 + ", " + "'" + "Russell" + "', " + 21 + ", " + 6 + ", " + 48 + ", " + 54 + ", " + 25 + ", " + 65 + ", " + 56 + ", " + 19 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto values(" +
					20 + ", " + "'" + "Kubica" + "', " + 34 + ", " + 4 + ", " + 50 + ", " + 33 + ", " + 28 + ", " + 22 + ", " + 34 + ", " + 20 + ")";
			st.executeUpdate(sentSQL);
			
			
			// DATOS TABLA ESCUDERIA
			
			sentSQL = "insert into escuderia values(" +
					1 + ", " + "'" + "Mercedes" + "', '" + "Toto Wolf" + "', " + 1 + ", " + 2 + ", " + 25000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					2 + ", " + "'" + "Ferrari" + "', '" + "Matia Binotto" + "', " + 3 + ", " + 4 + ", " + 25000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					3 + ", " + "'" + "Red Bull" + "', '" + "Christian Horner" + "', " + 5 + ", " + 6 + ", " + 25000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					4 + ", " + "'" + "Mclaren" + "', '" + "Andrea Seidl" + "', " + 7 + ", " + 8 + ", " + 20000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					5 + ", " + "'" + "Renault" + "', '" + "Cyril Abditeboul" + "', " + 9 + ", " + 10 + ", " + 17500000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					6 + ", " + "'" + "ToroRosso" + "', '" + "Franz Tost" + "', " + 11 + ", " + 12 + ", " + 15000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					7 + ", " + "'" + "RacingPoint" + "', '" + "Andrew Green" + "', " + 13 + ", " + 14 + ", " + 15000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					8 + ", " + "'" + "Haas" + "', '" + "Guenther Steiner" + "', " + 15 + ", " + 16 + ", " + 10000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					9 + ", " + "'" + "AlfaRomeo" + "', '" + "Frank Vasseur" + "', " + 17 + ", " + 18 + ", " + 10000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia values(" +
					10 + ", " + "'" + "Williams" + "', '" + "Claire Williams" + "', " + 19 + ", " + 20 + ", " + 8000000 + ")";
			st.executeUpdate(sentSQL);

			
			// DATOS CIRCUITOS
			
			sentSQL = "insert into circuito values(" +
					1 + ", " + "'Albert Park'" + ", " + "'Australia'" + ", " + 2 + ", " + 79.5 + ", " + 84.5 + ", " + 22.0 + ", " + 58 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					2 + ", " + "'Sakhir'" + ", " + "'Baréin'" + ", " + 3 + ", " + 76.8 + ", " + 82.4 + ", " + 22.0 + ", " + 57 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					3 + ", " + "'Shanghai'" + ", " + "'China'" + ", " + 3 + ", " + 80.5 + ", " + 83.7 + ", " + 24.0 + ", " + 56 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					4 + ", " + "'Baku City Circuit'" + "'Azerbaijan'" + ", " + ", " + 2 + ", " + 89.5 + ", " + 92 + ", " + 22.0 + ", " + 51 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					5 + ", " + "'Catalunya'" + ", " + "'España'" + ", " + 3 + ", " + 74.4 + ", " + 77.5 + ", " + 24.0 + ", " + 66 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					6 + ", " + "'Montecarlo'" + ", " + "'Monaco'" + ", " + 1 + ", " + 69.1 + ", " + 73.3 + ", " + 20.0 + ", " + 78 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					7 + ", " + "'Gilles Villeneuve'" + ", " + "'Canada'" + ", " + 2 + ", " + 69.2 + ", " + 72.0 + ", " + 20.0 + ", " + 70 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into circuito values(" +
					8 + ", " + "'Paul Ricard'" + ", " + "'Francia'" + ", " + 2 + ", " + 87.3 + ", " + 91.7 + ", " + 24.0 + ", " + 53 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					9 + ", " + "'Red Bull Ring'" + ", " + "'Austria'" + ", " + 2 + ", " + 62.0 + ", " + 66.5 + ", " + 20.0 + ", " + 71 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					10 + ", " + "'Silverstone'" + ", " + "'Gran Bretaña'" + ", " + 3 + ", " + 84.1 + ", " + 86.4 + ", " + 20.0 + ", " + 52 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					11 + ", " + "'Hockenheim'" + ", " + "'Alemania'" + ", " + 2 + ", " + 70.8 + ", " + 75.6 + ", " + 22.0 + ", " + 64 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					12 + ", " + "'Hungaroring'" + ", " + "'Hungria'" + ", " + 2 + ", " + 73.5 + ", " + 76.1 + ", " + 22.0 + ", " + 70 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					13 + ", " + "'Spa-Francorchamps'" + ", " + "'Belgica'" + ", " + 3 + ", " + 101.5 + ", " + 105.4 + ", " + 24.0 + ", " + 44 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					14 + ", " + "'Monza'" + ", " + 2 + ", " + "'Italia'" + ", " + 78.3 + ", " + 80.8 + ", " + 24.0 + ", " + 53 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					15 + ", " + "'Marina Bay'" + ", " + "'Singapur'" + ", " + 2 + ", " + 95.2 + ", " + 100.3 + ", " + 20.0 + ", " + 61 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					16 + ", " + "'Sochi'" + ", " + "'Rusia'" + ", " + 2 + ", " + 90.6 + ", " + 94.8 + ", " + 20.0 + ", " + 53 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					17 + ", " + "'Suzuka'" + ", " + "'Japon'" + ", " + 3 + ", " + 86.0 + ", " + 90.0 + ", " + 20.0 + ", " + 52 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					18 + ", " + "'Hermanos Rodriguez'" + ", " + "'Mexico'" + ", " + 2 + ", " + 74.0 + ", " + 78.2 + ", " + 22.0 + ", " + 71 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					19 + ", " + "'Circuit Of The Americas'" + ", "  + "'USA'" + ", " + 3 + ", " + 91.2 + ", " + 96.3 + ", " + 22.0 + ", " + 56 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					20 + ", " + "'Interlagos'" + ", " + "'Brasil'" + ", " + 2 + ", " + 66.3 + ", " + 69.5 + ", " + 24.0 + ", " + 71 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito values(" +
					21 + ", " + "'Yas Marina'" + ", "  + "'Abu Dhabi'" + ", " + 3 + ", " + 93.8 + ", " + 99.8 + ", " + 26.0 + ", " + 55 + ")";
			//st.executeUpdate(sentSQL);
			
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que aÃ±adir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			return false;
		}
	}


	//GESTION CLASIFICACION - Pilotos-Escuderias
	
	public static boolean insertclasificacionpilotos( Statement st, Integer puesto, Integer piloto_id, Integer puntos) {
		String sentSQL = "";
		try {
			sentSQL = "insert into clasificacionpilotos values (" +
					puesto + ", " + piloto_id + ", " + puntos + ")";
			st.executeUpdate(sentSQL);
					
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que aÃ±adir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			return false;
		}
	}
		
	
	public static boolean insertclasificacioncuderias( Statement st, Integer puesto, Integer escuderia_id, Integer puntos) {
		String sentSQL = "";
		try {
			sentSQL = "insert into clasificacionescuderias values (" +
					puesto + ", " + escuderia_id + ", " + puntos + ")";
			st.executeUpdate(sentSQL);
					
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD añadida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que aÃ±adir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			return false;
		}
	}
	
	
	public static boolean updateClasificacionPilotos( Statement st, int posicion, int piloto_id, int puntos) {
		String sentSQL = "";
		try {
			sentSQL = "update clasificacionpilotos set" +
					" posicion=" + posicion + 
					" piloto_id=" + piloto_id +
					" puntos=" + puntos ;
					//" where posicion=" + posicion + " AND piloto_id=" + piloto_id ;

			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD modificada " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que modificar 1 - error si no
				log( Level.SEVERE, "Error en update de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return false;
		}
	}
	
	
	public static boolean updateClasificacionEscuderias( Statement st, int posicion, int escuderia_id, int puntos) {
		String sentSQL = "";
		try {
			sentSQL = "update clasificacionescuderias set" +
					" posicion=" + posicion + 
					" escuderia_id=" + escuderia_id +
					" puntos=" + puntos ;
					//" where posicion=" + posicion + " AND escuderia_id=" + escuderia_id ;

			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD modificada " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que modificar 1 - error si no
				log( Level.SEVERE, "Error en update de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return false;
		}
	}


	
	//SELECCION DATOS - Componente-Coche-Piloto-Escuderia
		
	

	public static Componente componenteSelect (Statement st, int componente_id) {
		String sentSQL = "";
		try {
			sentSQL = "Select * from componente where componente_id =" + componente_id;
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				String nombrecomponente = rs.getString("nombre");
				Integer rendimiento = rs.getInt("rendimiento");
				
				Componente componente = new Componente(nombrecomponente, rendimiento);
				return componente;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}
	}
		
	
	
	
	public static Coche cocheSelect (Statement st, int coche_id) {
		String sentSQL = "";
		try {
			sentSQL = "Select * from coche where coche_id =" + coche_id;
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				String nombrecoche = rs.getString("nombre");			
				Integer c1 = rs.getInt("componente1_id");
				Componente comp1 = componenteSelect(st, c1);				
				Integer c2 = rs.getInt("componente2_id");
				Componente comp2 = componenteSelect(st, c2);
				Integer c3 = rs.getInt("componente3_id");
				Componente comp3 = componenteSelect(st, c3);

				Coche coche = new Coche(nombrecoche, comp1, comp2, comp3, 1);
				return coche;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}
	}	
	
	

	
	public static Piloto pilotoSelect (Statement st, int piloto_id) {
		String sentSQL = "";
		try {
			sentSQL = "Select * from coche where piloto_id =" + piloto_id;
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				String nombrepiloto = rs.getString("nombre");
				Integer edad = rs.getInt("edad");
				Integer nivel = rs.getInt("nivel");
				Integer regularidad = rs.getInt("regularidad");
				Integer adelantar = rs.getInt("adelantar");
				Integer defender = rs.getInt("defender");
				Integer velocidad = rs.getInt("velocidad");
				Integer mojado = rs.getInt("mojado");				
				Integer coche_id = rs.getInt("coche_id");
				
				Coche coche = cocheSelect(st,coche_id);
				
				Piloto piloto = new Piloto(nombrepiloto, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche);
				return piloto;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}		
	}
	
	

	
	public static Escuderia escuderiaSelect (Statement st, int escuderia_id) {
		String sentSQL = "";
		try {
			sentSQL = "Select * from escuderia where escuderia_id =" + escuderia_id;
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				String nombreescuderia = rs.getString("nombre");
				String director = rs.getString("director");				
				Integer p1_id = rs.getInt("piloto1_id");
				Piloto piloto1 = pilotoSelect(st, p1_id);
				Integer p2_id = rs.getInt("piloto2_id");
				Piloto piloto2 = pilotoSelect(st, p2_id);
				Integer presupuesto = rs.getInt("presupuesto");

				Escuderia escuderia = new Escuderia(nombreescuderia, director, piloto1, piloto2, presupuesto);
				return escuderia;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}			
	}

	
	public static ArrayList<Componente> listaComponentesSelect (Statement st) {
		ArrayList<Componente> ret = new ArrayList<>();
		String sentSQL = "";
		try {
			sentSQL = "Select * from componente";
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				int componente_id = rs.getInt("componente_id");		
				Componente componente = componenteSelect(st, componente_id);
				ret.add(componente);
				return ret;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<Coche> listaCochesSelect (Statement st) {
		ArrayList<Coche> ret = new ArrayList<>();
		String sentSQL = "";
		try {
			sentSQL = "Select * from coche";
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				int coche_id = rs.getInt("coche_id");		
				Coche coche = cocheSelect(st, coche_id);
				ret.add(coche);
				return ret;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ArrayList<Piloto> listaPilotosSelect (Statement st) {
		ArrayList<Piloto> ret = new ArrayList<>();
		String sentSQL = "";
		try {
			sentSQL = "Select * from piloto";
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				int piloto_id = rs.getInt("piloto_id");		
				Piloto piloto = pilotoSelect(st, piloto_id);
				ret.add(piloto);
				return ret;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}
	}
		
	
	public static ArrayList<Escuderia> listaEscuderiasSelect (Statement st) {
		ArrayList<Escuderia> ret = new ArrayList<>();
		String sentSQL = "";
		try {
			sentSQL = "Select * from escuderia";
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				int escuderia_id = rs.getInt("escuderia_id");
				Escuderia escuderia = escuderiaSelect(st, escuderia_id);	
				ret.add(escuderia);
				return ret;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}
	}
	
	
	//SELECCION DATOS - Circuito-Carrera-Temporadaa
	
	public static Circuito circuitoSelect (Statement st, int circuito_id) {
		String sentSQL = "";
		try {
			sentSQL = "Select * from circuito where circuito_id =" + circuito_id;
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				String nombrecircuito = rs.getString("nombre");
				String pais = rs.getString("pais");
				Integer nivDegradacion = rs.getInt("nivDegradacion");
				Float tiempoRefClas = rs.getFloat("tiempoRefClas");
				Float tiempoRefCar = rs.getFloat("tiempoRefCar");
				Float tiempoRefBox = rs.getFloat("tiempoRefBox");
				Integer vueltas = rs.getInt("vueltas");
				
				Circuito circuito = new Circuito(nombrecircuito, pais, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas);
				return circuito;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}	
	}
	
	
	public static Carrera carreraSelect (Statement st, int carrera_id) {
		String sentSQL = "";
		try {
			sentSQL = "Select DISTINCT * from carrera where carrera_id =" + carrera_id;
			ResultSet rs = st.executeQuery(sentSQL);
			
			//Solo una vez, sin ir pasando todos los datos, para no crear multiples carreras!??
			if (rs.next()) {
			Integer circuito_id = rs.getInt("circuito_id");
			Circuito circuito = circuitoSelect(st, circuito_id);
			ArrayList<Piloto> listaPilotos = listaPilotosSelect(st);
			
			Carrera carrera = new Carrera(circuito, listaPilotos);
			return carrera;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}	
	}
	
	
	public static ArrayList<Circuito> listaCircuitosSelect (Statement st) {
		ArrayList<Circuito> ret = new ArrayList<>();
		String sentSQL = "";
		try {
			sentSQL = "Select * from circuito";
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				int circuito_id = rs.getInt("circuito_id");		
				Circuito circuito = circuitoSelect(st, circuito_id);
				ret.add(circuito);
				return ret;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public static ArrayList<Carrera> listaCarrerasSelect (Statement st) {
		ArrayList<Carrera> ret = new ArrayList<>();
		String sentSQL = "";
		try {
			sentSQL = "Select * from circuito";
			ResultSet rs = st.executeQuery(sentSQL);
			
			if (rs.next()) {
				int carrera_id = rs.getInt("carrera_id");		
				Carrera carrera = carreraSelect(st, carrera_id);
				ret.add(carrera);
				return ret;
			}
			rs.close();
			log(Level.INFO, "BD/t" + sentSQL, null);
			return null;
		}catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			e.printStackTrace();
			return null;
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
		
		Connection con = initBD(url);
		//usarCrearTablasBD(con);
		Statement st = con.createStatement();
		st.executeUpdate("delete from componente");
		st.executeUpdate("delete from coche");
		st.executeUpdate("delete from piloto");
		st.executeUpdate("delete from escuderia");
		st.executeUpdate("delete from circuito");
		insertDatos(st);
	}
}