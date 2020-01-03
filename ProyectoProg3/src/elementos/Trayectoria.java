package elementos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class Trayectoria {

	// ATRIBUTOS de la clase Trayectoria - DATOS
	private ArrayList<Piloto> listaPilotos;
	private ArrayList<Componente> listaComponentes;
	private ArrayList<Coche> listaCoches;
	private ArrayList<Circuito> listaCircuitos;
	private ArrayList<Carrera> listaCarreras;
	private ArrayList<Escuderia> listaEscuderias;
	private ArrayList<Temporada> listaTemporadas;
	
	private static Escuderia escuderia; 		//Escuderia seleccionada para la trayectoria
	

	// CONSTRUCTOR de la clase Trayectoria
	public Trayectoria() {
		// Creación de la base de datos y la trayectoria
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st;
		try {
			st = con.createStatement();
			BD.usarCrearTablasBD(con);
			//PARA QUE NO SALGA EL UNIQUE CONSTRAINT ERROR EN LA BASE DE DATOS (METER MÁS DE UNA VEZ LOS MISMOS DATOS)
			//BD.insertDatos(st);
			
			// Inicializar componentes trayectoria
//			ArrayList<ArrayList<Mejora>> listaMejoras = Mejora.crearMejorasPredeterminadas();
			this.listaComponentes = BD.listaComponentesSelect(st);
			this.listaCoches = BD.listaCochesSelect(st);
			this.listaCircuitos = BD.listaCircuitosSelect(st);
			this.listaPilotos = BD.listaPilotosSelect(st);
			this.listaEscuderias = BD.listaEscuderiasSelect(st);
			this.listaCarreras = new ArrayList<Carrera>();
			this.listaTemporadas = new ArrayList<Temporada>();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// GETTERS Y SETTERS
	public ArrayList<Temporada> getListaTemporadas() {
		return listaTemporadas;
	}

	public void setListaTemporadas(ArrayList<Temporada> listaTemporadas) {
		this.listaTemporadas = listaTemporadas;
	}

	public ArrayList<Escuderia> getListaEscuderias() {
		return listaEscuderias;
	}

	public void setListaEscuderias(ArrayList<Escuderia> listaEscuderias) {
		this.listaEscuderias = listaEscuderias;
	}
	
	public ArrayList<Piloto> getListaPilotos() {
		return listaPilotos;
	}

	public void setListaPilotos(ArrayList<Piloto> listaPilotos) {
		this.listaPilotos = listaPilotos;
	}

	
	public static Escuderia getEscuderia() {
		return escuderia;
	}

	public static void setEscuderia(Escuderia escuderia) {
		Trayectoria.escuderia = escuderia;
	}

	// Método de simulación de una trayectoria
	public void simularTrayectoria() {
		
		// Añadir temporada
		Temporada t = new Temporada(2019, this.listaPilotos, this.listaEscuderias);
		this.getListaTemporadas().add(t);
		
		// Añadir carrera 1
		Carrera c = new Carrera(this.listaCircuitos.get(0), this.getListaPilotos());
		this.getListaTemporadas().get(0).getListaCarreras().add(c);
		
		// Simular Carrera (carrera 1)
		Integer carreraActual = 0;
		t.getListaCarreras().get(carreraActual).simularCarrera();
		t.getListaCarreras().get(carreraActual).ordenarClasificacionCarrera();
		t.getListaCarreras().get(carreraActual).repartirPuntos(t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).actualizarPuntosEscuderia(t.getPuntosEscuderia(), t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).repartirDinero(t.getPuntosEscuderia());
				
		// Comprobaciones (carrera 1)
		System.out.println("Resultado Carrera:");
		System.out.println(t.getListaCarreras().get(carreraActual).getListaPilotos());
		System.out.println(t.getListaCarreras().get(carreraActual).getListaTiempos());
		t.getPuntosPiloto().forEach( (k, v) -> System.out.println("Piloto: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Dinero: " + k.getPresupuesto()));
		
		// Añadir carrera 2
		Carrera c2 = new Carrera(this.listaCircuitos.get(1), this.getListaPilotos());
		this.getListaTemporadas().get(0).getListaCarreras().add(c);
		
		// Simular Carrera (carrera 2)
		carreraActual = 1;
		t.getListaCarreras().get(carreraActual).simularCarrera();
		t.getListaCarreras().get(carreraActual).ordenarClasificacionCarrera();
		t.getListaCarreras().get(carreraActual).repartirPuntos(t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).actualizarPuntosEscuderia(t.getPuntosEscuderia(), t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).repartirDinero(t.getPuntosEscuderia());
				
		// Comprobaciones (carrera 2)
		System.out.println("Resultado Carrera:");
		System.out.println(t.getListaCarreras().get(carreraActual).getListaPilotos());
		System.out.println(t.getListaCarreras().get(carreraActual).getListaTiempos());
		t.getPuntosPiloto().forEach( (k, v) -> System.out.println("Piloto: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Puntos: " + v ));
		t.getPuntosEscuderia().forEach( (k, v) -> System.out.println("Escudería: " + k + "   Dinero: " + k.getPresupuesto()));
	}
	

	// Método main de prueba
	public static void main(String[] args) {
		Trayectoria t = new Trayectoria();
		t.simularTrayectoria();
	}
}
