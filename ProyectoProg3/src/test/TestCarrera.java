package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import elementos.BD;
import elementos.Carrera;
import elementos.Circuito;
import elementos.Piloto;

public class TestCarrera {
	
	public static Carrera carrera;
	public static ArrayList<Circuito> listaCircuitos;
	public static ArrayList<Piloto> listaPilotos;
	public static int contador = 0;

	@BeforeClass
	public static void setUpClass() {
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st;
		try {
			st = con.createStatement();
			listaCircuitos = BD.listaCircuitosSelect(st);
			listaPilotos = BD.listaPilotosSelect(st);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void antes() {
		carrera = new Carrera(listaCircuitos.get(contador), listaPilotos);
		contador++;
	}

	@Test
	public void testCarrera1() {
		carrera.simularCarrera();
		carrera.ordenarClasificacionCarrera();
		double tiempoPrimero = carrera.getListaTiempos().get(0);
		assertTrue(tiempoPrimero > 5060 && tiempoPrimero < 5090);
	}
	
	@Test
	public void testCarrera2() {
		carrera.simularCarrera();
		carrera.ordenarClasificacionCarrera();
		Piloto piloto1 = carrera.getListaPilotos().get(0);
		Piloto piloto2 = carrera.getListaPilotos().get(1);
		Piloto piloto3 = carrera.getListaPilotos().get(2);
		Piloto piloto4 = carrera.getListaPilotos().get(3);
		assertTrue(piloto1.getNombre().equals("Hamilton") || piloto2.getNombre().equals("Hamilton") || 
				piloto3.getNombre().equals("Hamilton") || piloto4.getNombre().equals("Hamilton"));
	}
	
	@Test
	public void testCarrera3() {
		carrera.simularCarrera();
		carrera.ordenarClasificacionCarrera();
		Piloto piloto1 = carrera.getListaPilotos().get(18);
		Piloto piloto2 = carrera.getListaPilotos().get(19);
		assertTrue(piloto1.getNombre().equals("Kubica") || piloto2.getNombre().equals("Kubica"));
	}
	
	@Test
	public void testCarrera4() {
		carrera.simularCarrera();
		carrera.ordenarClasificacionCarrera();
		double tiempo1 = carrera.getListaTiempos().get(0);
		double tiempo2 = carrera.getListaTiempos().get(1);
		assertEquals(tiempo1, tiempo2, 10);
	}
	
	@Test
	public void testCarrera5() {
		carrera.simularCarrera();
		carrera.ordenarClasificacionCarrera();
		for (int i = 0; i < 10; i++) {
			if (carrera.getListaPilotos().get(i).getNombre().equals("Bottas")) {
				assertTrue(carrera.getListaTiempos().get(i) > 5320);
			}
		}
	}
}
