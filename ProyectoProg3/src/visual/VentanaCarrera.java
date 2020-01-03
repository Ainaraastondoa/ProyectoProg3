package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.BD;
import elementos.Carrera;
import elementos.Circuito;
import elementos.Escuderia;
import elementos.Piloto;
import elementos.Temporada;
import elementos.Trayectoria;


public class VentanaCarrera extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	JFrame VentanaClasifCarrera;
	Piloto piloto;
	
	ArrayList<Temporada> listaTemporadas;
	 
	public VentanaCarrera(JFrame m) throws SQLException {
		VentanaClasifCarrera = m; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();
		ArrayList<Circuito> circuitos = BD.listaCircuitosSelect(st);
		ArrayList<Piloto> pilotos = BD.listaPilotosSelect(st);
		ArrayList<Escuderia> escuderias = BD.listaEscuderiasSelect(st);
		this.listaTemporadas = new ArrayList<Temporada>();

		// Añadir temporada
		Temporada t = new Temporada(2019, pilotos, escuderias);
		this.getListaTemporadas().add(t);
		
		// Añadir carrera 1
		Carrera c = new Carrera(circuitos.get(0), pilotos);
		t.getListaCarreras().add(c);
		
		// Simular Carrera (carrera 1)
		Integer carreraActual = 0;
		t.getListaCarreras().get(carreraActual).simularCarrera();
		t.getListaCarreras().get(carreraActual).ordenarClasificacionCarrera();
		t.getListaCarreras().get(carreraActual).repartirPuntos(t.getPuntosPiloto());
//				t.getListaCarreras().get(carreraActual).actualizarPuntosEscuderia(t.getPuntosEscuderia(), t.getPuntosPiloto());
		t.getListaCarreras().get(carreraActual).repartirDinero(t.getPuntosEscuderia());
				
		// Comprobación (carrera 1)
		System.out.println("Resultado Carrera:");
		System.out.println(t.getListaCarreras().get(carreraActual).getListaPilotos());
		System.out.println(t.getListaCarreras().get(carreraActual).getListaTiempos());
		

//		//INFO ARRIBA
//		Label etiqueta = new Label(Circuito.crearCircuitosPredeterminados().get(i).toString());
//		pSuperior.add(etiqueta); 
//		getContentPane().add(pInferior, BorderLayout.SOUTH);
//		getContentPane().add(pSuperior, BorderLayout.NORTH); 
//		pInferior.add(bok);
//		
//		i++;
		

		
		
		//Creamos dos paneles para cada piloto de la escuderia
		JPanel pCentral = new JPanel();
		JPanel pDerecha = new JPanel();
		JPanel pIzquierda = new JPanel();
		JPanel pDerechaSupe = new JPanel();
		JPanel pIzquierdaSupe = new JPanel();
//			Label eti = new Label("PILOTO 1");
//			Label eti2 = new Label("PILOTO 2");
//			pDerechaSupe.add(eti2);
//			pIzquierdaSupe.add(eti);
//			pDerecha.add(pDerechaSupe);
//			pCentral.setLayout(new GridLayout(2,1));
//			pIzquierda.add(pIzquierdaSupe);
		
		
		
		
//		pIzquierda.setBackground(Color.DARK_GRAY);
//		pDerecha.setBackground(Color.GRAY);
//		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		pCentral.setLayout(new GridLayout(1,2));
//		pCentral.add(pIzquierda);
//		pCentral.add(pDerecha);
//		getContentPane().add(pCentral);
//		Font font = new Font("Verdana", Font.BOLD, 39);
//		//Imprimimos los datos de los pilotos con sus atributos _PROVISIONAL 
//		String nombre_escuderia_seleccionada = Trayectoria.getPiloto().getCoche().nombre;
//		for ( Escuderia escuderia : Escuderia.crearEscuderiasPredeterminadas(initDatos.initPilotos())) {
//			if (nombre_escuderia_seleccionada.equals(escuderia.nombre)) {
//				Escuderia escuderia_seleccionada = escuderia;
//				
//				Piloto p1 = escuderia_seleccionada.getPiloto1();
//				Piloto p2 = escuderia_seleccionada.getPiloto2();
//				
//				JTextArea piloto1 = new JTextArea(p1.toString2());
//				piloto1.setEditable( false );
//				piloto1.setBackground(Color.DARK_GRAY);
//				pIzquierda.add(piloto1);
//				piloto1.setFont(font);
//			
//				
//				JTextArea piloto2 = new JTextArea(p2.toString2());
//				piloto2.setEditable( false );
//				piloto2.setBackground(Color.GRAY);
//				pDerecha.add(piloto2);
//				piloto2.setFont(font);
//			}
//		}
		
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VentanaClasifCarrera.setVisible( true );
				
			}			
		}); 		
	}
	public ArrayList<Temporada> getListaTemporadas() {
		return listaTemporadas;
	}

	public void setListaTemporadas(ArrayList<Temporada> listaTemporadas) {
		this.listaTemporadas = listaTemporadas;
	}
}
