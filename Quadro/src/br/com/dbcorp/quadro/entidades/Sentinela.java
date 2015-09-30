package br.com.dbcorp.quadro.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sentinela {

	private int id;
	private String presidente;
	private String leitor;
	private DiaReuniao diaReuniao;
	
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
	
	public String getLeitor() {
		return leitor;
	}
	public void setLeitor(String leitor) {
		this.leitor = leitor;
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
