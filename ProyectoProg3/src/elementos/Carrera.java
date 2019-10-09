package elementos;

import java.util.ArrayList;

/**
 * La clase Carrera sirve para desarrollar las acciones de cada carrera, englobando pilotos con el circuito 
 * para hacer los cálculos de tiempo por vuelta, clasificación final, etc.
 *
 */
public class Carrera {

	//ATRIBUTOS de la clase Carrera
	public Circuito circuito;				//Circuito en el que se realiza la carrera correspondiente
	public ArrayList<Piloto> listaPilotos; 	//Lista de pilotos que compiten en la carrera
	
	//CONSTRUCTOR
	public Carrera(Circuito circuito, ArrayList<Piloto> listaPilotos) {
		this.circuito = circuito;
		this.listaPilotos = listaPilotos;
	}
	
	//GETTERS y SETTERS
	public Circuito getCircuito() {
		return circuito;
	}

	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}

	public ArrayList<Piloto> getListaPilotos() {
		return listaPilotos;
	}

	public void setListaPilotos(ArrayList<Piloto> listaPilotos) {
		this.listaPilotos = listaPilotos;
	}
	
	// Método para calcular tiempo por vuelta
	public float calcularTiempoPorVuelta(Piloto piloto, float tiempoReferencia) {
		float tiempoVueltaInicial = calcularTiempoInicial(tiempoReferencia);
		float tiempoVueltaTrasDegradacion = tiempoVueltaInicial * Coche.calcularDegradacion(piloto.getCoche().getPorcentajeRuedas());
		float tiempoVueltaFinal = tiempoVueltaTrasDegradacion * generarNumeroAleatorio();
		return tiempoVueltaFinal;
	}
	
	// Método para calcular tiempo por vuelta teniendo en cuenta únicamente los atributos del piloto y los componentes del coche
	public float calcularTiempoInicial(float tiempoReferencia) {
		return 0; //PENDIENTE
	}
	
	// Método para calcular número aleatorio entre 0,9975 y 1,0025 para usarlo en el cálculo del tiempo por vuelta
	public float generarNumeroAleatorio() {
		return 0; //PENDIENTE
	}
}
