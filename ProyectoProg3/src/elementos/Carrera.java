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
		float tiempoVueltaInicial = calcularTiempoInicial(tiempoReferencia, piloto);
		float tiempoVueltaTrasDegradacion = tiempoVueltaInicial * Coche.calcularDegradacion(piloto.getCoche().getPorcentajeRuedas());
		float tiempoVueltaFinal = tiempoVueltaTrasDegradacion * generarNumeroAleatorio();
		return tiempoVueltaFinal;
	}
	
	// Método para calcular tiempo por vuelta teniendo en cuenta únicamente los atributos del piloto y los componentes del coche
	public float calcularTiempoInicial(float tiempoReferencia, Piloto piloto) {
		return 0; //PENDIENTE
	}
	
	/**
	 * Método que calcula un número aleatorio entre 0,9975 y 1,0025 para la parte aleatoria del cálculo del tiempo por vuelta
	 * @return El número aleatorio por el que hay que multiplicar el tiempo por vuelta tras degradación
	 */
	public float generarNumeroAleatorio() {
		int n = (int)(Math.random() * (10025 - 9975 + 1) + 9975);
		float numeroAleatorio = (float)n / 10000;
		return numeroAleatorio;
	}
}
