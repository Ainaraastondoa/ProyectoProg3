package visual;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JTextArea;

import elementos.BD;
import elementos.Coche;
import elementos.Escuderia;
import elementos.Mejora;
import elementos.Piloto;
import elementos.Temporada;
import elementos.Trayectoria;


public class VentanaCoche extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	Coche coche;
	private JButton bVolver;
	private PanelConImagenFondo imagen_fondo;
 
	public VentanaCoche (JFrame m) throws SQLException {
		MenuPrincipalTrayectoria = m;
		setTitle( "MI COCHE" );
		setSize(m.getWidth(), m.getHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Label etiqueta;
		try {
			etiqueta = new Label(Trayectoria.getEscuderia().getPiloto1().getCoche().toString2());
		} catch (NullPointerException e) {
			etiqueta = new Label(Temporada.getEscuderia().getPiloto1().getCoche().toString2());
		}
		

		//FONDO DE LA VENTANA
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		String imagen = "";
		try {
			imagen += Trayectoria.getEscuderia().getPiloto1().getCoche().getImagen();
		} catch (NullPointerException e) {
			imagen += Temporada.getEscuderia().getPiloto1().getCoche().getImagen();
		}
		imagen_fondo.setImage(imagen);
		
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		//CREACI�N DEL PANEL QUE ALBERGA LOS DATOS Y BOTONES
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		//FUENTE
		Font font = new Font("Verdana", Font.BOLD, 39);

		//DATOS
//		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
//		Statement st = con.createStatement();
//		ArrayList<Piloto> pilotos = BD.listaPilotosSelect(st);

		//Imprimimos los datos del coche
		Escuderia escuderia_seleccionada = Trayectoria.getEscuderia();
		if (escuderia_seleccionada==null) {
			escuderia_seleccionada = Temporada.getEscuderia();
		}
		
		//PRESUPUESTO DEL EQUIPO
		Integer presupuesto = escuderia_seleccionada.getPresupuesto();		
		
		JTextArea pres = new JTextArea("Presupuesto: " + presupuesto.toString());
		pres.setEditable(false);
		pres.setBackground(Color.LIGHT_GRAY);
		pres.setFont(font);
				
		//CARACTERISTICAS COCHE
		Coche coche_seleccionado;
		try {
			coche_seleccionado = Trayectoria.getEscuderia().getPiloto1().getCoche();
		} catch (NullPointerException e) {
			coche_seleccionado = Temporada.getEscuderia().getPiloto1().getCoche();
		}
		
		JTextArea datos = new JTextArea(coche_seleccionado.toString2());
		datos.setEditable(false);
		datos.setBackground(Color.LIGHT_GRAY);
		datos.setFont(font);

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
			public void actionPerformed(ActionEvent e) {
				dispose();
				MenuPrincipalTrayectoria.setVisible( true );
			}					
		});
		datos.setBounds(this.getWidth()/20, this.getHeight()/15, 425, 175);
		pCentral.add(datos);
		pres.setBounds(this.getWidth()/3, this.getHeight()/10, 575, 65);
		pCentral.add(pres);
		bVolver.setBounds((this.getWidth()/20) * 9, (this.getHeight()/10) * 8, (this.getWidth()/20) * 2, this.getHeight()/10);
		pCentral.add(bVolver);
	}
}
