package elementos;

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
	 * Método que calcula el número cercano a 1 por el que tenemos que multiplicar el tiempo por vuelta
	 * @param porcentajeRuedas Porcentaje de degradación de las ruedas. 100% si están nuevas
	 * @return El número para multiplicar
	 */
	public static float calcularDegradacion(int porcentajeRuedas) {
		if (porcentajeRuedas == 100) { // Ruedas al 100%
			return 1;
		} else if (porcentajeRuedas >= 80) { // Ruedas entre el 100% y el 80%
			return ((100 - porcentajeRuedas) * (float)0.0002 + 1);
		} else if (porcentajeRuedas >= 60) { // Ruedas entre el 80% y el 60%
			return ((100 - porcentajeRuedas) * (float)0.0003 + 1);
		} else if (porcentajeRuedas >= 30) { // Ruedas entre el 60% y el 30%
			return ((100 - porcentajeRuedas) * (float)0.0004 + 1);
		} else if (porcentajeRuedas >= 15) { // Ruedas entre el 30% y el 15%
			return ((100 - porcentajeRuedas) * (float)0.0005 + 1);
		} else { // Ruedas entre el 15% y el 0%
			return ((100 - porcentajeRuedas) * (float)0.0006 + 1);
		}
	}
}
