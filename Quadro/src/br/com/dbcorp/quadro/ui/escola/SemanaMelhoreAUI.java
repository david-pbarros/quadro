package br.com.dbcorp.quadro.ui.escola;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.VidaMinisterio;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.ui.DTextField;

public class SemanaMelhoreAUI extends SemanaMelhoreUI {
	private static final long serialVersionUID = -8380400006598649332L;
	
	protected JLabel lbDesApre;
	
	protected JTextField txPresidente;
	protected JTextField txDesApresent;
	protected JTextField txTemaDiscurso;
	protected JTextField txOradorDiscurso;
	protected JTextField txOradorJoias;
	
	protected DesignacaoEscola designacaoDis;
	protected DesignacaoEscola designacaoJoi;

	public SemanaMelhoreAUI(List<DesignacaoEscola> designacoes, VidaMinisterio vidaMinisterio, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres) {
		FormLayout layout = new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:6dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("left:6dlu"),},
			new RowSpec[] {
				RowSpec.decode("top:6dlu"),
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("bottom:3dlu"),});
		
		
		this.inicializar(designacoes, vidaMinisterio, diaReuniao, homens, mulheres, layout);

		this.setMinimumSize(new Dimension(931, 318));//284
		this.setPreferredSize(new Dimension(1200, 318));
	}
	
	@Override
	protected void inicializar(List<DesignacaoEscola> designacoes, VidaMinisterio vidaMinisterio, DiaReuniao diaReuniao, List<String> homens, List<String> mulheres, FormLayout layout) {
		this.lbDesApre = new JLabel("Desig. Apresentação:");
		
		this.txPresidente = new JTextField();
		this.txDesApresent = new JTextField();
		
		this.txOradorDiscurso = new DTextField(homens);
		this.txOradorJoias = new DTextField(homens);
		this.txTemaDiscurso = new JTextField();
		
		super.inicializar(designacoes, vidaMinisterio, diaReuniao, homens, mulheres, layout);
		
		JPanel presidentePanel = new JPanel();
		presidentePanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC}));
		
		presidentePanel.add(new JLabel("Presidente:"), "2, 2, right, default");
		presidentePanel.add(this.txPresidente, "4, 2, fill, default");
		presidentePanel.add(this.lbDesApre, "6, 2, right, default");
		presidentePanel.add(this.txDesApresent, "8, 2, fill, default");
		
		add(this.topSeparator, "2, 1");
		add(this.headerPanel, "2, 2, fill, fill");
		add(presidentePanel, "2, 3, fill, fill");
		add(this.rightSep, "1, 1, 1, 5");
		add(this.leftSep, "3, 1, 1, 5");
		add(this.tesourosPanel, "2, 4, fill, fill");
		add(this.facaMelhorPanel, "2, 5, fill, fill");
		add(this.downSep, "2, 6");
	}
	
	public List<DesignacaoEscola> obterDesignacoes() {
		this.vidaMinisterio.setPresidente(this.txPresidente.getText());
		this.vidaMinisterio.setDesgApresentacao(this.txDesApresent.getText());
		
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
		
		this.txPresidente.setText(this.vidaMinisterio.getPresidente());
		this.txDesApresent.setText(this.vidaMinisterio.getDesgApresentacao());
		
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
	protected void setTipoDia() {
		super.setTipoDia();
		
		this.lbDesApre.setVisible(false);
		this.txDesApresent.setVisible(false);
		
		if (TipoDia.VIDEOS == this.diaReuniao.getTipoDia()) {
			this.lbDesApre.setVisible(true);
			this.txDesApresent.setVisible(true);
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
