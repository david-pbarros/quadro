package br.com.dbcorp.quadro.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Limpeza {
	
	private int seq;
	private String data;
	private String grupo;
	
	@Id
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
}
