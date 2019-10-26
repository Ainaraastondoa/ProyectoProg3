package visual;

import java.awt.BorderLayout;
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
