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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import elementos.*;

 
/**Ventana en la que se muestran los pilotos de la escuderia con sus 
 * respectivos atributos
 */

public class VentanaPiloto extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	Piloto piloto; 
	private JButton bVolver;
	private String fondo = "/img/fondoayuda.png";
	
	
	public VentanaPiloto(JFrame m) throws SQLException {
		MenuPrincipalTrayectoria = m;
		setTitle(" PILOTOS ");
		setSize(m.getWidth(), m.getHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		//FONDO DE LA VENTANA
		PanelConImagenFondo imagen_fondo = new PanelConImagenFondo();
		imagen_fondo.setImage(fondo);
		setContentPane(imagen_fondo);
		setLayout(new FlowLayout());
				
		//CREACION DE LOS PANELES Y SUBPANELES
		JPanel pCentral = new JPanel();
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		PanelConImagenFondo imagen_fondoI = new PanelConImagenFondo();
		PanelConImagenFondo imagen_fondoD = new PanelConImagenFondo();
		
		String imagen, imagen2;
		
		try {
			imagen = Trayectoria.getEscuderia().getPiloto1().getImagen();
			imagen2 = Trayectoria.getEscuderia().getPiloto2().getImagen();
		} catch (NullPointerException e) {
			imagen = Temporada.getEscuderia().getPiloto1().getImagen();
			imagen2 = Temporada.getEscuderia().getPiloto2().getImagen();
		}
		
		imagen_fondoI.setImage(imagen);
		imagen_fondoD.setImage(imagen2);
		
		imagen_fondoI.setOpaque(false);
		imagen_fondoD.setOpaque(false);
		
		imagen_fondoI.setBounds(60,420,1250,370);
		imagen_fondoD.setBounds(1000,420,1250,370);
		
		pCentral.add(imagen_fondoI);
		pCentral.add(imagen_fondoD);
		
		//FUENTE
		Font font = new Font("Verdana", Font.BOLD, 25);
		
		//OBTENEMOS LOS DATOS DE LOS PILOTOS DE LA ESCUDERIA SELECCIONADA
		Escuderia escuderia_seleccionada = Trayectoria.getEscuderia();
		if (escuderia_seleccionada==null) {
			escuderia_seleccionada = Temporada.getEscuderia();
		}
		Piloto p1 = escuderia_seleccionada.getPiloto1();
		Piloto p2 = escuderia_seleccionada.getPiloto2();
		
//		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
//		Statement st = con.createStatement();
		//ArrayList<Escuderia> escuderias = BD.listaEscuderiasSelect(st);
		//Imprimimos los datos de los pilotos con sus atributos _PROVISIONAL 
		
//		for (Escuderia escuderia : escuderias) {
//			if (escuderia_seleccionada.equals(escuderia)) {	
//			}
//		}
		
		//IMPRIMIMOS LOS DATOS DEL PRIMER PILOTO
		JTextArea piloto1 = new JTextArea(p1.toString2());
		piloto1.setEditable( false );
		piloto1.setBackground(Color.LIGHT_GRAY);
		piloto1.setFont(font);
	
		//IMPRIMIMOS LOS DATOS DEL SEGUNDO PILOTO
		JTextArea piloto2 = new JTextArea(p2.toString2());
		piloto2.setEditable( false );
		piloto2.setBackground(Color.LIGHT_GRAY);
		piloto2.setFont(font);
		
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

		piloto1.setBounds(this.getWidth()/20, this.getHeight()/15, 425, 350);
		pCentral.add(piloto1);
		piloto2.setBounds((this.getWidth()/20)*13, this.getHeight()/15, 425, 350);
		pCentral.add(piloto2);
		getContentPane().add(pCentral);
		bVolver.setBounds((this.getWidth()/20) * 9, (this.getHeight()/10) * 8, (this.getWidth()/20) * 2, this.getHeight()/10);
		pCentral.add(bVolver);
		
	}
}
