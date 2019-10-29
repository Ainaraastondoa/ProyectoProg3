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

public class VentanaCoche extends JFrame{
	
	JFrame MenuPrincipalTrayectoria;
	Coche coche;

	public VentanaCoche (JFrame m) {
		MenuPrincipalTrayectoria = m;
		JButton bok = new JButton("Ok");
		JPanel pInferior = new JPanel();
		JPanel pSuperior = new JPanel();
		Label etiqueta = new Label(Trayectoria.getEscuderia().toString());
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
		Font font1 = new Font("Verdana", Font.PLAIN, 22);

		//Imprimimos los datos del coche y sus mejoras
		Coche coche_seleccionado = Trayectoria.getEscuderia().getPiloto1().getCoche();		
		Coche datos = coche_seleccionado;
		Integer presupuesto = Trayectoria.getEscuderia().getPresupuesto();
		
		
		JTextArea da = new JTextArea(datos.toString2());
		da.setEditable(false);
		da.setBackground(Color.LIGHT_GRAY);
		pDatos.add(da);
		da.setFont(font);
		
		JTextArea pr = new JTextArea(presupuesto.toString());
		pr.setEditable(false);
		pr.setBackground(Color.LIGHT_GRAY);
		pPresupuesto.add(pr);
		pr.setFont(font);
		
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
		ArrayList<Mejora> motorR = coche_seleccionado.getMotor().listaMejoras;
//		ArrayList<Mejora> chasisR = coche_seleccionado.getChasis().listaMejoras;
//		ArrayList<Mejora> aerodinamicaR = coche_seleccionado.getAerodinamica().listaMejoras;

		
		JTextArea mm = new JTextArea(motor.toString());
		mm.setEditable(false);
		mm.setBackground(Color.LIGHT_GRAY);
		pChasis.add(mm);
		for (Mejora mejora : motor) {
			if (mejora.equals(motorR)) {
				mm.setFont(font1);
			} else {
				mm.setFont(font);
			}
		}
		
		//Si algunas de las mejoras de la lista se encuentra completada, se marca en negrita
//		JTextArea mm = new JTextArea (
//			if (int i=0; motor.get(i).equals(coche_seleccionado.getMotor().listaMejoras); i++) {
//				motor.get(i).toString();
//				mm.setFont(font1);
//			} else {
//				motor.get(i).toString();
//				mm.setFont(font);				
//			});
//		mm.setEditable(false);
//		mm.setBackground(Color.LIGHT_GRAY);
//		pMotor.add(mm);
		

		
		JTextArea mc = new JTextArea(chasis.toString());
		mc.setEditable(false);
		mc.setBackground(Color.LIGHT_GRAY);
		pChasis.add(mc);
		mc.setFont(font);
		
		JTextArea ma = new JTextArea(aerodinamica.toString());
		ma.setEditable(false);
		ma.setBackground(Color.LIGHT_GRAY);
		pAerodinamica.add(ma);
		ma.setFont(font);
		
		
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
