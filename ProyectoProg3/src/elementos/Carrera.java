package elementos;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	public ArrayList<Float> listaTiempos;	//Lista de tiempos de cada piloto a lo largo de la carrera
	
	//CONSTRUCTOR
	public Carrera(Circuito circuito, ArrayList<Piloto> listaPilotos) {
		this.circuito = circuito;
		this.listaPilotos = listaPilotos;
		this.listaTiempos = new ArrayList<Float>();
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
	
	public ArrayList<Float> getListaTiempos() {
		return listaTiempos;
	}

	public void setListaTiempos(ArrayList<Float> listaTiempos) {
		this.listaTiempos = listaTiempos;
	}

	/**
	 * Método para calcular el tiempo por vuelta final de cada piloto
	 * @param piloto Piloto del que queremos sacar su tiempo por vuelta
	 * @param tiempoReferencia Tiempo de referencia cogido de la web de la F1 para cada circuito (en segundos)
	 * @return El tiempo por vuelta definitivo de ese piloto concreto (en segundos / redondeado a milésimas)
	 */
	public float calcularTiempoPorVuelta(Piloto piloto) {
		float tiempoVueltaInicial = calcularTiempoInicial(piloto);
		float tiempoVueltaTrasDegradacion = tiempoVueltaInicial * piloto.getCoche().multiplicarDegradacion();
		float tiempoVueltaFinal = tiempoVueltaTrasDegradacion * generarNumeroAleatorio();
		BigDecimal tiempoFinalRedondeado = new BigDecimal(tiempoVueltaFinal).setScale(3, RoundingMode.HALF_EVEN);
		return tiempoFinalRedondeado.floatValue();
	}
	
	/**
	 * Método para calcular tiempo por vuelta teniendo en cuenta únicamente los atributos del piloto y los componentes del coche
	 * @param tiempoReferencia Tiempo de referencia cogido de la web de la F1 para cada circuito (en segundos)
	 * @param piloto Piloto del que queremos calcular el tiempo por vuelta
	 * @return Tiempo por vuelta inicial, calculado únicamente según el rendimiento de Coche + Piloto
	 */
	public float calcularTiempoInicial(Piloto piloto) {
		int puntuacionTotal = 0;
		puntuacionTotal += piloto.calcularPuntosPiloto();
		puntuacionTotal += piloto.getCoche().calcularPuntosCoche();
		float sumaSegundos = 3 - ((float)3 * puntuacionTotal / 10000);
		return (this.getCircuito().getTiempoReferenciaCarrera() + sumaSegundos);
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
	
	/**
	 * Método que calcula la degradación por vuelta de los neumáticos en función del circuito y hace la resta en el porcentaje del
	 * coche de los pilotos
	 * @param piloto Piloto del que queremos calcular la degradación
	 */
	public void calcularDegradacionPorVuelta(Piloto piloto) {
		if (this.getCircuito().getNivelDegradacion() == 1) { // Nivel de degradación Bajo
			piloto.getCoche().setPorcentajeRuedas(piloto.getCoche().getPorcentajeRuedas() - 2);
		} else if (this.getCircuito().getNivelDegradacion() == 2) { // Nivel de degradación Medio
			piloto.getCoche().setPorcentajeRuedas(piloto.getCoche().getPorcentajeRuedas() - 3);
		} else { // Nivel de degradación Alto
			piloto.getCoche().setPorcentajeRuedas(piloto.getCoche().getPorcentajeRuedas() - 4);
		}
	}
	
	/**
	 * Método que sirve para simular las paradas en boxes: pone las ruedas a degradación 0 y devuelve el tiempo perdido en el proceso
	 * @param piloto Piloto que entra a boxes
	 * @return Tiempo que pierde al cambiar las ruedas en boxes
	 */
	public float pararEnBoxes(Piloto piloto) {
		piloto.getCoche().setPorcentajeRuedas(100);
		return this.getCircuito().getTiempoExtraBoxes();
	}
	
	/**
	 * Método que calcula el tiempo total de la carrera de cada piloto y lo deja en la lista de tiempos sin ordenar
	 */
	public void simularCarrera() {
		for ( int i = 0; i < this.getCircuito().getVueltas(); i++ ) { // Se repite tantas veces como vueltas tenga el circuito
			for ( int j = 0; j < this.getListaPilotos().size(); j++ ) { // Se repite tantas veces como pilotos compitan en la carrera
				float tiempoVueltaPiloto = calcularTiempoPorVuelta( this.getListaPilotos().get( j ) );
				if ( this.getListaPilotos().get(j).getCoche().getPorcentajeRuedas() < 60 ) {
					tiempoVueltaPiloto += pararEnBoxes(this.getListaPilotos().get(j));
				}
				calcularDegradacionPorVuelta(this.getListaPilotos().get(j));
				if (listaTiempos.size() < 20) { // Primera vuelta
					listaTiempos.add(tiempoVueltaPiloto);
				} else { // Resto de vueltas
					float tiempoTotalPiloto = listaTiempos.get(j);
					tiempoTotalPiloto += tiempoVueltaPiloto;
					listaTiempos.set(j, tiempoTotalPiloto);
				}
			}
		}
	}
}
