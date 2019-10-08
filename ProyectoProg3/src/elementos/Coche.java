package elementos;

/** Esta clase define cada uno de los monoplazas y sus caracteristicas.
 * 
 */
public class Coche {
	
	//ATRIBUTOS de la clase Coche
	public String nombre; 			//Nombre del monoplaza
	public Componente motor;		//Motor que monta el coche
	public Componente chasis;		//Chasis que usa el coche
	public Componente aerodinamica;	//paquete aerodinamico del coche
	
	//CONSTRUCTOR	
	public Coche(String nombre, Componente motor, Componente chasis, Componente aerodinamica) {
		this.nombre = nombre;
		this.motor = motor;
		this.chasis = chasis;
		this.aerodinamica = aerodinamica;
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
}
