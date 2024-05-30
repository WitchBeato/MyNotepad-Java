package componentExtra.TabPage;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Info.Pages;
import componentExtra.SpecialButtons.AddButton;
import componentExtra.SpecialButtons.DirectionButton;
import componentExtra.SpecialButtons.SpecialButton;
import doing.Page;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;

import Frames.Notepad;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tabs extends JPanel {
	public final int TORIGHT = 1, TOLEFT = -1;
	private JTextArea txt_area;
	private static final long serialVersionUID = 1L;
	private JComponent component;
	private Notepad mainFrame;
	private Pages pages;
	private JScrollPane sp_Tabs;
	JPanel pnl_Tabs;
	/**
	 * Create the panel.
	 */
	Tabs(){
		Inıtiliaze();
	}
	//getting the component which we will work
	public Tabs(JComponent component, JTextArea text,Notepad notepad, Pages pages) {
		mainFrame = notepad;
		txt_area = text;
		this.pages = pages;
		this.component = component;
		Inıtiliaze();
	}
	private void Inıtiliaze() {
		AddButton btn_Add = new AddButton(this);
		setLayout(new BorderLayout(0, 0));
		JPanel pnl_ToRight = new JPanel();
		pnl_ToRight.setPreferredSize(new Dimension(60,0));
		add(pnl_ToRight, BorderLayout.EAST);
		DirectionButton btn_Right;
		if(component != null) {
			btn_Right = new DirectionButton(component,DirectionButton.RIGHT);
		}
		else {
			btn_Right = new DirectionButton();
		}
		
		btn_Right.setPreferredSize(new Dimension(30,0));
		pnl_ToRight.add(btn_Right);
		pnl_ToRight.setLayout(new GridLayout(1, 0 , 0, 0));
		
		btn_Add.setPreferredSize(new Dimension(30,0));
		pnl_ToRight.add(btn_Add);
		
		JPanel pnl_ToLeft = new JPanel();
		pnl_ToLeft.setPreferredSize(new Dimension(30, 0));
		add(pnl_ToLeft, BorderLayout.WEST);
		pnl_ToLeft.setLayout(new BorderLayout(0, 0));
		DirectionButton btn_Left;
		if(component != null) {
			btn_Left = new DirectionButton(component,DirectionButton.LEFT);
		}
		else {
			btn_Left = new DirectionButton();
		}
		Font font = btn_Left.getFont();
		btn_Left.setFont(new Font(font.getFamily(),font.getStyle(),8));
		pnl_ToLeft.add(btn_Left, BorderLayout.CENTER);
		
		sp_Tabs = new JScrollPane();
		sp_Tabs.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_Tabs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(sp_Tabs, BorderLayout.CENTER);
		pnl_Tabs = new JPanel() {
			@Override
			public void paint(Graphics g) {
				sp_Tabs.repaint();
				super.paint(g);
			}
		};
		pnl_Tabs.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notch = e.getWheelRotation();
				moveTabs(sp_Tabs, notch*-1);
			}
		});
		sp_Tabs.setViewportView(pnl_Tabs);
		pnl_Tabs.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		component.addPropertyChangeListener("background", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            	Color color = component.getBackground();
                btn_Right.setBackground(color);
                btn_Left.setBackground(color);
                pnl_Tabs.setBackground(color);
            }
        });
		setTabs(pages, pnl_Tabs);
		btn_Right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moveTabs(sp_Tabs, TORIGHT);
			}
		});
		btn_Left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moveTabs(sp_Tabs, TOLEFT);
			}
		});
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTab(pnl_Tabs);
			}
		});
	}
	//add tabs to program
	void setTabs(Pages pages,JPanel panel) {
		if(pages == null) return;
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ArrayList<Page> tabs = pages.rtnArray();
		for(int i = 0; i< pages.rtnArray().size();i++) {
			MyTabs mytabs = new MyTabs(this, tabs.get(i),txt_area,mainFrame);
			mytabs.setPreferredSize(new Dimension(100,20));
			mytabs.setBackground(Color.white);
			panel.add(mytabs);
			mytabs.setVisible(true);
		}
	}
	//it enables us to move between tabs
	void moveTabs(JScrollPane scrollpane, int direction) {
        JScrollBar horizontalScrollBar = scrollpane.getHorizontalScrollBar();
        horizontalScrollBar.setPreferredSize(new Dimension(0,0));
        int currentValue = horizontalScrollBar.getValue();
        horizontalScrollBar.setValue(currentValue+100*direction);
	}
	void addTab(JPanel panel) {
		pages.add("Null");
		MyTabs mytab = new MyTabs(this,pages.rtnArray().getLast(),txt_area,mainFrame);
		mytab.setPreferredSize(new Dimension(100,20));
		mytab.setBackground(this.getBackground());
		mytab.setVisible(true);
		panel.add(mytab);		
		mainFrame.repaint();
		panel.validate();
	}
	@Override
	public Color getBackground() {
		// TODO Auto-generated method stub
		if(pnl_Tabs != null) return pnl_Tabs.getBackground();
		else return super.getBackground();
	}
	@Override
	public void repaint() {
		super.repaint();
		if(sp_Tabs != null) sp_Tabs.repaint();
	}
	public void removetab(Page page) {
		pages.remove(page);
	}

}
