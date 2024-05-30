package componentExtra.SpecialButtons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import componentExtra.SpecialButtons.SpecialButton;

public class AddButton extends SpecialButton{
	
	public AddButton(Component component) {
		super(component);
		listenSize();
	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    setBackground(Color.red);
	    Graphics2D g2d = (Graphics2D) g;
	    
	    
	    int startX = widht / 2;
	    int startY = height / 8;
	    int endX = widht - startX;
	    int endY = height - startY;
	    
	    g2d.drawLine(startX, startY, endX, endY); // first slash |
	    g2d.drawLine(startY, startX, endY, endX); // second slash -
	}
}
