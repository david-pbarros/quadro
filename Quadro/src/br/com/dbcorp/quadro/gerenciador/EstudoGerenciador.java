package br.com.dbcorp.quadro.gerenciador;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Estudo;
import br.com.dbcorp.quadro.entidades.Genero;


public class EstudoGerenciador extends Gerenciador {
	
	public void salvarEstudos(List<Estudo> estudos) {
		for (Estudo estudo : estudos) {
			if (estudo.getId() == 0) {
				DataBaseHelper.persist(estudo);
			
			} else {
				DataBaseHelper.merge(estudo);
			}
			
			this.salvaPessoa(estudo.getDirigente(), Genero.M);
			this.salvaPessoa(estudo.getLeitor(), Genero.M);
		}
	}
	
	public Estudo obterEstudoSemana(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("FROM Estudo e WHERE e.diaReuniao = :dia")
				.setParameter("dia", diaReuniao);
		
		try {
			return (Estudo) query.getSingleResult();
			
		} catch (NoResultException exception) {
			return null;
		}
	}
	
}
