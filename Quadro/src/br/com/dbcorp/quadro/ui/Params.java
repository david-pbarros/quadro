package br.com.dbcorp.quadro.ui;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.swing.ImageIcon;

import br.com.dbcorp.quadro.Log;


public class Params {
	
	public static int WIDTH;
	public static int HEIGHT;
	public static int INTERNAL_WIDTH;
	public static int INTERNAL_HEIGHT;
	
	private static MainFrame FRAME;
	private static ImageIcon btNovo;
	private static ImageIcon btFechar;
	private static ImageIcon btRemover;
	private static ImageIcon btSalvar;
	private static ImageIcon btAprovar;
	private static ImageIcon btImprimir;
	private static ImageIcon btExport;
	private static ImageIcon btImport;
	private static ImageIcon btDeletar;
	private static ImageIcon btAdd;
	private static Image caixaTitulo;
	private static Image caixaTituloMenor;
	private static Image caixaMes;
	
	private static Properties props;
	
	private static DateTimeFormatter dateFormatter;
	private static DateTimeFormatter dateTimeFormatter;
	
	public static Properties propriedades() {
		if (props == null) {
			props = new Properties();
			
			try {
				props.load(iniLoad());
				
				for(Object key : props.keySet()) {
					System.out.println("chave: " + key + " valor: " + props.get(key));
				}
	
				props.put("verionNumber", 310);
				props.put("versionName", "3.1.0");
				
			} catch (Exception e) {
				Log.getInstance().error(e);
			}
		}
		
		return props;
	}
	
	public static MainFrame obterMainWindow() {
		return FRAME;
	}
	
	public static InputStream iniLoad() throws FileNotFoundException {
		File f = new File(getAppPath() + File.separator + "quadro.ini");
		return new FileInputStream(f);
	}
	
	public static void setDimensions(MainFrame frame) {
		FRAME = frame;
		WIDTH = FRAME.getWidth();
		HEIGHT = FRAME.getHeight();
		
		INTERNAL_WIDTH = (int) (WIDTH - (WIDTH * 0.02));
		INTERNAL_HEIGHT = (int) (HEIGHT - (HEIGHT * 0.15));
	}
	
	public static Image iconeAplicacao() {
		return obterImagem("/notice_ico.png").getImage();
	}
	
	public static ImageIcon btNovoImg() {
		if (btNovo == null) {
			btNovo = obterImagem("/newButton.png");
		}
		
		return btNovo;
	}
	
	public static ImageIcon btAddImg() {
		if (btAdd == null) {
			btAdd = obterImagem("/addButton.png");
		}
		
		return btAdd;
	}
	
	public static ImageIcon btFecharImg() {
		if (btFechar == null) {
			btFechar = obterImagem("/closeButton.png");
		}
		
		return btFechar;
	}
	
	public static ImageIcon btRemoverImg() {
		if (btRemover == null) {
			btRemover = obterImagem("/deleteButton.png");
		}
		
		return btRemover;
	}
	
	public static ImageIcon btSalvarImg() {
		if (btSalvar == null) {
			btSalvar = obterImagem("/saveButton.png");
		}
		
		return btSalvar;
	}
	
	public static ImageIcon btAprovarImg() {
		if (btAprovar == null) {
			btAprovar = obterImagem("/check.png");
		}
		
		return btAprovar;
	}
	
	public static ImageIcon btImprimirImg() {
		if (btImprimir == null) {
			btImprimir = obterImagem("/printer.png");
		}
		
		return btImprimir;
	}
	
	public static ImageIcon btExportImg() {
		if (btExport == null) {
			btExport = obterImagem("/export.png");
		}
		
		return btExport;
	}
	
	public static ImageIcon btImportImg() {
		if (btImport == null) {
			btImport = obterImagem("/importing.png");
		}
		
		return btImport;
	}
	
	public static ImageIcon btDeletarImg() {
		if (btDeletar == null) {
			btDeletar = obterImagem("/deletar.png");
		}
		
		return btDeletar;
	}
	
	public static Image caixaTitulo() {
		if (caixaTitulo == null) {
			caixaTitulo = obterImagem("/CaixaTitulo.png").getImage();
		}
		
		return caixaTitulo;
	}
	
	public static Image caixaTituloMenor() {
		if (caixaTituloMenor == null) {
			caixaTituloMenor = obterImagem("/CaixaTituloMenor.png").getImage();
		}
		
		return caixaTituloMenor;
	}
	
	public static Image caixaMes() {
		if (caixaMes == null) {
			caixaMes = obterImagem("/CaixaMes.png").getImage();
		}
		
		return caixaMes;
	}
	
	public static Image splashImage() {
		return obterImagem("/notice_board.png").getImage();
	}
	
	public static InputStream obterStream(String caminho) {
		InputStream stream = Params.class.getResourceAsStream(caminho);

		if (stream == null) {
			stream = Params.class.getResourceAsStream("/resources" + caminho);
		}
		
		return stream;
	}
	
	private static ImageIcon obterImagem(String caminho) {
		return new ImageIcon(obterURL(caminho));
	}
	
	private static URL obterURL(String caminho) {
		URL url = Params.class.getResource(caminho);

		if (url == null) {
			url = Params.class.getResource("/resources" + caminho);
		}
		
		return url;
	}
	
	public static String getAppPath() {
		String url = Params.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File dir = new File(url);
        
       System.out.println(dir.getParentFile().getPath() + " parent");
        
        File temp = new File(dir.getParentFile().getPath() + File.separator + "Quadro.jar");
        
        // se está executando do jar
        if (temp.exists()) {
    		return dir.getParentFile().getPath().replaceAll("%20", " ").replaceAll("%23", "#").replaceAll("%c3%a3", "ã").replaceAll("%c3%b3", "ó");
    	}
    	
    	return dir.getParentFile().getParentFile().getPath().replaceAll("%20", " ").replaceAll("%23", "#").replaceAll("%c3%a3", "ã").replaceAll("%c3%b3", "ó");
	}
	
	public static DateTimeFormatter dateFormate() {
		if (dateFormatter == null) {
			dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		}
		
		return dateFormatter;
	}
	
	public static DateTimeFormatter dateTimeFormate() {
		if (dateTimeFormatter == null) {
			dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		}
		
		return dateTimeFormatter;
	}
}