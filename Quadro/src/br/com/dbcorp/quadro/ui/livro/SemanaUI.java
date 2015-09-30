package br.com.dbcorp.quadro.ui.livro;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Estudo;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.ui.DTextField;
import br.com.dbcorp.quadro.ui.ReadOnlyCheckBox;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SemanaUI extends JPanel {
	private static final long serialVersionUID = 8298632485665436526L;
	
	private DiaReuniao diaReuniao;
	private Estudo estudo;
	private JTextField txData;
	private JTextField txDirigente;
	private JTextField txLeitor;
	private ReadOnlyCheckBox chAssembleia;
	private ReadOnlyCheckBox chVisita;
	private List<String> pessoas;
	
	public SemanaUI(DiaReuniao diaReuniao, Estudo estudo, List<String> pessoas) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("6dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("6dlu"),},
			new RowSpec[] {
				RowSpec.decode("6dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("6dlu"),}));
		
		this.estudo = estudo == null ? new Estudo() : estudo;
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
		
		this.txDirigente = new DTextField(this.pessoas);
		this.txLeitor = new DTextField(this.pessoas);
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.chAssembleia = new ReadOnlyCheckBox("Assembl\u00E9ia / Congresso");
		this.chVisita = new ReadOnlyCheckBox("Vis. Superintendente");
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		dataPanel.add(chAssembleia);
		dataPanel.add(chVisita);
		
		add(dataPanel, "4, 2, 4, 1, fill, fill");
		add(new JLabel("Dirigente:"), "3, 4, right, default");
		add(new JLabel("Leitor:"), "5, 6, right, default");
		add(this.txDirigente, "5, 4, 3, 1, fill, default");
		add(this.txLeitor, "7, 6, fill, default");
		
		this.setCampos();
		
		this.setMinimumSize(new Dimension(500, 107));
		this.setPreferredSize(new Dimension(800, 107));
	}
	
	public void reset() {
		this.estudo.setDirigente("");
		this.estudo.setLeitor("");		
	}
	
	public Estudo obterEstudo() {
		this.estudo.setDirigente(this.txDirigente.getText());
		this.estudo.setLeitor(this.txLeitor.getText());
		this.estudo.setDiaReuniao(this.diaReuniao);
		
		return this.estudo;
	}
	
	private void setCampos() {
		if (this.diaReuniao != null) {
			this.txData.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.diaReuniao.getDia()));
			
			if (TipoDia.ASSEMBLEIA == this.diaReuniao.getTipoDia()) {
				this.chAssembleia.setSelected(true);
				this.setCheck();
				this.chVisita.setEnabled(false);
			
			} else if (TipoDia.VISITA == this.diaReuniao.getTipoDia()) {
				this.chVisita.setSelected(true);
				this.setCheck();
				this.chAssembleia.setEnabled(false);
			}
		}
		
		this.txDirigente.setText(this.estudo.getDirigente());
		this.txLeitor.setText(this.estudo.getLeitor());
	}
	
	private void setCheck() {
		if (this.chAssembleia.isSelected() || this.chVisita.isSelected()) {
			this.txDirigente.setText("");
			this.txLeitor.setText("");
			
			this.txDirigente.setEnabled(false);
			this.txLeitor.setEnabled(false);
			
		} else {
			this.txDirigente.setEnabled(true);
			this.txLeitor.setEnabled(true);
		}
	}
}
