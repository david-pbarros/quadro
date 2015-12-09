package br.com.dbcorp.quadro.ui.visita;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.entidades.SemanaVisita;
import br.com.dbcorp.quadro.exceptions.DiaVisitaException;
import br.com.dbcorp.quadro.gerenciador.VisitaGerenciador;
import br.com.dbcorp.quadro.report.ReportCommon;
import br.com.dbcorp.quadro.ui.DScrollPane;
import br.com.dbcorp.quadro.ui.InternalUI;
import br.com.dbcorp.quadro.ui.Params;

public class VisitaUI extends InternalUI implements ActionListener, ItemListener {
	private static final long serialVersionUID = 592733447979297980L;

	private JPanel containerPanel;
	private VisitaGerenciador gerenciador;
	private List<Mes> mesesDesignacoes;
	private SemanaVisita visita;
	private Mes mesSelecionado;
	private List<String> mulheres;
	protected List<String> homens;

	private EscolaUI escolaUI;
	private ServicoUI servicoUI;
	private DiscursoUI discursoServicoUI;
	private DiscursoUI discursoFinalUI;
	private ReuniaoPublicaUI reuniaoPublicaUI;
	
	private JComboBox<String> cbMeses;
	private JButton btnSalvar;
	private JButton btnPrint;
	private JButton btnSair;
	private JPanel mesPanel;
	private DScrollPane scroll;
	
	public VisitaUI() {
		super();
		
		this.setButtons();
		
		this.gerenciador = new VisitaGerenciador();
		
		this.homens = this.gerenciador.listarNomesPessoas(Genero.M);
		this.mulheres = this.gerenciador.listarNomesPessoas(Genero.F);
		
		TitledBorder title = BorderFactory.createTitledBorder("Visita Superintendente");
		
		this.containerPanel = new JPanel();
		this.containerPanel.setLayout(new BorderLayout(0, 0));
		this.containerPanel.setBorder(title);
		
		JPanel commandPanel = new JPanel(new BorderLayout(0, 0));
		
		JPanel mesPanel = new JPanel(new BorderLayout(0, 0));
		mesPanel.add(new JLabel("Mês: "), BorderLayout.WEST);
		mesPanel.add(this.setMeses(), BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		buttonPanel.add(mesPanel);
		buttonPanel.add(this.btnSalvar);
		buttonPanel.add(this.btnPrint);
		
		JPanel sairPanel = new JPanel();
		sairPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		sairPanel.add(this.btnSair);
		
		commandPanel.add(buttonPanel, BorderLayout.WEST);
		commandPanel.add(sairPanel, BorderLayout.EAST);
		
		this.containerPanel.add(commandPanel, BorderLayout.NORTH);

		
		getContentPane().add(this.containerPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}

	@Override
	public void reset() {
		this.btnSalvar.setVisible(false);
		this.btnPrint.setVisible(false);

		if (this.mesPanel != null) {
			this.visita = new SemanaVisita();
			this.visita.setMes(this.mesSelecionado);
			
			this.mesPanel.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH - 50, this.mesPanel.getHeight()));
		}
	}
	
