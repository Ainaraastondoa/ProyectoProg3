package elementos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

/** Esta clase define las temporadas y sus atributos
 * 
 */
//¿¿¿¿IMPLEMENTS COMPARABLE????
public class Temporada {
	
	//ATRIBUTOS de la clase Temporada
	public int anno;
	public HashMap<Piloto, Integer> puntosPiloto;		//Puntos que tiene cada piloto en la temporada
	public ArrayList<Carrera> listaCarreras;			//Lista de carreras de la temporada
	public HashMap<Escuderia, Integer> puntosEscuderia;	//Puntos que lleva la escuderia
	
	// Properties
	public static Properties propertiesTemporada = new Properties();
	
	// Datos
	private static Escuderia escuderia; 				//Escuderia seleccionada para la temporada
	
	//CONSTRUCTOR
	public Temporada(int anno, ArrayList<Piloto> listaPilotos, ArrayList<Escuderia> listaEscuderias) {
		this.anno = anno;
		this.puntosPiloto = crearMapaPilotosPuntosRecursivo(listaPilotos);
		this.listaCarreras = new ArrayList<Carrera>();
		this.puntosEscuderia = crearMapaEscuderiasPuntosRecursivo(listaEscuderias);
	}
	
	//Creamos hashmap para hacer pruebas de visualizacion en ventana
//	public static HashMap<Piloto, Integer> crearPuntosPiloto(){
//		HashMap<Piloto, Integer> HMpuntosPiloto = new HashMap<Piloto, Integer>();
//		HMpuntosPiloto.put(new Piloto("Hamilton",34,10,75,81,75,85,88,null), 120);
//		HMpuntosPiloto.put(new Piloto("Bottas",30,8,60,47,52,66,40,null), 175);
//		HMpuntosPiloto.put(new Piloto("Vettel",32,9,62,69,66,77,60,null), 75);
//		HMpuntosPiloto.put(new Piloto("Leclerc",22,9,55,79,77,80,68,null), 90);
//		HMpuntosPiloto.put(new Piloto("Verstappen",22,9,50,75,70,77,90,null), 75);
//		HMpuntosPiloto.put(new Piloto("Albon",23,7,60,65,55,66,45,null), 90);
//		HMpuntosPiloto.put(new Piloto("Sainz",25,8,70,50,70,66,25,null), 75);
//		HMpuntosPiloto.put(new Piloto("Norris",19,7,52,68,45,72,20,null), 90);
//		HMpuntosPiloto.put(new Piloto("Ricciardo",30,9,55,79,70,75,64,null), 75);
//		HMpuntosPiloto.put(new Piloto("Hulkenberg",32,8,65,55,70,68,40,null), 90);
//		HMpuntosPiloto.put(new Piloto("Gasly",23,7,60,57,55,58,34,null), 75);
//		HMpuntosPiloto.put(new Piloto("Kvyat",25,6,42,35,50,55,41,null), 90);
//		HMpuntosPiloto.put(new Piloto("P�rez",29,7,75,47,55,48,42,null), 75);
//		HMpuntosPiloto.put(new Piloto("Stroll",20,6,36,50,55,53,39,null), 90);
//		HMpuntosPiloto.put(new Piloto("Magnussen",27,7,30,55,78,63,30,null), 75);
//		HMpuntosPiloto.put(new Piloto("Grosjean",33,5,4,44,44,48,45,null), 90);
//		HMpuntosPiloto.put(new Piloto("Raikkonen",40,7,68,58,64,65,25,null), 75);
//		HMpuntosPiloto.put(new Piloto("Giovinazzi",25,6,35,36,45,58,44,null), 90);
//		HMpuntosPiloto.put(new Piloto("Russell",21,48,6,54,25,65,56,null), 75);
//		HMpuntosPiloto.put(new Piloto("Kubica",34,4,50,33,28,22,34,null), 90);
//		
//		
//		return HMpuntosPiloto; 
//	}
	
//	public static HashMap<Escuderia, Integer> crearPuntosEscuderia(){
//		HashMap<Escuderia, Integer> HMpuntosEscuderia = new HashMap<Escuderia, Integer>();
//		HMpuntosEscuderia.put(new Escuderia("Mercedes", "Hamilton", "Bottas", "Toto Wolff", 120000), 120);
//		HMpuntosEscuderia.put(new Escueria"Bottas",30,8,60,47,52,66,40,null), 175);
//		HMpuntosEscuderia.put(new Escuderia("Vettel",32,9,62,69,66,77,60,null), 75);
//		HMpuntosEscuderia.put(new Escuderia("Leclerc",22,9,55,79,77,80,68,null), 90);
//		
//		return HMpuntosEscuderia; 
//	}
	

