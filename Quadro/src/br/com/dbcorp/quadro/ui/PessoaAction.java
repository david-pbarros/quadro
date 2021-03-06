package br.com.dbcorp.quadro.ui;

import java.awt.Event;

import javax.swing.Icon;

import br.com.dbcorp.quadro.action.Action;

public class PessoaAction extends Action {
	private static final long serialVersionUID = 5153224100458029817L;
	
	public static final String NAME_VALUE = "Pessoas";

	//Tooltip
	public static final String LONG_DESCRIPTION_VALUE = "Pessoas";

	//Atalho do menu
	public static final int KEY_STROKE_MODIFIERS = Event.KEY_ACTION;

	//�cone do Menu
	public static Icon SMALL_ICON_VALUE = null;

	//Descri��o do menu
	public static final String SHORT_DESCRIPTION_VALUE = NAME_VALUE;
	
	public PessoaAction(MainFrame mainFrame) {
		super(NAME_VALUE, mainFrame);

		putValue(LONG_DESCRIPTION, LONG_DESCRIPTION_VALUE);
		putValue(SMALL_ICON, SMALL_ICON_VALUE);
		putValue(SHORT_DESCRIPTION, SHORT_DESCRIPTION_VALUE);
		
		this.internalFrameClass = PessoasUI.class;
	}
}
