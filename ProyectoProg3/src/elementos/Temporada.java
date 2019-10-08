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
	public ArrayList<String> circuitos;					//Lista de circuitos
	public HashMap<Piloto, Integer> puntosPiloto;		//Puntos que lleva el piloto
	public HashMap<Escuderia, Integer> puntosEscuderia;	//Puntos que lleva la escuderia
	
	//CONSTRUCTOR
	public Temporada(ArrayList<Piloto> pilotos, ArrayList<Integer> puntos, ArrayList<String> circuitos,
			HashMap<Piloto, Integer> puntosPiloto, HashMap<Escuderia, Integer> puntosEscuderia) {
		super();
		this.pilotos = pilotos;
		this.puntos = puntos;
		this.circuitos = circuitos;
		this.puntosPiloto = puntosPiloto;
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

	public ArrayList<String> getCircuitos() {
		return circuitos;
	}

	public void setCircuitos(ArrayList<String> circuitos) {
		this.circuitos = circuitos;
	}

	public HashMap<Piloto, Integer> getPuntosPiloto() {
		return puntosPiloto;
	}

	public void setPuntosPiloto(HashMap<Piloto, Integer> puntosPiloto) {
		this.puntosPiloto = puntosPiloto;
	}

	public HashMap<Escuderia, Integer> getPuntosEscuderia() {
		return puntosEscuderia;
	}

	public void setPuntosEscuderia(HashMap<Escuderia, Integer> puntosEscuderia) {
		this.puntosEscuderia = puntosEscuderia;
	}	
}
