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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.BD;
import elementos.Coche;
import elementos.Escuderia;
import elementos.Mejora;
import elementos.Piloto;
import elementos.Trayectoria;


public class VentanaCoche extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	Coche coche;
	private PanelConImagenFondo imagen_fondo;
 
	public VentanaCoche (JFrame m) throws SQLException {
		MenuPrincipalTrayectoria = m;
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		Label etiqueta = new Label(Trayectoria.getEscuderia().getPiloto1().getCoche().toString2());
		pSuperior.add(etiqueta); 
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pSuperior, BorderLayout.NORTH); 
		pInferior.add(bok);
		
		//FONDO DE LA VENTANA
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		String imagen = "";
		imagen += Trayectoria.getEscuderia().getPiloto1().getCoche().getImagen();
		imagen_fondo.setImage(imagen);
		
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		JPanel pCentral = new JPanel();
		JPanel pDatos = new JPanel();
		JPanel pPresupuesto = new JPanel();
		pCentral.add(pDatos);
		pCentral.add(pPresupuesto);
		getContentPane().add(pCentral);
		Font font = new Font("Verdana", Font.BOLD, 39);

//		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
//		Statement st = con.createStatement();
//		ArrayList<Piloto> pilotos = BD.listaPilotosSelect(st);

		//Imprimimos los datos del coche y sus mejoras
		Escuderia escuderia_seleccionada = Trayectoria.getEscuderia();
		Integer presupuesto = escuderia_seleccionada.getPresupuesto();
		
		JTextArea pr = new JTextArea(presupuesto.toString());
		pr.setEditable(false);
		pr.setBackground(Color.LIGHT_GRAY);
		pPresupuesto.add(pr);
		pr.setFont(font);
			
		
		Coche coche_seleccionado = Trayectoria.getEscuderia().getPiloto1().getCoche();		
	
		JTextArea da = new JTextArea(coche_seleccionado.toString2());
		da.setEditable(false);
		da.setBackground(Color.LIGHT_GRAY);
		pDatos.add(da);
		da.setFont(font);
				
		//Escuchadores
		bok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MenuPrincipalTrayectoria.setVisible( true );
				
			}			
		}); 
	}
	
//	public void dibujar (Graphics g) {
//		g.drawImage(coche_seleccionado.getImagen(), 0, 0, null);
//	}
}
