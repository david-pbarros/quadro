package br.com.dbcorp.quadro.report.dto;

import java.time.LocalDate;
import java.util.List;

public class VisitaDTO {
	
	private LocalDate diaSemana;
	private LocalDate diaFimSemana;
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
	private String primeiroDiscurso;
	private String segundoDiscurso;
	private String terceiroDiscurso;
	private String oracaoServico;
	private String oracaoPublica;
	private String canticoFimServico;
	private String canticoIniPublica;
	private String canticoMeioPublica;
	private String canticoFimPublica;
	private String presidente;
	private String dirigenteSentinela;
	private List<VisitaServicoDTO> servico;
	
	public LocalDate getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(LocalDate diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public LocalDate getDiaFimSemana() {
		return diaFimSemana;
	}
	public void setDiaFimSemana(LocalDate diaFimSemana) {
		this.diaFimSemana = diaFimSemana;
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
	
	public String getPrimeiroDiscurso() {
		return primeiroDiscurso;
	}
	public void setPrimeiroDiscurso(String primeiroDiscurso) {
		this.primeiroDiscurso = primeiroDiscurso;
	}
	
	public String getSegundoDiscurso() {
		return segundoDiscurso;
	}
	public void setSegundoDiscurso(String segundoDiscurso) {
		this.segundoDiscurso = segundoDiscurso;
	}
	
	public String getTerceiroDiscurso() {
		return terceiroDiscurso;
	}
	public void setTerceiroDiscurso(String terceiroDiscurso) {
		this.terceiroDiscurso = terceiroDiscurso;
	}
	
	public String getOracaoServico() {
		return oracaoServico;
	}
	public void setOracaoServico(String oracaoServico) {
		this.oracaoServico = oracaoServico;
	}
	
	public String getOracaoPublica() {
		return oracaoPublica;
	}
	public void setOracaoPublica(String oracaoPublica) {
		this.oracaoPublica = oracaoPublica;
	}
	
	public String getCanticoFimServico() {
		return canticoFimServico;
	}
	public void setCanticoFimServico(String canticoFimServico) {
		this.canticoFimServico = canticoFimServico;
	}
	
	public String getCanticoIniPublica() {
		return canticoIniPublica;
	}
	public void setCanticoIniPublica(String canticoIniPublica) {
		this.canticoIniPublica = canticoIniPublica;
	}
	
	public String getCanticoMeioPublica() {
		return canticoMeioPublica;
	}
	public void setCanticoMeioPublica(String canticoMeioPublica) {
		this.canticoMeioPublica = canticoMeioPublica;
	}
	
	public String getCanticoFimPublica() {
		return canticoFimPublica;
	}
	public void setCanticoFimPublica(String canticoFimPublica) {
		this.canticoFimPublica = canticoFimPublica;
	}
	
	public String getPresidente() {
		return presidente;
	}
	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}
	
	public String getDirigenteSentinela() {
		return dirigenteSentinela;
	}
	public void setDirigenteSentinela(String dirigenteSentinela) {
		this.dirigenteSentinela = dirigenteSentinela;
	}
	
	public List<VisitaServicoDTO> getServico() {
		return servico;
	}
	public void setServico(List<VisitaServicoDTO> servico) {
		this.servico = servico;
	}
}
