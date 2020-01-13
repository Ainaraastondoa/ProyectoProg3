package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import elementos.BD;
import elementos.Piloto;
import elementos.Temporada;
import elementos.Trayectoria;

/**Ventana en la que se mostrar� a los pilotos 
 *clasificados seg�n su puntuaci�n 
 */ 

public class VentanaClasifPiloto extends JFrame{
	
	// HACER JTable
	JFrame VentanaClasifCarrera;
	private Object [][] data = {{}};
	private String fondo = "/img/fondotrayectoria.png";
	
	public VentanaClasifPiloto(JFrame v) {
		VentanaClasifCarrera = v;
		setTitle(" CLASIFICACIÓN PILOTOS ");
		setSize(v.getWidth(), v.getHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//FONDO DE LA VENTANA
		
		PanelConImagenFondo imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());

		//CREACION DE PANELES Y BOTONES
		
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
		
		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.NORTH);
		
		//CREACION JTABLE DONDE SE VAN A INTRODUCIR LOS RESULTADOS OBTENIDOS 
		//EN LA CARRERA
		String [] columnNames = {"PILOTO", "PUNTOS OBTENIDOS"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

		for (int row = 0; row < data.length; row++) {
			
			tableModel.addRow(data[row]);
		}		
		
		JTable tabla = new JTable() {
			
			//HACIENDO AQU� MISMO Source->Add/Implement methods
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			@Override
			public Class<?> getColumnClass(int column) {
				// TODO Auto-generated method stub
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
		
		//INTRODUCIMOS LOS DATOS CORRESPONDIENTES EN CADA COLUMNA
		 
		
		
//		//CUANDO SE CIERRE EL JUEGO PARA GUARDAR LA CLASIFICACION
//		for (Piloto piloto : Trayectoria.listaPilotos) {
//			Integer i = 0;
//			Statement st = con.createStatement();
//			while (i < 20) {
//			BD.insertclasificacionpilotos(st, Trayectoria.listaPilotos.indexOf(piloto) + 1, Temporada.crearPuntosPiloto().get(piloto), piloto.toString(), piloto.calcularPuntosPiloto())
//			}		
//		}
		
		//Creamos el panel de la clasificacion
		JPanel pCentral = new JPanel();
//		JPanel pDerecha = new JPanel();
//		JPanel pIzquierda = new JPanel();
//		pIzquierda.setBackground(Color.DARK_GRAY);
//		pDerecha.setBackground(Color.GRAY);
//		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		pCentral.setLayout(new GridLayout(1,2));
//		pCentral.add(pIzquierda);
//		pCentral.add(pDerecha);
		getContentPane().add(pCentral);

	
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasifEscuderia clasEscuderia = new VentanaClasifEscuderia( VentanaClasifPiloto.this );
				clasEscuderia.setLocation( getLocation() );
				clasEscuderia.setSize( getSize() );
				clasEscuderia.setVisible( true );
				VentanaClasifPiloto.this.setVisible( false );
				
			}
			
		});
	}

}
