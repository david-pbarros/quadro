package br.com.dbcorp.quadro.ui.equipeServico;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
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
import br.com.dbcorp.quadro.entidades.EquipeServico;
import br.com.dbcorp.quadro.ui.DTextField;
import br.com.dbcorp.quadro.ui.ReadOnlyCheckBox;

public class SemanaUI extends JPanel {
	private static final long serialVersionUID = 8298632485665436526L;
	
	private DiaReuniao diaReuniao;
	private EquipeServico equipeServico;
	private JTextField txData;
	private ReadOnlyCheckBox chAssembleia;
	private JTextField txind1;
	private JTextField txind2;
	private JTextField txind3;
	private JTextField txVolante1;
	private JTextField txVolante2;
	private List<String> pessoas;
	
	public SemanaUI(DiaReuniao diaReuniao, EquipeServico equipeServico, List<String> pessoas) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("6dlu"),
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
		
		this.equipeServico = equipeServico == null ? new EquipeServico() : equipeServico;
		this.diaReuniao = diaReuniao;
		this.pessoas = pessoas;
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "1, 1, 1, 7, right, default");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		add(separator_1, "5, 1, 1, 7, left, default");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "2, 7, 3, 1, default, bottom");
		
		JSeparator separator_3 = new JSeparator();
		add(separator_3, "2, 1, 3, 1, default, top");
		
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.chAssembleia = new ReadOnlyCheckBox("Assembl\u00E9ia / Congresso");
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		dataPanel.add(chAssembleia);
		
		JPanel indPanel = new JPanel();
		indPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.txind1 = new DTextField(this.pessoas);
		this.txind2 = new DTextField(this.pessoas);
		this.txind3 = new DTextField(this.pessoas);

		indPanel.add(new JLabel("Indicadores:"), "1, 1");
		indPanel.add(this.txind1, "1, 3, fill, default");
		indPanel.add(this.txind2, "1, 5, fill, default");
		indPanel.add(this.txind3, "1, 7, fill, default");
		
		JPanel volanPanel = new JPanel();
		volanPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.txVolante1 = new DTextField(this.pessoas);
		this.txVolante2 = new DTextField(this.pessoas);

		volanPanel.add(new JLabel("Volantes:"), "1, 1");
		volanPanel.add(this.txVolante1, "1, 3, fill, default");
		volanPanel.add(this.txVolante2, "1, 5, fill, default");
		
		add(dataPanel, "2, 2, 2, 1, fill, fill");
		add(indPanel, "3, 4, fill, fill");
		add(volanPanel, "3, 6, fill, fill");
		
		this.setCampos();
		
		this.setMinimumSize(new Dimension(500, 236));
		this.setPreferredSize(new Dimension(800, 236));
	}
	
	public void reset() {
		this.equipeServico.setIndcador1("");
		this.equipeServico.setIndcador2("");
		this.equipeServico.setIndcador3("");
		this.equipeServico.setVolante1("");
		this.equipeServico.setVolante2("");
	}
	
	public EquipeServico obterEquipeServico() {
		this.equipeServico.setIndcador1(this.txind1.getText());
		this.equipeServico.setIndcador2(this.txind2.getText());
		this.equipeServico.setIndcador3(this.txind3.getText());
		this.equipeServico.setVolante1(this.txVolante1.getText());
		this.equipeServico.setVolante2(this.txVolante2.getText());
		this.equipeServico.setDiaReuniao(this.diaReuniao);
		
		return this.equipeServico;
	}
	
	private void setCampos() {
		if (this.diaReuniao != null) {
			this.txData.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.diaReuniao.getDia()));
			
			if (TipoDia.ASSEMBLEIA == this.diaReuniao.getTipoDia()) {
				this.chAssembleia.setSelected(true);
				this.setCheck();
			}
			
			this.txind1.setText(this.equipeServico.getIndcador1());
			this.txind2.setText(this.equipeServico.getIndcador2());
			this.txind3.setText(this.equipeServico.getIndcador3());
			this.txVolante1.setText(this.equipeServico.getVolante1());
			this.txVolante2.setText(this.equipeServico.getVolante2());
		}
	}
	
	private void setCheck() {
		if (this.chAssembleia.isSelected()) {
			this.txind1.setText("");
			this.txind2.setText("");
			this.txind3.setText("");
			this.txVolante1.setText("");
			this.txVolante2.setText("");
			
			this.txind1.setEnabled(false);
			this.txind2.setEnabled(false);
			this.txind3.setEnabled(false);
			this.txVolante1.setEnabled(false);
			this.txVolante2.setEnabled(false);
			
		} else {
			this.txind1.setEnabled(true);
			this.txind2.setEnabled(true);
			this.txind3.setEnabled(true);
			this.txVolante1.setEnabled(true);
			this.txVolante2.setEnabled(true);
		}
	}
}
