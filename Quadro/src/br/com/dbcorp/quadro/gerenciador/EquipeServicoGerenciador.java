package br.com.dbcorp.quadro.gerenciador;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.EquipeServico;
import br.com.dbcorp.quadro.entidades.Genero;

public class EquipeServicoGerenciador extends Gerenciador {
	
	public void salvarServico(List<EquipeServico> equipesServico) {
		for (EquipeServico equipeServico : equipesServico) {
			if (equipeServico.getId() == 0) {
				DataBaseHelper.persist(equipeServico);
			
			} else {
				DataBaseHelper.merge(equipeServico);
			}
			
			this.salvaPessoa(equipeServico.getIndcador1(), Genero.M);
			this.salvaPessoa(equipeServico.getIndcador2(), Genero.M);
			this.salvaPessoa(equipeServico.getIndcador3(), Genero.M);
			this.salvaPessoa(equipeServico.getVolante1(), Genero.M);
			this.salvaPessoa(equipeServico.getVolante2(), Genero.M);
		}
	}
	
	public EquipeServico obterEquipeServicoSemana(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("FROM EquipeServico e WHERE e.diaReuniao = :dia")
				.setParameter("dia", diaReuniao);
		
		try {
			return (EquipeServico) query.getSingleResult();
			
		} catch (NoResultException exception) {
			return null;
		}
	}
	
}
