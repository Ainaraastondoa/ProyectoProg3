package elementos;

import java.util.ArrayList;

/** Esta clase define la clase escuderia y sus atributos
 *
 */
public class Escuderia {
	
	//ATRIBUTOS de la clase Escuderia
	public String nombre;		//Nombre de la escuderia
	public String director;		//Director tecnico del equipo
	public Piloto piloto1;		//Piloto 1 del equipo
	public Piloto piloto2;		//Piloto 2 del equipo
	public int presupuesto;		//Presupuesto de la escuderia
	
	//CONSTRUCTOR
	public Escuderia(String nombre, String director, Piloto piloto1, Piloto piloto2, int presupuesto) {
		this.nombre = nombre;
		this.director = director;
		this.piloto1 = piloto1;
		this.piloto2 = piloto2;
		this.presupuesto = presupuesto;
	}
	
	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Piloto getPiloto1() {
		return piloto1;
	}
	public void setPiloto1(Piloto piloto1) {
		this.piloto1 = piloto1;
	}
	public Piloto getPiloto2() {
		return piloto2;
	}
	public void setPiloto2(Piloto piloto2) {
		this.piloto2 = piloto2;
	}
	public int getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}
	
	// Método qur sirve para crear las escuderías predeterminadas. Ejecutar después de crear a los pilotos
	public static ArrayList<Escuderia> crearEscuderiasPredeterminadas(ArrayList<Piloto> listaPilotos) {
		ArrayList<Escuderia> listaEscuderias = new ArrayList<Escuderia>();
		listaEscuderias.add(new Escuderia("Mercedes", "Toto Wolf", listaPilotos.get(0), listaPilotos.get(1), 25000000));
		listaEscuderias.add(new Escuderia("Ferrari", "Mattia Binoto", listaPilotos.get(2), listaPilotos.get(3), 25000000));
		listaEscuderias.add(new Escuderia("RedBull", "Christian Horner", listaPilotos.get(4), listaPilotos.get(5), 25000000));
		listaEscuderias.add(new Escuderia("Mclaren", "Andrea Seidl", listaPilotos.get(6), listaPilotos.get(7), 20000000));
		listaEscuderias.add(new Escuderia("Renault", "Cyril Abditeboul", listaPilotos.get(8), listaPilotos.get(9), 17500000));
		listaEscuderias.add(new Escuderia("ToroRosso", "Franz Tost", listaPilotos.get(10), listaPilotos.get(11), 15000000));
		listaEscuderias.add(new Escuderia("RacingPoint", "Bono", listaPilotos.get(12), listaPilotos.get(13), 15000000));
		listaEscuderias.add(new Escuderia("Haas", "Guenther Steiner", listaPilotos.get(14), listaPilotos.get(15), 10000000));
		listaEscuderias.add(new Escuderia("AlfaRomeo", "Frank Vasseur", listaPilotos.get(16), listaPilotos.get(17), 10000000));
		listaEscuderias.add(new Escuderia("Williams", "Claire Williams", listaPilotos.get(18), listaPilotos.get(19), 8000000));
		return listaEscuderias;
	}

	@Override
	public String toString() {
		return nombre;
	}	
	
	public String toString2() {
		return "Escuderia [nombre=" + nombre + ", director=" + director + ", piloto1=" + piloto1 + ", piloto2="
				+ piloto2 + ", presupuesto=" + presupuesto + "]";
	}	
}
