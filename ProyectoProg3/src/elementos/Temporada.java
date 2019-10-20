package elementos;

import java.util.ArrayList;
import java.util.HashMap;

/** Esta clase define las temporadas y sus atributos
 *
 */
public class Temporada {
	
	//ATRIBUTOS de la clase Temporada
	public ArrayList<Piloto> pilotos;					//Lista de pilotos
	public ArrayList<Integer> puntos;					//Lista de puntos por piloto
	public ArrayList<Circuito> circuitos;				//Lista de circuitos
	public HashMap<Escuderia, Integer> puntosEscuderia;	//Puntos que lleva la escuderia
	
	//CONSTRUCTOR
	public Temporada(ArrayList<Piloto> pilotos, ArrayList<Circuito> circuitos, HashMap<Escuderia, Integer> puntosEscuderia) {
		this.pilotos = pilotos;
		this.puntos = new ArrayList<Integer>();
		this.circuitos = circuitos;
		this.puntosEscuderia = puntosEscuderia;
	}

	//GETTERS Y SETTERS
	public ArrayList<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(ArrayList<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public ArrayList<Integer> getPuntos() {
		return puntos;
	}

	public void setPuntos(ArrayList<Integer> puntos) {
		this.puntos = puntos;
	}

	public ArrayList<Circuito> getCircuitos() {
		return circuitos;
	}

	public void setCircuitos(ArrayList<Circuito> circuitos) {
		this.circuitos = circuitos;
	}

	public HashMap<Escuderia, Integer> getPuntosEscuderia() {
		return puntosEscuderia;
	}

	public void setPuntosEscuderia(HashMap<Escuderia, Integer> puntosEscuderia) {
		this.puntosEscuderia = puntosEscuderia;
	}
	
	/**
	 * Método que ordena a los pilotos en función de los puntos en la clasificación general.
	 */
	public void ordenarClasificacionTemporada() {
		for (int i = 0; i < this.getPuntos().size() - 1; i++) {
			for (int j = 1; j < this.getPuntos().size(); j++) {
				if (puntos.get(j) < puntos.get(j-1)) {
					int aux1 = puntos.get(j); Piloto aux2 = pilotos.get(j);
					puntos.set(j, puntos.get(j-1)); pilotos.set(j, pilotos.get(j-1));
					puntos.set(j-1, aux1); pilotos.set(j-1, aux2);
				}
			}
		}
	}
}
