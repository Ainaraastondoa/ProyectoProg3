package elementos;

import java.util.ArrayList;

/** Esta clase define los circuitos del mundial y sus caracteristicas
 *
 */
public class Circuito {
	
	//ATRIBUTOS de la clase circuito
	public String nombre;			//Nombre del circuito
	public String pais;				//Pais de origen
	public String fecha;			//Fecha de celebracion del GP
	public int degradacion;			//degradadion por vuelta en el circuito
	public float tiempoQ;			//Tiempo de referencia para Clasificacion
	public float tiempoR;			//Tiempo de referencia para Carrera
	public float tiempoB;			//Tiempo de referencia para la vuelta de Boxes
	public int vueltas;				//Vueltas que se completan en el GP
	public ArrayList resultados;	//Resultados y clasificacion en carrera
	
	//CONSTRUCTOR
	public Circuito(String nombre, String pais, String fecha, int degradacion, float tiempoQ, float tiempoR,
			float tiempoB, int vueltas, ArrayList resultados) {
		this.nombre = nombre;
		this.pais = pais;
		this.fecha = fecha;
		this.degradacion = degradacion;
		this.tiempoQ = tiempoQ;
		this.tiempoR = tiempoR;
		this.tiempoB = tiempoB;
		this.vueltas = vueltas;
		this.resultados = resultados;
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

	public float getTiempoQ() {
		return tiempoQ;
	}

	public void setTiempoQ(float tiempoQ) {
		this.tiempoQ = tiempoQ;
	}

	public float getTiempoR() {
		return tiempoR;
	}

	public void setTiempoR(float tiempoR) {
		this.tiempoR = tiempoR;
	}

	public float getTiempoB() {
		return tiempoB;
	}

	public void setTiempoB(float tiempoB) {
		this.tiempoB = tiempoB;
	}

	public int getVueltas() {
		return vueltas;
	}

	public void setVueltas(int vueltas) {
		this.vueltas = vueltas;
	}

	public ArrayList getResultados() {
		return resultados;
	}

	public void setResultados(ArrayList resultados) {
		this.resultados = resultados;
	}
}
