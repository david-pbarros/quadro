package br.com.dbcorp.quadro.ui.mes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.ui.Params;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class DiaPanel extends JPanel implements ActionListener, DocumentListener {
	private static final long serialVersionUID = -8126576081462706449L;
	
	private JCheckBox chAssembleia;
	private JCheckBox chRecaptulacao;
	private JCheckBox chVisita;
	private JCheckBox chSemReuniao;
	private DiaReuniao diareuniao;
	private JPanel descPanel;
	private JTextField txDescricao;
	
	public DiaPanel(DiaReuniao diareuniao) {
		this.diareuniao = diareuniao;
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:max(30dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				ColumnSpec.decode("1dlu"),
				ColumnSpec.decode("max(129dlu;default):grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblDia = new JLabel(new SimpleDateFormat("EE - dd").format(diareuniao.getDia()) + ":");
		this.chAssembleia = new JCheckBox("Assembl\u00E9ia/Congresso");
		this.chRecaptulacao = new JCheckBox("Recapitula\u00E7\u00E3o");
		this.chVisita = new JCheckBox("Visita Superintendente");
		this.chSemReuniao = new JCheckBox("Sem Reuni\u00E3o");
		
		this.chAssembleia.addActionListener(this);
		this.chRecaptulacao.addActionListener(this);
		this.chVisita.addActionListener(this);
		this.chSemReuniao.addActionListener(this);
		
		this.txDescricao = new JTextField();
		this.txDescricao.setColumns(20);
		this.txDescricao.getDocument().addDocumentListener(this);

		this.descPanel = new JPanel();
		this.descPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.descPanel.add(new JLabel("-  Descri\u00E7\u00E3o"));
		this.descPanel.add(this.txDescricao);
		this.descPanel.setVisible(false);
		
		add(lblDia, "1, 1");
		add(this.chSemReuniao, "3, 1");
		add(this.chVisita, "5, 1");
		add(this.chAssembleia, "9, 1");
		add(this.descPanel, "11, 1, left, fill");

		if (TipoDia.ASSEMBLEIA == this.diareuniao.getTipoDia()) {
			disableChecks(this.chAssembleia);
			this.descPanel.setVisible(true);
			this.txDescricao.setText(this.diareuniao.getDescricao());
		
		} else if (TipoDia.RECAPITULACAO == this.diareuniao.getTipoDia()) {
			disableChecks(this.chRecaptulacao);
		
		} else if (TipoDia.VISITA == this.diareuniao.getTipoDia()) {
			disableChecks(this.chVisita);
			
		} else if (TipoDia.SEM_REUNIAO == this.diareuniao.getTipoDia()) {
			disableChecks(this.chSemReuniao);
		}
		
		if ("S".equalsIgnoreCase(this.diareuniao.getQuando())) {
			add(this.chRecaptulacao, "7, 1");
		}

		this.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, 31));
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		TipoDia tipo = null;
		
		this.chRecaptulacao.setEnabled(true);
		this.chVisita.setEnabled(true);
		this.chSemReuniao.setEnabled(true);
		this.chAssembleia.setEnabled(true);
		
		if (event.getSource() == this.chAssembleia && this.chAssembleia.isSelected()) {
			tipo = TipoDia.ASSEMBLEIA;
			this.descPanel.setVisible(true);
			disableChecks(this.chAssembleia);
			
		} else if (event.getSource() == this.chRecaptulacao && this.chRecaptulacao.isSelected()) {
			tipo = TipoDia.RECAPITULACAO;
			disableChecks(this.chRecaptulacao);
			
		} else if (event.getSource() == this.chVisita && this.chVisita.isSelected()) {
			tipo = TipoDia.VISITA;
			disableChecks(this.chVisita);
			
		} else if (event.getSource() == this.chSemReuniao && this.chSemReuniao.isSelected()) {
			tipo = TipoDia.SEM_REUNIAO;
			disableChecks(this.chSemReuniao);
		}
		
		this.diareuniao.setTipoDia(tipo);
	}
	
	//DocumentListener
	@Override
	public void insertUpdate(DocumentEvent e) {
		this.diareuniao.setDescricao(this.txDescricao.getText());
	}
	
	private void disableChecks(JCheckBox chSelecionado) {
		this.chRecaptulacao.setEnabled(false);
		this.chVisita.setEnabled(false);
		this.chSemReuniao.setEnabled(false);
		this.chAssembleia.setEnabled(false);
		
		this.chAssembleia.setSelected(false);
		this.chRecaptulacao.setSelected(false);
		this.chVisita.setSelected(false);
		this.chSemReuniao.setSelected(false);
		
		chSelecionado.setSelected(true);
		chSelecionado.setEnabled(true);
		chSelecionado.setSelected(true);
	}

	//Document Listener - Not used
	@Override
	public void removeUpdate(DocumentEvent e) {}
	@Override
	public void changedUpdate(DocumentEvent event) {}
}