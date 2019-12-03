package elementos;

import java.util.ArrayList;

/** Esta clase define cada uno de los monoplazas y sus caracteristicas.
 * 
 */ 
public class Coche {

	//ATRIBUTOS de la clase Coche
	public String nombre; 			//Nombre del monoplaza
	public Componente motor;		//Motor que monta el coche
	public Componente chasis;		//Chasis que usa el coche
	public Componente aerodinamica;	//Paquete aerodinamico del coche
	public int porcentajeRuedas;	//Porcentaje restante de duración de las ruedas en carrera
	
	//CONSTRUCTOR	
	public Coche(String nombre, Componente motor, Componente chasis, Componente aerodinamica, int porcentajeRuedas) {
		this.nombre = nombre;
		this.motor = motor;
		this.chasis = chasis;
		this.aerodinamica = aerodinamica;
		this.porcentajeRuedas = porcentajeRuedas;
	}
	public Coche (Coche coche) {
		this.nombre = coche.nombre;
		this.motor = coche.motor;
		this.chasis = coche.chasis;
		this.aerodinamica = coche.aerodinamica;
		this.porcentajeRuedas = coche.porcentajeRuedas;
	}
	
	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Componente getMotor() {
		return motor;
	}

	public void setMotor(Componente motor) {
		this.motor = motor;
	}

	public Componente getChasis() {
		return chasis;
	}

	public void setChasis(Componente chasis) {
		this.chasis = chasis;
	}

	public Componente getAerodinamica() {
		return aerodinamica;
	}

	public void setAerodinamica(Componente aerodinamica) {
		this.aerodinamica = aerodinamica;
	}
	
	public int getPorcentajeRuedas() {
		return porcentajeRuedas;
	}
	
	public void setPorcentajeRuedas(int porcentajeRuedas) {
		this.porcentajeRuedas = porcentajeRuedas;
	}
	
	/**
	 * Método que calcula el número cercano a 1 por el que tenemos que multiplicar el tiempo por vuelta según la degradación de
	 * las ruedas.
	 * @return El número por el que multiplicar el tiempo por vuelta inicial
	 */
	public float multiplicarDegradacion() {
		if (this.porcentajeRuedas == 100) { // Ruedas al 100%
			return 1;
		} else if (this.porcentajeRuedas >= 80) { // Ruedas entre el 100% y el 80%
			return ((100 - this.porcentajeRuedas) * (float)0.0002 + 1);
		} else if (this.porcentajeRuedas >= 60) { // Ruedas entre el 80% y el 60%
			return ((100 - this.porcentajeRuedas) * (float)0.0003 + 1);
		} else if (this.porcentajeRuedas >= 30) { // Ruedas entre el 60% y el 30%
			return ((100 - this.porcentajeRuedas) * (float)0.0004 + 1);
		} else if (this.porcentajeRuedas >= 15) { // Ruedas entre el 30% y el 15%
			return ((100 - this.porcentajeRuedas) * (float)0.0005 + 1);
		} else { // Ruedas entre el 15% y el 0%
			return ((100 - this.porcentajeRuedas) * (float)0.0006 + 1);
		}
	}
	
	/**
	 * Método que calcula la puntuación sobre 7500 puntos de los componentes del coche para calcular después el rendimiento total
	 * sobre 10000 del Piloto + Coche concreto
	 * @return La puntuación total sobre 7500
	 */
	public int calcularPuntosCoche() {
		int puntuacion = 0;
		puntuacion += this.getMotor().getRendimiento() * 23;
		puntuacion += this.getAerodinamica().getRendimiento() * 22;
		puntuacion += this.getChasis().getRendimiento() * 20;
		return puntuacion;
	}
	
	/** Metodo que crea todos los coches del campeonato con sus correspondientes componentes predeterminados. Ejecutar obligatoriamente
	 * después del método crearComponentesPredeterminados de la clase Componente
	 * @param Lista de componentes predeterminados ordenados con sus correspondientes mejoras
	 * @return Lista de coches participantes en el campeonato
	 */
	public static ArrayList<Coche> crearCochesPredeterminados(ArrayList<Componente> listaComponentes) {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		listaCoches.add(new Coche("Mercedes",listaComponentes.get(0),listaComponentes.get(1),listaComponentes.get(2),100));
		listaCoches.add(new Coche("Ferrari",listaComponentes.get(3),listaComponentes.get(4),listaComponentes.get(5),100));
		listaCoches.add(new Coche("Redbull",listaComponentes.get(6),listaComponentes.get(7),listaComponentes.get(8),100));
		listaCoches.add(new Coche("Mclaren",listaComponentes.get(9),listaComponentes.get(10),listaComponentes.get(11),100));
		listaCoches.add(new Coche("Renault",listaComponentes.get(12),listaComponentes.get(13),listaComponentes.get(14),100));
		listaCoches.add(new Coche("Tororosso",listaComponentes.get(15),listaComponentes.get(16),listaComponentes.get(17),100));
		listaCoches.add(new Coche("Racingpoint",listaComponentes.get(18),listaComponentes.get(19),listaComponentes.get(20),100));
		listaCoches.add(new Coche("Haas",listaComponentes.get(21),listaComponentes.get(22),listaComponentes.get(23),100));
		listaCoches.add(new Coche("Alfaromeo",listaComponentes.get(24),listaComponentes.get(25),listaComponentes.get(26),100));
		listaCoches.add(new Coche("Williams",listaComponentes.get(27),listaComponentes.get(28),listaComponentes.get(29),100));
		
		return listaCoches;		
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
	//Ventana Coche
	public String toString2() {
		return "Motor=" + motor.getRendimiento() + "\nChasis=" + chasis.getRendimiento() + "\nAerodinamica=" + aerodinamica.getRendimiento();
	}
}
