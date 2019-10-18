package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**Esta clase sirve para crear la ventana principal
 *
 */

public class VentanaInicial extends JFrame{
	
	//metodo main de pruebas
	public static void main(String[] args) {
		VentanaInicial v = new VentanaInicial(); 
		v.setVisible( true );
	}
	
	public VentanaInicial() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize(600, 450);
		setTitle( "F1 Manager" );
		JButton bTrayectoria = new JButton( "Trayectoria" );
		JButton bJugador = new JButton( "Un jugador "); 
		JButton bMulti = new JButton( "Multijugador" );
		JButton b = new JButton( "" );
		bJugador.setBackground(Color.RED);
		bMulti.setBackground(Color.RED);
		getContentPane().setLayout( new GridLayout( 3, 1 ) );
		getContentPane().add( bTrayectoria );
		getContentPane().add( bJugador );
		getContentPane().add( bMulti );
		getContentPane().add( b );
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
						//MenuPrincipalTrayectoria menu = new MenuPrincipalTrayectoria( VentanaInicial.this );
							
					}
					
				});
	}

}
