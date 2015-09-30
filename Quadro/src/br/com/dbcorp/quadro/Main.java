package br.com.dbcorp.quadro;

import java.awt.EventQueue;

import br.com.dbcorp.quadro.ui.Splash;

public class Main {

	public static int width = 1024;//x
	public static int height = 768;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Splash splash = new Splash(Main.width, Main.height);
				splash.setVisible(true);
			}
		});
	}
}
