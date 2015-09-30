package br.com.dbcorp.quadro.ui;

import java.awt.Color;
import java.util.List;

import javax.swing.JTextField;

public class DTextField extends JTextField {
	private static final long serialVersionUID = 1159218232310621959L;
	
	private AutoSuggestor autoSuggestor;
	
	public DTextField(List<String> pessoas) {
		this.autoSuggestor = new AutoSuggestor(this, Params.obterMainWindow(), pessoas, Color.WHITE.brighter(), Color.BLUE.darker(), Color.RED.darker(), 0.90f);
	}
	
	public void setDictionary(List<String> nomes) {
		this.autoSuggestor.setDictionary(nomes);
	}
}
