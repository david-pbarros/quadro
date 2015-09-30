package br.com.dbcorp.quadro.gerenciador;

import java.util.List;

import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.entidades.SemanaVisita;
import br.com.dbcorp.quadro.exceptions.DiaVisitaException;


public class VisitaGerenciador extends Gerenciador{

	public void salvaVisita(SemanaVisita visita) {
		if (visita.getId() == 0) {
			DataBaseHelper.persist(visita);
			
		} else {
			DataBaseHelper.merge(visita);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Mes> obterMesesVisita() {
		Query query = DataBaseHelper.createQuery("SELECT DISTINCT(m) FROM Mes m INNER JOIN m.dias d WHERE d.tipo = 'V' AND d.quando = 'S'");
		
		return query.getResultList();
	}
	
	public SemanaVisita obterVisita(Mes mes) throws DiaVisitaException {
		Query query = DataBaseHelper.createQuery("SELECT v FROM SemanaVisita v INNER JOIN v.mes m  WHERE m.id = :id")
				.setParameter("id", mes.getId());
		
		try {
			SemanaVisita visita = (SemanaVisita) query.getSingleResult();
			visita.getDesignacoesEscola();
			
			return visita;
			
		} catch (Exception e) {
			SemanaVisita visita = new SemanaVisita();
			visita.setMes(mes);
			this.obterDiasReuniao(visita);
			
			return visita;
		}
	}
	
	private void obterDiasReuniao(SemanaVisita visita) throws DiaVisitaException {
		boolean hasDiaSemana = false;
		boolean hasDiaFind = false;
		
		for (DiaReuniao dia : visita.getMes().getDias()) {
			if (dia.getTipoDia() == TipoDia.VISITA) {
				if ("S".equalsIgnoreCase(dia.getQuando())) {
					visita.setDiaSemana(dia);
					
					hasDiaSemana = true;
				
				} else if ("F".equalsIgnoreCase(dia.getQuando())) {
					visita.setDiaFimSemana(dia);
					
					hasDiaFind = true;
				}
			}
		}
		
		if (!hasDiaSemana || !hasDiaFind) {
			this.obterDiasMesSeguinte(visita, hasDiaSemana, hasDiaFind);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void obterDiasMesSeguinte(SemanaVisita visita, boolean hasDiaSemana, boolean hasDiaFind) throws DiaVisitaException {
		Query query = DataBaseHelper.createQuery("SELECT m FROM Mes m WHERE m.ano = :ano AND m.mes <> :mes")
				.setParameter("ano", visita.getMes().getAno())
				.setParameter("mes", visita.getMes().getMes());
		
		List<Mes> meses = query.getResultList();
		
		if (meses != null && !meses.isEmpty()) {
			Mes proximoMes = null;
			
			for (Mes mes : meses) {
				if (mes.getMes().getNumero() == (visita.getMes().getMes().getNumero() + 1)) {
					proximoMes = mes;
					break;
				}
			}
			
			if (proximoMes != null) {
				for (DiaReuniao dia : proximoMes.getDias()) {
					if (dia.getTipoDia() == TipoDia.VISITA) {
						if ("S".equalsIgnoreCase(dia.getQuando()) && !hasDiaSemana) {
							visita.setDiaSemana(dia);
							
						} else if ("F".equalsIgnoreCase(dia.getQuando()) && !hasDiaFind) {
							visita.setDiaFimSemana(dia);
						}
					}
				}
			} else {
				throw new DiaVisitaException();
			}
		} else {
			throw new DiaVisitaException();
		}
	}
}
