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
	
	private static final long serialVersionUID = 1L;
	
	private JButton bTrayectoria;
	private JButton bTemporada;
	private JButton bMulti;
	private JButton bAyuda;
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondo.png";
//	private Audio musicamenu;
	
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
		
		//CREACIÓN DEL PANEL QUE ALBERGA LOS BOTONES
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		//CREACION DE 4 BOTONES DE LA VENTANA INICIAL		
		//AYUDA
		bAyuda = new JButton();
        bAyuda.setContentAreaFilled(false);
        bAyuda. setOpaque(false);
		bAyuda.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/ayuda2.png"));
		    	bAyuda.setIcon(icono);
//		    	//AGRANDAR EL BOTON EN UN 10%
//				bAyuda.setBounds((int) ((this.getWidth()/35) * 0.95), (int) (((this.getHeight()/18)*9.5) * 0.95), (int) (((this.getWidth()/35)*7) * 1.1), (int) (((this.getHeight()/18)*4) * 1.1));
//				revalidate();		    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/ayuda.png"));
		    	bAyuda.setIcon(icono);
		    }
		});    	
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/ayuda.png"));
		bAyuda.setIcon(icono);
		bAyuda.setBorder(null);
		bAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAyuda v = new VentanaAyuda( VentanaInicial.this );
				v.setLocation( getLocation() );
				v.setSize( getSize() );
				v.setVisible( true );
				VentanaInicial.this.setVisible( false ); 				
			}			
		}); 		
		
		//TRAYECTORIA
		bTrayectoria = new JButton();
		bTrayectoria.setContentAreaFilled(false);
		bTrayectoria. setOpaque(false);
		bTrayectoria.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/trayectoria2.png"));
		    	bTrayectoria.setIcon(icono2);
		    }
		    public void mouseExited(MouseEvent e) {
		    	ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/trayectoria.png"));
		    	bTrayectoria.setIcon(icono2);
		    }
		});
		ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/trayectoria.png"));
		bTrayectoria.setIcon(icono2);
		bTrayectoria.setBorder(null);
		bTrayectoria.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSelEscuderia escuderia;
				try {
					escuderia = new VentanaSelEscuderia( VentanaInicial.this, 0 );
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
		bTemporada.setContentAreaFilled(false);
		bTemporada. setOpaque(false);
		bTemporada.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/temporada2.png"));
		    	bTemporada.setIcon(icono3);
		    }
		    public void mouseExited(MouseEvent e) {
		    	ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/temporada.png"));
		    	bTemporada.setIcon(icono3);
		    }
		});
		ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/temporada.png"));
		bTemporada.setIcon(icono3);
		bTemporada.setBorder(null);
		bTemporada.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSelEscuderia escuderia;
				try {
					escuderia = new VentanaSelEscuderia( VentanaInicial.this, 1 );
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
		bMulti.setContentAreaFilled(false);
		bMulti. setOpaque(false);
		ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/multijugador.png"));
		bMulti.setIcon(icono4);
		bMulti.setBorder(null);
		
		bAyuda.setBounds((this.getWidth()/35), (int) ((this.getHeight()/18)*9.5), ((this.getWidth()/35)*7), ((this.getHeight()/18)*4));
		pCentral.add(bAyuda);
		bTrayectoria.setBounds((this.getWidth()/35)*9, (this.getHeight()/18)*9, (this.getWidth()/35)*8, (this.getHeight()/18)*5);
		pCentral.add(bMulti);
		bTemporada.setBounds(((this.getWidth()/35)*18), (this.getHeight()/18)*9, ((this.getWidth()/35)*8), ((this.getHeight()/18)*5));
		pCentral.add(bTemporada);
		bMulti.setBounds(((this.getWidth()/35)*27), (int) ((this.getHeight()/18)*9.5), ((this.getWidth()/35)*7), ((this.getHeight()/18)*4));
		pCentral.add(bTrayectoria);

	}
}