package br.com.dbcorp.quadro.ui.discurso;

import java.awt.Dimension;
import java.text.SimpleDateFormat;

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
import br.com.dbcorp.quadro.entidades.Discurso;
import br.com.dbcorp.quadro.ui.ReadOnlyCheckBox;

public class SemanaConvidadoUI extends SemanaUI {
	private static final long serialVersionUID = 8298632485665436526L;
	
	private ReadOnlyCheckBox chVisita;
	
	public SemanaConvidadoUI(DiaReuniao diaReuniao, Discurso discurso) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("6dlu"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;default)"),
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
		
		this.discurso = discurso == null ? new Discurso() : discurso;
		this.diaReuniao = diaReuniao;
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "1, 1, 1, 7, right, default");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		add(separator_1, "11, 1, 1, 7, left, default");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "2, 7, 9, 1, default, bottom");
		
		JSeparator separator_3 = new JSeparator();
		add(separator_3, "2, 1, 9, 1, default, top");
		
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.txTema = new JTextField();
		this.txOrador = new JTextField();
		this.txCongrecagao = new JTextField();
		this.txCidade = new JTextField();
		
		this.chAssembleia = new ReadOnlyCheckBox("Assembl\u00E9ia / Congresso");
		this.chVisita = new ReadOnlyCheckBox("Vis. Superintendente");
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		dataPanel.add(chAssembleia);
		dataPanel.add(chVisita);
		
		add(dataPanel, "4, 2, 6, 1, fill, fill");
		add(new JLabel("Tema:"), "3, 4, right, default");
		add(new JLabel("Orador:"), "7, 4, right, default");
		add(new JLabel("Congreca\u00E7\u00E3o:"), "3, 6, right, default");
		add(new JLabel("Cidade:"), "7, 6, right, default");
		add(this.txTema, "5, 4, fill, default");
		add(this.txOrador, "9, 4, fill, default");
		add(this.txCongrecagao, "5, 6, fill, default");
		add(this.txCidade, "9, 6, fill, default");
		
		this.setCampos();
		
		this.setMinimumSize(new Dimension(500, 107));
		this.setPreferredSize(new Dimension(800, 107));
	}
	
	@Override
	public Discurso obterDiscruso() {
		super.obterDiscruso();
		this.discurso.setCidade(this.txCidade.getText());
		this.discurso.setCongregacao(this.txCongrecagao.getText());
		
		this.discurso.setTipo("C");
		
		return this.discurso;
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
		
		this.txCidade.setText(this.discurso.getCidade());
		this.txCongrecagao.setText(this.discurso.getCongregacao());
		this.txOrador.setText(this.discurso.getOrador());
		this.txTema.setText(this.discurso.getTema());
	}
	
	private void setCheck() {
		if (this.chAssembleia.isSelected() || this.chVisita.isSelected()) {
			this.txTema.setText("");
			this.txCongrecagao.setText("");
			this.txOrador.setText("");
			this.txCidade.setText("");
			
			this.txTema.setEnabled(false);
			this.txCongrecagao.setEnabled(false);
			this.txOrador.setEnabled(false);
			this.txCidade.setEnabled(false);
			
		} else {
			this.txTema.setEnabled(true);
			this.txCongrecagao.setEnabled(true);
			this.txOrador.setEnabled(true);
			this.txCidade.setEnabled(true);
		}
	}
}
