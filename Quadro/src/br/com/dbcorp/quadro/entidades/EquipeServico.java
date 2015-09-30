package br.com.dbcorp.quadro.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EquipeServico {

	private int id;
	private String indcador1;
	private String indcador2;
	private String indcador3;
	private String volante1;
	private String volante2;
	private DiaReuniao diaReuniao;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIndcador1() {
		return indcador1;
	}
	public void setIndcador1(String indcador1) {
		this.indcador1 = indcador1;
	}
	
	public String getIndcador2() {
		return indcador2;
	}
	public void setIndcador2(String indcador2) {
		this.indcador2 = indcador2;
	}
	
	public String getIndcador3() {
		return indcador3;
	}
	public void setIndcador3(String indcador3) {
		this.indcador3 = indcador3;
	}
	
	public String getVolante1() {
		return volante1;
	}
	public void setVolante1(String volante1) {
		this.volante1 = volante1;
	}
	
	public String getVolante2() {
		return volante2;
	}
	public void setVolante2(String volante2) {
		this.volante2 = volante2;
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
