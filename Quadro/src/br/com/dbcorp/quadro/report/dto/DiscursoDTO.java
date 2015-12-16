package br.com.dbcorp.quadro.report.dto;

import java.time.LocalDate;

public class DiscursoDTO {

	private String orador;
	private String tema;
	private String congregacao;
	private String cidade;
	private String tipo;
	private LocalDate data;
	private String tpAssembleia;
	private boolean visita;
	
	public String getOrador() {
		return orador;
	}
	public void setOrador(String orador) {
		this.orador = orador;
	}
	
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String getCongregacao() {
		return congregacao;
	}
	public void setCongregacao(String congregacao) {
		this.congregacao = congregacao;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
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
