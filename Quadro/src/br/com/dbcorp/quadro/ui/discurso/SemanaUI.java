package br.com.dbcorp.quadro.ui.discurso;

import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Discurso;
import br.com.dbcorp.quadro.ui.ReadOnlyCheckBox;

public abstract class SemanaUI extends JPanel {
	private static final long serialVersionUID = 724475501677095187L;

	protected DiaReuniao diaReuniao;
	protected JTextField txData;
	protected JTextField txTema;
	protected ReadOnlyCheckBox chAssembleia;
	protected JTextField txOrador;
	protected Discurso discurso;
	protected JTextField txCongrecagao;
	protected JTextField txCidade;
	
	public Discurso obterDiscruso() {
		this.discurso.setOrador(this.txOrador.getText());
		this.discurso.setTema(this.txTema.getText());
		this.discurso.setDiaReuniao(this.diaReuniao);
		
		return this.discurso;
	};
	
	public void reset() {
		this.discurso.setCidade("");
		this.discurso.setCongregacao("");
		this.discurso.setOrador("");
		this.discurso.setTema("");
	}
}