package elementos;

/** Esta clase define las mejoras realizables a distintos componentes
 *
 */
public class Mejora {
	
	//ATRIBUTOS de la clase Mejora
	public String nombre;			//nombre de la pieza a mejorar
	public Componente componente;	//componente a mejorar
	public int mejora;				//Incremento en el rendimiento del componente
	public int tiempo;				//Duracion de la mejora(en semanas)
	public int coste;				//Coste de la mejora(dinero)
	public String codigo;			//codigo de la mejora
	
	//CONSTRUCTOR
	public Mejora(String nombre, Componente componente, int mejora, int tiempo, int coste, String codigo) {
		this.nombre = nombre;
		this.componente = componente;
		this.mejora = mejora;
		this.tiempo = tiempo;
		this.coste = coste;
		this.codigo = codigo;
	}

	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public int getMejora() {
		return mejora;
	}

	public void setMejora(int mejora) {
		this.mejora = mejora;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
