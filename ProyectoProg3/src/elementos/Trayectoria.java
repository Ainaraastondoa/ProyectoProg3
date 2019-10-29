package elementos;

import java.util.ArrayList;

public class Trayectoria {

	// ATRIBUTOS de la clase Trayectoria - DATOS
	
	private static ArrayList<ArrayList<Mejora>> listaMejoras = Mejora.crearMejorasPredeterminadas();
	private static ArrayList<Componente> listaComponentes;
	private static ArrayList<Coche> listaCoches;
	private static ArrayList<Piloto> listaPilotos;
	private static ArrayList<Escuderia> listaEscuderias;
	public ArrayList<Temporada> listaTemporadas;
	

	// CONSTRUCTOR de la clase Trayectoria
	public Trayectoria(ArrayList<Piloto> listaPilotos, ArrayList<Temporada> listaTemporadas) {
		this.listaPilotos = listaPilotos;
		this.listaTemporadas = listaTemporadas;
	}
	
	// GETTERS Y SETTERS
	public ArrayList<Piloto> getListaPilotos() {
		return listaPilotos;
	}

	public void setListaPilotos(ArrayList<Piloto> listaPilotos) {
		this.listaPilotos = listaPilotos;
	}

	public ArrayList<Temporada> getListaTemporadas() {
		return listaTemporadas;
	}

	public void setListaTemporadas(ArrayList<Temporada> listaTemporadas) {
		this.listaTemporadas = listaTemporadas;
	}

	public static void initalizeData() {
		listaComponentes = Componente.crearComponentesPredeterminados(listaMejoras);
		listaCoches = Coche.crearCochesPredeterminados(listaComponentes);
		listaPilotos = Piloto.crearPilotosPredeterminados(listaCoches);
		listaEscuderias = Escuderia.crearEscuderiasPredeterminadas(listaPilotos);
	}
	
	/** Devuleve la escuderia seleccionada
	 * @return escuderia que el jugador ha elegido para la trayectoria
	 */
	public static Escuderia getEscuderia() {	
		initalizeData();
		return listaEscuderias.get(1);
	}
}
