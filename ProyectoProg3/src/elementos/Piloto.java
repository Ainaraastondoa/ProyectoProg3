package elementos;

/*Esta clase define las caracteristicas de los pilotos 
 * 
 */
public class Piloto {
	
	//ATRIBUTOS de la clase Piloto
	public String nombre; 		//Nombre del piloto
	public int edad; 			//Edad del piloto
	public int nivel; 			//Nivel del piloto(sobre 10)
	public int regularidad; 	//Regularidad/consistencia (0-10)
	public int adelantar; 		//Capacidad de adelantamiento (0-10)
	public int defender; 		//Defensa (0-10)
	public int velocidad; 		//Rendimiento/velocidad del piloto a 1 vuelta (0-10)
	public int mojado; 			//Nivel del piloto en mojado (0-10)
	public Coche coche;			//Coche con el que compite
	
	//CONSTRUCTOR
	public Piloto(String nombre, int edad, int nivel, int regularidad, int adelantar, int defender, int velocidad, int mojado,
			Coche coche) {
		this.nombre = nombre;
		this.edad = edad;
		this.nivel = nivel;
		this.regularidad = regularidad;
		this.adelantar = adelantar;
		this.defender = defender;
		this.velocidad = velocidad;
		this.mojado = mojado;
		this.coche = coche;
	}
	
	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getRegularidad() {
		return regularidad;
	}
	public void setRegularidad(int regularidad) {
		this.regularidad = regularidad;
	}
	public int getAdelantar() {
		return adelantar;
	}
	public void setAdelantar(int adelantar) {
		this.adelantar = adelantar;
	}
	public int getDefender() {
		return defender;
	}
	public void setDefender(int defender) {
		this.defender = defender;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getMojado() {
		return mojado;
	}
	public void setMojado(int mojado) {
		this.mojado = mojado;
	}
	public Coche getCoche() {
		return coche;
	}
	public void setCoche(Coche coche) {
		this.coche = coche;
	}
}
