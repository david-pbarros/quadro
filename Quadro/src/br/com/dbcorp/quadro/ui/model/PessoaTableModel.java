package br.com.dbcorp.quadro.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.dbcorp.quadro.entidades.Pessoa;

public class PessoaTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 4842874392467777392L;

	private static final int PESSOA = 0;
	private static final int ABREVIACAO = 1;
	
	private String[] colunas = new String[] { "Nome Completo", "Abreviação" };
	
	private List<Pessoa> pessoas;

	public PessoaTableModel() {
		this(new ArrayList<Pessoa>());
	}
	
	public PessoaTableModel(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public void setItens(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public int getColumnCount() {
		return this.colunas.length;
	}

	public int getRowCount() {
		return this.pessoas.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return this.colunas[columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case PESSOA:
		case ABREVIACAO:
			return String.class;
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case PESSOA:
		case ABREVIACAO:
			return true;
		default:
			return false;
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pessoa pessoa = this.pessoas.get(rowIndex);
		
		switch (columnIndex) {
		case PESSOA:
			return pessoa.getNome();
		case ABREVIACAO:
			return pessoa.getAbreviacao();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Pessoa pessoa = this.pessoas.get(rowIndex);
		
		switch (columnIndex) {
		case PESSOA:
			pessoa.setNome((String) aValue);
			break;
		case ABREVIACAO:
			pessoa.setAbreviacao((String) aValue);
			break;
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
		
		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}
	
	public void limpar() {
		this.pessoas.clear();
		fireTableDataChanged();
	}
}
