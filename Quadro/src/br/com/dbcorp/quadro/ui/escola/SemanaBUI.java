package br.com.dbcorp.quadro.ui.escola;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;

public class SemanaBUI extends SemanaUI {
	private static final long serialVersionUID = 4683909168372381224L;
	
	public SemanaBUI(List<DesignacaoEscola> designacoes, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("6dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(170dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(170dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("6dlu"),},
			new RowSpec[] {
				RowSpec.decode("6dlu"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("6dlu"),}));
		
		this.inicializar(designacoes, diaReuniao, homens, mulheres);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "1, 1, 1, 11, right, default");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		add(separator_1, "11, 1, 1, 11, left, default");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "2, 11, 9, 1, default, bottom");
		
		JSeparator separator_3 = new JSeparator();
		add(separator_3, "2, 1, 9, 1, default, top");
		
		JPanel dataPanel = new JPanel();
		add(dataPanel, "5, 2, center, fill");
		
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		
		JPanel cbPanel = new JPanel();
		add(cbPanel, "7, 2, 3, 1, fill, fill");
		
		cbPanel.add(cbReca);
		cbPanel.add(cbAss);
		cbPanel.add(cbVis);
		
		add(new JLabel("N\u00BA"), "3, 4");
		add(new JLabel("Tema:"), "5, 4, center, default");
		add(new JLabel("Estudante:"), "7, 4, center, default");
		add(new JLabel("Ajudante:"), "9, 4, center, default");
		add(new JLabel("1"), "3, 6, right, default");
		add(new JLabel("2"), "3, 8, right, default");
		add(new JLabel("3"), "3, 10, right, default");
		
		add(this.txTema1, "5, 6, fill, default");
		add(this.txTema2, "5, 8, fill, default");
		add(this.txTema3, "5, 10, fill, default");
		add(this.txEstd1, "7, 6, fill, default");
		add(this.txEstd2, "7, 8, fill, default");
		add(this.txEstd3, "7, 10, fill, default");
		add(this.txAju1, "9, 6, fill, top");
		add(this.txAju2, "9, 8, fill, default");
		add(this.txAju3, "9, 10, fill, default");
		
		this.setMinimumSize(new Dimension(931, 157));
		this.setPreferredSize(new Dimension(1200, 157));
		
		this.setCampos();
	}
	
	public List<DesignacaoEscola> obterDesignacoes() {
		List<DesignacaoEscola> designacoes = new ArrayList<DesignacaoEscola>();
		
		if (!this.cbAss.isSelected() && !this.cbReca.isSelected() && !this.cbVis.isSelected()) {
			this.designacao1 = this.designacao1 == null ? new DesignacaoEscola() : this.designacao1;
			this.designacao1.setAjudante(this.txAju1.getText());
			this.designacao1.setEstudante(this.txEstd1.getText());
			this.designacao1.setTema(this.txTema1.getText());
			this.designacao1.setNumero(1);
			this.designacao1.setDia(this.diaReuniao);
			this.designacao1.setSala("B");
			
			this.designacao2 = this.designacao2 == null ? new DesignacaoEscola() : this.designacao2;
			this.designacao2.setAjudante(this.txAju2.getText());
			this.designacao2.setEstudante(this.txEstd2.getText());
			this.designacao2.setTema(this.txTema2.getText());
			this.designacao2.setNumero(2);
			this.designacao2.setDia(this.diaReuniao);
			this.designacao2.setSala("B");
			
			this.designacao3 = this.designacao3 == null ? new DesignacaoEscola() : this.designacao3;
			this.designacao3.setAjudante(this.txAju3.getText());
			this.designacao3.setEstudante(this.txEstd3.getText());
			this.designacao3.setTema(this.txTema3.getText());
			this.designacao3.setNumero(3);
			this.designacao3.setDia(this.diaReuniao);
			this.designacao3.setSala("B");
			
			designacoes.add(this.designacao1);
			designacoes.add(this.designacao2);
			designacoes.add(this.designacao3);
		}

		return designacoes;
	}
	
	private void setCampos() {
		this.setTipoDia();
		
		for (DesignacaoEscola designacao : this.designacoes) {
			if (designacao.getNumero() == 1) {
				this.designacao1 = designacao;
				this.setTela(designacao, this.txTema1, this.txEstd1,this.txAju1);
			
			} else if (designacao.getNumero() == 2) {
				this.designacao2 = designacao;
				this.setTela(designacao, this.txTema2, this.txEstd2,this.txAju2);
			
			} else if (designacao.getNumero() == 3) {
				this.designacao3 = designacao;
				this.setTela(designacao, this.txTema3, this.txEstd3,this.txAju3);
			}
		}
	}
}