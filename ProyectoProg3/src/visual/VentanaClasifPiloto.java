package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Ventana en la que se mostrar� a los pilotos 
 *clasificados seg�n su puntuaci�n 
 */

public class VentanaClasifPiloto extends JFrame{
	
	public VentanaClasifPiloto() {
		
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
	}

}
