package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Ventana en la que se mostrará a los pilotos 
 *clasificados según su puntuación 
 */

public class VentanaClasifPiloto extends JFrame{
	
	public VentanaClasifPiloto() {
		
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
	}

}
