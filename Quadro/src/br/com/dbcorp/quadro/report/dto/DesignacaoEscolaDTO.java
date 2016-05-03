package br.com.dbcorp.quadro.report.dto;

import java.time.LocalDate;

public class DesignacaoEscolaDTO {

	private LocalDate dataReuniao;
	private String tpAssembleia;
	private String leitorA;
	private String leitorB;
	private String estVisitaA;
	private String estVisitaB;
	private String estRevisitaA;
	private String estRevisitaB;
	private String estEstudoA;
	private String estEstudoB;
	private String ajuVisitaA;
	private String ajuVisitaB;
	private String ajuRevisitaA;
	private String ajuRevisitaB;
	private String ajuEstudoA;
	private String ajuEstudoB;
	private String desigVideos;
	private boolean recapitulacao;
	private boolean visita;
	private boolean videos;
	
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
	
	public String getLeitorA() {
		return leitorA;
	}
	public void setLeitorA(String leitor) {
		this.leitorA = leitor;
	}
	
	public String getLeitorB() {
		return leitorB;
	}
	public void setLeitorB(String leitorB) {
		this.leitorB = leitorB;
	}
	
	public String getEstVisitaA() {
		return estVisitaA;
	}
	public void setEstVisitaA(String estVisita) {
		this.estVisitaA = estVisita;
	}
	
	public String getEstVisitaB() {
		return estVisitaB;
	}
	public void setEstVisitaB(String estVisita) {
		this.estVisitaB = estVisita;
	}
	
	public String getEstRevisitaA() {
		return estRevisitaA;
	}
	public void setEstRevisitaA(String estRevisita) {
		this.estRevisitaA = estRevisita;
	}
	
	public String getEstRevisitaB() {
		return estRevisitaB;
	}
	public void setEstRevisitaB(String estRevisita) {
		this.estRevisitaB = estRevisita;
	}
	
	public String getEstEstudoA() {
		return estEstudoA;
	}
	public void setEstEstudoA(String estEstudo) {
		this.estEstudoA = estEstudo;
	}
	
	public String getEstEstudoB() {
		return estEstudoB;
	}
	public void setEstEstudoB(String estEstudo) {
		this.estEstudoB = estEstudo;
	}
	
	public String getAjuVisitaA() {
		return ajuVisitaA;
	}
	public void setAjuVisitaA(String ajuVisita) {
		this.ajuVisitaA = ajuVisita;
	}
	
	public String getAjuVisitaB() {
		return ajuVisitaB;
	}
	public void setAjuVisitaB(String ajuVisita) {
		this.ajuVisitaB = ajuVisita;
	}
	
	public String getAjuRevisitaA() {
		return ajuRevisitaA;
	}
	public void setAjuRevisitaA(String ajuRevisita) {
		this.ajuRevisitaA = ajuRevisita;
	}
	
	public String getAjuRevisitaB() {
		return ajuRevisitaB;
	}
	public void setAjuRevisitaB(String ajuRevisita) {
		this.ajuRevisitaB = ajuRevisita;
	}
	
	public String getAjuEstudoA() {
		return ajuEstudoA;
	}
	public void setAjuEstudoA(String ajuEstudo) {
		this.ajuEstudoA = ajuEstudo;
	}
	
	public String getAjuEstudoB() {
		return ajuEstudoB;
	}
	public void setAjuEstudoB(String ajuEstudo) {
		this.ajuEstudoB = ajuEstudo;
	}
	
	public String getDesigVideos() {
		return desigVideos;
	}
	public void setDesigVideos(String desigVideos) {
		this.desigVideos = desigVideos;
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
