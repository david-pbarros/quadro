package br.com.dbcorp.quadro.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DesignacaoServico implements Comparable<DesignacaoServico> {
	
	private int id;
	private String minutos;
	private String tema;
	private String orador;
	private int ordem;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMinutos() {
		return minutos;
	}
	public void setMinutos(String minutos) {
		this.minutos = minutos;
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
	
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	
	@Override
	public int compareTo(DesignacaoServico o) {
		if (o.ordem < this.ordem) {
			return 1;
		
		} else if (o.ordem > this.ordem) {
			return -1;
			
		} else {
			return 0;
		}
	}
}
