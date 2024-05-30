package componentExtra.SpecialButtons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class DirectionButton extends SpecialButton{
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	public final static int UP = 2;
	public final static int DOWN = 3;
	private int direction;
	public DirectionButton(Component component,int direction) {
		super(component);
		this.direction = direction;
		getRatio();
	}
	public DirectionButton() {
		super();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		xRatio = 1;
		yRatio = 1;
		Drawings.drawDirection(g2d, direction);
	}
	
}
class Drawings {
	public static void drawDirection(Graphics2D g2d,int direction) {
		g2d.setColor(Color.black);
		g2d.drawLine(2, 15, 20, 15);//horizontal line
		switch (direction) {
		case DirectionButton.RIGHT:
			g2d.drawLine(20, 15, 5, 5);//vertical line to up
			g2d.drawLine(20, 15, 5, 25);// vertical line to down
			break;
		case DirectionButton.LEFT:
			g2d.drawLine(2, 15, 12, 5);//vertical line to up
			g2d.drawLine(2, 15, 12, 25);// vertical line to down
			break;

		default:
			break;
		}

	}
}
