package br.com.dbcorp.quadro.ui.escola;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.gerenciador.DesignacaoEscolaGerenciador;
import br.com.dbcorp.quadro.report.ReportCommon;
import br.com.dbcorp.quadro.ui.InternalUI;
import br.com.dbcorp.quadro.ui.Params;

@SuppressWarnings("rawtypes")
public class DesignacoesUI extends InternalUI implements ActionListener, ItemListener {
	private static final long serialVersionUID = 8557021439788640363L;
	
	private DesignacaoEscolaGerenciador gerenciador;
	
	private List<SemanaUI> semanas;
	private List<DesignacaoEscola> designacaoes;
	
	private List<Mes> mesesDesignacoes;
	private List<DiaReuniao> diasReuniao;
	private List<String> homens;
	private List<String> mulheres;
	private Mes mesDesignacao;
	
	private JPanel containerPanel;
	private JComboBox cbMeses;
	private JComboBox cbSala;
	private JButton btnSalvar;
	private JButton btnSair;
	private JButton btImport;
	private JPanel mesPanel;
	private JScrollPane scroll;
	private JButton btnPrint;
	
	@SuppressWarnings("unchecked")
	public DesignacoesUI() {
		super();
		
		this.setButtons();
		
		this.cbSala = new JComboBox(new String[]{"A", "B"});
		this.cbSala.setPreferredSize(new Dimension(50, 25));
		this.cbSala.addItemListener(this);
		
		this.gerenciador = new DesignacaoEscolaGerenciador();
		this.homens = this.gerenciador.listarNomesPessoas(Genero.M);
		this.mulheres = this.gerenciador.listarNomesPessoas(Genero.F);
		
		TitledBorder title = BorderFactory.createTitledBorder("Escola do Ministério Teocrático");

		this.containerPanel = new JPanel();
		this.containerPanel.setLayout(new BorderLayout(0, 0));
		this.containerPanel.setBorder(title);
		
		JPanel commandPanel = new JPanel(new BorderLayout(0, 0));
		
		JPanel salaPanel = new JPanel(new BorderLayout(0, 0));
		salaPanel.add(new JLabel(" Sala: "), BorderLayout.WEST);
		salaPanel.add(this.cbSala, BorderLayout.CENTER);
		
		JPanel mesPanel = new JPanel(new BorderLayout(0, 0));
		mesPanel.add(new JLabel("Mês: "), BorderLayout.WEST);
		mesPanel.add(this.setMeses(), BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		buttonPanel.add(mesPanel);
		buttonPanel.add(salaPanel);
		buttonPanel.add(this.btnSalvar);
		buttonPanel.add(this.btnPrint);
		buttonPanel.add(this.btImport);
		
		JPanel sairPanel = new JPanel();
		sairPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		sairPanel.add(this.btnSair);
		
		commandPanel.add(buttonPanel, BorderLayout.WEST);
		commandPanel.add(sairPanel, BorderLayout.EAST);
		
		this.containerPanel.add(commandPanel, BorderLayout.NORTH);
		
		getContentPane().add(this.containerPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		this.reset();
	}
	
	@Override
	public void reset() {
		this.btnSalvar.setVisible(false);
		this.btnPrint.setVisible(false);

		if (this.mesPanel != null) {
			for (SemanaUI semana : this.semanas) {
				semana.reset();
			}
			
			this.mesPanel.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH - 50, this.mesPanel.getHeight()));
		}
	}
	
