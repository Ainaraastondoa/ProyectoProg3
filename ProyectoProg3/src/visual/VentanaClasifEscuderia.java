package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import elementos.Temporada;

/**Ventana en la que se mostrar�n las escuderias
 *clasificadas seg�n su puntuaci�n 
 */ 

public class VentanaClasifEscuderia extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private String fondo = "/img/fondoayuda.png";
	private Object [][] data = {{}};
	// HACER JTable
//	JFrame MenuPrincipalTrayectoria; 
	
	public VentanaClasifEscuderia(Temporada temp, int modoJuego) {
//		MenuPrincipalTrayectoria = v;
		setTitle(" CLASIFICACIÓN ESCUDERIAS ");
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//FONDO DE LA VENTANA
		
		PanelConImagenFondo imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		//CREACION DE PANELES Y BOTONES

		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension(1600, 900));
		pCentral.setOpaque(false);
		getContentPane().add(pCentral);
		
		JButton bok = new JButton("Ok");
		pCentral.add(bok, BorderLayout.SOUTH);
		
		//CREACION JTABLE DONDE SE VAN A INTRODUCIR LOS PUNTOS DE 
		//CADA ESCUDERIA
		
		String [] columnNames = {"ESCUDERIA", "PUNTOS OBTENIDOS"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

		for (int row = 0; row < data.length; row++) {
			
			tableModel.addRow(data[row]);
		}		
		
		JTable tabla = new JTable() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 0) {
					return Integer.class;
				}
				if (column == 1) {
					return String.class;
				}
				
				return super.getClass();
			} 			
		};
		
		tabla.setModel(tableModel);
		
		JScrollPane tablaPane = new JScrollPane(tabla);
		pCentral.add(tablaPane);
		
		tabla.setVisible(true);
	
		
		//INTRODUCIMOS LOS DATOS CORRESPONDIENTES EN EL JTABLE

		
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (modoJuego==0) { // Modo trayectoria
//					MenuPrincipalTrayectoria menu = new MenuPrincipalTrayectoria( VentanaClasifEscuderia.this );
//					menu.setLocation( getLocation() );
//					menu.setSize( getSize() );
//					menu.setVisible( true );
					VentanaClasifEscuderia.this.setVisible( false );
				} else { // Modo temporada
					MenuPrincipalTemporada menu = new MenuPrincipalTemporada(temp, modoJuego);
					menu.setLocation( getLocation() );
					menu.setSize( getSize() );
					menu.setVisible( true );
					VentanaClasifEscuderia.this.dispose();
				}
			}
			
		});
	}

}
