package componentExtra.SpecialButtons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import componentExtra.SpecialButtons.SpecialButton;

public class CloseButton extends SpecialButton{
	
	public CloseButton(Component component) {
		super(component);
		listenSize();
	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    setBackground(Color.red);
	    Graphics2D g2d = (Graphics2D) g;
	    
	    
	    int startX = widht / 4;
	    int startY = height / 4;
	    int endX = widht - startX;
	    int endY = height - startY;
	    
	    g2d.drawLine(startX, startY, endX, endY); // first slash \
	    g2d.drawLine(startX, endY, endX, startY); // second slash /
	}
}
