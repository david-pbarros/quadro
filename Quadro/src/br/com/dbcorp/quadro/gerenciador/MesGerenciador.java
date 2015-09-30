package br.com.dbcorp.quadro.gerenciador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Discurso;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.entidades.MesesDom;
import br.com.dbcorp.quadro.entidades.SemanaVisita;
import br.com.dbcorp.quadro.entidades.Servico;

public class MesGerenciador extends Gerenciador {
	
	@SuppressWarnings("unchecked")
	public List<Mes> listarMeses() {
		Query query = DataBaseHelper.createQuery("FROM Mes m");
		
		return query.getResultList();
	}

	public void inserir(Mes mes) {
		DataBaseHelper.persist(mes);
	}
	
	public void atualizar(Mes mes) {
		DataBaseHelper.merge(mes);
	}
	
	@SuppressWarnings("unchecked")
	public void remover(Mes mes) {
		EntityTransaction tx = DataBaseHelper.obterTransacao();

		tx.begin();
		for (DiaReuniao diaReuniao : mes.getDias()) {
			
			
			DataBaseHelper.createQuery("DELETE FROM EquipeServico e WHERE e.diaReuniao = :dia")
				.setParameter("dia", diaReuniao)
			.executeUpdate();
			
			DataBaseHelper.createQuery("DELETE FROM Estudo e WHERE e.diaReuniao = :dia")
				.setParameter("dia", diaReuniao)
			.executeUpdate();
			
			DataBaseHelper.createQuery("DELETE FROM DesignacaoEscola d WHERE d.dia = :dia")
				.setParameter("dia", diaReuniao)
			.executeUpdate();
			
			DataBaseHelper.createQuery("DELETE FROM Discurso d WHERE d.diaReuniao = :dia")
				.setParameter("dia", diaReuniao)
			.executeUpdate();
			
			DataBaseHelper.createQuery("DELETE FROM Sentinela s WHERE s.diaReuniao = :dia")
				.setParameter("dia", diaReuniao)
			.executeUpdate();
			
			Query query = DataBaseHelper.createQuery("FROM Servico s JOIN FETCH s.designacoes d WHERE s.diaReuniao = :dia")
					.setParameter("dia", diaReuniao);
					
			for (Servico servico : (List<Servico>) query.getResultList()) {
				DataBaseHelper.removeWithOutTX(servico);
			}
		}
		
		DataBaseHelper.removeWithOutTX(mes);
		
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	public Mes abrirMes(int diaSemana, int diaFind) {
		Mes mes = new Mes();
		
		Calendar cd = Calendar.getInstance();
		
		Query query = DataBaseHelper.createQuery("FROM Mes m ORDER BY m.id DESC");
		
		List<Mes> meses = query.getResultList();
		
		if (!meses.isEmpty()) {
			Mes mesD = meses.get(0);
			cd.set(Calendar.MONTH, mesD.getMes().ordinal() + 1);
			cd.set(Calendar.YEAR, mesD.getAno());
		}
		
		mes.setAno(cd.get(Calendar.YEAR));
		mes.setMes(MesesDom.values()[cd.get(Calendar.MONTH)]);

		Calendar cd2 = Calendar.getInstance();
		cd2.setTime(cd.getTime());
		
		List<DiaReuniao> dias = new ArrayList<DiaReuniao>();
		this.setDias(dias, diaSemana, cd, "S");
		this.setDias(dias, diaFind, cd2, "F");
		
		mes.setDias(dias);
		
		this.inserir(mes);
		
		return mes;
	}
	
	public SemanaVisita obterVisita(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("SELECT s FROM SemanaVisita s WHERE s.diaSemana.id = :id")
				.setParameter("id", diaReuniao.getId());
		
		try {
			return (SemanaVisita) query.getSingleResult();
		
		} catch (Exception e) {
			return null;
		}
	}
	
	public Servico obterServico(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("SELECT s FROM Servico s WHERE s.diaReuniao.id = :id")
			.setParameter("id", diaReuniao.getId());
		
		try {
			return (Servico) query.getSingleResult();
		
		} catch (Exception e) {
			return null;
		}
	}
	
	public String obterDirigenteEstudo(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("SELECT e.dirigente FROM Estudo e WHERE e.diaReuniao.id = :id")
				.setParameter("id", diaReuniao.getId());
			
		try {
			return (String) query.getSingleResult();
		
		} catch (Exception e) {
			return null;
		}
	}
	
	public DesignacaoEscola obterDestaque(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("SELECT e FROM DesignacaoEscola e WHERE e.numero = '0' AND e.dia.id = :id")
				.setParameter("id", diaReuniao.getId());
			
		try {
			return (DesignacaoEscola) query.getSingleResult();
		
		} catch (Exception e) {
			return null;
		}
	}
	
	public Discurso obterDiscurso(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("SELECT d FROM Discurso d WHERE d.tipo = 'E' AND d.diaReuniao.id = :id")
				.setParameter("id", diaReuniao.getId());
			
		try {
			return (Discurso) query.getSingleResult();
		
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean existeDia(Calendar data) {
		Query query = DataBaseHelper.createQuery("SELECT d FROM DiaReuniao d WHERE d.dia = :dia")
				.setParameter("dia", data.getTime());
		
		List<DiaReuniao> dias = query.getResultList();
		
		if (dias != null && !dias.isEmpty()) {
			return true;
		
		} else {
			return false;
		}
	}
	
	private void setDias(List<DiaReuniao> dias, int diaEscolhido, Calendar cd, String tipo) {
		//Seta o mes no primeiro dia
		cd.set(Calendar.DAY_OF_MONTH, 1);
		
		int mes = cd.get(Calendar.MONTH);
		
		//pega o primeiro dia da semana escolhido no mes
		int weekday = cd.get(Calendar.DAY_OF_WEEK);
		int dayDiff = 7 - (Calendar.SATURDAY - diaEscolhido);
		int days = (Calendar.SATURDAY - weekday + dayDiff) % 7;
		cd.add(Calendar.DAY_OF_YEAR, days);
		
		while (mes == cd.get(Calendar.MONTH)) {
			Calendar ct = Calendar.getInstance();
			ct.setTime(cd.getTime());
			
			DiaReuniao dia = new DiaReuniao();
			dia.setDia(ct.getTime());
			dia.setQuando(tipo);
			dias.add(dia);
			
			cd.add(Calendar.DAY_OF_YEAR, 7);
		}
	}
}
