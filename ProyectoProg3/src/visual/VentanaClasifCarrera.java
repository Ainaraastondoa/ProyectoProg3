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

/**Ventana en la que se mostraran los resultados de la carrera
 * con los pilotos clasificados por el tiempo que han logrado
 * y los puntos obtenidos tras finalizar la carrera
 */

public class VentanaClasifCarrera extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	
	public VentanaClasifCarrera(JFrame m) {
		MenuPrincipalTrayectoria = m; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
		//Imprimimos en la ventana a los pilotos con el tiempo que han
		//obtenido en la carrera y sus respectivos puntos
		
		 //Creamos el panel de la clasificación
		JPanel pCentral = new JPanel();
		JPanel pDerecha = new JPanel();
		JPanel pIzquierda = new JPanel();
		JPanel pCentro = new JPanel();
		Label eti = new Label("PILOTOS");
		Label eti2 = new Label("PUNTOS");
		Label eti3 = new Label("TIEMPO");
		JPanel pDerechaSupe = new JPanel();
		JPanel pIzquierdaSupe = new JPanel();
		JPanel pCentroSupe = new JPanel();
		pDerechaSupe.add(eti3);
		pIzquierdaSupe.add(eti);
		pCentroSupe.add(eti2);
		pDerecha.add(pDerechaSupe);
		pIzquierda.add(pIzquierdaSupe);
		pCentro.add(pCentroSupe);
		pCentral.setLayout(new GridLayout(3,1));
		pIzquierda.setBackground(Color.DARK_GRAY);
		pDerecha.setBackground(Color.GRAY);
		pCentro.setBackground(Color.BLACK);
		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(1,3));
		pCentral.add(pIzquierda);
		pCentral.add(pDerecha);
		pCentral.add(pCentro);
		getContentPane().add(pCentral);

		
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasifPiloto clasPilotos = new VentanaClasifPiloto( VentanaClasifCarrera.this );
				clasPilotos.setLocation( getLocation() );
				clasPilotos.setSize( getSize() );
				clasPilotos.setVisible( true );
				VentanaClasifCarrera.this.setVisible( false );
				
			}
			
		}); 
		
		
	}

}
