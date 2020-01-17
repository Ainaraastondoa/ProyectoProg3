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
	
//	JFrame MenuPrincipalTrayectoria;
	Coche coche;
	private JButton bVolver, bMejorarAero, bMejorarMotor, bMejorarChasis;
	private PanelConImagenFondo imagen_fondo;
 
	public VentanaCoche (Temporada temp, int modoJuego, int numCa) throws SQLException { // 0 para trayectoria, 1 para temporada
//		MenuPrincipalTrayectoria = m;
		setTitle( "MI COCHE" );
		setSize(1600, 600);
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
//		pCentral.setLayout(null);
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
		bVolver.setOpaque(false);
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
				if (modoJuego==0) { // trayectoria
//					dispose();
//					MenuPrincipalTrayectoria v = new MenuPrincipalTrayectoria();
//					v.setVisible( true );
				} else {
					dispose();
					MenuPrincipalTemporada v = new MenuPrincipalTemporada(temp, numCa);
					v.setVisible( true );
				}
			}					
		});
		
		// Botón Mejorar Aero (NO SE VE)
		bMejorarAero = new JButton();
		bMejorarAero.setContentAreaFilled(false);
		bMejorarAero.setOpaque(false);
		
		// Poner imagen al botón (GORKA)
		bMejorarAero.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/mejoraraero2.png"));
		    	bMejorarAero.setIcon(icono3);
		    }
		    public void mouseExited(MouseEvent e) {
		    	ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/mejoraraero.png"));
		    	bMejorarAero.setIcon(icono3);
		    }
		});
		ImageIcon icono3 = new ImageIcon(getClass().getResource("/img/mejoraraero.png"));
		bMejorarAero.setIcon(icono3);
		bMejorarAero.setBorder( null );
		bMejorarAero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Temporada.getEscuderia().getPresupuesto() >= 2500000) { // Comprobar presupuesto mayor que 2.500.000€
					if (Temporada.getEscuderia().getPiloto1().getCoche().getAerodinamica().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
						Temporada.getEscuderia().setPresupuesto(Temporada.getEscuderia().getPresupuesto() - 2500000);
						int rendi = Temporada.getEscuderia().getPiloto1().getCoche().getAerodinamica().getRendimiento() + 1;
						Temporada.getEscuderia().getPiloto1().getCoche().getAerodinamica().setRendimiento( rendi );
						repaint();
						System.out.println("funciona");
						revalidate();
					}
				}
			}
		});
		
		// Botón Mejorar Motor (NO SE VE)
		bMejorarMotor = new JButton();
		bMejorarMotor.setContentAreaFilled(false);
		bMejorarMotor.setOpaque(false);
		
		// Poner imagen al botón (GORKA)
		bMejorarMotor.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/mejorarmotor2.png"));
		    	bMejorarMotor.setIcon(icono4);
		    }
		    public void mouseExited(MouseEvent e) {
		    	ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/mejorarmotor.png"));
		    	bMejorarMotor.setIcon(icono4);
		    }
		});
		ImageIcon icono4 = new ImageIcon(getClass().getResource("/img/mejorarmotor.png"));
		bMejorarMotor.setIcon(icono4);
		bMejorarMotor.setBorder( null );
		bMejorarMotor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Temporada.getEscuderia().getPresupuesto() >= 3000000) { // Comprobar presupuesto mayor que 3.000.000€
					if (Temporada.getEscuderia().getPiloto1().getCoche().getMotor().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
						Temporada.getEscuderia().setPresupuesto(Temporada.getEscuderia().getPresupuesto() - 3000000);
						int rendi = Temporada.getEscuderia().getPiloto1().getCoche().getMotor().getRendimiento() + 1;
						Temporada.getEscuderia().getPiloto1().getCoche().getMotor().setRendimiento( rendi );
						repaint();
					}
				}
			}
		});
		
		// Botón Mejorar Chasis (NO SE VE)
			bMejorarChasis = new JButton();
			bMejorarChasis.setContentAreaFilled(false);
			bMejorarChasis.setOpaque(false);
			
			// Poner imagen al botón (GORKA)
			bMejorarChasis.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			    	ImageIcon icono5 = new ImageIcon(getClass().getResource("/img/mejorarchasis2.png"));
			    	bMejorarChasis.setIcon(icono5);
			    }
			    public void mouseExited(MouseEvent e) {
			    	ImageIcon icono5 = new ImageIcon(getClass().getResource("/img/mejorarchasis.png"));
			    	bMejorarChasis.setIcon(icono5);
			    }
			});
			ImageIcon icono5 = new ImageIcon(getClass().getResource("/img/mejorarchasis.png"));
			bMejorarChasis.setIcon(icono5);
			bMejorarChasis.setBorder( null );
			bMejorarChasis.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (Temporada.getEscuderia().getPresupuesto() >= 2750000) { // Comprobar presupuesto mayor que 2.750.000€
						if (Temporada.getEscuderia().getPiloto1().getCoche().getChasis().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
							Temporada.getEscuderia().setPresupuesto(Temporada.getEscuderia().getPresupuesto() - 2750000);
							int rendi = Temporada.getEscuderia().getPiloto1().getCoche().getChasis().getRendimiento() + 1;
							Temporada.getEscuderia().getPiloto1().getCoche().getChasis().setRendimiento( rendi );
							repaint();
						}
					}
				}
			});
		
		datos.setBounds(100, 110, 400, 200);
		pCentral.add(datos);
		pres.setBounds(this.getWidth()/3, this.getHeight()/10, 575, 65);
		pCentral.add(pres);
		bVolver.setBounds((this.getWidth()/20) * 9, (this.getHeight()/10) * 8, (this.getWidth()/20) * 2, this.getHeight()/10);
		pCentral.add(bVolver);
		bMejorarAero.setBounds(390, this.getHeight()/5, (this.getWidth()/20) * 2, this.getHeight()/10);
		pCentral.add(bMejorarAero);
		bMejorarMotor.setBounds(540, this.getHeight()/5, (this.getWidth()/20) * 2, this.getHeight()/10);
		pCentral.add(bMejorarMotor);
		bMejorarChasis.setBounds(690, this.getHeight()/5, (this.getWidth()/20) * 2, this.getHeight()/10);
		pCentral.add(bMejorarChasis);
		
	}
}
