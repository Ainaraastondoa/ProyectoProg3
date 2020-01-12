package elementos;

import java.util.ArrayList;

/** Esta clase define los circuitos del mundial y sus caracteristicas
 * 
 */
public class Circuito {
	
	//ATRIBUTOS de la clase Circuito
	public String nombre;							//Nombre del circuito
	public String pais;								//Pa�s de origen
	public int nivelDegradacion;					//Nivel de degradación por vuelta en el circuito (1-3)
	public float tiempoReferenciaClasif;			//Tiempo de referencia para Clasificacion
	public float tiempoReferenciaCarrera;			//Tiempo de referencia para Carrera
	public float tiempoExtraBoxes;					//Tiempo que se tarda en pasar por boxes en el circuito
	public int vueltas;								//Vueltas que se completan en el GP
	public String imagen;							//Imagen de la silueta del circuito

	//CONSTRUCTOR
	public Circuito(String nombre, String pais, int nivelDegradacion, float tiempoReferenciaClasif,
			float tiempoReferenciaCarrera, float tiempoReferenciaBoxes, int vueltas, String imagen) {
		this.nombre = nombre;
		this.pais = pais;
		this.nivelDegradacion = nivelDegradacion;
		this.tiempoReferenciaClasif = tiempoReferenciaClasif;
		this.tiempoReferenciaCarrera = tiempoReferenciaCarrera;
		this.tiempoExtraBoxes = tiempoReferenciaBoxes;
		this.vueltas = vueltas;
		this.imagen = imagen;
	}

	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getNivelDegradacion() {
		return nivelDegradacion;
	}

	public void setNivelDegradacion(int nivelDegradacion) {
		this.nivelDegradacion = nivelDegradacion;
	}

	public float getTiempoReferenciaClasif() {
		return tiempoReferenciaClasif;
	}

	public void setTiempoReferenciaClasif(float tiempoReferenciaClasif) {
		this.tiempoReferenciaClasif = tiempoReferenciaClasif;
	}

	public float getTiempoReferenciaCarrera() {
		return tiempoReferenciaCarrera;
	}

	public void setTiempoReferenciaCarrera(float tiempoReferenciaCarrera) {
		this.tiempoReferenciaCarrera = tiempoReferenciaCarrera;
	}

	public float getTiempoExtraBoxes() {
		return tiempoExtraBoxes;
	}

	public void setTiempoExtraBoxes(float tiempoExtraBoxes) {
		this.tiempoExtraBoxes = tiempoExtraBoxes;
	}

	public int getVueltas() {
		return vueltas;
	}

	public void setVueltas(int vueltas) {
		this.vueltas = vueltas;
	}	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * M�todo que sirve para crear los circuitos predeterminados de la F1
	 * @return Lista de los circuitos
	 */
//	public static ArrayList<Circuito> crearCircuitosPredeterminados() {
//		ArrayList<Circuito> listaCircuitos = new ArrayList<Circuito>();
//		
//		listaCircuitos.add(new Circuito("Albert Park", "Australia", 2, (float)79.5, (float)84.5, (float)22.0, 58));
//		listaCircuitos.add(new Circuito("Sakhir", "Bahrein", 3, (float)76.8, (float)82.4, (float)22.0, 57));
//		listaCircuitos.add(new Circuito("Shanghai", "China", 3, (float)80.5, (float)83.7, (float)24.0, 56));
//		listaCircuitos.add(new Circuito("Baku City Circuit", "Azerbaiy�n", 2, (float)89.5, (float)92.0, (float)22.0, 51));
//		listaCircuitos.add(new Circuito("Catalunya", "Espa�a", 3, (float)74.4, (float)77.5, (float)24.0, 66));
//		listaCircuitos.add(new Circuito("Montecarlo", "M�naco", 1, (float)69.1, (float)73.3, (float)20.0, 78));
//		listaCircuitos.add(new Circuito("Gilles Villeneuve", "Canad�", 2, (float)69.2, (float)72.0, (float)20.0, 70));
//		listaCircuitos.add(new Circuito("Paul Ricard", "Francia", 2, (float)87.3, (float)91.7, (float)24.0, 53));
//		listaCircuitos.add(new Circuito("Red Bull Ring", "Austria", 2, (float)62.0, (float)66.5, (float)20.0, 71));
//		listaCircuitos.add(new Circuito("Silverstone", "Reino Unido", 3, (float)84.1, (float)86.4, (float)20.0, 52));
//		listaCircuitos.add(new Circuito("Hockenheim", "Alemania", 2, (float)70.8, (float)75.6, (float)22.0, 64));
//		listaCircuitos.add(new Circuito("Hungaroring", "Hungr�a", 2, (float)73.5, (float)76.1, (float)22.0, 70));
//		listaCircuitos.add(new Circuito("Spa-Francorchamps", "B�lgica", 3, (float)101.5, (float)105.4, (float)24.0, 44));
//		listaCircuitos.add(new Circuito("Monza", "Italia", 2, (float)78.3, (float)80.8, (float)24.0, 53));
//		listaCircuitos.add(new Circuito("Marina Bay", "Singapur", 2, (float)95.2, (float)100.3, (float)20.0, 61));
//		listaCircuitos.add(new Circuito("Sochi", "Rusia", 2, (float)90.6, (float)94.8, (float)20.0, 53));
//		listaCircuitos.add(new Circuito("Suzuka", "Jap�n", 3, (float)86.0, (float)90.0, (float)20.0, 52));
//		listaCircuitos.add(new Circuito("Hermanos Rodr�guez", "M�xico", 2, (float)74.0, (float)78.2, (float)22.0, 71));
//		listaCircuitos.add(new Circuito("Las Am�ricas", "Estados Unidos", 3, (float)91.2, (float)96.3, (float)22.0, 56));
//		listaCircuitos.add(new Circuito("Interlagos", "Brasil", 2, (float)66.3, (float)69.5, (float)24.0, 71));
//		listaCircuitos.add(new Circuito("Yas Marina", "Abu Dabi", 3, (float)93.8, (float)99.8, (float)26.0, 55));
//		
//		return listaCircuitos;
//	}

	@Override
	public String toString() {
		return "Circuito [nombre=" + nombre + ", pais=" + pais + ", vueltas=" + vueltas + "]";
	}
}
