package br.com.dbcorp.quadro.ui.servico;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
				RowSpec.decode("max(2dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("6dlu"),}));
		
		this.servico = servico == null ? new Servico() : servico;
		this.diaReuniao = diaReuniao;
		this.parent = parent;
		this.pessoas = parent.pessoas;
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "1, 1, 1, 9, right, default");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		add(separator_1, "9, 1, 1, 9, left, default");
		
		JPanel panel = new JPanel();
		add(panel, "3, 4, 5, 1, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;default)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Tema:");
		panel.add(lblNewLabel, "4, 1");
		
		JLabel lblNewLabel_1 = new JLabel("Orador:");
		panel.add(lblNewLabel_1, "6, 1");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "2, 9, 7, 1, default, bottom");
		
		JSeparator separator_3 = new JSeparator();
		add(separator_3, "2, 1, 7, 1, default, top");
		
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.chAssembleia = new ReadOnlyCheckBox("Assembl\u00E9ia / Congresso");
		this.chVisita = new ReadOnlyCheckBox("Vis. Superintendente.");
		
		this.txOracao = new DTextField(this.pessoas);
		
		this.btnAdicionar = new JButton(Params.btNovoImg());
		this.btnAdicionar.setToolTipText("Adicionar Nova Designação");
		this.btnAdicionar.addActionListener(this);
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		dataPanel.add(chAssembleia);
		dataPanel.add(chVisita);
		dataPanel.add(this.btnAdicionar);
		
		this.designacoesPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) designacoesPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(0);
		
		add(dataPanel, "3, 2, 5, 1, fill, fill");
		add(this.designacoesPanel, "3, 6, 5, 1, fill, fill");
		add(new JLabel("Ora\u00E7\u00E3o:"), "3, 8, right, default");
		add(this.txOracao, "5, 8, fill, default");
		
		this.setCampos();
		
		this.semanaHeight = 155;
		
		this.setMinimumSize(new Dimension(500, 107));
		this.setPreferredSize(new Dimension(950, 116));
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
		return this.semanaHeight;
	}
	
	public void setHeight() {
		if (this.servico.getDesignacoes() != null) {
			int totalLinhas = 37 * (this.servico.getDesignacoes().size() - 1);
			
			this.semanaHeight = 155 + totalLinhas;
			
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
			this.txData.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.diaReuniao.getDia()));
			
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
