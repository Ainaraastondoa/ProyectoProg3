package elementos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

 
public class Trayectoria {

	// ATRIBUTOS de la clase Trayectoria - DATOS
	private ArrayList<Piloto> listaPilotos;
	private ArrayList<Componente> listaComponentes;
	private ArrayList<Coche> listaCoches;
	private ArrayList<Circuito> listaCircuitos;
	private ArrayList<Carrera> listaCarreras;
	private ArrayList<Escuderia> listaEscuderias;
	private ArrayList<Temporada> listaTemporadas;
	
	private static Escuderia escuderia; 		//Escuderia seleccionada para la trayectoria
	

	// CONSTRUCTOR de la clase Trayectoria
	public Trayectoria() {
		// Creación de la base de datos y la trayectoria
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st;
		try {
			st = con.createStatement();
			BD.usarCrearTablasBD(con);
			//PARA QUE NO SALGA EL UNIQUE CONSTRAINT ERROR EN LA BASE DE DATOS (METER MÁS DE UNA VEZ LOS MISMOS DATOS)
			//BD.insertDatos(st);
			
			// Inicializar componentes trayectoria
//			ArrayList<ArrayList<Mejora>> listaMejoras = Mejora.crearMejorasPredeterminadas();
			this.listaComponentes = BD.listaComponentesSelect(st);
			this.listaCoches = BD.listaCochesSelect(st);
			this.listaCircuitos = BD.listaCircuitosSelect(st);
			this.listaPilotos = BD.listaPilotosSelect(st);
			this.listaEscuderias = BD.listaEscuderiasSelect(st);
			this.listaCarreras = new ArrayList<Carrera>();
			this.listaTemporadas = new ArrayList<Temporada>();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// GETTERS Y SETTERS
	public ArrayList<Temporada> getListaTemporadas() {
		return listaTemporadas;
	}

	public void setListaTemporadas(ArrayList<Temporada> listaTemporadas) {
		this.listaTemporadas = listaTemporadas;
	}

	public ArrayList<Escuderia> getListaEscuderias() {
		return listaEscuderias;
	}

	public void setListaEscuderias(ArrayList<Escuderia> listaEscuderias) {
		this.listaEscuderias = listaEscuderias;
	}
	
	public ArrayList<Piloto> getListaPilotos() {
		return listaPilotos;
	}

	public void setListaPilotos(ArrayList<Piloto> listaPilotos) {
		this.listaPilotos = listaPilotos;
	}

	public ArrayList<Componente> getListaComponentes() {
		return listaComponentes;
	}

	public void setListaComponentes(ArrayList<Componente> listaComponentes) {
		this.listaComponentes = listaComponentes;
	}

	public ArrayList<Coche> getListaCoches() {
		return listaCoches;
	}

	public void setListaCoches(ArrayList<Coche> listaCoches) {
		this.listaCoches = listaCoches;
	}

	public ArrayList<Circuito> getListaCircuitos() {
		return listaCircuitos;
	}

	public void setListaCircuitos(ArrayList<Circuito> listaCircuitos) {
		this.listaCircuitos = listaCircuitos;
	}

	public ArrayList<Carrera> getListaCarreras() {
		return listaCarreras;
	}

	public void setListaCarreras(ArrayList<Carrera> listaCarreras) {
		this.listaCarreras = listaCarreras;
	}

	public static Escuderia getEscuderia() {
		return escuderia;
	}

	public static void setEscuderia(Escuderia escuderia) {
		Trayectoria.escuderia = escuderia;
	}

	// Método de aumento de la edad de los pilotos tras una temporada
	public void aumentarEdadPilotos() {
		for (Piloto p : this.getListaPilotos()) {
			p.setEdad( p.getEdad()+ 1 );
		}
	}
	
	// Método de simulación de una trayectoria
	public void simularTrayectoriaPrueba() {
		
		// Añadir temporada
		Temporada t = new Temporada(2019, this.listaPilotos, this.listaEscuderias);
		this.getListaTemporadas().add(t);
		
		// Añadir carrera 1
		Carrera c = new Carrera(this.getListaCircuitos().get(0), this.getListaPilotos());
		this.getListaTemporadas().get(0).getListaCarreras().add(c);
		
		// Simular Carrera (carrera 1)
		Integer carreraActual = 0;
		t.getListaCarreras().get(carreraActual).simularCarrera();
		t.getListaCarreras().get(carreraActual).ordenarClasificacionCarrera();
		t.getListaCarreras().get(carreraActual).repartirPuntos(t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).actualizarPuntosEscuderia(t.getPuntosEscuderia(), t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).repartirDinero(t.getPuntosEscuderia());
				
		// Comprobaciones (carrera 1)
		System.out.println("Resultado Carrera:");
		System.out.println(t.getListaCarreras().get(carreraActual).getListaPilotos());
		System.out.println(t.getListaCarreras().get(carreraActual).getListaTiempos());
		t.getPuntosPiloto().forEach( (k, v) -> System.out.println("Piloto: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Dinero: " + k.getPresupuesto()));
		
		// Añadir carrera 2
		Carrera c2 = new Carrera(this.getListaCircuitos().get(1), this.getListaPilotos());
		this.getListaTemporadas().get(0).getListaCarreras().add(c2);
		
		// Simular Carrera (carrera 2)
		carreraActual = 1;
		t.getListaCarreras().get(carreraActual).simularCarrera();
		t.getListaCarreras().get(carreraActual).ordenarClasificacionCarrera();
		t.getListaCarreras().get(carreraActual).repartirPuntos(t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).actualizarPuntosEscuderia(t.getPuntosEscuderia(), t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).repartirDinero(t.getPuntosEscuderia());
				
		// Comprobaciones (carrera 2)
		System.out.println("Resultado Carrera:");
		System.out.println(t.getListaCarreras().get(carreraActual).getListaPilotos());
		System.out.println(t.getListaCarreras().get(carreraActual).getListaTiempos());
		t.getPuntosPiloto().forEach( (k, v) -> System.out.println("Piloto: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Dinero: " + k.getPresupuesto()));
	}
	
	// Método que sirve para simular una temporada
	public void simularTemporada() {
		// Crear temporada. Las temporadas se van a leer desde el fichero de temporadas.
		Temporada t = new Temporada(2019, this.listaPilotos, this.listaEscuderias);
		this.getListaTemporadas().add(t);
		// Crear todas las carreras
		for (Circuito c : this.getListaCircuitos()) {
			Carrera ca = new Carrera(c, this.getListaPilotos());
			this.getListaTemporadas().get(0).getListaCarreras().add(ca);
		}
		// Simular todas las carreras
		for (Carrera ca : t.getListaCarreras()) {
			ca.simularCarrera();
			ca.ordenarClasificacionCarrera();
			ca.repartirPuntos(t.getPuntosPiloto());
			ca.actualizarPuntosEscuderia(t.getPuntosEscuderia(), t.getPuntosPiloto());
			ca.repartirDinero(t.getPuntosEscuderia());
		}
		// Mostrar resultados
		t.getPuntosPiloto().forEach( (k, v) -> System.out.println("Piloto: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Dinero: " + k.getPresupuesto()));
	}
	
	//Método para crear el fichero serializado donde se van a volcar los datos de las
	//temporadas
	public void crearFichero(String fichero) {
		
		ArrayList<Temporada> temporadas = this.getListaTemporadas();
		
		try {
			FileOutputStream fich = new FileOutputStream(fichero);
			ObjectOutputStream fichBi = new ObjectOutputStream(fich);
			for (Temporada temporada : temporadas) {
				fichBi.writeObject(temporada);
			}
			fichBi.close();
			fich.close();
			
		}catch( IOException e ){
			e.printStackTrace();	
		}	
	}
	
	//Método para cargar el fichero serializado
	public static ArrayList<Temporada> cargaFichero(String fichero){
		ArrayList<Temporada> temporadas = new ArrayList<>();
		
		try {
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Temporada temporada = null;
			while((temporada = (Temporada) ois.readObject()) != null){
				temporadas.add(temporada);	
			}
			ois.close();
			fis.close();
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}catch(ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return temporadas; 
			
	}
	
	//Método para la creación del fichero properties(de prueba)
	public void creacionProperties(String clave, String valor) {
		Properties properties = new Properties();
		properties.setProperty(clave,valor);
		try {
			properties.storeToXML(new FileOutputStream("ficheroPropiedades.xml"), "propiedades del proyecto");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//Método para leer el fichero properties(prueba)
	public void leerProperties() {
		
		
	}

	// Método main de prueba
	public static void main(String[] args) {
		Trayectoria t = new Trayectoria();
		t.simularTemporada();
//		t.crearFichero("src/datos/ficheroBinario.bin");
	}
}
