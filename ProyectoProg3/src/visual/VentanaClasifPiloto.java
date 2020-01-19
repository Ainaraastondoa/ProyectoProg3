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
import javax.swing.JScrollPane;
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
	
	private static final long serialVersionUID = 1L;
	// HACER JTable
//	JFrame VentanaClasifCarrera;
	private Object [][] data = {{}};
	private String fondo = "/img/fondoayuda.png";
	
	public VentanaClasifPiloto(Temporada temp, int modoJuego) { // 0 para trayectoria, 1 para temporada
//		VentanaClasifCarrera = v;
		setTitle(" CLASIFICACIÓN PILOTOS ");
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
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
		
		
		//CREACION JTABLE DONDE SE VAN A INTRODUCIR LOS PUNTOS
		//DE LOS PILOTOS
		String [] columnNames = {"PILOTO", "PUNTOS OBTENIDOS"};
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
		panelPrincipal.add(tablaPane);
		
		tabla.setVisible(true);
	
		
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
//		JPanel pCentral = new JPanel();
//		getContentPane().add(pCentral);

	
		//BOTON OK 
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasifEscuderia clasEscuderia = new VentanaClasifEscuderia( temp, modoJuego );
				clasEscuderia.setLocation( getLocation() );
				clasEscuderia.setSize( getSize() );
				clasEscuderia.setVisible( true );
				VentanaClasifPiloto.this.dispose();
			}
			
		});
	}

}
