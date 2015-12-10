package br.com.dbcorp.quadro.ui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class ReadOnlyCheckBox extends JCheckBox {
	private static final long serialVersionUID = -4752704813916253470L;

	public ReadOnlyCheckBox() {
		super();
		this.setFocusable(false);
	}
	
	public ReadOnlyCheckBox(String label) {
		super(label);
		this.setFocusable(false);
	}
	
	public ReadOnlyCheckBox (String text, boolean selected) {
        super(text,selected);
    }
	
	protected void processKeyEvent(KeyEvent e) {
		this.exibeMensagem();
	}

    protected void processMouseEvent(MouseEvent e) {
    	if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3) {
    		this.exibeMensagem();
    	}
    }
    
    private void exibeMensagem() {
    	JOptionPane.showMessageDialog(this.getParent(), "Use o cadastro de meses para definir dias especiais!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
}
