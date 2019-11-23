package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import elementos.Piloto;
import elementos.Trayectoria;
import elementos.initDatos;

public class VentanaSelPiloto extends JFrame{
	
	JFrame VentanaInicial;
	
	public VentanaSelPiloto(JFrame m) {		
		VentanaInicial = m;
		JButton bVolver = new JButton("Volver");
		JPanel pInferior = new JPanel();
		JPanel pCentral = new JPanel();
		JPanel pSuperior = new JPanel();
		Label etiqueta = new Label("Selecciona Piloto");
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pCentral, BorderLayout.CENTER);
		getContentPane().add(pSuperior, BorderLayout.NORTH); 
		pSuperior.add(etiqueta); 
		pInferior.add(bVolver);
		
		ArrayList<Piloto> listaPilotos = initDatos.initPilotos();
		for (Piloto piloto : listaPilotos) {
			JButton bPiloto = new JButton(piloto.getNombre());
			pCentral.add(bPiloto);

			bPiloto.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MenuPrincipalTrayectoria menu = new MenuPrincipalTrayectoria( VentanaSelPiloto.this );
					menu.setLocation( getLocation() );
					menu.setSize( getSize() );
					menu.setVisible( true );
					VentanaSelPiloto.this.setVisible( false );
					//Piloto seleccionado
					Trayectoria.setPiloto(piloto);
				}
				
			});
		}
		pCentral.setBackground(Color.DARK_GRAY);
		pCentral.setLayout(new GridLayout(4,5));
		
		//Escuchadores
		bVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VentanaInicial.setVisible( true );
				
			}			
		}); 
		
	}
}
