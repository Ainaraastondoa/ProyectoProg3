package elementos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BD {
	 
	
	static String url = "src/datos/F1BaseDatos.csv";
	
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
				statement.executeUpdate("create table componente " +
					"(componente_id string primary key not null" +	//codigo de componente
					", nombre string" +								//nombre de componente
					", rendimiento integer" +						//Rendimiento del componente
					")");
				
				statement.executeUpdate("create table coche " +
						"(coche_id string primary key not null" +
						", nombre string" +
						", componente_id1 string references componente(componente_id)" + 
						", componente_id2 string references componente(componente_id)" + 
						", componente_id3 string references componente(componente_id)" + 
						", porcentajeRuedas real" +
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
						", coche_id string not null references coche(coche_id)" +
						")");
				
				statement.executeUpdate("create table escuderia " +
					"(escuderia_id string primary key not null" +	// Codigo de la escuderia
					", nombre string" +           					// Nombre de la escuderia
					", director string" +           				// Nombre del director
					", piloto1_id string not null references piloto(piloto_id)"  +       		// Codigo del piloto1
					", piloto2_id string not null references piloto(piloto_id)" +    			// Codigo del piloto2
					", presupuesto integer" +						// Presupuesto del equipo
					")");
				
				//ESTO????
				//TABLA relacion
				statement.executeUpdate("create table relacion " +
					"(escuderia_id primary key not null" +
					", piloto1_id string not null references piloto(piloto_id)" +
					", piloto2_id string not null references piloto(piloto_id)" +
					", coche_id string not null references coche(coche_id)" +
					", componente1_id string not null references componente(componente_id)" +
					", componente2_id string not null references componente(componente_id)" +
					", componente3_id string not null references componente(componente_id)" +
					")");
				
				//TABLA clasificacionpilotos
				statement.executeUpdate("create table clasificacionpilotos " +
					"(puesto integer primary key not null" +	//puesto en la clasificacion
					", piloto_id string not null references piloto(piloto_id)" +						//codigo del piloto
					", nombre string not null references piloto(nombre)" +							//nombre del piloto
					", puntos integer " +						//puntos conseguidos
					")");
				
				
				//ESTO COMO??? PUNTOS ESCUDERIA SUMA 2 PILOTOS
				//TABLA clasificacionescuderias
				statement.executeUpdate("create table clasificacionescuderias " +
					"(puesto integer primary key not null" +	//puesto en la clasificacion
					", escuderia_id string not null references escuderia(escuderia_id)" +					//codigo de la escuderia
					", nombre string not null references escuderia(nombre)" +							//nombre de la escuderia
					", puntos integer " +						//puntos conseguidos
					")");
				
				//TABLA circuitos
				statement.executeUpdate("create table circuito " +
						"(circuito_id string primary key not null" +	
						", nombre string " +			
						", nivDegradacion integer" +					
						", tiempoRefClas float " +	
						", tiempoRefCar float " +					
						", tiempoRefBox float " +					
						", vueltas integer" +
						")");
				
				//CON LA ESCUDERIA PODEMOS ACCEDER A SUS PILOTOS, COCHES. ETC?
				//TABLA carrera
				statement.executeUpdate("create table carrera " +
						"(carrera_id integer primary key not null" +	//id carrera
						", circuito_id integer foreign key not null references circuito(circuito_id)" +
						", escuderia_id string not null references carrera(carrera_id) " +				//id escuderia participante
						")");
				
				//TABLA temporada
				statement.executeUpdate("create table temporada " +
					"(anno integer primary key not null" +										//a�o de temporada
					", carrera_id string not null references carrera(carrera_id) " +			//id carrera
					")");


								
			} catch (SQLException e) {} // Tabla ya existe. Nada que hacer
			try {
				
				statement.executeUpdate("create table componente " +
						"(componente_id string primary key" +	//codigo componente
						", nombre string" +						//nombre componente
						")");
				
				statement.executeUpdate("create table coche " +
						"(coche_id string primary key" +	//codigo de coche
						", nombre string" +					//nombre de coche
						")");
				
				statement.executeUpdate("create table piloto " +
						"(piloto_id string primary key" +	//codigo de piloto
						", nombre string" +					//nombre de piloto
						")");
				
				statement.executeUpdate("create table escuderia " +
					"(escuderia_id string primary key" +    // Codigo de escuderia
					", nombre String " +   					// Nombre de escuderia
					")");
				
			} catch (SQLException e) {} // Si la tabla ya existe no hay nada que hacer
			log( Level.INFO, "Creada base de datos", null );
			return statement;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en creación de base de datos", e );
			return null;
		}		
	}
	
	public static boolean insertDatos( Statement st) {
		String sentSQL = "";
		try {
			// DATOS TABLA COMPONENTE
			//DATOS MOTOR
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					1 + ", " + "'" + "mercedes.m" + "', " + 88 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					2 + ", " + "'" + "ferrari.m" + "', " + 91 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					3 + ", " + "'" + "renault.m" + "', " + 81 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					4 + ", " + "'" + "honda.m" + "', " + 80 + ")";
			st.executeUpdate(sentSQL);
			
			// DATOS AERODINAMICA
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					5 + ", " + "'" + "mercedes.a" + "', " + 88 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					6 + ", " + "'" + "ferrari.a" + "', " + 88 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					7 + ", " + "'" + "redbull.a" + "', " + 89 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					8 + ", " + "'" + "mclaren.a" + "', " + 82 + ")";
			st.executeUpdate(sentSQL);		
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					9 + ", " + "'" + "renault.a" + "', " + 78 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					10 + ", " + "'" + "tororosso.a" + "', " + 76 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					11 + ", " + "'" + "racingpoint.a" + "', " + 75 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					12 + ", " + "'" + "haas.a" + "', " + 71 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					13 + ", " + "'" + "alfaromeo.a" + "', " + 70 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					14 + ", " + "'" + "williams.a" + "', " + 51 + ")";
			st.executeUpdate(sentSQL);	
			
			//DATOS CHASIS
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					15 + ", " + "'" + "mercedes.c" + "', " + 90 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					16 + ", " + "'" + "ferrari.c" + "', " + 85 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					17 + ", " + "'" + "redbull.c" + "', " + 91 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					18 + ", " + "'" + "mclaren.c" + "', " + 79 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					19 + ", " + "'" + "renault.c" + "', " + 76 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					20 + ", " + "'" + "tororosso.c" + "', " + 77 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					21 + ", " + "'" + "racingpoint.c" + "', " + 73 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					22 + ", " + "'" + "haas.c" + "', " + 72 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					23 + ", " + "'" + "alfaromeo.c" + "', " + 68 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into componente (componente_id, nombre, rendimiento) values(" +
					24 + ", " + "'" + "williams.c" + "', " + 55 + ")";
			st.executeUpdate(sentSQL);
			
			
			//DATOS TABLA COCHE			
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					1 + ", " + "'" + "mercedes" + "', " + 1 + ", " + 5 + ", " + 15 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					2 + ", " + "'" + "ferrari" + "', " + 2 + ", " + 6 + ", " + 16 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					3 + ", " + "'" + "red bull" + "', " + 4 + ", " + 7 + ", " + 17 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					4 + ", " + "'" + "mclaren" + "', " + 3 + ", " + 8 + ", " + 18 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					5 + ", " + "'" + "renault" + "', " + 3 + ", " + 9 + ", " + 19 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					6 + ", " + "'" + "toro rosso" + "', " + 4 + ", " + 10 + ", " + 20 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					7 + ", " + "'" + "racing point" + "', " + 1 + ", " + 11 + ", " + 21 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					8 + ", " + "'" + "haas" + "', " + 2 + ", " + 12 + ", " + 22 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					9 + ", " + "'" + "alfa romeo" + "', " + 2 + ", " + 13 + ", " + 23 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into coche (coche_id, nombre, componente_id1, componente_id2, componente_id3, porcentajeRuedas) values(" +
					10 + ", " + "'" + "williams" + "', " + 1 + ", " + 14 + ", " + 24 + ", " + null + ")";
			st.executeUpdate(sentSQL);
			
			
			//DATOS TABLA PILOTO
	
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					1 + ", " + "'" + "Hamilton" + "', " + 34 + ", " + 10 + ", " + 75 + ", " + 81 + ", " + 75 + ", " + 85 + ", " + 88 + ", " + 1 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					2 + ", " + "'" + "Bottas" + "', " + 30 + ", " + 8 + ", " + 60 + ", " + 47 + ", " + 52 + ", " + 66 + ", " + 40 + ", " + 1 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					3 + ", " + "'" + "Vettel" + "', " + 32 + ", " + 9 + ", " + 62 + ", " + 69 + ", " + 66 + ", " + 77 + ", " + 60 + ", " + 2 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					4 + ", " + "'" + "Leclerc" + "', " + 22 + ", " + 9 + ", " + 55 + ", " + 79 + ", " + 77 + ", " + 80 + ", " + 68 + ", " + 2 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					5 + ", " + "'" + "Verstappen" + "', " + 22 + ", " + 9 + ", " + 50 + ", " + 75 + ", " + 70 + ", " + 77 + ", " + 90 + ", " + 3 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					6 + ", " + "'" + "Albon" + "', " + 23 + ", " + 7 + ", " + 60 + ", " + 65 + ", " + 55 + ", " + 66 + ", " + 45 + ", " + 3 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					7 + ", " + "'" + "Sainz" + "', " + 25 + ", " + 8 + ", " + 70 + ", " + 50 + ", " + 70 + ", " + 66 + ", " + 25 + ", " + 4 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					8 + ", " + "'" + "Norris" + "', " + 19 + ", " + 7 + ", " + 52 + ", " + 68 + ", " + 45 + ", " + 72 + ", " + 60 + ", " + 4 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					9 + ", " + "'" + "Ricciardo" + "', " + 30 + ", " + 9 + ", " + 55 + ", " + 79 + ", " + 70 + ", " + 66 + ", " + 25 + ", " + 5 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					10 + ", " + "'" + "Hulkenberg" + "', " + 32 + ", " + 8 + ", " + 65 + ", " + 55 + ", " + 70 + ", " + 68 + ", " + 40 + ", " + 5 + ")";
			st.executeUpdate(sentSQL);		
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					11 + ", " + "'" + "Gasly" + "', " + 23 + ", " + 7 + ", " + 60 + ", " + 57 + ", " + 55 + ", " + 58 + ", " + 34 + ", " + 6 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					12 + ", " + "'" + "Kvyat" + "', " + 25 + ", " + 6 + ", " + 42 + ", " + 35 + ", " + 50 + ", " + 55 + ", " + 41 + ", " + 6 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					13 + ", " + "'" + "Perez" + "', " + 29 + ", " + 7 + ", " + 75 + ", " + 47 + ", " + 55 + ", " + 48 + ", " + 42 + ", " + 7 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					14 + ", " + "'" + "Stroll" + "', " + 20 + ", " + 6 + ", " + 36 + ", " + 50 + ", " + 55 + ", " + 53 + ", " + 39 + ", " + 7 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					15 + ", " + "'" + "Magnussen" + "', " + 27 + ", " + 7 + ", " + 30 + ", " + 55 + ", " + 78 + ", " + 63 + ", " + 30 + ", " + 8 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					16 + ", " + "'" + "Grosjean" + "', " + 33 + ", " + 5 + ", " + 4 + ", " + 44 + ", " + 44 + ", " + 48 + ", " + 45 + ", " + 8 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					17 + ", " + "'" + "Raikkonen" + "', " + 40 + ", " + 7 + ", " + 68 + ", " + 58 + ", " + 64 + ", " + 65 + ", " + 25 + ", " + 9 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					18 + ", " + "'" + "Giovinazzi" + "', " + 25 + ", " + 6 + ", " + 35 + ", " + 36 + ", " + 45 + ", " + 58 + ", " + 44 + ", " + 9 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					19 + ", " + "'" + "Russell" + "', " + 21 + ", " + 6 + ", " + 48 + ", " + 54 + ", " + 25 + ", " + 65 + ", " + 56 + ", " + 10 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into piloto (piloto_id, nombre, edad, nivel, regularidad, adelantar, defender, velocidad, mojado, coche_id) values(" +
					20 + ", " + "'" + "Kubica" + "', " + 34 + ", " + 4 + ", " + 50 + ", " + 33 + ", " + 28 + ", " + 22 + ", " + 34 + ", " + 10 + ")";
			st.executeUpdate(sentSQL);
			
			
			// DATOS TABLA ESCUDERIA
			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					1 + ", " + "'" + "Mercedes" + "', '" + "Toto Wolf" + "', '" + 1 + ", " + 2 + ", " + 25000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					2 + ", " + "'" + "Ferrari" + "', '" + "Matia Binotto" + "', '" + 3 + ", " + 4 + ", " + 25000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					3 + ", " + "'" + "Red Bull" + "', '" + "Christian Horner" + "', '" + 5 + ", " + 6 + ", " + 25000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					4 + ", " + "'" + "Mclaren" + "', '" + "Andrea Seidl" + "', '" + 7 + ", " + 8 + ", " + 20000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					5 + ", " + "'" + "Renault" + "', '" + "Cyril Abditeboul" + "', '" + 9 + ", " + 10 + ", " + 17500000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					6 + ", " + "'" + "Toro Rosso" + "', '" + "Franz Tost" + "', '" + 11 + ", " + 12 + ", " + 15000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					7 + ", " + "'" + "Racing Point" + "', '" + "Andrew Green" + "', '" + 13 + ", " + 14 + ", " + 15000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					8 + ", " + "'" + "Haas" + "', '" + "Guenther Steiner" + "', '" + 15 + ", " + 16 + ", " + 10000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					9 + ", " + "'" + "Alfa Romeo" + "', '" + "Frank Vasseur" + "', '" + 17 + ", " + 18 + ", " + 10000000 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into escuderia (escuderia_id, nombre, director, piloto1_id, piloto2_id, presupuesto) values(" +
					10 + ", " + "'" + "Williams" + "', '" + "Claire Williams" + "', " + 19 + ", " + 20 + ", " + 8000000 + ")";
			st.executeUpdate(sentSQL);

			
			//RELACION ENTRE DATOS.
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					1 + ", " + 1 + ", " + 2 + ", " + 1 + ", " + 1 + ", " + 5 + ", " + 15 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					2 + ", " + 3 + ", " + 4 + ", " + 2 + ", " + 2 + ", " + 6 + ", " + 16 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					3 + ", " + 5 + ", " + 6 + ", " + 3 + ", " + 4 + ", " + 7 + ", " + 17 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					4 + ", " + 7 + ", " + 8 + ", " + 4 + ", " + 3 + ", " + 8 + ", " + 18 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					5 + ", " + 9 + ", " + 10 + ", " + 5 + ", " + 3 + ", " + 9 + ", " + 19 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					6 + ", " + 11 + ", " + 12 + ", " + 6 + ", " + 4 + ", " + 10 + ", " + 20 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					7 + ", " + 13 + ", " + 14 + ", " + 7 + ", " + 1 + ", " + 11 + ", " + 21 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					8 + ", " + 15 + ", " + 16 + ", " + 8 + ", " + 2 + ", " + 12 + ", " + 22 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					9 + ", " + 17 + ", " + 18 + ", " + 9 + ", " + 2 + ", " + 13 + ", " + 23 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into relacion (escuderia_id, piloto1_id, piloto2_id, coche_id, componente1_id, componente2_id, componente3_id) values(" +
					10 + ", " + 19 + ", " + 20 + ", " + 10 + ", " + 1 + ", " + 14 + ", " + 24 + ")";
			st.executeUpdate(sentSQL);
			
			//DATOS clasificacionpilotos
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					1 + ", " + 1 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					2 + ", " + 2 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					3 + ", " + 3 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					4 + ", " + 4 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					5 + ", " + 5 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					6 + ", " + 6 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					7 + ", " + 7 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					8 + ", " + 8 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					9 + ", " + 9 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					10 + ", " + 10 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					11 + ", " + 11 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					12 + ", " + 12 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					13 + ", " + 13 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					14 + ", " + 14 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					15 + ", " + 15 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					16 + ", " + 16 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					17 + ", " + 17 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					18 + ", " + 18 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					19 + ", " + 19 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionpilotos (puesto, piloto_id, puntos) values(" +
					20 + ", " + 20 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			
			//DATOS clasificacionescuderias
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					1 + ", " + 1 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					2 + ", " + 2 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					3 + ", " + 3 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					4 + ", " + 4 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					5 + ", " + 5 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					6 + ", " + 6 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					7 + ", " + 7 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					8 + ", " + 8 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					9 + ", " + 9 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into clasificacionescuderias (puesto, escuderia_id, puntos) values(" +
					10 + ", " + 10 + ", " + 0 + ")";
			st.executeUpdate(sentSQL);
			
			// DATOS CIRCUITOS
			
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					1 + ", " + "'Albert Park'" + ", " + 2 + ", " + 79.5 + ", " + 84.5 + ", " + 22.0 + ", " + 58 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					2 + ", " + "'Sakhir'" + ", " + 3 + ", " + 76.8 + ", " + 82.4 + ", " + 22.0 + ", " + 57 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					3 + ", " + "'Shanghai'" + ", " + 3 + ", " + 80.5 + ", " + 83.7 + ", " + 24.0 + ", " + 56 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					4 + ", " + "'Baku City Circuit'" + ", " + 2 + ", " + 89.5 + ", " + 92 + ", " + 22.0 + ", " + 51 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					5 + ", " + "'Catalunya'" + ", " + 3 + ", " + 74.4 + ", " + 77.5 + ", " + 24.0 + ", " + 66 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					6 + ", " + "'Montecarlo'" + ", " + 1 + ", " + 69.1 + ", " + 73.3 + ", " + 20.0 + ", " + 78 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					7 + ", " + "'Gilles Villeneuve'" + ", " + 2 + ", " + 69.2 + ", " + 72.0 + ", " + 20.0 + ", " + 70 + ")";
			st.executeUpdate(sentSQL);			
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					8 + ", " + "'Paul Ricard'" + ", " + 2 + ", " + 87.3 + ", " + 91.7 + ", " + 24.0 + ", " + 53 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					9 + ", " + "'Red Bull Ring'" + ", " + 2 + ", " + 62.0 + ", " + 66.5 + ", " + 20.0 + ", " + 71 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					10 + ", " + "'Silverstone'" + ", " + 3 + ", " + 84.1 + ", " + 86.4 + ", " + 20.0 + ", " + 52 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					11 + ", " + "'Hockenheim'" + ", " + 2 + ", " + 70.8 + ", " + 75.6 + ", " + 22.0 + ", " + 64 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					12 + ", " + "'Hungaroring'" + ", " + 2 + ", " + 73.5 + ", " + 76.1 + ", " + 22.0 + ", " + 70 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					13 + ", " + "'Spa-Francorchamps'" + ", " + 3 + ", " + 101.5 + ", " + 105.4 + ", " + 24.0 + ", " + 44 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					14 + ", " + "'Monza'" + ", " + 2 + ", " + 78.3 + ", " + 80.8 + ", " + 24.0 + ", " + 53 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					15 + ", " + "'Marina Bay'" + ", " + 2 + ", " + 95.2 + ", " + 100.3 + ", " + 20.0 + ", " + 61 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					16 + ", " + "'Sochi'" + ", " + 2 + ", " + 90.6 + ", " + 94.8 + ", " + 20.0 + ", " + 53 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					17 + ", " + "'Suzuka'" + ", " + 3 + ", " + 86.0 + ", " + 90.0 + ", " + 20.0 + ", " + 52 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					18 + ", " + "'Hermanos Rodriguez'" + ", " + 2 + ", " + 74.0 + ", " + 78.2 + ", " + 22.0 + ", " + 71 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					19 + ", " + "'Circuit Of The Americas'" + ", " + 3 + ", " + 91.2 + ", " + 96.3 + ", " + 22.0 + ", " + 56 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					20 + ", " + "'Interlagos'" + ", " + 2 + ", " + 66.3 + ", " + 69.5 + ", " + 24.0 + ", " + 71 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into circuito (circuito_id, nombre, nivDegradacion, tiempoRefClas, tiempoRefCar, tiempoRefBox, vueltas) values(" +
					21 + ", " + "'Yas Marina'" + ", " + 3 + ", " + 93.8 + ", " + 99.8 + ", " + 26.0 + ", " + 55 + ")";
			st.executeUpdate(sentSQL);

			
//			//DATOS carrera
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 1 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 2 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 3 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 4 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 5 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 6 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 7 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 8 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 9 + ")";
			st.executeUpdate(sentSQL);
			sentSQL = "insert into carrera (carrera_id, circuito_id, escuderia_id) values(" +
					1 + ", " + 1 + ", " + 10 + ")";
			//--- x21 ---
			
			
//			//DATOS temporada

			sentSQL = "insert into temporada (anno, carrera_id) values(" +
					2019 + ", " + 1 + ")";
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
	
	public static boolean insertclasificacioncuderias( Statement st, Integer puesto, Integer escuderia_id, String nombre, Integer puntos) {
		String sentSQL = "";
		try {
			sentSQL = "insert into clasificacionpilotos(puesto, piloto_id, nombre, puntos) values (" +
					puesto + ", " +
					"" + escuderia_id + ", " +
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
		insertDatos(st);
	}
}