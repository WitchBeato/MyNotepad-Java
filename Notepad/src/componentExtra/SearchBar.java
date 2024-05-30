package componentExtra;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class SearchBar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SearchBar() {
		Initiliaze();
	}
	private void Initiliaze() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.EAST);
	}

}
