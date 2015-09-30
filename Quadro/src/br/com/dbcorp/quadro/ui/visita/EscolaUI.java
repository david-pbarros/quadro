package br.com.dbcorp.quadro.ui.visita;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.ui.DTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class EscolaUI extends JPanel {
	private static final long serialVersionUID = 4568788325510906250L;
	
	private JTextField txDetaque;
	private JTextField txTema1;
	private JTextField txTema2;
	private JTextField txTema3;
	private JTextField txEstd0;
	private JTextField txEstd1;
	private JTextField txEstd2;
	private JTextField txEstd3;
	private JTextField txAju1;
	private JTextField txAju2;
	private JTextField txAju3;
	private List<DesignacaoEscola> designacoes;
	private DesignacaoEscola designacao0;
	private DesignacaoEscola designacao1;
	private DesignacaoEscola designacao2;
	private DesignacaoEscola designacao3;
	private List<String> homens;
	private List<String> mulheres;
	private List<String> nomes;
	
	public EscolaUI(List<DesignacaoEscola> designacoes, List<String> homens, List<String> mulheres) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(170dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(170dlu;default)"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		TitledBorder title = BorderFactory.createTitledBorder("Escola do Ministério Teocrático");
		
		this.setBorder(title);
		
		this.inicializar(designacoes, homens, mulheres);
		
		JPanel destaquePanel = new JPanel();
		destaquePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(80dlu;default)"),
				ColumnSpec.decode("6px"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("20px"),}));

		destaquePanel.add(new JLabel("Destaques da Leitura Biblica:"), "1, 1, fill, center");
		destaquePanel.add(this.txDetaque, "3, 1, fill, default");
		
		add(new JLabel("N\u00BA"), "1, 1");
		add(new JLabel("Tema:"), "3, 1, center, default");
		add(new JLabel("Estudante:"), "5, 1, center, default");
		add(new JLabel("Ajudante:"), "7, 3, center, top");
		add(new JLabel("1"), "1, 5, right, default");
		add(new JLabel("2"), "1, 7, right, default");
		add(new JLabel("3"), "1, 9, right, default");
		add(destaquePanel, "1, 3, 3, 1, fill, fill");
		add(this.txTema1, "3, 5, fill, default");
		add(this.txTema2, "3, 7, fill, default");
		add(this.txTema3, "3, 9, fill, default");
		add(this.txEstd0, "5, 3, fill, default");
		add(this.txEstd1, "5, 5, fill, default");
		add(this.txEstd2, "5, 7, fill, default");
		add(this.txEstd3, "5, 9, fill, default");
		add(this.txAju1, "7, 5, fill, top");
		add(this.txAju2, "7, 7, fill, default");
		add(this.txAju3, "7, 9, fill, default");
		
		this.setMinimumSize(new Dimension(931, 145));
		this.setPreferredSize(new Dimension(1200, 145));
		
		this.setCampos();
	}
	
	public void reset() {
		this.txAju1.setText("");
		this.txAju2.setText("");
		this.txAju3.setText("");
		
		this.txEstd1.setText("");
		this.txEstd2.setText("");
		this.txEstd3.setText("");
		
		this.txTema1.setText("");
		this.txTema2.setText("");
		this.txTema3.setText("");
	}
	
	protected void inicializar(List<DesignacaoEscola> designacoes, List<String> homens, List<String> mulheres) {
		this.designacoes = designacoes;
		this.homens = homens;
		this.mulheres = mulheres;
		
		this.nomes = new ArrayList<>(this.homens);
		this.nomes.addAll(this.mulheres);
		
		this.txDetaque = new JTextField();
		this.txTema1 = new JTextField();
		this.txTema2 = new JTextField();
		this.txTema3 = new JTextField();
		this.txEstd0 = new DTextField(this.homens);
		this.txEstd1 = new DTextField(this.mulheres);
		this.txEstd2 = new DTextField(this.homens);
		this.txEstd3 = new DTextField(this.nomes);
		this.txAju1 = new DTextField(this.mulheres);
		this.txAju2 = new DTextField(this.homens);
		this.txAju3 = new DTextField(this.nomes);
		
		this.txEstd0.setColumns(10);
	}
	
	protected void setTela(DesignacaoEscola designacao, JTextField txTema, JTextField txEstudante, JTextField txAjudante) {
		txTema.setText(designacao.getTema());
		txEstudante.setText(designacao.getEstudante());
		
		if (txAjudante != null) {
			txAjudante.setText(designacao.getAjudante());
		}
	}
	
	public List<DesignacaoEscola> obterDesignacoes() {
		List<DesignacaoEscola> designacoes = new ArrayList<DesignacaoEscola>();
		
		this.designacao0 = this.designacao0 == null ? new DesignacaoEscola() : this.designacao0;
		this.designacao0.setEstudante(this.txEstd0.getText());
		this.designacao0.setTema(this.txDetaque.getText());
		this.designacao0.setNumero(0);
		this.designacao0.setSala("A");
		
		this.designacao1 = this.designacao1 == null ? new DesignacaoEscola() : this.designacao1;
		this.designacao1.setAjudante(this.txAju1.getText());
		this.designacao1.setEstudante(this.txEstd1.getText());
		this.designacao1.setTema(this.txTema1.getText());
		this.designacao1.setNumero(1);
		this.designacao1.setSala("A");
		
		this.designacao2 = this.designacao2 == null ? new DesignacaoEscola() : this.designacao2;
		this.designacao2.setAjudante(this.txAju2.getText());
		this.designacao2.setEstudante(this.txEstd2.getText());
		this.designacao2.setTema(this.txTema2.getText());
		this.designacao2.setNumero(2);
		this.designacao2.setSala("A");
		
		this.designacao3 = this.designacao3 == null ? new DesignacaoEscola() : this.designacao3;
		this.designacao3.setAjudante(this.txAju3.getText());
		this.designacao3.setEstudante(this.txEstd3.getText());
		this.designacao3.setTema(this.txTema3.getText());
		this.designacao3.setNumero(3);
		this.designacao3.setSala("A");

		designacoes.add(this.designacao0);
		designacoes.add(this.designacao1);
		designacoes.add(this.designacao2);
		designacoes.add(this.designacao3);

		return designacoes;
	}
	
	private void setCampos() {
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