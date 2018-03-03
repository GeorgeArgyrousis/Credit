package view;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.Frame;

public class MainFrame {

	/* serialVersionUID */
	public static final long serialVersionUID = 1L;
	
	public static final String TITLE = "v0.10";
	/**
	 * Constructs a new Frame.
	 */
	public MainFrame(JPanel mainPanel) {
		/* Overriding main panel close operation */
		new Frame(TITLE, mainPanel).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
	}
}
