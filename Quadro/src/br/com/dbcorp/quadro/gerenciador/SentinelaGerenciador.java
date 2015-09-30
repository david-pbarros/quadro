package br.com.dbcorp.quadro.gerenciador;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Sentinela;


public class SentinelaGerenciador extends Gerenciador {
	
	public void salvarSentinela(List<Sentinela> sentinelas) {
		for (Sentinela sentinela : sentinelas) {
			if (sentinela.getId() == 0) {
				DataBaseHelper.persist(sentinela);
			
			} else {
				DataBaseHelper.merge(sentinela);
			}
			
			this.salvaPessoa(sentinela.getPresidente(), Genero.M);
			this.salvaPessoa(sentinela.getLeitor(), Genero.M);
		}
	}
	
	public Sentinela obterSentinelaSemana(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("FROM Sentinela s WHERE s.diaReuniao = :dia")
				.setParameter("dia", diaReuniao);
		
		try {
			return (Sentinela) query.getSingleResult();
			
		} catch (NoResultException exception) {
			return null;
		}
	}
	
}
