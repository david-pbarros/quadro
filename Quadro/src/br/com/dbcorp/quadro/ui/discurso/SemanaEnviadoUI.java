package br.com.dbcorp.quadro.ui.discurso;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Discurso;
import br.com.dbcorp.quadro.ui.DTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SemanaEnviadoUI extends SemanaUI {
	private static final long serialVersionUID = 8298632485665436526L;
	
	private List<String> pessoas;
	
	public SemanaEnviadoUI(DiaReuniao diaReuniao, Discurso discurso, List<String> pessoas) {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("6dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("6dlu"),},
			new RowSpec[] {
				RowSpec.decode("6dlu"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("6dlu"),}));
		
		this.discurso = discurso == null ? new Discurso() : discurso;
		this.diaReuniao = diaReuniao;
		this.pessoas = pessoas;
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator, "1, 1, 1, 5, right, default");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		add(separator_1, "11, 1, 1, 5, left, default");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "2, 5, 9, 1, default, bottom");
		
		JSeparator separator_3 = new JSeparator();
		add(separator_3, "2, 1, 9, 1, default, top");
		
		this.txData = new JTextField();
		this.txData.setEnabled(false);
		this.txData.setColumns(10);
		
		this.txTema = new JTextField();
		this.txOrador = new DTextField(this.pessoas);
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(new JLabel("Dia:"));
		dataPanel.add(this.txData);
		
		add(dataPanel, "4, 2, 6, 1, fill, fill");
		add(new JLabel("Tema:"), "3, 4, right, default");
		add(new JLabel("Orador:"), "7, 4, right, default");
		add(this.txTema, "5, 4, fill, default");
		add(this.txOrador, "9, 4, fill, default");
		
		this.setCampos();
		
		this.setMinimumSize(new Dimension(500, 83));
		this.setPreferredSize(new Dimension(800, 83));
	}
	
	@Override
	public Discurso obterDiscruso() {
		super.obterDiscruso();
		this.discurso.setTipo("E");
		
		return this.discurso;
	}
	
	private void setCampos() {
		if (this.diaReuniao != null) {
			this.txData.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.diaReuniao.getDia()));
		}
		
		this.txOrador.setText(this.discurso.getOrador());
		this.txTema.setText(this.discurso.getTema());
	}
}
