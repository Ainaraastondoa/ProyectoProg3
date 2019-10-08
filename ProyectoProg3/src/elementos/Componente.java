package elementos;

import java.util.ArrayList;

/** Esta clase define los componentes de los monoplazas
 *
 */
public class Componente {
	
	//ATRIBUTOS de la clase Componente
	public String nombre;			//Nombre del componente 
	public int incidencia;			//Incidencia del componente en el rendimiento general del coche
	public ArrayList listaMejoras;	//Lista de mejoras disponibles para el componente
	public int rendimiento;			//Rendimiento del componente (0-10)
		
	//CONSTRUCTOR
	public Componente(String nombre, int incidencia, ArrayList listaMejoras, int rendimiento) {
		this.nombre = nombre;
		this.incidencia = incidencia;
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

	public int getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(int incidencia) {
		this.incidencia = incidencia;
	}

	public ArrayList getListaMejoras() {
		return listaMejoras;
	}

	public void setListaMejoras(ArrayList listaMejoras) {
		this.listaMejoras = listaMejoras;
	}

	public int getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(int rendimiento) {
		this.rendimiento = rendimiento;
	}	
}
