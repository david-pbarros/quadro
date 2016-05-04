package br.com.dbcorp.quadro.gerenciador;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.entidades.Pessoa;

@SuppressWarnings("unchecked")
public class Gerenciador {

	public List<Mes> obterMeses() {
		Query query = DataBaseHelper.createQuery("FROM Mes m");
		
		return query.getResultList();
	}
	
	public List<DiaReuniao> obterDiasReuniaoSemanal(Mes mes) {
		return this.obterDiasReuniao(mes, "S");
	}
	
	public List<DiaReuniao> obterDiasReuniaoFind(Mes mes) {
		return this.obterDiasReuniao(mes, "F");
	}
	
	public List<DiaReuniao> obterDiasReuniaoEnviados(Mes mes) {
		Query query = DataBaseHelper.createQuery("SELECT d FROM Mes m JOIN FETCH m.dias d WHERE m.id = :id AND d.quando = 'F' AND d.tipo = 'E' ORDER BY d.dia")
				.setParameter("id", mes.getId());
		
		return query.getResultList();
	}
	
	public List<DiaReuniao> obterDiasReuniao(Mes mes) {
		Query query = DataBaseHelper.createQuery("SELECT d FROM Mes m JOIN FETCH m.dias d WHERE m.id = :id ORDER BY d.dia")
				.setParameter("id", mes.getId());
		
		List<DiaReuniao> lista = query.getResultList();
		
		for (int i = 0; i < lista.size(); i++) {
			if ("C".equalsIgnoreCase(lista.get(i).getTipo())) {
				lista.remove(i);
			}
		}
		
		return lista;
	}
	
	public List<Pessoa> listarPessoas(Genero genero) {
		Query query = DataBaseHelper.createQuery("FROM Pessoa p WHERE p.genero = :genero ORDER BY p.nome")
				.setParameter("genero", genero);
		
		return query.getResultList();
	}
	
	public List<String> listarNomesPessoas(Genero genero) {
		List<String> nomes = new ArrayList<String>();
		
		for (Pessoa pessoa : this.listarPessoas(genero)) {
			nomes.add(pessoa.getNome());
		}
		
		return nomes;
	}
	
	public String abreviacaoPessoa(String nome) {
		Query query = DataBaseHelper.createQuery("SELECT p.abreviacao FROM Pessoa p WHERE p.nome = :nome AND p.abreviacao != '' AND p.abreviacao IS NOT NULL")
			.setParameter("nome", nome);
		
		List<String> abrevicoes = query.getResultList();
		
		if (!abrevicoes.isEmpty()) {
			return abrevicoes.get(0);
		}
		
		return nome;
	}
	
	protected void salvaPessoa(String nome, Genero genero) {
		if (!"".equals(nome) && nome != null) {
			Query query = DataBaseHelper.createQuery("FROM Pessoa p WHERE p.nome = :nome")
					.setParameter("nome", nome);
			
			try {
				query.getSingleResult();
			
			} catch (NonUniqueResultException ex) {
				return;
				
			} catch(NoResultException ex) {
				Pessoa pessoa = new Pessoa();
				pessoa.setGenero(genero);
				pessoa.setNome(nome);
				
				DataBaseHelper.persist(pessoa);
			}
		}
	}
	
	private List<DiaReuniao> obterDiasReuniao(Mes mes, String quando) {
		Query query = DataBaseHelper.createQuery("SELECT d FROM Mes m JOIN FETCH m.dias d WHERE m.id = :id AND d.quando = :quando AND (d.tipo NOT IN ('C', 'E') OR d.tipo IS NULL) ORDER BY d.dia")
				.setParameter("id", mes.getId())
				.setParameter("quando", quando);
		
		return query.getResultList();
	}
}
