package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Field extends JTextField{
	
	private static final long serialVersionUID = 1L;
	
	private JSeparator separator;
	private JLabel label;
	
	private Color white = Color.WHITE; 

	public Field(String name, int x, int y, int width, int height, JPanel container){
		label = new JLabel(name);
		label.setForeground(white);
		separator = new JSeparator();
		separator.setForeground(white);
		if(height == 150){
			separator.setBounds(x, y + 85, width, height);
			label.setBounds(x, y - 35, width + 25, height);
		}else if(height == 100){
			separator.setBounds(x, y + 65, width, height);
			label.setBounds(x, y - 35, width + 25, height);
		}else{
			separator.setBounds(x, y + 18, width, height);
			label.setBounds(x, y - 18, width + 25, height);
		}
		label.setFont(new Font("Futura", getFont().getStyle(), 15));
		setText("");
		setBounds(x, y, width, height);
		setBorder(null);
        setOpaque(false);
		setBackground(null);
		setForeground(white);
		setColumns(10);
		setCaretColor(white);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clicked();
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					textSetter();
				}
			}
		});
		container.add(separator);
		container.add(label);
		container.add(this);
	}
	
	public void textSetter(){
		setText(getText());
		setFocusable(false);
	}
	
	public void clicked(){
		setFocusable(true);
	}
}
