package br.com.dbcorp.quadro.ui.mes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;

import br.com.dbcorp.quadro.Log;
import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DesignacaoServico;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.entidades.Discurso;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.entidades.SemanaVisita;
import br.com.dbcorp.quadro.entidades.Servico;
import br.com.dbcorp.quadro.gerenciador.MesGerenciador;
import br.com.dbcorp.quadro.ui.DScrollPane;
import br.com.dbcorp.quadro.ui.InternalUI;
import br.com.dbcorp.quadro.ui.Params;
import br.com.dbcorp.quadro.ui.dialog.NovoDiaDialog;
import br.com.dbcorp.quadro.ui.dialog.ReuniaoDialog;
import br.com.dbcorp.quadro.ui.model.MesTableModel;

public class MesesUI extends InternalUI implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = -1753463790399252181L;

	private Log log = Log.getInstance();
	
	private JTable table;
	private JPanel diasPanel;
	private DScrollPane scrollDias;
	private JButton btnNovo;
	private JButton btnRemove;
	private JButton btnSair;
	private JButton btnSalvar;
	private JButton btnExport;
	private JButton btnAddDia;
	private MesGerenciador gerenciador;
	private Mes mesSelecionado;
	
	private SimpleDateFormat sdf;
	private SimpleDateFormat diaSemana;
	
	private String doisTab = "\t\t";
	private String tresTab = "\t\t\t";
	
	/**
	 * Create the frame.
	 */
	public MesesUI() {
		super();
		
		this.gerenciador = new MesGerenciador(); 
		
		this.setButtons();
		
		TitledBorder title = BorderFactory.createTitledBorder("Meses");

		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout(0, 0));
		containerPanel.setBorder(title);
		
		JPanel commandPanel = new JPanel(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		buttonPanel.add(this.btnNovo);
		buttonPanel.add(this.btnAddDia);
		buttonPanel.add(this.btnSalvar);
		buttonPanel.add(this.btnExport);
		buttonPanel.add(this.btnRemove);
		
		JPanel sairPanel = new JPanel();
		sairPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		sairPanel.add(this.btnSair);
		
		commandPanel.add(buttonPanel, BorderLayout.WEST);
		commandPanel.add(sairPanel, BorderLayout.EAST);
		
		this.diasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
		
		JPanel diasContainer = new JPanel(new BorderLayout(30, 0));
		diasContainer.add(new JLabel("    Dias de Reunião:"), BorderLayout.NORTH);
		diasContainer.add(this.diasPanel, BorderLayout.CENTER);
		
		this.scrollDias = new DScrollPane();
		this.scrollDias.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, 300));
		this.scrollDias.setHorizontalScrollBarPolicy(DScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollDias.setViewportView(diasContainer);
		
		containerPanel.add(commandPanel, BorderLayout.NORTH);
		containerPanel.add(this.setTable(), BorderLayout.CENTER);
		containerPanel.add(this.scrollDias, BorderLayout.SOUTH);
		
		getContentPane().add(containerPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		this.reset();
	}

	//ActionListener
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnNovo)) {
			this.openDialogNovo();
			
		} else if (event.getSource().equals(this.btnRemove)) {
			int response = JOptionPane.showConfirmDialog(null, "Confirma Remoção?", "Remover?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.YES_OPTION) {
		    	this.removerBanco();
		    } 
		} else if (event.getSource().equals(this.btnSair)) {
			dispose();
		
		} else if (event.getSource().equals(this.btnSalvar)) {
			this.salvar();
		
		} else if (event.getSource().equals(this.btnExport)) {
			this.exportar();
		
		} else if (event.getSource().equals(this.btnAddDia)) {
			this.adicionarDia();
		}
	}
	
	//ListSelectionListener
	public void valueChanged(ListSelectionEvent event) {
		List<Mes> meses = ((MesTableModel) this.table.getModel()).getMeses();
		
		if (this.table.getSelectedRow() > -1 && meses.size() > this.table.getSelectedRow()) {
			this.mesSelecionado = meses.get(this.table.getSelectedRow());
			
			Collections.sort(this.mesSelecionado.getDias());
			
			this.btnRemove.setVisible(true);
			this.btnSalvar.setVisible(true);
			this.btnExport.setVisible(true);
			this.btnAddDia.setVisible(true);
			
			this.scrollDias.setVisible(true);
			
			this.setDias();
		}
	}
	
	public void reset() {
		if (this.table.getModel() instanceof MesTableModel) {
			MesTableModel model = (MesTableModel) this.table.getModel();
			model.setItens(this.gerenciador.listarMeses());
			model.fireTableDataChanged();
		
		} else {
			MesTableModel model = new MesTableModel(this.gerenciador.listarMeses());
			this.table.setModel(model);
		} 
		
		this.mesSelecionado = null;
		
		this.btnRemove.setVisible(false);
		this.btnSalvar.setVisible(false);
		this.btnExport.setVisible(false);
		this.scrollDias.setVisible(false);
		this.btnAddDia.setVisible(false);
		
		this.setColumnSize();
	}
	
	public void setDias() {
		this.diasPanel.removeAll();
		
		this.diasPanel.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, 38 * this.mesSelecionado.getDias().size()));
		
		for (DiaReuniao diaReuniao : this.mesSelecionado.getDias()) {
			this.diasPanel.add(new DiaPanel(diaReuniao));
		}
		
		this.repaint();
		this.validate();
	}
	
	private DScrollPane setTable() {
		this.table = new JTable();
		this.table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
		this.table.getSelectionModel().addListSelectionListener(this);
		
		DScrollPane scrollPane = new DScrollPane(this.table);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, 50));
		
		return scrollPane;
	}
	
	private void setButtons() {
		this.btnNovo = new JButton(Params.btNovoImg());
		this.btnRemove = new JButton(Params.btRemoverImg());
		this.btnSair = new JButton(Params.btFecharImg());
		this.btnSalvar = new JButton(Params.btSalvarImg());
		this.btnExport = new JButton(Params.btExportImg());
		this.btnAddDia = new JButton(Params.btAddImg());
		
		this.btnNovo.addActionListener(this);
		this.btnRemove.addActionListener(this);
		this.btnSair.addActionListener(this);
		this.btnSalvar.addActionListener(this);
		this.btnExport.addActionListener(this);
		this.btnAddDia.addActionListener(this);
		
		this.btnNovo.setToolTipText("Novo");
		this.btnRemove.setToolTipText("Remover");
		this.btnSair.setToolTipText("Sair");
		this.btnSalvar.setToolTipText("Salvar");
		this.btnExport.setToolTipText("Exportar");
		this.btnAddDia.setToolTipText("Adiciona Novo Dia");
	}
	
	private void setColumnSize() {
		TableColumnModel model = this.table.getColumnModel();
		
		int larguraTela = Params.INTERNAL_WIDTH - 10;
		
		model.getColumn(0).setMinWidth(larguraTela);
		model.getColumn(0).setPreferredWidth(larguraTela);
		model.getColumn(0).setMaxWidth(larguraTela);
	}
	
	private void openDialogNovo() {
		ReuniaoDialog d = new ReuniaoDialog();
		d.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				super.windowClosed(arg0);
				reset();
				
				table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
			}
		});
		
		int[] diasEscolhidos = d.exibir();
		this.mesSelecionado = this.gerenciador.abrirMes(diasEscolhidos[0], diasEscolhidos[1]);
	}
	
	private void removerBanco() {
		this.gerenciador.remover(this.mesSelecionado);
		
		this.reset();
	}
	
	private void salvar() {
		this.gerenciador.atualizar(this.mesSelecionado);
		
		this.reset();
	}
	
	private void exportar() {
		this.sdf = new SimpleDateFormat("dd");
		this.diaSemana = new SimpleDateFormat("EEEE");
		
		String fileName = this.mesSelecionado.getMes().toString() + ".xml";
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File(fileName));
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fileChooser.setFileFilter(filter);
		
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			Writer out = null;
			
			try {
				FileOutputStream fo = new FileOutputStream(fileChooser.getSelectedFile());
				
				out = new BufferedWriter(new OutputStreamWriter(fo, "UTF-8"));
		
				StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
					.append("<mes nome=\"")
					.append(this.mesSelecionado.getMes().name())
					.append("\" nr=\"")
					.append(this.mesSelecionado.getMes().getNumero())
					.append("\" ano=\"")
					.append(this.mesSelecionado.getAno())
					.append("\">");
				
				for (DiaReuniao diaReuniao : this.mesSelecionado.getDias()) {
					StringBuffer designacoes = this.designacoes(diaReuniao);
					
					if (designacoes != null) {
						sb.append(designacoes);
					}
				}
				
				sb.append("\n</mes>");
				
				out.write(sb.toString());
				
				out.flush();
				
				JOptionPane.showMessageDialog(this, "Processo de Exportação Finalizado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				String erro ="Erro no Processo de Exportação.";
				
				log.error(erro, e);
				
				JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);

			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						log.error(e);
					}
				}
			}
		}
	}
	
	private StringBuffer designacoes(DiaReuniao diaReuniao) {
		boolean temdesignacao = false;
		
		StringBuffer sb = new StringBuffer("\n\t<designacoes diaMes=\"")
			.append(this.sdf.format(diaReuniao.getDia()))
			.append(" diaSemana=\"")
			.append(this.diaSemana.format(diaReuniao.getDia()));
		
		if (TipoDia.VISITA == diaReuniao.getTipoDia()) {
			sb.append("\" visita=\"sim");
		}
		
		sb.append("\">");
	
		if ("S".equalsIgnoreCase(diaReuniao.getQuando())) {
			temdesignacao = this.designacoesSemana(sb, diaReuniao);
			
		} else {
			temdesignacao = this.designacaoDiscurso(sb, diaReuniao);
		}
		
		sb.append("\n\t</designacoes>");
		
		if (temdesignacao) {
			return sb;
		}
		
		return null;
	}
	
	private boolean designacoesSemana(StringBuffer sb, DiaReuniao diaReuniao) {
		boolean temDesignacao = false;
		
		if (TipoDia.VISITA == diaReuniao.getTipoDia()) {
			SemanaVisita visita = this.gerenciador.obterVisita(diaReuniao);
			
			if (visita != null) {
				temDesignacao = true;
				
				this.designacaoServico(sb, visita.getServico());
				this.designacaoSimples(sb, visita.getOracaoServico(), "oracao");
			}
		} else {
			String dirigente = this.gerenciador.obterDirigenteEstudo(diaReuniao);
			DesignacaoEscola destaque = this.gerenciador.obterDestaque(diaReuniao);
			Servico servico = this.gerenciador.obterServico(diaReuniao);
			
			if (dirigente != null && !"".equals(dirigente.trim())) {
				temDesignacao = true;
				
				this.designacaoSimples(sb, dirigente, "estudo_congregacao");
			}
			
			if (destaque != null) {
				temDesignacao = true;
				
				this.designacaoDestaque(sb, destaque);
			}
			
			if (servico != null) {
				temDesignacao = true;
				
				this.designacaoServico(sb, servico);
				this.designacaoSimples(sb, servico.getOracao(), "oracao");
			}
		}
		
		return temDesignacao;
	}
	
	private boolean designacaoDiscurso(StringBuffer sb, DiaReuniao diaReuniao) {
		boolean temDesignacao = false;
		
		Discurso discurso = this.gerenciador.obterDiscurso(diaReuniao);
		
		if (discurso != null) {
			temDesignacao = true;
			
			sb.append("\n").append(doisTab)
				.append("<designacao tipo=\"discurso\">")
				.append("\n").append(tresTab)
				.append("<designado>")
				.append(discurso.getOrador())
				.append("</designado>")
				.append("\n").append(tresTab)
				.append("<tema>")
				.append(discurso.getTema())
				.append("</tema>")
				.append("\n").append(doisTab)
				.append("</designacao>");
		}
		
		return temDesignacao;
	}
	
	private void designacaoServico(StringBuffer sb, Servico servico) {
		for (DesignacaoServico designacao : servico.getDesignacoes()) {
			sb.append("\n").append(doisTab)
				.append("<designacao tipo=\"servico\">")
				.append("\n").append(tresTab)
				.append("<designado>")
				.append(designacao.getOrador())
				.append("</designado>")
				.append("\n").append(tresTab)
				.append("<tempo>")
				.append(designacao.getMinutos())
				.append("</tempo>")
				.append("\n").append(tresTab)
				.append("<tema>")
				.append(designacao.getTema())
				.append("</tema>")
				.append("\n").append(doisTab)
				.append("</designacao>");
		}
	}
	
	private void designacaoDestaque(StringBuffer sb, DesignacaoEscola destaque) {
			sb.append("\n").append(doisTab)
				.append("<designacao tipo=\"destaque\">")
				.append("\n").append(tresTab)
				.append("<designado>")
				.append(destaque.getEstudante())
				.append("</designado>")
				.append("\n").append(tresTab)
				.append("<destaque>")
				.append(destaque.getTema())
				.append("</destaque>")
				.append("\n").append(doisTab)
				.append("</designacao>");
	}
	
	private void designacaoSimples(StringBuffer sb, String designado, String tipo) {
		sb.append("\n").append(doisTab)
			.append("<designacao tipo=\"")
			.append(tipo)
			.append("\">")
			.append("\n").append(tresTab)
			.append("<designado>")
			.append(designado)
			.append("</designado>")
			.append("\n").append(doisTab)
			.append("</designacao>");
	}
	
	private void adicionarDia() {
		new NovoDiaDialog(this.mesSelecionado, this.gerenciador, this).setVisible(true);
	}
}
