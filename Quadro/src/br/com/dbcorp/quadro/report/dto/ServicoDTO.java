package br.com.dbcorp.quadro.report.dto;

import java.time.LocalDate;

public class ServicoDTO {

	private LocalDate dataReuniao;
	private String tpAssembleia;
	private String tempo;
	private String tema;
	private String orador;
	private String oracao;
	private boolean visita;
	
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
	
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String getOrador() {
		return orador;
	}
	public void setOrador(String orador) {
		this.orador = orador;
	}
	
	public String getOracao() {
		return oracao;
	}
	public void setOracao(String oracao) {
		this.oracao = oracao;
	}
	
	public boolean isVisita() {
		return visita;
	}
	public void setVisita(boolean visita) {
		this.visita = visita;
	}
}
