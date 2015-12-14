package br.com.dbcorp.quadro.report.dto;

import java.util.Date;

public class DesignacaoEscolaDTO {

	private Date dataReuniao;
	private String tpAssembleia;
	private String leitura;
	private String leitor;
	private String estVisita;
	private String estRevisita;
	private String estEstudo;
	private String ajuVisita;
	private String ajuRevisita;
	private String ajuEstudo;
	private String discurso;
	private String orador;
	private String oradorJoias;
	private boolean recapitulacao;
	private boolean visita;
	private boolean videos;
	
	public Date getDataReuniao() {
		return dataReuniao;
	}
	public void setDataReuniao(Date dataReuniao) {
		this.dataReuniao = dataReuniao;
	}
	
	public String getTpAssembleia() {
		return tpAssembleia;
	}
	public void setTpAssembleia(String tpAssembleia) {
		this.tpAssembleia = tpAssembleia;
	}
	
	public String getLeitura() {
		return leitura;
	}
	public void setLeitura(String leitura) {
		this.leitura = leitura;
	}
	
	public String getLeitor() {
		return leitor;
	}
	public void setLeitor(String leitor) {
		this.leitor = leitor;
	}
	
	public String getEstVisita() {
		return estVisita;
	}
	public void setEstVisita(String estVisita) {
		this.estVisita = estVisita;
	}
	
	public String getEstRevisita() {
		return estRevisita;
	}
	public void setEstRevisita(String estRevisita) {
		this.estRevisita = estRevisita;
	}
	
	public String getEstEstudo() {
		return estEstudo;
	}
	public void setEstEstudo(String estEstudo) {
		this.estEstudo = estEstudo;
	}
	
	public String getAjuVisita() {
		return ajuVisita;
	}
	public void setAjuVisita(String ajuVisita) {
		this.ajuVisita = ajuVisita;
	}
	
	public String getAjuRevisita() {
		return ajuRevisita;
	}
	public void setAjuRevisita(String ajuRevisita) {
		this.ajuRevisita = ajuRevisita;
	}
	
	public String getAjuEstudo() {
		return ajuEstudo;
	}
	public void setAjuEstudo(String ajuEstudo) {
		this.ajuEstudo = ajuEstudo;
	}
	
	public String getDiscurso() {
		return discurso;
	}
	public void setDiscurso(String discurso) {
		this.discurso = discurso;
	}
	
	public String getOrador() {
		return orador;
	}
	public void setOrador(String orador) {
		this.orador = orador;
	}
	
	public String getOradorJoias() {
		return oradorJoias;
	}
	public void setOradorJoias(String oradorJoias) {
		this.oradorJoias = oradorJoias;
	}
	
	public boolean isRecapitulacao() {
		return recapitulacao;
	}
	public void setRecapitulacao(boolean recapitulacao) {
		this.recapitulacao = recapitulacao;
	}
	
	public boolean isVisita() {
		return visita;
	}
	public void setVisita(boolean visita) {
		this.visita = visita;
	}
	
	public boolean isVideos() {
		return videos;
	}
	public void setVideos(boolean videos) {
		this.videos = videos;
	}
}
