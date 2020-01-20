package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import elementos.Audio;
import elementos.BD;
import elementos.Circuito;
import elementos.Piloto;
import elementos.Temporada;


public class VentanaCarrera extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
//	JFrame MenuPrincipalTrayectoria;
//	JFrame VentanaClasifCarrera;
	Piloto piloto;
	private JButton bVolver; 
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondocarrera.jpg";
	private Audio musicaSemaforo;
	static boolean ejecutaHilo;	
	ArrayList<Temporada> listaTemporadas;
	 
	// modoJuego => 0 si es una trayectoria, 1 si es temporada
	public VentanaCarrera(Temporada temp, int numCarrera, int modoJuego, int numTemporada) throws SQLException {
//		VentanaClasifCarrera = m; 
		setTitle( "Trayectoria" );
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		ejecutaHilo = true;

		//FONDO DE LA VENTANA
		imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
		
		//DATOS
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();
		BD.usarCrearTablasBD(con);
//		BD.insertDatos(st);
		ArrayList<Circuito> circuitos = BD.listaCircuitosSelect(st);
		ArrayList<Piloto> pilotos = BD.listaPilotosSelect(st);
//		ArrayList<Escuderia> escuderias = BD.listaEscuderiasSelect(st);
		this.listaTemporadas = new ArrayList<Temporada>();

		
		//CREACION DEL PANEL PRINCIPAL Y LOS SUBPANELES
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		JPanel pInfo = new JPanel();
		pInfo.setBounds(150, 20, 1410, 80);
		pInfo.setOpaque(false);
//		pInfo.setBackground(Color.WHITE);
		pCentral.add(pInfo);
		
		JPanel pCarrera = new JPanel();
		pCarrera.setBounds(100, 105, 1500, 750);
		pCarrera.setOpaque(false);
//		pCarrera.setBackground(Color.BLACK);
		pCarrera.setLayout(null);
		pCentral.add(pCarrera);
		
		ArrayList<PanelConImagenFondo> paneles = new ArrayList<PanelConImagenFondo>();
		PanelConImagenFondo Hamilton = new PanelConImagenFondo();
		Hamilton.setName("Hamilton");
		paneles.add(Hamilton);
		PanelConImagenFondo Bottas = new PanelConImagenFondo();
		Bottas.setName("Bottas");
		paneles.add(Bottas);
		PanelConImagenFondo Vettel = new PanelConImagenFondo();
		Vettel.setName("Vettel");
		paneles.add(Vettel);
		PanelConImagenFondo Leclerc = new PanelConImagenFondo();
		Leclerc.setName("Leclerc");
		paneles.add(Leclerc);
		PanelConImagenFondo Verstappen = new PanelConImagenFondo();
		Verstappen.setName("Verstappen");
		paneles.add(Verstappen);
		PanelConImagenFondo Albon = new PanelConImagenFondo();
		Albon.setName("Albon");
		paneles.add(Albon);
		PanelConImagenFondo Sainz = new PanelConImagenFondo();
		Sainz.setName("Sainz");
		paneles.add(Sainz);
		PanelConImagenFondo Norris = new PanelConImagenFondo();
		Norris.setName("Norris");
		paneles.add(Norris);
		PanelConImagenFondo Ricciardo = new PanelConImagenFondo();
		Ricciardo.setName("Ricciardo");
		paneles.add(Ricciardo);
		PanelConImagenFondo Hulkenberg = new PanelConImagenFondo();
		Hulkenberg.setName("Hulkenberg");
		paneles.add(Hulkenberg);
		PanelConImagenFondo Gasly = new PanelConImagenFondo();
		Gasly.setName("Gasly");
		paneles.add(Gasly);
		PanelConImagenFondo Kvyat = new PanelConImagenFondo();
		Kvyat.setName("Kvyat");
		paneles.add(Kvyat);
		PanelConImagenFondo Perez = new PanelConImagenFondo();
		Perez.setName("Perez");
		paneles.add(Perez);
		PanelConImagenFondo Stroll = new PanelConImagenFondo();
		Stroll.setName("Stroll");
		paneles.add(Stroll);
		PanelConImagenFondo Magnussen = new PanelConImagenFondo();
		Magnussen.setName("Magnussen");
		paneles.add(Magnussen);
		PanelConImagenFondo Grosjean = new PanelConImagenFondo();
		Grosjean.setName("Grosjean");
		paneles.add(Grosjean);
		PanelConImagenFondo Raikkonen = new PanelConImagenFondo();
		Raikkonen.setName("Raikkonen");
		paneles.add(Raikkonen);
		PanelConImagenFondo Giovinazzi = new PanelConImagenFondo();
		Giovinazzi.setName("Giovinazzi");
		paneles.add(Giovinazzi);
		PanelConImagenFondo Russell = new PanelConImagenFondo();
		Russell.setName("Russell");
		paneles.add(Russell);
		PanelConImagenFondo Kubica = new PanelConImagenFondo();
		Kubica.setName("Kubica");
		paneles.add(Kubica);

		//HILO INICIAL DE SEMAFORO
		Thread hilo = new Thread(new Runnable() {			
			@Override
			public void run() {
				if(ejecutaHilo==true) {					
					String imagen = "/img/semaforo0.png";
					PanelConImagenFondo panel_semaforo = new PanelConImagenFondo();
					panel_semaforo.setImage(imagen);
					panel_semaforo.setOpaque(false);
					panel_semaforo.setBounds(600, 150, 400, 100);
					pCentral.add(panel_semaforo);
					try {
						Thread.sleep(900);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					musicaSemaforo = new Audio("/audio/semaforo.wav");
					musicaSemaforo.start();
					String imagen1 = "/img/semaforo1.png";
					panel_semaforo.setImage(imagen1);
					panel_semaforo.setOpaque(false);
					panel_semaforo.setBounds(600, 150, 400, 100);
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String imagen2 = "/img/semaforo2.png";
					panel_semaforo.setImage(imagen2);
					panel_semaforo.setOpaque(false);
					panel_semaforo.setBounds(600, 150, 400, 100);
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String imagen3 = "/img/semaforo3.png";
					panel_semaforo.setImage(imagen3);
					panel_semaforo.setOpaque(false);
					panel_semaforo.setBounds(600, 150, 400, 100);
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String imagen4 = "/img/semaforo4.png";
					panel_semaforo.setImage(imagen4);
					panel_semaforo.setOpaque(false);
					panel_semaforo.setBounds(600, 150, 400, 100);
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel_semaforo.setImage(imagen);
					panel_semaforo.setOpaque(false);
					panel_semaforo.setBounds(600, 150, 400, 100);
					repaint();
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					panel_semaforo.setVisible(false);;
					ejecutaHilo = false;
				}							
			}
		});		
		hilo.start();
		
		//PANEL CARRERA - CrearPilotos-Coches
		for (int i = 0; i < pilotos.size(); i++) {
			String img = pilotos.get(i).getImagen2();
			if (i==0) {
				Hamilton.setImage(img);
				Hamilton.setBounds(5, 0, 1250, 37);
				Hamilton.setOpaque(false);
				pCarrera.add(Hamilton);
			}else if (i==1) {
				Bottas.setImage(img);
				Bottas.setBounds(5, 37, 1250, 37);
				Bottas.setOpaque(false);
				pCarrera.add(Bottas);
			}else if (i==2) {
				Vettel.setImage(img);
				Vettel.setBounds(5, 74, 1250, 37);
				Vettel.setOpaque(false);
				pCarrera.add(Vettel);
			}else if (i==3) {
				Leclerc.setImage(img);
				Leclerc.setBounds(5, 111, 1250, 37);
				Leclerc.setOpaque(false);
				pCarrera.add(Leclerc);
			}else if (i==4) {
				Verstappen.setImage(img);
				Verstappen.setBounds(5, 148, 1250, 37);
				Verstappen.setOpaque(false);
				pCarrera.add(Verstappen);
			}else if (i==5) {
				Albon.setImage(img);
				Albon.setBounds(5, 185, 1250, 37);
				Albon.setOpaque(false);
				pCarrera.add(Albon);
			}else if (i==6) {
				Sainz.setImage(img);
				Sainz.setBounds(5, 222, 1250, 37);
				Sainz.setOpaque(false);
				pCarrera.add(Sainz);
			}else if (i==7) {
				Norris.setImage(img);
				Norris.setBounds(5, 259, 1250, 37);
				Norris.setOpaque(false);
				pCarrera.add(Norris);
			}else if (i==8) {
				Ricciardo.setImage(img);
				Ricciardo.setBounds(5, 296, 1250, 37);
				Ricciardo.setOpaque(false);
				pCarrera.add(Ricciardo);
			}else if (i==9) {
				Hulkenberg.setImage(img);
				Hulkenberg.setBounds(5, 333, 1250, 37);
				Hulkenberg.setOpaque(false);
				pCarrera.add(Hulkenberg);
			}else if (i==10) {
				Gasly.setImage(img);
				Gasly.setBounds(5, 370, 1250, 37);
				Gasly.setOpaque(false);
				pCarrera.add(Gasly);
			}else if (i==11) {
				Kvyat.setImage(img);
				Kvyat.setBounds(5, 407, 1250, 37);
				Kvyat.setOpaque(false);
				pCarrera.add(Kvyat);
			}else if (i==12) {
				Perez.setImage(img);
				Perez.setBounds(5, 444, 1250, 37);
				Perez.setOpaque(false);
				pCarrera.add(Perez);
			}else if (i==13) {
				Stroll.setImage(img);
				Stroll.setBounds(5, 481, 1250, 37);
				Stroll.setOpaque(false);
				pCarrera.add(Stroll);
			}else if (i==14) {
				Magnussen.setImage(img);
				Magnussen.setBounds(5, 518, 1250, 37);
				Magnussen.setOpaque(false);
				pCarrera.add(Magnussen);
			}else if (i==15) {
				Grosjean.setImage(img);
				Grosjean.setBounds(5, 555, 1250, 37);
				Grosjean.setOpaque(false);
				pCarrera.add(Grosjean);
			}else if (i==16) {
				Raikkonen.setImage(img);
				Raikkonen.setBounds(5, 592, 1250, 37);
				Raikkonen.setOpaque(false);
				pCarrera.add(Raikkonen);
			}else if (i==17) {
				Giovinazzi.setImage(img); 
				Giovinazzi.setBounds(5, 629, 1250, 37);
				Giovinazzi.setOpaque(false);
				pCarrera.add(Giovinazzi);
			}else if (i==18) {
				Russell.setImage(img);
				Russell.setBounds(5, 666, 1250, 37);
				Russell.setOpaque(false);
				pCarrera.add(Russell); 
			}else if (i==19) {
				Kubica.setImage(img);
				Kubica.setBounds(5, 703, 1250, 37);
				Kubica.setOpaque(false);
				pCarrera.add(Kubica);
			}
		}
		
		//CREACION DE BOTON VOLVER (AL FINALIZAR CARRERA)		
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
				if (modoJuego == 0) { // Modo Trayectoria
//					try {
//						MenuPrincipalTrayectoria v = new MenuPrincipalTrayectoria(temp, numCarrera + 1, 1);
//						v.setVisible( true );
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
				} else { // Modo Temporada
					if (numCarrera < 20) { // No ha sido la última carrera
						MenuPrincipalTemporada v = new MenuPrincipalTemporada(temp, numCarrera + 1, 1);
						v.setVisible( true );
					} else { // Ha sido la última carrera de la temporada
						
					}
				}
			}					
		});
		
	///DESDEAQUI
		// SIMULACIÓN DE LA CARRERA
		temp.simularCarreraTemporada( numCarrera );
		
		// Comprobación de los resultados de la carrera
		temp.getListaCarreras().get(numCarrera - 1).comprobarResultadoCarrera();
				
		Thread carrera = new Thread ( new Runnable() {			
			private String nombre;
			@Override
			public void run() {
				try {
					Thread.sleep(6250);
					for (int vuelta=0; vuelta < temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(); vuelta++) {
						//Recorrer la lista de pilotos ordenada segun posiciones en carrera.
						ArrayList<Piloto> posPilotos = temp.getListaCarreras().get(numCarrera-1).posPilotos();
						for (int pil=0; pil < posPilotos.size(); pil++) {
							//Recorrer paneles de pilotos
							for (PanelConImagenFondo panel : paneles) {	
								nombre = panel.toString();
								if (posPilotos.get(pil).toString().equals(nombre)) {
									if (pil==0) {
										panel.setBounds(panel.getX() + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==1) {
										panel.setBounds(panel.getX()  - 1 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==2) {
										panel.setBounds(panel.getX() - 2 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==3) {
										panel.setBounds(panel.getX() - 3 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==4) {
										panel.setBounds(panel.getX() - 4 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==5) {
										panel.setBounds(panel.getX() - 5 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==6) {
										panel.setBounds(panel.getX() -6 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==7) {
										panel.setBounds(panel.getX() -7 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==8) {
										panel.setBounds(panel.getX() -8 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==9) {
										panel.setBounds(panel.getX() -9 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==10) {
										panel.setBounds(panel.getX() - 10 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==11) {
										panel.setBounds(panel.getX() - 11 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==12) {
										panel.setBounds(panel.getX() - 12 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==13) {
										panel.setBounds(panel.getX() - 13 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==14) {
										panel.setBounds(panel.getX() - 14 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==15) {
										panel.setBounds(panel.getX() - 15 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==16) {
										panel.setBounds(panel.getX() - 16 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==17) {
										panel.setBounds(panel.getX() - 17 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==18) {
										panel.setBounds(panel.getX() - 18 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());
										repaint();
										revalidate();
									}
									if (pil==19) {
										panel.setBounds(panel.getX() - 19 + (pCarrera.getWidth() - 110) / temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas(), 
												panel.getY(), panel.getWidth(), panel.getHeight());							
										repaint();
										revalidate();
									}
								}
							}
						}					
						//TIEMPO ENTRE VUELTAS
						try {
							Thread.sleep(200);   
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//Cuando termina carrera - Saltar a clasificacion
						if (vuelta == temp.getListaCarreras().get(numCarrera-1).getCircuito().getVueltas()-1) {
							Thread.sleep(750);
							dispose();
							if (modoJuego == 0) { // Modo Trayectoria
								try {
									if (numCarrera < 21) {
										MenuPrincipalTrayectoria v = new MenuPrincipalTrayectoria(temp, numCarrera + 1, 1, numTemporada);
										v.setVisible( true );
									} else { // Cambio de temporada
										MenuPrincipalTrayectoria v = new MenuPrincipalTrayectoria(new Temporada(2020, BD.listaPilotosSelect(st), BD.listaEscuderiasSelect(st)), 50, 1, numTemporada);
										v.setVisible( true );
									}
									
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							} else { // Modo Temporada
								if (numCarrera < 21) { // No ha sido la última carrera
									MenuPrincipalTemporada v = new MenuPrincipalTemporada(temp, numCarrera + 1, 1);
									v.setVisible( true );
								} else { // Ha sido la última carrera de la temporada
									VentanaClasifPiloto v = new VentanaClasifPiloto(temp, 1, 50, 0); // 50 porque se ha acabado, para finalizar
									v.setVisible( true );
								}
							}
						}
					}
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}				
		});
		carrera.start();
		
		
//		// Creación Progress Bar para dibujar transcurso carrera
//		JProgressBar j1 = new JProgressBar();
//		j1.setString( "Hamilton" );
//		JProgressBar j2 = new JProgressBar();
//		j2.setString( "Bottas" );
//		JProgressBar j3 = new JProgressBar();
//		j3.setString( "Vettel" );
//		JProgressBar j4 = new JProgressBar();
//		j4.setString( "Leclerc" );
//		JProgressBar j5 = new JProgressBar();
//		j5.setString( "Verstappen" );
//		JProgressBar j6 = new JProgressBar();
//		j6.setString( "Albon" );
//		JProgressBar j7 = new JProgressBar();
//		j7.setString( "Sainz" );
//		JProgressBar j8 = new JProgressBar();
//		j8.setString( "Norris" );
//		JProgressBar j9 = new JProgressBar();
//		j9.setString( "Ricciardo" );
//		JProgressBar j10 = new JProgressBar();
//		j10.setString( "Hulkenberg" );
//		JProgressBar j11 = new JProgressBar();
//		j11.setString( "Gasly" );
//		JProgressBar j12 = new JProgressBar();
//		j12.setString( "Kyvat" );
//		JProgressBar j13 = new JProgressBar();
//		j13.setString( "Perez" );
//		JProgressBar j14 = new JProgressBar();
//		j14.setString( "Stroll" );
//		JProgressBar j15 = new JProgressBar();
//		j15.setString( "Magnussen" );
//		JProgressBar j16 = new JProgressBar();
//		j16.setString( "Grosjean" );
//		JProgressBar j17 = new JProgressBar();
//		j17.setString( "Raikkonen" );
//		JProgressBar j18 = new JProgressBar();
//		j18.setString( "Giovinazzi" );
//		JProgressBar j19 = new JProgressBar();
//		j19.setString( "Russell" );
//		JProgressBar j20 = new JProgressBar();
//		j20.setString( "Kubica" );
//		
//
//		
//		
//		ArrayList<JProgressBar> listaEtiquetasBarra = new ArrayList<JProgressBar>(Arrays.asList(j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12,
//				j13, j14, j15, j16, j17, j18, j19, j20));
//		
//		float listaBarras[] = new float[20];
//
//		float contador = (float)2.0;
//		
//		for (int i = 0; i < 20; i++) {
//			String p = temp.getListaCarreras().get( numCarrera - 1 ).getListaPilotos().get(i).getNombre();
//			for (int j = 0; j < listaEtiquetasBarra.size(); i++) {
//				if ( p.equals( listaEtiquetasBarra.get( j ).getString() ) ) {
//					listaBarras[ j ] = contador;
//				}
//			}
//			contador -= 0.1;
//		}
//		
//		System.out.println( listaBarras );
		

		//FUENTE
		Font font = new Font("Verdana", Font.BOLD, 45);
		
		//INFO CARRERA PARTE SUPERIOR
		JTextArea infoCarrera = new JTextArea(//BD.circuitoSelect(st, numCarrera).getImagen() + 
				BD.circuitoSelect(st, numCarrera).getNombre() + " -> " + BD.circuitoSelect(st, numCarrera).getVueltas() +
				" vueltas");
		infoCarrera.setForeground(Color.BLACK);
		infoCarrera.setEditable(false);
		infoCarrera.setOpaque(false);
//		infoCarrera.setBackground(null);
		infoCarrera.setFont(font);
		
		//IMAGEN
		PanelConImagenFondo imagenCarrera = new PanelConImagenFondo();
		String img = circuitos.get(numCarrera - 1).getImagen();
		imagenCarrera.setImage(img);
		imagenCarrera.setOpaque(false);
		
		//POSICION
		pInfo.add(infoCarrera);
		imagenCarrera.setBounds(220, 0, 150, 100);
		pCentral.add(imagenCarrera);
//		bVolver.setBounds((this.getWidth()/20) * 9, (this.getHeight()/10) * 8, (this.getWidth()/20) * 2, this.getHeight()/10);
//		bVolver.setVisible( true );
//		pCentral.add(bVolver);		

		
	}
	public ArrayList<Temporada> getListaTemporadas() {
		return listaTemporadas;
	}

	public void setListaTemporadas(ArrayList<Temporada> listaTemporadas) {
		this.listaTemporadas = listaTemporadas;
	}
}
