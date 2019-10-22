package elementos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * La clase Carrera sirve para desarrollar las acciones de cada carrera, englobando pilotos con el circuito 
 * para hacer los c�lculos de tiempo por vuelta, clasificaci�n final, etc.
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
	 * M�todo para calcular el tiempo por vuelta final de cada piloto
	 * @param piloto Piloto del que queremos sacar su tiempo por vuelta
	 * @param tiempoReferencia Tiempo de referencia cogido de la web de la F1 para cada circuito (en segundos)
	 * @return El tiempo por vuelta definitivo de ese piloto concreto (en segundos / redondeado a mil�simas)
	 */
	public float calcularTiempoPorVuelta(Piloto piloto) {
		float tiempoVueltaInicial = calcularTiempoInicial(piloto);
		float tiempoVueltaTrasDegradacion = tiempoVueltaInicial * piloto.getCoche().multiplicarDegradacion();
		float tiempoVueltaFinal = tiempoVueltaTrasDegradacion * generarNumeroAleatorio();
		BigDecimal tiempoFinalRedondeado = new BigDecimal(tiempoVueltaFinal).setScale(3, RoundingMode.HALF_EVEN);
		return tiempoFinalRedondeado.floatValue();
	}
	
	/**
	 * M�todo para calcular tiempo por vuelta teniendo en cuenta �nicamente los atributos del piloto y los componentes del coche
	 * @param tiempoReferencia Tiempo de referencia cogido de la web de la F1 para cada circuito (en segundos)
	 * @param piloto Piloto del que queremos calcular el tiempo por vuelta
	 * @return Tiempo por vuelta inicial, calculado �nicamente seg�n el rendimiento de Coche + Piloto
	 */
	public float calcularTiempoInicial(Piloto piloto) {
		int puntuacionTotal = 0;
		puntuacionTotal += piloto.calcularPuntosPiloto();
		puntuacionTotal += piloto.getCoche().calcularPuntosCoche();
		float sumaSegundos = 3 - ((float)3 * puntuacionTotal / 10000);
		return (this.getCircuito().getTiempoReferenciaCarrera() + sumaSegundos);
	}
	
	/**
	 * M�todo que calcula un n�mero aleatorio entre 0,9975 y 1,0025 para la parte aleatoria del c�lculo del tiempo por vuelta
	 * @return El n�mero aleatorio por el que hay que multiplicar el tiempo por vuelta tras degradaci�n
	 */
	public float generarNumeroAleatorio() {
		int n = (int)(Math.random() * (10025 - 9975 + 1) + 9975);
		float numeroAleatorio = (float)n / 10000;
		return numeroAleatorio;
	}
	
	/**
	 * M�todo que calcula la degradaci�n por vuelta de los neum�ticos en funci�n del circuito y hace la resta en el porcentaje del
	 * coche de los pilotos
	 * @param piloto Piloto del que queremos calcular la degradaci�n
	 */
	public void calcularDegradacionPorVuelta(Piloto piloto) {
		if (this.getCircuito().getNivelDegradacion() == 1) { // Nivel de degradaci�n Bajo
			piloto.getCoche().setPorcentajeRuedas(piloto.getCoche().getPorcentajeRuedas() - 2);
		} else if (this.getCircuito().getNivelDegradacion() == 2) { // Nivel de degradaci�n Medio
			piloto.getCoche().setPorcentajeRuedas(piloto.getCoche().getPorcentajeRuedas() - 3);
		} else { // Nivel de degradaci�n Alto
			piloto.getCoche().setPorcentajeRuedas(piloto.getCoche().getPorcentajeRuedas() - 4);
		}
	}
	
	/**
	 * M�todo que sirve para simular las paradas en boxes: pone las ruedas a degradaci�n 0 y devuelve el tiempo perdido en el proceso
	 * @param piloto Piloto que entra a boxes
	 * @return Tiempo que pierde al cambiar las ruedas en boxes
	 */
	public float pararEnBoxes(Piloto piloto) {
		piloto.getCoche().setPorcentajeRuedas(100);
		return this.getCircuito().getTiempoExtraBoxes();
	}
	
	/**
	 * M�todo que calcula el tiempo total de la carrera de cada piloto y lo deja en la lista de tiempos sin ordenar
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
	
	/**
	 * M�todo que ordena a los pilotos en funci�n de su tiempo de carrera, ordena la clasificaci�n.
	 * No se puede usar antes de que todos los pilotos completen 1 vuelta por lo menos
	 */
	public void ordenarClasificacionCarrera() {
		for (int i = 0; i < this.getListaTiempos().size() - 1; i++) {
			for (int j = 1; j < this.getListaTiempos().size(); j++) {
				if (listaTiempos.get(j) < listaTiempos.get(j-1)) {
					float aux1 = listaTiempos.get(j); Piloto aux2 = listaPilotos.get(j);
					listaTiempos.set(j, listaTiempos.get(j-1)); listaPilotos.set(j, listaPilotos.get(j-1));
					listaTiempos.set(j-1, aux1); listaPilotos.set(j-1, aux2);
				}
			}
		}
	}
	
	/** Metodo para pasar un tiempo en segundos a un formato m�s visual mm:ss
	 * @param seg Segundos que quieres cambiar de formato
	 * @return tiempo en formato mm:ss
	 */
	public String segundosAMinutos(float seg) {
		int minutos = (int) seg / 60; 	
		int segundos = (int) seg - (minutos * 60);
		
		String minssegs = minutos + ":" + segundos; 
		return minssegs; 
	}
	
	/** Metodo para pasar un tiempo en segundos a un formato m�s visual hh:mm:ss
	 * @param seg Segundos que quieres cambiar de formato
	 * @return tiempo en formato hh:mm:ss
	 */
	public String segundosAHoras(float seg) {
		int horas = (int) seg / 3600;
		int minRest = (int) seg - (horas*60);		
		int minutos = (int) minRest / 60;	
		int segundos = (int) seg - (minutos * 60);
		
		String horMinsSegs = horas + ":" + minutos + ":" + segundos; 
		return horMinsSegs; 
	}
	
	/** Metodo que al finalizar la carrera, o cuando dos pilotos hayan recorrido la misma distancia, 
	 * calcula la diferencia de tiempo que hay entre los dos.
	 * @param tiempoP1 Tiempo del piloto que va delante (menor)
	 * @param tiempoP2 Tiempo del piloto seguidor (mayor)
	 * @return Diferencia de tiempo entre ambos en formato visual mm:ss
	 */
	public String tiempoEntrePilotos(float tiempoP1, float tiempoP2) {
		float tiempo = tiempoP2-tiempoP1;
		String tiempoEntrePilotos = segundosAMinutos(tiempo);
		return tiempoEntrePilotos;		
	}
}
