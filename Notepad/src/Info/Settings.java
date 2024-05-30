package Info;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable{
	private String directory;
	private Color foreground;
	private Color background;
	private Font textFont;
	public Settings(String directory) {
		this.directory = directory;
	}
	public void setForeground(Color color) {
		this.foreground = color;
		try {
			saveFile(directory, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setBackground(Color color) {
		this.background = color;
		try {
			saveFile(directory, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setFont(Font font) {
		this.textFont = font;
		try {
			saveFile(directory, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Color getForeground() {
		return foreground;
	}
	public Color getBackground() {
		return background;
	}
	public Font getFont() {
		return textFont;
	}
	public static void saveFile(String Directory,Settings setting) throws IOException {
		File file = new File(Directory);
		FileOutputStream fileOut = new FileOutputStream(file);
		ObjectOutputStream stream = new ObjectOutputStream(fileOut);
		stream.writeObject(setting);
		stream.close();
		fileOut.close();
	}
	public static Settings getFile(String Directory) throws IOException, ClassNotFoundException {
		Settings setting;
		File file = new File(Directory);
		FileInputStream fileOut = new FileInputStream(file);
		ObjectInputStream stream = new ObjectInputStream(fileOut);
		setting = (Settings) stream.readObject();
		stream.close();
		fileOut.close();
		return setting;
	}
}
