package elementos;


/** Esta clase define los componentes de los monoplazas
 * 
 */
public class Componente {
	
	//ATRIBUTOS de la clase Componente
	public String nombre;					//Nombre del componente
//	public ArrayList<Mejora> listaMejoras;	//Lista de mejoras disponibles para el componente
	public int rendimiento;					//Rendimiento del componente (0-100)
		
//	//CONSTRUCTOR
//	public Componente(String nombre, ArrayList<Mejora> listaMejoras) {
//		this.nombre = nombre;
//		this.listaMejoras = listaMejoras;
//		this.rendimiento = calcularRendimientoComponente(listaMejoras);
//	}
	
	//CONSTRUCTOR - SIN MEJORAS
	public Componente (String nombre, Integer rendimiento) {
		this.nombre = nombre;
		this.rendimiento = rendimiento;
	}

	@Override
	public String toString() {
		return nombre;
	}

	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getRendimiento() {
		return rendimiento;
	}

	public void setRendimiento(int rendimiento) {
		this.rendimiento = rendimiento;
	}


//	public ArrayList<Mejora> getListaMejoras() {
//		return listaMejoras;
//	}
//
//	public void setListaMejoras(ArrayList<Mejora> listaMejoras) {
//		this.listaMejoras = listaMejoras;
//	}
//
//	/** Método que crea todos los componentes predeterminados de cada uno de los coches. Ejecutar obligatoriamente después del método
//	 * crearMejorasPredeterminadas de la clase Mejora
//	 * @param Lista de las mejoras por componentes de cada coche ordenadas
//	 * @return Lista ordenada de todos los componentes de cada uno de los coches
//	 */
//	public static ArrayList<Componente> crearComponentesPredeterminados(ArrayList<ArrayList<Mejora>> listaMejoras) {
//		ArrayList<Componente> listaComponentes = new ArrayList<Componente>();
//		//MERCEDES (0-2)
//		listaComponentes.add(new Componente("mercedes.m", listaMejoras.get(0))); //MOTOR
//		listaComponentes.add(new Componente("mercedes.a", listaMejoras.get(1))); //AERODINAMICA
//		listaComponentes.add(new Componente("mercedes.c", listaMejoras.get(2))); //CHASIS
//		//FERRARI (3-5)
//		listaComponentes.add(new Componente("ferrari.m", listaMejoras.get(3)));
//		listaComponentes.add(new Componente("ferrari.a", listaMejoras.get(4)));
//		listaComponentes.add(new Componente("ferrari.c", listaMejoras.get(5)));
//		//REDBULL (6-8)
//		listaComponentes.add(new Componente("redbull.m", listaMejoras.get(6)));
//		listaComponentes.add(new Componente("redbull.a", listaMejoras.get(7)));
//		listaComponentes.add(new Componente("redbull.c", listaMejoras.get(8)));
//		//MCLAREN (9-11)
//		listaComponentes.add(new Componente("mclaren.m", listaMejoras.get(9)));
//		listaComponentes.add(new Componente("mclaren.a", listaMejoras.get(10)));
//		listaComponentes.add(new Componente("mclaren.c", listaMejoras.get(11)));
//		//RENAULT (12-14)
//		listaComponentes.add(new Componente("renault.m", listaMejoras.get(12)));
//		listaComponentes.add(new Componente("renault.a", listaMejoras.get(13)));
//		listaComponentes.add(new Componente("renault.c", listaMejoras.get(14)));
//		//TOROROSSO (15-17)
//		listaComponentes.add(new Componente("tororosso.m", listaMejoras.get(15)));
//		listaComponentes.add(new Componente("tororosso.a", listaMejoras.get(16)));
//		listaComponentes.add(new Componente("tororosso.c", listaMejoras.get(17)));
//		//RACINGPOINT (18-20)
//		listaComponentes.add(new Componente("racingpoint.m", listaMejoras.get(18)));
//		listaComponentes.add(new Componente("racingpoint.a", listaMejoras.get(19)));
//		listaComponentes.add(new Componente("racingpoint.c", listaMejoras.get(20)));
//		//HAAS (21-23)
//		listaComponentes.add(new Componente("haas.m", listaMejoras.get(21)));
//		listaComponentes.add(new Componente("haas.a", listaMejoras.get(22)));
//		listaComponentes.add(new Componente("haas.c", listaMejoras.get(23)));
//		//ALFAROMEO (24-26)
//		listaComponentes.add(new Componente("alfaromeo.m", listaMejoras.get(24)));
//		listaComponentes.add(new Componente("alfaromeo.a", listaMejoras.get(25)));
//		listaComponentes.add(new Componente("alfaromeo.c", listaMejoras.get(26)));
//		//WILLIAMS (27-29)
//		listaComponentes.add(new Componente("williams.m", listaMejoras.get(27)));
//		listaComponentes.add(new Componente("williams.a", listaMejoras.get(28)));
//		listaComponentes.add(new Componente("williams.c", listaMejoras.get(29)));
//
//		return listaComponentes;
//	}
//	
//	/**
//	 * Método que sirve para calcular el rendimiento sobre 100 de un componente en función de su lista de mejoras
//	 * @param listaMejoras Lista de mejoras que tiene un componente
//	 * @return Rendimiento del 0 al 100 que tendrá ese componente en función de sus mejoras
//	 */
//	public int calcularRendimientoComponente(ArrayList<Mejora> listaMejoras) {
//		int rendimiento = 0;
//		for (Mejora m: listaMejoras) {
//			rendimiento += m.getAumentoRendimiento();
//		}
//		return rendimiento;
//	}
}
