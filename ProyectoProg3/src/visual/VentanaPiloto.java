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
	private PanelConImagenFondo imagen_fondoI;
	private PanelConImagenFondo imagen_fondoD;
	
	public VentanaPiloto(JFrame m) throws SQLException {
		MenuPrincipalTrayectoria = m;
		setTitle(" PILOTOS ");
		setSize(m.getWidth(), m.getHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//FONDO DE LA VENTANA
		//PARA EL PRIMER PILOTO
		imagen_fondoI = new PanelConImagenFondo();
		imagen_fondoI.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		String imagen = "";
		imagen += Trayectoria.getEscuderia().getPiloto1().getImagen();
		imagen_fondoI.setImage(imagen);
		
		imagen_fondoI.setLayout(new FlowLayout(FlowLayout.LEFT));
		setContentPane(imagen_fondoI);
		
		
		
		//PARA EL SEGUNDO PILOTO
		imagen_fondoD = new PanelConImagenFondo();
		imagen_fondoD.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		String imagen2 = "";
		imagen2 += Trayectoria.getEscuderia().getPiloto2().getImagen();
		imagen_fondoD.setImage(imagen2);
		
		imagen_fondoD.setLayout(new FlowLayout(FlowLayout.RIGHT));
		setContentPane(imagen_fondoD);
		
		
		//CREACION DEL PANEL QUE ALBERGA LOS DATOS Y BOTONES
//		JPanel pInferior = new JPanel();
//		getContentPane().add(pInferior, BorderLayout.SOUTH);
		//CREAMOS DOS PANELES PARA CADA PILOTO DE LA ESCUDERIA
		//AÑADIMO
//		JPanel pDerecha = new JPanel();
//		JPanel pIzquierda = new JPanel();
		
		JPanel pCentral = new JPanel();
//		pDerecha.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pCentral.setLayout(new GridLayout(1,2));
		pCentral.setPreferredSize( new Dimension( this.getWidth(), this.getHeight() ));
		pCentral.setOpaque(false);
		pCentral.setLayout(null);
		getContentPane().add(pCentral);
		
		//FUENTE
		Font font = new Font("Verdana", Font.BOLD, 39);
		
		//OBTENEMOS LOS DATOS DE LOS PILOTOS DE LA ESCUDERIA SELECCIONADA
		Escuderia escuderia_seleccionada = Trayectoria.getEscuderia();
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
//		pIzquierda.add(piloto1);
		pCentral.add(piloto1);
		piloto1.setFont(font);
	
		//IMPRIMIMOS LOS DATOS DEL SEGUNDO PILOTO
		JTextArea piloto2 = new JTextArea(p2.toString2());
		piloto2.setEditable( false );
		piloto2.setBackground(Color.LIGHT_GRAY);
//		pDerecha.add(piloto2);
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
//		pCentral.add(pIzquierda);
//		pCentral.add(pDerecha);
//		piloto1.setBounds(this.getWidth()/20, this.getHeight()/15, 425, 650);
		pCentral.add(piloto1);
		//piloto2.setBounds(x, y, width, height);
		pCentral.add(piloto2);
		getContentPane().add(pCentral);
		bVolver.setBounds((this.getWidth()/20) * 9, (this.getHeight()/10) * 8, (this.getWidth()/20) * 2, this.getHeight()/10);
		pCentral.add(bVolver);
		
	}
}