	//ItemListener
	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == this.cbMeses) {
			if ( !"Selecione...".equals(this.cbMeses.getSelectedItem()) && this.cbMeses.getSelectedItem() != null ) {
				for (Mes mesTemp : this.mesesDesignacoes) {
					if (this.cbMeses.getSelectedItem().equals(mesTemp.getMes().toString()) || 
							this.cbMeses.getSelectedItem().equals(mesTemp.getMes().toString()  + "/" + mesTemp.getAno())) {
						this.mesSelecionado = mesTemp;
						break;
					}
				}
				
				try {
					this.visita = this.gerenciador.obterVisita(this.mesSelecionado);
					this.montaVisita();
				
				} catch (DiaVisitaException e) {
					JOptionPane.showMessageDialog(this, "Dias da visita não localizado. Verifique a criação do próximo mes.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if (this.scroll != null) {
					this.containerPanel.remove(this.scroll);
					this.scroll = null;
					this.btnSalvar.setVisible(false);
					this.revalidate();
					this.repaint();
				}
			}
		}
		
	}

	//ActionListener
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnSalvar)) {
			this.salvar();
			
		} else if (event.getSource().equals(this.btnSair)) {
			dispose();
		
		} else if (event.getSource().equals(this.btnPrint)) {
			this.imprimir();
		}
	}
	
	public void setHeight() {
		int escolaHeight = 145;
		int discursoHeight = 85;
		int servicoHeight = this.servicoUI.getSemanaHeight() + 20;
		int reuniaoPulicaHeight = 190;
		
		this.mesPanel.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, escolaHeight + servicoHeight + discursoHeight + reuniaoPulicaHeight + discursoHeight));
		
		this.revalidate();
		this.repaint();
	}

	private void setButtons() {
		this.btnSalvar = new JButton(Params.btSalvarImg());
		this.btnPrint = new JButton(Params.btImprimirImg());
		this.btnSair = new JButton(Params.btFecharImg());
		
		this.btnSalvar.addActionListener(this);
		this.btnPrint.addActionListener(this);
		this.btnSair.addActionListener(this);
		
		this.btnSalvar.setToolTipText("Salvar");
		this.btnPrint.setToolTipText("Imprimir");
		this.btnSair.setToolTipText("Sair");
	}
	
	@SuppressWarnings("rawtypes")
	private JComboBox setMeses() {
		this.cbMeses = new JComboBox<String>();
		this.cbMeses.addItemListener(this);
		this.cbMeses.setPreferredSize(new Dimension(120, 25));

		this.mesesDesignacoes = this.gerenciador.obterMesesVisita();
		
		this.comboMeses();
		
		return this.cbMeses;
	}
	
	private void comboMeses() {
		this.cbMeses.addItem("Selecione...");
		
		for (Mes mes : this.mesesDesignacoes) {
			String temp = mes.getMes().toString();
			
			if (mes.getAno() != Calendar.getInstance().get(Calendar.YEAR)) {
				temp += "/" + mes.getAno();
			}
			
			this.cbMeses.addItem(temp);
		}
	}
	
	private void montaVisita() {
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		this.btnSalvar.setVisible(true);
		
		if ( this.scroll == null ) {
			this.mesPanel = new JPanel();

			this.scroll = new DScrollPane();
			this.scroll.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, Params.INTERNAL_HEIGHT-300));
			this.scroll.setHorizontalScrollBarPolicy(DScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			this.scroll.setViewportView(this.mesPanel);
			
			this.containerPanel.add(this.scroll, BorderLayout.CENTER);
			
		} else {
			this.mesPanel.removeAll();
		}
		
		if (this.visita.getDesignacoesEscola() == null) {
			this.visita.setDesignacoesEscola(new ArrayList<DesignacaoEscola>());
		}
		
		this.escolaUI = new EscolaUI(this.visita.getDesignacoesEscola(), this.homens, this.mulheres);
		this.servicoUI = new ServicoUI(this.visita.getServico(), this);
		this.discursoServicoUI = new DiscursoUI(this.visita, 1);
		this.reuniaoPublicaUI = new ReuniaoPublicaUI(this.visita);
		this.discursoFinalUI = new DiscursoUI(this.visita, 3);
		
		this.servicoUI.setHeight();
		
		this.mesPanel.add(this.escolaUI);
		this.mesPanel.add(this.servicoUI);
		this.mesPanel.add(this.discursoServicoUI);
		this.mesPanel.add(this.reuniaoPublicaUI);
		this.mesPanel.add(this.discursoFinalUI);
		
		this.setHeight();
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void salvar() {
		this.visita.setDesignacoesEscola(this.escolaUI.obterDesignacoes());
		this.visita.setServico(this.servicoUI.obterServico());
		this.discursoServicoUI.obterDiscurso();
		this.reuniaoPublicaUI.obterReuniaoPublica();
		this.discursoFinalUI.obterDiscurso();
		
		this.gerenciador.salvaVisita(this.visita);
		
		this.btnPrint.setVisible(true);
	}
	
	private void imprimir() {
		ReportCommon.getInstance().gerarVisita(this.visita);
	}
}
