package br.com.dbcorp.quadro.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VidaMinisterio {

	private int id;
	private String presidente;
	private String desgApresentacao;
	private DiaReuniao dia;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPresidente() {
		return presidente;
	}
	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}
	
	public String getDesgApresentacao() {
		return desgApresentacao;
	}
	public void setDesgApresentacao(String desgApresentacao) {
		this.desgApresentacao = desgApresentacao;
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
