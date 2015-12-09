package br.com.dbcorp.quadro.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;

import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Pessoa;
import br.com.dbcorp.quadro.gerenciador.PessoaGerenciador;
import br.com.dbcorp.quadro.ui.dialog.PessoaDialog;
import br.com.dbcorp.quadro.ui.model.PessoaTableModel;

public class PessoasUI extends InternalUI implements TableModelListener, ActionListener, ListSelectionListener {
	private static final long serialVersionUID = -1753463790399252181L;

	private JTable table;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnRemove;
	private JButton btnSair;
	private JComboBox<String> cbGenero;
	
	private PessoaGerenciador gerenciador;
	private Pessoa pessoaSelecionada;
	private List<Pessoa> pessoasSelecionadas;
	
	/**
	 * Create the frame.
	 */
	public PessoasUI() {
		super();
		
		this.gerenciador = new PessoaGerenciador(); 
		this.pessoasSelecionadas = new ArrayList<Pessoa>();
		
		this.setButtons();
		
		TitledBorder title = BorderFactory.createTitledBorder("Pessoas");

		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout(0, 0));
		containerPanel.setBorder(title);
		
		JPanel commandPanel = new JPanel(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		buttonPanel.add(this.btnNovo);
		buttonPanel.add(this.btnSalvar);
		buttonPanel.add(this.btnRemove);
		
		String[] generos = {Genero.M.getDescricao(), Genero.F.getDescricao()};
		this.cbGenero = new JComboBox<String>(generos);
		this.cbGenero.addActionListener(this);
		
		JPanel sairPanel = new JPanel();
		sairPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		sairPanel.add(this.btnSair);
		
		commandPanel.add(buttonPanel, BorderLayout.WEST);
		commandPanel.add(sairPanel, BorderLayout.EAST);
		commandPanel.add(this.cbGenero, BorderLayout.SOUTH);
		
		containerPanel.add(commandPanel, BorderLayout.NORTH);
		containerPanel.add(this.setTable(), BorderLayout.CENTER);
		
		getContentPane().add(containerPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		this.reset();
	}

	//ActionListener
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnNovo)) {
			this.openDialogNovo();
			
		} else if (event.getSource().equals(this.btnSalvar)) {
			this.atualizarBanco();
		
		} else if (event.getSource().equals(this.btnRemove)) {
			int response = JOptionPane.showConfirmDialog(null, "Confirma Remoção?", "Remover?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.YES_OPTION) {
		    	this.removerBanco();
		    } 
		} else if (event.getSource().equals(this.btnSair)) {
			dispose();
		
		} else if (event.getSource().equals(this.cbGenero)) {
			this.reset();
		}
	}
	
	//ListSelectionListener
	public void valueChanged(ListSelectionEvent event) {
		List<Pessoa> ajudantes = ((PessoaTableModel) this.table.getModel()).getPessoas();
		
		if (this.table.getSelectedRow() > -1 && ajudantes.size() > this.table.getSelectedRow()) {
			this.pessoaSelecionada = ajudantes.get(this.table.getSelectedRow());
			
			this.btnRemove.setVisible(true);
		}
	}

	//TableModelListener
	public void tableChanged(TableModelEvent event) {
	    if (event.getType() == TableModelEvent.UPDATE) {
	    	PessoaTableModel model = (PessoaTableModel)event.getSource();
	    	
	    	if (!model.getPessoas().isEmpty()) {
	    		this.pessoasSelecionadas.add(model.getPessoas().get(event.getFirstRow()));
	    	}
	    	
	    	this.btnSalvar.setVisible(true);
	    }
	}
	
	private DScrollPane setTable() {
		this.table = new JTable();
		this.table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
		this.table.getSelectionModel().addListSelectionListener(this);
		
		DScrollPane scrollPane = new DScrollPane(this.table);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH, 100));
		
		return scrollPane;
	}
	
	private void setButtons() {
		this.btnNovo = new JButton(Params.btNovoImg());
		this.btnSalvar = new JButton(Params.btSalvarImg());
		this.btnRemove = new JButton(Params.btRemoverImg());
		this.btnSair = new JButton(Params.btFecharImg());
		
		this.btnNovo.addActionListener(this);
		this.btnSalvar.addActionListener(this);
		this.btnRemove.addActionListener(this);
		this.btnSair.addActionListener(this);
		
		this.btnNovo.setToolTipText("Novo");
		this.btnSalvar.setToolTipText("Salvar");
		this.btnRemove.setToolTipText("Remover");
		this.btnSair.setToolTipText("Sair");
	}
	
	public void reset() {
		Genero genero = this.cbGenero.getSelectedIndex() == 0 ? Genero.M : Genero.F;
		
		if (this.table.getModel() instanceof PessoaTableModel) {
			PessoaTableModel model = (PessoaTableModel) this.table.getModel();
			model.setItens(this.gerenciador.listarPessoas(genero));
			model.fireTableDataChanged();
		
		} else {
			PessoaTableModel model = new PessoaTableModel(this.gerenciador.listarPessoas(genero));
			model.addTableModelListener(this);
			this.table.setModel(model);
		} 
		
		this.btnSalvar.setVisible(false);
		this.btnRemove.setVisible(false);
		
		this.setColumnSize();
	}
	
	private void setColumnSize() {
		TableColumnModel model = this.table.getColumnModel();
		
		int larguraAbr = ((Params.INTERNAL_WIDTH - 10) / 3);
		int larguraNome = larguraAbr * 2;
		
		model.getColumn(0).setMinWidth(larguraNome );
		model.getColumn(0).setPreferredWidth(larguraNome);
		model.getColumn(0).setMaxWidth(larguraNome);
		
		model.getColumn(1).setMinWidth(larguraAbr);
		model.getColumn(1).setPreferredWidth(larguraAbr);
		model.getColumn(1).setMaxWidth(larguraAbr);
	}
	
	private void openDialogNovo() {
		PessoaDialog d = new PessoaDialog(this.gerenciador, this.cbGenero.getSelectedIndex() == 0 ? Genero.M : Genero.F);
		d.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				super.windowClosed(arg0);
				reset();
			}
		});
		
		d.setVisible(true);
	}
	
	private void atualizarBanco() {
		for (Pessoa pessoa : this.pessoasSelecionadas) {
			this.gerenciador.atualizar(pessoa);
		}
		
		this.btnSalvar.setVisible(false);
	}
	
	private void removerBanco() {
		this.gerenciador.remover(this.pessoaSelecionada);
		
		this.reset();
	}
	
	
}
