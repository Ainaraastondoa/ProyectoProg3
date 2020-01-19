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
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondoayuda.png";
	 
	public VentanaSelEscuderia(JFrame m, int modoJuego) throws SQLException {	
		eleccionModoJuego = modoJuego;
		VentanaInicial = m;
		setTitle("Selecciona tu escuderia");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		//FONDO DE LA VENTANA
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());

		//DATOS
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();	
		ArrayList<Escuderia> listaEscuderias = BD.listaEscuderiasSelect(st);	
		
		//CREACIÓN DEL PANEL QUE ALBERGA LOS DATOS Y BOTONES
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension(1600, 900));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		JPanel p1 = new JPanel();
		p1.setBounds(240, 90, 1060, 150);
		p1.setOpaque(false);
		pCentral.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setBounds(80, 280, 1430, 150);
		p2.setOpaque(false);
		pCentral.add(p2);
		
		JPanel p3 = new JPanel();
		p3.setBounds(240, 470, 1060, 150);
		p3.setOpaque(false);
		pCentral.add(p3);

		JPanel pVolver = new JPanel();
		pVolver.setBounds(720, 700, 160, 100);
		pVolver.setOpaque(false);
		pCentral.add(pVolver);
		
		
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
//				bEscuderia.setBounds((this.getWidth()/20) * 4, (this.getHeight()/24) * 5, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p1.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(1).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 8, (this.getHeight()/24) * 5, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p1.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(2).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 12, (this.getHeight()/24) * 5, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p1.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(3).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 2, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p2.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(4).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 6, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p2.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(5).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 10, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p2.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(6).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 14, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p2.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(7).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 4, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p3.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(8).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 8, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p3.add(bEscuderia);	
			}
			else if (escuderia.getNombre().equals(listaEscuderias.get(9).getNombre())) {
//				bEscuderia.setBounds((this.getWidth()/20) * 12, (this.getHeight()/24) * 10, (this.getWidth()/20) * 4, (this.getHeight()/24) * 4);
				p3.add(bEscuderia);	
			}		
		}		
		
		//BOTON VOLVER
		bVolver = new JButton(); 
		bVolver.setContentAreaFilled(false);
		bVolver. setOpaque(false);
		bVolver.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/volver2.png"));
		    	bVolver.setIcon(icono2);
		    }
		    public void mouseExited(MouseEvent e) {
		    	ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/volver.png"));
		    	bVolver.setIcon(icono2);
		    }
		});
		ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/volver.png"));
		bVolver.setIcon(icono2);
		bVolver.setBorder(null);
		bVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VentanaInicial v;
				try {
					v = new VentanaInicial();
					v.setVisible( true );			
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			  
		}); 
		pVolver.add(bVolver);		
	}
}

