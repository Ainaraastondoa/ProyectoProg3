package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Esta clase es para visualizar el menu principal
 * del modo trayectoria
 */

public class MenuPrincipalTrayectoria extends JFrame{
	
	JFrame VentanaInicio; 
	
	public MenuPrincipalTrayectoria(JFrame v) {
		VentanaInicio = v; 
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 400, 300 );
		JButton bCarrera = new JButton( "Carrera" );
		JButton bCoche = new JButton( "Coche"); 
		JButton bPilotos = new JButton( "Pilotos" );
		JButton bClasif = new JButton( "Clasificacion");
		getContentPane().setLayout( new GridLayout( 3, 1 ) );
		getContentPane().add( bCarrera );
		getContentPane().add( bCoche );
		getContentPane().add( bPilotos);
		getContentPane().add( bClasif );
		JButton bVolver = new JButton( "Volver" ); 
		JPanel pInferior = new JPanel();
		pInferior.setBackground( Color.LIGHT_GRAY );
		getContentPane().add( pInferior, BorderLayout.SOUTH); 
		pInferior.add( bVolver );
		
		//Escuchadores
		bCarrera.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		
		bCoche.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		bPilotos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		bClasif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		}); 
		
		
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
