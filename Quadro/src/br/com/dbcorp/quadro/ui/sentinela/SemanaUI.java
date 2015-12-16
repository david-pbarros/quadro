package br.com.dbcorp.quadro.ui.sentinela;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.entidades.Sentinela;
import br.com.dbcorp.quadro.ui.DTextField;
import br.com.dbcorp.quadro.ui.Params;
import br.com.dbcorp.quadro.ui.ReadOnlyCheckBox;

public class SemanaUI extends JPanel {
	private static final long serialVersionUID = 8298632485665436526L;
	
	private DiaReuniao diaReuniao;
	private Sentinela sentinela;
	private JTextField txData;
	private JTextField txPresidente;
	private JTextField txLeitor;
	private ReadOnlyCheckBox chAssembleia;
	private ReadOnlyCheckBox chVisita;
	private List<String> pessoas;
	
	public SemanaUI(DiaReuniao diaReuniao, Sentinela sentinela, List<String> pessoas) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("6dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				RowSpec.decode("6dlu"),}));
		
		this.sentinela = sentinela == null ? new Sentinela() : sentinela;
		this.diaReuniao = diaReuniao;
		this.pessoas = pessoas;
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "1, 1, 1, 7, right, default");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		add(separator_1, "9, 1, 1, 7, left, default");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "2, 7, 7, 1, default, bottom");
		
		JSeparator separator_3 = new JSeparator();
		add(separator_3, "2, 1, 7, 1, default, top");
		
		this.txPresidente = new DTextField(this.pessoas);
		this.txLeitor = new DTextField(this.pessoas);
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.chAssembleia = new ReadOnlyCheckBox("Assembl\u00E9ia / Congresso");
		this.chVisita = new ReadOnlyCheckBox("Vis. Superintendente");
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		dataPanel.add(this.chAssembleia);
		dataPanel.add(this.chVisita);
		
		add(dataPanel, "4, 2, 4, 1, fill, fill");
		add(new JLabel("Presidente:"), "3, 4, right, default");
		add(new JLabel("Leitor:"), "5, 6, right, default");
		add(this.txPresidente, "5, 4, 3, 1, fill, default");
		add(this.txLeitor, "7, 6, fill, default");
		
		this.setCampos();
		
		this.setMinimumSize(new Dimension(500, 107));
		this.setPreferredSize(new Dimension(800, 107));
	}
	
	public void reset() {
		this.sentinela.setPresidente("");
		this.sentinela.setLeitor("");		
	}
	
	public Sentinela obterSentinela() {
		this.sentinela.setPresidente(this.txPresidente.getText());
		this.sentinela.setLeitor(this.txLeitor.getText());
		this.sentinela.setDiaReuniao(this.diaReuniao);
		
		return this.sentinela;
	}
	
	private void setCampos() {
		if (this.diaReuniao != null) {
			this.txData.setText(this.diaReuniao.getDia().format(Params.dateFormate()));
			
			if (TipoDia.ASSEMBLEIA == this.diaReuniao.getTipoDia()) {
				this.chAssembleia.setSelected(true);
				this.setCheck();
			
			} else if (TipoDia.VISITA == this.diaReuniao.getTipoDia()) {
				this.chVisita.setSelected(true);
				this.setCheck();
			}
		}
		
		this.txPresidente.setText(this.sentinela.getPresidente());
		this.txLeitor.setText(this.sentinela.getLeitor());
	}
	
	private void setCheck() {
		if (this.chAssembleia.isSelected() || this.chVisita.isSelected()) {
			this.txPresidente.setText("");
			this.txLeitor.setText("");
			
			this.txPresidente.setEnabled(false);
			this.txLeitor.setEnabled(false);
			
		} else {
			this.txPresidente.setEnabled(true);
			this.txLeitor.setEnabled(true);
		}
	}
}
