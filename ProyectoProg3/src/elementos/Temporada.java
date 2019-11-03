package elementos;

import java.util.ArrayList;
import java.util.HashMap;

/** Esta clase define las temporadas y sus atributos
 *
 */
public class Temporada {
	
	//ATRIBUTOS de la clase Temporada
	public int año;
	public HashMap<Piloto, Integer> puntosPiloto;		//Puntos que tiene cada piloto en la temporada
	public ArrayList<Carrera> listaCarreras;			//Lista de carreras de la temporada
	public HashMap<Escuderia, Integer> puntosEscuderia;	//Puntos que lleva la escuderia
	
	//CONSTRUCTOR
	public Temporada(int año, ArrayList<Carrera> listaCarreras, ArrayList<Piloto> listaPilotos, ArrayList<Escuderia> listaEscuderias) {
		this.año = año;
		this.puntosPiloto = crearMapaPilotosPuntos(listaPilotos);
		this.listaCarreras = listaCarreras;
		this.puntosEscuderia = crearMapaEscuderiasPuntos(listaEscuderias);
	}
	
	//Creamos hashmap para hacer pruebas de visualizacion en ventana
	public static HashMap<Piloto, Integer> crearPuntosPiloto(){
		HashMap<Piloto, Integer> HMpuntosPiloto = new HashMap<Piloto, Integer>();
		HMpuntosPiloto.put(new Piloto("Hamilton",34,10,75,81,75,85,88,null), 120);
		HMpuntosPiloto.put(new Piloto("Bottas",30,8,60,47,52,66,40,null), 175);
		HMpuntosPiloto.put(new Piloto("Vettel",32,9,62,69,66,77,60,null), 75);
		HMpuntosPiloto.put(new Piloto("Leclerc",22,9,55,79,77,80,68,null), 90);
		
		return HMpuntosPiloto; 
	}
	
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
	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
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
}
