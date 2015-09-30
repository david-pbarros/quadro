package br.com.dbcorp.quadro.gerenciador;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.DesignacaoServico;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Servico;

public class ServicoGerenciador extends Gerenciador {

	public Servico obterServicos(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("FROM Servico s JOIN FETCH s.designacoes d WHERE s.diaReuniao = :dia")
				.setParameter("dia", diaReuniao);
		
		try {
			return (Servico) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void salvarServico(List<Servico> servicos) {
		for (Servico servico : servicos) {
			if (servico.getId() == 0) {
				DataBaseHelper.persist(servico);
			
			} else {
				DataBaseHelper.merge(servico);
			}
			
			for (DesignacaoServico designacao : servico.getDesignacoes()) {
				this.salvaPessoa(designacao.getOrador(), Genero.M);
			}
			
			this.salvaPessoa(servico.getOracao(), Genero.M);
		}
	}
	
}
