package br.com.dbcorp.quadro.ui.livro;

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
import javax.swing.border.TitledBorder;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Estudo;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.gerenciador.EstudoGerenciador;
import br.com.dbcorp.quadro.report.ReportCommon;
import br.com.dbcorp.quadro.ui.DScrollPane;
import br.com.dbcorp.quadro.ui.InternalUI;
import br.com.dbcorp.quadro.ui.Params;

@SuppressWarnings("rawtypes")
public class DesignacoesUI extends InternalUI implements ActionListener, ItemListener {
	private static final long serialVersionUID = 8557021439788640363L;
	
	private EstudoGerenciador gerenciador;
	
	private List<SemanaUI> semanas;
	
	private List<Mes> mesesDesignacoes;
	private List<DiaReuniao> diasReuniao;
	private List<Estudo> estudoMes;
	private List<String> pessoas;
	private Mes mesDesignacao;
	
	private JPanel containerPanel;
	private JComboBox cbMeses;
	private JButton btnSalvar;
	private JButton btnPrint;
	private JButton btnSair;
	private JPanel mesPanel;
	private DScrollPane scroll;
	
	public DesignacoesUI() {
		super();
		
		this.setButtons();
		
		this.gerenciador = new EstudoGerenciador();
		this.pessoas = this.gerenciador.listarNomesPessoas(Genero.M);
		
		TitledBorder title = BorderFactory.createTitledBorder("Estudo de Livro Congregacional");

		this.containerPanel = new JPanel();
		this.containerPanel.setLayout(new BorderLayout(0, 0));
		this.containerPanel.setBorder(title);
		
		JPanel commandPanel = new JPanel(new BorderLayout(0, 0));
		
		JPanel mesPanel = new JPanel(new BorderLayout(0, 0));
		mesPanel.add(new JLabel("M�s: "), BorderLayout.WEST);
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
		}
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
		this.estudoMes = new ArrayList<Estudo>();
		
		this.btnSalvar.setVisible(true);
		
		
		if ( this.scroll == null ) {
			this.mesPanel = new JPanel();
			
			this.scroll = new DScrollPane();
			this.scroll.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, Params.INTERNAL_HEIGHT));
			this.scroll.setHorizontalScrollBarPolicy(DScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			this.scroll.setViewportView(this.mesPanel);
			
			this.containerPanel.add(scroll, BorderLayout.CENTER);
		
		} else {
			this.mesPanel.removeAll();
		}
		
		for (DiaReuniao diaReuniao : this.diasReuniao) {
			Estudo estudo = this.gerenciador.obterEstudoSemana(diaReuniao);
			this.estudoMes.add(estudo);
			
			SemanaUI semana = new SemanaUI(diaReuniao, estudo, this.pessoas);
			
			this.semanas.add(semana);
			this.mesPanel.add(semana);
		}

		this.mesPanel.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, 110 * diasReuniao.size()));
		
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
		List<Estudo> estudos = new ArrayList<Estudo>();
		
		for (SemanaUI semana : this.semanas) {
			estudos.add(semana.obterEstudo());
		}
		
		this.gerenciador.salvarEstudos(estudos);
		
		this.carregarMes();
		
		this.btnPrint.setVisible(true);
	}
	
	private void imprimir() {
		ReportCommon.getInstance().gerarEstudoBiblico(this.estudoMes, this.mesDesignacao);
	}
}