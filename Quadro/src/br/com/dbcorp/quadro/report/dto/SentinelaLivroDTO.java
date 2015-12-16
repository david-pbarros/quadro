package br.com.dbcorp.quadro.report.dto;

import java.time.LocalDate;

public class SentinelaLivroDTO {

	private String presidente;
	private String leitor;
	private LocalDate dataReuniao;
	private String tpAssembleia;
	private boolean visita;
	
	public String getPresidente() {
		return presidente;
	}
	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}
	
	public String getLeitor() {
		return leitor;
	}
	public void setLeitor(String leitor) {
		this.leitor = leitor;
	}
	
	public LocalDate getDataReuniao() {
		return dataReuniao;
	}
	public void setDataReuniao(LocalDate dataReuniao) {
		this.dataReuniao = dataReuniao;
	}
	
	public String getTpAssembleia() {
		return tpAssembleia;
	}
	public void setTpAssembleia(String tpAssembleia) {
		this.tpAssembleia = tpAssembleia;
	}
	
	public boolean isVisita() {
		return visita;
	}
	public void setVisita(boolean visita) {
		this.visita = visita;
	}
}
