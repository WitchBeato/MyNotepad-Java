package Info;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import doing.Page;

public class Pages implements Serializable{
	ArrayList<Page> tabs;
	public Pages(){
		tabs = new ArrayList<>();
	}
	//add new tab to Pages
	public void newTab(String Path) {
		tabs.add(new Page(Path));
	}
	public ArrayList<Page> rtnArray(){
		return tabs;
	}
	public void add() {
		Page page = new Page();
		tabs.add(page);
	}
	public void add(Page page) {
		tabs.add(page);
	}
	public void add(String path) {
		Page page = new Page(path);
		tabs.add(page);
	}
	public void remove(Page page) {
		tabs.remove(page);
	}
	public static void saveFile(String Directory,Pages pages) throws IOException {
		File file = new File(Directory);
		FileOutputStream fileOut = new FileOutputStream(file);
		ObjectOutputStream stream = new ObjectOutputStream(fileOut);
		stream.writeObject(pages);
		stream.close();
		fileOut.close();
	}
	public static Pages getFile(String Directory) throws IOException, ClassNotFoundException {
		Pages pages;
		File file = new File(Directory);
		FileInputStream fileOut = new FileInputStream(file);
		ObjectInputStream stream = new ObjectInputStream(fileOut);
		pages = (Pages) stream.readObject();
		stream.close();
		fileOut.close();
		return pages;
	}
	
}
