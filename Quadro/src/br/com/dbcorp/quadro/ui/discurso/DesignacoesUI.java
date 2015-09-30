package br.com.dbcorp.quadro.ui.discurso;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Discurso;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.gerenciador.DiscursoGerenciador;
import br.com.dbcorp.quadro.report.ReportCommon;
import br.com.dbcorp.quadro.ui.InternalUI;
import br.com.dbcorp.quadro.ui.Params;

@SuppressWarnings("rawtypes")
public class DesignacoesUI extends InternalUI implements ActionListener, ItemListener {
	private static final long serialVersionUID = 8557021439788640363L;
	
	private DiscursoGerenciador gerenciador;
	
	private List<SemanaUI> semanas;
	
	private List<Mes> mesesDesignacoes;
	private List<DiaReuniao> diasReuniao;
	private List<String> pessoas;
	private Mes mesDesignacao;
	
	private JPanel containerPanel;
	private JComboBox cbMeses;
	private JComboBox cbTipo;
	private JButton btnSalvar;
	private JButton btnNova;
	private JButton btnSair;
	private JPanel mesPanel;
	private JScrollPane scroll;
	private JButton btnPrint;
	
	@SuppressWarnings("unchecked")
	public DesignacoesUI() {
		super();
		
		this.setButtons();
		
		this.gerenciador = new DiscursoGerenciador();
		this.pessoas = this.gerenciador.listarNomesPessoas(Genero.M);
		
		TitledBorder title = BorderFactory.createTitledBorder("Discurso Publico");

		this.containerPanel = new JPanel();
		this.containerPanel.setLayout(new BorderLayout(0, 0));
		this.containerPanel.setBorder(title);
		
		JPanel commandPanel = new JPanel(new BorderLayout(0, 0));
		
		JPanel mesPanel = new JPanel(new BorderLayout(0, 0));
		mesPanel.add(new JLabel("Mês: "), BorderLayout.WEST);
		mesPanel.add(this.setMeses(), BorderLayout.CENTER);
		
		this.cbTipo = new JComboBox(new String[]{"Convidados", "Enviados"});
		this.cbTipo.addItemListener(this);
		
		JPanel tipoPanel = new JPanel();
		tipoPanel.add(new JLabel("Tipo:"));
		tipoPanel.add(this.cbTipo);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		buttonPanel.add(mesPanel);
		buttonPanel.add(tipoPanel);
		buttonPanel.add(this.btnNova);
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
		this.btnNova.setVisible(false);
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
			
		} else if (event.getSource().equals(this.btnNova)) {
			this.novaSemanaEnvio();
		}
	}
	
	//ItemListener
	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == this.cbMeses || event.getSource() == this.cbTipo) {
			if ( !"Selecione...".equals(this.cbMeses.getSelectedItem()) && this.cbMeses.getSelectedItem() != null ) {
				for (Mes mesTemp : this.mesesDesignacoes) {
					if (this.cbMeses.getSelectedItem().equals(mesTemp.getMes().toString()) || 
							this.cbMeses.getSelectedItem().equals(mesTemp.getMes().toString()  + "/" + mesTemp.getAno())) {
						this.mesDesignacao = mesTemp;
						break;
					}
				}
				
				if (this.cbTipo.getSelectedIndex() == 0) {
					this.diasReuniao = this.gerenciador.obterDiasReuniaoFind(this.mesDesignacao);
					this.btnNova.setVisible(false);
					
				} else {
					this.diasReuniao = this.gerenciador.obterDiasReuniaoEnviados(this.mesDesignacao); 
					this.btnNova.setVisible(true);
				}
				
				this.carregarMes();
				
			} else {
				if (this.scroll != null) {
					this.containerPanel.remove(this.scroll);
					this.scroll = null;
					this.btnSalvar.setVisible(false);
					this.btnNova.setVisible(false);
					this.revalidate();
					this.repaint();
				}
			}
		}
	}
	
	private void setButtons() {
		this.btnSalvar = new JButton(Params.btSalvarImg());
		this.btnSair = new JButton(Params.btFecharImg());
		this.btnPrint = new JButton(Params.btImprimirImg());
		this.btnNova = new JButton(Params.btNovoImg());
		
		this.btnSalvar.addActionListener(this);
		this.btnSair.addActionListener(this);
		this.btnPrint.addActionListener(this);
		this.btnNova.addActionListener(this);
		
		this.btnSalvar.setToolTipText("Salvar");
		this.btnSair.setToolTipText("Sair");
		this.btnPrint.setToolTipText("Imprimir");
		this.btnNova.setToolTipText("Nova semana");
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
		
		int height = 0;
		
		for (DiaReuniao diaReuniao : this.diasReuniao) {
			SemanaUI semana = null;
			
			if (this.cbTipo.getSelectedIndex() == 0) {
				semana = new SemanaConvidadoUI(diaReuniao, this.gerenciador.obterDiscursoSemana(diaReuniao, "C"));
				height = 110;
				
			} else if (this.cbTipo.getSelectedIndex() == 1) {
				semana = new SemanaEnviadoUI(diaReuniao, this.gerenciador.obterDiscursoSemana(diaReuniao, "E"), this.pessoas);
				height = 86;
			}
			
			this.semanas.add(semana);
			this.mesPanel.add(semana);
		}
		
		this.mesPanel.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, height * diasReuniao.size()));
		
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
		List<Discurso> discursos = new ArrayList<Discurso>();
		
		for (SemanaUI semana : this.semanas) {
			discursos.add(semana.obterDiscruso());
		}
		
		this.gerenciador.salvarDiscurso(discursos);
		
		this.carregarMes();
		
		this.btnPrint.setVisible(true);
	}
	
	private void imprimir() {
		List<Discurso> discursos = new ArrayList<Discurso>();
		
		List<DiaReuniao> diasConvidados = this.gerenciador.obterDiasReuniaoFind(this.mesDesignacao);
		for (DiaReuniao diaReuniao : diasConvidados) {
			discursos.add(this.gerenciador.obterDiscursoSemana(diaReuniao, "C"));
		}
		
		List<DiaReuniao> diasEnviados = this.gerenciador.obterDiasReuniaoEnviados(this.mesDesignacao);
		for (DiaReuniao diaReuniao : diasEnviados) {
			Discurso discurso = this.gerenciador.obterDiscursoSemana(diaReuniao, "E");
			
			if (discurso != null && !"".equals(discurso.getTema().trim()) && !"".equals(discurso.getOrador().trim())) {
				discursos.add(discurso);
			}
		}
		
		ReportCommon.getInstance().gerarDiscursos(discursos, this.mesDesignacao);
	}
	
	private void novaSemanaEnvio() {
		String dia = JOptionPane.showInputDialog(null, "Informe o dia da designação:", "Envio de Orador", JOptionPane.QUESTION_MESSAGE);
		
		if (dia != null && !"".equals(dia.trim())) {
			try {
				int diaNumber = Integer.parseInt(dia);
				
				if (diaNumber > 0 && diaNumber < 31) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					DiaReuniao diaEnvio = new DiaReuniao();
					diaEnvio.setTipoDia(TipoDia.ESPECIAL_ENVIADOS);
					diaEnvio.setQuando("F");
					diaEnvio.setDia(sdf.parse(dia + "/" + this.mesDesignacao.getMes().getNumero() + "/" + this.mesDesignacao.getAno()));
					
					this.mesDesignacao.getDias().add(diaEnvio);
					
					this.gerenciador.salvarMesDesignacao(this.mesDesignacao);
					
					this.salvar();
					
					this.diasReuniao = this.gerenciador.obterDiasReuniaoEnviados(this.mesDesignacao); 
					this.carregarMes();
					
				} else {
					JOptionPane.showMessageDialog(this, "Não é um dia válido!");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Valor informado não é numerico!");
			}
		}
	}
}