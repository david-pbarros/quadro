package br.com.dbcorp.quadro.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class DiaReuniao implements Comparable<DiaReuniao> {
	public enum TipoDia {
		ASSEMBLEIA("A"), RECAPITULACAO("R"), VISITA("V"), SEM_REUNIAO("C"), ESPECIAL_ENVIADOS("E"), VIDEOS("AV");
		
		String sigla;
		
		private TipoDia(String sigla) {
			this.sigla = sigla;
		}
		
		@Override
		public String toString() {
			return this.sigla;
		}
		
		public static TipoDia getTipo(String value) {
	        if (value != null && !"".equals(value.trim())) {
	        	for(TipoDia v : values()) {
		        	if(v.toString().equalsIgnoreCase(value))
		        		return v;
		        }
		        
		        throw new IllegalArgumentException();
	        }
	        
	        return null;
	    }
	}
	
	private int id;
	private Date dia;
	private String quando;
	private String tipo;
	private String descricao;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	
	public String getQuando() {
		return quando;
	}
	public void setQuando(String quando) {
		this.quando = quando;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public int compareTo(DiaReuniao o) {
		return this.dia.compareTo(o.dia);
	}
	
	@Transient
	public TipoDia getTipoDia() {
		return TipoDia.getTipo(this.tipo);
	}
	public void setTipoDia(TipoDia tipoDia) {
		if (tipoDia != null) {
			this.tipo = tipoDia.toString();
			
		} else {
			this.tipo = null;
		}
	}
}