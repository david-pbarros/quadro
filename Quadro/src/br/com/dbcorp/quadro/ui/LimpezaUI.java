package br.com.dbcorp.quadro.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.Log;
import br.com.dbcorp.quadro.entidades.Limpeza;
import br.com.dbcorp.quadro.gerenciador.LimpezaGerenciador;
import br.com.dbcorp.quadro.report.ReportCommon;
import br.com.dbcorp.quadro.ui.dialog.ReuniaoDialog;

public class LimpezaUI extends InternalUI implements ActionListener {
	private static final long serialVersionUID = -5703107302525621155L;
	
	private Log log = Log.getInstance();
	
	private JButton btnNovo;
	private JButton btnSair;
	private JFormattedTextField txDataIni;
	
	private List<Limpeza> limpezas;
	private List<JTextField> semanas;
	private List<JTextField> grupos;
	
	private LimpezaGerenciador gerenciador;
	private JTextField txSemana1;
	private JTextField txGrupo1;
	private JTextField txSemana2;
	private JTextField txSemana3;
	private JTextField txSemana4;
	private JTextField txSemana5;
	private JTextField txSemana6;
	private JTextField txSemana7;
	private JTextField txSemana8;
	private JTextField txSemana9;
	private JTextField txSemana10;
	private JTextField txSemana11;
	private JTextField txSemana12;
	private JTextField txSemana13;
	private JTextField txSemana14;
	private JTextField txSemana15;
	private JTextField txGrupo2;
	private JTextField txGrupo3;
	private JTextField txGrupo4;
	private JTextField txGrupo5;
	private JTextField txGrupo6;
	private JTextField txGrupo7;
	private JTextField txGrupo8;
	private JTextField txGrupo9;
	private JTextField txGrupo10;
	private JTextField txGrupo11;
	private JTextField txGrupo12;
	private JTextField txGrupo13;
	private JTextField txGrupo14;
	private JTextField txGrupo15;
	private JButton btnSalvar;
	private JButton btnPrint;
	
	public LimpezaUI() {
		super();
		
		this.gerenciador = new LimpezaGerenciador();
		
		this.setButtons();
		
		TitledBorder title = BorderFactory.createTitledBorder("Limpeza do Salão do Reino");
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout(0, 0));
		containerPanel.setBorder(title);
		
		JPanel commandPanel = new JPanel(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel diaPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) diaPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		buttonPanel.add(diaPanel);
		
		MaskFormatter mf1 = null;
		
		try {
			mf1 = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			log.error(e);
		}
		
		this.txDataIni = new JFormattedTextField(mf1);
		this.txDataIni.setColumns(6);

		diaPanel.add(new JLabel("Dia Inicial:"));
		diaPanel.add(this.txDataIni);

		buttonPanel.add(this.btnNovo);
		buttonPanel.add(this.btnSalvar);
		buttonPanel.add(this.btnPrint);;
		
		JPanel sairPanel = new JPanel();
		sairPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		sairPanel.add(this.btnSair);
		
		commandPanel.add(buttonPanel, BorderLayout.WEST);
		commandPanel.add(sairPanel, BorderLayout.EAST);
		
		containerPanel.add(commandPanel, BorderLayout.NORTH);

		getContentPane().add(containerPanel, BorderLayout.CENTER);
		
		DScrollPane scrollPane = new DScrollPane();
		containerPanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		this.txSemana1 = new JTextField();
		this.txSemana2 = new JTextField();
		this.txSemana3 = new JTextField();
		this.txSemana4 = new JTextField();
		this.txSemana5 = new JTextField();
		this.txSemana6 = new JTextField();
		this.txSemana7 = new JTextField();
		this.txSemana8 = new JTextField();
		this.txSemana9 = new JTextField();
		this.txSemana10 = new JTextField();
		this.txSemana11 = new JTextField();
		this.txSemana12 = new JTextField();
		this.txSemana13 = new JTextField();
		this.txSemana14 = new JTextField();
		this.txSemana15 = new JTextField();
		this.txGrupo1 = new JTextField();
		this.txGrupo2 = new JTextField();
		this.txGrupo3 = new JTextField();
		this.txGrupo4 = new JTextField();
		this.txGrupo5 = new JTextField();
		this.txGrupo6 = new JTextField();
		this.txGrupo7 = new JTextField();
		this.txGrupo8 = new JTextField();
		this.txGrupo9 = new JTextField();
		this.txGrupo10 = new JTextField();
		this.txGrupo11 = new JTextField();
		this.txGrupo12 = new JTextField();
		this.txGrupo13 = new JTextField();
		this.txGrupo14 = new JTextField();
		this.txGrupo15 = new JTextField();
		
