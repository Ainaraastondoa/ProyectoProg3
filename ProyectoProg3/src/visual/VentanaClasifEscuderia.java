package visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Ventana en la que se mostrarán las escuderias
 *clasificadas según su puntuación 
 */

public class VentanaClasifEscuderia extends JFrame{
	
	JFrame MenuPrincipalTrayectoria; 
	
	public VentanaClasifEscuderia(JFrame v) {
		MenuPrincipalTrayectoria = v; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
		//Imprimimos las escuderias con sus respectivos puntos
		
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MenuPrincipalTrayectoria menu = new MenuPrincipalTrayectoria( VentanaClasifEscuderia.this );
				menu.setLocation( getLocation() );
				menu.setSize( getSize() );
				menu.setVisible( true );
				VentanaClasifEscuderia.this.setVisible( false );
				
				
			}
			
		});
	}

}
