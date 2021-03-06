package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.Coche;
import elementos.Escuderia;
import elementos.Temporada;
import elementos.Trayectoria;

public class VentanaCoche extends JFrame{

	private static final long serialVersionUID = 1L;
	
	Coche coche;
	private JButton bVolver, bMejorarAero, bMejorarMotor, bMejorarChasis;
	private PanelConImagenFondo imagen_fondo;
 
	public VentanaCoche (Temporada temp, int numCa, int modoJuego, int numTempo) throws SQLException { // 0 para trayectoria, 1 para temporada
		setTitle( "MI COCHE" );
		setSize(1600, 600); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
   
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
		pCentral.setPreferredSize( new Dimension(1600, 900));
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
					dispose();
					MenuPrincipalTrayectoria tra;
					try {
						tra = new MenuPrincipalTrayectoria(temp, numCa, 1, numTempo);
						tra.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					dispose();
					MenuPrincipalTemporada tem = new MenuPrincipalTemporada(temp, numCa, 1);
					tem.setVisible(true);
				} 				 
			}
		});
		
		// Botón Mejorar Aero 
		bMejorarAero = new JButton();
		bMejorarAero.setContentAreaFilled(false);
		bMejorarAero.setOpaque(false);
		
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
				if (numTempo == 0) { // Temporada
					if (Temporada.getEscuderia().getPresupuesto() >= 2500000) { // Comprobar presupuesto mayor que 2.500.000€
						if (Temporada.getEscuderia().getPiloto1().getCoche().getAerodinamica().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
							Temporada.getEscuderia().setPresupuesto(Temporada.getEscuderia().getPresupuesto() - 2500000);
							int rendi = Temporada.getEscuderia().getPiloto1().getCoche().getAerodinamica().getRendimiento() + 1;
							Temporada.getEscuderia().getPiloto1().getCoche().getAerodinamica().setRendimiento( rendi );
							try {
								dispose();
//								menu.setVisible(false);
								VentanaCoche vc = new VentanaCoche(temp, numCa, modoJuego, 0);
								vc.setLocation( getLocation() );
								vc.setSize( getSize() );
								vc.setVisible( true );
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
//							repaint();
//							System.out.println("funciona");
//							revalidate();
					}
				}
			} else {
				if (Trayectoria.getEscuderia().getPresupuesto() >= 2500000) { // Comprobar presupuesto mayor que 2.500.000€
					if (Trayectoria.getEscuderia().getPiloto1().getCoche().getAerodinamica().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
						Trayectoria.getEscuderia().setPresupuesto(Trayectoria.getEscuderia().getPresupuesto() - 2500000);
						int rendi = Trayectoria.getEscuderia().getPiloto1().getCoche().getAerodinamica().getRendimiento() + 1;
						Trayectoria.getEscuderia().getPiloto1().getCoche().getAerodinamica().setRendimiento( rendi );
						try {
							dispose();
//							menu.setVisible(false);
							VentanaCoche vc = new VentanaCoche(temp, numCa, modoJuego, numTempo);
							vc.setLocation( getLocation() );
							vc.setSize( getSize() );
							vc.setVisible( true );
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
			}
		}});
		
		// Botón Mejorar Motor 
		bMejorarMotor = new JButton();
		bMejorarMotor.setContentAreaFilled(false);
		bMejorarMotor.setOpaque(false);
		
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
				if (numTempo == 0) {
					if (Temporada.getEscuderia().getPresupuesto() >= 3000000) { // Comprobar presupuesto mayor que 3.000.000€
						if (Temporada.getEscuderia().getPiloto1().getCoche().getMotor().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
							Temporada.getEscuderia().setPresupuesto(Temporada.getEscuderia().getPresupuesto() - 3000000);
							int rendi = Temporada.getEscuderia().getPiloto1().getCoche().getMotor().getRendimiento() + 1;
							Temporada.getEscuderia().getPiloto1().getCoche().getMotor().setRendimiento( rendi );
							try {
								dispose();
								VentanaCoche vc = new VentanaCoche(temp, numCa, modoJuego, 0);
								vc.setLocation( getLocation() );
								vc.setSize( getSize() );
								vc.setVisible( true );
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
//							repaint();
						}
					}
				} else {
					if (Trayectoria.getEscuderia().getPresupuesto() >= 3000000) { // Comprobar presupuesto mayor que 3.000.000€
						if (Trayectoria.getEscuderia().getPiloto1().getCoche().getMotor().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
							Trayectoria.getEscuderia().setPresupuesto(Trayectoria.getEscuderia().getPresupuesto() - 3000000);
							int rendi = Trayectoria.getEscuderia().getPiloto1().getCoche().getMotor().getRendimiento() + 1;
							Trayectoria.getEscuderia().getPiloto1().getCoche().getMotor().setRendimiento( rendi );
							try {
								dispose();
								VentanaCoche vc = new VentanaCoche(temp, numCa, modoJuego, numTempo);
								vc.setLocation( getLocation() );
								vc.setSize( getSize() );
								vc.setVisible( true );
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			} 
		});
		
		// Botón Mejorar Chasis 
			bMejorarChasis = new JButton();
			bMejorarChasis.setContentAreaFilled(false);
			bMejorarChasis.setOpaque(false);
			
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
					if (numTempo == 0) {
						if (Temporada.getEscuderia().getPresupuesto() >= 2750000) { // Comprobar presupuesto mayor que 2.750.000€
							if (Temporada.getEscuderia().getPiloto1().getCoche().getChasis().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
								Temporada.getEscuderia().setPresupuesto(Temporada.getEscuderia().getPresupuesto() - 2750000);
								int rendi = Temporada.getEscuderia().getPiloto1().getCoche().getChasis().getRendimiento() + 1;
								Temporada.getEscuderia().getPiloto1().getCoche().getChasis().setRendimiento( rendi );
								try {
									dispose();
									VentanaCoche vc = new VentanaCoche(temp, numCa, modoJuego, 0);
									vc.setLocation( getLocation() );
									vc.setSize( getSize() );
									vc.setVisible( true );
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
//								repaint();
							}
						}
					} else {
						if (Trayectoria.getEscuderia().getPresupuesto() >= 2750000) { // Comprobar presupuesto mayor que 2.750.000€
							if (Trayectoria.getEscuderia().getPiloto1().getCoche().getChasis().getRendimiento() <= 110) { // Comprobar rendimiento máximo 110
								Trayectoria.getEscuderia().setPresupuesto(Trayectoria.getEscuderia().getPresupuesto() - 2750000);
								int rendi = Trayectoria.getEscuderia().getPiloto1().getCoche().getChasis().getRendimiento() + 1;
								Trayectoria.getEscuderia().getPiloto1().getCoche().getChasis().setRendimiento( rendi );
								try {
									dispose();
									VentanaCoche vc = new VentanaCoche(temp, numCa, modoJuego, numTempo);
									vc.setLocation( getLocation() );
									vc.setSize( getSize() );
									vc.setVisible( true );
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
//								repaint();
							}
						}
					}
				}
			});
		
		datos.setBounds(50, 60, 400, 200);
		pCentral.add(datos);
		pres.setBounds(1000, 100, 700, 75);
		pCentral.add(pres);
		bVolver.setBounds(720, 700, 160, 85);
		pCentral.add(bVolver);
		bMejorarAero.setBounds(50, 340, 400, 100);
		pCentral.add(bMejorarAero);
		bMejorarMotor.setBounds(50, 490, 400, 100);
		pCentral.add(bMejorarMotor);
		bMejorarChasis.setBounds(50, 640, 400, 100);
		pCentral.add(bMejorarChasis);
		
	}
}
