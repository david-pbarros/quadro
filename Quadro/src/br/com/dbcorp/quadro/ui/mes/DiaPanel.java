package br.com.dbcorp.quadro.ui.mes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;

public class DiaPanel extends JPanel implements ActionListener, DocumentListener {
	private static final long serialVersionUID = -8126576081462706449L;
	
	private JCheckBox chAssembleia;
	private JCheckBox chRecaptulacao;
	private JCheckBox chVideos;
	private JCheckBox chVisita;
	private JCheckBox chSemReuniao;
	private DiaReuniao diareuniao;
	private JPanel descPanel;
	private JTextField txDescricao;
	private DiaPanel diaPar;
	
	private List<DiaPanel> diasReuniao;
	
	public DiaPanel(DiaReuniao diareuniao, List<DiaPanel> diasReuniao) {
		this.diareuniao = diareuniao;
		this.diasReuniao = diasReuniao;
		
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("right:max(30dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:default"),
				ColumnSpec.decode("1dlu"),
				ColumnSpec.decode("max(129dlu;default):grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblDia = new JLabel(diareuniao.getDia().getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("pt")) + " - " + diareuniao.getDia().getDayOfMonth() + ":");
		this.chAssembleia = new JCheckBox("Assembl\u00E9ia/Congresso");
		this.chRecaptulacao = new JCheckBox("Recapitula\u00E7\u00E3o");
		this.chVideos = new JCheckBox("Apresentações");
		this.chVisita = new JCheckBox("Visita Superintendente");
		this.chSemReuniao = new JCheckBox("Sem Reuni\u00E3o");
		
		this.chAssembleia.addActionListener(this);
		this.chRecaptulacao.addActionListener(this);
		this.chVideos.addActionListener(this);
		this.chVisita.addActionListener(this);
		this.chSemReuniao.addActionListener(this);
		
		this.txDescricao = new JTextField();
		this.txDescricao.setColumns(20);
		this.txDescricao.getDocument().addDocumentListener(this);
		
		this.txDescricao.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (diaPar != null) {
					diaPar.txDescricao.setText(txDescricao.getText());
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		this.descPanel = new JPanel();
		this.descPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.descPanel.add(new JLabel("-  Descri\u00E7\u00E3o"));
		this.descPanel.add(this.txDescricao);
		this.descPanel.setVisible(false);
		
		add(lblDia, "1, 1");
		add(this.chSemReuniao, "3, 1");
		add(this.chVisita, "5, 1");

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
		
		} else if (TipoDia.VIDEOS == this.diareuniao.getTipoDia()) {
			disableChecks(this.chVideos);
		}
		
		if ("S".equalsIgnoreCase(this.diareuniao.getQuando())) {
			add(this.chRecaptulacao, "7, 1");
			add(this.chVideos, "9, 1");
			add(this.chAssembleia, "11, 1");
			add(this.descPanel, "13, 1, left, fill");
		
		} else {
			add(this.chAssembleia, "9, 1");
			add(this.descPanel, "11, 1, left, fill");
		}

		this.setPreferredSize(new Dimension(1032, 31));
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		TipoDia tipo = null;
		
		this.chRecaptulacao.setEnabled(true);
		this.chVisita.setEnabled(true);
		this.chSemReuniao.setEnabled(true);
		this.chVideos.setEnabled(true);
		this.chAssembleia.setEnabled(true);
		this.descPanel.setVisible(false);
		this.diareuniao.setDescricao(null);
		
		if (this.diaPar == null) {
			if (DayOfWeek.SUNDAY == this.diareuniao.getDia().getDayOfWeek() || DayOfWeek.SATURDAY ==this.diareuniao.getDia().getDayOfWeek()) {
				this.diaPar = this.diasReuniao.stream()
						.filter(p->p.diareuniao.getDia().isBefore(this.diareuniao.getDia()))
						.max(Comparator.comparing(p->p.diareuniao.getDia()))
						.orElse(null);

			} else {
				this.diaPar = this.diasReuniao.stream()
						.filter(p->p.diareuniao.getDia().isAfter(this.diareuniao.getDia()))
						.min(Comparator.comparing(p->p.diareuniao.getDia()))
						.orElse(null);
			}
		}
		
		if (this.diaPar != null) {
			this.diaPar.diaPar = this;
			
			if (event.getSource() == this.chVisita || event.getSource() == this.chAssembleia) {
				this.diaPar.enableByPar();
			}
		}
		
		if (event.getSource() == this.chAssembleia && this.chAssembleia.isSelected()) {
			tipo = TipoDia.ASSEMBLEIA;
			this.descPanel.setVisible(true);
			disableChecks(this.chAssembleia);
			
			if (this.diaPar != null) {
				this.diaPar.disableByPar(1);
			}
		} else if (event.getSource() == this.chRecaptulacao && this.chRecaptulacao.isSelected()) {
			tipo = TipoDia.RECAPITULACAO;
			disableChecks(this.chRecaptulacao);
			
		} else if (event.getSource() == this.chVisita && this.chVisita.isSelected()) {
			tipo = TipoDia.VISITA;
			disableChecks(this.chVisita);
			
			if (this.diaPar != null) {
				this.diaPar.disableByPar(2);
			}
			
		} else if (event.getSource() == this.chSemReuniao && this.chSemReuniao.isSelected()) {
			tipo = TipoDia.SEM_REUNIAO;
			disableChecks(this.chSemReuniao);
		
		} else if (event.getSource() == this.chVideos && this.chVideos.isSelected()) {
			tipo = TipoDia.VIDEOS;
			disableChecks(this.chVideos);
		} 
		
		this.diareuniao.setTipoDia(tipo);
	}
	
	//DocumentListener
	@Override
	public void insertUpdate(DocumentEvent e) {
		this.diareuniao.setDescricao(this.txDescricao.getText());
	}
	
	private void disableByPar(int tipo) {
		this.descPanel.setVisible(tipo == 1);
		disableChecks(tipo == 1 ? this.chAssembleia : this.chVisita);
		
		this.diareuniao.setTipoDia(tipo == 1 ? TipoDia.ASSEMBLEIA : TipoDia.VISITA);
	}
	
	private void enableByPar() {
		this.chRecaptulacao.setEnabled(true);
		this.chVisita.setEnabled(true);
		this.chSemReuniao.setEnabled(true);
		this.chVideos.setEnabled(true);
		this.chAssembleia.setEnabled(true);
		this.descPanel.setVisible(false);
		
		this.chAssembleia.setSelected(false);
		this.chVisita.setSelected(false);
		
		this.diareuniao.setTipoDia(null);
		this.diareuniao.setDescricao(null);
	}
	
	private void disableChecks(JCheckBox chSelecionado) {
		this.chRecaptulacao.setEnabled(false);
		this.chVisita.setEnabled(false);
		this.chSemReuniao.setEnabled(false);
		this.chAssembleia.setEnabled(false);
		this.chVideos.setEnabled(false);
		
		this.chAssembleia.setSelected(false);
		this.chRecaptulacao.setSelected(false);
		this.chVisita.setSelected(false);
		this.chSemReuniao.setSelected(false);
		this.chVideos.setSelected(false);
		
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