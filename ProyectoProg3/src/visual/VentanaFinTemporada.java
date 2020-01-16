package visual;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import elementos.Temporada;

public class VentanaFinTemporada extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton bOK, bClasif;
	private PanelConImagenFondo imagenFondo;
	private String fondo = "/img/FONDO.jpg"; // Hay que poner foto
	
	public VentanaFinTemporada(Temporada temp) {
		// Creación ventana
		setTitle( "Final de la Temporada" );
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Fondo
		imagenFondo = new PanelConImagenFondo();
//		imagenFondo.setImage( fondo );
//		setContentPane( imagenFondo );
		
		// Creación del panel para botones
		JPanel panel = new JPanel();
		panel.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ) );
		panel.setOpaque( false );
		panel.setLayout(null);
		getContentPane().add( panel );
		
		// Creación de los botones
		// Botón OK
		bOK = new JButton();
		bOK.setContentAreaFilled( false );
		bOK.setOpaque( false );
		bOK.addMouseListener( new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/inicio2.png")); // CAMBIAR POR OK
		    	bOK.setIcon(icono);	    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/inicio.png")); // CAMBIAR POR OK
		    	bOK.setIcon(icono);
		    }
		});
		ImageIcon icono = new ImageIcon(getClass().getResource("/img/inicio.png")); // CAMBIAR POR OK
		bOK.setIcon(icono);
		bOK.setBorder( null );
		bOK.addActionListener( new ActionListener() {
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
		
		// Botón CLASIFICACIÓN
		bClasif = new JButton();
		bClasif.setContentAreaFilled(false);
		bClasif.setOpaque(false);
		bClasif.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/clasificacion2.png"));
		    	bClasif.setIcon(icono);	    
			}
			public void mouseExited(MouseEvent e) {
		    	ImageIcon icono = new ImageIcon(getClass().getResource("/img/clasificacion.png"));
		    	bClasif.setIcon(icono);
		    }
		});
		ImageIcon icono2 = new ImageIcon(getClass().getResource("/img/clasificacion.png"));
		bClasif.setIcon(icono2);
		bClasif.setBorder( null );
		bClasif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasifPiloto v = new VentanaClasifPiloto(temp, 2);
				v.setLocation( getLocation() );
				v.setSize( getSize() );
				v.setVisible( true );
				VentanaFinTemporada.this.dispose();
			}
		});
		
		// Posicionar botones y añadirlos al panel central
		bOK.setBounds((this.getWidth()/25) * 2, (this.getHeight()/92) * 66,
				(this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		panel.add( bOK );
		bClasif.setBounds((this.getWidth()/25) * 18, (this.getHeight()/92) * 66,
				(this.getWidth()/25) * 5, (this.getHeight()/92)*19);
		panel.add( bClasif );
	}
}
