package elementos;

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
}
