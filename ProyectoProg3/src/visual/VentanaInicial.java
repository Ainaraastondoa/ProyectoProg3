package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

import elementos.*;


/**Esta clase sirve para crear la ventana principal
 *
 */ 

public class VentanaInicial extends JFrame{
	
	private JButton bTrayectoria;
	private JButton bTemporada;
	private JButton bMulti;
	private JButton bAyuda;
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondo.png";
	private Audio musicamenu;
	
	//metodo main de pruebas
	public static void main(String[] args) throws SQLException {
		VentanaInicial v = new VentanaInicial(); 
		v.setVisible( true );
	}
	
	public VentanaInicial() throws SQLException {
		setTitle( "F1 Manager" );
		setSize(1600, 900);
		setLocation(0,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//FONDO DE LA VENTANA
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		//MUSICA MENU
//		musicamenu = new Audio("/audio/menu.wav");
//		musicamenu.start();
		
		//DATOS
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		BD.usarCrearTablasBD(con);
//		Statement st = con.createStatement();
//		BD.insertDatos(st);
		
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( 1600, 900 ));
		pCentral.setOpaque(false);
		add(pCentral, BorderLayout.WEST);
		
		//4 BOTONES DE LA VENTANA INICIAL
		//AYUDA
		bAyuda = new JButton();
		bAyuda.setBounds((pCentral.getWidth()/35), ((pCentral.getHeight()/18)*11), ((pCentral.getWidth()/35)*7), ((pCentral.getHeight()/18)*4));
		revalidate();
		bAyuda.setBackground(new Color(0,0,0,0));
		bAyuda.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	bAyuda.setIcon(null);
		    	revalidate();
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/ayuda2.png"));
		    	bAyuda.setIcon(icono);
		    }
		    public void mouseExited(MouseEvent e) {
		    	bAyuda.setIcon(null);
		    	revalidate();
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/ayuda.png"));
		    	bAyuda.setIcon(icono);
		    }
		});    	
		bAyuda.setIcon(null);
		revalidate();
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/ayuda.png"));
		bAyuda.setIcon(icono);
		bAyuda.setBorder(null);
		bAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		    	bAyuda.setIcon(null);
		    	revalidate();
				VentanaAyuda v = new VentanaAyuda( VentanaInicial.this );
				v.setLocation( getLocation() );
				v.setSize( getSize() );
				v.setVisible( true );
				VentanaInicial.this.setVisible( false ); 				
			}			
		}); 		
		
		//TRAYECTORIA
		bTrayectoria = new JButton();
		bTrayectoria.setIcon(null);
		revalidate();
		bTrayectoria.setBounds(((VentanaInicial.WIDTH/35)*9), ((int) ((VentanaInicial.HEIGHT/18)*10.5)), ((VentanaInicial.WIDTH/35)*8), ((VentanaInicial.HEIGHT/18)*5));
		bTrayectoria.setBackground(new Color(0,0,0,0));
		bTrayectoria.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	bTrayectoria.setIcon(null);
		    	revalidate();
		    	ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/trayectoria2.png"));
		    	bTrayectoria.setIcon(icono2);
		    }
		    public void mouseExited(MouseEvent e) {
		    	bTrayectoria.setIcon(null);
		    	revalidate();
		    	ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/trayectoria.png"));
		    	bTrayectoria.setIcon(icono2);
		    }
		});
		bTrayectoria.setIcon(null);
		revalidate();
		ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/trayectoria.png"));
		bTrayectoria.setIcon(icono2);
		bTrayectoria.setBorder(null);
		bTrayectoria.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSelEscuderia escuderia;
				try {
					bTrayectoria.setIcon(null);
			    	revalidate();
					escuderia = new VentanaSelEscuderia( VentanaInicial.this );
					escuderia.setLocation( getLocation() );
					escuderia.setSize( getSize() );
					escuderia.setVisible( true );
					VentanaInicial.this.setVisible( false );
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}					
		});
		
		//TEMPORADA		
		bTemporada = new JButton();
		bTemporada.setIcon(null);
		revalidate();
		bTemporada.setBounds(((VentanaInicial.WIDTH/35)*18), ((int) ((VentanaInicial.HEIGHT/18)*10.5)), ((VentanaInicial.WIDTH/35)*8), ((VentanaInicial.HEIGHT/18)*5));
		bTemporada.setBackground(new Color(0,0,0,0));
		bTemporada.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	bTemporada.setIcon(null);
		    	revalidate();
		    	ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/temporada2.png"));
		    	bTemporada.setIcon(icono3);
		    }
		    public void mouseExited(MouseEvent e) {
		    	bTemporada.setIcon(null);
		    	revalidate();
		    	ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/temporada.png"));
		    	bTemporada.setIcon(icono3);
		    }
		});
		bTemporada.setIcon(null);
		revalidate();
		ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/temporada.png"));
		bTemporada.setIcon(icono3);
		bTemporada.setBorder(null);
		bTemporada.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSelEscuderia escuderia;
				try {
					bTemporada.setIcon(null);
			    	revalidate();
					escuderia = new VentanaSelEscuderia( VentanaInicial.this );
					escuderia.setLocation( getLocation() );
					escuderia.setSize( getSize() );
					escuderia.setVisible( true );
					VentanaInicial.this.setVisible( false );
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}					
		});
		
		//MULTIJUGADOR - EN DESARROLLO
		bMulti = new JButton();
		bMulti.setIcon(null);
		revalidate();
		bMulti.setBounds(((VentanaInicial.WIDTH/35)*27), ((VentanaInicial.HEIGHT/18)*11), ((VentanaInicial.WIDTH/35)*7), ((VentanaInicial.HEIGHT/18)*4));
		bMulti.setBackground(new Color(0,0,0,0));
		bMulti.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
				bMulti.setIcon(null);
		    	revalidate();
				ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/multijugador.png"));
				bMulti.setIcon(icono4);
		    }
		    public void mouseExited(MouseEvent e) {
				bMulti.setIcon(null);
		    	revalidate();
				ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/multijugador.png"));
				bMulti.setIcon(icono4);
		    }
		});
		bMulti.setIcon(null);
		revalidate();
		ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/multijugador.png"));
		bMulti.setIcon(icono4);
		revalidate();
		bMulti.setBorder(null);
		
		pCentral.add(bAyuda);
		pCentral.add(bMulti);
		pCentral.add(bTemporada);
		pCentral.add(bTrayectoria);
		
//		add(bTrayectoria);
//		add(bTemporada);
//		add(bMulti);
//		add(bAyuda);
//		bAyuda.setBounds((VentanaInicial.WIDTH/35), ((VentanaInicial.HEIGHT/18)*11), ((VentanaInicial.WIDTH/35)*7), ((VentanaInicial.HEIGHT/18)*4));
//		bTrayectoria.setBounds(((VentanaInicial.WIDTH/35)*9), ((int) ((VentanaInicial.HEIGHT/18)*10.5)), ((VentanaInicial.WIDTH/35)*8), ((VentanaInicial.HEIGHT/18)*5));
//		bTemporada.setBounds(((VentanaInicial.WIDTH/35)*18), ((int) ((VentanaInicial.HEIGHT/18)*10.5)), ((VentanaInicial.WIDTH/35)*8), ((VentanaInicial.HEIGHT/18)*5));
//		bMulti.setBounds(((VentanaInicial.WIDTH/35)*27), ((VentanaInicial.HEIGHT/18)*11), ((VentanaInicial.WIDTH/35)*7), ((VentanaInicial.HEIGHT/18)*4));

	}
}