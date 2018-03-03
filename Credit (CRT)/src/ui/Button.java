package ui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends JButton {

	private static final long serialVersionUID = 1L;

	private Color background;
	private Color foreground;
	
	private Color blue = new Color(0, 221, 219);
	private Color orange = new Color(255, 192, 115);
	
	private int type;
	private boolean removeText = true;
	private String transaction;
	private String from;
	private Double amount;
	private String to;

	public Button(String name, int x, int y, int width, int height, JPanel panel, int type) {
		this.type = type;
		setDefaultColours();
		setText(name);
		setFont(new Font("Futura", this.getFont().getStyle(), this.getFont().getSize()));
		setContentAreaFilled(true);
		setBorderPainted(false);
		setOpaque(true);
		setBounds(x, y, width, height);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				if(type <= 2){
					if(type == 1) setBackground(orange);
					if(type == 2) setBackground(blue);
					setForeground(Color.WHITE);
				}else{
					if(type == 3) setForeground(orange);
					if(type == 4) setForeground(blue);
					setBackground(Color.WHITE);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setDefaultColours();
			}

			public void mouseEntered(MouseEvent e) {
				if(panel != null) panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(panel != null) panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		if(panel != null) panel.add(this);
	}
	
	public void paintComponent(Graphics g){
		//TODO :: set the correct colors for every transaction and make them usable!
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		if(!removeText){
			g2.setColor(orange);
			g2.setFont(new Font("Futura", this.getFont().getStyle(), 14));
			if(amount != 0.0) g2.drawString(amount + "", 137, 40);
		}
	}
	
	public void setFrom(String from){
		this.from = from;
	}
	
	public void setAmount(Double amount){
		this.amount = amount;
	}
	
	public void setTo(String to){
		this.to = to;
	}
	
	public void addImage(String path, boolean removeText){
		this.removeText = removeText;
		BufferedImage img = null;
		ImageIcon icon = null;
		try{
			img = ImageIO.read(new File(path));
		}catch(IOException e){
			e.printStackTrace();
		}
		icon = new ImageIcon(img);
		this.setIcon(icon);
		if(removeText){
			this.setText("");
		}else{
			setContentAreaFilled(false);
		}
	}
	
	public void setDefaultColours(){
		if(type <= 2){
			background = new Color(253, 253, 253);
			if(type == 1) foreground = orange;
			if(type == 2) foreground = blue;
		}else{
			foreground = new Color(253, 253, 253);
			if(type == 3) background = orange;
			if(type == 4) background = blue;
		}
		setForeground(foreground);
		setBackground(background);
	}
}
