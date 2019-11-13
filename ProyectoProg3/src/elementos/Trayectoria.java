package elementos;

import java.util.ArrayList;

public class Trayectoria {

	// ATRIBUTOS de la clase Trayectoria - DATOS
	private ArrayList<Piloto> listaPilotos;
	private ArrayList<Escuderia> listaEscuderias;
	private ArrayList<Temporada> listaTemporadas;
	private static Piloto piloto; //Piloto seleccionado para la trayectoria.
	

	// CONSTRUCTOR de la clase Trayectoria
	public Trayectoria() {
		ArrayList<ArrayList<Mejora>> listaMejoras = Mejora.crearMejorasPredeterminadas();
		ArrayList<Componente> listaComponentes = Componente.crearComponentesPredeterminados(listaMejoras);
		ArrayList<Coche> listaCoches = Coche.crearCochesPredeterminados(listaComponentes);
		this.listaPilotos = Piloto.crearPilotosPredeterminados(listaCoches);
		this.listaEscuderias = Escuderia.crearEscuderiasPredeterminadas(this.listaPilotos);
		this.listaTemporadas = new ArrayList<Temporada>();
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

	public static Piloto getPiloto() {
		return piloto;
	}

	public static void setPiloto(Piloto piloto) {
		Trayectoria.piloto = piloto;
	}

	// Método de simulación de una trayectoria
	public void simularTrayectoria() {
		
		// Creación de Trayectoria
		ArrayList<Circuito> listaCircuitos = Circuito.crearCircuitosPredeterminados();
		
		// Añadir temporada
		ArrayList<Carrera> listaCarreras = Carrera.crearCarreras(listaCircuitos, this.listaPilotos);
		Temporada t = new Temporada(2019, listaCarreras, listaPilotos, listaEscuderias);
		this.getListaTemporadas().add(t);
		
		// Simular Carrera (carrera 1)
		t.getListaCarreras().get(0).simularCarrera();
		t.getListaCarreras().get(0).ordenarClasificacionCarrera();
		t.getListaCarreras().get(0).repartirPuntos(t.getPuntosPiloto());
		t.getListaCarreras().get(0).actualizarPuntosEscuderia(t.getPuntosEscuderia(), t.getPuntosPiloto());
		t.getListaCarreras().get(0).repartirDinero(t.getPuntosEscuderia());
		
		// Comprobación (carrera 1)
		System.out.println("Resultado Carrera:");
		System.out.println(t.getListaCarreras().get(0).getListaPilotos());
		System.out.println(t.getListaCarreras().get(0).getListaTiempos());
	}
	
	// Método main de prueba
	public static void main(String[] args) {
		Trayectoria t = new Trayectoria();
		t.simularTrayectoria();
	}
}
