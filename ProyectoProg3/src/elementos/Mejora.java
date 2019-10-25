package elementos;

import java.util.ArrayList;
import java.util.Arrays;

/** Esta clase define las mejoras realizables a distintos componentes
 *
 */
public class Mejora {
	
	//ATRIBUTOS de la clase Mejora
	public String nombre;						//Nombre de la pieza a mejorar
	public String componente;					//Nombre del componente a mejorar
	public int aumentoRendimiento;				//Incremento en el rendimiento del componente
	public int coste;							//Coste de la mejora(dinero)
	public ArrayList<String> mejorasPrevias; 	//Lista de nombres de mejoras previas que necesita para poder canjearse
	
	//CONSTRUCTOR
	public Mejora(String nombre, String componente, int aumentoRendimiento, int coste, ArrayList<String> mejorasPrevias) {
		this.nombre = nombre;
		this.componente = componente;
		this.aumentoRendimiento = aumentoRendimiento;
		this.coste = coste;
		this.mejorasPrevias = mejorasPrevias;
	}

	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public int getAumentoRendimiento() {
		return aumentoRendimiento;
	}

	public void setAumentoRendimiento(int aumentoRendimiento) {
		this.aumentoRendimiento = aumentoRendimiento;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
	
	public ArrayList<String> getMejorasPrevias() {
		return mejorasPrevias;
	}

	public void setMejorasPrevias(ArrayList<String> mejorasPrevias) {
		this.mejorasPrevias = mejorasPrevias;
	}

	/**
	 * Método que crea todos las mejoras de los componentes del coche junto a su árbol
	 * @return Lista de todas las mejoras del juego
	 */
	public static ArrayList<Mejora> crearMejoras() {
		ArrayList<Mejora> listaMejoras = new ArrayList<Mejora>();
		// Motor (0-9)
		listaMejoras.add(new Mejora("PotenciaI", "Motor", 15, 9000000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("PotenciaII", "Motor", 15, 9000000, new ArrayList<String>(Arrays.asList("PotenciaI"))));
		listaMejoras.add(new Mejora("ConsumoI", "Motor", 10, 6000000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("ConsumoII", "Motor", 10, 6000000, new ArrayList<String>(Arrays.asList("ConsumoI"))));
		listaMejoras.add(new Mejora("EficienciaI", "Motor", 5, 3000000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("EficienciaII", "Motor", 5, 3000000, new ArrayList<String>(Arrays.asList("EficienciaI"))));
		listaMejoras.add(new Mejora("ControlCalidadMotor", "Motor", 10, 6000000, new ArrayList<String>(Arrays.asList
				("ConsumoII", "EficienciaII"))));
		listaMejoras.add(new Mejora("EficienciaIII", "Motor", 5, 3000000, new ArrayList<String>(Arrays.asList
				("PotenciaII", "ControlCalidadMotor"))));
		listaMejoras.add(new Mejora("ConsumoIII", "Motor", 10, 6000000, new ArrayList<String>(Arrays.asList("ControlCalidadMotor"))));
		listaMejoras.add(new Mejora("PotenciaIII", "Motor", 15, 9000000, new ArrayList<String>(Arrays.asList
				("EficienciaIII", "ConsumoIII"))));
		// Aerodinámica (10-19)
		listaMejoras.add(new Mejora("AleronesI", "Aerodinamica", 10, 6000000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("AleronesII", "Aerodinamica", 10, 6000000, new ArrayList<String>(Arrays.asList("AleronesI"))));
		listaMejoras.add(new Mejora("ResistenciaVientoI", "Aerodinamica", 5, 3000000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("ResistenciaVientoII", "Aerodinamica", 5, 6000000, new ArrayList<String>(Arrays.asList
				("ResistenciaVientoI"))));
		listaMejoras.add(new Mejora("CargaAerodinamicaI", "Aerodinamica", 15, 9000000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("CargaAerodinamicaII", "Aerodinamica", 15, 9000000, new ArrayList<String>(Arrays.asList
				("CargaAerodinamicaI", "ResistenciaVientoI"))));
		listaMejoras.add(new Mejora("DRS", "Aerodinamica", 5, 3000000, new ArrayList<String>(Arrays.asList
				("AleronesII", "ResistenciaVientoII"))));
		listaMejoras.add(new Mejora("FondoPlano", "Aerodinamica", 10, 6000000, new ArrayList<String>(Arrays.asList("DRS"))));
		listaMejoras.add(new Mejora("DifusorI", "Aerodinamica", 10, 6000000, new ArrayList<String>(Arrays.asList("DRS"))));
		listaMejoras.add(new Mejora("CargaAerodinamicaIII", "Aerodinamica", 15, 9000000, new ArrayList<String>(Arrays.asList
				("FondoPlano", "DifusorI", "CargaAerodinamicaII"))));
		// Chasis (20-29)
		listaMejoras.add(new Mejora("SuspensionI", "Chasis", 12, 7200000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("SuspensionII", "Aerodinamica", 12, 7200000, new ArrayList<String>(Arrays.asList("SuspensionI"))));
		listaMejoras.add(new Mejora("ReduccionPesoI", "Aerodinamica", 10, 6000000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("ReduccionPesoII", "Aerodinamica", 5, 3000000, new ArrayList<String>(Arrays.asList
				("ReduccionPesoI"))));
		listaMejoras.add(new Mejora("RedistribucionPesoI", "Aerodinamica", 8, 4800000, new ArrayList<String>()));
		listaMejoras.add(new Mejora("RedistribucionPesoII", "Aerodinamica", 8, 4800000, new ArrayList<String>(Arrays.asList
				("RedistribucionPesoI"))));
		listaMejoras.add(new Mejora("SuspensionIII", "Aerodinamica", 10, 6000000, new ArrayList<String>(Arrays.asList
				("SuspensionII", "ReduccionPesoII"))));
		listaMejoras.add(new Mejora("ControlCalidadChasis", "Aerodinamica", 5, 3000000, new ArrayList<String>(Arrays.asList
				("RedistribucionPesoII", "ReduccionPesoII"))));
		listaMejoras.add(new Mejora("RedistribucionPesoIII", "Aerodinamica", 15, 9000000, new ArrayList<String>(Arrays.asList
				("ControlCalidadChasis"))));
		listaMejoras.add(new Mejora("ReduccionPesoIII", "Aerodinamica", 15, 9000000, new ArrayList<String>(Arrays.asList
				("ControlCalidadChasis"))));
		return listaMejoras;
	}
	
	/**
	 * Método que sirve para crear las mejoras predeterminadas de los componentes de los coches de las 10 escuderías.
	 * Se devuelve en forma de lista de listas. La lista sigue este orden: (escuderia1-motor, escuderia1-aero, escuderia1-chasis, 
	 * escuderia2-motor...), y tiene en total 30 elementos (3 por cada escudería + 10 escuderías)
	 * @return La lista con las mejoras por compoenente y equipo ordenadas
	 */
	public static ArrayList<ArrayList<Mejora>> crearMejorasPredeterminadas() {
		ArrayList<Mejora> lista = crearMejoras();
		// Mercedes
		ArrayList<Mejora> mercedesM = new ArrayList<Mejora>(Arrays.asList(lista.get(0), lista.get(1), lista.get(2), lista.get(3),
				lista.get(4), lista.get(5), lista.get(6), lista.get(7)));
		ArrayList<Mejora> mercedesA = new ArrayList<Mejora>(Arrays.asList(lista.get(10), lista.get(11), lista.get(12), lista.get(13),
				lista.get(14), lista.get(15), lista.get(16), lista.get(17), lista.get(18)));
		ArrayList<Mejora> mercedesC = new ArrayList<Mejora>(Arrays.asList(lista.get(20), lista.get(21), lista.get(22), lista.get(23),
				lista.get(24), lista.get(25), lista.get(26), lista.get(27)));
		// Ferrari
		ArrayList<Mejora> ferrariM = mercedesM;
		ArrayList<Mejora> ferrariA = new ArrayList<Mejora>(Arrays.asList(lista.get(10), lista.get(11), lista.get(12), lista.get(13),
				lista.get(14), lista.get(15), lista.get(16)));
		ArrayList<Mejora> ferrariC = new ArrayList<Mejora>(Arrays.asList(lista.get(20), lista.get(21), lista.get(22), lista.get(23),
				lista.get(24), lista.get(25)));
		// Redbull
		ArrayList<Mejora> redbullM = new ArrayList<Mejora>(Arrays.asList(lista.get(0), lista.get(2), lista.get(3), lista.get(4),
				lista.get(5), lista.get(6)));
		ArrayList<Mejora> redbullA = mercedesA;
		ArrayList<Mejora> redbullC = mercedesC;
		// Mclaren
		ArrayList<Mejora> mclarenM = new ArrayList<Mejora>(Arrays.asList(lista.get(0), lista.get(1), lista.get(2), lista.get(3),
				lista.get(4), lista.get(5), lista.get(6)));
		ArrayList<Mejora> mclarenA = new ArrayList<Mejora>(Arrays.asList(lista.get(10), lista.get(11), lista.get(12), lista.get(13),
				lista.get(14)));
		ArrayList<Mejora> mclarenC = new ArrayList<Mejora>(Arrays.asList(lista.get(20), lista.get(21), lista.get(22), lista.get(23),
				lista.get(24)));
		// Renault
		ArrayList<Mejora> renaultM = mclarenM;
		ArrayList<Mejora> renaultA = mclarenA;
		ArrayList<Mejora> renaultC = new ArrayList<Mejora>(Arrays.asList(lista.get(20), lista.get(22), lista.get(23), lista.get(24)));
		// Toro Rosso
		ArrayList<Mejora> tororossoM = redbullM;
		ArrayList<Mejora> tororossoA = new ArrayList<Mejora>(Arrays.asList(lista.get(10), lista.get(11), lista.get(12), lista.get(14)));
		ArrayList<Mejora> tororossoC = new ArrayList<Mejora>(Arrays.asList(lista.get(20), lista.get(22), lista.get(24)));
		// Racing Point
		ArrayList<Mejora> racingM = mercedesM;
		ArrayList<Mejora> racingA = tororossoA;
		ArrayList<Mejora> racingC = renaultC;
		// Haas
		ArrayList<Mejora> haasM = ferrariM;
		ArrayList<Mejora> haasA = tororossoA;
		ArrayList<Mejora> haasC = new ArrayList<Mejora>(Arrays.asList(lista.get(20), lista.get(22), lista.get(24), lista.get(25)));
		// Alfa Romeo
		ArrayList<Mejora> alfaromeoM = ferrariM;
		ArrayList<Mejora> alfaromeoA = new ArrayList<Mejora>(Arrays.asList(lista.get(10), lista.get(12), lista.get(14)));
		ArrayList<Mejora> alfaromeoC = tororossoC;
		// Williams
		ArrayList<Mejora> williamsM = mercedesM;
		ArrayList<Mejora> williamsA = new ArrayList<Mejora>(Arrays.asList(lista.get(10), lista.get(12)));
		ArrayList<Mejora> williamsC = new ArrayList<Mejora>(Arrays.asList(lista.get(20), lista.get(22)));
		
		ArrayList<ArrayList<Mejora>> listaFinal = new ArrayList<ArrayList<Mejora>>();
		listaFinal.add(mercedesM); listaFinal.add(mercedesA); listaFinal.add(mercedesC);
		listaFinal.add(ferrariM); listaFinal.add(ferrariA); listaFinal.add(ferrariC);
		listaFinal.add(redbullM); listaFinal.add(redbullA); listaFinal.add(redbullC);
		listaFinal.add(mclarenM); listaFinal.add(mclarenA); listaFinal.add(mclarenC);
		listaFinal.add(renaultM); listaFinal.add(renaultA); listaFinal.add(renaultC);
		listaFinal.add(tororossoM); listaFinal.add(tororossoA); listaFinal.add(tororossoC);
		listaFinal.add(racingM); listaFinal.add(racingA); listaFinal.add(racingC);
		listaFinal.add(haasM); listaFinal.add(haasA); listaFinal.add(haasC);
		listaFinal.add(alfaromeoM); listaFinal.add(alfaromeoA); listaFinal.add(alfaromeoC);
		listaFinal.add(williamsM); listaFinal.add(williamsA); listaFinal.add(williamsC);
		return listaFinal;
	}
}