	//GETTERS Y SETTERS
	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	public HashMap<Piloto, Integer> getPuntosPiloto() {
		return puntosPiloto;
	}

	public void setPuntosPiloto(HashMap<Piloto, Integer> puntosPiloto) {
		this.puntosPiloto = puntosPiloto;
	}

	public ArrayList<Carrera> getListaCarreras() {
		return listaCarreras;
	}

	public void setListaCarreras(ArrayList<Carrera> listaCarreras) {
		this.listaCarreras = listaCarreras;
	}

	public HashMap<Escuderia, Integer> getPuntosEscuderia() {
		return puntosEscuderia;
	}

	public void setPuntosEscuderia(HashMap<Escuderia, Integer> puntosEscuderia) {
		this.puntosEscuderia = puntosEscuderia;
	}
	
	public static Escuderia getEscuderia() {
		return escuderia;
	}

	public static void setEscuderia(Escuderia escuderia) {
		Temporada.escuderia = escuderia;
	}
	
	/**
	 * Método para guardar en un fichero properties el número de carrera por el que se va en la temporada
	 * @param numCarrera Número que queremos almacenar
	 */
	public static void guardarCarreraActual(int numCarrera) {
		propertiesTemporada.setProperty("numCarreraTemporada", String.valueOf(numCarrera));
		try {
			propertiesTemporada.store(new FileWriter( "archivoProperties" ), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para leer de un fichero properties el número de carrera por el que se va en la temporada
	 * @return Número de carrera por la que se va en la temporada
	 */
	public static int leerCarreraActual() {
		try {
			propertiesTemporada.load( new FileReader( "archivoProperties" ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String ca = propertiesTemporada.getProperty( "numCarreraTemporada" );
		int numCarrera = Integer.valueOf( ca );
		return numCarrera;
	}
	
	/**
	 * Método que sirve para crear el hashmap de pilotos y puntos
	 * @param listaPilotos Lista de pilotos que toman parte en la temporada
	 * @return Puntos por piloto en la temporada (a 0 en el inicio)
	 */
	public HashMap<Piloto, Integer> crearMapaPilotosPuntos(ArrayList<Piloto> listaPilotos) {
		HashMap<Piloto, Integer> mapa = new HashMap<Piloto, Integer>();
		for (Piloto p: listaPilotos) {
			mapa.put(p, 0);
		}
		return mapa;
	}
	
	/**
	 * Método que sirve para crear el hashmap de pilotos y puntos de manera recursiva
	 * @param listaPilotos Lista de pilotos que toman parte en la temporada
	 * @return Puntos por piloto en la temporada (a 0 en el inicio)
	 */
	public /*static*/ HashMap<Piloto, Integer> crearMapaPilotosPuntosRecursivo(ArrayList<Piloto> listaPilotos) {
		HashMap<Piloto, Integer> mapa = crearMapaRecursivoAux(listaPilotos, 0, new HashMap<Piloto, Integer>());
		return mapa;
	}
	
	/**
	 * Método recursivo para el establecimiento del mapa de pilotos y puntos a inicio de temporada
	 * @param listaPilotos Lista de pilotos que toman parte en la temporada
	 * @param indice Indice de la listaPilotos
	 * @param mapa Mapa donde están los puntos por piloto
	 * @return Mismo mapa con los puntos por piloto
	 */
	public /*static*/ HashMap<Piloto, Integer> crearMapaRecursivoAux(ArrayList<Piloto> listaPilotos, int indice,
			HashMap<Piloto, Integer> mapa) {
		if (indice == listaPilotos.size() - 1) {
			mapa.put( listaPilotos.get(indice), 0 );
			return mapa;
		} else {
			mapa.put( listaPilotos.get(indice), 0 );
			mapa = crearMapaRecursivoAux( listaPilotos, indice + 1, mapa );
			return mapa;
		}
	}
	
	/**
	 * Método que sirve para crear el hashmap de escuderías y puntos
	 * @param listaEscuderias Lista de escuderías que toman parte en la temporada
	 * @return Puntos por escudería en la temporada (a 0 en el inicio)
	 */
	public HashMap<Escuderia, Integer> crearMapaEscuderiasPuntos(ArrayList<Escuderia> listaEscuderias) {
		HashMap<Escuderia, Integer> mapa = new HashMap<Escuderia, Integer>();
			for (Escuderia c: listaEscuderias) {
				mapa.put(c, 0);
			}
		return mapa;
	}
	
	/**
	 * Método que sirve para crear el hashmap de escuderías y puntos de manera recursiva
	 * @param listaPilotos Lista de escuderías que toman parte en la temporada
	 * @return Puntos por escudería en la temporada (a 0 en el inicio)
	 */
	public /*static*/ HashMap<Escuderia, Integer> crearMapaEscuderiasPuntosRecursivo(ArrayList<Escuderia> listaEscuderias) {
		HashMap<Escuderia, Integer> mapa = crearMapaRecursivoAux2(listaEscuderias, 0, new HashMap<Escuderia, Integer>());
		return mapa;
	}
	
	/**
	 * Método recursivo para el establecimiento del mapa de escuderías y puntos a inicio de temporada
	 * @param listaEscuderias Lista de escuderías que toman parte en la temporada
	 * @param indice Indice de la listaEscuderias
	 * @param mapa Mapa donde están los puntos por escudería
	 * @return Mismo mapa con los puntos por escudería
	 */
	public /*static*/ HashMap<Escuderia, Integer> crearMapaRecursivoAux2(ArrayList<Escuderia> listaEscuderias, int indice,
			HashMap<Escuderia, Integer> mapa) {
		if (indice == listaEscuderias.size() - 1) {
			mapa.put( listaEscuderias.get(indice), 0 );
			return mapa;
		} else {
			mapa.put( listaEscuderias.get(indice), 0 );
			mapa = crearMapaRecursivoAux2( listaEscuderias, indice + 1, mapa );
			return mapa;
		}
	}
	
	/**
	 * Método que sirve para crear las carreras de una temporada
	 * @param listaCircuitos Lista de los circuitos en los que se compite en esa temporada
	 */
	public void crearCarrerasTemporada(ArrayList<Circuito> listaCircuitos) {
		ArrayList<Piloto> listaPilotos = new ArrayList<Piloto>();
		for (Piloto p : puntosPiloto.keySet()) {
			listaPilotos.add(p);
		}
		for (Circuito c : listaCircuitos) {
			Carrera ca = new Carrera(c, listaPilotos);
			listaCarreras.add(ca);
		}
	}
	
	/**
	 * Método que sirve para simular una carrera y actualizar los resultados según el número de la carrera que se le pase
	 * @param numCarrera Número de la carrera en el orden adecuado, 1 para la primera carrera de la temporada
	 */
	public void simularCarreraTemporada( int numCarrera ) {
		Carrera ca = listaCarreras.get(numCarrera - 1);
		ca.simularCarrera();
		ca.ordenarClasificacionCarrera();
		ca.repartirPuntos( this.getPuntosPiloto() );
		ca.actualizarPuntosEscuderia( this.getPuntosEscuderia(), this.getPuntosPiloto() );
		ca.repartirDinero( this.getPuntosEscuderia() );
	}
	
	// Método main de prueba
//	public static void main(String[] args) {
//		Componente c1 = new Componente("a", 80);
//		Coche coche = new Coche("SF19", c1, c1, c1, 97, "patata2");
//		Piloto p1 = new Piloto("Alex", 18, 50, 32, 45, 73, 27, 24, coche, "patata");
//		Piloto p2 = new Piloto("Carlos", 18, 50, 32, 45, 73, 27, 24, coche, "patata");
//		Piloto p3 = new Piloto("Javier", 18, 50, 32, 45, 73, 27, 24, coche, "patata");
//		Piloto p4 = new Piloto("Jaime", 18, 50, 32, 45, 73, 27, 24, coche, "patata");
//		Piloto p5 = new Piloto("Santi", 18, 50, 32, 45, 73, 27, 24, coche, "patata");
//		ArrayList<Piloto> listaPilotos = new ArrayList<Piloto>(Arrays.asList(p1,p2,p3,p4,p5));
//		HashMap<Piloto, Integer> mapaPilotos = crearMapaPilotosPuntosRecursivo(listaPilotos);
//		mapaPilotos.forEach( (k,v) -> {
//			System.out.println("Piloto: " + k.getNombre() + "   Puntos: " + v);
//		});
//		
//		Escuderia e1 = new Escuderia("Ferrari", "Binotto", p1, p2, 200000, "patata", "tomate");
//		Escuderia e2 = new Escuderia("Mercedes", "Binotto", p1, p2, 200000, "patata", "tomate");
//		Escuderia e3 = new Escuderia("Red Bull", "Binotto", p1, p2, 200000, "patata", "tomate");
//		Escuderia e4 = new Escuderia("Renault", "Binotto", p1, p2, 200000, "patata", "tomate");
//		
//		ArrayList<Escuderia> listaEscuderias = new ArrayList<Escuderia>(Arrays.asList(e1,e2,e3,e4));
//		HashMap<Escuderia, Integer> mapaEscuderias = crearMapaEscuderiasPuntosRecursivo(listaEscuderias);
//		mapaEscuderias.forEach( (k,v) -> {
//			System.out.println("Escudería: " + k.getNombre() + "   Puntos: " + v);
//		});
//	}
}
