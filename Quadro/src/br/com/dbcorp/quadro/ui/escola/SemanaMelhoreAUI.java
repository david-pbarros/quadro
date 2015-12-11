package br.com.dbcorp.quadro.ui.escola;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.ui.DTextField;

public class SemanaMelhoreAUI extends SemanaMelhoreUI {
	private static final long serialVersionUID = -8380400006598649332L;
	
	protected JTextField txTemaDiscurso;
	protected JTextField txOradorDiscurso;
	protected JTextField txOradorJoias;
	
	protected DesignacaoEscola designacaoDis;
	protected DesignacaoEscola designacaoJoi;

	public SemanaMelhoreAUI(List<DesignacaoEscola> designacoes, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres) {
		this.txOradorDiscurso = new DTextField(homens);
		this.txOradorJoias = new DTextField(homens);
		this.txTemaDiscurso = new JTextField();
		
		this.inicializar(designacoes, diaReuniao, homens, mulheres);
		
		this.setMinimumSize(new Dimension(931, 284));
		this.setPreferredSize(new Dimension(1200, 284));
	}
	
	public List<DesignacaoEscola> obterDesignacoes() {
		List<DesignacaoEscola> designacoes = new ArrayList<DesignacaoEscola>();
		
		if (!this.cbAss.isSelected() && !this.cbVis.isSelected()) {
			this.designacaoDis = this.designacaoDis == null ? new DesignacaoEscola() : this.designacaoDis;
			this.designacaoJoi = this.designacaoJoi == null ? new DesignacaoEscola() : this.designacaoJoi;
			
			this.setDesignacao(this.designacaoDis, "A", -1, this.txTemaDiscurso, this.txOradorDiscurso, null);
			this.setDesignacao(this.designacaoJoi, "A", 0, null, this.txOradorJoias, null);
			
			designacoes.add(this.designacaoDis);
			designacoes.add(this.designacaoJoi);
			
			this.obterDesignacoesEstudo(designacoes, "A");
		}
		
		return designacoes;
	}
	
	@Override
	protected void setTesouroPanel() {
		this.tesourosPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		this.tesourosPanel.add(new JLabel("Designado:"), "3, 1, center, default");
		this.tesourosPanel.add(new JLabel("Tema / Fonte:"), "5, 1");
		this.tesourosPanel.add(new JLabel("Leitura da B\u00EDblia:"), "1, 7, right, default");
		this.tesourosPanel.add(this.txLeitor, "3, 7, fill, default");
		this.tesourosPanel.add(this.txLeituraFonte, "5, 7, fill, default");
		
		this.tesourosPanel.add(new JLabel("Discurso:"), "1, 3, right, default");
		this.tesourosPanel.add(new JLabel("Encontre joias espirituais:"), "1, 5, right, default");
		this.tesourosPanel.add(this.txOradorDiscurso, "3, 3, fill, default");
		this.tesourosPanel.add(this.txTemaDiscurso, "5, 3, fill, default");
		this.tesourosPanel.add(this.txOradorJoias, "3, 5, 3, 1, fill, default");
	}
	
	@Override
	protected void setCampos() {
		super.setCampos();
		
		for (DesignacaoEscola designacao : this.designacoes) {
			if (designacao.getNumero() == -1) {
				this.designacaoDis = designacao;
				setTela(designacao, this.txOradorDiscurso, null, this.txTemaDiscurso);
				
			} else if (designacao.getNumero() == 0) {
				this.designacaoJoi = designacao;
				this.setTela(designacao, this.txOradorJoias, null, null);
			} 
		}
	}
	
	@Override
	protected void semanaEnabled(boolean valor) {
		this.txTemaDiscurso.setEnabled(valor || this.cbVid.isSelected() || this.cbReca.isSelected());
		this.txOradorDiscurso.setEnabled(valor || this.cbVid.isSelected() || this.cbReca.isSelected());
		this.txOradorJoias.setEnabled(valor || this.cbVid.isSelected() || this.cbReca.isSelected());
		
		this.txLeituraFonte.setEnabled(valor || this.cbVid.isSelected());
		this.txLeitor.setEnabled(valor || this.cbVid.isSelected());
		
		super.semanaEnabled(valor);
	}
	
	@Override
	protected void limparCampos() {
		this.txTemaDiscurso.setText("");
		this.txOradorDiscurso.setText("");
		this.txOradorJoias.setText("");
		
		super.limparCampos();
	}
}
