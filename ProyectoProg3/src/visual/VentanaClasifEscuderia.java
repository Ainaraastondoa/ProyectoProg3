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

/**Ventana en la que se mostrar�n las escuderias
 *clasificadas seg�n su puntuaci�n 
 */ 

public class VentanaClasifEscuderia extends JFrame{
	
	// HACER JTable
	JFrame MenuPrincipalTrayectoria; 
	
	public VentanaClasifEscuderia(JFrame v) {
		MenuPrincipalTrayectoria = v; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
		//Imprimimos las escuderias con sus respectivos puntos
		//Creamos el panel de la clasificaci�n
		JPanel pCentral = new JPanel();
		JPanel pDerecha = new JPanel();
		JPanel pIzquierda = new JPanel();
		Label eti = new Label("ESCUDERIAS");
		Label eti2 = new Label("PUNTOS");
		JPanel pDerechaSupe = new JPanel();
		JPanel pIzquierdaSupe = new JPanel();
		pDerechaSupe.add(eti2);
		pIzquierdaSupe.add(eti);
		pDerecha.add(pDerechaSupe);
		pCentral.setLayout(new GridLayout(2,1));
		pIzquierda.add(pIzquierdaSupe);
		pIzquierda.setBackground(Color.GRAY);
		pDerecha.setBackground(Color.DARK_GRAY);
		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(1,2));
		pCentral.add(pIzquierda);
		pCentral.add(pDerecha);
		getContentPane().add(pCentral);

		
//		//Escuchadores
//		bok.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MenuPrincipalTrayectoria menu = new MenuPrincipalTrayectoria( VentanaClasifEscuderia.this );
//				menu.setLocation( getLocation() );
//				menu.setSize( getSize() );
//				menu.setVisible( true );
//				VentanaClasifEscuderia.this.setVisible( false );
//				
//				
//			}
//			
//		});
	}

}