	//ActionListener
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnSalvar)) {
			this.salvar();
			
		} else if (event.getSource().equals(this.btnSair)) {
			dispose();
		
		} else if (event.getSource().equals(this.btImport)) {
			this.importar();
		
		} else if (event.getSource().equals(this.btnPrint)) {
			this.imprimir();
		}
	}
	
	//ItemListener
	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == this.cbMeses) {
			if ( !"Selecione...".equals(this.cbMeses.getSelectedItem()) && this.cbMeses.getSelectedItem() != null ) {
				for (Mes mesTemp : this.mesesDesignacoes) {
					if (this.cbMeses.getSelectedItem().equals(mesTemp.getMes().toString()) || 
							this.cbMeses.getSelectedItem().equals(mesTemp.getMes().toString()  + "/" + mesTemp.getAno())) {
						this.mesDesignacao = mesTemp;
						break;
					}
				}
				
				this.diasReuniao = this.gerenciador.obterDiasReuniaoSemanal(this.mesDesignacao); 
				
				this.carregarMes();
				
			} else {
				if (this.scroll != null) {
					this.containerPanel.remove(this.scroll);
					this.scroll = null;
					this.btnSalvar.setVisible(false);
					this.revalidate();
					this.repaint();
				}
			}
		} else if (event.getSource() == this.cbSala) {
			this.carregarMes();
		}
	}
	
	private void setButtons() {
		this.btnSalvar = new JButton(Params.btSalvarImg());
		this.btnSair = new JButton(Params.btFecharImg());
		this.btImport = new JButton(Params.btImportImg());
		this.btnPrint = new JButton(Params.btImprimirImg());
		
		this.btnSalvar.addActionListener(this);
		this.btnSair.addActionListener(this);
		this.btImport.addActionListener(this);
		this.btnPrint.addActionListener(this);
		
		this.btnSalvar.setToolTipText("Salvar");
		this.btnSair.setToolTipText("Sair");
		this.btImport.setToolTipText("Importar Designações");
		this.btnPrint.setToolTipText("Imprimir");
	}
	
	private JComboBox setMeses() {
		this.cbMeses = new JComboBox();
		this.cbMeses.addItemListener(this);
		this.cbMeses.setPreferredSize(new Dimension(120, 25));

		this.mesesDesignacoes = this.gerenciador.obterMeses();
		
		this.comboMeses();
		
		return this.cbMeses;
	}
	
	private void carregarMes() {
		if (this.mesDesignacao != null) {
			this.montaMes();
		}
	}
	
	private void montaMes() {
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		this.semanas = new ArrayList<SemanaUI>();
		this.designacaoes = new ArrayList<DesignacaoEscola>();
		
		this.btnSalvar.setVisible(true);
		
		
		if ( this.scroll == null ) {
			this.mesPanel = new JPanel();
			
			this.scroll = new JScrollPane();
			this.scroll.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, Params.INTERNAL_HEIGHT));
			this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			this.scroll.setViewportView(this.mesPanel);
			
			this.containerPanel.add(scroll, BorderLayout.CENTER);
		
		} else {
			this.mesPanel.removeAll();
		}
		
		String sala = (String) this.cbSala.getSelectedItem();
		
		int semanaHeight = "A".equals(sala) ? 189 : 163;
		
		for (DiaReuniao diaReuniao : this.diasReuniao) {
			SemanaUI semana = null;
			
			List<DesignacaoEscola> designacaoEscola = this.gerenciador.obterDesignacaoesPorSala(diaReuniao, sala);
			this.designacaoes.addAll(designacaoEscola);
			
			if ("A".equals(sala)) {
				semana = new SemanaAUI(designacaoEscola, diaReuniao, this.homens, this.mulheres);
				
			} else {
				semana = new SemanaBUI(this.temasSalaB(designacaoEscola), diaReuniao, this.homens, this.mulheres);
			}
			
			this.semanas.add(semana);
			this.mesPanel.add(semana);
		}

		this.mesPanel.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, semanaHeight * diasReuniao.size()));
		
		this.revalidate();
		this.repaint();
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	@SuppressWarnings("unchecked")
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
	
	private void salvar() {
		List<DesignacaoEscola> designacoes = new ArrayList<DesignacaoEscola>();
		
		for (SemanaUI semana : this.semanas) {
			designacoes.addAll(semana.obterDesignacoes());
		}
		
		this.gerenciador.atualizarDesignacoes(designacoes);
		
		this.carregarMes();
		
		this.btnPrint.setVisible(true);
	}
	
	private List<DesignacaoEscola> temasSalaB(List<DesignacaoEscola> designacaoEscola) {
		for (DesignacaoEscola designacao : designacaoEscola) {
			designacao.setTema(this.gerenciador.tema(designacao));
		}
		
		return designacaoEscola;
	}
	
	private void importar() {
		this.gerenciador.importar(this);
		this.carregarMes();
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void imprimir() {
		ReportCommon.getInstance().gerarEscola(this.designacaoes, this.mesDesignacao, (String) this.cbSala.getSelectedItem());
	}
}