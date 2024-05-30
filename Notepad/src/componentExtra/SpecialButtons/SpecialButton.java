package componentExtra.SpecialButtons;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;

public class SpecialButton extends JButton{
	private Component component;
	protected int height;
	protected int widht;
	protected float xRatio = 1;
	protected float yRatio = 1;
	JButton me = this;
	public SpecialButton(Component component) {
		this.component = component;
		Inıtiliaze();
	}
	public SpecialButton() {
		Inıtiliaze();
	}
	private void Inıtiliaze() {
		setOpaque(true);
		setText("");
		this.setMinimumSize(new Dimension(100,100));
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        RenderingHints rh = new RenderingHints(
        		RenderingHints.KEY_ANTIALIASING,
        		RenderingHints.VALUE_ANTIALIAS_ON
        		);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(rh);
    }
    protected void getRatio() {
		height = this.getSize().height;
		widht = this.getSize().width;
        xRatio = (float)(widht) / 100f;
        yRatio = (float)(height)/100f;
    }
    void listenSize() {
    	this.addComponentListener(new ComponentAdapter() {
    	    @Override
    	    public void componentResized(ComponentEvent e) {
                Dimension size = e.getComponent().getSize();
    	        widht = (int) size.getWidth();
    	        height = (int) size.getHeight();
    	        repaint();
    	    }
    	});
    }
}
