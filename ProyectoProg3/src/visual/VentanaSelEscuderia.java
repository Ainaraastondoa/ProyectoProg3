package visual;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;

import elementos.BD;
import elementos.Escuderia;
import elementos.Piloto;
import elementos.Trayectoria;

public class VentanaSelEscuderia extends JFrame{
	JFrame VentanaInicial;
	 
	public VentanaSelEscuderia(JFrame m) throws SQLException {		
		VentanaInicial = m;
		JButton bVolver = new JButton("Volver");
		JPanel pInferior = new JPanel();
		JPanel pCentral = new JPanel();
		JPanel pSuperior = new JPanel();
		Label etiqueta = new Label("Selecciona tu escuderia");
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pCentral, BorderLayout.CENTER);
		getContentPane().add(pSuperior, BorderLayout.NORTH); 
		pSuperior.add(etiqueta); 
		pInferior.add(bVolver);
		
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();
		
		ArrayList<Escuderia> listaEscuderias = BD.listaEscuderiasSelect(st);
		for (Escuderia escuderia : listaEscuderias) {
			JButton bEscuderia = new JButton(escuderia.getNombre());
			pCentral.add(bEscuderia);

			bEscuderia.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MenuPrincipalTrayectoria menu = new MenuPrincipalTrayectoria( VentanaSelEscuderia.this );
					menu.setLocation( getLocation() );
					menu.setSize( getSize() );
					menu.setVisible( true );
					VentanaSelEscuderia.this.setVisible( false );
					//Piloto seleccionado
					Trayectoria.setPiloto(escuderia.piloto1);;
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
