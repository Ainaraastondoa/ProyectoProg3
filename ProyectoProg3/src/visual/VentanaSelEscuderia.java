package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import elementos.BD;
import elementos.Escuderia;
import elementos.Piloto;
import elementos.Temporada;
import elementos.Trayectoria;

public class VentanaSelEscuderia extends JFrame{
	
	private static final long serialVersionUID = 1L;

	// Datos
	private int eleccionModoJuego; // 0 si es una trayectoria, 1 si es temporada
	
	// Componentes Ventana
	JFrame VentanaInicial;
	private JButton bVolver;
//	private PanelConImagenFondo imagen_fondo;
//	private String fondo = "/img/selesc.png";
	
	public VentanaSelEscuderia(JFrame m, int modoJuego) throws SQLException {	
		eleccionModoJuego = modoJuego;
		VentanaInicial = m;
		setTitle("Selecciona tu escuderia");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//FONDO DE LA VENTANA
//		imagen_fondo = new PanelConImagenFondo();
//		imagen_fondo.setImage(fondo);
//		setContentPane(imagen_fondo);
//		setLayout(new FlowLayout());

		//DATOS
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();	
		ArrayList<Escuderia> listaEscuderias = BD.listaEscuderiasSelect(st);	
		
		//CREACI�N DEL PANELES QUE ALBERGAN LOS BOTONES
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		
		
		
		//QUIERO USAR ESTO PERO NO ME VA
//		pCentral.setLayout(null);

		
		
		getContentPane().add(pCentral);
		
		//BOTONES (Escuderias + Volver)
		for (Escuderia escuderia : listaEscuderias) {
			//BOTON ESCUDERIA
			JButton bEscuderia = new JButton();
						
			bEscuderia.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
					String imagen = "";
					imagen += escuderia.getImagen2();
			    	ImageIcon icono = new ImageIcon(getClass().getResource(imagen));
			    	bEscuderia.setIcon(icono);
			    }
			    public void mouseExited(MouseEvent e) {
			    	String imagen = "";
					imagen += escuderia.getImagen1();
			    	ImageIcon icono = new ImageIcon(getClass().getResource(imagen));
			    	bEscuderia.setIcon(icono);
			    }
			});    	
			String imagen = "";
			imagen += escuderia.getImagen1();
			ImageIcon icono = new ImageIcon(getClass().getResource(imagen));
			bEscuderia.setIcon(icono);
			bEscuderia.setBorder(null);
			
			bEscuderia.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (eleccionModoJuego == 0) { // Modo Trayectoria
						MenuPrincipalTrayectoria menu;
						try {
							menu = new MenuPrincipalTrayectoria();
							menu.setLocation( getLocation() );
							menu.setSize( getSize() );
							menu.setVisible( true );
							VentanaSelEscuderia.this.setVisible( false );
							
							//Escuderia seleccionada
							Trayectoria.setEscuderia(escuderia);
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else { // Modo Temporada
						MenuPrincipalTemporada menu;
						Connection con = BD.initBD("src/datos/F1BaseDatos.db");
						Statement st;
						try {
							st = con.createStatement();
							menu = new MenuPrincipalTemporada( new Temporada(2019, BD.listaPilotosSelect(st), BD.listaEscuderiasSelect(st)), 0 );
							menu.setLocation( getLocation() );
							menu.setSize( getSize() );
							menu.setVisible( true );
							VentanaSelEscuderia.this.setVisible( false );
							
							//Escuderia seleccionada
							Temporada.setEscuderia( escuderia );
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				}				
			});	
			if (escuderia.getNombre().equals(listaEscuderias.get(0).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 4, (this.getHeight()/24) * 5, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(1).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 8, (this.getHeight()/24) * 5, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(2).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 12, (this.getHeight()/24) * 5, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(3).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 2, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(4).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 6, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(5).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 10, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(6).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 14, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(7).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 4, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(8).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 8, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
				pCentral.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(9).getNombre())) {
				bEscuderia.setBounds((this.getWidth()/20) * 12, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				pCentral.add(bEscuderia);	
			}		
		}		
//		pCentral.setBackground(Color.DARK_GRAY);
//		pCentral.setLayout(new GridLayout(4,5));
		
		//BOTON VOLVER
		bVolver = new JButton("Volver");
//		bVolver.setContentAreaFilled(false);
//		bVolver. setOpaque(false);
		
//		bVolver.addMouseListener(new MouseAdapter() {
//		    public void mouseEntered(MouseEvent e) {
//				ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/volver.png"));
//				bVolver.setIcon(icono4);
//		    }
//		    public void mouseExited(MouseEvent e) {
//				ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/volver.png"));
//				bVolver.setIcon(icono4);
//		    }
//		});
//
//		ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/volver.png"));
//		bVolver.setIcon(icono4);
//		bVolver.setBorder(null);
		bVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VentanaInicial.setVisible( true );				
			}			
		}); 
		
		bVolver.setBounds((this.getWidth()/20) * 9, (int) ((this.getHeight()/24) * 20.5), (this.getWidth()/20) * 2, (this.getHeight()/24) * 2);
		pCentral.add(bVolver);		
	}
}

