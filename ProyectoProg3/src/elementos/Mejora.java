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
	public ArrayList<Mejora> crearMejoras() {
		ArrayList<Mejora> listaMejoras = new ArrayList<Mejora>();
		// Motor
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
		// Aerodinámica
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
		// Chasis
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
}
