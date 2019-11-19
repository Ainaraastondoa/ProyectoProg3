package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.Coche;
import elementos.Escuderia;
import elementos.Mejora;
import elementos.Trayectoria;
import elementos.initDatos;

public class VentanaCoche extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	Coche coche;

	public VentanaCoche (JFrame m) {
		MenuPrincipalTrayectoria = m;
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		Label etiqueta = new Label(Trayectoria.getPiloto().getCoche().toString());
		pSuperior.add(etiqueta); 
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pSuperior, BorderLayout.NORTH); 
		pInferior.add(bok);
		
		JPanel pCentral = new JPanel();
		JPanel pMotor = new JPanel();
		JPanel pChasis = new JPanel();
		JPanel pAerodinamica = new JPanel();
		JPanel pDatos = new JPanel();
		JPanel pPresupuesto = new JPanel();
		pCentral.add(pMotor);
		pCentral.add(pChasis);
		pCentral.add(pAerodinamica);
		pCentral.add(pDatos);
		pCentral.add(pPresupuesto);
		getContentPane().add(pCentral);
		Font font = new Font("Verdana", Font.BOLD, 39);
		Font font1 = new Font("Verdana", Font.PLAIN, 39);

		//Imprimimos los datos del coche y sus mejoras
		String nombre_escuderia_seleccionada = Trayectoria.getPiloto().getCoche().nombre;
		for ( Escuderia escuderia : Escuderia.crearEscuderiasPredeterminadas(initDatos.initPilotos())) {
			if (nombre_escuderia_seleccionada.equals(escuderia.nombre)) {
				Escuderia escuderia_seleccionada = escuderia;
				Integer presupuesto = escuderia_seleccionada.getPresupuesto();
				
				JTextArea pr = new JTextArea(presupuesto.toString());
				pr.setEditable(false);
				pr.setBackground(Color.LIGHT_GRAY);
				pPresupuesto.add(pr);
				pr.setFont(font);
			}
		}
		Coche coche_seleccionado = Trayectoria.getPiloto().getCoche();		
	
		JTextArea da = new JTextArea(coche_seleccionado.toString2());
		da.setEditable(false);
		da.setBackground(Color.LIGHT_GRAY);
		pDatos.add(da);
		da.setFont(font);
				
		//LISTA MEJORAS
		ArrayList<Mejora> listaMejoras = Mejora.crearMejoras();
		ArrayList<Mejora> motor = new ArrayList<Mejora>();
		ArrayList<Mejora> chasis = new ArrayList<Mejora>();
		ArrayList<Mejora> aerodinamica = new ArrayList<Mejora>();

		int i = 0;
		while (i<=9) {
			motor.add(listaMejoras.get(i));
			i++;
		}
		while (i>=10 && i<=19) {
			chasis.add(listaMejoras.get(i));
			i++;
		} 
		while (i>=20 && i<=29) {
			aerodinamica.add(listaMejoras.get(i));
			i++;
		}
		
		//MEJORAS YA REALIZADAS
		ArrayList<ArrayList<Mejora>> listaMejorasR = Mejora.crearMejorasPredeterminadas();
		ArrayList<Mejora> motorR = new ArrayList<Mejora>();
		motorR.addAll(listaMejorasR.get(0));
		ArrayList<Mejora> chasisR = new ArrayList<Mejora>();
		chasisR.addAll(listaMejorasR.get(1));
		ArrayList<Mejora> aerodinamicaR = new ArrayList<Mejora>();
		aerodinamicaR.addAll(listaMejorasR.get(2));
		
		//Si algunas de las mejoras de la lista se encuentra completada, se cambia la fuente 
		//(Habr� que usar JTextPane)
		JTextArea mm = new JTextArea(motor.toString());
		mm.setEditable(false);
		mm.setBackground(Color.LIGHT_GRAY);
		pMotor.add(mm);
		mm.setFont(font);
		for (Mejora mejora : motorR) {
			if (!motor.contains(mejora)) {
				mm.setFont(font1);
			}
		}	
		
		JTextArea mc = new JTextArea(chasis.toString());
		mc.setEditable(false);
		mc.setBackground(Color.LIGHT_GRAY);
		pChasis.add(mc);
		mc.setFont(font);
		for (Mejora mejora : chasisR) {
			if (!chasis.contains(mejora)) {
				mc.setFont(font1);
			}
		}
			
		JTextArea ma = new JTextArea(aerodinamica.toString());
		ma.setEditable(false);
		ma.setBackground(Color.LIGHT_GRAY);
		pAerodinamica.add(ma);
		ma.setFont(font);
		for (Mejora mejora : aerodinamicaR) {
			if (!aerodinamica.contains(mejora)) {
				ma.setFont(font1);
			}
		}		
		
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
