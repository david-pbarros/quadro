package br.com.dbcorp.quadro.gerenciador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.entidades.Limpeza;
import br.com.dbcorp.quadro.entidades.MesesDom;

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
	
	public List<String> obtemDatas(Date dataInicial, int[] diasEscolhidos) {
		List<String> datas = new ArrayList<String>();
		
		List<String[]> semanas = setDias(diasEscolhidos[0], dataInicial);
		List<String[]> finds = setDias(diasEscolhidos[1], dataInicial);
		
		for (int i = 0; i < 15; i++) {
			String[] semana = semanas.get(i);
			String[] find = finds.get(i);
			
			StringBuffer sb = new StringBuffer(semana[0])
				.append(" ");
			
			if (!semana[1].equals(find[1])) {
				sb.append("de ")
					.append(semana[1].substring(0, 3))
					.append(". ");
			}
			
			sb.append("e ")
			 	.append(find[0])
			 	.append(" de ")
			 	.append(find[1]);
			
			datas.add(sb.toString());
		}
		
		return datas;
	}
	
	private List<String[]> setDias(int diaEscolhido, Date dtInicial) {
		List<String[]> dias = new ArrayList<String[]>();

		Calendar cd = Calendar.getInstance();
		cd.setTime(dtInicial);
		
		//Seta o mes no primeiro dia
		cd.set(Calendar.DAY_OF_MONTH, 1);
		
		//pega o primeiro dia da semana escolhido no mes
		int weekday = cd.get(Calendar.DAY_OF_WEEK);
		int dayDiff = 7 - (Calendar.SATURDAY - diaEscolhido);
		int days = (Calendar.SATURDAY - weekday + dayDiff) % 7;
		cd.add(Calendar.DAY_OF_YEAR, days);
		
		while (dias.size() < 15) {
			Calendar ct = Calendar.getInstance();
			ct.setTime(cd.getTime());
			
			cd.add(Calendar.DAY_OF_YEAR, 7);
			
			if (cd.getTime().getTime() >= dtInicial.getTime()) {
				String[] temp = new String[2];
				
				temp[0] = Integer.toString(cd.get(Calendar.DAY_OF_MONTH));
				temp[1] = MesesDom.values()[cd.get(Calendar.MONTH)].name();
				
				dias.add(temp);
			}
		}
		
		return dias;
	}
}
