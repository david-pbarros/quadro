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
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;

public class SemanaAUI extends SemanaUI {
	private static final long serialVersionUID = 4683909168372381224L;
	
	private DesignacaoEscola designacao0;
	
	public SemanaAUI(List<DesignacaoEscola> designacoes, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres) {
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
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("6dlu"),}));
		
		this.inicializar(designacoes, diaReuniao, homens, mulheres);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "1, 1, 1, 13, right, default");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		add(separator_1, "11, 1, 1, 13, left, default");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "2, 13, 9, 1, default, bottom");
		
		JSeparator separator_3 = new JSeparator();
		add(separator_3, "2, 1, 9, 1, default, top");
		
		JPanel destaquePanel = new JPanel();
		destaquePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(80dlu;default)"),
				ColumnSpec.decode("6px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),}));

		destaquePanel.add(new JLabel("Destaques da Leitura Biblica:"), "1, 1, fill, center");
		destaquePanel.add(this.txDetaque, "3, 1, fill, default");
		
		JPanel dataPanel = new JPanel();
		add(dataPanel, "5, 2, center, fill");
		
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		
		JPanel cbPanel = new JPanel();
		add(cbPanel, "7, 2, 3, 1, fill, fill");
		
		cbPanel.add(this.cbReca);
		cbPanel.add(this.cbVid);
		cbPanel.add(this.cbAss);
		cbPanel.add(this.cbVis);
		
		add(new JLabel("N\u00BA"), "3, 4");
		add(new JLabel("Tema:"), "5, 4, center, default");
		add(new JLabel("Estudante:"), "7, 4, center, default");
		add(new JLabel("Ajudante:"), "9, 6, center, top");
		add(new JLabel("1"), "3, 8, right, default");
		add(new JLabel("2"), "3, 10, right, default");
		add(new JLabel("3"), "3, 12, right, default");
		add(destaquePanel, "3, 6, 3, 1, fill, fill");
		add(this.txTema1, "5, 8, fill, default");
		add(this.txTema2, "5, 10, fill, default");
		add(this.txTema3, "5, 12, fill, default");
		add(this.txEstd0, "7, 6, fill, default");
		add(this.txEstd1, "7, 8, fill, default");
		add(this.txEstd2, "7, 10, fill, default");
		add(this.txEstd3, "7, 12, fill, default");
		add(this.txAju1, "9, 8, fill, top");
		add(this.txAju2, "9, 10, fill, default");
		add(this.txAju3, "9, 12, fill, default");
		
		this.setMinimumSize(new Dimension(931, 186));
		this.setPreferredSize(new Dimension(1200, 186));
		
		this.setCampos();
	}
	
	@Override
	protected void semanaEnabled(boolean valor) {
		super.semanaEnabled(valor);
		
		if (TipoDia.RECAPITULACAO == this.diaReuniao.getTipoDia() || TipoDia.VIDEOS == this.diaReuniao.getTipoDia()) {
			this.txEstd0.setEnabled(true);
			this.txDetaque.setEnabled(true);
		
			if (TipoDia.VIDEOS == this.diaReuniao.getTipoDia()) {
				this.txEstd1.setEnabled(true);
				this.txTema1.setEnabled(true);
				this.txAju1.setEnabled(true);
			}
		} else {
			this.txEstd0.setEnabled(valor);
			this.txDetaque.setEnabled(valor);
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.dbcorp.quadro.ui.escola.SemanaUI#obterDesignacoes()
	 */
	@Override
	public List<DesignacaoEscola> obterDesignacoes() {
		List<DesignacaoEscola> designacoes = new ArrayList<DesignacaoEscola>();
		
		if (!this.cbAss.isSelected() && !this.cbVis.isSelected()) {
			this.designacao0 = this.designacao0 == null ? new DesignacaoEscola() : this.designacao0;
			this.designacao0.setEstudante(this.txEstd0.getText());
			this.designacao0.setTema(this.txDetaque.getText());
			this.designacao0.setNumero(0);
			this.designacao0.setDia(this.diaReuniao);
			this.designacao0.setSala("A");
			
			if (!this.cbReca.isSelected()) {
				this.designacao1 = this.designacao1 == null ? new DesignacaoEscola() : this.designacao1;
				this.designacao1.setAjudante(this.txAju1.getText());
				this.designacao1.setEstudante(this.txEstd1.getText());
				this.designacao1.setTema(this.txTema1.getText());
				this.designacao1.setNumero(1);
				this.designacao1.setDia(this.diaReuniao);
				this.designacao1.setSala("A");
				
				designacoes.add(this.designacao1);
				
				if (!this.cbVid.isSelected()) {
					this.designacao2 = this.designacao2 == null ? new DesignacaoEscola() : this.designacao2;
					this.designacao2.setAjudante(this.txAju2.getText());
					this.designacao2.setEstudante(this.txEstd2.getText());
					this.designacao2.setTema(this.txTema2.getText());
					this.designacao2.setNumero(2);
					this.designacao2.setDia(this.diaReuniao);
					this.designacao2.setSala("A");
					
					this.designacao3 = this.designacao3 == null ? new DesignacaoEscola() : this.designacao3;
					this.designacao3.setAjudante(this.txAju3.getText());
					this.designacao3.setEstudante(this.txEstd3.getText());
					this.designacao3.setTema(this.txTema3.getText());
					this.designacao3.setNumero(3);
					this.designacao3.setDia(this.diaReuniao);
					this.designacao3.setSala("A");
					designacoes.add(this.designacao2);
					designacoes.add(this.designacao3);
				}
			}
					
			designacoes.add(this.designacao0);
		}

		return designacoes;
	}
	
	private void setCampos() {
		this.setTipoDia();
		
		for (DesignacaoEscola designacao : this.designacoes) {
			if (designacao.getNumero() == 0) {
				this.designacao0 = designacao;
				this.setTela(designacao, this.txDetaque, this.txEstd0, null);
				
			} else if (designacao.getNumero() == 1) {
				this.designacao1 = designacao;
				this.setTela(designacao, this.txTema1, this.txEstd1, this.txAju1);
			
			} else if (designacao.getNumero() == 2) {
				this.designacao2 = designacao;
				this.setTela(designacao, this.txTema2, this.txEstd2, this.txAju2);
			
			} else if (designacao.getNumero() == 3) {
				this.designacao3 = designacao;
				this.setTela(designacao, this.txTema3, this.txEstd3, this.txAju3);
			}
		}
	}
}