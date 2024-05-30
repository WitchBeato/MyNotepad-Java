package doing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Info.Settings;

public class Page implements Serializable{
	private String path;
	private String name;
	public Page() {
		name = "Null";
		path = name;
	}
	public Page(String path) {
		this.path = path;
		setName(path);
	}
	public String getPath() {
		if(path != null) return path;
		else return null;
	}
	public String getName  () {
		return name;
	}
	//get name of the file
	private void setName(String Path) {
		char c[] = path.toCharArray();
		String realName = "";
		for (int i = c.length-1; i >= 0; i--) {
			if(c[i] == '\\') {
				break;
			}
			realName += c[i];
		}
		realName = Work.getReverse(realName);
		this.name = realName;
	}
	public void setPath(String path) {
		this.path = path;
		setName(path);
	}
	public Boolean getisSame() {
		if(path == null) return false;
		else if(path.equals(name)) return true;
		else return false;
	}
}
