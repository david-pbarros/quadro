package br.com.dbcorp.quadro.entidades;

public enum MesesDom {
	Janeiro(1),
	Fevereiro(2),
	Março(3),
	Abril(4),
	Maio(5),
	Junho(6),
	Julho(7),
	Agosto(8),
	Setembro(9),
	Outubro(10),
	Novembro(11),
	Dezembro(12);
	
	private int mes;
	
	private MesesDom(int mes) {
		this.mes = mes;
	}
	
	public int getNumero() {
		return this.mes;
	}
}