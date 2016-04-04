package br.com.dbcorp.quadro.ui.servico;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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

import br.com.dbcorp.quadro.entidades.DesignacaoServico;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.entidades.Servico;
import br.com.dbcorp.quadro.ui.DTextField;
import br.com.dbcorp.quadro.ui.Params;
import br.com.dbcorp.quadro.ui.ReadOnlyCheckBox;
import com.jgoodies.forms.layout.Sizes;

public class SemanaUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8298632485665436526L;
	
	private DiaReuniao diaReuniao;
	private Servico servico;
	private JTextField txData;
	private ReadOnlyCheckBox chAssembleia;
	private ReadOnlyCheckBox chVisita;
	private DTextField txOracao;
	private JButton btnAdicionar;
	private JPanel designacoesPanel;
	private int semanaHeight;
	private DesignacoesUI parent;
	protected List<String> pessoas;
	
	private List<DesignacaoServicoUI> designacaosServicoUI;
	private JTextField txOraIni;
	private JTextField txPresidente;
	private JTextField txDiscurso;
	private JTextField txOraDisc;
	private JTextField txJoias;
	
	private int basicHeight = 279;
	
	public SemanaUI(DiaReuniao diaReuniao, Servico servico, DesignacoesUI parent) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("6dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("6dlu"),}));
		
		this.servico = servico == null ? new Servico() : servico;
		this.diaReuniao = diaReuniao;
		this.parent = parent;
		this.pessoas = parent.pessoas;
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JPanel tesourosPanel = new JPanel();
		tesourosPanel.setBorder(new TitledBorder(null, "Tesouros da Palavra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("100dlu", true), Sizes.constant("100dlu", true)), 0),},
			new RowSpec[] {
				RowSpec.decode("bottom:default"),}));
		
		panel.add(new JLabel("Tema:"), "4, 1");
		panel.add(new JLabel("Orador:"), "6, 1");
		
		JSeparator separator_2 = new JSeparator();
		JSeparator separator_3 = new JSeparator();
		
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.chAssembleia = new ReadOnlyCheckBox("Assembl\u00E9ia / Congresso");
		this.chVisita = new ReadOnlyCheckBox("Vis. Superintendente.");
		
		this.txOracao = new DTextField(this.pessoas);
		this.txOraIni = new DTextField(this.pessoas);
		this.txPresidente = new DTextField(this.pessoas);
		this.txDiscurso = new JTextField();
		this.txOraDisc = new DTextField(this.pessoas);
		this.txJoias = new DTextField(this.pessoas);
		
		JLabel lblEncontreJiasEspirituais = new JLabel("Encontre J\u00F3ias Espirituais:");
		lblEncontreJiasEspirituais.setToolTipText("Orador");
		
		this.btnAdicionar = new JButton(Params.btNovoImg());
		this.btnAdicionar.setToolTipText("Adicionar Nova Designação");
		this.btnAdicionar.addActionListener(this);
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		dataPanel.add(chAssembleia);
		dataPanel.add(chVisita);
		//dataPanel.add(this.btnAdicionar);
		
		this.designacoesPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) designacoesPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		
		JPanel cabecalhoPanel = new JPanel();
		cabecalhoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;min)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		cabecalhoPanel.add(new JLabel("Ora\u00E7\u00E3o:"), "3, 1, right, top");
		cabecalhoPanel.add(this.txOraIni, "5, 1, fill, default");
		cabecalhoPanel.add(new JLabel("Presidente:"), "7, 1, right, default");
		cabecalhoPanel.add(this.txPresidente, "9, 1, fill, default");
		
		tesourosPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;min)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		tesourosPanel.add(new JLabel("Discruso Tema:"), "1, 1, right, default");
		tesourosPanel.add(this.txDiscurso, "3, 1, fill, default");
		tesourosPanel.add(new JLabel("Orador:"), "5, 1, right, default");
		tesourosPanel.add(this.txOraDisc, "7, 1, fill, default");
		tesourosPanel.add(lblEncontreJiasEspirituais, "1, 3, right, default");
		tesourosPanel.add(txJoias, "3, 3, 5, 1, fill, default");
		
		JPanel nossaVidaPanel = new JPanel();
		nossaVidaPanel.setBorder(new TitledBorder(null, "Nossa Vida Crist\u00E3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		nossaVidaPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("fill:default"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(2dlu;min):grow"),}));
		
		nossaVidaPanel.add(panel, "1, 1, fill, center");
		nossaVidaPanel.add(this.btnAdicionar, "3, 1");
		nossaVidaPanel.add(this.designacoesPanel, "1, 3, 3, 1, fill, fill");
		
		add(separator, "1, 1, 1, 11, right, default");
		add(separator_1, "9, 1, 1, 11, left, default");
		add(nossaVidaPanel, "3, 8, 5, 1, fill, fill");
		//add(panel, "3, 10, 5, 1, fill, fill");
		add(separator_2, "2, 11, 7, 1, default, bottom");
		add(separator_3, "2, 1, 7, 1, default, top");
		add(dataPanel, "3, 2, 5, 1, fill, fill");
		add(cabecalhoPanel, "3, 4, 5, 1, fill, fill");
		add(tesourosPanel, "3, 6, 5, 1, fill, fill");
		//add(this.designacoesPanel, "3, 12, 5, 1, fill, fill");
		add(new JLabel("Ora\u00E7\u00E3o:"), "3, 10, right, default");
		add(this.txOracao, "5, 10, fill, default");
		
		this.setCampos();
		
		this.semanaHeight = 279;//this.basicHeight;
		
		Dimension d = new Dimension(950, this.semanaHeight);
		
		this.setPreferredSize(new Dimension(950, 340));
		this.setMinimumSize(d);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnAdicionar)) {
			this.adicionaDesignacao(null);
			this.setHeightInterno();
		}
	}
	
	public void reset() {
		this.servico.setOracao("");
		
		for (DesignacaoServicoUI designacaoServicoUI : this.designacaosServicoUI) {
			designacaoServicoUI.reset();
		}
	}
	
	public Servico obterServico() {
		if (this.servico.getDesignacoes() != null) {
			this.servico.getDesignacoes().clear();
			
			if (this.designacaosServicoUI != null) {
				for (DesignacaoServicoUI designacaoServicoUI : this.designacaosServicoUI) {
					this.servico.getDesignacoes().add(designacaoServicoUI.obterDesignacao());
				}
			}
			
			this.servico.setOracao(this.txOracao.getText());
		}

		this.servico.setDiaReuniao(this.diaReuniao);
		
		return this.servico;
	}
	
	public int getSemanaHeight() {
		if (this.servico.getDesignacoes() == null || this.servico.getDesignacoes().isEmpty()) {
			return this.semanaHeight;
			
		} else {
			return this.semanaHeight - 60;
		}
	}
	
	public void setHeight() {
		if (this.servico.getDesignacoes() != null) {
			
			int totalLinhas = 36 * (this.servico.getDesignacoes().size() - 1);
			
			this.semanaHeight = this.basicHeight + totalLinhas;
			
			this.setPreferredSize(new Dimension(950, this.semanaHeight));
		}
	}
	
	public void removeDesignacao(DesignacaoServicoUI designacaoServicoUI, DesignacaoServico designacaoServico) {
		this.servico.getDesignacoes().remove(designacaoServico);
		
		this.designacaosServicoUI.remove(designacaoServicoUI);
		
		this.designacoesPanel.remove(designacaoServicoUI);
		
		this.setHeightInterno();
	}
	
	private void setCampos() {
		if (this.diaReuniao != null) {
			this.txData.setText(this.diaReuniao.getDia().format(Params.dateFormate()));
			
			if (TipoDia.ASSEMBLEIA == this.diaReuniao.getTipoDia()) {
				this.chAssembleia.setSelected(true);
				this.chVisita.setEnabled(false);
				this.setCheck();
				
			} else if (TipoDia.VISITA == this.diaReuniao.getTipoDia()) {
				this.chVisita.setSelected(true);
				this.chAssembleia.setEnabled(false);
				this.setCheck();
			}
		}
		
		this.txOracao.setText(this.servico.getOracao());
		
		if (this.servico.getDesignacoes() != null) {
			for (DesignacaoServico designacaoServico : this.servico.getDesignacoes()) {
				adicionaDesignacao(designacaoServico);
			}
		}
	}
	
	private void setCheck() {
		if (this.chAssembleia.isSelected() || this.chVisita.isSelected()) {
			this.txOracao.setText("");
			this.txOracao.setEnabled(false);
			this.txDiscurso.setText("");
			this.txDiscurso.setEnabled(false);
			this.txOraDisc.setText("");
			this.txOraDisc.setEnabled(false);
			this.txOraIni.setText("");
			this.txOraIni.setEnabled(false);
			this.txJoias.setText("");
			this.txJoias.setEnabled(false);
			
			this.btnAdicionar.setEnabled(false);
			
			if (this.servico.getDesignacoes() != null) {
				this.servico.getDesignacoes().clear();
			}
		} else {
			this.txOracao.setEnabled(true);
			this.btnAdicionar.setEnabled(true);
		}
	}
	
	private void adicionaDesignacao(DesignacaoServico designacaoServico) {
		if (designacaoServico == null) {
			int ordem  = 0;
			
			if (this.servico.getDesignacoes() != null) {
				ordem = this.servico.getDesignacoes().size();
			
			} else {
				this.servico.setDesignacoes(new ArrayList<DesignacaoServico>());
			}
			
			designacaoServico =   new DesignacaoServico();
			designacaoServico.setOrdem(ordem);

			this.servico.getDesignacoes().add(designacaoServico);
		}
		
		this.setDesignacaoTela(designacaoServico);
	}
	
	private void setDesignacaoTela(DesignacaoServico designacaoServico) {
		DesignacaoServicoUI designacaoServicoUI = new DesignacaoServicoUI(designacaoServico, this);
		
		if (this.designacaosServicoUI == null) {
			this.designacaosServicoUI = new ArrayList<DesignacaoServicoUI>();
		}
		
		this.designacaosServicoUI.add(designacaoServicoUI);
		
		this.designacoesPanel.add(designacaoServicoUI);
	}
	
	private void setHeightInterno() {
		this.setHeight();
		
		this.parent.setHeight();
	}
}
