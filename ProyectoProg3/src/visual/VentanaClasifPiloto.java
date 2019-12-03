package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.BD;
import elementos.Piloto;
import elementos.Temporada;
import elementos.Trayectoria;

/**Ventana en la que se mostrará a los pilotos 
 *clasificados según su puntuación 
 */

public class VentanaClasifPiloto extends JFrame{
	
	// HACER JTable
	JFrame VentanaClasifCarrera;

	
	public VentanaClasifPiloto(JFrame v) {
		VentanaClasifCarrera = v; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
		//Imprimimos a sus pilotos con sus respectivos puntos 
		
		
		//CUANDO SE CIERRE EL JUEGO PARA GUARDAR LA CLASIFICACION
		for (Piloto piloto : Trayectoria.listaPilotos) {
			Integer i = 0;
			Statement st = con.createStatement();
			while (i < 20) {
			BD.insertclasificacionpilotos(st, Trayectoria.listaPilotos.indexOf(piloto) + 1, Temporada.crearPuntosPiloto().get(piloto), piloto.toString(), piloto.calcularPuntosPiloto())
			}		
		}
		
		//Creamos el panel de la clasificacion
		JPanel pCentral = new JPanel();
		JPanel pDerecha = new JPanel();
		JPanel pIzquierda = new JPanel();
		Label eti = new Label("PILOTOS");
		Label eti2 = new Label("PUNTOS");
		JPanel pDerechaSupe = new JPanel();
		JPanel pIzquierdaSupe = new JPanel();
		pDerechaSupe.add(eti2);
		pIzquierdaSupe.add(eti);
		pDerecha.add(pDerechaSupe);
		pCentral.setLayout(new GridLayout(2,1));
		pIzquierda.add(pIzquierdaSupe);
		pIzquierda.setBackground(Color.DARK_GRAY);
		pDerecha.setBackground(Color.GRAY);
		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(1,2));
		pCentral.add(pIzquierda);
		pCentral.add(pDerecha);
		getContentPane().add(pCentral);

	
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasifEscuderia clasEscuderia = new VentanaClasifEscuderia( VentanaClasifPiloto.this );
				clasEscuderia.setLocation( getLocation() );
				clasEscuderia.setSize( getSize() );
				clasEscuderia.setVisible( true );
				VentanaClasifPiloto.this.setVisible( false );
				
			}
			
		});
	}

}
