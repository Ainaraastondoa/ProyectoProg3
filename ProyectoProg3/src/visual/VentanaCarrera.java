package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.Carrera;
import elementos.Circuito;
import elementos.Escuderia;
import elementos.Piloto;
import elementos.Temporada;
import elementos.Trayectoria;
import elementos.initDatos;

public class VentanaCarrera extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	JFrame VentanaClasifCarrera;
	Piloto piloto; 
	
	public VentanaCarrera(JFrame m) {
		VentanaClasifCarrera = m; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		
		ArrayList<Circuito> circuitos = Circuito.crearCircuitosPredeterminados();
		ArrayList<Piloto> pilotos = initDatos.initPilotos();
		
		//CARRERA
	
		int i = 0;
		Carrera c = new Carrera(circuitos.get(i), pilotos);
		c.simularCarrera();
		
		// Creación de Trayectoria
		ArrayList<Circuito> listaCircuitos = Circuito.crearCircuitosPredeterminados();
		
		// Añadir temporada
		ArrayList<Carrera> listaCarreras = Carrera.crearCarreras(listaCircuitos, pilotos);
		
		//if ( i >= 20) {
		Temporada t = new Temporada(2019, listaCarreras, pilotos, Escuderia.crearEscuderiasPredeterminadas(initDatos.initPilotos()));
		//this.getListaTemporadas().add(t);
		
		
		// Simular Carrera (carrera 1)
		t.getListaCarreras().get(i).simularCarrera();
		t.getListaCarreras().get(i).ordenarClasificacionCarrera();
		t.getListaCarreras().get(i).repartirPuntos(t.getPuntosPiloto());
		t.getListaCarreras().get(i).actualizarPuntosEscuderia(t.getPuntosEscuderia(), t.getPuntosPiloto());
		t.getListaCarreras().get(i).repartirDinero(t.getPuntosEscuderia());
		
		// Comprobación (carrera 1)
		System.out.println("Resultado Carrera:");
		System.out.println(t.getListaCarreras().get(i).getListaPilotos());
		System.out.println(t.getListaCarreras().get(i).getListaTiempos());
		

		//INFO ARRIBA
		Label etiqueta = new Label(Circuito.crearCircuitosPredeterminados().get(i).toString());
		pSuperior.add(etiqueta); 
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pSuperior, BorderLayout.NORTH); 
		pInferior.add(bok);
		
		i++;
		

		
		
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
}
