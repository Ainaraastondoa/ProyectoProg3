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
	
	
	// HACER JTable
	JFrame MenuPrincipalTrayectoria;
	private Object [][] data = {{}};

	
	public VentanaClasifCarrera(JFrame m) {
		MenuPrincipalTrayectoria = m; 
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.add(bok);
		
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
		
		//Imprimimos en la ventana a los pilotos con el tiempo que han
		//obtenido en la carrera y sus respectivos puntos
				
		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.NORTH);
		
		String [] columnNames = {"Posicion", "Piloto", "Tiempo", "Puntos"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
		for (int row = 0; row < data.length; row++) {
			
			tableModel.addRow(data[row]);
		}		
		JTable tabla = new JTable() {
			
			//HACIENDO AQUÍ MISMO Source->Add/Implement methods
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
				if (column == 2) {
					return Double.class;
				}
				if (column == 3) {
					return Integer.class;
				}
				return super.getClass();
			} 			
		};
		
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
	
		tabla.setModel(tableModel);

		JScrollPane tablaPane = new JScrollPane(tabla);
		panelPrincipal.add(tablaPane);

		VentanaClasifCarrera.this.add(panelPrincipal);
		VentanaClasifCarrera.this.setVisible(true);	
	}
}
