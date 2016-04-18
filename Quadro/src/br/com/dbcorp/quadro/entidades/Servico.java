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
	private String oracInicial;
	private String presidente;
	private String temaDisc;
	private String orador;
	private String joias;
	
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
	
	
	public String getOracInicial() {
		return oracInicial;
	}
	public void setOracInicial(String oracInicial) {
		this.oracInicial = oracInicial;
	}
	
	public String getPresidente() {
		return presidente;
	}
	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}
	
	public String getTemaDisc() {
		return temaDisc;
	}
	public void setTemaDisc(String temaDisc) {
		this.temaDisc = temaDisc;
	}
	
	public String getOrador() {
		return orador;
	}
	public void setOrador(String orador) {
		this.orador = orador;
	}
	
	public String getJoias() {
		return joias;
	}
	public void setJoias(String joias) {
		this.joias = joias;
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
