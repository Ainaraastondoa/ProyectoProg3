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
		listaComponentes.add(new Componente("mercedes.m", null, 88)); //MOTOR
		listaComponentes.add(new Componente("mercedes.a", null, 88)); //AERODINAMICA
		listaComponentes.add(new Componente("mercedes.c", null, 90)); //CHASIS
		//FERRARI
		listaComponentes.add(new Componente("ferrari.m", null, 91));
		listaComponentes.add(new Componente("ferrari.a", null, 88));
		listaComponentes.add(new Componente("ferrari.c", null, 85));
		//REDBULL
		listaComponentes.add(new Componente("redbull.m", null, 80));
		listaComponentes.add(new Componente("redbull.a", null, 89));
		listaComponentes.add(new Componente("redbull.c", null, 91));
		//MCLAREN
		listaComponentes.add(new Componente("mclaren.m", null, 81));
		listaComponentes.add(new Componente("mclaren.a", null, 82));
		listaComponentes.add(new Componente("mclaren.c", null, 79));
		//RENAULT
		listaComponentes.add(new Componente("renault.m", null, 81));
		listaComponentes.add(new Componente("renault.a", null, 78));
		listaComponentes.add(new Componente("renault.c", null, 76));
		//TOROROSSO
		listaComponentes.add(new Componente("tororosso.m", null, 80));
		listaComponentes.add(new Componente("tororosso.a", null, 76));
		listaComponentes.add(new Componente("tororosso.c", null, 77));
		//RACINGPOINT
		listaComponentes.add(new Componente("racingpoint.m", null, 86));
		listaComponentes.add(new Componente("racingpoint.a", null, 75));
		listaComponentes.add(new Componente("racingpoint.c", null, 73));
		//HAAS
		listaComponentes.add(new Componente("haas.m", null, 91));
		listaComponentes.add(new Componente("haas.a", null, 73));
		listaComponentes.add(new Componente("haas.c", null, 72));
		//ALFAROMEO
		listaComponentes.add(new Componente("alfaromeo.m", null, 91));
		listaComponentes.add(new Componente("alfaromeo.a", null, 70));
		listaComponentes.add(new Componente("alfaromeo.c", null, 68));
		//WILLIAMS
		listaComponentes.add(new Componente("williams.m", null, 86));
		listaComponentes.add(new Componente("williams.a", null, 51));
		listaComponentes.add(new Componente("williams.c", null, 55));

		return listaComponentes;
	}
}
