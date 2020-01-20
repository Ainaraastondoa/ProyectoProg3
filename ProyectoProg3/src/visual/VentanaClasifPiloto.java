package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	// Labels para clasificación
	private JLabel lp1, lp2, lp3, lp4, lp5, lp6, lp7, lp8, lp9, lp10, lp11, lp12, lp13, lp14, lp15, lp16, lp17, lp18, lp19, lp20,
		lp21, lp22, lp23, lp24, lp25, lp26, lp27, lp28, lp29, lp30, lp31, lp32, lp33, lp34, lp35, lp36, lp37, lp38, lp39, lp40;
	
	public VentanaClasifPiloto(Temporada temp, int modoJuego, int numCarrera) { // 0 para trayectoria, 1 para temporada
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
		
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension(1600, 900));
		pCentral.setOpaque(false);
		getContentPane().add(pCentral);
		
		JButton bok = new JButton("Ok");
		bok.setBounds((this.getWidth()/20) * 9, (this.getHeight()/10) * 8, (this.getWidth()/20) * 2, this.getHeight()/10);
		pCentral.add(bok, BorderLayout.SOUTH);
		pCentral.add(bok);
		
		//CREACION JTABLE DONDE SE VAN A INTRODUCIR LOS PUNTOS
		//DE LOS PILOTOS
		String [] columnNames = {"PILOTO", "PUNTOS OBTENIDOS"};
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
//				
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
		HashMap<Piloto, Integer> mapaOrdenado = temp.getPuntosPiloto().entrySet().stream().sorted( 
				Map.Entry.comparingByValue() ).collect(Collectors.toMap(Map.Entry::getKey,
						Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		lp1 = new JLabel(); lp21 = new JLabel();
		lp2 = new JLabel(); lp22 = new JLabel();
		lp3 = new JLabel(); lp23 = new JLabel();
		lp4 = new JLabel(); lp24 = new JLabel();
		lp5 = new JLabel(); lp25 = new JLabel();
		lp6 = new JLabel(); lp26 = new JLabel();
		lp7 = new JLabel(); lp27 = new JLabel();
		lp8 = new JLabel(); lp28 = new JLabel();
		lp9 = new JLabel(); lp29 = new JLabel();
		lp10 = new JLabel(); lp30 = new JLabel();
		lp11 = new JLabel(); lp31 = new JLabel();
		lp12 = new JLabel(); lp32 = new JLabel();
		lp13 = new JLabel(); lp33 = new JLabel();
		lp14 = new JLabel(); lp34 = new JLabel();
		lp15 = new JLabel(); lp35 = new JLabel();
		lp16 = new JLabel(); lp36 = new JLabel();
		lp17 = new JLabel(); lp37 = new JLabel();
		lp18 = new JLabel(); lp38 = new JLabel();
		lp19 = new JLabel(); lp39 = new JLabel();
		lp20 = new JLabel(); lp40 = new JLabel();
		
		ArrayList<JLabel> listaLabels = new ArrayList<JLabel>(Arrays.asList(lp1, lp2, lp3, lp4,
				lp5, lp6, lp7, lp8, lp9, lp10, lp11, lp12, lp13, lp14, lp15, lp16, lp17, lp18, lp19, lp20, lp21, lp22,
				lp23, lp24, lp25, lp26, lp27, lp28, lp29, lp30, lp31, lp32, lp33, lp34, lp35, lp36, lp37, lp38, lp39, lp40));
		
		int contador = 20;
		
		
		for (Map.Entry<Piloto, Integer> entrada : mapaOrdenado.entrySet()) {
			if (contador > 0) {
				listaLabels.get( contador - 1 ).setText( contador + ". " + entrada.getKey() ); // Nombre piloto con posición
				listaLabels.get( contador + 19 ).setText( entrada.getValue().toString() ); // Puntos del piloto
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
		lp21.setFont( new Font( lp21.getFont().getFontName(), lp21.getFont().getStyle(), 30 ));
		lp21.setForeground( Color.WHITE );
		lp22.setFont( new Font( lp22.getFont().getFontName(), lp22.getFont().getStyle(), 30 ));
		lp22.setForeground( Color.WHITE );
		lp23.setFont( new Font( lp23.getFont().getFontName(), lp23.getFont().getStyle(), 30 ));
		lp23.setForeground( Color.WHITE );
		lp24.setFont( new Font( lp24.getFont().getFontName(), lp24.getFont().getStyle(), 30 ));
		lp24.setForeground( Color.WHITE );
		lp25.setFont( new Font( lp25.getFont().getFontName(), lp25.getFont().getStyle(), 30 ));
		lp25.setForeground( Color.WHITE );
		lp26.setFont( new Font( lp26.getFont().getFontName(), lp26.getFont().getStyle(), 30 ));
		lp26.setForeground( Color.WHITE );
		lp27.setFont( new Font( lp27.getFont().getFontName(), lp27.getFont().getStyle(), 30 ));
		lp27.setForeground( Color.WHITE );
		lp28.setFont( new Font( lp28.getFont().getFontName(), lp28.getFont().getStyle(), 30 ));
		lp28.setForeground( Color.WHITE );
		lp29.setFont( new Font( lp29.getFont().getFontName(), lp29.getFont().getStyle(), 30 ));
		lp29.setForeground( Color.WHITE );
		lp30.setFont( new Font( lp30.getFont().getFontName(), lp30.getFont().getStyle(), 30 ));
		lp30.setForeground( Color.WHITE );
		lp31.setFont( new Font( lp31.getFont().getFontName(), lp31.getFont().getStyle(), 30 ));
		lp31.setForeground( Color.WHITE );
		lp32.setFont( new Font( lp32.getFont().getFontName(), lp32.getFont().getStyle(), 30 ));
		lp32.setForeground( Color.WHITE );
		lp33.setFont( new Font( lp33.getFont().getFontName(), lp33.getFont().getStyle(), 30 ));
		lp33.setForeground( Color.WHITE );
		lp34.setFont( new Font( lp34.getFont().getFontName(), lp34.getFont().getStyle(), 30 ));
		lp34.setForeground( Color.WHITE );
		lp35.setFont( new Font( lp35.getFont().getFontName(), lp35.getFont().getStyle(), 30 ));
		lp35.setForeground( Color.WHITE );
		lp36.setFont( new Font( lp36.getFont().getFontName(), lp36.getFont().getStyle(), 30 ));
		lp36.setForeground( Color.WHITE );
		lp37.setFont( new Font( lp37.getFont().getFontName(), lp37.getFont().getStyle(), 30 ));
		lp37.setForeground( Color.WHITE );
		lp38.setFont( new Font( lp38.getFont().getFontName(), lp38.getFont().getStyle(), 30 ));
		lp38.setForeground( Color.WHITE );
		lp39.setFont( new Font( lp39.getFont().getFontName(), lp39.getFont().getStyle(), 30 ));
		lp39.setForeground( Color.WHITE );
		lp40.setFont( new Font( lp40.getFont().getFontName(), lp40.getFont().getStyle(), 30 ));
		lp40.setForeground( Color.WHITE );
		
		// Colocarlos en pantalla
//		lp1.setBounds( this.getWidth() / 2 , this.getHeight() / 2 - 25, 150, 50);
//		lp2.setBounds( this.getWidth() / 2 , this.getHeight() / 2 - 50, 150, 50);
//		lp3.setBounds( this.getWidth() / 2 , this.getHeight() / 2 - 75, 150, 50);
//		lp4.setBounds( this.getWidth() / 2 , this.getHeight() / 2 - 100, 150, 50);
//		lp5.setBounds( this.getWidth() / 2 , this.getHeight() / 2 - 125, 150, 50);
		
		// Añadir labels al panel
		pCentral.add(lp1); pCentral.add(lp21);
		pCentral.add(lp2); pCentral.add(lp22);
		pCentral.add(lp3); pCentral.add(lp23);
		pCentral.add(lp4); pCentral.add(lp24);
		pCentral.add(lp5); pCentral.add(lp25);
		pCentral.add(lp6); pCentral.add(lp26);
		pCentral.add(lp7); pCentral.add(lp27);
		pCentral.add(lp8); pCentral.add(lp28);
		pCentral.add(lp9); pCentral.add(lp29);
		pCentral.add(lp10); pCentral.add(lp30);
		pCentral.add(lp11); pCentral.add(lp31);
		pCentral.add(lp12); pCentral.add(lp32);
		pCentral.add(lp13); pCentral.add(lp33);
		pCentral.add(lp14); pCentral.add(lp34);
		pCentral.add(lp15); pCentral.add(lp35);
		pCentral.add(lp16); pCentral.add(lp36);
		pCentral.add(lp17); pCentral.add(lp37);
		pCentral.add(lp18); pCentral.add(lp38);
		pCentral.add(lp19); pCentral.add(lp39);
		pCentral.add(lp20); pCentral.add(lp40);
		
		lp1.setVisible( true ); lp21.setVisible( true );
		lp2.setVisible( true ); lp22.setVisible( true );
		lp3.setVisible( true ); lp23.setVisible( true );
		lp4.setVisible( true ); lp24.setVisible( true );
		lp5.setVisible( true ); lp25.setVisible( true );
		lp6.setVisible( true ); lp26.setVisible( true );
		lp7.setVisible( true ); lp27.setVisible( true );
		lp8.setVisible( true ); lp28.setVisible( true );
		lp9.setVisible( true ); lp29.setVisible( true );
		lp10.setVisible( true ); lp30.setVisible( true );
		lp11.setVisible( true ); lp31.setVisible( true );
		lp12.setVisible( true ); lp32.setVisible( true );
		lp13.setVisible( true ); lp33.setVisible( true );
		lp14.setVisible( true ); lp34.setVisible( true );
		lp15.setVisible( true ); lp35.setVisible( true );
		lp16.setVisible( true ); lp36.setVisible( true );
		lp17.setVisible( true ); lp37.setVisible( true );
		lp18.setVisible( true ); lp38.setVisible( true );
		lp19.setVisible( true ); lp39.setVisible( true );
		lp20.setVisible( true ); lp40.setVisible( true );
		
		
		
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
				VentanaClasifEscuderia clasEscuderia = new VentanaClasifEscuderia( temp, modoJuego, numCarrera );
				clasEscuderia.setLocation( getLocation() );
				clasEscuderia.setSize( getSize() );
				clasEscuderia.setVisible( true );
				VentanaClasifPiloto.this.dispose();
			}
			
		});
	}

}
