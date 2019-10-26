package visual;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Ventana en la que se mostraran los resultados de la carrera
 * con los pilotos clasificados por el tiempo que han logrado
 * y los puntos obtenidos tras finalizar la carrera
 */

public class VentanaClasifCarrera extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	
	public VentanaClasifCarrera(JFrame m) {
		MenuPrincipalTrayectoria = m; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
		//Imprimimos en la ventana a los pilotos con el tiempo que han
		//obtenido en la carrera y sus respectivos puntos
		
		
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasifPiloto clasPilotos = new VentanaClasifPiloto( VentanaClasifCarrera.this );
				clasPilotos.setLocation( getLocation() );
				clasPilotos.setSize( getSize() );
				clasPilotos.setVisible( true );
				VentanaClasifCarrera.this.setVisible( false );
				
			}
			
		}); 
		
		
	}

}
