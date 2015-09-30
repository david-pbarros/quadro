package br.com.dbcorp.quadro.report.dto;

public class VisitaServicoDTO {
	
	private String tempo;
	private String tema;
	private String orador;
	private String cantico;
	
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
	
	public String getCantico() {
		return cantico;
	}
	public void setCantico(String cantico) {
		this.cantico = cantico;
	}
}