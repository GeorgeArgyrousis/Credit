package ui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

public class PasswordField extends JPasswordField{

private static final long serialVersionUID = 1L;
	
	private JSeparator separator;
	private JLabel label;
	
	private Color lightBlue = new Color(83, 192, 191);
	private Color darkBlue = new Color(53, 64, 72);

	public PasswordField(String name, int x, int y, int width, int height, JPanel container){
		label = new JLabel(name);
		label.setForeground(lightBlue);
		separator = new JSeparator();
		separator.setForeground(lightBlue);
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
		setText("");
		setBounds(x, y, width, height);
		setBackground(darkBlue);
		setForeground(lightBlue);
		setBorder(null);
		setColumns(10);
		setCaretColor(lightBlue);
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
	
	@SuppressWarnings("deprecation")
	public void textSetter(){
		setText(getText());
		setFocusable(false);
	}
	
	public void clicked(){
		setFocusable(true);
	}
}
