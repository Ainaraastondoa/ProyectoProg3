package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Clase para la ventana de ayuda en la que explicaremos
 * las reglas del simmulador 
 *
 */

public class VentanaAyuda extends JFrame{
	
	JFrame VentanaInicio; 
	
	public VentanaAyuda(JFrame v) {
		VentanaInicio = v; 
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 400, 300 );
		JButton bVolver = new JButton( "Volver" ); 
		JPanel pInferior = new JPanel();
		pInferior.setBackground( Color.LIGHT_GRAY );
		getContentPane().add( pInferior, BorderLayout.SOUTH); 
		pInferior.add( bVolver ); 
		
		addWindowListener(new WindowAdapter() {
			
			public void WindowClosed(WindowEvent e) {
				VentanaInicio.setVisible( true );
			}	
	});
		bVolver.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				VentanaInicio.setVisible( true );
				
			}

}); 
}
}
