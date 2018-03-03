package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Label extends JLabel{

	private static final long serialVersionUID = 1L;

	public Label(String name, int x, int y, int width, int height, JPanel container){
		setText(name);
		setHorizontalAlignment(SwingConstants.CENTER);
		setForeground(new Color(255, 255, 255));
		setFont(new Font("Futura", getFont().getStyle(), height));
		setBounds(x, y, width, height);
		container.add(this);
	}
}
