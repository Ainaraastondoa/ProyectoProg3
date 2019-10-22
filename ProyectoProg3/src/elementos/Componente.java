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
	
	/** Metodo que crea todos los componentes de cada uno de los coches
	 * @return lista de todos los componentes de cada uno de los coches
	 */
	public ArrayList<Componente> crearComponentes() {
		ArrayList<Componente> listaComponentes = new ArrayList<Componente>();
		//MERCEDES
		listaComponentes.add(new Componente("mercedes.m", listaMejoras, 88)); //MOTOR
		listaComponentes.add(new Componente("mercedes.a", listaMejoras, 88)); //AERODINAMICA
		listaComponentes.add(new Componente("mercedes.c", listaMejoras, 90)); //CHASIS
		//FERRARI
		listaComponentes.add(new Componente("ferrari.m", listaMejoras, 91));
		listaComponentes.add(new Componente("ferrari.a", listaMejoras, 88));
		listaComponentes.add(new Componente("ferrari.c", listaMejoras, 85));
		//REDBULL
		listaComponentes.add(new Componente("redbull.m", listaMejoras, 80));
		listaComponentes.add(new Componente("redbull.a", listaMejoras, 89));
		listaComponentes.add(new Componente("redbull.c", listaMejoras, 91));
		//MCLAREN
		listaComponentes.add(new Componente("mclaren.m", listaMejoras, 81));
		listaComponentes.add(new Componente("mclaren.a", listaMejoras, 82));
		listaComponentes.add(new Componente("mclaren.c", listaMejoras, 79));
		//RENAULT
		listaComponentes.add(new Componente("renault.m", listaMejoras, 81));
		listaComponentes.add(new Componente("renault.a", listaMejoras, 78));
		listaComponentes.add(new Componente("renault.c", listaMejoras, 76));
		//TOROROSSO
		listaComponentes.add(new Componente("tororosso.m", listaMejoras, 80));
		listaComponentes.add(new Componente("tororosso.a", listaMejoras, 76));
		listaComponentes.add(new Componente("tororosso.c", listaMejoras, 77));
		//RACINGPOINT
		listaComponentes.add(new Componente("racingpoint.m", listaMejoras, 86));
		listaComponentes.add(new Componente("racingpoint.a", listaMejoras, 75));
		listaComponentes.add(new Componente("racingpoint.c", listaMejoras, 73));
		//HAAS
		listaComponentes.add(new Componente("haas.m", listaMejoras, 91));
		listaComponentes.add(new Componente("haas.a", listaMejoras, 73));
		listaComponentes.add(new Componente("haas.c", listaMejoras, 72));
		//ALFAROMEO
		listaComponentes.add(new Componente("alfaromeo.m", listaMejoras, 91));
		listaComponentes.add(new Componente("alfaromeo.a", listaMejoras, 70));
		listaComponentes.add(new Componente("alfaromeo.c", listaMejoras, 68));
		//WILLIAMS
		listaComponentes.add(new Componente("williams.m", listaMejoras, 86));
		listaComponentes.add(new Componente("williams.a", listaMejoras, 51));
		listaComponentes.add(new Componente("williams.c", listaMejoras, 55));

		return listaComponentes;
	}
}
