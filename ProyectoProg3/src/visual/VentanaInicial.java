package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import elementos.*;

/**Esta clase sirve para crear la ventana principal
 *
 */ 

public class VentanaInicial extends JFrame{
	
	//metodo main de pruebas
	public static void main(String[] args) throws SQLException {
		VentanaInicial v = new VentanaInicial(); 
		v.setVisible( true );
	}
	
	public VentanaInicial() throws SQLException {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(1300, 900);
		setTitle( "F1 Manager" );
		
		Connection con = BD.initBD("src/datos/F1BaseDatos.db");
		Statement st = con.createStatement();
		BD.usarCrearTablasBD(con);
		//BD.insertDatos(st);
		
		JButton bTrayectoria = new JButton( "Trayectoria" );
		JButton bJugador = new JButton( "Un jugador "); 
		JButton bMulti = new JButton( "Multijugador" );
		JButton b = new JButton( "" );
		bJugador.setBackground(Color.RED);
		bMulti.setBackground(Color.RED);
		JPanel pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout( 3, 1 ));
		pCentral.add(bTrayectoria);
		pCentral.add(bJugador);
		pCentral.add(bMulti);
		pCentral.add(b);
		JButton bAyuda = new JButton("Ayuda"); 
		JPanel pInferior = new JPanel();
		pInferior.setBackground(Color.LIGHT_GRAY);
		pInferior.add( bAyuda );
		getContentPane().add( pInferior, BorderLayout.SOUTH);
		bAyuda.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						VentanaAyuda v = new VentanaAyuda( VentanaInicial.this );
						v.setLocation( getLocation() );
						v.setSize( getSize() );
						v.setVisible( true );
						VentanaInicial.this.setVisible( false ); 
						
					}
					
				}); 
		
		bTrayectoria.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						VentanaSelEscuderia escuderia;
						try {
							escuderia = new VentanaSelEscuderia( VentanaInicial.this );
							escuderia.setLocation( getLocation() );
							escuderia.setSize( getSize() );
							escuderia.setVisible( true );
							VentanaInicial.this.setVisible( false );
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}					
				});
	}
}
