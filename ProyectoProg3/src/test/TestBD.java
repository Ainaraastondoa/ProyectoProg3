package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

import elementos.BD;
import elementos.Coche;
import elementos.Componente;
import elementos.Escuderia;
import elementos.Piloto;

public class TestBD {

	@Test
	public void test() throws SQLException {
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();
		
//		ArrayList<Componente> listaComponentes = BD.listaComponentesSelect(st);
		ArrayList<Coche> listaCoches = BD.listaCochesSelect(st);
// 		String componente1 = listaComponentes.get(0).toString();
//		assertEquals(componente1, "mercedes.m");
//		
//		ArrayList<Piloto> pilotos = BD.listaPilotosSelect(st);
//		for (Piloto piloto : pilotos) {
//			System.out.println(piloto.toString());
//		}
//		
//		ArrayList<Escuderia> escuderias = BD.listaEscuderiasSelect(st);
//		for (Escuderia escuderia : escuderias) {
//			System.out.println(escuderia.getPiloto1().toString());
//			System.out.println(escuderia.getPiloto2().toString());
//		}
		
		for (Coche coche : listaCoches) {
			System.out.println(coche.toString2());
		}
//		for (Componente componente : listaComponentes) {
//			System.out.println(componente.toString());
//			//System.out.println(BD.componenteSelect(st, 1).toString());
//		}
	}
}
