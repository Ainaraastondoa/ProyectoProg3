package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/** Clase para la ventana de ayuda en la que explicaremos
 * las reglas del simulador 
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
		
		JPanel pCentral = new JPanel();
		getContentPane().add( pCentral, BorderLayout.CENTER); 
		JTextArea text = new JTextArea("AYUDA\r\n" + 
				"\r\n" + 
				"	MODO TRAYECTORIA: Modo de juego que consiste en gestionar a una escudería de F1 a lo largo de 10 temporadas completas. Ejerce de director y controla el desarrollo del monoplaza a lo largo de las carreras.\r\n" + 
				"\r\n" + 
				"	La primera vez que entras en el Modo Trayectoria deberás elegir una entre las 10 escuderías participantes en la F1, tras ello, pasarás a la ventana principal de Trayectoria. A partir de ese momento, cada vez que entres al Modo Trayectoria, serás enviado a la ventana principal también al no tener que volver elegir una escudería.\r\n" + 
				"\r\n" + 
				"	Una vez en la ventana principal, existen varias opciones: Carrera, Piloto, Coche, Clasificación y Volver.\r\n" + 
				"\r\n" + 
				"	El botón Carrera simula la siguiente carrera en el calendario de la temporada y te muestra los resultados de esta, con su actualización correspondiente en las clasificaciones de pilotos y equipos.\r\n" + 
				"\r\n" + 
				"	El botón Piloto permite visualizar los atributos de los dos pilotos de tu escudería: consistencia, adelantar, defender, rendimiento a 1 vuelta y conducción en mojado.\r\n" + 
				"\r\n" + 
				"	El botón Coche permite ver al árbol de mejoras del coche de la escudería y seleccionar las mejoras que queremos realizar en función de nuestro presupuesto.\r\n" + 
				"\r\n" + 
				"	El botón Clasificación nos muestra las clasificaciones de pilotos y escuderías de la temporada actual.\r\n" + 
				"\r\n" + 
				"	El botón Volver te devuelve al menú principal del juego.\r\n" + 
				"\r\n" + 
				"	UN JUGADOR: Modos de juego disponibles próximamente\r\n" + 
				"\r\n" + 
				"	MULTIJUGADOR: Modos de juego disponibles próximamente");
		text.setEditable( false );
		pCentral.add(text); 
		
		
//		JPanel panel = new JPanel();
//		getContentPane().add(panel, BorderLayout.CENTER);
//		
//		JTextArea text = new JTextArea();
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setViewportView(text);
//		scrollPane.getPreferredSize();
//		text.setText(texto);
//		panel.add(text);
//
//		
//		panel.add(scrollPane);
//		JLabel instruccion = new JLabel(texto); 
//		panel.add(instruccion);
		
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
