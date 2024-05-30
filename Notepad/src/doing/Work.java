package doing;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import Info.Settings;

public class Work {
	public static Color colorChooser(String name,Color clr_initial) {
		Color color = JColorChooser.showDialog(null, name+"'s color", clr_initial);
		return color;
	}
	public static Color setFontColor(Settings setting,String name,Color clr_initial) {
		Color color = colorChooser(name, clr_initial);
		setting.setForeground(color);
		return color;
	}
	public static Color setBackgroundColor(Settings setting,String name,Color clr_initial) {
		Color color = colorChooser(name, clr_initial);
		setting.setBackground(color);
		return color;
	}
	public static JList<String> getFontList() {
		DefaultListModel<String> model = new DefaultListModel<>();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		JList<String> lst_fontList = new JList<>(fonts);
		lst_fontList.setValueIsAdjusting(true);
		lst_fontList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lst_fontList.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		return lst_fontList;
	}
	public static void saveText(String text,String directory) {
		if(directory == null) return;
        try {
            FileWriter writer = new FileWriter(directory);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static String getPath(FileNameExtensionFilter filter) {
		JFileChooser choser = new JFileChooser();
		choser.setFileFilter(filter);
		int secim = choser.showOpenDialog(choser);
		if(secim == JFileChooser.APPROVE_OPTION) return choser.getSelectedFile().getAbsolutePath();
		else return null;
	}
	public static String getReverse(String Text) {
		StringBuilder sb = new StringBuilder(Text);
		return sb.reverse().toString();
	}
	public static Color getColorSub(Color color,float CLRRATION) {
		Color color_Sub = new Color(Math.round((float)(color.getRed())*CLRRATION),Math.round((float)(color.getGreen())*CLRRATION)
				,Math.round((float)(color.getBlue())*CLRRATION));
		return color_Sub;
	}
	int getProduct(int a,float b) {
		return (int)(a*b);
	}
	public static String getTextfromPath(String path) {
		String text = null;
		try {
			 text = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	public static Color getLightenColor(Color color,float ratio) {
		if(ratio >1f || ratio < -1f ) {
			ratio = 1f;
		}
		int red = color.getRed();
		int blue = color.getBlue();
		int green = color.getGreen();
		int temp = red;
		Color colorNew = color;
		if(temp <blue) temp = blue;
		if(temp < green) temp = green;
		if(temp == red) {
			int value = getlightensub(red,ratio);
			colorNew = new Color(value,green,blue);
		}
		else if(temp == green) {
			int value = getlightensub(green,ratio);
			colorNew = new Color(red,value,blue);
		}
		else if(temp == blue) {
			int value = getlightensub(blue,ratio);
			colorNew = new Color(red,green,value);
		}
		return colorNew;
	}
	public static String getTextfromDialog() {
		String input = JOptionPane.showInputDialog(null,"please enter the value");
		return input;
	}
	private static int getLocationonString(int startPoint,String text,String words) {
		int newPoint = text.indexOf(words, startPoint);
		return newPoint;
	}
	public static void textHighlight(JTextArea area, String words) {
		Highlighter lighter = area.getHighlighter();
		HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
		int size = words.length();
		int index = 0;
		while (true) {
			//lighter.removeAllHighlights();
			index = getLocationonString(index, area.getText(), words);
			if(index == -1) break;
			try {
				lighter.addHighlight(index, index+size, painter);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			index += size;
		}
		
	}
	public static void removeHighlight(JTextArea area) {
		Highlighter lighter = area.getHighlighter();
		lighter.removeAllHighlights();
	}
	//get the  lighten value we need
    private static int getlightensub(int value,float ratio){
   	 value = (int) (value + (255-value)*ratio);
   	 return value;
    }
}
