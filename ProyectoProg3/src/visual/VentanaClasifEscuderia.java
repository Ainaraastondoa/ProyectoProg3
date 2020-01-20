package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import elementos.Escuderia;
import elementos.Piloto;
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
	
	// Labels para clasificación
	private JLabel lp1, lp2, lp3, lp4, lp5, lp6, lp7, lp8, lp9, lp10, lp11, lp12, lp13, lp14, lp15, lp16, lp17, lp18, lp19, lp20;
	
	public VentanaClasifEscuderia(Temporada temp, int modoJuego, int numCarrera) {
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
		
//		JTable tabla = new JTable() {
//			
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//			@Override
//			public Class<?> getColumnClass(int column) {
//				if (column == 0) {
//					return Integer.class;
//				}
//				if (column == 1) {
//					return String.class;
//				}
//				
//				return super.getClass();
//			} 			
//		};
//		
//		tabla.setModel(tableModel);
//		
//		JScrollPane tablaPane = new JScrollPane(tabla);
//		pCentral.add(tablaPane);
//		
//		tabla.setVisible(true);
	
		
		//INTRODUCIMOS LOS DATOS
		HashMap<Escuderia, Integer> mapaOrdenado = temp.getPuntosEscuderia().entrySet().stream().sorted( 
				Map.Entry.comparingByValue() ).collect(Collectors.toMap(Map.Entry::getKey,
						Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		lp1 = new JLabel();
		lp2 = new JLabel();
		lp3 = new JLabel(); 
		lp4 = new JLabel(); 
		lp5 = new JLabel(); 
		lp6 = new JLabel(); 
		lp7 = new JLabel(); 
		lp8 = new JLabel(); 
		lp9 = new JLabel(); 
		lp10 = new JLabel(); 
		lp11 = new JLabel(); 
		lp12 = new JLabel(); 
		lp13 = new JLabel(); 
		lp14 = new JLabel(); 
		lp15 = new JLabel(); 
		lp16 = new JLabel(); 
		lp17 = new JLabel(); 
		lp18 = new JLabel(); 
		lp19 = new JLabel(); 
		lp20 = new JLabel();
		
		ArrayList<JLabel> listaLabels = new ArrayList<JLabel>(Arrays.asList(lp1, lp2, lp3, lp4,
				lp5, lp6, lp7, lp8, lp9, lp10, lp11, lp12, lp13, lp14, lp15, lp16, lp17, lp18, lp19, lp20));
		
		int contador = 10;
		
		
		for (Map.Entry<Escuderia, Integer> entrada : mapaOrdenado.entrySet()) {
			if (contador > 0) {
				listaLabels.get( contador - 1 ).setText( contador + ". " + entrada.getKey() ); // Nombre escudería con posición
				listaLabels.get( contador + 9 ).setText( entrada.getValue().toString() ); // Puntos de la escudería
				contador--;
			}
		}
		
		// Dar formato a los labels
		lp1.setFont( new Font( lp1.getFont().getFontName(), lp1.getFont().getStyle(), 30 ));
		lp1.setForeground( Color.WHITE );
		lp2.setFont( new Font( lp2.getFont().getFontName(), lp2.getFont().getStyle(), 30 ));
		lp2.setForeground( Color.WHITE );
		lp3.setFont( new Font( lp3.getFont().getFontName(), lp3.getFont().getStyle(), 30 ));
		lp3.setForeground( Color.WHITE );
		lp4.setFont( new Font( lp4.getFont().getFontName(), lp4.getFont().getStyle(), 30 ));
		lp4.setForeground( Color.WHITE );
		lp5.setFont( new Font( lp5.getFont().getFontName(), lp5.getFont().getStyle(), 30 ));
		lp5.setForeground( Color.WHITE );
		lp6.setFont( new Font( lp6.getFont().getFontName(), lp6.getFont().getStyle(), 30 ));
		lp6.setForeground( Color.WHITE );
		lp7.setFont( new Font( lp7.getFont().getFontName(), lp7.getFont().getStyle(), 30 ));
		lp7.setForeground( Color.WHITE );
		lp8.setFont( new Font( lp8.getFont().getFontName(), lp8.getFont().getStyle(), 30 ));
		lp8.setForeground( Color.WHITE );
		lp9.setFont( new Font( lp9.getFont().getFontName(), lp9.getFont().getStyle(), 30 ));
		lp9.setForeground( Color.WHITE );
		lp10.setFont( new Font( lp10.getFont().getFontName(), lp10.getFont().getStyle(), 30 ));
		lp10.setForeground( Color.WHITE );
		lp11.setFont( new Font( lp11.getFont().getFontName(), lp11.getFont().getStyle(), 30 ));
		lp11.setForeground( Color.WHITE );
		lp12.setFont( new Font( lp12.getFont().getFontName(), lp12.getFont().getStyle(), 30 ));
		lp12.setForeground( Color.WHITE );
		lp13.setFont( new Font( lp13.getFont().getFontName(), lp13.getFont().getStyle(), 30 ));
		lp13.setForeground( Color.WHITE );
		lp14.setFont( new Font( lp14.getFont().getFontName(), lp14.getFont().getStyle(), 30 ));
		lp14.setForeground( Color.WHITE );
		lp15.setFont( new Font( lp15.getFont().getFontName(), lp15.getFont().getStyle(), 30 ));
		lp15.setForeground( Color.WHITE );
		lp16.setFont( new Font( lp16.getFont().getFontName(), lp16.getFont().getStyle(), 30 ));
		lp16.setForeground( Color.WHITE );
		lp17.setFont( new Font( lp17.getFont().getFontName(), lp17.getFont().getStyle(), 30 ));
		lp17.setForeground( Color.WHITE );
		lp18.setFont( new Font( lp18.getFont().getFontName(), lp18.getFont().getStyle(), 30 ));
		lp18.setForeground( Color.WHITE );
		lp19.setFont( new Font( lp19.getFont().getFontName(), lp19.getFont().getStyle(), 30 ));
		lp19.setForeground( Color.WHITE );
		lp20.setFont( new Font( lp20.getFont().getFontName(), lp20.getFont().getStyle(), 30 ));
		lp20.setForeground( Color.WHITE );

		// Añadir labels al panel
		pCentral.add(lp1); pCentral.add(lp11);
		pCentral.add(lp2); pCentral.add(lp12);
		pCentral.add(lp3); pCentral.add(lp13);
		pCentral.add(lp4); pCentral.add(lp14);
		pCentral.add(lp5); pCentral.add(lp15);
		pCentral.add(lp6); pCentral.add(lp16); 
		pCentral.add(lp7); pCentral.add(lp17); 
		pCentral.add(lp8); pCentral.add(lp18);
		pCentral.add(lp9); pCentral.add(lp19);
		pCentral.add(lp10); pCentral.add(lp20);
		
		
		lp1.setVisible( true );
		lp2.setVisible( true ); 
		lp3.setVisible( true ); 
		lp4.setVisible( true );
		lp5.setVisible( true ); 
		lp6.setVisible( true ); 
		lp7.setVisible( true ); 
		lp8.setVisible( true ); 
		lp9.setVisible( true ); 
		lp10.setVisible( true ); 
		lp11.setVisible( true ); 
		lp12.setVisible( true ); 
		lp13.setVisible( true ); 
		lp14.setVisible( true );
		lp15.setVisible( true ); 
		lp16.setVisible( true ); 
		lp17.setVisible( true ); 
		lp18.setVisible( true ); 
		lp19.setVisible( true ); 
		lp20.setVisible( true );
		
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (modoJuego==0) { // Modo trayectoria
					MenuPrincipalTrayectoria menu;
					try {
						dispose();
						menu = new MenuPrincipalTrayectoria( temp, modoJuego, 1 );
						menu.setLocation( getLocation() );
						menu.setSize( getSize() );
						menu.setVisible( true );
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else { // Modo temporada
					dispose();
					MenuPrincipalTemporada menu = new MenuPrincipalTemporada(temp, numCarrera, 1);
					menu.setLocation( getLocation() );
					menu.setSize( getSize() );
					menu.setVisible( true );
				}
			}
			
		});
	}

}
