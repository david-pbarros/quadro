package br.com.dbcorp.quadro.entidades;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Servico {

	private int id;
	private String oracao;
	private String cantico;
	private DiaReuniao diaReuniao;
	private List<DesignacaoServico> designacoes;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOracao() {
		return oracao;
	}
	public void setOracao(String oracao) {
		this.oracao = oracao;
	}
	
	public String getCantico() {
		return cantico;
	}
	public void setCantico(String cantico) {
		this.cantico = cantico;
	}
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_dia")
	public DiaReuniao getDiaReuniao() {
		return diaReuniao;
	}
	public void setDiaReuniao(DiaReuniao diaReuniao) {
		this.diaReuniao = diaReuniao;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_servico")
	public List<DesignacaoServico> getDesignacoes() {
		if (designacoes !=null && !designacoes.isEmpty()) {
			Collections.sort(designacoes);
		}
		
		return designacoes;
	}
	public void setDesignacoes(List<DesignacaoServico> designacoes) {
		this.designacoes = designacoes;
	}
}
