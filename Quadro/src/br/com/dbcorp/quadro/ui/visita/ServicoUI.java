package br.com.dbcorp.quadro.ui.visita;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DesignacaoServico;
import br.com.dbcorp.quadro.entidades.Servico;
import br.com.dbcorp.quadro.ui.Params;

public class ServicoUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8298632485665436526L;
	
	private Servico servico;
	private JTextField txCantico;
	private JButton btnAdicionar;
	private JPanel designacoesPanel;
	private int semanaHeight;
	private VisitaUI parent;
	protected List<String> pessoas;
	
	private List<DesignacaoServicoUI> designacaosServicoUI;
	
	public ServicoUI(Servico servico, VisitaUI parent) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("6dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("6dlu"),},
			new RowSpec[] {
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("6dlu"),}));
		
		this.servico = servico == null ? new Servico() : servico;
		this.parent = parent;
		this.pessoas = parent.homens;
		
		TitledBorder title = BorderFactory.createTitledBorder("Reunião de Serviço");
		this.setBorder(title);
		
		JPanel panel = new JPanel();
		add(panel, "3, 3, 5, 1, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(130dlu;default)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Tema:");
		panel.add(lblNewLabel, "4, 1");
		
		JLabel lblNewLabel_1 = new JLabel("Orador:");
		panel.add(lblNewLabel_1, "6, 1");
		
		this.txCantico = new JTextField();
		
		this.btnAdicionar = new JButton(Params.btNovoImg());
		this.btnAdicionar.setToolTipText("Adicionar Nova Designação");
		this.btnAdicionar.addActionListener(this);
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(this.btnAdicionar);
		
		this.designacoesPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) designacoesPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(0);
		
		add(dataPanel, "3, 1, fill, fill");
		add(this.designacoesPanel, "3, 5, 5, 1, fill, fill");
		add(new JLabel("C\u00E2ntico"), "3, 7, right, default");
		add(this.txCantico, "5, 7, fill, default");
		
		this.setCampos();
		
		this.semanaHeight = 155;
		
		this.setMinimumSize(new Dimension(931, 107));
		this.setPreferredSize(new Dimension(1200, 133));
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
			
			this.servico.setCantico(this.txCantico.getText());
		}
		
		return this.servico;
	}
	
	public int getSemanaHeight() {
		return this.semanaHeight;
	}
	
	public void setHeight() {
		if (this.servico.getDesignacoes() != null) {
			int totalLinhas = 37 * (this.servico.getDesignacoes().size() - 1);
			
			this.semanaHeight = 155 + totalLinhas;
			
			this.setPreferredSize(new Dimension(1200, this.semanaHeight));
		}
	}
	
	public void removeDesignacao(DesignacaoServicoUI designacaoServicoUI, DesignacaoServico designacaoServico) {
		this.servico.getDesignacoes().remove(designacaoServico);
		
		this.designacaosServicoUI.remove(designacaoServicoUI);
		
		this.designacoesPanel.remove(designacaoServicoUI);
		
		this.setHeightInterno();
	}
	
	private void setCampos() {
		this.txCantico.setEnabled(true);
		this.btnAdicionar.setEnabled(true);
		
		this.txCantico.setText(this.servico.getCantico());
		
		if (this.servico.getDesignacoes() != null) {
			for (DesignacaoServico designacaoServico : this.servico.getDesignacoes()) {
				adicionaDesignacao(designacaoServico);
			}
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
