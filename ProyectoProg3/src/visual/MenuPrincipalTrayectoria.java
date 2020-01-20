package visual;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JPanel;

import elementos.BD;
import elementos.Temporada;


/**Esta clase es para visualizar el menu principal
 * del modo trayectoria
 */ 
public class MenuPrincipalTrayectoria extends JFrame{ 
	 
	
	private static final long serialVersionUID = 1L;
	//	JFrame VentanaInicio; 
	private JButton bInicio;			//Boton que devuelve a la ventana inicial
	private JButton bCarrera;			//Boton que inicia la proxima carrera
	private JButton bClasificacion;		//Boton que muestra la clasificacion de pilotos y escuderias
	private JButton bMercado;			//Boton dedicado al mercado de fichajes (en desarrollo)
	private JButton bCoche;				//Boton que muestra la ventana Coche
	private JButton bPiloto;			//Boton que muestra la ventana pilto
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondotrayectoria.png";
	
	// Datos
	private Temporada temporada;
	private int numCarrera;
	private int numTemporada;

	
	public MenuPrincipalTrayectoria(Temporada temp, int numCa, int audio, int numTempo) throws SQLException {
		
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
			numTemporada = 1;
		} else {
			if (numCa < 40) { // Normal
				numCarrera = numCa;
				numTemporada = numTempo;
			} else { // Cambio de temporada
				numCarrera = 1;
				numTemporada = numTempo + 1;
			}
		}
		
//		VentanaInicio = v;  
		setTitle( "Trayectoria" );
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//FONDO DE LA VENTANA
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		//DATOS
//		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
//		BD.usarCrearTablasBD(con);
//		Statement st = con.createStatement();
//		BD.insertDatos(st);
		
		//CREACI�N DEL PANEL QUE ALBERGA LOS BOTONES
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		//MUSICA
//		Audio musicatrayectoria = new Audio("/audio/trayectoria.wav");
//		if (audio == 1) {
//			musicatrayectoria.start();
//		}
//		Audio.lanzaAudioEnHilo("/audio/trayectoria.wav");
//		Font font = new Font("Arial", Font.BOLD, 44);
//		JLabel texto = new JLabel("Red Hot Chili Peppers - Can't Stop" );
//		texto.setFont(font);
//		texto.setForeground(Color.white);
////		texto.setOpaque(false);
//		texto.setBounds(60, 30, 800, 50);
//		texto.setBackground(new Color(255,255,255));
//		pCentral.add(texto);
//		//A LOS 6 segundos se quita el titulo de la cancion
//		Timer t = new Timer(6000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                texto.setText(null);
//            }  
//        });
//		t.setRepeats(false);
//        t.start();
		
		//CREACION DE 6 BOTONES DE LA VENTANA 	
		//INICIO
		bInicio = new JButton();
		bInicio.setContentAreaFilled(false);
		bInicio. setOpaque(false);
		bInicio.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/inicio2.png"));
		    	bInicio.setIcon(icono);
//		    	//AGRANDAR EL BOTON EN UN 10%
//				bAyuda.setBounds((int) ((this.getWidth()/35) * 0.95), (int) (((this.getHeight()/18)*9.5) * 0.95), (int) (((this.getWidth()/35)*7) * 1.1), (int) (((this.getHeight()/18)*4) * 1.1));
//				revalidate();		    
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
			@Override
			public void actionPerformed(ActionEvent e) {
//				musicatrayectoria.stop();
				dispose();
				VentanaInicial v;
				try {
					v = new VentanaInicial();
					v.setVisible( true );
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}			
		}); 
		
		//CARRERA
		bCarrera = new JButton();
		bCarrera.setContentAreaFilled(false);
		bCarrera. setOpaque(false);
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
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCarrera vCarrera;
//				musicatrayectoria.stop();
				try {
					dispose();
					if (numTemporada < 11) {
						vCarrera = new VentanaCarrera(temporada, numCarrera, 1, numTemporada);
						vCarrera.setLocation(getLocation());
						vCarrera.setSize(getSize());
						vCarrera.setVisible(true);
					} else { // Se acabó lo que se daba
						VentanaInicial v = new VentanaInicial();
						v.setVisible( true );
					}
				} catch (SQLException sqle) {
					// TODO Auto-generated catch block
					sqle.printStackTrace();
				}		
			}			
		}); 
		
		
		//CLASIFICACION
		bClasificacion = new JButton();
		bClasificacion.setContentAreaFilled(false);
		bClasificacion. setOpaque(false);
		bClasificacion.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/clasificacion2.png"));
		    	bClasificacion.setIcon(icono);
