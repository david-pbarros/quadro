package br.com.dbcorp.quadro.ui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Pessoa;
import br.com.dbcorp.quadro.exceptions.DuplicateKeyException;
import br.com.dbcorp.quadro.gerenciador.PessoaGerenciador;
import br.com.dbcorp.quadro.ui.Params;

public class PessoaDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 7789542379134478478L;
	
	private PessoaGerenciador gerenciador;
	private Pessoa pessoa;
	private JTextField nomeFl;
	private JButton btnOK;
	private JButton btnCancela;
	
	public PessoaDialog(PessoaGerenciador gerenciador, Genero genero) {
		setTitle("Nova Pessoa");
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.gerenciador = gerenciador;
		this.pessoa = new Pessoa();
		this.pessoa.setGenero(genero);
		this.nomeFl = new JTextField();
		this.btnOK = new JButton("Salvar");
		this.btnCancela = new JButton("Cancelar");
		
		this.nomeFl.setColumns(70);
		
		this.btnOK.addActionListener(this);
		this.btnCancela.addActionListener(this);
		
		JPanel infoPanel = new JPanel();
		infoPanel.add(new JLabel("Nome: "));
		infoPanel.add(this.nomeFl);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.btnOK);
		buttonPanel.add(this.btnCancela);
		
		getContentPane().add(infoPanel, BorderLayout.CENTER); 
		getContentPane().add(buttonPanel, BorderLayout.SOUTH); 
		
		pack();
		
		this.centerScreen();
	}
	
	private void centerScreen() {
		int w = Params.WIDTH - this.getPreferredSize().width;
		int h = Params.HEIGHT - this.getPreferredSize().height;
		setLocation(w/2, h/2);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnOK)) {
			this.inserir();
		} else {
			dispose();
		}
	}
	
	private void inserir() {
		this.pessoa.setNome(this.nomeFl.getText());
		
		try {
			this.gerenciador.inserir(pessoa);
			
			dispose();
		} catch (DuplicateKeyException e) {
			JOptionPane.showMessageDialog(this, "Pessoa já existente", "Erro!", JOptionPane.WARNING_MESSAGE);
		}
	}
}