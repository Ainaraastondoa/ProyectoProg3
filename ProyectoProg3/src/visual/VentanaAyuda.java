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
		setResizable(false);
		
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
				"	MODO TRAYECTORIA: Modo de juego que consiste en gestionar a una escudería de F1 a lo largo de 10 temporadas completas. " +
				"\r\n" +
				"	Ejerce de director y controla el desarrollo del monoplaza a lo largo de las carreras.\r\n" + 
				"\r\n" + 
				"	La primera vez que entras en el Modo Trayectoria deberás elegir una entre las 10 escuderías participantes en la F1, " +
				"\r\n" +
				"	tras ello, pasarás a la ventana principal de Trayectoria.\r\n " +
				"\r\n" +
				"	A partir de ese momento, cada vez que entres al Modo Trayectoria, serás enviado a la ventana principal " +
				"\r\n" +
				"	también al no tener que volver elegir una escudería.\r\n" + 
				"\r\n" + 
				"	Una vez en la ventana principal, existen varias opciones: Carrera, Piloto, Coche, Clasificación y Volver.\r\n" + 
				"\r\n" + 
				"	El botón Carrera simula la siguiente carrera en el calendario de la temporada y te muestra los resultados de esta, " +
				"\r\n" +
				"	con su actualización correspondiente en las clasificaciones de pilotos y equipos.\r\n" + 
				"\r\n" + 
				"	El botón Piloto permite visualizar los atributos de los dos pilotos de tu escudería: consistencia, adelantar, " +
				"\r\n" +
				"	defender, rendimiento a 1 vuelta y conducción en mojado.\r\n" + 
				"\r\n" + 
				"	El botón Coche permite ver al árbol de mejoras del coche de la escudería y seleccionar las mejoras que queremos " +
				"\r\n" +
				"	realizar en función de nuestro presupuesto.\r\n" + 
				"\r\n" + 
				"	El botón Clasificación nos muestra las clasificaciones de pilotos y escuderías de la temporada actual.\r\n" + 
				"\r\n" + 
				"	El botón Volver te devuelve al menú principal del juego.\r\n" + 
				"\r\n" + 
				"	UN JUGADOR: Modos de juego disponibles próximamente\r\n" + 
				"\r\n" + 
				"	MULTIJUGADOR: Modos de juego disponibles próximamente");
		Font fuente = new Font("Arial", Font.BOLD, 20);
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
