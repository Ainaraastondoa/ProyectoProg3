package elementos;

import java.util.ArrayList;

public class initDatos {
	
	public static ArrayList<Piloto> initPilotos() {
	 
	ArrayList<ArrayList<Mejora>> listaMejoras = Mejora.crearMejorasPredeterminadas();
	ArrayList<Componente> listaComponentes = Componente.crearComponentesPredeterminados(listaMejoras);
	ArrayList<Coche> listaCoches = Coche.crearCochesPredeterminados(listaComponentes);
	ArrayList<Piloto> listaPilotos = Piloto.crearPilotosPredeterminados(listaCoches);
	return listaPilotos;
	}
}
