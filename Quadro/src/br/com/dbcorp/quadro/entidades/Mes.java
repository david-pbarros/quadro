package br.com.dbcorp.quadro.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Mes {

	private int id;
	private MesesDom mes;
	private int ano;
	private List<DiaReuniao> dias;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public MesesDom getMes() {
		return mes;
	}
	public void setMes(MesesDom mes) {
		this.mes = mes;
	}
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_mes")
	public List<DiaReuniao> getDias() {
		return dias;
	}
	public void setDias(List<DiaReuniao> dias) {
		this.dias = dias;
	}
}
