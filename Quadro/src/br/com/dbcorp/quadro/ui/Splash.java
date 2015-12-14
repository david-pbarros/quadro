package br.com.dbcorp.quadro.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
					verificarInicializacao();
					
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
	
	public static void verificarInicializacao() throws IOException {
		Path iniPath = Paths.get(Params.getAppPath() + File.separator + "quadro.ini");
		Path logPath = Paths.get(Params.getAppPath() + File.separator + "Log");
		
		try {
			Files.createFile(iniPath);
			preencheIni(Files.newBufferedWriter(iniPath, StandardOpenOption.APPEND), logPath);
			
		} catch (FileAlreadyExistsException faex) {
			System.out.println("Arquivo ini já existente.");
		}
				
		try {
			Files.createDirectory(logPath);

		} catch (FileAlreadyExistsException faex) {
			System.out.println("Diretorio de Log já existente.");
		}
	}
	
	private static void preencheIni(BufferedWriter bw, Path logPath) throws IOException {
		bw.write("[DATABASE]");
		bw.newLine();
		bw.write("javax.persistence.jdbc.url=jdbc:sqlite:");
		
		String db = JOptionPane.showInputDialog("Caminho do banco de dados:", Params.getAppPath() + File.separator + "quadro.db");
		
		bw.write(db.replace("\\", "/"));
		bw.newLine();
		bw.write("eclipselink.ddl-generation=create-or-extend-tables");
		bw.newLine();
		bw.write("eclipselink.ddl-generation.output-mode=database");
		bw.newLine();
		bw.write("javax.persistence.jdbc.driver=org.sqlite.JDBC");
		bw.newLine();
		bw.write("javax.persistence.jdbc.user=sa");
		bw.newLine();
		bw.write("javax.persistence.jdbc.password=");
		bw.newLine();
		bw.write("[LOG]");
		bw.newLine();
		bw.write("logType=ERROR");
		bw.newLine();
		bw.write("logPath=");
		
		String log = JOptionPane.showInputDialog("Caminho do diretorio de logs:", logPath.toString());
		
		bw.write(log.replace("\\", "/"));
		bw.newLine();
		bw.write("[SINCRONISMO]");
		bw.newLine();
		bw.write("server=");
		bw.write(JOptionPane.showInputDialog("URL do servidor de sincronismo:", "quadro.jwdbcorp.dx.am"));
		bw.flush();
	}
}