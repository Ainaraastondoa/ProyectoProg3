package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JTextArea;

import elementos.BD;
import elementos.Carrera;
import elementos.Circuito;
import elementos.Escuderia;
import elementos.Piloto;
import elementos.Temporada;
import elementos.Trayectoria;


public class VentanaCarrera extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
//	JFrame MenuPrincipalTrayectoria;
//	JFrame VentanaClasifCarrera;
	Piloto piloto;
	private JButton bVolver;
	private PanelConImagenFondo imagen_fondo;
	private String fondo = "/img/fondocarrera.jpg";
	private PanelConImagenFondo panel_semaforo;
	static boolean ejecutaHilo = true;
	
	ArrayList<Temporada> listaTemporadas;
	 
	// modoJuego => 0 si es una trayectoria, 1 si es temporada
	public VentanaCarrera(Temporada temp, int numCarrera, int modoJuego) throws SQLException {
//		VentanaClasifCarrera = m; 
		setTitle( "Trayectoria" );
		setSize(1600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

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
		ArrayList<Escuderia> escuderias = BD.listaEscuderiasSelect(st);
		this.listaTemporadas = new ArrayList<Temporada>();

		
		//CREACION DEL PANEL PRINCIPAL Y LOS SUBPANELES
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		JPanel pInfo = new JPanel();
		pInfo.setBounds(0, 0, 1250, 100);
		pInfo.setOpaque(false);
//		pInfo.setBackground(Color.WHITE);
		pCentral.add(pInfo);
		
		JPanel pClasificacion = new JPanel();
		pClasificacion.setBounds(1250, 0, 350, 900);
		pClasificacion.setOpaque(false);
//		pClasificacion.setBackground(Color.DARK_GRAY);
		pCentral.add(pClasificacion);
		
		JPanel pCarrera = new JPanel();
		pCarrera.setBounds(0, 100, 1250, 800);
		pCarrera.setOpaque(false);
//		pCarrera.setBackground(Color.BLACK);
		pCarrera.setLayout(null);
		pCentral.add(pCarrera);
		
		PanelConImagenFondo p1 = new PanelConImagenFondo();
		PanelConImagenFondo p2 = new PanelConImagenFondo();
		PanelConImagenFondo p3 = new PanelConImagenFondo();
		PanelConImagenFondo p4 = new PanelConImagenFondo();
		PanelConImagenFondo p5 = new PanelConImagenFondo();
		PanelConImagenFondo p6 = new PanelConImagenFondo();
		PanelConImagenFondo p7 = new PanelConImagenFondo();
		PanelConImagenFondo p8 = new PanelConImagenFondo();
		PanelConImagenFondo p9 = new PanelConImagenFondo();
		PanelConImagenFondo p10 = new PanelConImagenFondo();
		PanelConImagenFondo p11 = new PanelConImagenFondo();
		PanelConImagenFondo p12 = new PanelConImagenFondo();
		PanelConImagenFondo p13 = new PanelConImagenFondo();
		PanelConImagenFondo p14 = new PanelConImagenFondo();
		PanelConImagenFondo p15 = new PanelConImagenFondo();
		PanelConImagenFondo p16 = new PanelConImagenFondo();
		PanelConImagenFondo p17 = new PanelConImagenFondo();
		PanelConImagenFondo p18 = new PanelConImagenFondo();
		PanelConImagenFondo p19 = new PanelConImagenFondo();
		PanelConImagenFondo p20 = new PanelConImagenFondo();
		

		//HILO INICIAL DE SEMAFORO
		Thread hilo = new Thread(new Runnable() {			
			@Override
			public void run() {
				while(ejecutaHilo) {
					for (int i=0; i<5; i++) {
						String imagen = "/img/semaforo" + i + ".png";
						panel_semaforo.setImage(imagen);
						panel_semaforo.setBounds(500, 150, 400, 100);
						setContentPane(panel_semaforo);

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}			
			}
		});		
		hilo.start();
		ejecutaHilo = false;
		
		//PANEL CARRERA - CrearPilotos-Coches
		for (int i = 0; i < pilotos.size(); i++) {
			String img = pilotos.get(i).getImagen2();
			if (i==0) {
				p1.setImage(img);
				p1.setBounds(0, 0, 1250, 37);
				p1.setOpaque(false);
				pCarrera.add(p1);
			}else if (i==1) {
				p2.setImage(img);
				p2.setBounds(0, 37, 1250, 37);
				p2.setOpaque(false);
				pCarrera.add(p2);
			}else if (i==2) {
				p3.setImage(img);
				p3.setBounds(0, 74, 1250, 37);
				p3.setOpaque(false);
				pCarrera.add(p3);
			}else if (i==3) {
				p4.setImage(img);
				p4.setBounds(0, 111, 1250, 37);
				p4.setOpaque(false);
				pCarrera.add(p4);
			}else if (i==4) {
				p5.setImage(img);
				p5.setBounds(0, 148, 1250, 37);
				p5.setOpaque(false);
				pCarrera.add(p5);
			}else if (i==5) {
				p6.setImage(img);
				p6.setBounds(0, 185, 1250, 37);
				p6.setOpaque(false);
				pCarrera.add(p6);
			}else if (i==6) {
				p7.setImage(img);
				p7.setBounds(0, 222, 1250, 37);
				p7.setOpaque(false);
				pCarrera.add(p7);
			}else if (i==7) {
				p8.setImage(img);
				p8.setBounds(0, 259, 1250, 37);
				p8.setOpaque(false);
				pCarrera.add(p8);
			}else if (i==8) {
				p9.setImage(img);
				p9.setBounds(0, 296, 1250, 37);
				p9.setOpaque(false);
				pCarrera.add(p9);
			}else if (i==9) {
				p10.setImage(img);
				p10.setBounds(0, 333, 1250, 37);
				p10.setOpaque(false);
				pCarrera.add(p10);
			}else if (i==10) {
				p11.setImage(img);
				p11.setBounds(0, 370, 1250, 37);
				p11.setOpaque(false);
				pCarrera.add(p11);
			}else if (i==11) {
				p12.setImage(img);
				p12.setBounds(0, 407, 1250, 37);
				p12.setOpaque(false);
				pCarrera.add(p12);
			}else if (i==12) {
				p13.setImage(img);
				p13.setBounds(0, 444, 1250, 37);
				p13.setOpaque(false);
				pCarrera.add(p13);
			}else if (i==13) {
				p14.setImage(img);
				p14.setBounds(0, 481, 1250, 37);
				p14.setOpaque(false);
				pCarrera.add(p14);
			}else if (i==14) {
				p15.setImage(img);
				p15.setBounds(0, 518, 1250, 37);
				p15.setOpaque(false);
				pCarrera.add(p15);
			}else if (i==15) {
				p16.setImage(img);
				p16.setBounds(0, 555, 1250, 37);
				p16.setOpaque(false);
				pCarrera.add(p16);
			}else if (i==16) {
				p17.setImage(img);
				p17.setBounds(0, 592, 1250, 37);
				p17.setOpaque(false);
				pCarrera.add(p17);
			}else if (i==17) {
				p18.setImage(img);
				p18.setBounds(0, 629, 1250, 37);
				p18.setOpaque(false);
				pCarrera.add(p18);
			}else if (i==18) {
				p19.setImage(img);
				p19.setBounds(0, 666, 1250, 37);
				p19.setOpaque(false);
				pCarrera.add(p19);
			}else if (i==19) {
				p20.setImage(img);
				p20.setBounds(0, 703, 1250, 37);
				p20.setOpaque(false);
				pCarrera.add(p20);
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
					try {
						MenuPrincipalTrayectoria v = new MenuPrincipalTrayectoria();
						v.setVisible( true );
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else { // Modo Temporada
					if (numCarrera < 20) { // No ha sido la última carrera
						MenuPrincipalTemporada v = new MenuPrincipalTemporada(temp, numCarrera + 1);
						v.setVisible( true );
					} else { // Ha sido la última carrera de la temporada
						
					}
				}
			}					
		});
	

		// SIMULACIÓN DE LA CARRERA
		temp.simularCarreraTemporada( numCarrera );
		
		// Comprobación de los resultados de la carrera
		temp.getListaCarreras().get(numCarrera - 1).comprobarResultadoCarrera();
				

		//FUENTE
		Font font = new Font("Verdana", Font.BOLD, 45);
		
		//INFO CARRERA PARTE SUPERIOR
		JTextArea infoCarrera = new JTextArea(//BD.circuitoSelect(st, numCarrera).getImagen() + 
				BD.circuitoSelect(st, numCarrera).getNombre() + " -> " + BD.circuitoSelect(st, numCarrera).getVueltas() +
				" vueltas");
		infoCarrera.setEditable(false);
		infoCarrera.setOpaque(false);
//		infoCarrera.setBackground(null);
		infoCarrera.setFont(font);
		
		//POSICION
		pInfo.add(infoCarrera);
		bVolver.setBounds((this.getWidth()/20) * 9, (this.getHeight()/10) * 8, (this.getWidth()/20) * 2, this.getHeight()/10);
		bVolver.setVisible( true );
		pCentral.add(bVolver);		

		
	}
	public ArrayList<Temporada> getListaTemporadas() {
		return listaTemporadas;
	}

	public void setListaTemporadas(ArrayList<Temporada> listaTemporadas) {
		this.listaTemporadas = listaTemporadas;
	}
}
