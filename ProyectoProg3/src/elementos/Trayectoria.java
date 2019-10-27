package elementos;

import java.util.ArrayList;

public class Trayectoria {

	// ATRIBUTOS de la clase Trayectoria
	public ArrayList<Piloto> listaPilotos;
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
}
