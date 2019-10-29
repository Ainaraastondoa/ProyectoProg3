package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.Temporada;

/**Ventana en la que se mostrará a los pilotos 
 *clasificados según su puntuación 
 */

public class VentanaClasifPiloto extends JFrame{
	
	JFrame VentanaClasifCarrera;

	
	public VentanaClasifPiloto(JFrame v) {
		VentanaClasifCarrera = v; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
		//Imprimimos a sus pilotos con sus respectivos puntos 
//		JTextArea pilotos = new JTextArea(Temporada.crearPuntosPiloto().get(0).toString());
//		getContentPane().add(pilotos, BorderLayout.CENTER);
		
		//Creamos el panel de la clasificacion
		JPanel pCentral = new JPanel();
		
		pCentral.setBackground(Color.gray);
		pCentral.setLayout(new GridLayout(1,2));
		getContentPane().add(pCentral);
		Font font = new Font("Verdana", Font.BOLD, 39);

//		JTextArea pilotos = new JTextArea(Temporada.puntosPiloto..toString());
//		piloto1.setEditable( false );
//		piloto1.setBackground(Color.DARK_GRAY);
//		pIzquierda.add(piloto1);
//		piloto1.setFont(font);
	
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
