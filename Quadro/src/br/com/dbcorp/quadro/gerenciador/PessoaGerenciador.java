package br.com.dbcorp.quadro.gerenciador;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.Pessoa;
import br.com.dbcorp.quadro.exceptions.DuplicateKeyException;

public class PessoaGerenciador extends Gerenciador {

	public void inserir(Pessoa pessoa) throws DuplicateKeyException {
		Query query = DataBaseHelper.createQuery("FROM Pessoa p WHERE p.nome = :nome")
				.setParameter("nome", pessoa.getNome());
		
		try {
			query.getSingleResult();
			throw new DuplicateKeyException();
			
		} catch (NoResultException exception) {
			DataBaseHelper.persist(pessoa);
		}
	}
	
	public void atualizar(Pessoa pessoa) {
		DataBaseHelper.merge(pessoa);
	}
	
	public void remover(Pessoa pessoa) {
		DataBaseHelper.remove(pessoa);
	}
}
