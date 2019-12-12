package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

import elementos.BD;
import elementos.Componente;

public class TestBD {

	@Test
	public void test() throws SQLException {
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();
		
		ArrayList<Componente> listaComponentes = BD.listaComponentesSelect(st);
		
		String componente1 = listaComponentes.get(0).toString();
		assertEquals(componente1, "mercedes.m");
		
		for (Componente componente : listaComponentes) {
			System.out.println(componente.toString());
		}
	}
}
