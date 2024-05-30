package componentExtra;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

public class SpeacialLabel extends JLabel{
	public static Color green = new Color(148,212,122);
	Component component;
	public SpeacialLabel(Color color,Component component) {
		initiliaze(color,component);
	}
	public SpeacialLabel(int size, Color color,Component component) {
		Font font = this.getFont();
		this.setFont(new Font(font.getFamily(), font.getStyle(), size));
		initiliaze(color,component);
	}
	private void initiliaze(Color color,Component component) {
		this.setText("");
		this.setForeground(color);
		this.component = component;
		this.setOpaque(true);
		this.setVisible(false);
	}
	public void setMessage(String text) {
		this.setBackground(component.getBackground());
		this.setText(text);
		this.setVisible(true);
		this.repaint();
		  ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		  Runnable runnable = () -> {
		    this.setVisible(false);
		    executor.shutdown();
		  };

		  executor.schedule(runnable, 3, TimeUnit.SECONDS);
	}
	
}
