package elementos;

import java.util.ArrayList;

/*Esta clase define las caracteristicas de los pilotos 
 * 
 */
public class Piloto {
	
	//ATRIBUTOS de la clase Piloto
	public String nombre; 		//Nombre del piloto
	public int edad; 			//Edad del piloto
	public int nivel; 			//Nivel del piloto (0-10)
	public int regularidad; 	//Regularidad/consistencia (0-100)
	public int adelantar; 		//Capacidad de adelantamiento (0-100)
	public int defender; 		//Defensa (0-100)
	public int velocidad; 		//Rendimiento/velocidad del piloto a 1 vuelta (0-100)
	public int mojado; 			//Nivel del piloto en mojado (0-100)
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
	
	/**
	 * Método que calcula la puntuación sobre 3500 puntos de los atributos del piloto para calcular después el rendimiento total
	 * sobre 10000 del Piloto + Coche concreto
	 * @return La puntuación total sobre 3500
	 */
	public int calcularPuntosPiloto() {
		int puntuacion = 0;
		puntuacion += this.getRegularidad() * 9;
		puntuacion += this.getAdelantar() * 7;
		puntuacion += this.getDefender() * 7;
		puntuacion += this.getVelocidad() * 9;
		puntuacion += this.getMojado() * 3;
		return puntuacion;
	}
	
	/** Metodo que crea todos los pilotos del campeonato
	 * @return lista de pilotos participantes
	 */
	public ArrayList<Piloto> crearPiloto() {
		ArrayList<Piloto> listaPilotos = new ArrayList<Piloto>();
		
		listaPilotos.add(new Piloto("hamilton",34,10,75,81,75,85,88,null));
		listaPilotos.add(new Piloto("bottas",30,8,60,47,52,66,40,null));
		listaPilotos.add(new Piloto("vettel",32,9,62,69,66,77,60,null));
		listaPilotos.add(new Piloto("leclerc",22,9,55,79,77,80,68,null));
		listaPilotos.add(new Piloto("verstappen",22,9,50,75,70,77,90,null));
		listaPilotos.add(new Piloto("albon",23,7,60,65,55,66,45,null));
		listaPilotos.add(new Piloto("sainz",25,8,70,50,70,66,25,null));
		listaPilotos.add(new Piloto("norris",19,7,52,68,45,72,20,null));
		listaPilotos.add(new Piloto("ricciardo",30,9,55,79,70,75,64,null));
		listaPilotos.add(new Piloto("hulkenberg",32,8,65,55,70,68,40,null));
		listaPilotos.add(new Piloto("gasly",23,7,60,57,55,58,34,null));
		listaPilotos.add(new Piloto("kvyiat",25,6,42,35,50,55,41,null));
		listaPilotos.add(new Piloto("perez",29,7,75,47,55,48,42,null));
		listaPilotos.add(new Piloto("stroll",20,6,36,50,55,53,39,null));
		listaPilotos.add(new Piloto("magnussen",27,7,30,55,78,63,30,null));
		listaPilotos.add(new Piloto("grosjean",33,5,4,44,44,48,45,null));
		listaPilotos.add(new Piloto("raikkonen",40,7,68,58,64,65,25,null));
		listaPilotos.add(new Piloto("giovinazzi",25,6,35,36,45,58,44,null));
		listaPilotos.add(new Piloto("russell",21,48,6,54,25,65,56,null));
		listaPilotos.add(new Piloto("kubica",34,4,50,33,28,22,34,null));

		return listaPilotos;		
	}
}
