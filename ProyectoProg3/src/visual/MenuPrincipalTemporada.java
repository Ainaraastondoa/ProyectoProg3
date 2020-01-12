package visual;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import elementos.Audio;
//import elementos.Temporada;

/**Esta clase es para visualizar el menu principal
 * del modo Temporada
 */
public class MenuPrincipalTemporada extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Componentes Ventana
	JFrame ventanaInicio;
	private JButton bInicio;
	private JButton bCarrera;
	private JButton bClasificacion;
	private JButton bCoche;
	private JButton bPiloto;
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondotrayectoria.png";
//	private Audio musicaMenu;
	
	// Datos
//	private Temporada temporada;
//	private int numCarrera;
	
	public MenuPrincipalTemporada(JFrame v) {
		ventanaInicio = v;
		setTitle("Temporada");
		setSize(v.getWidth(), v.getHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				ventanaInicio.setVisible( true );			
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
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCarrera vCarrera;
				try {
					vCarrera = new VentanaCarrera (MenuPrincipalTemporada.this);
					vCarrera.setLocation(getLocation());
					vCarrera.setSize(getSize());
					vCarrera.setVisible(true);
					MenuPrincipalTemporada.this.setVisible(false);
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
				VentanaClasifPiloto clasifPiloto = new VentanaClasifPiloto( MenuPrincipalTemporada.this );
				clasifPiloto.setLocation( getLocation() );
				clasifPiloto.setSize( getSize() );
				clasifPiloto.setVisible( true );
				MenuPrincipalTemporada.this.setVisible( false );		
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
				try {
					coche = new VentanaCoche( MenuPrincipalTemporada.this );
					coche.setLocation( getLocation() );
					coche.setSize( getSize() );
					coche.setVisible( true );
					MenuPrincipalTemporada.this.setVisible( false );
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
				try {
					pilotos = new VentanaPiloto( MenuPrincipalTemporada.this );
					pilotos.setLocation( getLocation() );
					pilotos.setSize( getSize() );
					pilotos.setVisible( true );
					MenuPrincipalTemporada.this.setVisible( false );	
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
		bCoche.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 24, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bCoche);
		bPiloto.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 3, (this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		pCentral.add(bPiloto);
	}
}
