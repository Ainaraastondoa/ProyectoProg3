package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.*;


/**Ventana en la que se muestran los pilotos de la escuderia con sus 
 * respectivos atributos
 */

public class VentanaPiloto extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	Piloto piloto; 
	
	public VentanaPiloto(JFrame m) {
		MenuPrincipalTrayectoria = m; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		Label etiqueta = new Label("ESCUDERIA");
		pSuperior.add(etiqueta); 
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pSuperior, BorderLayout.NORTH); 
		pInferior.add(bok);
		//Creamos dos paneles para cada piloto de la escuderia
		JPanel pCentral = new JPanel();
		JPanel pDerecha = new JPanel();
		JPanel pIzquierda = new JPanel();
		JPanel pDerechaSupe = new JPanel();
		JPanel pIzquierdaSupe = new JPanel();
//		Label eti = new Label("PILOTO 1");
//		Label eti2 = new Label("PILOTO 2");
//		pDerechaSupe.add(eti2);
//		pIzquierdaSupe.add(eti);
//		pDerecha.add(pDerechaSupe);
//		pCentral.setLayout(new GridLayout(2,1));
//		pIzquierda.add(pIzquierdaSupe);
		pIzquierda.setBackground(Color.DARK_GRAY);
		pDerecha.setBackground(Color.GRAY);
		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(1,2));
		pCentral.add(pIzquierda);
		pCentral.add(pDerecha);
		getContentPane().add(pCentral);
		Font font = new Font("Verdana", Font.BOLD, 39);
		//Imprimimos los datos de los pilotos con sus atributos _PROVISIONAL 
		Escuderia escuderia_seleccionada = Trayectoria.getEscuderia();
		Piloto p1 = escuderia_seleccionada.getPiloto1();
		Piloto p2 = escuderia_seleccionada.getPiloto2();	
		
		JTextArea piloto1 = new JTextArea(p1.toString());
		piloto1.setEditable( false );
		piloto1.setBackground(Color.DARK_GRAY);
		pIzquierda.add(piloto1);
		piloto1.setFont(font);
	
		
		JTextArea piloto2 = new JTextArea(p2.toString());
		piloto2.setEditable( false );
		piloto2.setBackground(Color.GRAY);
		pDerecha.add(piloto2);
		piloto2.setFont(font);

		
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MenuPrincipalTrayectoria.setVisible( true );
				
			}
			
		}); 
		
	}
	

}
