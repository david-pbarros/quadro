package br.com.dbcorp.quadro.ui.escola;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.ui.DTextField;
import br.com.dbcorp.quadro.ui.ReadOnlyCheckBox;

public abstract class SemanaUI extends JPanel {
	private static final long serialVersionUID = -7407804004373159689L;
	
	protected ReadOnlyCheckBox cbReca;
	protected ReadOnlyCheckBox cbAss;
	protected ReadOnlyCheckBox cbVis;
	protected JTextField txData;
	protected JTextField txDetaque;
	protected JTextField txTema1;
	protected JTextField txTema2;
	protected JTextField txTema3;
	protected JTextField txEstd0;
	protected JTextField txEstd1;
	protected JTextField txEstd2;
	protected JTextField txEstd3;
	protected JTextField txAju1;
	protected JTextField txAju2;
	protected JTextField txAju3;
	protected DiaReuniao diaReuniao;
	protected List<DesignacaoEscola> designacoes;
	protected DesignacaoEscola designacao1;
	protected DesignacaoEscola designacao2;
	protected DesignacaoEscola designacao3;
	protected List<String> homens;
	protected List<String> mulheres;
	protected List<String> nomes;
	
	public abstract List<DesignacaoEscola> obterDesignacoes();

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
		
		this.cbAss.setSelected(false);
		this.cbReca.setSelected(false);
		this.cbVis.setSelected(false);
		
	}
	
	protected void inicializar(List<DesignacaoEscola> designacoes, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres) {
		this.designacoes = designacoes;
		this.diaReuniao = diaReuniao;
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
		
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.cbReca = new ReadOnlyCheckBox("Recapitula\u00E7\u00E3o");
		this.cbAss = new ReadOnlyCheckBox("Ass. / Congr.");
		this.cbVis = new ReadOnlyCheckBox("Vis. Super.");
	}
	
	protected void setTela(DesignacaoEscola designacao, JTextField txTema, JTextField txEstudante, JTextField txAjudante) {
		txTema.setText(designacao.getTema());
		txEstudante.setText(designacao.getEstudante());
		
		if (txAjudante != null) {
			txAjudante.setText(designacao.getAjudante());
		}
	}
	
	protected void setTipoDia() {
		if (this.diaReuniao != null) {
			this.txData.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.diaReuniao.getDia()));
			
			if (TipoDia.ASSEMBLEIA == this.diaReuniao.getTipoDia()) {
				this.cbAss.setSelected(true);
				this.ass();
				this.semanaEnabled(false);
				
			} else if (TipoDia.RECAPITULACAO == this.diaReuniao.getTipoDia()) {
				this.cbReca.setSelected(true);
				this.recap();
				this.semanaEnabled(false);
				
			} else if (TipoDia.VISITA == this.diaReuniao.getTipoDia()) {
				this.cbVis.setSelected(true);
				this.vis();
				this.semanaEnabled(false);
			}
		}
	}
	
	protected void semanaEnabled(boolean valor) {
		this.txEstd1.setEnabled(valor);
		this.txEstd2.setEnabled(valor);
		this.txEstd3.setEnabled(valor);
		
		this.txTema1.setEnabled(valor);
		this.txTema2.setEnabled(valor);
		this.txTema3.setEnabled(valor);
		
		this.txAju1.setEnabled(valor);
		this.txAju2.setEnabled(valor);
		this.txAju3.setEnabled(valor);
		
		this.limparCampos();
	}
	
	private void recap() {
		this.cbVis.setEnabled(false);
		this.cbAss.setEnabled(false);
		this.semanaEnabled(false);
	}
	
	private void ass() {
		this.cbVis.setEnabled(false);
		this.cbReca.setEnabled(false);
		this.semanaEnabled(false);
	}
	
	private void vis() {
		this.cbReca.setEnabled(false);
		this.cbAss.setEnabled(false);
		this.semanaEnabled(false);
	}
	
	private void limparCampos() {
		this.txEstd1.setText("");
		this.txEstd2.setText("");
		this.txEstd3.setText("");
		
		this.txTema1.setText("");
		this.txTema2.setText("");
		this.txTema3.setText("");
		
		this.txAju1.setText("");
		this.txAju2.setText("");
		this.txAju3.setText("");
	}
}