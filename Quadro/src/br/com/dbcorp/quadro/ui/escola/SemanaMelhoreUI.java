package br.com.dbcorp.quadro.ui.escola;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.ui.DTextField;
import br.com.dbcorp.quadro.ui.ReadOnlyCheckBox;

public abstract class SemanaMelhoreUI extends JPanel {
	private static final long serialVersionUID = -564622418425978784L;
	
	protected JPanel tesourosPanel;
	
	protected ReadOnlyCheckBox cbReca;
	protected ReadOnlyCheckBox cbAss;
	protected ReadOnlyCheckBox cbVis;
	protected ReadOnlyCheckBox cbVid;
	protected JTextField txData;
	protected DiaReuniao diaReuniao;
	
	
	protected JTextField txLeituraFonte;
	protected JTextField txLeitor;
	
	protected JTextField txEstdVisita;
	protected JTextField txEstdReVisita;
	protected JTextField txEstdEstudo;
	protected JTextField txAjudVisita;
	protected JTextField txAjudReVisita;
	protected JTextField txAjudEstudo;

	protected List<DesignacaoEscola> designacoes;
	protected List<String> homens;
	protected List<String> mulheres;
	protected List<String> nomes;
	
	protected DesignacaoEscola designacaoLei;
	protected DesignacaoEscola designacaoVis;
	protected DesignacaoEscola designacaoRev;
	protected DesignacaoEscola designacaoEst;

	public abstract List<DesignacaoEscola> obterDesignacoes();
	protected abstract void setTesouroPanel();
	
	public void reset() {
		//this.txAju1.setText("");
		
		this.cbAss.setSelected(false);
		this.cbReca.setSelected(false);
		this.cbVid.setSelected(false);
		this.cbVis.setSelected(false);
		
	}
	
