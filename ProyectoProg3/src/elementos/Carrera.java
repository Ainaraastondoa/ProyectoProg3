package elementos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

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
		/*PR*/System.out.println(piloto + " -> Tiempo Inicial: " + tiempoVueltaInicial);
		float tiempoVueltaTrasDegradacion = tiempoVueltaInicial * piloto.getCoche().multiplicarDegradacion();
		/*PR*/System.out.println(piloto + " -> Tiempo tras Degradación: " + tiempoVueltaTrasDegradacion);
		float tiempoVueltaFinal = tiempoVueltaTrasDegradacion * generarNumeroAleatorio();
		/*PR*/System.out.println(piloto + " -> Tiempo tras Aleatorio: " + tiempoVueltaFinal);
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
			/*P*///System.out.println("Vuelta " + (i + 1));
			for ( int j = 0; j < this.getListaPilotos().size(); j++ ) { // Se repite tantas veces como pilotos compitan en la carrera
				float tiempoVueltaPiloto = calcularTiempoPorVuelta( this.getListaPilotos().get( j ) );
				if ( this.getListaPilotos().get(j).getCoche().getPorcentajeRuedas() < 60 ) {
					tiempoVueltaPiloto += pararEnBoxes(this.getListaPilotos().get(j));
				}
				calcularDegradacionPorVuelta(this.getListaPilotos().get(j));
				if (listaTiempos.size() < 20) { // Primera vuelta
					listaTiempos.add(tiempoVueltaPiloto);
					/*P*///System.out.println("Piloto: " + this.getListaPilotos().get(j) + "  ->  " + this.getListaTiempos().get(j));
				} else { // Resto de vueltas
					float tiempoTotalPiloto = listaTiempos.get(j);
					tiempoTotalPiloto += tiempoVueltaPiloto;
					listaTiempos.set(j, tiempoTotalPiloto);
					/*P*///System.out.println("Piloto: " + this.getListaPilotos().get(j) + "  ->  " + this.getListaTiempos().get(j));
				}
			}
		}
	}
	
	/**
	 * Método que ordena a los pilotos en función de su tiempo de carrera, ordena la clasificación.
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
	
	/**
	 * Método que sirve para otorgar puntos en función de la posición en carrera.
	 * Obligatoriamente debe ser ejecutado después de que los pilotos hayan sido ordenados en función de su tiempo total
	 * @param puntosPiloto Mapa de la clase Tempoarada que relaciona a los pilotos con sus puntos esa temporada
	 */
	public void repartirPuntos(HashMap<Piloto, Integer> puntosPiloto) {
		for (int i = 0; i < listaPilotos.size() - 10; i++) {
			if (i == 0) { // 1º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 25);
			} else if (i == 1) { // 2º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 18);
			} else if (i == 2) { // 3º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 15);
			} else if (i == 3) { // 4º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 12);
			} else if (i == 4) { // 5º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 10);
			} else if (i == 5) { // 6º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 8);
			} else if (i == 6) { // 7º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 6);
			} else if (i == 7) { // 8º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 4);
			} else if (i == 8) { // 9º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 2);
			} else if (i == 9) { // 10º Puesto
				puntosPiloto.put(listaPilotos.get(i), puntosPiloto.get(listaPilotos.get(i)) + 1);
			}
		}
	}
	
	/**
	 * Método que sirve para actualizar los puntos de una escudería después de una carrera
	 * @param puntosEscuderia Mapa de la clase Tempoarada que relaciona a las escuderías con sus puntos esa temporada
	 * @param puntosPiloto Mapa de la clase Tempoarada que relaciona a los pilotos con sus puntos esa temporada
	 */
	public void actualizarPuntosEscuderia(HashMap<Escuderia, Integer> puntosEscuderia, HashMap<Piloto, Integer> puntosPiloto) {
		puntosEscuderia.forEach( (k,v) -> {
			int pts1 = puntosPiloto.get(k.getPiloto1()); int pts2 = puntosPiloto.get(k.getPiloto2());
			puntosEscuderia.put(k, pts1 + pts2);
		});
	}
	
	/**
	 * Método que sirve para repartir el dinero a la escudería en función de la posición de sus pilotos. 
	 * Obligatoriamente debe ser ejecutado después de que los pilotos hayan sido ordenados en función de su tiempo total
	 * @param puntosEscuderia Mapa de la clase Tempoarada que relaciona a las escuderías con sus puntos esa temporada
	 */
	public void repartirDinero(HashMap<Escuderia, Integer> puntosEscuderia) {
		puntosEscuderia.forEach( (k,v) -> {
			for (int i = 0; i < listaPilotos.size(); i++) { // Piloto 1
				if (k.getPiloto1().equals(listaPilotos.get(i))) {
					if (i == 0) { // Puesto 1
						k.setPresupuesto(k.getPresupuesto() + 1000000);
					} else if (i < 3) { // Puestos 2-3
						k.setPresupuesto(k.getPresupuesto() + 750000);
					} else if (i < 10) { // Puestos 4-10
						k.setPresupuesto(k.getPresupuesto() + 500000);
					} else { //Puesto 11-20
						k.setPresupuesto(k.getPresupuesto() + 250000);
					}}}
			for (int j = 0; j < listaPilotos.size(); j++) { // Piloto 2
				if (k.getPiloto2().equals(listaPilotos.get(j))) {
					if (j == 0) { // Puesto 1
						k.setPresupuesto(k.getPresupuesto() + 1000000);
					} else if (j < 3) { // Puestos 2-3
						k.setPresupuesto(k.getPresupuesto() + 750000);
					} else if (j < 10) { // Puestos 4-10
						k.setPresupuesto(k.getPresupuesto() + 500000);
					} else { //Puesto 11-20
						k.setPresupuesto(k.getPresupuesto() + 250000);
					}}}
			});
	}
	
	/** Metodo para pasar un tiempo en segundos a un formato más visual mm:ss
	 * @param seg Segundos que quieres cambiar de formato
	 * @return tiempo en formato mm:ss
	 */
	public String segundosAMinutos(float seg) {
		int minutos = (int) seg / 60; 	
		int segundos = (int) seg - (minutos * 60);
		
		String minssegs = minutos + ":" + segundos; 
		return minssegs; 
	}
	
	/** Metodo para pasar un tiempo en segundos a un formato más visual hh:mm:ss
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
	
	/** Método que al finalizar la carrera, o cuando dos pilotos hayan recorrido la misma distancia, 
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
	
	/**
	 * Método que sirve para crear la lista de carreras de una temporada
	 * @param listaCircuitos Lista de circuitos de F1
	 * @param listaPilotos Lista de los pilotos que participan en la temporada
	 * @return Lista de carreras en una temporada
	 */
	public static ArrayList<Carrera> crearCarreras(ArrayList<Circuito> listaCircuitos, ArrayList<Piloto> listaPilotos) {
		ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>();
		for (Circuito c: listaCircuitos) {
			listaCarreras.add(new Carrera(c, listaPilotos));
		}
		return listaCarreras;
	}
}
