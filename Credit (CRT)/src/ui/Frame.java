package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	public Frame(String title, JPanel wrapper){
		this.setTitle(title);
		this.setLayout(new BorderLayout());
		this.add(wrapper, BorderLayout.CENTER);
		this.pack();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

}
