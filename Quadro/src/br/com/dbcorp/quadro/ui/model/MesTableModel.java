package br.com.dbcorp.quadro.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.entidades.MesesDom;

public class MesTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 4842874392467777392L;

	private static final int MES = 0;
	private static final int ANO = 1;
	
	private String[] colunas = new String[] { "Mes", "Ano" };
	
	private List<Mes> meses;

	public MesTableModel() {
		this(new ArrayList<Mes>());
	}
	
	public MesTableModel(List<Mes> meses) {
		this.meses = meses;
	}
	
	public void setItens(List<Mes> meses) {
		this.meses = meses;
	}
	
	public List<Mes> getMeses() {
		return meses;
	}
	
	public int getColumnCount() {
		return this.colunas.length;
	}

	public int getRowCount() {
		return this.meses.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return this.colunas[columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case MES:
		case ANO:
			return String.class;
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Mes mes = this.meses.get(rowIndex);
		
		switch (columnIndex) {
		case MES:
			return mes.getMes().name();
		case ANO:
			return mes.getAno();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Mes mes = this.meses.get(rowIndex);
		
		switch (columnIndex) {
		case MES:
			mes.setMes(MesesDom.valueOf((String) aValue));
			break;
		case ANO:
			mes.setAno((Integer) aValue);
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
		
		fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
	}
	
	public void limpar() {
		this.meses.clear();
		fireTableDataChanged();
	}
}
