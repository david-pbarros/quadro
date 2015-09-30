package br.com.dbcorp.quadro.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {
	private static final long serialVersionUID = -3737552790292607704L;
	
	private Image backgroundImage;
	
	public JPanelWithBackground(Image img) throws IOException {
		this.backgroundImage = img;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.backgroundImage, 0, 0, this);
	}
}
