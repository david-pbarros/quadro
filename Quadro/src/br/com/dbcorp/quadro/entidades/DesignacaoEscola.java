package br.com.dbcorp.quadro.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DesignacaoEscola {

	private int id;
	private String sala;
	private int numero;
	private String estudante;
	private String ajudante;
	private String tema;
	private DiaReuniao dia;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getEstudante() {
		return estudante;
	}
	public void setEstudante(String estudante) {
		this.estudante = estudante;
	}
	
	public String getAjudante() {
		return ajudante;
	}
	public void setAjudante(String ajudante) {
		this.ajudante = ajudante;
	}
	
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_dia")
	public DiaReuniao getDia() {
		return dia;
	}
	public void setDia(DiaReuniao dia) {
		this.dia = dia;
	}
}