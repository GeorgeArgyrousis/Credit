package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Color darkblue = new Color(0, 126, 203);
	private Color lightblue = new Color(79, 210, 246);
	
	private BufferedImage img = null;
	
	public Panel(int x, int y, int width, int height){
		setLayout(null);
		setPreferredSize(new Dimension(width, height));
		setVisible(true);
	}

	public Panel(int x, int y, int width, int height, JPanel container) {
		setBackground(lightblue);
		setLayout(null);
		setBounds(x, y, width, height);
		setVisible(true);
		if(container != null) container.add(this);
	}
	
	public void addImage(String path){
		try{
			img = ImageIO.read(new File(path));
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
		if(img != null){
			g.drawImage(img, 0, 0, null);
		}else{
			super.paintComponent(g);
			int panelHeight = getHeight();
			int panelWidth = getWidth();
			GradientPaint gradientPaint = new GradientPaint(panelWidth / 2, 0, darkblue, panelWidth / 2, panelHeight, lightblue);
			if (g instanceof Graphics2D) {
				Graphics2D graphics2D = (Graphics2D) g;
				graphics2D.setPaint(gradientPaint);
				graphics2D.fillRect(0, 0, panelWidth, panelHeight);
			}
		}
	}
}
