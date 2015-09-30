package br.com.dbcorp.quadro.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class SemanaVisita {

	private int id;
	private String canticoIniServico;
	private String canticoFimServico;
	private String canticoIniPublica;
	private String canticoMeioPublica;
	private String canticoFimPublica;
	private String oracaoServico;
	private String oracaoPublica;
	private String primeiroDiscurso;
	private String segundoDiscurso;
	private String terceiroDiscurso;
	private String dirigenteSentinela;
	private String presidente;
	private Mes mes;
	private Servico servico;
	private DiaReuniao diaSemana;
	private DiaReuniao diaFimSemana;
	private List<DesignacaoEscola> designacoesEscola;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCanticoIniServico() {
		return canticoIniServico;
	}
	public void setCanticoIniServico(String canticoIniServico) {
		this.canticoIniServico = canticoIniServico;
	}
	
	public String getCanticoFimServico() {
		return canticoFimServico;
	}
	public void setCanticoFimServico(String canticoFimServico) {
		this.canticoFimServico = canticoFimServico;
	}
	
	public String getCanticoIniPublica() {
		return canticoIniPublica;
	}
	public void setCanticoIniPublica(String canticoIniPublica) {
		this.canticoIniPublica = canticoIniPublica;
	}
	
	public String getCanticoMeioPublica() {
		return canticoMeioPublica;
	}
	public void setCanticoMeioPublica(String canticoMeioPublica) {
		this.canticoMeioPublica = canticoMeioPublica;
	}
	
	public String getCanticoFimPublica() {
		return canticoFimPublica;
	}
	public void setCanticoFimPublica(String canticoFimPublica) {
		this.canticoFimPublica = canticoFimPublica;
	}
	
	public String getOracaoServico() {
		return oracaoServico;
	}
	public void setOracaoServico(String oracaoServico) {
		this.oracaoServico = oracaoServico;
	}
	
	public String getOracaoPublica() {
		return oracaoPublica;
	}
	public void setOracaoPublica(String oracaoPublica) {
		this.oracaoPublica = oracaoPublica;
	}
	
	public String getPrimeiroDiscurso() {
		return primeiroDiscurso;
	}
	public void setPrimeiroDiscurso(String primeiroDiscurso) {
		this.primeiroDiscurso = primeiroDiscurso;
	}
	
	public String getSegundoDiscurso() {
		return segundoDiscurso;
	}
	public void setSegundoDiscurso(String segundoDiscurso) {
		this.segundoDiscurso = segundoDiscurso;
	}
	
	public String getTerceiroDiscurso() {
		return terceiroDiscurso;
	}
	public void setTerceiroDiscurso(String terceiroDiscurso) {
		this.terceiroDiscurso = terceiroDiscurso;
	}
	
	public String getDirigenteSentinela() {
		return dirigenteSentinela;
	}
	public void setDirigenteSentinela(String dirigenteSentinela) {
		this.dirigenteSentinela = dirigenteSentinela;
	}
	
	public String getPresidente() {
		return presidente;
	}
	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_mes")
	public Mes getMes() {
		return mes;
	}
	public void setMes(Mes mes) {
		this.mes = mes;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_servico")
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_dia_semana")
	public DiaReuniao getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(DiaReuniao diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_dia_fim_semana")
	public DiaReuniao getDiaFimSemana() {
		return diaFimSemana;
	}
	public void setDiaFimSemana(DiaReuniao diaFimSemana) {
		this.diaFimSemana = diaFimSemana;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_visita")
	public List<DesignacaoEscola> getDesignacoesEscola() {
		return designacoesEscola;
	}
	public void setDesignacoesEscola(List<DesignacaoEscola> designacoesEscola) {
		this.designacoesEscola = designacoesEscola;
	}
}