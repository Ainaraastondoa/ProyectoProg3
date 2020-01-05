package visual;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import elementos.BD;
import elementos.Escuderia;
import elementos.Piloto;
import elementos.Trayectoria;

public class VentanaSelEscuderia extends JFrame{
	
	JFrame VentanaInicial;
	private JPanel pCentral;
	private JPanel pInferior;
	private JButton bVolver;
//	private PanelConImagenFondo imagen_fondo;
//	private String fondo = "/img/selesc.png";
	
	public VentanaSelEscuderia(JFrame m) throws SQLException {		
		VentanaInicial = m;
		setTitle("Selecciona tu escuderia");
		
		//FONDO DE LA VENTANA
//		imagen_fondo = new PanelConImagenFondo();
//		imagen_fondo.setImage(fondo);
//		setContentPane(imagen_fondo);
//		setLayout(new FlowLayout());
		
		//PANELES
		pCentral = new JPanel();
		pInferior = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		
		//DATOS
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();	
		ArrayList<Escuderia> listaEscuderias = BD.listaEscuderiasSelect(st);	
		
		//BOTONES (Escuderias + Volver)
		for (Escuderia escuderia : listaEscuderias) {
			//BOTON ESCUDERIA
			JButton bEscuderia = new JButton(escuderia.getNombre());
			pCentral.add(bEscuderia);
//			bEscuderia.setSize(200, 200);
//			bEscuderia.setBounds(0, 0, 200,200);
			bEscuderia.setBackground(new Color(0,0,0,0));
			
			bEscuderia.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
//			    	bEscuderia.setIcon(null);
//			    	revalidate();
					String imagen = "";
					imagen += escuderia.getImagen2();
			    	ImageIcon icono = new ImageIcon(getClass().getResource(imagen));
			    	bEscuderia.setIcon(icono);
			    }
			    public void mouseExited(MouseEvent e) {
//			    	bEscuderia.setIcon(null);
//			    	revalidate();
			    	String imagen = "";
					imagen += escuderia.getImagen1();
			    	ImageIcon icono = new ImageIcon(getClass().getResource(imagen));
			    	bEscuderia.setIcon(icono);
			    }
			});    	
//			bEscuderia.setIcon(null);
//			revalidate();
			String imagen = "";
			imagen += escuderia.getImagen1();
			ImageIcon icono = new ImageIcon(getClass().getResource(imagen));
			bEscuderia.setIcon(icono);
			bEscuderia.setBorder(null);
			
			bEscuderia.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MenuPrincipalTrayectoria menu;
					try {
						menu = new MenuPrincipalTrayectoria( VentanaSelEscuderia.this );
						menu.setLocation( getLocation() );
						menu.setSize( getSize() );
						menu.setVisible( true );
						VentanaSelEscuderia.this.setVisible( false );
						
						//Escuderia seleccionada
						Trayectoria.setEscuderia(escuderia);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}				
			});	
		}		
		pCentral.setBackground(Color.DARK_GRAY);
		pCentral.setLayout(new GridLayout(4,5));
		
		//BOTON VOLVER
		bVolver = new JButton("Volver");
		pInferior.add(bVolver);
		//Escuchadores
		bVolver.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				VentanaInicial.setVisible( true );				
			}			
		}); 		
	}
}

