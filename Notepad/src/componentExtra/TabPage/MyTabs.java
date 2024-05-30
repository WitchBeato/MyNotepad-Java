package componentExtra.TabPage;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import componentExtra.SpecialButtons.CloseButton;
import doing.Page;
import doing.Work;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Frames.Notepad;
import Info.Pages;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyTabs extends JPanel {
	private JPanel me = this;
	private Tabs tabs;
	private Color color;
	private JPanel pnl_Left;
	private CloseButton btn_Close;
	private JLabel lbl_Name;
	private String text = "null";
	//for change the text area and mytabs from frame i have to put them here
	private JTextArea txt_area;
	private Notepad notepad;
	private Page page;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MyTabs(Tabs component,Page page,JTextArea area,Notepad notepad) {
		txt_area = area;
		text = page.getName();
		this.notepad = notepad;
		this.page = page;
		tabs = component;
		Initiliaze();
		componentChange(component, pnl_Left);
	}
	public MyTabs(JComponent component,String name) {
		text = name;
		Initiliaze();
		componentChange(component, pnl_Left);
	}
	public MyTabs(JComponent component) {
		me.setBackground(component.getBackground());
		
		Initiliaze();
		componentChange(component, pnl_Left);
	}
	public MyTabs() {
		Initiliaze();
	}
	void Initiliaze() {
		setLayout(new BorderLayout(0, 0));
		
		btn_Close = new CloseButton(this);
		btn_Close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});
		//btn_Close.setPreferredSize(new Dimension(5,100));
		add(btn_Close, BorderLayout.EAST);
		btn_Close.setPreferredSize(new Dimension(30,0));
		pnl_Left = new JPanel();
		pnl_Left.setPreferredSize(new Dimension(200,0));
		add(pnl_Left, BorderLayout.WEST);
		pnl_Left.setLayout(new BorderLayout(0, 0));
		
		lbl_Name = new JLabel("New label");
		lbl_Name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickOn();
			}
		});
		lbl_Name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnl_Left.add(lbl_Name, BorderLayout.CENTER);
		lbl_Name.setText(text);
		color = pnl_Left.getBackground();
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
		tabRefresh();
	}
	public void setPath(String path) {
		page.setPath(path);
		tabRefresh();
	}
	void propertyListen() {
		this.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();
				if(name.equals("widht")) {
					int widht = me.getSize().width;
					btn_Close.setPreferredSize(new Dimension(widht*2/3,0));
				}
				else if(name.equals("height")) {
					int height = me.getSize().height;
					Font font =lbl_Name.getFont();
					lbl_Name.setFont(new Font(font.getName(),font.getStyle(),height/13));
				}
				
			}
		});
	}
	void Close() {
	    if (me!= null) {
	            Container tabsParent = this.getParent();
	            tabsParent.remove(me);
	            tabsParent.repaint();
	            tabsParent.validate();
	            tabs.removetab(page);
	    }
	}
	void componentChange(JComponent component,JPanel pnl_Left) {
		component.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String name = evt.getPropertyName();
				if(name.equals("background")) {
					me.setBackground(component.getBackground());
					pnl_Left.setBackground(component.getBackground());
				}
				pnl_Left.setBackground(component.getBackground());
			}
		});
	}
	//it will work when panel is clicked
	void clickOn() {
		if(!page.getisSame() && !text.equals("null")) {
			String text = Work.getTextfromPath(page.getPath());
			txt_area.setText(text);
		}
		//pnl_Left.setBackground(Work.getLightenColor(color, 0.5f));
		notepad.setTab(this);
	}
	void setOldColor() {
		me.setBackground(color);
	}
	private void tabRefresh() {
		lbl_Name.setText(page.getName());
	}
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(bg);
		if(pnl_Left != null)pnl_Left.setBackground(bg);
	}

}
