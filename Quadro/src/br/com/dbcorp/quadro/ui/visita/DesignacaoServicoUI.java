package br.com.dbcorp.quadro.ui.visita;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DesignacaoServico;
import br.com.dbcorp.quadro.ui.DTextField;
import br.com.dbcorp.quadro.ui.Params;

public class DesignacaoServicoUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = -2164593759720499320L;
	
	private JTextField txMinutos;
	private JTextField txTema;
	private JTextField txOrador;
	private List<String> pessoas;
	
	private DesignacaoServico designacaoServico;
	private JButton btnDeletar;
	
	private ServicoUI parent;
	
	public DesignacaoServicoUI(DesignacaoServico designacaoServico, ServicoUI parent) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.designacaoServico = designacaoServico;
		this.parent = parent;
		this.pessoas = parent.pessoas;
		
		this.txMinutos = new JTextField();
		this.txTema = new JTextField();
		this.txOrador = new DTextField(this.pessoas);
		
		this.btnDeletar = new JButton(Params.btDeletarImg());
		this.btnDeletar.addActionListener(this);
		
		add(this.txMinutos, "2, 2, fill, default");
		add(this.txTema, "4, 2, fill, default");
		add(this.txOrador, "6, 2, fill, default");
		add(btnDeletar, "8, 2");
		
		this.setCampos();
		
		this.setMinimumSize(new Dimension(900, 34));
		this.setPreferredSize(new Dimension(1150, 34));
	}
	
	public void reset() {
		this.designacaoServico.setMinutos("");
		this.designacaoServico.setOrador("");
		this.designacaoServico.setTema("");
	}
	
	public DesignacaoServico obterDesignacao() {
		this.designacaoServico.setMinutos(this.txMinutos.getText());
		this.designacaoServico.setOrador(this.txOrador.getText());
		this.designacaoServico.setTema(this.txTema.getText());
		
		return this.designacaoServico;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnDeletar)) {
			this.parent.removeDesignacao(this, this.designacaoServico);
		}
	}
	
	private void setCampos() {
		this.txMinutos.setText(this.designacaoServico.getMinutos());
		this.txOrador.setText(this.designacaoServico.getOrador());
		this.txTema.setText(this.designacaoServico.getTema());
	}
}
