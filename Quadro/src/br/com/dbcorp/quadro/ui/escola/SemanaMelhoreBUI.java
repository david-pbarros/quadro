package br.com.dbcorp.quadro.ui.escola;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.VidaMinisterio;

public class SemanaMelhoreBUI extends SemanaMelhoreUI {
	private static final long serialVersionUID = -8380400006598649332L;
	
	public SemanaMelhoreBUI(List<DesignacaoEscola> designacoes, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres) {
		FormLayout layout = new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:6dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("left:6dlu"),},
			new RowSpec[] {
				RowSpec.decode("top:6dlu"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("bottom:3dlu"),});
		
		this.inicializar(designacoes, null, diaReuniao, homens, mulheres, layout);
		
		this.setMinimumSize(new Dimension(931, 230));
		this.setPreferredSize(new Dimension(1200, 230));
	}
	
	public List<DesignacaoEscola> obterDesignacoes() {
		List<DesignacaoEscola> designacoes = new ArrayList<DesignacaoEscola>();
		
		if (!this.cbAss.isSelected() && !this.cbVis.isSelected()) {
			this.obterDesignacoesEstudo(designacoes, "B");
		}
		
		return designacoes;
	}
	
	@Override
	protected void inicializar(List<DesignacaoEscola> designacoes, VidaMinisterio vidaMinisterio, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres, FormLayout layout) {
		super.inicializar(designacoes, vidaMinisterio, diaReuniao, homens, mulheres, layout);
		
		add(this.topSeparator, "2, 1");
		add(this.headerPanel, "2, 2, fill, fill");
		add(this.rightSep, "1, 1, 1, 5");
		add(this.leftSep, "3, 1, 1, 5");
		add(this.downSep, "2, 5");
		add(this.tesourosPanel, "2, 3, fill, fill");
		add(this.facaMelhorPanel, "2, 4, fill, fill");
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.tesourosPanel.add(new JLabel("Designado:"), "3, 1, center, default");
		this.tesourosPanel.add(new JLabel("Tema / Fonte:"), "5, 1");
		this.tesourosPanel.add(new JLabel("Leitura da B\u00EDblia:"), "1, 3, right, default");
		this.tesourosPanel.add(this.txLeitor, "3, 3, fill, default");
		this.tesourosPanel.add(this.txLeituraFonte, "5, 3, fill, default");
	}
	
	@Override
	protected void semanaEnabled(boolean valor) {
		this.txLeituraFonte.setEnabled(valor);
		this.txLeitor.setEnabled(valor);
		
		super.semanaEnabled(valor);
	}
}
