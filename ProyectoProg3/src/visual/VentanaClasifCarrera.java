package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import elementos.Piloto;
import elementos.Temporada;
import elementos.Trayectoria;

/**Ventana en la que se mostraran los resultados de la carrera
 * con los pilotos clasificados por el tiempo que han logrado
 * y los puntos obtenidos tras finalizar la carrera
 */ 

public class VentanaClasifCarrera extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	private Object [][] data = {{}};
	private String fondo = "/img/fondotrayectoria.png";

	
	public VentanaClasifCarrera(JFrame m) {
		MenuPrincipalTrayectoria = m;
		setTitle(" CLASIFICACIÓN DE LA CARRERA ");
		setSize(m.getWidth(), m.getHeight());
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
		
		//CREACION JTABLE DONDE SE VAN A INTRODUCIR LOS RESULTADOS OBTENIDOS 
		//EN LA CARRERA
		String [] columnNames = {"Posicion", "Piloto", "Tiempo", "Puntos"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

		for (int row = 0; row < data.length; row++) {
			
			tableModel.addRow(data[row]);
		}		
		
		JTable tabla = new JTable() {
			
			//HACIENDO AQU� MISMO Source->Add/Implement methods
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
				if (column == 2) {
					return Double.class;
				}
				if (column == 3) {
					return Integer.class;
				}
				return super.getClass();
			} 			
		};
		
		tabla.setModel(tableModel);
		
		//AÑADIMOS LOS DATOS A SU COLUMNA CORRESPONDIENTE
		
		
		//ORDENAR
//		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
//		tabla.setRowSorter(sorter);

//		JScrollPane tablaPane = new JScrollPane(tabla);
//		panelPrincipal.add(tablaPane);

		VentanaClasifCarrera.this.add(panelPrincipal);
		VentanaClasifCarrera.this.setVisible(true);	
		
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
		
				
//		Integer carreraActual = 0;
//		Temporada.this.listaCarreras.get(carreraActual).getListaPilotos();
//		Temporada.this.listaCarreras.get(carreraActual).getListaTiempos();
//		Temporada.this.listaCarreras.get(carreraActual).getListaPilotos();
//
//		public getDatos(Integer puesto, String piloto, Double tiempo, Integer puntos) {
//			tableModel.addRow(puesto);
//			tableModel.addRow(piloto);
//			tableModel.addRow(tiempo);
//			tableModel.addRow(puntos);
//		
	
		
	}
}
