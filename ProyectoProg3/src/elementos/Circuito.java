package elementos;

/** Esta clase define los circuitos del mundial y sus caracteristicas
 *
 */
public class Circuito {
	
	//ATRIBUTOS de la clase Circuito
	public String nombre;							//Nombre del circuito
	public String pais;								//País de origen
	public String fecha;							//Fecha de celebracion del GP
	public int degradacion;							//Degradadion por vuelta en el circuito
	public float tiempoReferenciaClasif;			//Tiempo de referencia para Clasificacion
	public float tiempoReferenciaCarrera;			//Tiempo de referencia para Carrera
	public float tiempoReferenciaBoxes;				//Tiempo de referencia para la vuelta de Boxes
	public int vueltas;								//Vueltas que se completan en el GP
	
	//CONSTRUCTOR
	public Circuito(String nombre, String pais, String fecha, int degradacion, float tiempoReferenciaClasif,
			float tiempoReferenciaCarrera, float tiempoReferenciaBoxes, int vueltas) {
		this.nombre = nombre;
		this.pais = pais;
		this.fecha = fecha;
		this.degradacion = degradacion;
		this.tiempoReferenciaClasif = tiempoReferenciaClasif;
		this.tiempoReferenciaCarrera = tiempoReferenciaCarrera;
		this.tiempoReferenciaBoxes = tiempoReferenciaBoxes;
		this.vueltas = vueltas;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getDegradacion() {
		return degradacion;
	}

	public void setDegradacion(int degradacion) {
		this.degradacion = degradacion;
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

	public float getTiempoReferenciaBoxes() {
		return tiempoReferenciaBoxes;
	}

	public void setTiempoReferenciaBoxes(float tiempoReferenciaBoxes) {
		this.tiempoReferenciaBoxes = tiempoReferenciaBoxes;
	}

	public int getVueltas() {
		return vueltas;
	}

	public void setVueltas(int vueltas) {
		this.vueltas = vueltas;
	}
}
