package componentExtra;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;
import java.awt.BorderLayout;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class FontList extends JPanel {

	private static final long serialVersionUID = 1L;
	String fontName;
	public JList lst_Fonts;
	/**
	 * Create the panel.
	 */
	public FontList() {
		setLayout(new BorderLayout(0, 0));
		String[] fonts = getFontList();
		lst_Fonts = new JList(fonts);
		lst_Fonts.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		lst_Fonts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lst_Fonts.setFont(new Font("MV Boli", Font.BOLD, 16));
		add(lst_Fonts, BorderLayout.CENTER);

	}
	private static String[] getFontList() {
		DefaultListModel<String> model = new DefaultListModel<>();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = ge.getAvailableFontFamilyNames();
		return fonts;
	}
	public String getName() {
		return fontName;
	}
	
}
