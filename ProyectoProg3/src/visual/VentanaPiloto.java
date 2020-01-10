package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import elementos.*;

 
/**Ventana en la que se muestran los pilotos de la escuderia con sus 
 * respectivos atributos
 */

public class VentanaPiloto extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	Piloto piloto; 
	private JButton bVolver; 
	
	public VentanaPiloto(JFrame m) throws SQLException {
		MenuPrincipalTrayectoria = m;
		setTitle(" Pilotos ");
		setSize(m.getWidth(), m.getHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//CREACION DEL PANEL QUE ALBERGA LOS DATOS Y BOTONES
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		//Creamos dos paneles para cada piloto de la escuderia
		JPanel pCentral = new JPanel();
		JPanel pDerecha = new JPanel();
		JPanel pIzquierda = new JPanel();
		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(1,2));
		pCentral.add(pIzquierda);
		pCentral.add(pDerecha);
		getContentPane().add(pCentral);
		
		//FUENTE
		Font font = new Font("Verdana", Font.BOLD, 39);
		
		//OBTENEMOS LOS DATOS DE LOS PILOTOS DE LA ESCUDERIA SELECCIONADA
		Escuderia escuderia_seleccionada = Trayectoria.getEscuderia();
		Piloto p1 = escuderia_seleccionada.getPiloto1();
		Piloto p2 = escuderia_seleccionada.getPiloto2();
		
//		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
//		Statement st = con.createStatement();
		//ArrayList<Escuderia> escuderias = BD.listaEscuderiasSelect(st);
		//Imprimimos los datos de los pilotos con sus atributos _PROVISIONAL 
		
//		for (Escuderia escuderia : escuderias) {
//			if (escuderia_seleccionada.equals(escuderia)) {	
//			}
//		}
		
		//IMPRIMIMOS LOS DATOS DEL PRIMER PILOTO
		JTextArea piloto1 = new JTextArea(p1.toString2());
		piloto1.setEditable( false );
		piloto1.setBackground(Color.LIGHT_GRAY);
		pIzquierda.add(piloto1);
		piloto1.setFont(font);
	
		//IMPRIMIMOS LOS DATOS DEL SEGUNDO PILOTO
		JTextArea piloto2 = new JTextArea(p2.toString2());
		piloto2.setEditable( false );
		piloto2.setBackground(Color.LIGHT_GRAY);
		pDerecha.add(piloto2);
		piloto2.setFont(font);
		
		//BOTON VOLVER
		bVolver = new JButton(); 
		bVolver.setContentAreaFilled(false);
		bVolver. setOpaque(false);
		
		//Escuchadores
		bVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MenuPrincipalTrayectoria.setVisible( true );
				
			}			
		}); 		
	}
}
