package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondoayuda.png";
	
	public VentanaAyuda(JFrame v) {
		VentanaInicio = v; 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(v.getWidth(), v.getHeight()); 
		
		//FONDO DE LA VENTANA
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		//CREACI�N DEL PANEL QUE ALBERGA EL TEXTO Y EL BOTON
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(new FlowLayout());
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		//TEXTO
		JTextArea texto = new JTextArea(//"AYUDA\r\n" + 
				"\r\n" + 
				"	MODO TRAYECTORIA: Modo de juego que consiste en gestionar a una escuder�a de F1 a lo largo de 10 temporadas completas. " +
				"\r\n" +
				"	Ejerce de director y controla el desarrollo del monoplaza a lo largo de las carreras.\r\n" + 
				"\r\n" + 
				"	La primera vez que entras en el Modo Trayectoria deber�s elegir una entre las 10 escuder�as participantes en la F1, " +
				"\r\n" +
				"	tras ello, pasar�s a la ventana principal de Trayectoria.\r\n " +
				"\r\n" +
				"	A partir de ese momento, cada vez que entres al Modo Trayectoria, ser�s enviado a la ventana principal " +
				"\r\n" +
				"	tambi�n al no tener que volver elegir una escuder�a.\r\n" + 
				"\r\n" + 
				"	Una vez en la ventana principal, existen varias opciones: Carrera, Piloto, Coche, Clasificaci�n y Volver.\r\n" + 
				"\r\n" + 
				"	El bot�n Carrera simula la siguiente carrera en el calendario de la temporada y te muestra los resultados de esta, " +
				"\r\n" +
				"	con su actualizaci�n correspondiente en las clasificaciones de pilotos y equipos.\r\n" + 
				"\r\n" + 
				"	El bot�n Piloto permite visualizar los atributos de los dos pilotos de tu escuder�a: consistencia, adelantar, " +
				"\r\n" +
				"	defender, rendimiento a 1 vuelta y conducci�n en mojado.\r\n" + 
				"\r\n" + 
				"	El bot�n Coche permite ver al �rbol de mejoras del coche de la escuder�a y seleccionar las mejoras que queremos " +
				"\r\n" +
				"	realizar en funci�n de nuestro presupuesto.\r\n" + 
				"\r\n" + 
				"	El bot�n Clasificaci�n nos muestra las clasificaciones de pilotos y escuder�as de la temporada actual.\r\n" + 
				"\r\n" + 
				"	El bot�n Volver te devuelve al men� principal del juego.\r\n" + 
				"\r\n" + 
				"	UN JUGADOR: Modos de juego disponibles pr�ximamente\r\n" + 
				"\r\n" + 
				"	MULTIJUGADOR: Modos de juego disponibles pr�ximamente");
		Font fuente = new Font("Agency FB", Font.BOLD, 18);
		texto.setFont(fuente);
		texto.setForeground(Color.white);
		texto.setBackground(new Color(0,0,0,0));
		texto.setEditable( false );
		texto.setBounds(this.getWidth()/15, this.getHeight()/20, (this.getWidth()/15)*14, (this.getHeight()/20) * 15);
		pCentral.add(texto, BorderLayout.PAGE_START);
		
		//BOTON VOLVER
		JButton bVolver = new JButton( "Volver" ); 
//		bVolver.setLayout(null);
		bVolver.setBounds((v.getWidth()/20) * 9, (int) ((v.getHeight()/24) * 20.5), (v.getWidth()/20) * 2, (v.getHeight()/24) * 2);
		pCentral.add(bVolver, BorderLayout.PAGE_END);
		
		//ESCUCHADORES
		bVolver.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				VentanaInicio.setVisible( true );				
			}
		});

		addWindowListener(new WindowAdapter() {
			
			public void WindowClosed(WindowEvent e) {
				VentanaInicio.setVisible( true );
			}	
		});
	}
}
