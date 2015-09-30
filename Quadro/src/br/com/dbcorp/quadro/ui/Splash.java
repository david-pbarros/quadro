package br.com.dbcorp.quadro.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JWindow;

import br.com.dbcorp.quadro.sincronismo.Versao;

public class Splash extends JWindow {
	private static final long serialVersionUID = -7952839849461887170L;

	private int width;
	private int height;
	
	public Splash(int width, int height) {
		final Splash splash = this;
		final int widthScreen = width;
		final int heightScreen = height;
		
		Image img = Params.splashImage();
		
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		
		Dimension size = new Dimension(this.width, this.height);
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    
	    this.centerScreen();
	    try {
	    	getContentPane().add(new JPanelWithBackground(img));
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					
					MainFrame mainFrame = new MainFrame(widthScreen, heightScreen, splash);
					mainFrame.setVisible(true);
					
					new Thread(new Runnable() {
						@Override
						public void run() {
							new Versao().verifica();
						}
					}).start();
					
				} catch (Exception e) {
					e.printStackTrace();
					
					JOptionPane.showMessageDialog(splash, "Erro ao iniciar o aplicativo.", "", JOptionPane.ERROR_MESSAGE);
					
					splash.dispose();
				}
				
			}
		}).start();
	}
	
	private void centerScreen() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		int w = dimension.width - this.width;
		int h = dimension.height - this.height;
		setLocation(w/2, h/2);
	}
}

/*class JPanelWithBackground extends JPanel {
	private static final long serialVersionUID = -3737552790292607704L;
	
	private Image backgroundImage;
	
	public JPanelWithBackground(Image img) throws IOException {
		this.backgroundImage = img;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.backgroundImage, 0, 0, this);
	}
}*/