	protected void inicializar(List<DesignacaoEscola> designacoes, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:6dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("left:6dlu"),},
			new RowSpec[] {
				RowSpec.decode("top:6dlu"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("bottom:3dlu"),}));
		
		JPanel headerPanel = new JPanel();
		
		headerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JSeparator rightSep = new JSeparator();
		JSeparator topSeparator = new JSeparator();
		JSeparator leftSep = new JSeparator();
		JSeparator downSep = new JSeparator();

		rightSep.setOrientation(SwingConstants.VERTICAL);
		leftSep.setOrientation(SwingConstants.VERTICAL);
		
		this.designacoes = designacoes;
		this.diaReuniao = diaReuniao;
		
		this.mulheres = mulheres;
		this.homens = homens;
		
		this.nomes = new ArrayList<>(this.homens);
		this.nomes.addAll(this.mulheres);
		
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.cbReca = new ReadOnlyCheckBox("Recapitula\u00E7\u00E3o");
		this.cbVid = new ReadOnlyCheckBox("Apresentações");
		this.cbAss = new ReadOnlyCheckBox("Ass. / Congr.");
		this.cbVis = new ReadOnlyCheckBox("Vis. Super.");
		
		this.txLeitor = new DTextField(this.homens);
		
		this.txEstdVisita = new DTextField(this.nomes);
		this.txEstdReVisita = new DTextField(this.nomes);
		this.txEstdEstudo = new DTextField(this.nomes);
		this.txAjudVisita = new DTextField(this.nomes);
		this.txAjudReVisita = new DTextField(this.nomes);
		this.txAjudEstudo = new DTextField(this.nomes);

		this.txLeituraFonte = new JTextField();
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		
		headerPanel.add(dataPanel, "1, 1, fill, fill");
		headerPanel.add(this.cbReca, "3, 1");
		headerPanel.add(this.cbVid, "5, 1");
		headerPanel.add(this.cbAss, "7, 1");
		headerPanel.add(this.cbVis, "9, 1");
		
		this.tesourosPanel = new JPanel();
		this.tesourosPanel.setBorder(new TitledBorder(null, "Tesouros da Palavra de Deus", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		this.setTesouroPanel();
		
		JPanel facaMelhorPanel = new JPanel();
		facaMelhorPanel.setBorder(new TitledBorder(null, "Fa\u00E7a Seu Melhor no Minist\u00E9rio", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		facaMelhorPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		facaMelhorPanel.add(new JLabel("Estudante:"), "3, 1, center, default");
		facaMelhorPanel.add(new JLabel("Ajudante:"), "5, 1, center, default");
		facaMelhorPanel.add(new JLabel("Visita:"), "1, 3, right, default");
		facaMelhorPanel.add(new JLabel("Revisita:"), "1, 5, right, default");
		facaMelhorPanel.add(new JLabel("Estudo:"), "1, 7, right, default");
		facaMelhorPanel.add(this.txEstdVisita, "3, 3, fill, default");
		facaMelhorPanel.add(this.txAjudVisita, "5, 3, fill, default");
		facaMelhorPanel.add(this.txEstdReVisita, "3, 5, fill, default");
		facaMelhorPanel.add(this.txAjudReVisita, "5, 5, fill, default");
		facaMelhorPanel.add(this.txEstdEstudo, "3, 7, fill, default");
		facaMelhorPanel.add(this.txAjudEstudo, "5, 7, fill, default");
		
		add(topSeparator, "2, 1");
		add(headerPanel, "2, 2, fill, fill");
		add(rightSep, "1, 1, 1, 5");
		add(leftSep, "3, 1, 1, 5");
		add(downSep, "2, 5");
		add(this.tesourosPanel, "2, 3, fill, fill");
		add(facaMelhorPanel, "2, 4, fill, fill");
		
		this.setCampos();
	}
	
	protected void setDesignacao(DesignacaoEscola designacao, String sala, int numero, JTextField tema, JTextField designado, JTextField ajudante) {
		designacao.setEstudante(designado.getText());
		designacao.setNumero(numero);
		designacao.setDia(this.diaReuniao);
		designacao.setSala(sala);

		designacao.setTema(tema == null ? null : tema.getText());
		designacao.setAjudante(ajudante == null ? null : ajudante.getText());
	}
	
	protected void setCampos() {
		this.setTipoDia();
		
		for (DesignacaoEscola designacao : this.designacoes) {
			if (designacao.getNumero() == 1) {
				this.designacaoLei = designacao;
				this.setTela(designacao, this.txLeitor, null, this.txLeituraFonte);
				
			} else if (designacao.getNumero() == 2) {
				this.designacaoVis = designacao;
				this.setTela(designacao, this.txEstdVisita, this.txAjudVisita, null);
			
			} else if (designacao.getNumero() == 3) {
				this.designacaoRev = designacao;
				this.setTela(designacao, this.txEstdReVisita, this.txAjudReVisita, null);
			
			} else if (designacao.getNumero() == 4) {
				this.designacaoEst = designacao;
				this.setTela(designacao, this.txEstdEstudo, this.txAjudEstudo, null);
			}
		}
	}
	
	protected void setTipoDia() {
		if (this.diaReuniao != null) {
			this.txData.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.diaReuniao.getDia()));
			
			if (TipoDia.ASSEMBLEIA == this.diaReuniao.getTipoDia()) {
				this.cbAss.setSelected(true);
				this.setChecks(this.cbAss);
				
			} else if (TipoDia.RECAPITULACAO == this.diaReuniao.getTipoDia()) {
				this.cbReca.setSelected(true);
				this.setChecks(this.cbReca);
				
			} else if (TipoDia.VISITA == this.diaReuniao.getTipoDia()) {
				this.cbVis.setSelected(true);
				this.setChecks(this.cbVis);
			
			} else if (TipoDia.VIDEOS == this.diaReuniao.getTipoDia()) {
				this.cbVid.setSelected(true);
				this.setChecks(this.cbVid);
			}
		}
	}
	
	private void setChecks(ReadOnlyCheckBox checkSelecionado) {
		this.cbVis.setEnabled(false);
		this.cbAss.setEnabled(false);
		this.cbVid.setEnabled(false);
		this.cbReca.setEnabled(false);
		
		checkSelecionado.setEnabled(true);
		
		this.semanaEnabled(false);
	}
	
	protected void semanaEnabled(boolean valor) {
		this.txEstdVisita.setEnabled(valor);
		this.txEstdReVisita.setEnabled(valor);
		this.txEstdEstudo.setEnabled(valor);
		this.txAjudVisita.setEnabled(valor);
		this.txAjudReVisita.setEnabled(valor);
		this.txAjudEstudo.setEnabled(valor);
		
		this.limparCampos();
	}
	
	protected void setTela(DesignacaoEscola designacao, JTextField estudante, JTextField ajudante, JTextField temaFonte) {
		estudante.setText(designacao.getEstudante());
		
		if (ajudante != null) {
			ajudante.setText(designacao.getAjudante());
		}
		
		if (temaFonte != null) {
			temaFonte.setText(designacao.getTema());
		}
	}
	
	protected void limparCampos() {
		this.txLeituraFonte.setText("");
		this.txLeitor.setText("");
		this.txEstdVisita.setText("");
		this.txEstdReVisita.setText("");
		this.txEstdEstudo.setText("");
		this.txAjudVisita.setText("");
		this.txAjudReVisita.setText("");
		this.txAjudEstudo.setText("");
	}
	
	protected void obterDesignacoesEstudo(List<DesignacaoEscola> designacoes, String sala) {
		if (!this.cbReca.isSelected()) {
			this.designacaoLei = this.designacaoLei == null ? new DesignacaoEscola() : this.designacaoLei;
			
			if (!("B".equals(sala) && this.cbVid.isSelected())) {
				this.setDesignacao(this.designacaoLei, sala, 1, this.txLeituraFonte, this.txLeitor, null);
			}
			
			designacoes.add(this.designacaoLei);
			
			if (!this.cbVid.isSelected()) {
				this.designacaoVis = this.designacaoVis == null ? new DesignacaoEscola() : this.designacaoVis;
				this.designacaoRev = this.designacaoRev == null ? new DesignacaoEscola() : this.designacaoRev;
				this.designacaoEst = this.designacaoEst == null ? new DesignacaoEscola() : this.designacaoEst;
				
				this.setDesignacao(this.designacaoVis, sala, 2, null, this.txEstdVisita, this.txAjudVisita);
				this.setDesignacao(this.designacaoRev, sala, 3, null, this.txEstdReVisita, this.txAjudReVisita);
				this.setDesignacao(this.designacaoEst, sala, 4, null, this.txEstdEstudo, this.txAjudEstudo);
				
				designacoes.add(this.designacaoVis);
				designacoes.add(this.designacaoRev);
				designacoes.add(this.designacaoEst);
			}
		}
	}
}
