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
	
	
	
	private String oracaoIni;
	private String presidente;
	private String temaDiscurso;
	private String oradorDiscruso;
	private String oradorJoias;
	
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
	
	
	
	
	
	public String getOracaoIni() {
		return oracaoIni;
	}
	public void setOracaoIni(String oracaoIni) {
		this.oracaoIni = oracaoIni;
	}
	
	public String getPresidente() {
		return presidente;
	}
	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}
	
	public String getTemaDiscurso() {
		return temaDiscurso;
	}
	public void setTemaDiscurso(String temaDiscurso) {
		this.temaDiscurso = temaDiscurso;
	}
	
	public String getOradorDiscruso() {
		return oradorDiscruso;
	}
	public void setOradorDiscruso(String oradorDiscruso) {
		this.oradorDiscruso = oradorDiscruso;
	}
	
	public String getOradorJoias() {
		return oradorJoias;
	}
	public void setOradorJoias(String oradorJoias) {
		this.oradorJoias = oradorJoias;
	}
}
