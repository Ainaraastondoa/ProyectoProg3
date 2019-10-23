package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Ventana en la que se mostrarán las escuderias
 *clasificadas según su puntuación 
 */

public class VentanaClasifEscuderia extends JFrame{
	
	public VentanaClasifEscuderia() {
		
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
	}

}
