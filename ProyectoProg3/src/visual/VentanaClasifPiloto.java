package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import elementos.Piloto;
import elementos.Temporada;

/**Ventana en la que se mostrar� a los pilotos 
 *clasificados seg�n su puntuaci�n 
 */ 

public class VentanaClasifPiloto extends JFrame{
	
	private static final long serialVersionUID = 1L;
	// HACER JTable
//	JFrame VentanaClasifCarrera;
	private Object [][] data = {{}};
	private String fondo = "/img/fondoc.png";
	private JButton bSiguiente;
	
	// Labels para clasificación
	private JLabel lp1, lp2, lp3, lp4, lp5, lp6, lp7, lp8, lp9, lp10, lp11, lp12, lp13, lp14, lp15, lp16, lp17, lp18, lp19, lp20,
		lp21, lp22, lp23, lp24, lp25, lp26, lp27, lp28, lp29, lp30, lp31, lp32, lp33, lp34, lp35, lp36, lp37, lp38, lp39, lp40;
	
	public VentanaClasifPiloto(Temporada temp, int modoJuego, int numCarrera, int numTempo) { // 0 para trayectoria, 1 para temporada
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
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		JPanel pLabels = new JPanel();
		pLabels.setPreferredSize( new Dimension(600, 820));
		pLabels.setBounds(375, 20, 600, 820);
		pLabels.setOpaque(true);
		pLabels.setLayout(null);
		pCentral.add(pLabels);
		
		//BOTON SIGUIENTE
		bSiguiente = new JButton();
		bSiguiente.setContentAreaFilled(false);
		bSiguiente. setOpaque(false);
		bSiguiente.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/siguiente2.png"));
		    	bSiguiente.setIcon(icono2);
		    }
		    public void mouseExited(MouseEvent e) {
		    	ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/siguiente.png"));
		    	bSiguiente.setIcon(icono2);
		    }
		});
		ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/siguiente.png"));
		bSiguiente.setIcon(icono2);
		bSiguiente.setBorder(null);
		bSiguiente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasifEscuderia clasEscuderia = new VentanaClasifEscuderia( temp, modoJuego, numCarrera, numTempo );
				clasEscuderia.setLocation( getLocation() );
				clasEscuderia.setSize( getSize() );
				clasEscuderia.setVisible( true );
				VentanaClasifPiloto.this.dispose();
			}			
		});
		bSiguiente.setBounds(1212, 377, 160, 85);
		pCentral.add(bSiguiente);
		
		
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
		
		//lp1-lp21 -> Los jLabels del piloto en la posicion1.
		for (Map.Entry<Piloto, Integer> entrada : mapaOrdenado.entrySet()) {
			if (contador > 0 && contador < 6) {
				listaLabels.get( contador - 1 ).setText( "   " + contador + ".      " + entrada.getKey() ); // Nombre piloto con posición (triple tab)
				listaLabels.get( contador + 19 ).setText( "			      " + entrada.getValue().toString() ); // Puntos del piloto
				contador--;
			} else if ( contador >= 6 && contador < 10) {
				listaLabels.get( contador - 1 ).setText( "   " + contador + ".      " + entrada.getKey() ); // Nombre piloto con posición (triple tab)
				listaLabels.get( contador + 19 ).setText( "			       " + entrada.getValue().toString() ); // Puntos del piloto
				contador--;
			} else if (contador >= 10) {
				listaLabels.get( contador - 1 ).setText( "  " + contador + ".     " + entrada.getKey() ); // Nombre piloto con posición (triple tab)
				listaLabels.get( contador + 19 ).setText( "			       " + entrada.getValue().toString() ); // Puntos del piloto
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
		lp1.setBounds( 0, 0, 400, 41); lp21.setBounds(400, 0, 200, 41);
		lp1.setOpaque(true); lp21.setOpaque(true);
		lp1.setBackground(Color.DARK_GRAY); lp21.setBackground(Color.DARK_GRAY);
		
		lp2.setBounds( 0, 41, 400, 41); lp22.setBounds(400, 41, 200, 41);
		lp2.setOpaque(true); lp22.setOpaque(true);
		lp2.setBackground(Color.GRAY); lp22.setBackground(Color.GRAY);
		
		lp3.setBounds( 0, 82, 400, 41); lp23.setBounds(400, 82, 200, 41);
		lp3.setOpaque(true); lp23.setOpaque(true);
		lp3.setBackground(Color.DARK_GRAY); lp23.setBackground(Color.DARK_GRAY);
		
		lp4.setBounds( 0, 123, 400, 41); lp24.setBounds(400, 123, 200, 41);
		lp4.setOpaque(true); lp24.setOpaque(true);
		lp4.setBackground(Color.GRAY); lp24.setBackground(Color.GRAY);
		
		lp5.setBounds( 0, 164, 400, 41); lp25.setBounds(400, 164, 200, 41);
		lp5.setOpaque(true); lp25.setOpaque(true);
		lp5.setBackground(Color.DARK_GRAY); lp25.setBackground(Color.DARK_GRAY);
		
		lp6.setBounds( 0, 205, 400, 41); lp26.setBounds(400, 205, 200, 41);
		lp6.setOpaque(true); lp26.setOpaque(true);
		lp6.setBackground(Color.GRAY); lp26.setBackground(Color.GRAY);
		
		lp7.setBounds( 0, 246, 400, 41); lp27.setBounds(400, 246, 200, 41);
		lp7.setOpaque(true); lp27.setOpaque(true);
		lp7.setBackground(Color.DARK_GRAY); lp27.setBackground(Color.DARK_GRAY);
		
		lp8.setBounds( 0, 287, 400, 41); lp28.setBounds(400, 287, 200, 41);
		lp8.setOpaque(true); lp28.setOpaque(true);
		lp8.setBackground(Color.GRAY); lp28.setBackground(Color.GRAY);
		
		lp9.setBounds( 0, 328, 400, 41); lp29.setBounds(400, 328, 200, 41);
		lp9.setOpaque(true); lp29.setOpaque(true);
		lp9.setBackground(Color.DARK_GRAY); lp29.setBackground(Color.DARK_GRAY);
		
		lp10.setBounds( 0, 369, 400, 41); lp30.setBounds(400, 369, 200, 41);
		lp10.setOpaque(true); lp30.setOpaque(true);
		lp10.setBackground(Color.GRAY); lp30.setBackground(Color.GRAY);
		
		lp11.setBounds( 0, 410, 400, 41); lp31.setBounds(400, 410, 200, 41);
		lp11.setOpaque(true); lp31.setOpaque(true);
		lp11.setBackground(Color.DARK_GRAY); lp31.setBackground(Color.DARK_GRAY);
		
		lp12.setBounds( 0, 451, 400, 41); lp32.setBounds(400, 451, 200, 41);
		lp12.setOpaque(true); lp32.setOpaque(true);
		lp12.setBackground(Color.GRAY); lp32.setBackground(Color.GRAY);

		lp13.setBounds( 0, 492, 400, 41); lp33.setBounds(400, 492, 200, 41);
		lp13.setOpaque(true); lp33.setOpaque(true);
		lp13.setBackground(Color.DARK_GRAY); lp33.setBackground(Color.DARK_GRAY);
		
		lp14.setBounds( 0, 533, 400, 41); lp34.setBounds(400, 533, 200, 41);
		lp14.setOpaque(true); lp34.setOpaque(true);
		lp14.setBackground(Color.GRAY); lp34.setBackground(Color.GRAY);
		
		lp15.setBounds( 0, 574, 400, 41); lp35.setBounds(400, 574, 200, 41);
		lp15.setOpaque(true); lp35.setOpaque(true);
		lp15.setBackground(Color.DARK_GRAY); lp35.setBackground(Color.DARK_GRAY);
		
		lp16.setBounds( 0, 615, 400, 41); lp36.setBounds(400, 615, 200, 41);
		lp16.setOpaque(true); lp36.setOpaque(true);
		lp16.setBackground(Color.GRAY); lp36.setBackground(Color.GRAY);
		
		lp17.setBounds( 0, 656, 400, 41); lp37.setBounds(400, 656, 200, 41);
		lp17.setOpaque(true); lp37.setOpaque(true);
		lp17.setBackground(Color.DARK_GRAY); lp37.setBackground(Color.DARK_GRAY);
		
		lp18.setBounds( 0, 697, 400, 41); lp38.setBounds(400, 697, 200, 41);
		lp18.setOpaque(true); lp38.setOpaque(true);
		lp18.setBackground(Color.GRAY); lp38.setBackground(Color.GRAY);
		
		lp19.setBounds( 0, 738, 400, 41); lp39.setBounds(400, 738, 200, 41);
		lp19.setOpaque(true); lp39.setOpaque(true);
		lp19.setBackground(Color.DARK_GRAY); lp39.setBackground(Color.DARK_GRAY);
		
		lp20.setBounds( 0, 779, 400, 41); lp40.setBounds(400, 779, 200, 41);
		lp20.setOpaque(true); lp40.setOpaque(true);
		lp20.setBackground(Color.GRAY); lp40.setBackground(Color.GRAY);
		
		
		// Añadir labels al panel
		pLabels.add(lp1); pLabels.add(lp21);
		pLabels.add(lp2); pLabels.add(lp22);
		pLabels.add(lp3); pLabels.add(lp23);
		pLabels.add(lp4); pLabels.add(lp24);
		pLabels.add(lp5); pLabels.add(lp25);
		pLabels.add(lp6); pLabels.add(lp26);
		pLabels.add(lp7); pLabels.add(lp27);
		pLabels.add(lp8); pLabels.add(lp28);
		pLabels.add(lp9); pLabels.add(lp29);
		pLabels.add(lp10); pLabels.add(lp30);
		pLabels.add(lp11); pLabels.add(lp31);
		pLabels.add(lp12); pLabels.add(lp32);
		pLabels.add(lp13); pLabels.add(lp33);
		pLabels.add(lp14); pLabels.add(lp34);
		pLabels.add(lp15); pLabels.add(lp35);
		pLabels.add(lp16); pLabels.add(lp36);
		pLabels.add(lp17); pLabels.add(lp37);
		pLabels.add(lp18); pLabels.add(lp38);
		pLabels.add(lp19); pLabels.add(lp39);
		pLabels.add(lp20); pLabels.add(lp40);
		
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

	}
}