//		    	//AGRANDAR EL BOTON EN UN 10%
//				bAyuda.setBounds((int) ((this.getWidth()/35) * 0.95), (int) (((this.getHeight()/18)*9.5) * 0.95), (int) (((this.getWidth()/35)*7) * 1.1), (int) (((this.getHeight()/18)*4) * 1.1));
//				revalidate();		    
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
//				musicatrayectoria.stop();
				dispose();		
				if (numTemporada < 11) {
					VentanaClasifPiloto clasifPiloto = new VentanaClasifPiloto( temp, 0, numCarrera, numTemporada );
					clasifPiloto.setLocation( getLocation() );
					clasifPiloto.setSize( getSize() );
					clasifPiloto.setVisible( true );
				} else {
					VentanaInicial v;
					try {
						v = new VentanaInicial();
						v.setVisible( true );
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}			
		}); 
		
		//MERCADO
		bMercado = new JButton();
		bMercado.setContentAreaFilled(false);
		bMercado. setOpaque(false);
		ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/mercado.png"));
		bMercado.setIcon(icono4);
		bMercado.setBorder(null);
		
		//MI COCHE
		bCoche = new JButton();
		bCoche.setContentAreaFilled(false);
		bCoche. setOpaque(false);
		bCoche.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/micoche2.png"));
		    	bCoche.setIcon(icono);
//		    	//AGRANDAR EL BOTON EN UN 10%
//				bAyuda.setBounds((int) ((this.getWidth()/35) * 0.95), (int) (((this.getHeight()/18)*9.5) * 0.95), (int) (((this.getWidth()/35)*7) * 1.1), (int) (((this.getHeight()/18)*4) * 1.1));
//				revalidate();		    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/micoche.png"));
		    	bCoche.setIcon(icono);
		    }
		});    	
		ImageIcon icono5 = new ImageIcon(getClass().getResource("/img/micoche.png"));
		bCoche.setIcon(icono5);
		bCoche.setBorder(null);
		bCoche.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCoche coche;
//				musicatrayectoria.stop();
				try {
					dispose();
					if (numTemporada < 11) {
						coche = new VentanaCoche( temp, numCa, 0, numTempo );
						coche.setLocation( getLocation() );
						coche.setSize( getSize() );
						coche.setVisible( true );
					} else {
						VentanaInicial v;
						try {
							v = new VentanaInicial();
							v.setVisible( true );
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}			
		}); 
		
		//MIS PILOTOS
		bPiloto = new JButton();
		bPiloto.setContentAreaFilled(false);
		bPiloto. setOpaque(false);
		bPiloto.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/mispilotos2.png"));
		    	bPiloto.setIcon(icono);
//		    	//AGRANDAR EL BOTON EN UN 10%
//				bAyuda.setBounds((int) ((this.getWidth()/35) * 0.95), (int) (((this.getHeight()/18)*9.5) * 0.95), (int) (((this.getWidth()/35)*7) * 1.1), (int) (((this.getHeight()/18)*4) * 1.1));
//				revalidate();		    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/mispilotos.png"));
		    	bPiloto.setIcon(icono);
		    }
		});    	
		ImageIcon icono6 = new ImageIcon(getClass().getResource("/img/mispilotos.png"));
		bPiloto.setIcon(icono6);
		bPiloto.setBorder(null);
		bPiloto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPiloto pilotos;
//				musicatrayectoria.stop();
				try {
					dispose();
					if (numTemporada < 11) {
						pilotos = new VentanaPiloto( temp, numCa, 0, numTemporada );
						pilotos.setLocation( getLocation() );
						pilotos.setSize( getSize() );
						pilotos.setVisible( true );
					} else {
						VentanaInicial v;
						try {
							v = new VentanaInicial();
							v.setVisible( true );
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}			
		}); 
		
		//POSICIONAR BOTONES Y A�ADIRLOS AL PANEL CENTRAL
		bInicio.setBounds((this.getWidth()/25) * 2, (this.getHeight()/92) * 66, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bInicio);
		//***
		bCarrera.setBounds((this.getWidth()/25) * 9, (this.getHeight()/92) * 63, (this.getWidth()/25) * 7, (this.getHeight()/92)*27); 
		pCentral.add(bCarrera);
		
		bClasificacion.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 66, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bClasificacion);
		bMercado.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 45, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bMercado);
		bCoche.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 24, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bCoche);
		bPiloto.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 3, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bPiloto);
		//LABEL DE MUSICA
//		lMusica.setBounds(this.getWidth()/80, this.getHeight()/46, this.getWidth()/3, this.getHeight()/10);
//		pCentral.add(lMusica);
		
//		addWindowListener(new WindowAdapter() {
//			
//			public void WindowClosed(WindowEvent e) {
//				VentanaInicio.setVisible( true );
//			}	
//		});	
	}
}
