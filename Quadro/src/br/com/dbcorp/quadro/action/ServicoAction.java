package br.com.dbcorp.quadro.action;

import java.awt.Event;

import javax.swing.Icon;

import br.com.dbcorp.quadro.ui.MainFrame;
import br.com.dbcorp.quadro.ui.servico.DesignacoesUI;

public class ServicoAction extends Action {
	private static final long serialVersionUID = -1900363790893943261L;

	public static final String NAME_VALUE = "Vida Crist�";

	//Atalho do menu
	public static final int KEY_STROKE_MODIFIERS = Event.KEY_ACTION;

	//�cone do Menu
	public static Icon SMALL_ICON_VALUE = null;

	//Descri��o do menu
	public static final String SHORT_DESCRIPTION_VALUE = "Nossa Vida Crist�";
	
	public ServicoAction(MainFrame mainFrame) {
		super(NAME_VALUE, mainFrame);

		putValue(SMALL_ICON, SMALL_ICON_VALUE);
		putValue(SHORT_DESCRIPTION, SHORT_DESCRIPTION_VALUE);
		
		this.internalFrameClass = DesignacoesUI.class;
	}
}