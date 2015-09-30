package br.com.dbcorp.quadro.report.dto;

import java.util.Date;

public class DesignacaoEscolaDTO {

	private Date dataReuniao;
	private String tpAssembleia;
	private String leitura;
	private String leitor;
	private String tema1;
	private String tema2;
	private String tema3;
	private String estudante1;
	private String estudante2;
	private String estudante3;
	private String ajudante1;
	private String ajudante2;
	private boolean recapitulacao;
	private boolean visita;
	
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
	
	public String getTema1() {
		return tema1;
	}
	public void setTema1(String tema1) {
		this.tema1 = tema1;
	}
	
	public String getTema2() {
		return tema2;
	}
	public void setTema2(String tema2) {
		this.tema2 = tema2;
	}
	
	public String getTema3() {
		return tema3;
	}
	public void setTema3(String tema3) {
		this.tema3 = tema3;
	}
	
	public String getEstudante1() {
		return estudante1;
	}
	public void setEstudante1(String estudante1) {
		this.estudante1 = estudante1;
	}
	
	public String getEstudante2() {
		return estudante2;
	}
	public void setEstudante2(String estudante2) {
		this.estudante2 = estudante2;
	}
	
	public String getEstudante3() {
		return estudante3;
	}
	public void setEstudante3(String estudante3) {
		this.estudante3 = estudante3;
	}
	
	public String getAjudante1() {
		return ajudante1;
	}
	public void setAjudante1(String ajudante1) {
		this.ajudante1 = ajudante1;
	}
	
	public String getAjudante2() {
		return ajudante2;
	}
	public void setAjudante2(String ajudante2) {
		this.ajudante2 = ajudante2;
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
}
