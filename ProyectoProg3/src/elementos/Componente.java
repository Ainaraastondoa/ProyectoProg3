package elementos;

import java.util.ArrayList;

/** Esta clase define los componentes de los monoplazas
 *
 */
public class Componente {
	
	//ATRIBUTOS de la clase Componente
	public String nombre;					//Nombre del componente
	public ArrayList<Mejora> listaMejoras;	//Lista de mejoras disponibles para el componente
	public int rendimiento;					//Rendimiento del componente (0-100)
		
	//CONSTRUCTOR
	public Componente(String nombre, ArrayList<Mejora> listaMejoras, int rendimiento) {
		this.nombre = nombre;
		this.listaMejoras = listaMejoras;
		this.rendimiento = rendimiento;
	}

	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Mejora> getListaMejoras() {
		return listaMejoras;
	}

	public void setListaMejoras(ArrayList<Mejora> listaMejoras) {
		this.listaMejoras = listaMejoras;
	}

	public int getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(int rendimiento) {
		this.rendimiento = rendimiento;
	}	
}
