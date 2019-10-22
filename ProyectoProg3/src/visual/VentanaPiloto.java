package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import elementos.Piloto;

/**Ventana en la que se muestran los pilotos de la escuderia con sus 
 * respectivos atributos
 */

public class VentanaPiloto extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	
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
		Label eti = new Label("PILOTO 1");
		Label eti2 = new Label("PILOTO 2");
		pDerechaSupe.add(eti2);
		pIzquierdaSupe.add(eti);
		pDerecha.add(pDerechaSupe);
		pIzquierda.add(pIzquierdaSupe); 
		pIzquierda.setBackground(Color.DARK_GRAY);
		pDerecha.setBackground(Color.GRAY);
		pIzquierda.setLayout(new FlowLayout(FlowLayout.LEFT));
		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(1,2));
		pCentral.add(pIzquierda);
		pCentral.add(pDerecha);
		getContentPane().add(pCentral);
		//Imprimimos a los pilotos de la escuderia con sus atributos
		
		
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
