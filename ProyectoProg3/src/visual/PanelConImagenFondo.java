package visual;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelConImagenFondo extends JPanel{

	private Image fondo=null;
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
    	g.drawImage(fondo,0 ,0 , null);
	}
	public void setImage(String image){
        fondo = new ImageIcon(getClass().getResource(image)).getImage();
    
	}
}