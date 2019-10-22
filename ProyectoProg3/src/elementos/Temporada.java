package elementos;

import java.util.ArrayList;
import java.util.HashMap;

/** Esta clase define las temporadas y sus atributos
 *
 */
public class Temporada {
	
	//ATRIBUTOS de la clase Temporada
	public HashMap<Piloto, Integer> puntosPiloto;		//Puntos que tiene cada piloto en la temporada
	public ArrayList<Circuito> circuitos;				//Lista de circuitos
	public HashMap<Escuderia, Integer> puntosEscuderia;	//Puntos que lleva la escuderia
	public ArrayList<Escuderia> listaEscuderias;		//Lista de escuderias que participan en la temporada
	
	//CONSTRUCTOR
	public Temporada(HashMap<Piloto, Integer> puntosPiloto, ArrayList<Circuito> circuitos, HashMap<Escuderia, Integer> puntosEscuderia,
			ArrayList<Escuderia> listaEscuderias) {
		this.puntosPiloto = puntosPiloto;
		this.circuitos = circuitos;
		this.puntosEscuderia = puntosEscuderia;
		this.listaEscuderias = listaEscuderias;
	}

	//GETTERS Y SETTERS
	public HashMap<Piloto, Integer> getPuntosPiloto() {
		return puntosPiloto;
	}

	public void setPuntosPiloto(HashMap<Piloto, Integer> puntosPiloto) {
		this.puntosPiloto = puntosPiloto;
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

	public ArrayList<Escuderia> getListaEscuderias() {
		return listaEscuderias;
	}

	public void setListaEscuderias(ArrayList<Escuderia> listaEscuderias) {
		this.listaEscuderias = listaEscuderias;
	}
	
}
