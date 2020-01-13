package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import elementos.Audio;
import elementos.BD;
import elementos.Carrera;
import elementos.Circuito;
import elementos.Piloto;
import elementos.Temporada;


/**Esta clase es para visualizar el menu principal
 * del modo trayectoria
 */ 

public class MenuPrincipalTrayectoria extends JFrame{
	
//	JFrame VentanaInicio;
	private JButton bInicio;			//Boton que devuelve a la ventana inicial
	private JButton bCarrera;			//Boton que inicia la proxima carrera
	private JButton bClasificacion;		//Boton que muestra la clasificacion de pilotos y escuderias
	private JButton bMercado;			//Boton dedicado al mercado de fichajes (en desarrollo)
	private JButton bCoche;				//Boton que muestra la ventana Coche
	private JButton bPiloto;			//Boton que muestra la ventana pilto
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondotrayectoria.png";
	private Audio musicamenu;
	
	// Datos
//	private Temporada temporada;
//	private int numCarrera;

	
	public MenuPrincipalTrayectoria() throws SQLException {
//		VentanaInicio = v;  
		setTitle( "Trayectoria" );
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//FONDO DE LA VENTANA
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		//MUSICA MENU
//		musicamenu = new Audio("/audio/menu.wav");
//		musicamenu.start();
//		JLabel lMusica = new JLabel("Himno F1");
		
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
//		    	//AGRANDAR EL BOTON EN UN 10%
//				bAyuda.setBounds((int) ((this.getWidth()/35) * 0.95), (int) (((this.getHeight()/18)*9.5) * 0.95), (int) (((this.getWidth()/35)*7) * 1.1), (int) (((this.getHeight()/18)*4) * 1.1));
//				revalidate();		    
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
				try {
					dispose();
					vCarrera = new VentanaCarrera ( MenuPrincipalTrayectoria.this );
					//vCarrera = new VentanaCarrera ( temporada, numCarrera, 0 );
					vCarrera.setLocation(getLocation());
					vCarrera.setSize(getSize());
					vCarrera.setVisible(true);
//					MenuPrincipalTrayectoria.this.setVisible(false);
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
				VentanaClasifPiloto clasifPiloto = new VentanaClasifPiloto( MenuPrincipalTrayectoria.this );
				clasifPiloto.setLocation( getLocation() );
				clasifPiloto.setSize( getSize() );
				clasifPiloto.setVisible( true );
				MenuPrincipalTrayectoria.this.setVisible( false );		
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
				try {
					coche = new VentanaCoche( MenuPrincipalTrayectoria.this );
					coche.setLocation( getLocation() );
					coche.setSize( getSize() );
					coche.setVisible( true );
					MenuPrincipalTrayectoria.this.setVisible( false );
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
				try {
					pilotos = new VentanaPiloto( MenuPrincipalTrayectoria.this );
					pilotos.setLocation( getLocation() );
					pilotos.setSize( getSize() );
					pilotos.setVisible( true );
					MenuPrincipalTrayectoria.this.setVisible( false );	
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
