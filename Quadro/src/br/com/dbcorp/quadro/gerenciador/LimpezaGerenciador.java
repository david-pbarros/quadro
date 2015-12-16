package br.com.dbcorp.quadro.gerenciador;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.Limpeza;

public class LimpezaGerenciador extends Gerenciador {

	@SuppressWarnings("unchecked")
	public List<Limpeza> obtemPrograma() {
		Query query = DataBaseHelper.createQuery("FROM Limpeza l ORDER BY l.seq");
		
		return query.getResultList();
	}
	
	public void salvar(List<Limpeza> limpezas) {
		for (Limpeza limpeza : limpezas) {
			if (DataBaseHelper.find(Limpeza.class, limpeza.getSeq()) != null) {
				DataBaseHelper.merge(limpeza);
				
			} else {
				DataBaseHelper.persist(limpeza);
			}
		}
	}
	
	public List<String> obtemDatas(LocalDate dataInicial, int[] diasEscolhidos) {
		Locale ptBr = new Locale("pt");
		
		List<String> datas = new ArrayList<String>();
		
		List<LocalDate> dias = setDias(diasEscolhidos[0], dataInicial);
		dias.addAll(setDias(diasEscolhidos[1], dataInicial));
		
		dias.sort(Comparator.naturalOrder());
		
		for (int i = 0; i < dias.size(); i = i+2) {
			LocalDate semana = dias.get(i);
			LocalDate find = dias.get(i+1);
			
			StringBuffer sb = new StringBuffer();
			sb.append(semana.getDayOfMonth());
			
			if (semana.getMonth() != find.getMonth()) {
				sb.append(" de ")
					.append(semana.getMonth().getDisplayName(TextStyle.FULL, ptBr));
			}
			
			sb.append(" e ")
				.append(find.getDayOfMonth())
				.append(" de ")
				.append(find.getMonth().getDisplayName(TextStyle.FULL, ptBr));
			
			datas.add(sb.toString());
		}
		
		return datas;
	}
	
	private List<LocalDate> setDias(int diaEscolhido, LocalDate cd) {
		LocalDate date = cd.withDayOfMonth(1);
		
		//pega o primeiro dia da semana escolhido no mes
		int weekday = date.getDayOfWeek().getValue();
		int dayDiff = 6 - (DayOfWeek.SATURDAY.getValue() - diaEscolhido);
		int days = (DayOfWeek.SATURDAY.getValue() - weekday + dayDiff) % 7;
		date = date.plusDays(days);
		
		List<LocalDate> dias = new ArrayList<LocalDate>();
		
		while (dias.size() < 15) {
			if (!date.isBefore(cd)) {
				dias.add(date);
			}
			
			date = date.plusDays(7);
		}
		
		return dias;
	}
}
