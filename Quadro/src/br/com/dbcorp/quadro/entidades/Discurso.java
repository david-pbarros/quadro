package br.com.dbcorp.quadro.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Discurso {

	private int id;
	private String orador;
	private String tema;
	private String congregacao;
	private String cidade;
	private String tipo;
	private DiaReuniao diaReuniao;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_dia")
	public DiaReuniao getDiaReuniao() {
		return diaReuniao;
	}
	public void setDiaReuniao(DiaReuniao diaReuniao) {
		this.diaReuniao = diaReuniao;
	}
}
