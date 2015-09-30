package br.com.dbcorp.quadro.action;

import java.awt.Event;

import javax.swing.Icon;

import br.com.dbcorp.quadro.ui.MainFrame;
import br.com.dbcorp.quadro.ui.escola.DesignacoesUI;

public class EscolaAction extends Action {
	private static final long serialVersionUID = -1900363790893943261L;

	public static final String NAME_VALUE = "Escola";

	//Atalho do menu
	public static final int KEY_STROKE_MODIFIERS = Event.KEY_ACTION;

	//Ícone do Menu
	public static Icon SMALL_ICON_VALUE = null;

	//Descrição do menu
	public static final String SHORT_DESCRIPTION_VALUE = "Escola do Ministerio Teocrático";
	
	public EscolaAction(MainFrame mainFrame) {
		super(NAME_VALUE, mainFrame);

		putValue(SMALL_ICON, SMALL_ICON_VALUE);
		putValue(SHORT_DESCRIPTION, SHORT_DESCRIPTION_VALUE);
		
		this.internalFrameClass = DesignacoesUI.class;
	}
}