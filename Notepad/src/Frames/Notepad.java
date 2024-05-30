package Frames;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Info.Directories;
import Info.Pages;
import Info.Settings;
import componentExtra.SpeacialLabel;
import componentExtra.TabPage.MyTabs;
import componentExtra.TabPage.Tabs;
import doing.Work;

import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Notepad extends JFrame {

	private static final long serialVersionUID = 1L;
	private Settings settings;
	private Pages pages;
	private MyTabs tab_Selected;
	float CLRRATION = (float) (8f/10f);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notepad frame = new Notepad();
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
	public Notepad() {
		setTitle("MyNotepad");
		initiliazePages();
		JTextArea txt_Text = new JTextArea();
		txt_Text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Work.removeHighlight(txt_Text);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 532);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mn_File = new JMenu("File");
		mn_File.setForeground(new Color(0, 0, 0));
		mn_File.setFont(new Font("Consolas", Font.BOLD, 17));
		menuBar.add(mn_File);
		Tabs pnl_Tab = new Tabs(txt_Text,txt_Text,this,pages);
		pnl_Tab.setPreferredSize(new Dimension(0,30));
		SpeacialLabel slbl_Info = new SpeacialLabel(20,SpeacialLabel.green,txt_Text);
		slbl_Info.setHorizontalAlignment(SwingConstants.RIGHT);
		initiliazeSettings(txt_Text,menuBar,slbl_Info,pnl_Tab);
		JMenuItem itm_Save = new JMenuItem("Save");
		itm_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tab_Selected == null) {
					fileSaveAs(txt_Text, slbl_Info);
					return;
				}
				if(!tab_Selected.getPage().getisSame()) {
					fileSave(tab_Selected.getPage().getPath(), txt_Text,slbl_Info);
				}
				else {
					fileSaveAs(txt_Text, slbl_Info);
				}
				
			}
		});
		itm_Save.setForeground(new Color(220, 20, 60));
		itm_Save.setFont(new Font("Consolas", Font.BOLD, 17));
		mn_File.add(itm_Save);
		
		JMenuItem itm_Load = new JMenuItem("Load");
		itm_Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileLoad(txt_Text,slbl_Info);
			}
		});
		
		JMenuItem itm_SaveAs = new JMenuItem("Save as...");
		itm_SaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileSaveAs(txt_Text, slbl_Info);
			}
		});
		itm_SaveAs.setForeground(new Color(178, 34, 34));
		itm_SaveAs.setFont(new Font("Consolas", Font.BOLD, 17));
		mn_File.add(itm_SaveAs);
		itm_Load.setForeground(new Color(0, 255, 0));
		itm_Load.setFont(new Font("Consolas", Font.BOLD, 17));
		mn_File.add(itm_Load);
		
		JMenuItem itm_Find = new JMenuItem("Find");
		itm_Find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = Work.getTextfromDialog();
				Work.textHighlight(txt_Text, text);
			}
		});
		itm_Find.setForeground(new Color(169, 169, 169));
		itm_Find.setFont(new Font("Consolas", Font.BOLD, 17));
		mn_File.add(itm_Find);
		
		JMenuItem itm_Close = new JMenuItem("Close");
		itm_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		itm_Close.setForeground(new Color(255, 0, 0));
		itm_Close.setFont(new Font("Consolas", Font.BOLD, 17));
		mn_File.add(itm_Close);
		
		JMenu mn_Settings = new JMenu("Settings");
		mn_Settings.setForeground(Color.BLACK);
		mn_Settings.setFont(new Font("Consolas", Font.BOLD, 17));
		menuBar.add(mn_Settings);
		
		JMenuItem itm_Font = new JMenuItem("Font");
		itm_Font.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FontChooser choser = new FontChooser(txt_Text,settings);
				choser.setResizable(false);
			}
		});
		itm_Font.setForeground(new Color(75, 0, 130));
		itm_Font.setFont(new Font("Consolas", Font.BOLD, 17));
		mn_Settings.add(itm_Font);
		
		JMenuItem itm_FontColor = new JMenuItem("Font Color");
		itm_FontColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Chose the color", txt_Text.getForeground());
				txt_Text.setForeground(color);
			}
		});
		itm_FontColor.setForeground(new Color(184, 134, 11));
		itm_FontColor.setFont(new Font("Consolas", Font.BOLD, 17));
		mn_Settings.add(itm_FontColor);
		

		
		JPanel pnl_Content = new JPanel();
		getContentPane().add(pnl_Content, BorderLayout.CENTER);
		pnl_Content.setLayout(new BorderLayout(0, 0));
		
		pnl_Content.add(txt_Text, BorderLayout.CENTER);
		
		pnl_Content.add(slbl_Info, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				beforeCloseSettings(txt_Text);
				beforeClosePages(Directories.localPages, pages);
			}
		});
		JMenuItem itm_Background = new JMenuItem("Background Color");
		itm_Background.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Chose the color", txt_Text.getForeground());
				setMenuColor(txt_Text,pnl_Tab, menuBar,slbl_Info, color);
			}
		});
		itm_Background.setForeground(new Color(210, 105, 30));
		itm_Background.setFont(new Font("Consolas", Font.BOLD, 17));
		mn_Settings.add(itm_Background);
		pnl_Content.add(pnl_Tab, BorderLayout.NORTH);
		setShortcut(mn_File);
	}
	private void initiliazeSettings(JTextArea txt_Text, JMenuBar menuBar,SpeacialLabel label,JPanel pnl_Tabs) {
		String directory = Directories.localSettings;
		File file = new File(directory);
		if(file.exists()) {
			try {
				settings = Settings.getFile(directory);
				txt_Text.setForeground(settings.getForeground());
				txt_Text.setFont(settings.getFont());
				setMenuColor(txt_Text,pnl_Tabs, menuBar, label, settings.getBackground());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			settings = new Settings(directory);
		}
	}
	private void initiliazePages()  {
		final String directory = Directories.localPages;
		File file = new File(directory);
		if(file.exists()) {
			try {
				pages = Pages.getFile(directory);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pages == null) {
			pages = new Pages();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void setMenuColor(JTextArea txt_Text,JPanel pnl_Tabs ,JMenuBar menuBar,SpeacialLabel label,Color color) {
		txt_Text.setBackground(color);
		Color color_Sub = Work.getColorSub(color, CLRRATION);
		pnl_Tabs.setBackground(Work.getColorSub(color_Sub, CLRRATION));
		menuBar.setBackground(color_Sub);
		label.setForeground(color_Sub);
	}
	//load the file 
	private void fileLoad(JTextArea area,SpeacialLabel label) {
		String path = Work.getPath(new FileNameExtensionFilter("Text File", "txt"));
		String text = Work.getTextfromPath(path);
		area.setText(text);
		tab_Selected.setPath(path);
		label.setMessage("file loaded succesfully");
	}
	//save the file
	private void fileSave(String path,JTextArea txt_area,SpeacialLabel label) {
		if(path.equals(null)) return;
		fileSaveSub(path, txt_area);
		label.setMessage("file saved succesfully");
		
	}
	private void fileSaveAs(JTextArea txt_area,SpeacialLabel label) {
		String path = Work.getPath(new FileNameExtensionFilter("Text Files(.txt)", "txt"));
		if(path != null)fileSave(path, txt_area, label);
		
	}
	//looking for direction has .txt before save the file
	private void fileSaveSub(String path, JTextArea txt_area) {
		char c[] = path.toCharArray();
		String extension = "";
		for (int i = c.length-1; i>=0 && c[i] != '.';i--) {
			extension += c[i];
		}
		extension = Work.getReverse(extension);
		if(extension.equals(path)) {
			path += ".txt";
			Work.saveText(txt_area.getText(), path);
			tab_Selected.setPath(path);
		}
		else if(extension.equals("txt")) {
			Work.saveText(txt_area.getText(), path);
			tab_Selected.setPath(path);
		}
		else {
			return;
		}
	}
	private void beforeCloseSettings(JTextArea txt_Text) {
		settings.setBackground(txt_Text.getBackground());
		settings.setFont(txt_Text.getFont());
		settings.setForeground(txt_Text.getForeground());
	}
	private void beforeClosePages(String directory,Pages pages) {
		try {
			Pages.saveFile(directory, pages);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setShortcut(JMenu mn_Items) {
		InputMap inputMap = mn_Items.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap actionMap = mn_Items.getActionMap();

	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "save");
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK), "load");
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK), "find");
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "copy");

	    actionMap.put("save", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            mn_Items.getItem(0).doClick();
	        }
	    });

	    actionMap.put("load", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            mn_Items.getItem(2).doClick();
	        }
	    });

	    actionMap.put("find", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            mn_Items.getItem(3).doClick();
	        }
	    });

	    actionMap.put("copy", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            mn_Items.getItem(4).doClick();
	        }
	    });
	}
	public void setTab(MyTabs tab) {
		tab_Selected = tab;
	}

}
