package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Ventana en la que se mostrar�n las escuderias
 *clasificadas seg�n su puntuaci�n 
 */

public class VentanaClasifEscuderia extends JFrame{
	
	public VentanaClasifEscuderia() {
		
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
	}

}
