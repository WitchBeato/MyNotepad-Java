package Frames;
import java.awt.EventQueue;
import componentExtra.FontList;
import doing.Work;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Info.Settings;

import javax.swing.ComboBoxEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class FontChooser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_FontSize;
	private JTextArea area;
	private Settings setting;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FontChooser frame = new FontChooser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private FontChooser() {
		setResizable(false);
		Inıtıalize();	
	}
	// area must be the area we try to change
	public FontChooser(JTextArea area,Settings setting) {
		Inıtıalize();	
		this.setVisible(true);
		this.area = area;
		this.setting = setting;
	}
	private void Inıtıalize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 822, 549);
		JLabel lbl_Test = new JLabel("Notepad");
		FontList pnl_fontChooser = new FontList();
		lbl_Test.setFont(pnl_fontChooser.getFont());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnl_fontChooser.lst_Fonts.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String fontFamily = pnl_fontChooser.lst_Fonts.getSelectedValue().toString();
				Font font = lbl_Test.getFont();
				font =Inwork.giveFont(lbl_Test, font.getSize(), font.getStyle(), fontFamily);
				lbl_Test.setFont(font); 
			}
		});
		setContentPane(contentPane);
		JComboBox cmb_FontType = new JComboBox();
		cmb_FontType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Font font = lbl_Test.getFont();
				font =Inwork.giveFont(lbl_Test, font.getSize(), cmb_FontType.getSelectedIndex(), font.getFamily());
			}
		});
		cmb_FontType.setBounds(501, 366, 241, 62);
		cmb_FontType.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		Inwork.setTypes(cmb_FontType);
		
		JLabel lbl_FontFamily = new JLabel("Font Family");
		lbl_FontFamily.setBounds(47, 10, 367, 79);
		lbl_FontFamily.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_FontFamily.setFont(new Font("Segoe UI", Font.BOLD, 17));
		
		txt_FontSize = new JTextField();
		txt_FontSize.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				Inwork.setinSizeBox(txt_FontSize, lbl_Test, e, setting);
			}
		});
		txt_FontSize.setBounds(501, 239, 241, 56);
		txt_FontSize.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		txt_FontSize.setColumns(10);
		
		JLabel lbl_FontSize = new JLabel("Font Size");
		lbl_FontSize.setBounds(501, 173, 241, 56);
		lbl_FontSize.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_FontSize.setFont(new Font("Segoe UI", Font.BOLD, 17));
		JLabel lbl_FontType = new JLabel("Font Type");
		lbl_FontType.setBounds(501, 305, 241, 56);
		lbl_FontType.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_FontType.setFont(new Font("Segoe UI", Font.BOLD, 17));
		
		
		JButton btn_OK = new JButton("OK");
		btn_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setFont(lbl_Test.getFont());
			}
		});
		btn_OK.setBounds(534, 438, 197, 59);
		btn_OK.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		JLabel lbl_Warning = new JLabel("Warning");
		lbl_Warning.setBounds(429, 10, 88, 19);
		lbl_Warning.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Warning.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_Warning.setVisible(false);
		contentPane.setLayout(null);
		contentPane.add(lbl_FontFamily);
		contentPane.add(lbl_FontType);
		contentPane.add(txt_FontSize);
		contentPane.add(lbl_FontSize);
		contentPane.add(cmb_FontType);
		contentPane.add(btn_OK);
		contentPane.add(lbl_Warning);
		
		JScrollPane sp_fontList = new JScrollPane(pnl_fontChooser);
		sp_fontList.setBounds(10, 85, 440, 417);
		contentPane.add(sp_fontList);
		sp_fontList.getVerticalScrollBar().setUnitIncrement(20);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		panel.setBounds(460, 85, 338, 79);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(lbl_Test, BorderLayout.CENTER);
		
	}
	public static void getFont(JTextArea area) {
		FontChooser chooser = new FontChooser();
		chooser.setVisible(true);
				
	}
}
class Inwork{
	static Font giveFont(JLabel lbl_Font,int size, int fontType, String fontFamily) {
	    Font font = new Font(fontFamily,fontType,size);
	    lbl_Font.setFont(font);
	    return font;
	}
	static void setTypes(JComboBox<String> box) {
		box.addItem("Plain");
		box.addItem("Bold");
		box.addItem("Italic");
		
	}
	static void setinSizeBox(JTextField txt_FontSize,JLabel lbl_Test, KeyEvent e,Settings setting) {
		int tempSize = txt_FontSize.getText().length();
		char c = e.getKeyChar();
		if(!Character.isDigit(c) || tempSize>2) {
			String text = txt_FontSize.getText();
			txt_FontSize.setText(text.substring(0, text.length() - 1));
			
		}
		if(tempSize != 0) {
			int size = Integer.parseInt(txt_FontSize.getText());
			Font font = lbl_Test.getFont();
			font =Inwork.giveFont(lbl_Test, size, font.getStyle(), font.getFamily());
			lbl_Test.setFont(font); 
			setting.setFont(font);
		}
	}
}