		panel.add(new JLabel("Dias:"), "2, 2, center, default");
		panel.add(new JLabel("Grupos Respons\u00E1veis:"), "4, 2, center, default");
		panel.add(this.txSemana1, "2, 4, fill, default");
		panel.add(this.txSemana2, "2, 6, fill, default");
		panel.add(this.txSemana3, "2, 8, fill, default");
		panel.add(this.txSemana4, "2, 10, fill, default");
		panel.add(this.txSemana5, "2, 12, fill, default");
		panel.add(this.txSemana6, "2, 14, fill, default");
		panel.add(this.txSemana7, "2, 16, fill, default");
		panel.add(this.txSemana8, "2, 18, fill, default");
		panel.add(this.txSemana9, "2, 20, fill, default");
		panel.add(this.txSemana10, "2, 22, fill, default");
		panel.add(this.txSemana11, "2, 24, fill, default");
		panel.add(this.txSemana12, "2, 26, fill, default");
		panel.add(this.txSemana13, "2, 28, fill, default");
		panel.add(this.txSemana14, "2, 30, fill, default");
		panel.add(this.txSemana15, "2, 32, fill, default");
		panel.add(this.txGrupo1, "4, 4, fill, default");
		panel.add(this.txGrupo2, "4, 6, fill, default");
		panel.add(this.txGrupo3, "4, 8, fill, default");
		panel.add(this.txGrupo4, "4, 10, fill, default");
		panel.add(this.txGrupo5, "4, 12, fill, default");
		panel.add(this.txGrupo6, "4, 14, fill, default");
		panel.add(this.txGrupo7, "4, 16, fill, default");
		panel.add(this.txGrupo8, "4, 18, fill, default");
		panel.add(this.txGrupo9, "4, 20, fill, default");
		panel.add(this.txGrupo10, "4, 22, fill, default");
		panel.add(this.txGrupo11, "4, 24, fill, default");
		panel.add(this.txGrupo12, "4, 26, fill, default");
		panel.add(this.txGrupo13, "4, 28, fill, default");
		panel.add(this.txGrupo14, "4, 30, fill, default");
		panel.add(this.txGrupo15, "4, 32, fill, default");
		
		panel.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH - 200, Params.INTERNAL_HEIGHT));
		
		setVisible(true);
		
		this.setListas();

		this.reset();
	}

	@Override
	public void reset() {
		this.limpezas = this.gerenciador.obtemPrograma();
		this.btnPrint.setVisible(false);
		
		if (this.limpezas.size() < 15) {
			for (int seq = this.limpezas.size(); seq < 15; seq++) {
				Limpeza limp = new Limpeza();
				limp.setSeq(seq+1);
				
				this.limpezas.add(limp);
			}
		}
		
		this.setCampos();
	}
	
	//ActionListener
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnNovo)) {
			this.novo();
			
		} else if (event.getSource().equals(this.btnSalvar)) {
			this.salvar();
			
		} else if (event.getSource().equals(this.btnSair)) {
			dispose();
		
		} else if (event.getSource().equals(this.btnPrint)) {
			this.imprimir();
		}
	}
	
	private void setButtons() {
		this.btnNovo = new JButton(Params.btNovoImg());
		this.btnSair = new JButton(Params.btFecharImg());
		this.btnSalvar = new JButton(Params.btSalvarImg());
		this.btnPrint = new JButton(Params.btImprimirImg());
		
		this.btnNovo.addActionListener(this);
		this.btnSair.addActionListener(this);
		this.btnSalvar.addActionListener(this);
		this.btnPrint.addActionListener(this);
		
		this.btnNovo.setToolTipText("Novo");
		this.btnSair.setToolTipText("Sair");
		this.btnSalvar.setToolTipText("Salvar");
		this.btnPrint.setToolTipText("Imprimir");
	}
	
	private void novo() {
		if (this.txDataIni.getText().length() == 10) {
			ReuniaoDialog d = new ReuniaoDialog();
			
			int[] dias = d.exibir();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try {
				Date dtInidtIni = sdf.parse(this.txDataIni.getText());
				
				List<String> datas = this.gerenciador.obtemDatas(dtInidtIni, dias);
				
				for (int i = 0; i <15; i++) {
					this.semanas.get(i).setText(datas.get(i));
				}
			} catch (ParseException e) {
				String erro = "Data inicial invalida.";
				
				log.error(erro, e);
				JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Sem data inicial definida.", "Erro", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void setListas() {
		this.semanas = new ArrayList<JTextField>();
		this.grupos = new ArrayList<JTextField>();
		
		this.semanas.add(this.txSemana1);
		this.semanas.add(this.txSemana2);
		this.semanas.add(this.txSemana3);
		this.semanas.add(this.txSemana4);
		this.semanas.add(this.txSemana5);
		this.semanas.add(this.txSemana6);
		this.semanas.add(this.txSemana7);
		this.semanas.add(this.txSemana8);
		this.semanas.add(this.txSemana9);
		this.semanas.add(this.txSemana10);
		this.semanas.add(this.txSemana11);
		this.semanas.add(this.txSemana12);
		this.semanas.add(this.txSemana13);
		this.semanas.add(this.txSemana14);
		this.semanas.add(this.txSemana15);
		
		
		this.grupos.add(this.txGrupo1);
		this.grupos.add(this.txGrupo2);
		this.grupos.add(this.txGrupo3);
		this.grupos.add(this.txGrupo4);
		this.grupos.add(this.txGrupo5);
		this.grupos.add(this.txGrupo6);
		this.grupos.add(this.txGrupo7);
		this.grupos.add(this.txGrupo8);
		this.grupos.add(this.txGrupo9);
		this.grupos.add(this.txGrupo10);
		this.grupos.add(this.txGrupo11);
		this.grupos.add(this.txGrupo12);
		this.grupos.add(this.txGrupo13);
		this.grupos.add(this.txGrupo14);
		this.grupos.add(this.txGrupo15);
	}
	
	private void setCampos() {
		for (int i = 0; i < 15; i++) {
			this.semanas.get(i).setText(this.limpezas.get(i).getData());
			this.grupos.get(i).setText(this.limpezas.get(i).getGrupo());
		}
	}
	
	private void salvar() {
		for (int i = 0; i < 15; i++) {
			Limpeza l = this.limpezas.get(i);
			
			l.setData(this.semanas.get(i).getText());
			l.setGrupo(this.grupos.get(i).getText());
		}
		
		
		this.gerenciador.salvar(this.limpezas);
		
		this.btnPrint.setVisible(true);
	}
	
	private void imprimir() {
		ReportCommon.getInstance().gerarLimpeza(this.limpezas);
	}
}
