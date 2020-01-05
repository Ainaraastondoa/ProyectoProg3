package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import elementos.BD;
import elementos.Carrera;
import elementos.Circuito;
import elementos.Piloto;


/**Esta clase es para visualizar el menu principal
 * del modo trayectoria
 */ 

public class MenuPrincipalTrayectoria extends JFrame{
	
	JFrame VentanaInicio;
	

	
	public MenuPrincipalTrayectoria(JFrame v) throws SQLException {
		VentanaInicio = v;  
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize( 400, 300 );
		JButton bCarrera = new JButton( "Carrera" );
		JButton bCoche = new JButton( "Coche"); 
		JButton bPilotos = new JButton( "Pilotos" );
		JButton bClasif = new JButton( "Clasificacion");
		JPanel pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout( 3, 1 ));
		pCentral.add(bCarrera);
		pCentral.add(bCoche);
		pCentral.add(bPilotos);
		pCentral.add(bClasif);
		JButton bVolver = new JButton( "Volver" ); 
		JPanel pInferior = new JPanel();
		pInferior.setBackground( Color.LIGHT_GRAY );
		getContentPane().add( pInferior, BorderLayout.SOUTH); 
		pInferior.add( bVolver );
		
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();
		
		//Escuchadores
		bCarrera.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaCarrera vCarrera;
				try {
					vCarrera = new VentanaCarrera ( MenuPrincipalTrayectoria.this);
					vCarrera.setLocation(getLocation());
					vCarrera.setSize(getSize());
					vCarrera.setVisible(true);
					MenuPrincipalTrayectoria.this.setVisible(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		});
		
		bCoche.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCoche coche;
				try {
					coche = new VentanaCoche( MenuPrincipalTrayectoria.this );
					coche.setLocation( getLocation() );
					coche.setSize( getSize() );
					coche.setVisible( true );
					MenuPrincipalTrayectoria.this.setVisible( false );
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
		});
		
		bPilotos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPiloto pilotos;
				try {
					pilotos = new VentanaPiloto( MenuPrincipalTrayectoria.this );
					pilotos.setLocation( getLocation() );
					pilotos.setSize( getSize() );
					pilotos.setVisible( true );
					MenuPrincipalTrayectoria.this.setVisible( false );	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
		});
		
		bClasif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasifCarrera clasifCarre = new VentanaClasifCarrera( MenuPrincipalTrayectoria.this );
				clasifCarre.setLocation( getLocation() );
				clasifCarre.setSize( getSize() );
				clasifCarre.setVisible( true );
				MenuPrincipalTrayectoria.this.setVisible( false );
			}
			
		}); 
		
		
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
