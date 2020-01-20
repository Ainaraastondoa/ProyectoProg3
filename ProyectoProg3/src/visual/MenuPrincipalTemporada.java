package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import elementos.Audio;
import elementos.BD;
//import elementos.Audio;
import elementos.Temporada;

/**Esta clase es para visualizar el menu principal
 * del modo Temporada
 */
public class MenuPrincipalTemporada extends JFrame {

	private static final long serialVersionUID = 1L;	
	// Componentes Ventana 
	private JButton bInicio;
	private JButton bCarrera;
	private JButton bClasificacion;
	private JButton bCoche; 
	private JButton bPiloto;
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondotemporada.png";
	
	// Datos
	private Temporada temporada;
	private int numCarrera;
	
	// Constructor Ventana Temporada. audio a 1 cuando haya que reiniciar cancion
	public MenuPrincipalTemporada(Temporada temp, int numCa, int audio) {
		
		// Creación Temporada
		temporada = temp;
		if (temporada.getListaCarreras().size() < 10) { // Las carreras no se han creado aún
			Connection con = BD.initBD("src/datos/F1BaseDatos.db");
			Statement st;
			try {
				st = con.createStatement();
				temporada.crearCarrerasTemporada( BD.listaCircuitosSelect(st) );
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			numCarrera = 1; // Hay que comenzar por la primera carrera
		} else {
			numCarrera = numCa;
		}
		
		// Creación Ventana
		setTitle("Temporada");
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		// Fondo Ventana
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		// Creación del panel que alberga los botones
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		//MUSICA
		Audio musicatemporada = new Audio("/audio/temporada.wav");
		if (audio == 1) {
			musicatemporada.start();
		}
		Font font = new Font("Arial", Font.BOLD, 44);
		JLabel texto = new JLabel("Muse - Uprising");
		texto.setFont(font);
		texto.setForeground(Color.white);
//		texto.setOpaque(false);
		texto.setBounds(60, 30, 800, 50);
		texto.setBackground(new Color(255,255,255));
		pCentral.add(texto);
		//A LOS 6 segundos se quita el titulo de la cancion
		Timer t = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texto.setText(null);
            }  
        });
		t.setRepeats(false);
        t.start();
        
        
        
		
		// Creación de los botones
		// Botón INICIO
		bInicio = new JButton();
		bInicio.setContentAreaFilled(false);
		bInicio.setOpaque(false);
		bInicio.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/inicio2.png"));
		    	bInicio.setIcon(icono);	    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/inicio.png"));
		    	bInicio.setIcon(icono);
		    }
		});    	
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/inicio.png"));
		bInicio.setIcon(icono); 
		bInicio.setBorder(null);
		bInicio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				musicatemporada.stop();
				dispose();
				try { 
					VentanaInicial v = new VentanaInicial();
					v.setVisible( true );
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
			}			
		});
		
		// Botón CARRERA
		bCarrera = new JButton();
		bCarrera.setContentAreaFilled(false);
		bCarrera.setOpaque(false);
		bCarrera.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/carrera2.png"));
		    	bCarrera.setIcon(icono);    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/carrera.png"));
		    	bCarrera.setIcon(icono);
		    }
		});    	
		ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/carrera.png"));
		bCarrera.setIcon(icono2);
		bCarrera.setBorder(null);
		bCarrera.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCarrera vCarrera;
				musicatemporada.stop();
				try {
					dispose();
					vCarrera = new VentanaCarrera(temporada, numCarrera, 1);
					vCarrera.setLocation(getLocation());
					vCarrera.setSize(getSize());
					vCarrera.setVisible( true );

				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}		
			}			
		});
		
		// Botón Clasificación
		bClasificacion = new JButton();
		bClasificacion.setContentAreaFilled(false);
		bClasificacion.setOpaque(false);
		bClasificacion.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/clasificacion2.png"));
		    	bClasificacion.setIcon(icono);	    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/clasificacion.png"));
		    	bClasificacion.setIcon(icono);
		    }
		});    	
		ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/clasificacion.png"));
		bClasificacion.setIcon(icono3);
		bClasificacion.setBorder(null);
		bClasificacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (numCarrera > 1) {
					musicatemporada.stop();
					dispose();
					VentanaClasifPiloto clasifPiloto = new VentanaClasifPiloto( temporada, 1, numCarrera );
					clasifPiloto.setLocation( getLocation() );
					clasifPiloto.setSize( getSize() );
					clasifPiloto.setVisible( true );
				}
			}			
		});
		
		// Botón Coche
		bCoche = new JButton();
		bCoche.setContentAreaFilled(false);
		bCoche.setOpaque(false);
		bCoche.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/micoche2.png"));
		    	bCoche.setIcon(icono);		    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/micoche.png"));
		    	bCoche.setIcon(icono);
		    }
		});    	
		ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/micoche.png"));
		bCoche.setIcon(icono4);
		bCoche.setBorder(null);
		bCoche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCoche coche;
				musicatemporada.stop();
				try {
					dispose();
					coche = new VentanaCoche( temp, numCa, 1 );
					coche.setLocation( getLocation() );
					coche.setSize( getSize() );
					coche.setVisible( true );
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
			}			
		});
		
		// Botón Piloto
		bPiloto = new JButton();
		bPiloto.setContentAreaFilled(false);
		bPiloto.setOpaque(false);
		bPiloto.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/mispilotos2.png"));
		    	bPiloto.setIcon(icono);	    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/mispilotos.png"));
		    	bPiloto.setIcon(icono);
		    }
		});    	
		ImageIcon icono5 = new ImageIcon(getClass().getResource("/img/mispilotos.png"));
		bPiloto.setIcon(icono5);
		bPiloto.setBorder(null);
		bPiloto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPiloto pilotos;
				musicatemporada.stop();
				try {
					dispose();
					pilotos = new VentanaPiloto( temp, numCa, 1 );
					pilotos.setLocation( getLocation() );
					pilotos.setSize( getSize() );
					pilotos.setVisible( true );
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
			}			
		});
		
		// Posicionar botones y añadirlos al panel central
		bInicio.setBounds((this.getWidth()/25) * 2, (this.getHeight()/92) * 66, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bInicio);
		bCarrera.setBounds((this.getWidth()/25) * 9, (this.getHeight()/92) * 63, (this.getWidth()/25) * 7, (this.getHeight()/92)*27); 
		pCentral.add(bCarrera);
		bClasificacion.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 66, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bClasificacion);
		bCoche.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 37, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bCoche);
		bPiloto.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 10, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bPiloto);
	}
}
