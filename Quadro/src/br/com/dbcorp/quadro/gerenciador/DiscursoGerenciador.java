package br.com.dbcorp.quadro.gerenciador;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Discurso;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Mes;


public class DiscursoGerenciador extends Gerenciador {
	
	public void salvarDiscurso(List<Discurso> discursos) {
		for (Discurso discurso : discursos) {
			if (discurso.getId() == 0) {
				DataBaseHelper.persist(discurso);
			
			} else {
				DataBaseHelper.merge(discurso);
			}
			
			if ("E".equalsIgnoreCase(discurso.getTipo())) {
				this.salvaPessoa(discurso.getOrador(), Genero.M);
			}
		}
	}
	
	public Discurso obterDiscursoSemana(DiaReuniao diaReuniao, String tipo) {
		Query query = DataBaseHelper.createQuery("FROM Discurso d WHERE d.diaReuniao = :dia AND d.tipo = :tipo")
				.setParameter("dia", diaReuniao)
				.setParameter("tipo", tipo);
		
		try {
			return (Discurso) query.getSingleResult();
			
		} catch (NoResultException exception) {
			return null;
		}
	}
	
	public void salvarMesDesignacao(Mes mes) {
		if (mes.getId() == 0) {
			DataBaseHelper.persist(mes);
		
		} else {
			DataBaseHelper.merge(mes);
		}
	}
	
}
